package gov.utah.iris.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.AccountDataAccess;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.common.CaseConstants;
import gov.utah.iris.common.Constants;
import gov.utah.iris.factory.Fraud;
import gov.utah.iris.factory.FraudFactory;
import gov.utah.iris.manager.ComplaintManager;
import gov.utah.iris.manager.HttpCacheManager;
import gov.utah.iris.model.ComplaintDataPoint;
import gov.utah.iris.model.FraudType;
import gov.utah.iris.service.AdminService;
import gov.utah.iris.service.MapDataService;
import gov.utah.iris.util.ComplaintItemUtil;
import gov.utah.iris.util.FormatUtil;
import gov.utah.iris.util.MailSender;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller class to process reported frauds.
 * 
 * @author hnguyen
 *
 */
@Controller
@RequestMapping("/secure")
public class ReportedFraudsController extends BaseController {
	
	@Autowired
	private AdminService aService;
	
	@Autowired
	private MapDataService mService;
	
    private static String TEST_OR_PROD = "laptop";
    static {
        try {
            ResourceBundle prop = ResourceBundle.getBundle("iris");
            TEST_OR_PROD = prop.getString("test_or_prod");
        } catch (MissingResourceException e) {}
    }
    
	/**
	 * Process fraud(s) submitted from pickIncidents.jsp.
	 * @param userInfo
	 * @param formBean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/processFrauds.shtml", method = RequestMethod.POST)
	public String processFrauds(@ModelAttribute("formBean") ReportedFraudsForm formBean, HttpServletRequest request) throws Exception {
		logger.debug("Enter processFrauds ...");

		@SuppressWarnings("unchecked")
		Map<String, String> userInfo = (Map<String, String>)request.getSession().getAttribute(Constants.USER_INFO);
		String umdId = userInfo.get(Constants.UMD_ID);
		
		//1. create or update user account in WS and in session
		AccountDataAccess ada = new AccountDataAccess();
		formBean.setUmdId(umdId);
		ada.processWsAccount(formBean);
				
		formBean.setDefaultAddress(formBean.getVictim().get("addr1Street"));
		formBean.setDefaultZipCode(formBean.getVictim().get("addr1Zip"));
		formBean.setAlertORI(getAlertORI(formBean.getTrackingNumber()));
		formBean.getVictim().put(Constants.UMD_ID, umdId);
		
		request.getSession().setAttribute(Constants.USER_INFO, formBean.getVictim());
		
		// 2. create webservice complaint item(s) with subitems (accou, trans, suspect ...) in session cache. 
		for (int i = 0; i < formBean.getFrauds().length; i++) {
			Fraud fraud = FraudFactory.getInstance(formBean.getFrauds()[i], formBean, request.getSession());
			fraud.createFraud();			
		}
		
		// 3. submit complaint to law enforcement
		if ("submitReport".equals(formBean.getActionType())) {
			doSubmitComplaint(formBean.getVictim(), formBean.getTrackingNumber());
		}	// otherwise, just keep the save complaint in step 2 for later submission.
		
		// 4. clear session cache object
		HttpCacheManager cache = new HttpCacheManager(request.getSession());
		cache.clear();
		
		return "redirect:/secure/claimsList.shtml";
	}
    
	/**
	 * Update incident on webservice.
	 * 
	 * @param formBean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateIncident.shtml", method = RequestMethod.POST)
	public String updateIncident(@ModelAttribute("formBean") ReportedFraudsForm formBean, HttpServletRequest request) throws Exception {
		AccountDataAccess ada = new AccountDataAccess();
		ada.processWsAccount(formBean);
		
		formBean.setDefaultAddress(formBean.getVictim().get("addr1Street"));
		formBean.setDefaultZipCode(formBean.getVictim().get("addr1Zip"));
		formBean.setAlertORI(getAlertORI(formBean.getTrackingNumber()));	
		
		@SuppressWarnings("unchecked")
		String umdId = ((Map<String, String>)request.getSession().getAttribute(Constants.USER_INFO)).get(Constants.UMD_ID);
		formBean.setUmdId(umdId);
		formBean.getVictim().put(Constants.UMD_ID, umdId);
		
		// send to webservice to update
		FraudEnum fraudType = FraudEnum.valueOf(Constants.getFraudType().get(formBean.getFraudTypeCd()));
		Fraud fraud = FraudFactory.getInstance(fraudType, formBean, request.getSession());
		fraud.updateFraud();
		
		return "redirect:/secure/selectComplaint.shtml?trackingNumber=" + formBean.getTrackingNumber();
	}
	
	@RequestMapping(value = "/submitComplaint.shtml", method = RequestMethod.GET)
	public String submitComplaint(HttpServletRequest request) throws Exception {
		String trackingNumber = request.getParameter("trackingNumber");
		@SuppressWarnings("unchecked")
		Map<String, String> userInfo = (Map<String, String>)request.getSession().getAttribute(Constants.USER_INFO);
		doSubmitComplaint(userInfo, trackingNumber);
		
		return "redirect:/secure/claimsList.shtml";
	}
	
	private void doSubmitComplaint(Map<String, String> userInfo, String trackingNumber) throws Exception {
		// 1. submit complaint to webservices.
		Map<String, String> ids = new HashMap<String, String>();
		ids.put(Constants.UMD_ID, userInfo.get(Constants.UMD_ID));
		ids.put("trackingNumber", trackingNumber);
		Map<String, Object> complaint = ComplaintManager.selectComplaint(ids, "complaintDetails");
		
		Collection<String> jurisdictions = getJurisdictions(complaint);
		Collection<Map<String, String>>complaintDetails = getComplainDetails(complaint);
		
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.putAll(ids);
		Collection<Map<String, String>> agencies = ComplaintManager.submitComplaint(hm, jurisdictions);

        try {
            // 2. insert victim and complaint info into complaint_fact table, local database, for id theft map action.
            insertMapInfo(complaintDetails, userInfo);
        } catch (Exception e) {
            // ignore the error - could not locate AGRC map location. Map statistic page will not have this information in database.
        }

		// 3. send email notification to law enforcement agencies/default agency.
		sendNotification(agencies, trackingNumber, userInfo);
		
		// 4. send email confirmation to user.
		sendConfirmation(trackingNumber, complaintDetails, userInfo);
	}
	
	/**
     * Returns a colleciton of jurisdictions
     * @param complaint
     * @return
     */
	private Collection<String> getJurisdictions(Map<String, Object> complaint) {
        Collection<String> jurisdictions = new ArrayList<String>();
        Map<String, String> jurisMap = new HashMap<String, String>();

        /**
         * 1. get complaint details
         * 2. iterate over complaint details
         * 3. extract jurisdictions and add them to the jurisdictions collection
         */
        if (complaint.containsKey("complaintDetails") ) {
            @SuppressWarnings("unchecked")
			Collection<Map<String, String>> complaintDetails = (Collection<Map<String, String>>)complaint.get("complaintDetails");

            Iterator<Map<String, String>> iterator = complaintDetails.iterator();
            while( iterator.hasNext() ) {
                Map<String, String> detail = (Map<String, String>)iterator.next();
                String dataElementCd = (String)detail.get("dataElementCd");

                if ("jurisdiction".equals(dataElementCd.trim())) {
                    jurisMap.put(detail.get("dataElementVal"), null);
                }
            }
        }
        else {
            logger.error("#-> could not find complaintDetails in complaint...");
        }

        // add all keys to jurisdictions
        jurisdictions.addAll(jurisMap.keySet());

        return jurisdictions;
    }
	
