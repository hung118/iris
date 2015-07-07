package gov.utah.iris.manager;

import gov.utah.iris.common.Constants;
import gov.utah.iris.manager.ComplaintManager;
import gov.utah.iris.manager.PersistantManagerImpl;
import gov.utah.iris.manager.interfaces.PersistantManager;
import gov.utah.iris.xml.NameValueParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Complaint class to handle complaint tasks with static methods.
 * 
 * @author hnguyen
 *
 */
public class ComplaintManager {
    
    public static Map<String, Object> selectComplaint(Map<String, String> ids, String type) throws Exception {
        PersistantManagerImpl manager = new PersistantManagerImpl();
        return manager.select(ids, type);
    }
    
    /**
     * Create complaint in Web service.
     * @param umdId
     * @return
     * @throws Exception
     */
	public static String createComplaint(String umdId) throws Exception {
		Map<String, String> ids = new HashMap<String, String>();
        ids.put(Constants.UMD_ID, umdId);
        PersistantManager manager = new PersistantManagerImpl();
        return manager.create(ids, "complaint");
	}
	
	/**
	 * Delete complaint item (not likely be used).
	 * @param ids
	 * @throws Exception
	 */
	public static void deleteComplaint(Map<String, String>ids) throws Exception {
        PersistantManager manager = new PersistantManagerImpl();
        manager.delete(ids, "complaint");
	}
    
	/**
	 * Submit complaint to webservices.
	 * @param ids
	 * @return collection of agencies
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Collection<Map<String, String>> submitComplaint(Map<String, Object>ids, Collection<String> jurisdictions) throws Exception {
		// create case for each incident
		List<Map<String, String>> jurisdictionIds = new ArrayList<Map<String, String>>();
		Iterator<String> jurisIt = jurisdictions.iterator();
		while (jurisIt.hasNext()) {
			String jurisdictionId = (String)jurisIt.next();
			Map<String, String> complaintCase = new HashMap<String, String>();
			complaintCase.put("id", jurisdictionId);
			jurisdictionIds.add(complaintCase);
		}
		
		// submit complaint to webservice and return the parsed response xml
		ids.put("jurisdictionId", jurisdictionIds);
		PersistantManager manager = new PersistantManagerImpl();
		String responseXml = manager.create(ids, "case");
		
		Map<String, Object> cases = new NameValueParser().parse("<div>" + responseXml + "</div>");
		return (Collection<Map<String, String>>)cases.get("agency");
	}
}
