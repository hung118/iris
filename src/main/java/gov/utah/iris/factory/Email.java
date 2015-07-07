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
public class Email extends Fraud {

	public Email(ReportedFraudsForm formBean, HttpSession session) throws Exception {
		super(FraudEnum.EMAIL, formBean);
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
		form.putAll(formBean.getEmail());

		// put suspect onto form
		HttpCacheManager cache = new HttpCacheManager(session);
		form.put("suspects", removeIndex(cache.get("emailSuspect")));
		
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
		form.putAll(formBean.getEmail());
		
		// send form (suspects are already handled in ajax) to webservice to update complaint item
		ComplaintItemManager.updateComplaintItem(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("email");
		
		initFormData();
		
		// set data
		setData("suspect");
	}

	private void initFormData() {
		formData.put("sendersEmail", "");
		formData.put("recipientsEmail", "");
		formData.put("dateReceived", "");
		formData.put("dateProvided", "");
		formData.put("providedInfo", "");
		formData.put("description", "");
	}
}
