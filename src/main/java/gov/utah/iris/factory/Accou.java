package gov.utah.iris.factory;

import javax.servlet.http.HttpSession;

import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.common.Constants;
import gov.utah.iris.manager.ComplaintItemManager;

/**
 * Accou sub item fraud class.
 * 
 * @author hnguyen
 *
 */
public class Accou extends Fraud {

	public Accou() {
		
	}
	
	public Accou(ReportedFraudsForm formBean, HttpSession session) throws Exception {
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
		form.putAll(formBean.getAccou());
		
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
		form.putAll(formBean.getAccou());
		
		// send form to webservice to update complaint item
		ComplaintItemManager.updateComplaintItem(form, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see gov.utah.iris.factory.Fraud#selectFraud()
	 */
	@Override
	public void selectFraud() throws Exception {
		setFormName("accou");
	
		initFormData();
		
		// set data
		setData("");
	}
	
	public void initFormData() {
		formData.put("accountHolderType", "");
		formData.put("accountHolderFirstName", "");
		formData.put("accountHolderMiddleName", "");
		formData.put("accountHolderLastName", "");
	}
}
