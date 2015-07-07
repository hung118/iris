/*
 * MapXmlHandler.java
 *
 * Created on August 3, 2005, 2:21 PM
 */
package gov.utah.iris.xml;

import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
import java.io.CharArrayWriter;

import org.xml.sax.*;
import org.apache.log4j.Logger;

import gov.utah.iris.xml.interfaces.MapHandler;

/**
 *
 * @author  SKINGDON
 */
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
public class MapXmlHandler extends DefaultHandler implements MapHandler {

    private static Logger log = Logger.getLogger(MapXmlHandler.class);

	private Map currentMap = new HashMap();
    private Map data = new HashMap();
    private Stack tagNames = new Stack();
    private Stack maps = new Stack();
    private Stack subItems = new Stack();
    private CharArrayWriter elementValue = new CharArrayWriter();
    private int previousDepth = 0;

    /** Creates a new instance of MapXmlHandler */
    public MapXmlHandler() {
    }

    public void startElement( String uri, String localName, String tagName, Attributes attributes ) throws SAXException {

        // if this is the results tag make the map
        if ( "results".equals(tagName) ) {
            maps.push(data);
        }
        else {

            if ( tagNames.size() > previousDepth ) {

                // add collection and map
                Collection group = new ArrayList();
                Map subItemMap = new HashMap();

                // make new map the current map
                maps.push(subItemMap);

                // reset previousDepth to tagNames size
                previousDepth = tagNames.size();
            }
            else if ( tagNames.size() < previousDepth ) {
                //
            }

            tagNames.push(tagName);
        }

        // reset elementValues
        elementValue.reset();

    }

    public void endElement( String namespaceURI, String localName, String tagName) throws SAXException {

        // take tagName off stack
        tagNames.pop();

        // get the tag's content
        String value = elementValue.toString().trim();

        if ( tagNames.size() > 0 ) {

            // if the tagName is not already in the data map create a Collection to hold it
            if ( currentMap.get(tagName) == null ) {

            }



        }
        else {
            log.debug( tagName + ": " + value );
            currentMap.put(tagName, value);
        }
    }

    // get the element content
    public void characters( char[] chars, int startPos, int dataLength ) throws SAXException {
        elementValue.write(chars, startPos, dataLength);
    }

    public Map getData() {
        return this.data;
    }


}
