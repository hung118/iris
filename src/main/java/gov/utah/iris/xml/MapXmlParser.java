package gov.utah.iris.xml;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import gov.utah.iris.util.NameValueLinkedListBean;

import java.util.*;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Date: Oct 21, 2005
 */
@SuppressWarnings({"rawtypes", "unchecked", "unused", "null"})
public class MapXmlParser {

    private static Logger log = Logger.getLogger(MapXmlParser.class);

	public Map parse( String xml ) {

        SAXBuilder parser = new SAXBuilder();
        Document doc = null;
        Map result = null;

        log.debug("#-> parsing xml...");
        try {
            log.debug("#-> xml: " + xml );
            Reader reader = new StringReader(xml);
            doc = parser.build(reader);
        }
        catch (JDOMException e) {
            log.debug("#-> JDOMException while parsing xml");
            log.debug("#-> " + e.toString());
        }
        catch (IOException e) {
            log.debug("#-> IOException while parsing xml");
            log.debug("#-> " + e.toString());
        }

        log.debug("#-> starting to read parsed xml");
        if ( doc != null ) {

            Element root = doc.getRootElement();
            log.debug("#-> contains root elem: " + doc.hasRootElement() );

            if ( root != null ) {

                /**
                 * @todo
                 * 1. get children
                 * 2. iterator over children
                 * 3. put name and value in map
                 * 4. if name already exists in map, take old one out, put in collection of maps
                 */

                Collection children = root.getChildren();
                Iterator childrenIt = children.iterator();
                while ( childrenIt.hasNext() ) {

                    Element child = (Element)childrenIt.next();

                    String childName = child.getName();

                    if ( result != null && result.get(childName) != null ) {
                        // does not exist
                        result.put(childName, child.getText());
                    }
                    else {
                        // already exists
                        Object tempValue = result.get(childName);
                        
                    }



                }

                log.debug("#-> number of results in result: " + result.size());
            }
            else {
                log.debug("#-> root was null...");
            }
        }

        return result;
    }


    /**
     *
     * @param node
     * @return
     */
    private NameValueLinkedListBean parseNode( Element node ) {

        NameValueLinkedListBean bean = new NameValueLinkedListBean();

        // set name
        bean.setName(node.getName());
        log.debug("#-> bean name: " + bean.getName());

        // set value
        bean.setValue(node.getTextTrim());
        log.debug("#-> bean value: " + bean.getValue());

        // if there are children add each child
        log.debug("#-> iterating over children...");
        Collection children = node.getChildren();
        log.debug("#-> number of children: " + children.size());
        Iterator childrenIt = children.iterator();
        while ( childrenIt.hasNext() ) {

            Element child = (Element)childrenIt.next();

            // create new NameValueLinkedListBean for node
            log.debug("#-> next child: " + child.getName());
            bean.addChild(parseNode( child ));

        }

        return bean;
    }

}