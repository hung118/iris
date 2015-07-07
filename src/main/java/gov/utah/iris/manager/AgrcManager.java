package gov.utah.iris.manager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStream;

import gov.utah.mapserv.WSUTSGID_FeatureAttributes.Attribute;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.AttributeQuery;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.AttributeQueryResponse;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.SpatialQuery;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.SpatialQueryResponse;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoap;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoapStub;
import gov.utah.mapserv.WSUTSGID_Geolocator.GeocodeResult;
import gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoap;
import gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoapStub;
import net.yagga.util.MetaJarResources;

/**
 * AGRC address/ORI lookup Webservice client class.
 * 
 * @author hnguyen
 *
 */
public class AgrcManager {

    private static Log logger = LogFactory.getLog(AgrcManager.class);

    /**
     * Returns the jurisdiction Id by extracting it from the current form jurisdiction property. Or if
     * the jurisdiction property is not present, uses the current users address
     * @param form
     * @param request
     * @return
     */
    public static String getJurisdictionId(Map<String, Object> form, String ori, String defaultAddress, String defaultZipCode) throws Exception {
    	// check for security alert
    	if (!"Not Found".equals(ori)) {
    		if ("UT018015A".equals(ori)) {	// UT018015A is DEFAULT ORID, ie ID Fraud Unit
    			return "DEFAULT";
    		} else {		// return ORI from alert form page
    			return ori;		
    		}
    	}

        String address = null;
        String crossStreet = null;
        String zipCode = null;
        String defaultCrossStreet = null;
        String precinctsParams = null;

        if ( form.get("jurisdiction") == null ) {	// jurisdiction mapping does not exist
            //useDefaultAddress = true;
        } else {	// jurisdiction mapping exists, but may be empty
			Map<String, String> jurisdiction = AgrcManager.parseJurisdiction((String)form.remove("jurisdiction"));
            address = (String)form.get((String)jurisdiction.get("address"));
            zipCode = (String)form.get((String)jurisdiction.get("zipCode"));

            if ( address == null || "".equals(address) ) {
                //log.debug("#-> no street address. cannot lookup jurisdiction with supplied address...");
                //useDefaultAddress = true;
            }
            else if ( zipCode == null || "".equals(zipCode) ) {
                //log.debug("#-> no zipCode. cannot lookup jurisdiction with supplied address...");
                //useDefaultAddress = true;
            }
        }

        String precinct = getPrecinctId(address, crossStreet, zipCode, defaultAddress, defaultCrossStreet, defaultZipCode, precinctsParams);
        return precinct;
    }

    /**
     * Parses the jurisdiction information from the jurisdiction string
     * @param jurisdiction - String of name value pairs - (address=123 easy st;city=salt lake city;state=ut;zipCode=84101)
     * @return Map
     */
    public static Map<String, String> parseJurisdiction(String jurisdiction) {
        Map<String, String> jurisMap = new HashMap<String, String>();
        String parts[] = jurisdiction.split(";");
        for ( int i=0; i < parts.length; i++ ) {
            String part[] = parts[i].split("=");
            if ( part.length == 2 ) {
                jurisMap.put(part[0], part[1]);
            }
        }

        return jurisMap;
    }

    /**
     * Returns the InputStream of the specified templateName
     * @param templateName
     * @return
     */
    public static InputStream getTemplateStream( String templateName ) {
        MetaJarResources jarResource = new MetaJarResources("agrc-client");
        return jarResource.openResource(templateName);

        //String resource = "gov/iris/resources/xml/" + templateName;
        //log.debug("#-> getting resource: " + resource);
        //return AgrcManager.class.getClassLoader().getResourceAsStream(resource);
    }

