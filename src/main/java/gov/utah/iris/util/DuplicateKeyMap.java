package gov.utah.iris.util;

import java.util.*;

/**
 * Date: Oct 20, 2005
 */
@SuppressWarnings("rawtypes")
public class DuplicateKeyMap implements Map {

	private Vector keys = new Vector();
    private Map store = new HashMap();


    /**
     * Clears all the keys and values in the store
     */
    public void clear() {
        keys.clear();
        store.clear();
    }


    /**
     * Returns true if the sore contains the specified key
     * @param key - Object key
     * @return boolean
     */
    public boolean containsKey( Object key ) {
        return keys.contains(key);
    }

    /**
     * Returns true is the store contains the specified value
     * @param value
     * @return
     */
    public boolean containsValue( Object value ) {
        return store.containsValue(value);
    }


    /**
     * Returns the entry set
     * @return
     */
    public Set entrySet() {
        return store.entrySet();
    }


    /**
     * Returns true if the specified object is equal to the current instance
     * @param o
     * @return
     */
    public boolean equals( Object o ) {
        return store.equals(o);
    }


    /**
     *
     * @param key
     * @return
     */
    public Object get( Object key ) {
        return null;
    }

    public int hashCode() {
        return store.hashCode();
    }

    public boolean isEmpty() {
        return store.isEmpty();
    }

    public Set keySet() {
        return store.keySet();
    }

    public Object put( Object key, Object value ) {
        return null;
    }

    public void putAll( Map map ) {
        //
    }

    public Object remove( Object key )  {
        return null;
    }

    public int size() {
        return keys.size();
    }

    public Collection values() {
        return store.values();
    }

}
