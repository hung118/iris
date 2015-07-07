package gov.utah.iris.xml;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.Element;
import org.jdom.Attribute;
import org.jdom.input.SAXBuilder;

import gov.utah.iris.util.NameValueLinkedListBean;

import java.util.*;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Date: Oct 21, 2005
 */
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class NameValueParser {

    private static Logger log = Logger.getLogger(NameValueParser.class);

	public Map parse( String xml ) {

        SAXBuilder parser = new SAXBuilder();
        Document doc = null;
        Map result = null;

        //log.debug("#-> parsing xml...");
        try {
            //log.debug("#-> xml: " + xml );
            Reader reader = new StringReader(xml);
            doc = parser.build(reader);
        }
        catch (JDOMException e) {
            log.error("#-> JDOMException while parsing xml");
            log.error("#-> " + e.toString());
        }
        catch (IOException e) {
            log.error("#-> IOException while parsing xml");
            log.error("#-> " + e.toString());
        }

        //log.debug("#-> starting to read parsed xml");
        if ( doc != null ) {

            Element root = doc.getRootElement();
            //log.debug("#-> contains root elem: " + doc.hasRootElement() );

            if ( root != null ) {

                // getting NameValueLinkedListBean starting at root
                result = parseNode(root);
                //log.debug("#-> number of children in result: " + result.size());

            }
            else {
                log.error("#-> root was null...");
            }
        }

        //return toMap(result);
        if ( result == null ) {
            return new TreeMap();
        }
        return result;
    }


    /**
     * Parses an xml element node. It puts each of its child nodes into a map
     * using the tag name as the key and the value as the value.
     * @param node
     * @return
     */
    private Map parseNode( Element node ) {

        Map result = new TreeMap();

        Collection children = node.getChildren();
        Iterator childrenIt = children.iterator();
        while( childrenIt.hasNext() ) {

            Element child = (Element)childrenIt.next();
            String name = child.getName();
            //log.debug("#-> element name: " + name);

            if ( "group".equals(name) ) {
                Attribute groupIdAttr = child.getAttribute("id");
                String groupId = groupIdAttr.getValue();

                // get the group collection
                Collection group = (Collection)result.get(groupId);
                if ( result.get(groupId) == null ) {
                    //log.debug("#-> making new group: " + groupId);
                    group = new ArrayList();
                    result.put( groupId, group );
                }

                Collection groupChildren = child.getChildren();
                Iterator groupIt = groupChildren.iterator();
                //log.debug("#-> number of children in group: " + groupChildren.size());
                while ( groupIt.hasNext() ) {
                    Element groupChild = (Element)groupIt.next();
                    //log.debug("#-> groupChild element name: " + groupChild.getName());
                    group.add(parseNode(groupChild));
                }

            }
            else {
                //log.debug("#-> node value: " + child.getValue());
                result.put(name, child.getValue());
            }

        }

        return result;
    }


    /**
     * Converts the specified NameValueLinkedListBean to a Map
     * @param bean - NameValueLinkedListBean to convert
     * @return
     */
    private Map toMap( NameValueLinkedListBean bean ) {

        Map result = new TreeMap();

        // get the names
        Collection names = bean.getChildrenNames();

        // iterate over names, if they only have one in the collection, set it, otherwise, set collection
        //log.debug("#-> iterating over names...");
        Iterator namesIt = names.iterator();
        while ( namesIt.hasNext() ) {

            String name = (String)namesIt.next();
            //log.debug("#-> name: " + name);

            ArrayList children = (ArrayList)bean.getChild(name);

            if ( children != null ) {

                // Note: size() == 0 is skipped (ignored)
                if ( children.size() == 1 ) {

                    //log.debug("#-> only one child");
                    NameValueLinkedListBean child = (NameValueLinkedListBean)children.get(0);

                    //log.debug("#-> setting to result: " + child.getName() + ":" + child.getValue());
                    result.put(child.getName(),child.getValue());

                }
                else if ( children.size() > 1 ) {

                    Collection childGroup = new ArrayList();

                    // iterate over children
                    //log.debug("#-> iterating over children: " + children.size());
                    Iterator childrenIt = children.iterator();
                    while ( childrenIt.hasNext() ) {

                        NameValueLinkedListBean child = (NameValueLinkedListBean)childrenIt.next();

                        // call toMap on each one
                        //log.debug("#-> recurive toMap()...");
                        Map childMap = toMap(child);

                        // add results to a collection
                        //log.debug("#-> adding childMap to childGroup: " + childMap.size());
                        childGroup.add(childMap);

                    }

                    // add collection to result
                    //log.debug("#-> putting childGroup into result: " + name + ":" + childGroup.size());
                    result.put(name, childGroup);
                }

            }

        }

        return result;

    }
}
