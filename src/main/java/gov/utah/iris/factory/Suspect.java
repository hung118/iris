package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.common.Constants;
import gov.utah.iris.manager.ComplaintItemManager;

/**
 * Suspect sub item fraud class.
 * 
 * @author hnguyen
 *
 */
public class Suspect extends Fraud {
	
	public Suspect() {
		
	}

	public Suspect(ReportedFraudsForm formBean, HttpSession session) throws Exception {
		super(FraudEnum.valueOf(Constants.getFraudType().get(formBean.getFraudTypeCd())), formBean);
		super.session = session;
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#processFraud()
	 */
	@Override
	public String createFraud() throws Exception {
		// put item values onto form
		form.putAll(formBean.getSuspect());
		form.remove("umdId");
		form.remove("jurisdiction");
		
		// send form to webservice to create suspect
		form.put("personType", "s");
		return ComplaintItemManager.createSuspect(form);
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#updateFraud()
	 */
	@Override
	public void updateFraud() throws Exception {
		// put item values onto form
		form.putAll(formBean.getSuspect());
		
		// send form to webservice to update suspect
		ids.put("personType", "s");
		ComplaintItemManager.updateSuspect(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("suspect");
		
		// init form data
		initFormData();
		
		// set data
		setData("");
	}
	
	public void initFormData() {
		formData.put("firstName", "");
		formData.put("middleName", "");
		formData.put("lastName", "");	
		formData.put("addr1Street", "");
		formData.put("addr1City", "");
		formData.put("addr1State", "");
		formData.put("addr1Zip", "");
		formData.put("emailAddress", "");
		formData.put("eveningPhone", "");
		formData.put("addr2Street", "");
		formData.put("addr2City", "");
		formData.put("addr2State", "");
		formData.put("addr2Zip", "");
		formData.put("emailAddress2", "");
		formData.put("dayPhone", "");
		
		formData.put("dob", "");
		formData.put("ssn", "");
		formData.put("dlNumber", "");	
		formData.put("dlState", "");
		formData.put("race", "");
		formData.put("sex", "");
		formData.put("physicalDescription", "");
		formData.put("gangAssoc", "");
		formData.put("gangMoniker", "");
	}

}
