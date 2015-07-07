package gov.utah.iris.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Date: Sep 12, 2005
 */
public class MailAuthenticator extends Authenticator {

    private String username = "";
    private String password = "";


    /**
     * 
     * @param username
     * @param password
     */
    public MailAuthenticator( String username, String password ) {
        this.username = username;
        this.password = password;
    }


    /**
     *
     * @return
     */
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.username,this.password);
    }

}
