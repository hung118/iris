package gov.utah.iris.bean;

import gov.utah.iris.common.Constants;
import gov.utah.iris.manager.PersistantManagerImpl;
import gov.utah.iris.manager.interfaces.PersistantManager;
import gov.utah.das.its.AppProfile.Connection;
import gov.utah.das.its.AppProfile.Profile;
import gov.utah.das.its.AppProfile.Client.APClient;
import gov.utah.dts.det.util.DateUtilities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Handles all account tasks (UMD and Web service).
 * 
 * @author HNGUYEN
 *
 */
@SuppressWarnings({"rawtypes"})
public class AccountDataAccess {

	static String PROFILE_SERVER = null;
	static String PROFILE_SERVER_BAK = null;
	static String PROFILE_SERVER_LOGS = null;
	static String PROFILE_NAME = null;
	static String CONNECTION_KEY = null;

	static {
		try {
			ResourceBundle prop = ResourceBundle.getBundle("iris");
            PROFILE_SERVER = prop.getString("profile_server");
            PROFILE_SERVER_BAK = prop.getString("profile_server_bak");
            PROFILE_SERVER_LOGS = prop.getString("profile_server_logs");
			PROFILE_NAME = prop.getString("profile_name");
			CONNECTION_KEY = prop.getString("connection_key");			
		} catch (MissingResourceException e) {}
	}
		
	public AccountDataAccess() {}
	
	/**
	 * Creates account in Web service.
	 * @param account
	 * @param umdId
	 * @throws Exception
	 */
	public void create(Map<String, String> account, String umdId) throws Exception {

		// call Web service to save info to db
		PersistantManagerImpl manager = new PersistantManagerImpl();
        account.put(Constants.UMD_ID, umdId);
        account.put("personType", "v");
        manager.create(account, "person");
	}
	
	public Map select(String umdId) throws Exception {
		
		PersistantManager manager = new PersistantManagerImpl();
		Map<String, String> ids = new HashMap<String, String>();
		ids.put(Constants.UMD_ID, umdId);

		return manager.select(ids, "person");
	}
	
	/**
	 * Updates account in Web service
	 * @throws Exception
	 */
	public void update(Map<String, String> account, String umdId) throws Exception {
		
		// call Web service to save info to db
        PersistantManagerImpl manager = new PersistantManagerImpl();

        Map<String, String> ids = new HashMap<String, String>();
        ids.put(Constants.UMD_ID, umdId);

        //System.out.println("*** update ws: " + umdId + " " + (String)account.get("addr1Zip"));
        manager.update(account, ids, "person");
	}
	
	/** Gets user info from app profile.
	 * 
	 * @param umdId
	 * @throws Exception
	 * @returns Map object
	 */
	public Map<String, String> getUmdInfo(String umdId) throws Exception {
		
		APClient.initialize(PROFILE_SERVER, PROFILE_SERVER_BAK, PROFILE_SERVER_LOGS, true);
		Connection conn = APClient.getConnection(CONNECTION_KEY);
	
		LinkedHashMap<String, String> account = new LinkedHashMap<String, String>();

		// from IRIS profile
		conn.setCacheMode(false);
		Profile profile = conn.getProfile(umdId, PROFILE_NAME);
		if (profile.is("Administrator")) {
			account.put("administrator", "yes");
		} else {
			account.put("administrator", "no");
		}
		
		// from Public profile
		profile = conn.getProfile(umdId, "Public");
		account.put("firstName", profile.getString("FirstName"));
		account.put("middleName", profile.getString("Initials"));
		account.put("lastName", profile.getString("LastName"));
		account.put("emailAddress", profile.getString("Email"));
		account.put("addr1Street", profile.getString("HomeAddress"));
		account.put("addr1City", profile.getString("HomeCity"));
		account.put("addr1State", profile.getString("HomeState"));
		account.put("addr1Zip", profile.getString("HomeZIPCode"));
		account.put("dayPhone", profile.getString("HomePhone"));
		account.put("sex", "");	// not used
		account.put("race", "");	// not used
		
		// from Private profile
		profile = conn.getProfile(umdId, "Private");
		account.put("ssn", profile.getString("SSN"));
		
		String[] dob = profile.getString("BirthDay").split("-");
		if (dob.length > 1) {
			account.put("dob", dob[1] + "/" + dob[2] + "/" + dob[0]);
		} else {
			account.put("dob", "");
		}

		// DriversLicense is of state:number, e.g. UT:23451222
		try {
			String[] driversLicense = profile.getString("DriversLicense").split(":");
			account.put("dlState",  driversLicense[0]);
			account.put("dlNumber", driversLicense[1]);			
		} catch (Exception e) {
			// ignore the error when use doesn't enter driver license #
		}

		conn.close();
		
		return account;
	}
		
