package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.common.Constants;
import gov.utah.iris.manager.ComplaintItemManager;

/**
 * Issue sub item fraud class.
 * 
 * @author hnguyen
 *
 */
public class Issue extends Fraud {

	public Issue() {
		
	}
	
	public Issue(ReportedFraudsForm formBean, HttpSession session) throws Exception {
		super(FraudEnum.valueOf(Constants.getFraudType().get(formBean.getFraudTypeCd())), formBean);
		super.session = session;
		init();
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#processFraud()
	 */
	@Override
	public String createFraud() throws Exception {
		// put item values onto form
		form.putAll(formBean.getIssue());
		
		// send form to webservice to create complaint item
		return ComplaintItemManager.createComplaintItem(form);
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#updateFraud()
	 */
	@Override
	public void updateFraud() throws Exception {
		// put item values onto form
		form.putAll(formBean.getIssue());
		
		// send form to webservice to update complaint item
		ComplaintItemManager.updateComplaintItem(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("issue");
		
		// init form data
		initFormData();
		
		// set data
		setData("");
	}
	
	public void initFormData() {
		formData.put("issuerName", "");
		formData.put("issuerAddress", "");
		formData.put("issuerCity", "");		
		formData.put("issuerState", "");
		formData.put("issuerZipCode", "");
		formData.put("issuerPhoneNumber", "");
	}

}
