package gov.utah.iris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import gov.utah.iris.model.AlertTracking;
import gov.utah.iris.model.Complaint;
import gov.utah.iris.repository.AlertTrackingRepository;
import gov.utah.iris.repository.ComplaintRepository;
import gov.utah.iris.repository.LawEnforceRepository;

/**
 * Implementation class for UserService interface.
 * 
 * @author hnguyen
 *
 */
public class UserServiceImpl implements UserService {

	@Autowired
	AlertTrackingRepository alertTrackingRepository;
	
	@Autowired
	ComplaintRepository complaintRepository;
	
	@Autowired
	LawEnforceRepository lawEnforceRepository;
		
	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.UserService#saveAlertTracking(gov.utah.iris.model.AlertTracking)
	 */
	@Override
	public void saveAlertTracking(AlertTracking alertTracking) {
		alertTrackingRepository.save(alertTracking);
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.UserService#findAlertTrackings(java.lang.String)
	 */
	@Override
	public List<AlertTracking> findAlertTrackings(String trackingNumber) {
		return alertTrackingRepository.findAlertTrackings(trackingNumber);
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.UserService#deleteAlertTracking(java.lang.String)
	 */
	@Override
	public void deleteAlertTracking(AlertTracking alertTracking) {
		alertTrackingRepository.delete(alertTracking);
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.UserService#deleteAlertTrackings(java.util.List)
	 */
	@Override
	public void deleteAlertTrackings(List<AlertTracking> alertTrackings) {
		alertTrackingRepository.delete(alertTrackings);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.UserService#getComplaintCount(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Integer getComplaintCount(String umdId, String trackingNumber, String fraudTypeCd) {
		return complaintRepository.getComplaintCount(umdId, trackingNumber, fraudTypeCd);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.UserService#saveComplaint(gov.utah.iris.model.Complaint)
	 */
	@Override
	public void saveComplaint(Complaint complaint) {
		complaintRepository.save(complaint);
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.UserService#deleteComplaintById(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteComplaintById(String umdId, String trackingNumber,
			String fraudTypeCd) {
		complaintRepository.deleteById(umdId, trackingNumber, fraudTypeCd);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.UserService#findComplaint(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Complaint> findComplaints(String umdId, String trackingNumber) {
		return complaintRepository.findComplaints(umdId, trackingNumber);
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.UserService#deleteComplaints(java.util.List)
	 */
	@Override
	public void deleteComplaints(List<Complaint> complaints) {
		complaintRepository.delete(complaints);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.UserService#getLeCount(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Integer getLeCount(String umdId, String trackingNumber, String fraudTypeCd) {
		return lawEnforceRepository.getLeCount(umdId, trackingNumber, fraudTypeCd);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.UserService#findOri(java.lang.String)
	 */
	@Override
	public String findOri(String trackingNumber) {
		return alertTrackingRepository.findOriCode(trackingNumber);
	}

}
