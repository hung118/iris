package gov.utah.iris.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import gov.utah.iris.bean.AccountDataAccess;
import gov.utah.iris.common.Constants;
import gov.utah.das.its.AppProfile.Application;
import gov.utah.das.its.AppProfile.Connection;
import gov.utah.das.its.AppProfile.Profile;
import gov.utah.das.its.AppProfile.Client.APClient;
import gov.utah.dts.det.enums.FraudEnum;
import gov.utah.iris.model.Alert;
import gov.utah.iris.model.AlertId;
import gov.utah.iris.model.DwsLogin;
import gov.utah.iris.model.Jurisdiction;
import gov.utah.iris.service.AdminService;
import gov.utah.iris.service.MapDataService;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.Attribute;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.AttributeQuery;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.AttributeQueryResponse;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.SpatialQuery;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.SpatialQueryResponse;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoap;
import gov.utah.mapserv.WSUTSGID_FeatureAttributes.WSUTSGID_FeatureAttributesSoapStub;
import gov.utah.mapserv.WSUTSGID_Geolocator.GeocodeResult;
import gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoap;
import gov.utah.mapserv.WSUTSGID_Geolocator.UTSGID_GeoLocatorSoapStub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * AdminController class.
 * 
 * @author hnguyen
 *
 */
@Controller
@RequestMapping("/secure")
public class AdminController extends BaseController {
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private MapDataService mService; 
	
	private static String TEST_OR_PROD = "laptop";
	
	private static String TODAY = "Today";
	private static String YESTERDAY = "Yesterday";
	private static String WEEK_TO_DATE = "Week_To_Date";
	private static String MONTH_TO_DATE = "Month_To_Date";
	private static String YEAR_TO_DATE = "Year_To_Date";
	private static String ALL_TIME = "All_Time";
	
	static String PROFILE_SERVER = null;
	static String PROFILE_SERVER_BAK = null;
	static String PROFILE_SERVER_LOGS = null;
	static String PROFILE_NAME = null;
	static String CONNECTION_KEY = null;
	
    static {
        try {
            ResourceBundle prop = ResourceBundle.getBundle("iris");
            TEST_OR_PROD = prop.getString("test_or_prod");
            PROFILE_SERVER = prop.getString("profile_server");
            PROFILE_SERVER_BAK = prop.getString("profile_server_bak");
            PROFILE_SERVER_LOGS = prop.getString("profile_server_logs");
			PROFILE_NAME = prop.getString("profile_name");
			CONNECTION_KEY = prop.getString("connection_key");
        } catch (MissingResourceException e) {}
    }
    
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(HttpServletRequest request) throws Exception {
		// check user access if not running on laptop
		if (!"laptop".equals(TEST_OR_PROD)) {
			if (!hasAccess(request.getHeader(Constants.USER_SITEMINDER))) {
				throw new Exception("User access denied!");				
			}
		}
		
		// dashboard
		request.getSession().setAttribute("dashboard", getDashboard());
		
		return "adminConsole";
	}
	
	@RequestMapping(value = "/viewAlertList.admin", method = RequestMethod.GET)
	public ModelAndView viewAlertList() throws Exception {
		List<Alert> alerts = service.findAllSorted();
		ModelAndView mav = new ModelAndView("alertList");
		mav.addObject("alerts", alerts);
		
		return mav;
	}
	
	@RequestMapping(value = "/viewAlert.admin", method = RequestMethod.GET)
	public String viewAlert(Model model, @Param("name") String name, @Param("code") String code) throws Exception {
		Alert alert = new Alert();
		if (name == null) {	// add alert - init alert form
			alert.setMethod("addAlert");
		} else {
			alert = service.findAlertByNameCode(name, code);
			if (alert.getFrauds() != null) {
				alert.setFraudsArr(alert.getFrauds().split(";"));
			}
			if (!alert.getOriList().isEmpty()) {
				alert.setOriCode(alert.getOriList().get(0).getOriCode());
			}
			alert.setMethod("updateAlert");			
		} 
		
		model.addAttribute("alert", alert);
		return "alertForm";
	}
	