	/**
	 * Updates UMD user info with Web service account.
	 * @return
	 * @throws Exception
	 */
	public void updateUmdInfo(Map wsAccount, String umdId) throws Exception {
		
		APClient.initialize(PROFILE_SERVER, PROFILE_SERVER_BAK, PROFILE_SERVER_LOGS, true);
		Connection conn = APClient.getConnection(CONNECTION_KEY);
		
		Profile profile = conn.getProfile(umdId, "Public");
		profile.setValue("FirstName", (String)wsAccount.get("firstName"));
		profile.setValue("Initials", (String)wsAccount.get("middleName"));
		profile.setValue("LastName", (String)wsAccount.get("lastName"));		
		profile.setValue("Email", (String)wsAccount.get("emailAddress"));
		profile.setValue("HomeAddress", (String)wsAccount.get("addr1Street"));
		profile.setValue("HomeCity", (String)wsAccount.get("addr1City"));
		profile.setValue("HomeState", (String)wsAccount.get("addr1State"));
		profile.setValue("HomeZIPCode", (String)wsAccount.get("addr1Zip"));
		profile.setValue("HomePhone", (String)wsAccount.get("dayPhone"));

		profile = conn.getProfile(umdId, "Private");
		profile.setValue("SSN", (String)wsAccount.get("ssn"));
		profile.setValue("BirthDay", (String)wsAccount.get("dob"));
		
		//profile.setValue("DriversLicense", (String)wsAccount.get("DriversLicense"));
		
		conn.close();
	}
	
	/**
	 * Compares user important fields info in UMD to those in Web service.
	 * @param umd
	 * @param ws
	 * @return boolean value
	 * @throws Exception
	 */
	public boolean isUserInfoMatched(Map umd, Map ws) throws Exception {
		
		Set keySet = umd.keySet();
		Iterator keysIt = keySet.iterator();
		
		while (keysIt.hasNext()) {
			String key = (String)keysIt.next();
			String value = (String)umd.get(key);
			
			if ("firstName".equalsIgnoreCase(key) && !value.equalsIgnoreCase((String)ws.get(key))) {
				return false;
			}
			if ("lastName".equalsIgnoreCase(key) && !value.equalsIgnoreCase((String)ws.get(key))) {
				return false;
			}
			if ("emailAddress".equalsIgnoreCase(key) && !value.equalsIgnoreCase((String)ws.get(key))) {
				return false;
			}
			if ("addr1Street".equalsIgnoreCase(key) && !value.equalsIgnoreCase((String)ws.get(key))) {
				return false;
			}
			if ("addr1City".equalsIgnoreCase(key) && !value.equalsIgnoreCase((String)ws.get(key))) {
				return false;
			}
			if ("addr1State".equalsIgnoreCase(key) && !value.equalsIgnoreCase((String)ws.get(key))) {
				return false;
			}
			if ("addr1Zip".equalsIgnoreCase(key) && !value.equalsIgnoreCase((String)ws.get(key))) {
				return false;
			}
			if ("dayPhone".equalsIgnoreCase(key) && !value.equalsIgnoreCase((String)ws.get(key))) {
				return false;
			}
			if ("ssn".equalsIgnoreCase(key) && !value.equalsIgnoreCase((String)ws.get(key))) {
				return false;
			}
			if ("dob".equalsIgnoreCase(key) && !DateUtilities.isDateStrEqual(value, (String)ws.get(key), "MM/dd/yyyy")) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Create or update WS account.
	 * 
	 * @param formBean
	 * @throws Exception
	 */
	public void processWsAccount(ReportedFraudsForm formBean) throws Exception {
		Map<String, String> account = new HashMap<String, String>();
		account.put("firstName", formBean.getVictim().get("firstName"));
		account.put("middleName", formBean.getVictim().get("middleName"));
		account.put("lastName", formBean.getVictim().get("lastName"));
		account.put("addr1Street", formBean.getVictim().get("addr1Street"));
		account.put("addr1City", formBean.getVictim().get("addr1City"));
		account.put("addr1State", formBean.getVictim().get("addr1State"));
		account.put("addr1Zip", formBean.getVictim().get("addr1Zip"));
		account.put("emailAddress", formBean.getVictim().get("emailAddress"));
		account.put("dayPhone", formBean.getVictim().get("dayPhone"));
		account.put("dob", formBean.getVictim().get("dob"));
		account.put("ssn", formBean.getVictim().get("ssn"));
		
		@SuppressWarnings("unchecked")
		Map<String, String> wsAccount = select(formBean.getUmdId());		
		if (wsAccount.get("lastName") == null || "".equals(wsAccount.get("lastName"))) {	// account does not exist in WS
			create(account, formBean.getUmdId());
		} else {
			update(account, formBean.getUmdId());
		}
	}
}
