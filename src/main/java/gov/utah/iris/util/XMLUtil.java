package gov.utah.iris.util;

import java.io.*;
import org.jdom.*;
import org.jdom.input.*;

/**
 * Date: May 19, 2005
 */
public class XMLUtil {

    /**
     * Logger
     */
    //private static Log log = LogFactory.getLog(XMLUtil.class);

    public static Document parse(String path, boolean validation) {

        // Create the JDOM SAXBuilder
        SAXBuilder saxBuilder = new SAXBuilder(validation);

        // Return the JDOM tree
        Document doc = new Document();
        try {
            doc = saxBuilder.build(new File(path));
        } catch (JDOMException e) {
            // builder failed
            e.printStackTrace();
        } catch (IOException e) {
            // File not found
            e.printStackTrace();
        }
        return doc;
    }

}
