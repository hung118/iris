package gov.utah.iris.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: May 6, 2005
 * Time: 4:40:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Constants {

	public static final String DATASOURCE = "java:comp/env/jdbc/iris";
	public static final String POSTAL_DATASOURCE = "java:comp/env/postalDS";
	public static final String POSTAL_WS_ENDPOINT = "http://api.dts.utah.gov/addressLookup/services/AddressLookupService";
    public static final String ACCOUNT = "account";
    public static final String ACCOUNT_TYPE = "accountType";
    public static final String ACCOUNT_TYPE_FORM = "accountTypeForm";
    public static final String ACTION = "action";
    public static final String BUSINESS_ACCOUNT = "businessAccount";
    public static final String CANCEL = "cancel";
    public static final String CANCEL_COMPLAINT_ITEM = "cancelComplaintItem";
    public static final String COLLECTION_AGENCIES = "collectionAgencies";
    public static final String COLLECTION_AGENCY_CALLS = "collectionAgencyCalls";
    public static final String CLAIM = "claim";
    public static final String CLAIM_HELP_SELECTION = "claimHelpSelection";
    public static final String CLAIMS = "claims";
    public static final String CLAIMS_LIST = "claimsList";
    public static final String CONFIRM = "confirm";
    public static final String CONFIRM_PASSWORD = "confirmPassword";
    public static final String CONTENT = "content";
    public static final String CONTENT_MANAGER = "contentManager";
    public static final String CONTENT_PATH = "contentPath";
    public static final String CREATE = "create";
    public static final String CREATE_ACCOUNT = "createAccount";
    public static final String CREATE_PERSONAL = "createPersonal";
    public static final String CREATE_BUSINESS = "createBusiness";
    public static final String CREATE_BUSINESS_FORM = "createBusinessForm";
    public static final String CREATE_PERSONAL_FORM = "createPersonalForm";
    public static final String DEFAULT = "default";
    public static final String DISCLAIMER = "disclaimer";
    public static final String DISPLAY = "display";
    public static final String DONT_USE_CLAIM_HELP = "dontUseClaimHelp";
    public static final String EDIT = "edit";
    public static final String EMPTY_STRING = "";
    public static final String FAILURE = "failure";
    public static final String HOME = "home";
    public static final String HTTPS = "https";
    public static final String INDEX = "index";
    public static final String IRS_NOTIFICATION_CONTENT = "irsNotificationContent";
    public static final String ITEM_INDEX = "itemIndex";
    public static final String LIST_CLAIMS = "claimsList";
    public static final String LOGIN_PAGE = "/claims/Login.do";
    public static final String MENU = "menu";
    public static final String METHOD = "method";
    public static final String NO_CLAIMS = "noClaims";
    public static final String PAGE = "page";
    public static final String PAGES = "pages";
    public static final String PASSWORD = "password";
    public static final String PERSONAL_ACCOUNT = "personalAccount";
    public static final String RELOAD = "reload";
    public static final String SECTION = "section";
    public static final String SECTIONS = "sections";
    public static final String SUBMIT_BUTTON = "submitButton";
    public static final String SUCCESS = "success";
    public static final String TEMPLATE = "template";
    public static final String UMD_ID = "umdId";
    public static final String UPDATE_USER_INFO_PAGE = "/claims/VictimInformation.do?method=displayUpdate";
    public static final String USE_CLAIM_HELP = "useClaimHelp";
    public static final String USER = "user";
    public static final String USER_INFO = "userInfo";
    public static final String USER_ID = "userId";
    public static final String USERNAME = "username";
    public static final String USER_SITEMINDER = "sm_userdn";
    public static final String VIEW = "view";
    public static final String VIEW_CLAIM = "viewClaim";
    public static final String PICK_INCIDENTS = "pickIncidents";
    
	/**
	 * Generic exception logging message for NoResultExceptions
	 */
	public static final String EXCEPTION_MSG_NO_RESULT = "NO RESULT FOUND FOR PROVIDED PARAMETER: ";


	private static HashMap<String, Integer> fraudType = new HashMap<String, Integer>(20);
	static {
		fraudType.put("BANKTRANS", new Integer(1));
		fraudType.put("BENEFITS", new Integer(2));
		fraudType.put("UNAUTHCREDIT", new Integer(15));
		fraudType.put("CREDITREPORT", new Integer(4));
		fraudType.put("LOAN", new Integer(11));
		fraudType.put("COLLECTOR", new Integer(3));
		fraudType.put("IDLOSTSTOLEN", new Integer(9));
		fraudType.put("SSN", new Integer(14));
		fraudType.put("TELEPHONE", new Integer(18));
		fraudType.put("UTILITIES", new Integer(16));
		fraudType.put("SUIT", new Integer(13));
		fraudType.put("EMAIL", new Integer(6));
		fraudType.put("OTHER_IDTHEFT", new Integer(19));
		fraudType.put("accou", new Integer(20));
		fraudType.put("trans", new Integer(21));
		fraudType.put("colle", new Integer(22));
		fraudType.put("credi", new Integer(23));
		fraudType.put("issue", new Integer(24));
		fraudType.put("tranx", new Integer(25));
		fraudType.put("suspect", new Integer(26));
	}
	
	public static Map<String, Integer> getFraudType() {
		return fraudType;
	}
}
