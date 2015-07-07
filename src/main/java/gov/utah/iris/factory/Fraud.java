package gov.utah.iris.factory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.common.Constants;
import gov.utah.iris.manager.AgrcManager;
import gov.utah.iris.manager.ComplaintItemManager;
import gov.utah.iris.manager.HttpCacheManager;

/**
 * This abstract class is the parent class of all fraud instances and it will contains the common logic applicable in
 * fraud processing of all fraud types.
 * 
 * @author hnguyen
 *
 */
public abstract class Fraud {
	
	protected HttpSession session;
	protected FraudEnum fraudType;
	protected ReportedFraudsForm formBean;
	protected Map<String, Object> form = new HashMap<String, Object>();
	protected Map<String, String> ids = new HashMap<String, String>();
	protected String formName;
	protected Map<String, String> formData = new HashMap<String, String>();

	public Fraud() {
		
	}
	
	public Fraud(FraudEnum fraudType, ReportedFraudsForm formBean) {
		this.fraudType = fraudType;
		this.formBean = formBean;
	}

	/**
	 * Create a fraud in web service.
	 * @throws Exception
	 */
	public abstract String createFraud() throws Exception;
		
	/**
	 * Update a fraud in web service.
	 * @throws Exception
	 */
	public abstract void updateFraud() throws Exception;
	
	/**
	 * retrieve fraud from web service.
	 * @throws Exception
	 */
	public abstract void selectFraud() throws Exception;
	
	/**
	 * Data initialization.
	 * @throws Exception
	 */
	protected void init() throws Exception {
		form.put(Constants.UMD_ID, formBean.getUmdId());
		form.put("trackingNumber", formBean.getTrackingNumber());
		form.put("fraudTypeCd", FraudEnum.getString(fraudType));
		if (formBean.getFraudTypeSeq() != null) {
			form.put("fraudTypeSeq", String.valueOf(formBean.getFraudTypeSeq()));
		}
		
		// put AGRC lookup values onto form
        String jurisdictionId = AgrcManager.getJurisdictionId(form, formBean.getAlertORI(), formBean.getDefaultAddress(), formBean.getDefaultZipCode());
        if (jurisdictionId != null && !"".equals(jurisdictionId)) {
            form.put("jurisdiction", jurisdictionId);
        } else {
        	throw new Exception("Cannot find jurisdiction!");
        }
	}

	/**
	 * Get values from webservice to set data to be retrieved from caller.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected void setData(String subItems) throws Exception {
		form.remove("jurisdiction");
		Map<String, String>ids = new HashMap<String, String>();
		ids.put(Constants.UMD_ID, (String)form.get(Constants.UMD_ID));
		ids.put("trackingNumber", (String)form.get("trackingNumber"));
		ids.put("fraudTypeCd", (String)form.get("fraudTypeCd"));
		ids.put("fraudTypeSeq", ((String)form.get("fraudTypeSeq")));
		Map<String, Object> fields = ComplaintItemManager.selectComplaintItem(ids);
		
		// set form data (banktrans, ssn, ...) from fields
        Set<String> keyset = formData.keySet();
        Iterator<String> formIt = keyset.iterator();
        while (formIt.hasNext())  {
            String key = (String)formIt.next();
            String value = (String)fields.get(key);

            if (value != null) {
                value = value.trim();
            }
           	formData.put(key, value);
        }
        
        // put subItems (accou, trans, suspect ...) from fields on session cache.
        HttpCacheManager cache = new HttpCacheManager(session);
        if (!"".equals(subItems)) {
        	String items[] = subItems.split(",");
        	for (int i = 0; i < items.length; i++) {
				Collection<Map<String, String>> group = (Collection<Map<String, String>>)fields.get(items[i]);
        		if (group != null) {
        			if ("suspect".equals(items[i])) {
        				cache.addAll(((String)form.get("fraudTypeCd")).toLowerCase() + "Suspect", group);
        			} else {
        				cache.addAll(items[i], group);
        			}
        		}        		
        	}
        }
	}
	
	protected Collection<Map<String, String>> removeIndex(Collection<Map<String, String>> collectionType) {
        Iterator<Map<String, String>> it = collectionType.iterator();
        int i = 0;
        while (it.hasNext()) {
        	Map<String, String> formType = (Map<String, String>)it.next();
        	formType.remove("index");
        	formType.remove("trackingNumber");
        	formType.remove("wsAction");
        	formType.remove("fraudTypeSeq");
        	formType.remove("dataElementSeq");
	        i = i + 1;
        }
        
        return collectionType;
	}
	
	protected void setFormName(String formName) {
		this.formName = formName;
	}
	
	public FraudEnum getFraudType() {
		return fraudType;
	}

	public void setFraudType(FraudEnum fraudType) {
		this.fraudType = fraudType;
	}

	public ReportedFraudsForm getFormBean() {
		return formBean;
	}

	public String getFormName() {
		return formName;
	}

	public Map<String, String> getFormData() {
		return formData;
	}

}
