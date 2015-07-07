/*
 * XmlParser.java
 */

package gov.utah.iris.xml.interfaces;

import java.util.Collection;

/**
 * This interface is to be used for all XML parsing in classes using StoreAccessors.
 * This is the common parent interface for a strategy pattern.
 * @author  SKINGDON
 */
public interface XmlParser {

    /**
     * Parses the specified xml and returns a HashMap of values or some other data structure
     * @param xml - String of xml
     * @return data - Collection of values or other structures
     */
    @SuppressWarnings("rawtypes")
	public Collection parse(String xml);

}