	/**
	 * Sends email to notify law enforcement agencies/default agency.
	 * @param agencies
	 */
	private void sendNotification(Collection<Map<String, String>> agencies, String trackingNumber, Map<String, String> userInfo) throws Exception {
		List<String> recipients = new ArrayList<String>();
		if (agencies.size() > 0) {
			Iterator<Map<String, String>> agenciesIt = agencies.iterator();
			while (agenciesIt.hasNext()) {
				Map<String, String> agency = agenciesIt.next();
				recipients.add(agency.get("contactEmail"));
			}
		} else {
			logger.error("#-> Error: agencies list is empty!");
		}
		recipients.removeAll(recipients);		// Redmine 135555 - Cease all email notifications except default agency when sending mails in production.
			
        String subject = "Identity Theft Reporting Information System Complaint Notification";
        StringBuffer body = new StringBuffer("A new Identity Theft case is waiting for you in UCJIS. \nTracking Number: " + trackingNumber +
        		"\nEmail: " + userInfo.get("emailAddress") + "\nUMD ID: " + userInfo.get(Constants.UMD_ID));
        
        // add instructions at the end of the body.
        body.append("\n\nTo access the UCJIS identity theft report, follow the instructions below:\n\n")
            .append("(1) Visit www.ucjis.utah.gov\n")
            .append("(2) Enter your UCJIS username and password to access UCJIS.\n")
            .append("(3) Click on the \"Person\" link located in the upper left hand corner of the screen.\n")
            .append("(4) Click \"Inquiry.\"\n")
            .append("(5) Click \"Utah.\"\n")
            .append("(6) Click \"ID Fraud.\"\n")
            .append("(7) To view the ID Theft report, copy and paste the Tracking number from this email into the Tracking Number box.\n")
            .append("(8) Please contact the victim and add your case number and contact information to the UCJIS report. This will help the victim begin clearing their name.\n\n")
            .append("If you do not have an ID Fraud link, please contact your TAC and ask them to contact BCI to give you rights to the ID theft form.\n\n")
            .append("If you have any questions or concerns, please email them to idfraudunit@utah.gov \n\n")
            .append("Do not reply to this email");
        
        String from = "noreply@www.iris.utah.gov";
        String alertEmail = getAlertEmail(trackingNumber);
        
        if (!"Not Found".equals(alertEmail)) {	// check if this trackingNumber has been persisted for security alert
        	recipients.add(alertEmail);		// send to alert email
        } else if ("production".equals(TEST_OR_PROD)) {	// on prod
        	recipients.add("idfraudunit@utah.gov"); 
        } else if ("test".equals(TEST_OR_PROD)) {	// on AT
            recipients.add("smorrill@utah.gov"); 
            recipients.add("RCORDOVA@utah.gov");
        } else { // on local host
        	recipients.add("hnguyen@utah.gov");
        }
        
        MailSender.send(subject, body.toString(), from, recipients, null, null);
	}
    
