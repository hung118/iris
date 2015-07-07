package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.iris.manager.HttpCacheManager;
import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.manager.ComplaintItemManager;

/**
 * COLLECTOR fraud class.
 * 
 * @author hnguyen
 *
 */
public class Collector extends Fraud {

	public Collector(ReportedFraudsForm formBean, HttpSession session) throws Exception {
		super(FraudEnum.COLLECTOR, formBean);
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
		form.putAll(formBean.getCollector());
		
		// put accou and trans subitems onto form
		HttpCacheManager cache = new HttpCacheManager(session);
		form.put("colle", removeIndex(cache.get("colle")));
		form.put("credi", removeIndex(cache.get("credi")));
		
		// put suspect onto form
		form.put("suspects", removeIndex(cache.get("collectorSuspect")));
		
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
		form.putAll(formBean.getCollector());
		
		// send form (sub items are already handled in ajax) to webservice to update complaint item
		ComplaintItemManager.updateComplaintItem(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("collector");
		
		initFormData();
		
		// set data
		setData("colle,credi,suspect");
	}

	private void initFormData() {
		formData.put("firstContactDate", "");
		formData.put("stillReceivingCalls", "");
		formData.put("incidentDesc", "");
	}
}
