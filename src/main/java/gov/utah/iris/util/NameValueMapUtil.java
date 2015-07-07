package gov.utah.iris.util;

import java.util.*;

/**
 * Date: Oct 21, 2005
 */
public class NameValueMapUtil {

    @SuppressWarnings("rawtypes")
	public static Map nameValueToCollection( NameValueLinkedListBean beans ) {

        if ( beans == null ) {
            return null;
        }

        Map results = new LinkedHashMap();

        /**
         * @todo
         * 1. get all the names in a map
         * 2. iterate over the names and get the values
         * 3. put the values in the map, using collections where there are multiple values

        // 1. get all the names in the results map
        Iterator beansIt = beans.iterator();
        while ( beansIt.hasNext() ) {
            // get bean
            NameValueLinkedListBean bean = (NameValueLinkedListBean)beansIt.next();
            if ( bean.hasChildren() ) {
                //
            }
            else {
                results.put(bean.getName(), bean.getValue());
            }
        }

        // 2. iterate over the names and get the values
        Set keyset = results.keySet();
        Iterator resultsIt = keyset.iterator();
        while ( resultsIt.hasNext() ) {

            String key = (String)resultsIt.next();

            beansIt = beans.iterator();
            while ( beansIt.hasNext() ) {
                // get bean
                NameValueLinkedListBean bean = (NameValueLinkedListBean)beansIt.next();
                Collection values = bean.getChild(key);
            }



        }*/

        return results;

    }

}
