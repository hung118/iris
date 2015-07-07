package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.common.Constants;
import gov.utah.iris.manager.ComplaintItemManager;

/**
 * Trans sub item fraud class.
 * 
 * @author hnguyen
 *
 */
public class Trans extends Fraud {

	public Trans() {
		
	}
	
	public Trans(ReportedFraudsForm formBean, HttpSession session) throws Exception {
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
		form.putAll(formBean.getTrans());
		
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
		form.putAll(formBean.getTrans());
		
		// send form to webservice to update complaint item
		ComplaintItemManager.updateComplaintItem(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("trans");
		
		// init form data
		initFormData();
		
		// set data
		setData("");
	}
	
	public void initFormData() {
		formData.put("transDate", "");
		formData.put("transAccountType", "");
		formData.put("transAmount", "");		
		formData.put("transType", "");
		formData.put("transTypeParam", "");
		formData.put("transCompanyOrPerson", "");
		formData.put("transAddress", "");
		formData.put("transCity", "");
		formData.put("transState", "");
		formData.put("transZipCode", "");
		formData.put("transPhoneNumber", "");
	}

}
