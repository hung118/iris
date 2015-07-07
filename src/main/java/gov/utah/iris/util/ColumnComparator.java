package gov.utah.iris.util;

import java.util.Comparator;
import java.util.Map;
import java.text.Collator;
import java.text.CollationKey;

/**
 * Date: Nov 8, 2005
 */
@SuppressWarnings("rawtypes")
public class ColumnComparator implements Comparator {

    private String column = "";

    public int compare( Object one, Object two ) {

        int result = -1;

        if (one instanceof Map && two instanceof Map) {

            Map map1 = (Map)one;
            Map map2 = (Map)two;

            Collator collator = Collator.getInstance();
            CollationKey[] keys = new CollationKey[2];
            keys[0] = collator.getCollationKey((String)map1.get(column));
            keys[1] = collator.getCollationKey((String)map2.get(column));

            result = keys[0].compareTo( keys[1] );

            if ( result == 0 ) {
                result = -1;
            }

        }

        return result;
    }

    public String getColumn() {
        return this.column;
    }

    public void setColumn( String column ) {
        this.column = column;
    }
}
