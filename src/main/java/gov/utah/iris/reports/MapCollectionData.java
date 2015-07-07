package gov.utah.iris.reports;

import gov.utah.iris.common.Constants;
import gov.utah.iris.manager.PersistantManagerImpl;
import gov.utah.iris.manager.interfaces.PersistantManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapCollectionData {
	
	private static final String SUBITEM_LIST = "accou,trans,colle,credi,issue,tranx";
	private String umdId;
	private String trackingNumber;
	private String fraudTypeCd;
	private Collection<Map> mapCollection;
	private boolean subItem = false;
	private boolean suspect = false;
	private Collection<Map> subItemList = new ArrayList<Map>(2);
	private Collection<Map> suspectList;

	// Default constructor for Affidavit report
	public MapCollectionData(Map user) {
		mapCollection = new ArrayList<Map>(1);
		mapCollection.add(user);
	}
	
	// Constructor for form data from web service
	public MapCollectionData(String umdId, String trackingNumber, String fraudTypeCd) throws Exception {
		this.umdId = umdId;
		this.trackingNumber = trackingNumber;
		this.fraudTypeCd = fraudTypeCd;
		setMapCollection();
	}
	
	/**
	 * Retrieves data from web service and returns collection of Map objects.
	 * @return Collection of objects
	 */
	@SuppressWarnings("unchecked")
	public void setMapCollection() throws Exception {
        
		Map<String, String> ids = new LinkedHashMap<String, String>();
        ids.put(Constants.UMD_ID, umdId);
        ids.put("trackingNumber", trackingNumber);
        ids.put("fraudTypeCd", fraudTypeCd);
        ids.put("fraudTypeSeq", "1");
		
        PersistantManager manager = new PersistantManagerImpl();
        Map fields = manager.select( ids, "complaintItem" );
		
        // check for sub items and set them
        String items[] = SUBITEM_LIST.split(",");
        
        for (int i = 0; i < items.length; i++) {
        	Map<String, Collection> mapObj = new LinkedHashMap<String, Collection>();
        	Collection group = (Collection)fields.get(items[i]);
        	
        	if (group != null) {
        		subItem = true;
        		mapObj.put(items[i],group);
        		subItemList.add(mapObj);
        	}
        }
        
        // check for suspect and set it
        suspectList = (Collection)fields.get("suspect");
        if (suspectList != null) {
        	suspect = true;
        }
        
		mapCollection = new ArrayList<Map>();
		mapCollection.add(fields);
	}
	
	public Collection getMapCollection() {
		return mapCollection;
	}
	
	public boolean isSubItem() {
		return this.subItem;
	}
	
	public boolean isSuspect() {
		return this.suspect;
	}
	
	public Collection getSubItemList() {
		return this.subItemList;
	}
	
	public Collection getSuspectList() {
		return this.suspectList;
	}
}
