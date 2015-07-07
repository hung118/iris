package gov.utah.iris.service;

import gov.utah.iris.model.ComplaintDataPoint;
import gov.utah.iris.model.FraudType;

import java.util.Calendar;
import java.util.List;

public interface MapDataService {

	/**
	 * Find list of ComplaintDataPoint records.
	 * @param type
	 * @param dateKey
	 * @param county
	 * @param city
	 * @param zipCode
	 * @return
	 */
	public List<ComplaintDataPoint> findDataPoints(String type, String dateKey, String county, String city, String zipCode);
	
	/**
	 * Queries AGRC Web service for mapping information: 1) UTM_X and UTM_Y 2) Other info
	 * @param address
	 * @param zipCode
	 * @return
	 * @throws Exception
	 */
	public ComplaintDataPoint getAGRCMapInfo_WS(String streetAddress, String zipCode) throws Exception;
	
	/**
	 * Save data point.
	 * @param cdp
	 */
	public void saveDataPoint(ComplaintDataPoint cdp);
	
	/**
	 * Finds all fraud types.
	 * @return
	 */
	public List<FraudType> findAllFraudTypes();
	
	/**
	 * Gets fraud counts for dashboard.
	 * @param typeId
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Long getDashboardFraudCount(Long typeId, Calendar date1, Calendar date2);
}
