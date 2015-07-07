package gov.utah.iris.service;

import gov.utah.iris.model.Alert;
import gov.utah.iris.model.AlertJurisdiction;
import gov.utah.iris.model.DwsLogin;
import gov.utah.iris.model.Jurisdiction;
import gov.utah.iris.repository.AlertJurisdictionRepository;
import gov.utah.iris.repository.AlertRepository;
import gov.utah.iris.repository.DwsLoginRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService {

	@Autowired
	AlertRepository alertRepository;
	
	@Autowired
	AlertJurisdictionRepository ajRepository;

	@Autowired
	DwsLoginRepository dwsLoginRepository;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.utah.gomb.service.AgencyService#findAllSorted()
	 */
	@Override
	public List<Alert> findAllSorted() {
		return alertRepository.findAllSorted();
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.AdminService#findAlertByNameCode(java.lang.String, java.lang.String)
	 */
	@Override
	public Alert findAlertByNameCode(String name, String code) {
		return alertRepository.findbyNameCode(name, code);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.AdminService#findJurisdictions(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Jurisdiction> findJurisdictions(String name, String code) {
		return alertRepository.findJurisdictions(name, code);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.AdminService#findAllJurisdiction()
	 */
	@Override
	public List<Jurisdiction> findAllJurisdiction() {
		return alertRepository.findAllJurisdictions();
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.AdminService#saveAlert(gov.utah.iris.model.Alert)
	 */
	@Override
	public Alert saveAlert(Alert alert) {
		return alertRepository.save(alert);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.AdminService#saveAlertJurisdiction(gov.utah.iris.model.AlertJurisdiction)
	 */
	@Override
	public AlertJurisdiction saveAlertJurisdiction(AlertJurisdiction aj) {
		return ajRepository.save(aj);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.AdminService#deleteAlert(gov.utah.iris.model.Alert)
	 */
	@Override
	public void deleteAlert(Alert alert) {
		alertRepository.delete(alert);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.AdminService#findeAllDwsLogins()
	 */
	@Override
	public List<DwsLogin> findeAllDwsLogins() {
		return dwsLoginRepository.findAllSorted();
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.AdminService#saveDwsLogin(gov.utah.iris.model.DwsLogin)
	 */
	@Override
	public void saveDwsLogin(DwsLogin dwsLogin) {
		dwsLoginRepository.save(dwsLogin);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.AdminService#findAlertUrlExit(java.lang.String)
	 */
	@Override
	public String findAlertUrlExit(String trackingNumber) {
		return alertRepository.findAlertUrlExit(trackingNumber);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.service.AdminService#findAlertEmail(java.lang.String)
	 */
	@Override
	public String findAlertEmail(String trackingNumber) {
		return alertRepository.findAlertEmail(trackingNumber);
	}

}
