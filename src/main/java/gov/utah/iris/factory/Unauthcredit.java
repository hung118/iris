package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.iris.manager.HttpCacheManager;
import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.manager.ComplaintItemManager;

/**
 * UNAUTHCREDIT fraud class.
 * 
 * @author hnguyen
 *
 */
public class Unauthcredit extends Fraud {

	public Unauthcredit(ReportedFraudsForm formBean, HttpSession session) throws Exception {
		super(FraudEnum.UNAUTHCREDIT, formBean);
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
		form.putAll(formBean.getUnauthcredit());
		
		// put tranx subitem onto form
		HttpCacheManager cache = new HttpCacheManager(session);
		form.put("tranx", removeIndex(cache.get("tranx")));
		
		// put suspect onto form
		form.put("suspects", removeIndex(cache.get("unauthcreditSuspect")));
		
		// send form to webservice to create complaint item
		return ComplaintItemManager.createComplaintItem(form);
	}
	
	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#updateFraud()
	 */
	@Override
	public void updateFraud() throws Exception {
		// put item values onto form, subitems already handled.
		form.putAll(formBean.getUnauthcredit());
		
		// send form (sub items are already handled in ajax) to webservice to update complaint item
		ComplaintItemManager.updateComplaintItem(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("unauthcredit");
		
		initFormData();
		
		// set data
		setData("tranx,suspect");
	}

	private void initFormData() {
		formData.put("firstName", "");
		formData.put("middleName", "");
		formData.put("lastName", "");
		formData.put("cardNumber", "");
		formData.put("cardType", "");
		formData.put("issuerName", "");
		formData.put("billingAddress", "");
		formData.put("city", "");
		formData.put("state", "");
		formData.put("zipCode", "");
		formData.put("issuerPhoneNumber", "");
		formData.put("dateAccountOpened", "");
		formData.put("balanceDue", "");
		formData.put("incidentDesc", "");
	}
}
