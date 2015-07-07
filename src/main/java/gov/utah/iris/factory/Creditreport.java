package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.iris.manager.HttpCacheManager;
import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.manager.ComplaintItemManager;

/**
 * CREDITREPORT Fraud class.
 * 
 * @author hnguyen
 *
 */
public class Creditreport extends Fraud {

	public Creditreport(ReportedFraudsForm formBean, HttpSession session) throws Exception {
		super(FraudEnum.CREDITREPORT, formBean);
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
		form.putAll(formBean.getCreditreport());
		
		// put accou and trans subitems onto form
		HttpCacheManager cache = new HttpCacheManager(session);
		form.put("issue", removeIndex(cache.get("issue")));

		// put suspect onto form
		form.put("suspects", removeIndex(cache.get("creditreportSuspect")));
		
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
		form.putAll(formBean.getCreditreport());
		
		// send form (sub items are already handled in ajax) to webservice to update complaint item
		ComplaintItemManager.updateComplaintItem(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("creditreport");
		
		initFormData();
		
		// set data
		setData("issue,suspect");
	}

	private void initFormData() {
		formData.put("dateLearned", "");
		formData.put("unrecognizableInquires", "");
		formData.put("accountNumber", "");
		formData.put("creditLimit", "");
		formData.put("dateCreated", "");
		formData.put("isBalanceDue", "");
		formData.put("balanceDue", "");
		formData.put("incidentDesc", "");
	}
}
