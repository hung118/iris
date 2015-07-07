package gov.utah.iris.manager.interfaces;

import java.util.Map;

/**
 * Date: Sep 13, 2005
 */
@SuppressWarnings("rawtypes")
public interface PersistantManager {

	public String create( Map bean, String type ) throws Exception;

    public Map<String, Object> select(Map<String, String> ids, String type)  throws Exception;

    public void update( Map bean, Map ids, String type ) throws Exception;

    public void delete( Map ids, String type )  throws Exception;

}
