package gov.utah.iris.util;

import java.io.*;

/**
 * Date: May 16, 2005
 */
public interface XSLParser {

    /**
     * Sets the current working xml file to the specified file
     * @param xslFile
     */
    public void setXSL( File xslFile );

    public String parseToString( File xmlFile );

    public File parseToFile( File xmlFile );

}
