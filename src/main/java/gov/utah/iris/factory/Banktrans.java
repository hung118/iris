package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.iris.manager.HttpCacheManager;
import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.manager.ComplaintItemManager;

/**
 * BANKTRANS fraud class.
 * 
 * @author hnguyen
 *
 */
public class Banktrans extends Fraud {

	public Banktrans(ReportedFraudsForm formBean, HttpSession session) throws Exception {
		super(FraudEnum.BANKTRANS, formBean);
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
		form.putAll(formBean.getBanktrans());
		
		// put accou and trans subitems onto form
		HttpCacheManager cache = new HttpCacheManager(session);
		form.put("accou", removeIndex(cache.get("accou")));
		form.put("trans", removeIndex(cache.get("trans")));
		
		// put suspect onto form
		form.put("suspects", removeIndex(cache.get("banktransSuspect")));
		
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
		form.putAll(formBean.getBanktrans());
		
		// send form (sub items are already handled in ajax) to webservice to update complaint item
		ComplaintItemManager.updateComplaintItem(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("banktrans");
		
		initFormData();
		
		// set data
		setData("accou,trans,suspect");
	}

	private void initFormData() {
		formData.put("bankName", "");
		formData.put("bankAddress", "");
		formData.put("bankCity", "");
		formData.put("bankState", "");
		formData.put("bankZipCode", "");
		formData.put("bankPhoneNumber", "");
		formData.put("contactFirstName", "");
		formData.put("contactLastName", "");
		formData.put("contactPhoneNumber", "");
		formData.put("dateLearned", "");
		formData.put("incidentDesc", "");
	}
}
