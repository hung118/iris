package gov.utah.iris.service;

import java.util.List;

import gov.utah.iris.model.AlertTracking;
import gov.utah.iris.model.Complaint;

public interface UserService {

	/**
	 * Save Alert_Tracking entity.
	 * @param alertTracking
	 */
	public void saveAlertTracking(AlertTracking alertTracking);
	
	/**
	 * Delete alert tracking entity.
	 * @param trackingNumber
	 */
	public void deleteAlertTracking(AlertTracking alertTracking);
	
	/**
	 * Find alert trackings.
	 * @param trackingNumber
	 * @return
	 */
	public List<AlertTracking> findAlertTrackings(String trackingNumber);
	
	/**
	 * Delete alert trackings.
	 * @param alertTrackings
	 */
	public void deleteAlertTrackings(List<AlertTracking> alertTrackings);
	
	/**
	 * Get complaint count by id.
	 * @param umdId
	 * @param trackingNumber
	 * @param fraudTypeCd
	 * @return
	 */
	public Integer getComplaintCount(String umdId, String trackingNumber, String fraudTypeCd);
	
	/**
	 * Save complaint entity.
	 * @param complaint
	 */
	public void saveComplaint(Complaint complaint);
	
	/**
	 * Delete complaint by id.
	 * @param umdId
	 * @param trackingNumber
	 * @param fraudTypeCd
	 */
	public void deleteComplaintById(String umdId, String trackingNumber, String fraudTypeCd);
	
	/**
	 * Find complaints by umd id and tracking number.
	 * @param umdId
	 * @param trackingNumber
	 * @return
	 */
	public List<Complaint> findComplaints(String umdId, String trackingNumber);
	
	/**
	 * Get count of law enforce records.
	 * @param umdId
	 * @param trackingNumber
	 * @param fraudTypeId
	 * @return
	 */
	public Integer getLeCount(String umdId, String trackingNumber, String fraudTypeCd);
	
	/**
	 * Find ORI code by tracking number.
	 * @param trackingNumber
	 * @return
	 */
	public String findOri(String trackingNumber);
	
	/**
	 * Delete complaints.
	 * @param complaints
	 */
	public void deleteComplaints(List<Complaint> complaints);
	
}
