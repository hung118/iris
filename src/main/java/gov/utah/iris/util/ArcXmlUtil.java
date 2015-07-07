package gov.utah.iris.util;

import java.net.*;
import java.io.*;

public class ArcXmlUtil {

    /**
     * Logger
     */
    //private static Log log = LogFactory.getLog(ArcXmlUtil.class);

    /**
     * Retreives a response from AGRC's ArcXML system by sending the specifiend xmlStr to the specified remoteUrl.
     * @param remoteUrl
     * @param xmlStr
     * @return
     */
    public static String send( String remoteUrl, String xmlStr ) {

        // verify attributes
        if ( remoteUrl == null ) {
            return "Missing remote URL";
        }
        if ( xmlStr == null ) {
            return "Missing XML";
        }

        // this is to stuff the final results in
        StringBuffer result = new StringBuffer();

        try {

            URL url = new URL(remoteUrl);
            URLConnection urlConn = url.openConnection();
            urlConn.setRequestProperty("Content-Type", "text/xml");
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);

            // send xml
            PrintWriter pw = new PrintWriter(urlConn.getOutputStream()); //urlConn.getOutputStream()
            pw.write(xmlStr);
            pw.close();

            // get result
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                result.append(inputLine);

            in.close();

        } catch (MalformedURLException e) {
            result = new StringBuffer("Error: Problems with the URL : ArcXmlUtil.getRemoteXML");
            e.printStackTrace();
        } catch (IOException e) {
            result = new StringBuffer( "Error: Problems with IO : ArcXmlUtil.getRemoteXML" );
            e.printStackTrace();
        }

        //System.out.println("*** Agrc Geocode - url: " + remoteUrl);
        //System.out.println("*** request: " + xmlStr);
        //System.out.println("*** response: " + result.toString());
        return result.toString();
    }
}

