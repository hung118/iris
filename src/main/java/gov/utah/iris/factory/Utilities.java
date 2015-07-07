package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.iris.manager.HttpCacheManager;
import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.manager.ComplaintItemManager;

/**
 * Fraud class.
 * 
 * @author hnguyen
 *
 */
public class Utilities extends Fraud {

	public Utilities(ReportedFraudsForm formBean, HttpSession session) throws Exception {
		super(FraudEnum.UTILITIES, formBean);
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
		form.putAll(formBean.getUtilities());

		// put suspect onto form
		HttpCacheManager cache = new HttpCacheManager(session);
		form.put("suspects", removeIndex(cache.get("utilitiesSuspect")));
		
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
		form.putAll(formBean.getUtilities());
		
		// send form to webservice to update complaint item
		ComplaintItemManager.updateComplaintItem(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("utilities");
		
		initFormData();
		
		// set data
		setData("suspect");
	}

	private void initFormData() {
		formData.put("accountNumber", "");
		formData.put("utilitiesType", "");
		formData.put("companyName", "");
		formData.put("address", "");
		formData.put("city", "");
		formData.put("state", "");
		formData.put("zipCode", "");
		formData.put("phoneNumber", "");
		formData.put("firstName", "");
		formData.put("lastName", "");
		formData.put("dateEstablished", "");
		formData.put("amountOwed", "");
		formData.put("dateLearned", "");
		formData.put("howLearned", "");
	}
}