	private String getAlertEmail(String trackingNumber) {
		String email = aService.findAlertEmail(trackingNumber);
		
		if (email == null || "".equals(email)) {
			return "Not Found";
		} else {
			return email;
		}
	}
	
	private void sendConfirmation(String trackingNumber, Collection<Map<String, String>>complaintDetails, Map<String, String>user) throws Exception {
    	String from = "noreply@www.iris.utah.gov";
    	StringBuffer incidentsReported = new StringBuffer("").append("Date filed\t\t").append("Incident Type\n");
    	Iterator<Map<String, String>> details = complaintDetails.iterator();
    	while (details.hasNext()) {
    		Map<String, String> detail = details.next();
    		incidentsReported.append(FormatUtil.date((String)detail.get("insertDateTime"), "long")).append("\t");
    		
    		Map<String, String> caseWeb = CaseConstants.getCaseWebMap();
    		incidentsReported.append(caseWeb.get((String)detail.get("fraudTypeCd"))).append("\n");
    	}
    	
    	ArrayList<String> recipient = new ArrayList<String>(1);
    	recipient.add(user.get("emailAddress"));
    	String subject = "Identity Theft Complaint Confirmation";
    	StringBuffer body = new StringBuffer("CONFIRMATION\n\nThank you for visiting the Utah Attorney General’s ID Theft Central. This email confirms that your identity theft Incident Report was received. An investigating officer may contact you if additional information is needed regarding your report. After law enforcement closes your case, you will receive a notification email.\n\n");
    	body.append("Incident number: ").append(trackingNumber).append(".\n")
    	.append("Identity theft reported:\n")
    	.append(incidentsReported.toString()).append("\n\n")

    	.append("Your Contact Information:\n")
    	.append("First Name: ").append(user.get("firstName")).append("\n")
    	.append("Middle Initiall: ").append(user.get("middleName")).append("\n")
    	.append("Last Name: ").append(user.get("lastName")).append("\n")
    	.append("Address: ").append(user.get("addr1Street")).append("\n")
    	.append("City: ").append(user.get("addr1City")).append("\n")
    	.append("State: ").append(user.get("addr1State")).append("\n")
    	.append("Zip Code: ").append(user.get("addr1Zip")).append("\n")
    	.append("Valid Email Address: ").append(user.get("emailAddress")).append("\n")
    	.append("Phone: ").append(user.get("dayPhone")).append("\n\n")
    	
    	.append("YOUR FEEDBACK IS IMPORTANT\n")
    	.append("Please take a moment to take a short survey about your experience using the Utah Attorney General’s ID Theft Central program. Click on the ID Theft Central Customer Satisfaction Survey link below:\n\n")
    	.append("https://docs.google.com/a/utah.gov/forms/d/1G-eYROGJsTYkN3zyp78ka8YM7s3IpP7N9lqli4g5rqw/viewform?usp=send_form");
    	
    	MailSender.send(subject, body.toString(), from, recipient, null, null);
	}
	
