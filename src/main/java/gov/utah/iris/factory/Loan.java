package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.iris.manager.HttpCacheManager;
import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.manager.ComplaintItemManager;

/**
 * Loan fraud class.
 * 
 * @author hnguyen
 *
 */
public class Loan extends Fraud {

	public Loan(ReportedFraudsForm formBean, HttpSession session) throws Exception {
		super(FraudEnum.LOAN, formBean);
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
		form.putAll(formBean.getLoan());

		// put suspect onto form
		HttpCacheManager cache = new HttpCacheManager(session);
		form.put("suspects", removeIndex(cache.get("loanSuspect")));
		
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
		form.putAll(formBean.getLoan());
		
		// send form (suspects are already handled in ajax) to webservice to update complaint item
		ComplaintItemManager.updateComplaintItem(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("loan");
		
		initFormData();
		
		// set data
		setData("suspect");
	}

	private void initFormData() {
		formData.put("loanOfficerFirstName", "");
		formData.put("loanOfficerLastName", "");
		formData.put("businessName", "");
		formData.put("businessAddress", "");
		formData.put("businessCity", "");
		formData.put("businessState", "");
		formData.put("businessZipCode", "");
		formData.put("businessPhoneNumber", "");
		formData.put("loanNumber", "");
		formData.put("loanAmount", "");
		formData.put("dateIssued", "");
		formData.put("dateLearned", "");		
		formData.put("howLearned", "");
	}
}