	@RequestMapping(value = "/saveAlert.admin", method = RequestMethod.POST)
	public String saveAlert(@ModelAttribute("alert") Alert alert) throws Exception {
		if (alert.getFraudsArr().length > 0) {
			StringBuffer frauds = new StringBuffer();
			for (int i = 0; i < alert.getFraudsArr().length; i++) {
				if (i == alert.getFraudsArr().length - 1) {
					frauds.append(alert.getFraudsArr()[i]);
				} else {
					frauds.append(alert.getFraudsArr()[i]).append(";");
				}
			}
			alert.setFrauds(frauds.toString());
		}
		if (!"".equals(alert.getOriCode())) {
			Jurisdiction j = new Jurisdiction();
			j.setOriCode(alert.getOriCode());
			List<Jurisdiction> l = new ArrayList<Jurisdiction>();
			l.add(j);
			alert.setOriList(l);
		}
		service.saveAlert(alert);
		
		return "redirect:/secure/viewAlertList.admin";
	}
	
	@RequestMapping(value = "/deleteAlert.admin", method = RequestMethod.GET)
	public String deleteAlert(@Param("name") String name, @Param("code") String code) throws Exception {
		AlertId ai = new AlertId();
		ai.setCode(code);
		ai.setName(name);
		Alert alert = new Alert();
		alert.setId(ai);
		
		service.deleteAlert(alert);
		return "redirect:/secure/viewAlertList.admin";
	}
	
	@ModelAttribute("oriList")
	public List<Jurisdiction> getAllOriList() {
		return service.findAllJurisdiction();
	}
	
	@RequestMapping(value = "/userProxyForm.admin", method = RequestMethod.GET)
	public String userProxyForm() throws Exception {
		return "userProxyForm";
	}
	
	@RequestMapping(value = "/userProxy.admin", method = RequestMethod.GET)
	public String userProxy(@Param(Constants.UMD_ID) String umdId, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();

		// get Web service user info and put it on session
		AccountDataAccess ada = new AccountDataAccess();
		@SuppressWarnings("unchecked")
		Map<String, String> wsAccount = ada.select(umdId);
		session.setAttribute(Constants.USER_INFO, wsAccount);

		return "userProxyInfo";
	}
	
	@RequestMapping(value = "/searchORIForm.admin", method = RequestMethod.GET)
	public String searchORIForm() throws Exception {
		return "searchORIForm";
	}

	@RequestMapping(value = "/searchORI.admin", method = RequestMethod.GET)
	public String searchORI(HttpServletRequest request) throws Exception {
    	try {
    		// get streetAddress and zipCode from request
    		String streetAddress = request.getParameter("streetAddress");
    		String zipCode = request.getParameter("zipCode");
    		
    		UTSGID_GeoLocatorSoap geolocator = new  UTSGID_GeoLocatorSoapStub(
            		new URL("http://mapserv.utah.gov/WSUTSGID_Geolocator/Default.asmx"), 
            		new org.apache.axis.client.Service());
            
            GeocodeResult  geocodeAddress = geolocator.geocodeAddress("hnguyen", streetAddress, zipCode);
            
            WSUTSGID_FeatureAttributesSoap muni = new WSUTSGID_FeatureAttributesSoapStub(
            		new URL("http://mapserv.utah.gov/WSUTSGID_FeatureAttributes/default.asmx"), 
            		new org.apache.axis.client.Service());
            
            // query fips
            String[] sAttributeList = {"FIPS"};
            SpatialQuery spatialQuery = new SpatialQuery("hnguyen", "SGID10.BOUNDARIES.Municipalities", sAttributeList, 
            		geocodeAddress.getUTM_X(), geocodeAddress.getUTM_Y());
            SpatialQueryResponse spatialQueryResponse = muni.getFeatureAttributes(spatialQuery);
            
            String fips = null;
            Attribute[] spatialQueryResult = spatialQueryResponse.getSpatialQueryResult();
            if (spatialQueryResult[0].getValue() != null && spatialQueryResult[0].getValue().length() > 0) {	// municipality found
            	fips = spatialQueryResult[0].getValue();
            } else {	// not found. Query counties to find fips instead, since the point is not in a municipality
            	spatialQuery.setSGIDLayerName("SGID10.BOUNDARIES.Counties");
            	spatialQueryResponse = muni.getFeatureAttributes(spatialQuery);
            	spatialQueryResult = spatialQueryResponse.getSpatialQueryResult();
            	if (spatialQueryResult[0].getValue() != null && spatialQueryResult[0].getValue().length() > 0) {	// found in county
            		fips = spatialQueryResult[0].getValue();
            	}
            }
            
            // query agencies
            String[] aAttributeList = {"MUNICIPALITY", "ORI", "COUNTY", "AGENCY"};
            AttributeQuery attributeQuery = new AttributeQuery("hnguyen", "SGID10.BOUNDARIES.UtahLawEnforcementAgencies",
            		aAttributeList, "FIPS", fips, "=");
            AttributeQueryResponse attributeQueryResponse = muni.getFeatureAttributes(attributeQuery);
            Attribute[] attributeQueryResult = attributeQueryResponse.getAttributeQueryResult();
            
            StringBuffer sb = new StringBuffer("<br>Address, Zip Code: <b>" + streetAddress + ", " + zipCode + "</b><br><br>");
            sb.append("UTM_X: <b>" + geocodeAddress.getUTM_X() + "</b><br>");
            sb.append("UTM_y: <b>" + geocodeAddress.getUTM_Y()+ "</b><br><br>");
            for (int i = 0; i < attributeQueryResult.length; i++) {
            	sb.append(attributeQueryResult[i].getField() + ": <b>" + attributeQueryResult[i].getValue() + "</b><br>");
            }
            
            request.setAttribute("searchResult", sb.toString());
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new Exception(e.getMessage());
    	}
		return "searchORIForm";
	}
	
