package gov.utah.iris.util;

import java.util.*;

/**
 * Complaint item utility.
 * 
 * @author hnguyen
 *
 */
public class ComplaintItemUtil {

	/**
	 * Removes duplicate complaint item
	 * 
	 * Date: Sep 27, 2005
	 * Revised: 09/18/2014
	 * 
	 */
	public static Collection<Map<String, String>> removeDuplicateComplaintItems(Collection<Map<String, String>> complaintItems) {

        Collection<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, Object> tempMap = new HashMap<String, Object>();

        if (complaintItems == null) {
            return list;
        }

        Iterator<Map<String, String>> itemsIt = complaintItems.iterator();

        while ( itemsIt.hasNext() )  {
            Map<String, String> complaintItem = (Map<String, String>)itemsIt.next();
            if (complaintItem.get("fraudTypeCd") != null && complaintItem.get("fraudTypeSeq") != null) {
                String key = complaintItem.get("fraudTypeCd") + complaintItem.get("fraudTypeSeq");
                tempMap.put(key, complaintItem);
            }

        }

        Set<String> keyset = tempMap.keySet();
        Iterator<String> tempMapIt = keyset.iterator();
        while (tempMapIt.hasNext()) {
            String key = (String)tempMapIt.next();
            @SuppressWarnings("unchecked")
			Map<String, String> complaintItem = (Map<String, String>)tempMap.get(key);
            list.add(complaintItem);
        }

        return list;
    }

}
