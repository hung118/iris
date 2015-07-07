package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.common.Constants;
import gov.utah.iris.manager.ComplaintItemManager;

/**
 * Tranx sub item fraud class.
 * 
 * @author hnguyen
 *
 */
public class Tranx extends Fraud {

	public Tranx() {
		
	}
	
	public Tranx(ReportedFraudsForm formBean, HttpSession session) throws Exception {
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
		form.putAll(formBean.getTranx());
		
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
		form.putAll(formBean.getTranx());
		
		// send form to webservice to update complaint item
		ComplaintItemManager.updateComplaintItem(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("tranx");
		
		// init form data
		initFormData();
		
		// set data
		setData("");
	}
	
	public void initFormData() {
		formData.put("tranxAmount", "");
		formData.put("tranxDate", "");
		formData.put("tranxRecipientType", "");		
		formData.put("tranxFirstName", "");
		formData.put("tranxMiddleName", "");
		formData.put("tranxLastName", "");
		formData.put("tranxBusinessName", "");
		formData.put("tranxAddress", "");
		formData.put("tranxCity", "");
		formData.put("tranxState", "");
		formData.put("tranxZipCode", "");
	}

}
