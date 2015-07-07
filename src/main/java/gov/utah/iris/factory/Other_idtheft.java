package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.manager.ComplaintItemManager;

/**
 * Fraud class.
 * 
 * @author hnguyen
 *
 */
public class Other_idtheft extends Fraud {

	public Other_idtheft(ReportedFraudsForm formBean, HttpSession session) throws Exception {
		super(FraudEnum.OTHER_IDTHEFT, formBean);
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
		form.putAll(formBean.getOther_idtheft());
		
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
		form.putAll(formBean.getOther_idtheft());
		
		// send form to webservice to update complaint item
		ComplaintItemManager.updateComplaintItem(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("other_idtheft");
		
		initFormData();
		
		// set data
		setData("");
	}

	private void initFormData() {
		formData.put("holder", "");
		formData.put("firstName", "");
		formData.put("middleName", "");
		formData.put("lastName", "");
		formData.put("ssn", "");
		formData.put("incidentDate", "");
		formData.put("incidentDesc", "");
	}
}
