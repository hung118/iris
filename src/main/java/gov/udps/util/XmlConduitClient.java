package gov.udps.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import net.yagga.util.MetaJarResources;

import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Map;

/**
 * Utility that offers access to UCJIS systems. This utility will save you hours of trying to figure out XmlConduit.
 * Date: Jul 20, 2005
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class XmlConduitClient implements XmlConduitCommon {

    /**
     * Logger
     */
    static Logger log = Logger.getLogger(XmlConduitClient.class);
    
    static String WEBSERVICE_SERVER = "http://test.webservices.ucjis.utah.gov/XMLConduit/XMLConduit";
    
    static {
        try {
            ResourceBundle prop = ResourceBundle.getBundle("iris");
            WEBSERVICE_SERVER = prop.getString("webservice_server");
        } catch (MissingResourceException e) {}
    }

    /**
     * <p>Sends the specified content (probably xml), wraps it with the required udps and soap tempaltes.
     * Then sends it to the UCJIS system. Takes the response returned by the remote server, strips off the
     * soap and udps templates and then returns the result.</p>
     * @param payload - String xml that you would like to send to UCJIS through XmlConduit
     * @param params- HashMap of parameters that are needed to send. Possible parameters are:
     *      <ul>
     *          <li>agency</li>
     *          <li>username</li>
     *          <li>password</li>
     *          <li>agency</li>
     *          <li>type</li>
     *          <li>class</li>
     *          <li>authenticator</li>
     *          <li>routingCode</li>
     *          <li>udpsVersion</li>
     *          <li>systemId</li>
     *          <li>transactionID</li>
     *          <li>organizationORIID</li>
     *          <li>organizationID</li>
     *      </ul>
     * @return String response payload from server (xml)
     */
    public static String send( String payload, Map params ) throws Exception {

        String resultXml = null;
        String xml = new String(XmlConduitClient.addXmlConduitTemplate(payload, params));

        // wrap and send with the beast!
        log.info("IN: "  + xml);
        try {

            // seed element tag name
            String seedElemTagName = "udps:DocumentDescriptor";

            // get key material
            StringBuffer seedString = new StringBuffer(UCJISCipher.getNodeXMLKey(seedElemTagName, xml));
            //log.debug("Key Material: " + seedString);

            // get username and password
            StringBuffer username = new StringBuffer((String)params.get(USERNAME));
            StringBuffer password = new StringBuffer((String)params.get(PASSWORD));
            //log.debug("username: " + username);
            //log.debug("password: " + password);

            // encrypt the username
            //String encryptedUsername = UCJISCipher.encrypt(xml, USERNAME, seedElemTagName);
            String encryptedUsername = UCJISCipher.encrypt(seedString, username, false);
            params.put( USERNAME, encryptedUsername );
            //log.debug( "Encrypted username: " + encryptedUsername );

            // encrypt the password
            //String encryptedPassword = UCJISCipher.encrypt(xml, PASSWORD, seedElemTagName);
            String encryptedPassword = UCJISCipher.encrypt(seedString, password, false);
            params.put( PASSWORD, encryptedPassword );
            //log.debug( "Encrypted password: " + encryptedPassword );

            // add username and password to xml
            xml = new String( XmlConduitClient.addXmlConduitTemplate(payload, params) );

            // remove carriage returns...! Why? They are needed in textarea input, Hung (12/1/2009).
            // xml = xml.replaceAll("\r\n",""); 
            //log.info("xml: " + xml);

            // base 64 encode xml
            String encodedChars = new String( Base64.encode(xml.getBytes()) );
            //log.info( "Encoded Chars: " + encodedChars );

            // wrap in soap message
            String soap = XmlConduitClient.addSoap("<content>" + encodedChars + "</content>", null);
            //log.info("Soap: " + soap);

            // send
            log.info("#-> Connecting to " + WEBSERVICE_SERVER);
            String response = null;
            if (WEBSERVICE_SERVER.startsWith("https")) {	// SSL connection
            	response = URLUtil_SSL.getRemoteXML(WEBSERVICE_SERVER, soap);
            } else {	// default connection port 80
            	response = URLUtil.getRemoteXML(WEBSERVICE_SERVER, soap);
            }
            //log.info("Response: " + response);

            // remove soap
            response = XmlConduitClient.removeSoap(response);
            //log.info("Response w/o soap: " + response);

            // decrypt result
            String decryptedResult = new String(Base64.decode(response.toCharArray()) );
            //log.info( "Decrypted Result: " + decryptedResult );

            // remove xml conduit template
            resultXml = XmlConduitClient.removeXmlConduitTemplate(decryptedResult);
            log.info("OUT: " + resultXml);


        } catch ( Throwable t ) {
        	t.printStackTrace();
        	throw new Exception("Error from Web service: " + t.getMessage());
        }

        return resultXml;
    }


    /**
     * Adds the soap wrapper to the specified content
     * @param payload - xml String to become the payload of the
     * @param params - Map of soap name/value parameters
     * @return String - complete soap xml message
     */
    public static String addSoap( String payload, Map params ) {
        return XSLTUtil.transform(payload, new StreamSource(getTemplateStream("addSoap.xsl")), params);
    }


    /**
     * Removes the soap wrapper from the specified content and returns its payload
     * @param xml - xml String that needs its soap wrapper removed
     * @return String payload from the soap message
     */
    public static String removeSoap( String xml ) {
        return XSLTUtil.transform(xml, new StreamSource(getTemplateStream("removeSoap.xsl")), null);

    }


    /**
     * Adds the xml conduit wrapper around the specified content and builds the header from the specified params
     * @param payload - xml String that needs to be wrapped with the xml conduit wrapper
     * @param params - Map of name/value parameters. Possible parameters are:
     *      <ul>
     *          <li>agency</li>
     *          <li>username</li>
     *          <li>password</li>
     *          <li>type</li>
     *          <li>class</li>
     *          <li>authenticator</li>
     *          <li>routingCode</li>
     *          <li>udpsVersion</li>
     *          <li>systemId</li>
     *          <li>transactionID</li>
     *          <li>organizationORIID</li>
     *          <li>organizationID</li>
     *      </ul>
     * @return
     */
    public static String addXmlConduitTemplate(String payload, Map params) {
        return XSLTUtil.transform(payload, new StreamSource(getTemplateStream("addXmlConduitTemplate.xsl")), params);
    }


    /**
     * Removes the xml conduit wrapper and returns its payload
     * @param xml - xml String that needs its xml conduit wrapper removed
     * @return String payload from the soap message
     */
    public static String removeXmlConduitTemplate( String xml ) {
    	
        return XSLTUtil.transform(xml.replace("&", "&amp;"), new StreamSource(getTemplateStream("removeXmlConduitTemplate.xsl")), null);
    }


    /**
     * returns an inputstream of the specified template found in the xmlconduit-client.jar archive
     * @param templateName - String name of the template to retreive
     * @return InputStream
     */
    public static InputStream getTemplateStream( String templateName ) {
    	
		MetaJarResources jarResource = new MetaJarResources("xmlconduit-client");	// this throws 
		// TOP Error reading sizes in 'xmlconduit-client':java.util.zip.ZipException: The 
		// system cannot find the file specified. It's ok just ignore it (net.yagga.util.MetaJarResources.java)
    	
        return jarResource.openResource(templateName);
    }

}
