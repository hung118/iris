package gov.utah.iris.controller;

import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.bean.AccountDataAccess;
import gov.utah.iris.bean.ReportedFraudsForm;
import gov.utah.iris.common.Constants;
import gov.utah.iris.manager.ComplaintManager;
import gov.utah.iris.manager.HttpCacheManager;
import gov.utah.iris.model.DwsLogin;
import gov.utah.iris.service.AdminService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Start controller.
 * 
 * @author hnguyen
 *
 */

@Controller
@RequestMapping("/secure")
public class StartController extends BaseController {
	
	@Autowired
	private AdminService service;
	
	static String UMD_URL = "https://login2.utah.gov";
	static String TEST_OR_PROD = "laptop";
	static String UMD_ID = null;
	static {
		try {
			ResourceBundle prop = ResourceBundle.getBundle("iris");
			UMD_URL = prop.getString("umd_url");
			TEST_OR_PROD = prop.getString("test_or_prod");
			UMD_ID = prop.getString("umd_id");
		} catch (MissingResourceException e) {}
	}

	/**
	 * Possible cases:
	 *		1) https://iris.utah.gov/iris/secure/start?SSN&BANKTRANS - frauds pick list which is sent from WordPress page.
	 *		2) dwslogin - reports SSN fraud and inserts a record into dws_login table
	 * 		3) Alert page (Unused?) 
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public ModelAndView start(HttpServletRequest request) throws Exception {		
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView(Constants.PICK_INCIDENTS);
		
		// check if user logs in
		Map<String, String> userInfo = (Map<String, String>)session.getAttribute(Constants.USER_INFO);
		if (userInfo == null) {	// not login
			userInfo = getLoginInfo(request);
			session.setAttribute(Constants.USER_INFO, userInfo);
		} else {	// make sure user info in session is up-to-date from webservice by checking ssn.
			if (userInfo.get("ssn") == null || "".equals(userInfo.get("ssn"))) {
				AccountDataAccess ada = new AccountDataAccess();
				Map<String, String> wsAccount = ada.select(userInfo.get(Constants.UMD_ID));	
				session.setAttribute(Constants.USER_INFO, wsAccount);
			}
		}
		
		// create a complaint
		String trackingNumber = ComplaintManager.createComplaint(userInfo.get(Constants.UMD_ID));
		
		List<FraudEnum> reportedFrauds = null;
		if (request.getParameter("dwsNumber") != null) { // case 2) dws login
			reportedFrauds = new ArrayList<FraudEnum>();
			reportedFrauds.add(FraudEnum.SSN);
			
			DwsLogin dwsLogin = new DwsLogin();
			dwsLogin.setUmdId(userInfo.get(Constants.UMD_ID));
			dwsLogin.setDwsNumber(request.getParameter("dwsNumber"));
			dwsLogin.setCreatedDate(new Date());
			dwsLogin.setServer(request.getServerName());
			service.saveDwsLogin(dwsLogin);
		} else {	// case 1) wordpress page
			reportedFrauds = getReportedFraudList(request.getParameterNames());
		}
		
		if (!reportedFrauds.isEmpty()) {	
			// clear session cache object
			HttpCacheManager cache = new HttpCacheManager(request.getSession());
			cache.clear();
			
			ReportedFraudsForm formBean = new ReportedFraudsForm();
			FraudEnum[] frauds = new FraudEnum[reportedFrauds.size()];
			for (int i = 0; i < reportedFrauds.size(); i++) {
				frauds[i] = reportedFrauds.get(i);
			}
			formBean.setFrauds(frauds);
			formBean.setTrackingNumber(trackingNumber);
			formBean.setUmdId(userInfo.get(Constants.UMD_ID));

			// populate data from session user info
			formBean.getVictim().put("firstName", userInfo.get("firstName"));
			formBean.getVictim().put("middleName", userInfo.get("middleName"));
			formBean.getVictim().put("lastName", userInfo.get("lastName"));
			formBean.getVictim().put("addr1Street", userInfo.get("addr1Street"));
			formBean.getVictim().put("addr1City", userInfo.get("addr1City"));
			formBean.getVictim().put("addr1State", userInfo.get("addr1State"));
			formBean.getVictim().put("addr1Zip", userInfo.get("addr1Zip"));
			formBean.getVictim().put("emailAddress", userInfo.get("emailAddress"));
			formBean.getVictim().put("dayPhone", userInfo.get("dayPhone"));
			formBean.getVictim().put("dob", userInfo.get("dob"));
			formBean.getVictim().put("ssn", userInfo.get("ssn"));
			
			formBean.setWsAction("insert");
			mav.addObject("formBean", formBean);
			mav.addObject("reportedFraudList", reportedFrauds);
		}
		
		return mav;
		// TODO check for security alert page case 3)
	}
	
	/**
	 * DWS case - adds SSN fraud to pickIncidents and inserts a record into dws_login table.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dwslogin.shtml", method = RequestMethod.GET)
	public String dwslogin(HttpServletRequest request) throws Exception {
		return "dwsloginForm";
	}
	
	@RequestMapping(value = "/login.shtml", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) throws Exception {
		if (request.getSession().getAttribute(Constants.USER_INFO) == null) {
			request.getSession().setAttribute(Constants.USER_INFO, getLoginInfo(request));
		}
		
		ModelAndView mav = new ModelAndView("redirect:/secure/claimsList.shtml");
		return mav;
	}
		
	@RequestMapping(value = "/logout.shtml", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
        session.invalidate();
       	return "redirect:" + UMD_URL + "/user/logoff?TARGET=http://www.idtheft.utah.gov";        
        // TODO check if this is a security alert case.  
	}
	
	/**
     * Go to UMD URL for editing account information. This action is called by Edit My Account button.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/gotoUMDAccount.shtml", method = RequestMethod.GET)
	public String gotoUMDAccount(HttpServletRequest request) throws Exception {
    	// determine which server and protocol (http or https) running on: http://hung.dev.utah.gov, https://iris.utah.gov, ...
    	StringBuffer url;
    	if ("production".equals(TEST_OR_PROD)) {
    		url = new StringBuffer("https://" + request.getHeader("x-forwarded-host"));	// returns https://iris.utah.gov
    	} else {
    		url = new StringBuffer("https://" + request.getHeader("x-forwarded-host"));	// returns https://iris.at.utah.gov
    	}
    	String gotoURL = UMD_URL + "/user/account?TARGET=" + url.toString();
    	
    	return "redirect:" + gotoURL;    
	}
		
	private Map<String, String> getLoginInfo(HttpServletRequest request) throws Exception {
		// 1. get user UMD ID (ex. cn=100049092,ou=2,ou=Pub,o=UT)
		String umdId = null;
		if ("laptop".equals(TEST_OR_PROD)) {
			umdId = UMD_ID;
		} else {
			umdId = request.getHeader(Constants.USER_SITEMINDER);
		}		
		if (umdId == null) {
			String error = "UMD ID is null!";
			logger.error(error);
			throw new Exception(error);
		}
		
		// look up user in webservice
		AccountDataAccess ada = new AccountDataAccess();
		@SuppressWarnings("unchecked")
		Map<String, String> wsAccount = ada.select(umdId);		
		if (wsAccount.get("lastName") == null || "".equals(wsAccount.get("lastName"))) {	// account does not exist in WS, create it.
			wsAccount.put("firstName", request.getHeader("irisfirstname"));
			wsAccount.put("lastName", request.getHeader("irislastname"));
			wsAccount.put("emailAddress", request.getHeader("irismail"));
			ada.create(wsAccount, umdId);
		}
		
		return wsAccount;
	}
	
	private List<FraudEnum> getReportedFraudList(Enumeration<String> reportedFrauds) {
		List<FraudEnum> ret = new ArrayList<FraudEnum>();
		while (reportedFrauds.hasMoreElements()) {
			String e = reportedFrauds.nextElement();
			if ("pressCharges".equals(e)) {
				// Redmine 35292 - a band aid to fix WordPress JS!
			} else {
				ret.add(FraudEnum.valueOf((Constants.getFraudType()).get(e)));
			}
		}
		
		return ret;
	}
}
