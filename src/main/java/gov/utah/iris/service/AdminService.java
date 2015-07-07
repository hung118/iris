package gov.utah.iris.service;

import gov.utah.iris.model.Alert;
import gov.utah.iris.model.AlertJurisdiction;
import gov.utah.iris.model.DwsLogin;
import gov.utah.iris.model.Jurisdiction;

import java.util.List;

public interface AdminService {

	/**
	 * Find all sorted.
	 * 
	 * @return the list
	 */
	public List<Alert> findAllSorted();
	
	/**
	 * Find alet by name and code.
	 * 
	 * @param name
	 * @param code
	 * @return
	 */
	public Alert findAlertByNameCode(String name, String code);
	
	/**
	 * Find jurisdictions by alert name and code 
	 * 
	 * @param name
	 * @param code
	 * @return
	 */
	public List<Jurisdiction> findJurisdictions(String name, String code);
	
	/**
	 * Find all juridictions.
	 * 
	 * @return
	 */
	public List<Jurisdiction> findAllJurisdiction();
	
	/**
	 * Save alert entity.
	 * 
	 * @param alert
	 * @return
	 */
	public Alert saveAlert(Alert alert);
	
	/**
	 * Delete alert entity.
	 * 
	 * @param alert
	 */
	public void deleteAlert(Alert alert);
	
	/**
	 * Save alert juridiction entity.
	 * 
	 * @param aj
	 * @return
	 */
	public AlertJurisdiction saveAlertJurisdiction(AlertJurisdiction aj);
	
	/**
	 * Find all dws login records.
	 * 
	 * @return
	 */
	public List<DwsLogin> findeAllDwsLogins();
	
	/**
	 * Save dws_login record.
	 * 
	 * @param dwsLogin
	 */
	public void saveDwsLogin(DwsLogin dwsLogin);
	
	/**
	 * Find alert url exit.
	 * 
	 * @param trackingNumber
	 * @return
	 */
	public String findAlertUrlExit(String trackingNumber);
	
	/**
	 * find alert email.
	 * @param trackingNumber
	 * @return
	 */
	public String findAlertEmail(String trackingNumber);
}
