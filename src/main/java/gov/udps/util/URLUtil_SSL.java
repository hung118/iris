package gov.udps.util;

import java.io.*;
import java.net.*;
import java.security.Security;
import java.security.Provider;

/**
 * A utility implements secure communication between a Java client and an HTTPS server (test.webservices.ucjis.utah.gov,
 * webservices.ucjis.utah.gov, ...) To make this method (getRemoteXML()) works, keystore of certificates location needs to be
 * specified, either:
 * 1) import certicicates to cacerts in jdk jre\lib\security directory 
 * or
 * 2) put a line
 * 		JAVA_OPTS=-Djavax.net.ssl.trustStore=/hosts/iris/java/jdk1.5.0_06/jre/lib/security/client.jks
 * to tomcat's catalina.sh.
 *  
 * @author hnguyen
 *
 */
public class URLUtil_SSL {
	
    @SuppressWarnings("rawtypes")
	public static String getRemoteXML( String remoteUrl, String xml ) throws Exception {

        StringBuffer result = new StringBuffer();

        try {
        	// use the JSSE handler
        	System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol"); 
			Class clsFactory = Class.forName("com.sun.net.ssl.internal.ssl.Provider");
			if( (null != clsFactory) && (null == Security.getProvider("SunJSSE")) ) {
					Security.addProvider((Provider)clsFactory.newInstance());        
			}
			
			// now https can be used as normal http
            URL url = new URL( remoteUrl );

            if ( xml != null ) {
                URLConnection urlConn = url.openConnection();
                urlConn.setRequestProperty("Content-Type", "text/xml");
                
                urlConn.setDoOutput(true);
                urlConn.setDoInput(true);

                urlConn.setUseCaches(false);	// turn off caching
                
                // Write the arguments as post data (sending xml request)
                DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());
                out.writeBytes(xml);
                out.flush();

                // read in the result
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    result.append(inputLine);

                in.close();
                out.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String resultStr = result.toString();

        //System.out.println(resultStr);

        return resultStr;
    }
    
    public static String getRemoteXML_80( String remoteUrl, String xml ) {

        StringBuffer result = new StringBuffer();

        try {

            URL url = new URL( remoteUrl );
            //FileReader fr = new FileReader( xmlFilePath );
            //FileInputStream fis = new FileInputStream( xmlFilePath );
            //char[] buffer = new char[1024 * 10];
            //int bytes_read = 0;

            //if ( (bytes_read = fr.read(buffer)) != -1 ) {
            if ( xml != null ) {
                URLConnection urlConn = url.openConnection();
                urlConn.setRequestProperty("Content-Type", "text/xml");
                urlConn.setDoOutput(true);
                urlConn.setDoInput(true);

                /*byte[] out_buffer = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><GETCLIENTSERVICES/>".getBytes();

                OutputStream out = urlConn.getOutputStream();

                out.w.write(out_buffer);

                System.out.println(new String(out_buffer));*/

                PrintWriter pw = new PrintWriter(urlConn.getOutputStream()); //urlConn.getOutputStream()
                // send xml
                pw.write(xml);

                //pw.write(buffer, 0, bytes_read);
                pw.close();

                // read in the result
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    result.append(inputLine);

                in.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String resultStr = result.toString();

        //System.out.println(resultStr);

        return resultStr;
    }
}
