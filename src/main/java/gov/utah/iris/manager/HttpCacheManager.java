package gov.utah.iris.manager;

import javax.servlet.http.HttpSession;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Session objects management class.
 * 
 * @author hnguyen
 *
 */
@SuppressWarnings("unchecked")
public class HttpCacheManager {

    private Map<String, Object> cache = null;
    private HttpSession session = null;

    /**
     * Constructor that sets the http session
     * @param session
     */
    public HttpCacheManager (HttpSession session) {
        this.session = session;
        if (this.session.getAttribute("cache") == null) {
            this.session.setAttribute("cache", new HashMap<String, Object>());
        }

        setCache(session);
    }

    /**
     * Adds the specified values to the cache, storing it with the specified type, i.e. accou, trans, suspects, ...
     * @param type
     * @param values
     */
    public void add(String type, Map<String, String> values) {
        if ( cache.get(type) == null ) {
            cache.put(type, new ArrayList<Map<String, String>>());
        }

		Collection<Map<String, String>> cacheValues = (Collection<Map<String, String>>)cache.get(type);
        cacheValues.add(values);
        save();
    }

    /**
     * Adds all of the elements in the specified collection to the cache of the specified type
     * @param type
     * @param values
     */
    public void addAll( String type, Collection<Map<String, String>> values) {
    	cache.put(type, values);
        save();
    }

    /**
     * Retreives a specific item from the cache
     * @param type
     * @return
     */
    public Collection<Map<String, String>> get(String type) {
		Collection<Map<String, String>> result = (Collection<Map<String, String>>)cache.get(type);

        if ( result == null ) {
            result = new ArrayList<Map<String, String>>();
        }

        return result;
    }

    /**
     * updates the specified item in the cache
     * @param type
     * @param values
     * @param index
     */
    public void update(String type, Map<String, String> values, int index) {
        if ( cache.get(type) == null ) {
            add(type, values);
        } else {
			ArrayList<Map<String, String>> items = (ArrayList<Map<String, String>>)cache.get(type);
            // only replace the values that are in the new map
            Map<String, String> item = (Map<String, String>)items.get(index);
            item.putAll(values);
        }

        save();
    }

    /**
     * Deletes the specified item from the cache
     * @param type
     */
    public void remove(String type) {
        cache.remove(type);
        save();
    }

    /**
     * Removes the specified index item from the specified type
     * @param type
     * @param index
     */
    public void remove(String type, int index) {
        if ( cache.get(type) != null ) {
			ArrayList<Map<String, String>> items = (ArrayList<Map<String, String>>)cache.get(type);

            if ( index >= 0 && index < items.size() ) {
                items.remove(index);
            }
        }

        save();
    }

    /**
     * Removes all contents from the cache
     */
    public void clear() {
        cache = new HashMap<String, Object>();
        save();
    }

    /**
     * sets the cache session to the specified session
     * @param session
     */
	public void setCache(HttpSession session) {
        if (session != null) {
        	cache = (Map<String, Object>)session.getAttribute("cache");
        }
    }

    /**
     * Returns the current cache
     * @return
     */
    public Map<String, Object> getCache() {
        return this.cache;
    }

    /**
     * Saves the current cache to the specified session
     */
    private void save() {
        session.setAttribute("cache", cache);
    }

    /**
     * Returns the number of objects in the cache
     * @return
     */
    public int size() {
        return cache.size();
    }
}