	@RequestMapping(value = "/viewDWSList.admin", method = RequestMethod.GET)
	public ModelAndView viewDWSList() throws Exception {
		List<DwsLogin> dwsLogins = service.findeAllDwsLogins();
		ModelAndView mav = new ModelAndView("dwsList");
		mav.addObject("dwsList", dwsLogins);
		
		return mav;
	}
	
	/**
	 * Connects to UMD app profile to check for Administrator field. 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	private boolean hasAccess(String umdId) throws Exception {
		APClient.initialize(PROFILE_SERVER, PROFILE_SERVER_BAK, PROFILE_SERVER_LOGS, true);
		Connection conn = APClient.getConnection(CONNECTION_KEY);
		Application app = conn.getApplication(PROFILE_NAME);
		Profile profile = app.getProfile(umdId);
		boolean userAccess = profile.is("Administrator"); 
		conn.close();
		
		return userAccess;
	}
	
	private Map<String, Map<String, String>> getDashboard() {
		Map<String, Map<String, String>> dashboard = new HashMap<String, Map<String, String>>();
		dashboard.put(TODAY, getFraudMap(TODAY));
		dashboard.put(YESTERDAY, getFraudMap(YESTERDAY));
		dashboard.put(WEEK_TO_DATE, getFraudMap(WEEK_TO_DATE));
		dashboard.put(MONTH_TO_DATE, getFraudMap(MONTH_TO_DATE));
		dashboard.put(YEAR_TO_DATE, getFraudMap(YEAR_TO_DATE));
		dashboard.put(ALL_TIME, getFraudMap(ALL_TIME));
		
		return dashboard;
	}
	
	private Map<String, String> getFraudMap(String dateType) {
		Map<String, String> fraudMap = new HashMap<String, String>();
		Calendar dateObj = Calendar.getInstance();
		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();
		int diff = 0;
		switch (dateType) {
		case "Today":
			date2.add(Calendar.DAY_OF_MONTH, 1);
			break;
		case "Yesterday":
			dateObj.add(Calendar.DAY_OF_WEEK, -1);
			
			date1.add(Calendar.DAY_OF_WEEK, -1);			
			break;
		case "Week_To_Date":
			diff = dateObj.get(Calendar.DAY_OF_WEEK) - dateObj.getFirstDayOfWeek();
			dateObj.add(Calendar.DAY_OF_WEEK, -diff);
			
			diff = date1.get(Calendar.DAY_OF_WEEK) - date1.getFirstDayOfWeek();
			date1.add(Calendar.DAY_OF_WEEK, -diff);
			date2.add(Calendar.DAY_OF_MONTH, 1);
			break;
		case "Month_To_Date":
			diff = dateObj.get(Calendar.DAY_OF_MONTH) - dateObj.getActualMinimum(Calendar.DAY_OF_MONTH);
			dateObj.add(Calendar.DAY_OF_MONTH, - diff);
			
			diff = date1.get(Calendar.DAY_OF_MONTH) - date1.getActualMinimum(Calendar.DAY_OF_MONTH);
			date1.add(Calendar.DAY_OF_MONTH, -diff);
			date2.add(Calendar.DAY_OF_MONTH, 1);
			break;
		case "Year_To_Date":
			dateObj.set(Calendar.DAY_OF_MONTH, 1);
			dateObj.set(Calendar.MONTH, Calendar.JANUARY);
			
			date1.set(Calendar.DAY_OF_MONTH, 1);
			date1.set(Calendar.MONTH, Calendar.JANUARY);
			date2.add(Calendar.DAY_OF_MONTH, 1);
			break;
		case "All_Time":
			date1 = null;
			date2 = null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		fraudMap.put("dateStr", sdf.format(dateObj.getTime()));
		Long banktransCount = mService.getDashboardFraudCount(new Long(FraudEnum.BANKTRANS.getId()), date1, date2);
		fraudMap.put("BANKTRANS", String.valueOf(banktransCount));
		Long benefitsCount = mService.getDashboardFraudCount(new Long(FraudEnum.BENEFITS.getId()), date1, date2);
		fraudMap.put("BENEFITS", String.valueOf(benefitsCount));
		Long unauthcreditCount = mService.getDashboardFraudCount(new Long(FraudEnum.UNAUTHCREDIT.getId()), date1, date2);
		fraudMap.put("UNAUTHCREDIT", String.valueOf(unauthcreditCount));
		Long creditreportCount = mService.getDashboardFraudCount(new Long(FraudEnum.CREDITREPORT.getId()), date1, date2);
		fraudMap.put("CREDITREPORT", String.valueOf(creditreportCount));
		Long loanCount = mService.getDashboardFraudCount(new Long(FraudEnum.LOAN.getId()), date1, date2);
		fraudMap.put("LOAN", String.valueOf(loanCount));
		Long collectorCount = mService.getDashboardFraudCount(new Long(FraudEnum.COLLECTOR.getId()), date1, date2);
		fraudMap.put("COLLECTOR", String.valueOf(collectorCount));
		Long idloststolenCount = mService.getDashboardFraudCount(new Long(FraudEnum.IDLOSTSTOLEN.getId()), date1, date2);
		fraudMap.put("IDLOSTSTOLEN", String.valueOf(idloststolenCount));
		Long ssnCount = mService.getDashboardFraudCount(new Long(FraudEnum.SSN.getId()), date1, date2);
		fraudMap.put("SSN", String.valueOf(ssnCount));
		Long telephoneCount = mService.getDashboardFraudCount(new Long(FraudEnum.TELEPHONE.getId()), date1, date2);
		fraudMap.put("TELEPHONE", String.valueOf(telephoneCount));
		Long utilitiesCount = mService.getDashboardFraudCount(new Long(FraudEnum.UTILITIES.getId()), date1, date2);
		fraudMap.put("UTILITIES", String.valueOf(utilitiesCount));
		Long suitCount = mService.getDashboardFraudCount(new Long(FraudEnum.SUIT.getId()), date1, date2);
		fraudMap.put("SUIT", String.valueOf(suitCount));
		Long emailCount = mService.getDashboardFraudCount(new Long(FraudEnum.EMAIL.getId()), date1, date2);
		fraudMap.put("EMAIL", String.valueOf(emailCount));
		Long other_idtheftCount = mService.getDashboardFraudCount(new Long(FraudEnum.OTHER_IDTHEFT.getId()), date1, date2);
		fraudMap.put("OTHER_IDTHEFT", String.valueOf(other_idtheftCount));
		Long total = banktransCount + benefitsCount + unauthcreditCount + creditreportCount + loanCount + collectorCount + idloststolenCount +
				ssnCount + telephoneCount + utilitiesCount + suitCount + emailCount + other_idtheftCount;
		fraudMap.put("TOTAL", String.valueOf(total));
		
		return fraudMap;
	}
}