	private Collection<Map<String, String>> getComplainDetails(Map<String, Object>complaint) {
		@SuppressWarnings("unchecked")
		Collection<Map<String, String>> complaintDetails = (Collection<Map<String, String>>)complaint.get("complaintDetails");
		
		return ComplaintItemUtil.removeDuplicateComplaintItems(complaintDetails);
	}
	
	/**
	 * Inserts a record to local database table which will be used by map action.
	 * 
	 * @param complaintDetails
	 * @param user
	 * @throws Exception
	 */
	private void insertMapInfo(Collection<Map<String, String>> complaintDetails, Map<String, String> user) throws Exception {
		List<FraudType> fraudTypes = mService.findAllFraudTypes();
		
		ComplaintDataPoint cdp = mService.getAGRCMapInfo_WS((String)user.get("addr1Street"), (String)user.get("addr1Zip"));
		
		Iterator<Map<String, String>> details = complaintDetails.iterator();
		while (details.hasNext()) {
			Map<String, String> detail = details.next();
			ComplaintDataPoint cdpCopy = (ComplaintDataPoint)copyObject((Object)cdp);
			FraudType ft = getFraudType((String)detail.get("fraudTypeCd"), fraudTypes);
			cdpCopy.setType(ft);
			cdpCopy.setTypeCode(ft.getCode());
			mService.saveDataPoint(cdpCopy);
		}
	}
	
	private FraudType getFraudType(String fraud, List<FraudType> fraudTypes) {
		FraudType retObj = new FraudType();
		
		for (int i = 0; i < fraudTypes.size(); i++) {
			FraudType fraudType = fraudTypes.get(i);
			if (fraud.equals(fraudType.getCode())) {
				retObj.setCode(fraudType.getCode());
				retObj.setId(fraudType.getId());
				break;
			}
		}
		
		return retObj;
	}
	
	private Object copyObject(Object objSource) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(objSource);
		oos.flush();
		oos.close();
		bos.close();
		byte[] byteData = bos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
		Object objDest = new ObjectInputStream(bais).readObject();
		
		return objDest;
	}
}

