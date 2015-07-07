package gov.utah.iris.service;

import gov.utah.iris.model.ComplaintDataPoint;
import gov.utah.iris.model.FraudType;
import gov.utah.iris.repository.ComplaintDataPointRepository;
import gov.utah.iris.repository.FraudTypeRepository;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * returns JSON with points that match the search requested, queries AGRC Web service for mapping
 * information, and other DB related tasks.
 * 
 * @author CCUPP & HNGUYEN
 * 
 */
@Transactional
public class MapDataServiceImpl implements MapDataService {

	@Autowired
	ComplaintDataPointRepository complainDataPointRepository;
	
	@Autowired
	FraudTypeRepository ftRepository;
	
	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.MapDataService#findDataPoints(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ComplaintDataPoint> findDataPoints(String type, String dateKey,
			String county, String city, String zipCode) {
		return complainDataPointRepository.findDataPoints(type, dateKey, county, city, zipCode);
	}
	
	/**
	 * Queries AGRC Web service for mapping information: 1) UTM_X and UTM_Y 2) Other info
	 *
	 * @param zipCode
	 * @return
	 * @throws Exception
	 */
	public ComplaintDataPoint getAGRCMapInfo_WS(String streetAddress, String zipCode) throws Exception {
		try {
            ComplaintDataPoint cdp = new ComplaintDataPoint();

            UTSGID_GeoLocatorSoap geolocator = new UTSGID_GeoLocatorSoapStub(
                    new URL("http://mapserv.utah.gov/WSUTSGID_Geolocator/Default.asmx"),
                    new org.apache.axis.client.Service());

            GeocodeResult geocodeAddress = geolocator.geocodeAddress("hnguyen", streetAddress, zipCode);
            // 1) set UTM_X and UTM_Y
            cdp.setUtmEasting(new Float(geocodeAddress.getUTM_X()));
            cdp.setUtmNorthing(new Float(geocodeAddress.getUTM_Y()));

            // 2) set other info: address, zip, city, county, and ori
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
            if (spatialQueryResult[0].getValue() != null && spatialQueryResult[0].getValue().length() > 0) {    // municipality found
                fips = spatialQueryResult[0].getValue();
            } else {    // not found. Query counties to find fips instead, since the point is not in a municipality
                spatialQuery.setSGIDLayerName("SGID10.BOUNDARIES.Counties");
                spatialQueryResponse = muni.getFeatureAttributes(spatialQuery);
                spatialQueryResult = spatialQueryResponse.getSpatialQueryResult();
                if (spatialQueryResult[0].getValue() != null && spatialQueryResult[0].getValue().length() > 0) {    // found in county
                    fips = spatialQueryResult[0].getValue();
                }
            }

            // query agencies
            String[] aAttributeList = {"MUNICIPALITY", "ORI", "COUNTY", "AGENCY"};
            AttributeQuery attributeQuery = new AttributeQuery("hnguyen", "SGID10.BOUNDARIES.UtahLawEnforcementAgencies",
                    aAttributeList, "FIPS", fips, "=");
            AttributeQueryResponse attributeQueryResponse = muni.getFeatureAttributes(attributeQuery);
            Attribute[] attributeQueryResult = attributeQueryResponse.getAttributeQueryResult();

            for (int i = 0; i < attributeQueryResult.length; i++) {
                if ("MUNICIPALITY".equals(attributeQueryResult[i].getField()))
                    cdp.setCity(attributeQueryResult[i].getValue());
                if ("ORI".equals(attributeQueryResult[i].getField()))
                    cdp.setJurisdictionCode(attributeQueryResult[i].getValue());
                if ("COUNTY".equals(attributeQueryResult[i].getField()))
                    cdp.setCounty(attributeQueryResult[i].getValue());
            }

            cdp.setAddress1(streetAddress);
            cdp.setZip(zipCode);

            return cdp;
        } catch (Exception e) {
            System.out.println("** Error in getAGRCMapInfo_WS() ..." + e.getMessage());
            e.printStackTrace();
            throw new Exception(e);
        }
	}
	
	@Override
	public void saveDataPoint(ComplaintDataPoint cdp) {
		complainDataPointRepository.save(cdp);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.MapDataService#findAllFraudTypes()
	 */
	@Override
	public List<FraudType> findAllFraudTypes() {
		Iterable<FraudType> e = ftRepository.findAll();
		ArrayList<FraudType> l = new ArrayList<FraudType>();
		if (e != null) {
			for (FraudType ft : e) {
				l.add(ft);
			}
		}
		
		return l;
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.MapDataService#getDashboardFraudCount(java.lang.Long, java.lang.String)
	 */
	@Override
	public Long getDashboardFraudCount(Long typeId, Calendar date1, Calendar date2) {
		return complainDataPointRepository.getDashboardFraudCount(typeId, date1, date2);
	}
	
}