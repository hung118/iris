/*
 * CollectionXmlHandler.java
 *
 * Created on August 3, 2005, 2:21 PM
 */
package gov.utah.iris.xml;

import org.xml.sax.helpers.DefaultHandler;

import java.util.*;
import java.io.CharArrayWriter;

import org.xml.sax.*;
import org.apache.log4j.Logger;

import gov.utah.iris.xml.interfaces.CollectionHandler;

/**
 *
 * @author  SKINGDON
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class CollectionXmlHandler extends DefaultHandler implements CollectionHandler {

    private static Logger log = Logger.getLogger(gov.utah.iris.xml.MapXmlHandler.class);

    private String key = null;
	private Collection complaints = new ArrayList();
    private Map content = null;
    private CharArrayWriter elementValue = new CharArrayWriter();
    private String contentNodeName = "content";

    /** Creates a new instance of ComplaintsHandler */
    public CollectionXmlHandler() {
    }


    /**
     * Handles each occurance of a starting element
     * @param uri
     * @param localName
     * @param tagName
     * @param attributes
     * @throws SAXException
     */
    public void startElement( String uri, String localName, String tagName, Attributes attributes ) throws SAXException {

        log.debug("#-> Start Element: " + tagName);
        if ( contentNodeName.equals(tagName) || content == null ) {
            log.debug( "#-> Making new map: " + contentNodeName );
            content = new LinkedHashMap();
        }

        key = attributes.getValue("key");
        log.debug("#-> Key attribute: " + key);

        // reset elementValues
        elementValue.reset();

    }


    /**
     * Handles each occurance of an ending element
     * @param namespaceURI
     * @param localName
     * @param tagName
     * @throws SAXException
     */
    public void endElement( String namespaceURI, String localName, String tagName) throws SAXException {

        String value = elementValue.toString().trim();

        log.debug("#-> End Element: " + tagName);
        if ( contentNodeName.equals(tagName) ) {
            log.debug( "#-> storing complaint..." );
            complaints.add(content);
        }
        else if ( key != null ) {
            log.debug( key + ": " + value );
            content.put(key, value);
        }
        else {
            log.debug( tagName + ": " + value );
            content.put(tagName, value);
        }

    }

    /**
     * get the element content
     * @param chars
     * @param startPos
     * @param dataLength
     * @throws SAXException
     */
    public void characters( char[] chars, int startPos, int dataLength ) throws SAXException {
        elementValue.write(chars, startPos, dataLength);
    }


    /**
     * Returns the collection of data
     * @return
     */
    public Collection getData() {
        return this.complaints;
    }


    /**
     * Sets the content node name - this is the parent node that you have multiple instences of
     * @param contentNodeName
     */
    public void setContentNodeName( String contentNodeName ) {
        log.debug("#-> setting content node name: " + contentNodeName);
        this.contentNodeName = contentNodeName;
    }


    /**
     * Returns the name of the content node
     * @return
     */
    public String getContentNodeName() {
        return this.contentNodeName;
    }

}


