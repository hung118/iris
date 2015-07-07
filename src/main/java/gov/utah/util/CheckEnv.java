package gov.utah.util;

import gov.utah.iris.common.Constants;
import gov.utah.iris.common.Dao;
import gov.utah.iris.manager.PersistantManagerImpl;
import gov.utah.iris.manager.interfaces.PersistantManager;
import gov.utah.das.its.AppProfile.Connection;
import gov.utah.das.its.AppProfile.Profile;
import gov.utah.das.its.AppProfile.Client.APClient;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Utility class checks if Siteminder, AGRC address lookup, Web Services, and data source
 * are up and running.
 * 
 * This class is used in showEnvironment.jsp.
 * 
 * @author HNGUYEN
 *
 */
public class CheckEnv {

	static String PROFILE_SERVER = null;
	static String PROFILE_SERVER_BAK = null;
	static String PROFILE_SERVER_LOGS = null;
	static String PROFILE_NAME = null;
	static String CONNECTION_KEY = null;
	static String WEBSERVICE_SERVER = null;

	static {
		try {
			ResourceBundle prop = ResourceBundle.getBundle("iris");
            PROFILE_SERVER = prop.getString("profile_server");
            PROFILE_SERVER_BAK = prop.getString("profile_server_bak");
            PROFILE_SERVER_LOGS = prop.getString("profile_server_logs");
			PROFILE_NAME = prop.getString("profile_name");
			CONNECTION_KEY = prop.getString("connection_key");
			WEBSERVICE_SERVER = prop.getString("webservice_server");
		} catch (MissingResourceException e) {}
	}
	
	public CheckEnv() {}
	
	public String checkSiteminder(String umdId) throws Exception {
		
		System.out.println("Entering checkSiteminder ...");
		
		APClient.initialize(PROFILE_SERVER, PROFILE_SERVER_BAK, PROFILE_SERVER_LOGS, true);
		Connection conn = null;
		StringBuffer sb = new StringBuffer("<strong>Checking ...</strong><br/>\n");
		try {
			conn = APClient.getConnection(CONNECTION_KEY);

			Profile profile = conn.getProfile(umdId, PROFILE_NAME);
			if (profile.is("Administrator")) {
				sb.append("Success! Login user is an administrator.");
			} else {
				sb.append("Success! Login user is not an administrator.");
			}
		} catch (Exception e) {
			sb.append("There is an error when connecting to Siteminder server, <strong>" + PROFILE_SERVER +
					"</strong>, with app profile, <Strong>" + PROFILE_NAME + "</strong>: <br />");
			sb.append("<font color=red>" + e.getMessage() + "</font>");
			
			System.out.println("UMD Error message: " + e.getMessage());
		} finally {
			conn.close();
		}
		
		return sb.append("<br /><br />").toString();
	}
	
	public String checkAddressLookup_old() {
		
    	String remoteUrl = "http://atlas.utah.gov/servlet/com.esri.esrimap.Esrimap?ClientVersion=4.0&ServiceName=AGidtheft&CustomService=Geocode&Form=False&Encode=False";
    	String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ARCXML version=\"1.1\"><REQUEST><GET_GEOCODE minscore=\"35\" maxcandidates=\"1\"><LAYER id=\"geocode\"/><ADDRESS><GCTAG value=\"118 N 1660 W\" id=\"STREET\"/><GCTAG value=\"\" id=\"CROSSSTREET\"/><GCTAG value=\"84015\" id=\"ZONE\"/></ADDRESS></GET_GEOCODE></REQUEST></ARCXML>";
    	StringBuffer sb = new StringBuffer("<strong>Checking ...</strong><br/>\n");
		StringBuffer result = new StringBuffer();
    	
		try {
            URL url = new URL(remoteUrl);
            URLConnection urlConn = url.openConnection();
            urlConn.setRequestProperty("Content-Type", "text/xml");
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);

            // send xml
            PrintWriter pw = new PrintWriter(urlConn.getOutputStream()); //urlConn.getOutputStream()
            pw.write(xmlStr);
            pw.close();

            // get result
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
            	result.append(inputLine);
            }
                
