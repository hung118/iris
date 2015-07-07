package gov.utah.iris.manager;

import gov.utah.iris.manager.interfaces.PersistantManager;
import gov.utah.iris.xml.NameValueParser;
import gov.udps.util.XmlConduitClient;
import gov.utah.dts.det.util.StringUtilities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Date: Sep 13, 2005
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class PersistantManagerImpl implements PersistantManager {

	boolean defaultFound = false;
	
    /**
     * Logger
     */
    static Logger log = Logger.getLogger(PersistantManagerImpl.class);

    static final String PASSWORD = "id4fraud";
    static String USER_NAME = "IDFRAUDT";
    
    static {
        try {
            ResourceBundle prop = ResourceBundle.getBundle("iris");
            USER_NAME = prop.getString("username");
        } catch (MissingResourceException e) {}
    }

    /**
     *
     * @param beanMap
     * @param type
     */
    public String create( Map beanMap, String type ) throws Exception {

        /**
         * 1. format to xml
         * 2. send over web service
         */

        // building request xml
        //log.info("#-> building request xml for create...");
        StringBuffer xml = new StringBuffer();
        xml.append("<insert>");
        xml.append("<type>").append(type).append("</type>");
        xml.append("<action>").append("insert").append("</action>");
        xml.append(getXml(beanMap));
        xml.append("</insert>");

        //log.info("sending xml...");
        String response = send(xml.toString().replaceAll("&", "&amp;"));

        // parse response to get id
        //log.info("parsing response xml...");
        String id = "";
        if ( response.indexOf("<id>") > -1 ) {

            // strip out <id> tag & content
            id = response.substring(response.indexOf("<id>") + 4, response.indexOf("</id>"));
            //log.debug("#-> stripped id: " + id);
        }
        else {
            //log.debug("#-> response xml: " + response);
            //log.debug("#-> problem with cases: no <id> tag: " + response.indexOf("<id>"));
        }

        //log.info("returning id xml: " + id);
        return id;
    }


    /**
     * @param ids
     * @param type
     * @return
     */
    public Map<String, Object> select(Map<String, String> ids, String type) throws Exception {

        // building request xml
        //log.info("#-> building request xml for select...");
        StringBuffer xml = new StringBuffer();
        xml.append("<select>");
        xml.append("<type>").append(type).append("</type>");
        xml.append("<action>").append("select").append("</action>");
        xml.append(getXml(ids));
        xml.append("</select>");

        // request from store
        //log.info("#-> requesting with xml...");
        String response = send(xml.toString());

        // parse response
        //log.info("#-> parsing response...");
        NameValueParser parser = new NameValueParser();
        Map<String, Object> parserResult = parser.parse(response.replaceAll("null", ""));

        //log.info("#-> Map of results: " + parserResult.size());
        return parserResult;
    }


    /**
     *
     * @param beanMap
     * @param ids
     * @param type
     */
    public void update( Map beanMap, Map ids, String type )throws Exception {

        /**
         * 1. format to xml
         * 2. send over web service
         */

        // use element key for node name
        beanMap.putAll(ids);

        // building request xml
        //log.info("#-> building request xml for update...");
        StringBuffer xml = new StringBuffer();
        xml.append("<update>");
        xml.append("<type>").append(type).append("</type>");
        xml.append("<action>update</action>");
        xml.append(getXml(beanMap));
        xml.append("</update>");

        //log.info("sending xml...");
        send(xml.toString().replace("&", "&amp;"));
    }


    /**
     *
     * @param ids
     * @param object
     */
    public void delete( Map ids, String object ) throws Exception {

        // building request xml
        //log.info("#-> building request xml for delete...");
        StringBuffer xml = new StringBuffer();
        xml.append("<delete>");
        xml.append("<type>").append(object).append("</type>");
        xml.append("<action>").append("delete").append("</action>");
        xml.append(getXml(ids));
        xml.append("</delete>");

        // request from store
        //log.info("sending delete request...");
        send(xml.toString());

    }

    /**
     *
     * @param xml
     * @return
     */
    private String send(String xml) throws Exception {

        // set up parameters for sending
        Map params = new HashMap();
        params.put("username", USER_NAME);
        params.put("password", PASSWORD);     
        params.put("udpsAgency","AGIDFR");
        params.put("organizationOriId", "UXAGIDFRD");
        params.put("type", "IRIS");
        params.put("authenticator", "UCJIS");
        params.put("routingCode", "L");
        params.put("systemId", "Webservices");
        params.put("transactionID", "1456789");

        // submit to persistant store
        //log.info("#-> xml: " + xml);
        String result = XmlConduitClient.send(xml, params);
        //log.info("result: " + result);
        return result;

    }

    private StringBuffer getXml( Map ids ) {

        StringBuffer result = new StringBuffer();

        Set keySet = ids.keySet();
        Iterator keysIt = keySet.iterator();

        while ( keysIt.hasNext() ) {
            String key = (String)keysIt.next();
            Object value = ids.get(key);
            //log.debug(key + ":" + value);

            if ( value instanceof String ) {
            	// Redmine 10609 - remove special characters
            	value =  StringUtilities.removeSpecialChars((String)value);
            	
                result.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
                if ("DEFAULT".equalsIgnoreCase((String)value)) {
                	defaultFound = true;
                }
            } else if ( value instanceof Collection ) {

                StringBuffer group = new StringBuffer();

                //log.debug("#-> value is a Collection...");

                group.append("<group id=\"").append(key).append("\">");

                // iterate over children and recurse
                Collection child = (Collection)value;
                Iterator childIt = child.iterator();

                while ( childIt.hasNext() ) {

                    Object childItem = childIt.next();

                    group.append("<").append(key).append(">");
                    if ( childItem instanceof Map ) {
                        group.append( getXml((Map)childItem) );
                    } else if ( childItem instanceof String ) {
                    	// Redmine 10609 - remove special characters
                    	childItem = StringUtilities.removeSpecialChars((String)childItem);
                    	
                        group.append( childItem );
                        if ("DEFAULT".equalsIgnoreCase((String)childItem)) {
                        	defaultFound = true;	// set true to global var.
                        }
                    }
                    group.append("</").append(key).append(">");
                }
                
                // insert <jurisdictionId>DEFAULT</jurisdictionId> in <group id="jurisdictionId"> for default access.
                // This occurs when creating case insert (after click on Submit Complaint button.)
                if ("jurisdictionId".equals(key) && !defaultFound) {	// prevent duplicate <id>DEFAULT</id>
                	group.append("<jurisdictionId><id>DEFAULT</id></jurisdictionId>");
                }

                group.append("</group>");

                result.append(group);
            }
        }

        return result;

    }
}
