package gov.utah.iris.manager;

import gov.utah.iris.manager.interfaces.PersistantManager;

import java.util.Map;

/**
 * Complaint item class handles complaint item tasks with static methods.
 * 
 * @author hnguyen
 *
 */
public class ComplaintItemManager {
	
	private static PersistantManager manager = new PersistantManagerImpl();
	
	public static String createComplaintItem(Map<String, Object>form) throws Exception {
        return manager.create(form, "complaintItem");
	}
	
	public static String createSuspect(Map<String, Object>form) throws Exception {
        return manager.create(form, "person");
	}
	
	public static void updateComplaintItem(Map<String, Object>form, Map<String, String>ids) throws Exception {
	    manager.update(form, ids, "complaintItem");
	}
	
	public static void updateSuspect(Map<String, Object>form, Map<String, String>ids) throws Exception {
        manager.update(form, ids, "person");
	}
	
	public static Map<String, Object> selectComplaintItem(Map<String, String>ids) throws Exception {		
		Map<String, Object> fields = manager.select(ids, "complaintItem");		
		return fields;
	}
	
	public static void deleteComplaintItem(Map<String, String>ids) throws Exception {
        manager.delete(ids, "complaintItem");
	}
	
	public static void deleteSuspect(Map<String, String> ids) throws Exception {
		manager.delete(ids, "person");
	}
}