            sb.append("Success! Query returns:<br />" + result.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
            in.close();
		} catch (Exception e) {
			sb.append("There is an error when connecting to AGRC address lookup server:<br />")
			.append("<strong>" + remoteUrl + "</strong><br />")
			.append("with xml string: <br />)")
			.append("<Strong>" + xmlStr.replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "</strong>: <br />")
			.append("<font color=red>" + e.getMessage() + "</font>");							
		}
		
		return sb.append("<br /><br />").toString();
	}

	public String checkAddressLookup() {
		
		System.out.println("Entering checkAddressLookup ...");
		
		String streetAddress = "252 S Eagle Ridge Dr";
		String zipCode = "84054";
		StringBuffer sb = new StringBuffer("<strong>Checking ...</strong><br/>\n");
        String precinct = null;
    	try {
            UTSGID_GeoLocatorSoap geolocator = new  UTSGID_GeoLocatorSoapStub(
            		new URL("http://mapserv.utah.gov/WSUTSGID_Geolocator/Default.asmx"), 
            		new org.apache.axis.client.Service());
            
            GeocodeResult  geocodeAddress = geolocator.geocodeAddress("hnguyen", streetAddress, zipCode);
            
            WSUTSGID_FeatureAttributesSoap muni = new WSUTSGID_FeatureAttributesSoapStub(
            		new URL("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes/default.asmx"), 
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
                
            sb.append("Success! Query returns:<br />" + precinct);
            
		} catch (Exception e) {
			sb.append("There is an error when connecting to AGRC address lookup server with an error:<br />")
			.append("<font color=red>" + e.getMessage() + "</font>");	
			
			System.out.println("checkingAddressLookup error message: " + e.getMessage());
		}
		
		return sb.append("<br /><br />").toString();
	}

	@SuppressWarnings("rawtypes")
	public String checkWebService(String umdId) {
		
		System.out.println("Entering checkWebService ...");
		
		StringBuffer sb = new StringBuffer("<strong>Checking ...</strong><br/>\n");
		try {			
			PersistantManager manager = new PersistantManagerImpl();
			Map<String, String> ids = new HashMap<String, String>();
			ids.put(Constants.UMD_ID, umdId);
			
			Map obj = manager.select(ids, "person");
			sb.append("Success! Query returns from Web service, <strong>" + WEBSERVICE_SERVER + "</strong>")
			.append(": <br />" + (String)obj.get("firstName") + " " + (String)obj.get("lastName"));
		} catch (Exception e) {
			sb.append("There is an error when connecting to Web service, <strong>" + WEBSERVICE_SERVER + "</strong>:<br />")
			.append("<font color=red>" + e.getMessage() + "</font>");	
			
			System.out.println("checkWebservice error message: " + e.getMessage());
		}
		
		return sb.append("<br /><br />").toString();
	}
	
	public String checkDatabase() throws Exception {
		
		System.out.println("Entering checkDatabase ...");
		
		Dao dao = new Dao();		
		StringBuffer sb = new StringBuffer("<strong>Checking ...</strong><br/>\n");
		try {
			ResultSet rs = dao.executeQuery("select count(*) from dws_login");
			rs.next();
			sb.append("Success! Database query returns <strong>" + rs.getInt(1) + " records</strong> from DWS_LOGIN table.");
		} catch (Exception e) {
			sb.append("There is an error when connecting to database:<br />")
			.append("<font color=red>" + e.getMessage() + "</font>");
			
			System.out.println("checkDatabase error message: " + e.getMessage());
		} finally {
			dao.closeConnection();
		}
		
		return sb.append("<br /><br />").toString();
	}
}