    /**
     *
     * @param address
     * @param crossStreet
     * @param zipCode
     * @param defaultAddress
     * @param defaultCrossStreet
     * @param defaultZipCode
     * @param precinctsParams
     * @return
     */
    private static String getPrecinctId(String address, String crossStreet, String zipCode, String defaultAddress, String defaultCrossStreet, String defaultZipCode, String precinctsParams) {
        final String DEFAULT_PRECINCT = "default";
        String precinct = DEFAULT_PRECINCT;
        String addr = null;
        String cross = null;
        String zip = null;
        //boolean suppliedAddress = false;
        boolean suppliedDefault = false;

        if ( address != null && zipCode != null ) {
            //log.debug("#-> using supplied address and zipCode...");
            addr = address;
            cross = crossStreet;
            zip = zipCode;
            //suppliedAddress = true;
        } else if ( defaultAddress != null && defaultZipCode != null ) {
            //log.debug("#-> using supplied defaultAddress and defaultZipCode...");
            addr = defaultAddress;
            cross = defaultCrossStreet;
            zip = defaultZipCode;
            suppliedDefault = true;
        } else {
            return null;
        }

        // get the precinct
        precinct = getPrecinctId(addr, cross, zip);
        if ( precinct == null ) {
            //log.debug("#-> precinct was null...");
            // try again with defaults if defaults were not used
            if ( !suppliedDefault ) {
                //log.debug("#-> trying again with victim's address...");
                precinct = getPrecinctId(defaultAddress, defaultCrossStreet, defaultZipCode);
                if ( precinct == null ) {
                    //log.debug("#-> precinct was still null. using default...");
                    precinct = DEFAULT_PRECINCT;
                }
            } else {
                //log.debug("#-> victim address was already used. setting precinct to default...");
                precinct = DEFAULT_PRECINCT;
            }
        }

        logger.info("#-> returning precinct: " + precinct);
        return precinct;
    }


    /**
     * This method attempts to retreive the precinct (ORI) for the specified address and zipCode
     * This code works for the new server https://mapserv.utah.gov. Argument crossStreet is not used.
     * Refer to axis-1_4 in docs/AGRCWebsite directory.
     * @param address
     * @param crossStreet
     * @param zipCode
     * @return
     */
    private static String getPrecinctId( String streetAddress, String crossStreet, String zipCode ) {
        if ( streetAddress == null || zipCode == null ) {
            //throw new IllegalArgumentException("Address and zipCode must not be null.");
            return null;	// will be default to fraud id unit ORI.
        }

        String precinct = null;
    	try {
            UTSGID_GeoLocatorSoap geolocator = new  UTSGID_GeoLocatorSoapStub(
            		new URL("https://mapserv.utah.gov/WSUTSGID_Geolocator/Default.asmx"), 
            		new org.apache.axis.client.Service());
            
            GeocodeResult  geocodeAddress = geolocator.geocodeAddress("hnguyen", streetAddress, zipCode);
            
            WSUTSGID_FeatureAttributesSoap muni = new WSUTSGID_FeatureAttributesSoapStub(
            		new URL("https://mapserv.utah.gov/WSUTSGID_FeatureAttributes/default.asmx"), 
            		new org.apache.axis.client.Service());
            
            // query fips
            String[] sAttributeList = {"FIPS"};
            SpatialQuery spatialQuery = new SpatialQuery("hnguyen", "SGID10.BOUNDARIES.Municipalities", sAttributeList, 
            		geocodeAddress.getUTM_X(), geocodeAddress.getUTM_Y());
            SpatialQueryResponse spatialQueryResponse = muni.getFeatureAttributes(spatialQuery);
            
            String fips = null;
            Attribute[] spatialQueryResult = spatialQueryResponse.getSpatialQueryResult();
            if (spatialQueryResult[0].getValue() != null && spatialQueryResult[0].getValue().length() > 0) {	// municipality found
            	fips = spatialQueryResult[0].getValue();
            } else {	// not found. Query counties to find fips instead, since the point is not in a municipality
            	spatialQuery.setSGIDLayerName("SGID10.BOUNDARIES.Counties");
            	spatialQueryResponse = muni.getFeatureAttributes(spatialQuery);
            	if (spatialQueryResult[0].getValue() != null && spatialQueryResult[0].getValue().length() > 0) {	// found in county
            		fips = spatialQueryResult[0].getValue();
            	} else {	// also not found in county, return null ie default to fraud id unit ORI
            		return null;
            	}
            }
            
            // query agencies
            String[] aAttributeList = {"MUNICIPALITY", "ORI", "COUNTY", "AGENCY"};
            AttributeQuery attributeQuery = new AttributeQuery("hnguyen", "SGID10.BOUNDARIES.UtahLawEnforcementAgencies",
            		aAttributeList, "FIPS", fips, "=");
            AttributeQueryResponse attributeQueryResponse = muni.getFeatureAttributes(attributeQuery);
            Attribute[] attributeQueryResult = attributeQueryResponse.getAttributeQueryResult();
            for (int i = 0; i < attributeQueryResult.length; i++) {
            	if ("ORI".equals(attributeQueryResult[i].getField())) {
            		precinct = attributeQueryResult[i].getValue();
            	}
            }
            
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;	// will be default to fraud id unit ORI.
    	}
        
        return precinct;
    }
}
