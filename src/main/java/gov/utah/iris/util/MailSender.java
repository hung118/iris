package gov.utah.iris.util;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Message.RecipientType;

import java.util.*;

//import sun.security.krb5.internal.crypto.e;

/**
 * Date: Sep 12, 2005
 */
@SuppressWarnings({"rawtypes"})
public class MailSender {   
    /**
     *
     * @param subject
     * @param body
     * @param from
     * @param recipients
     */
	public static void send( String subject, String body, String from, Collection recipients, Collection ccRecipients, Collection bccRecipients ) 
    throws Exception {

        String smtpHost = "send.state.ut.us";//"www.idtheft.utah.gov";
        //String smtpHost = "localhost";

        Properties props = new Properties();
        props.put("mail.smtp.host",  smtpHost);

        try{

            Session session = Session.getDefaultInstance(props, null);//new MailAuthenticator("idtheft", "Hcb5pXCy"));
            MimeMessage message = new MimeMessage(session);

            message.setText(body);
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);

            // add recipients
            addRecipients(message, recipients, RecipientType.TO);

            // add ccRecipients
            if ( ccRecipients != null ) {
                addRecipients(message, ccRecipients, RecipientType.CC);
            }

            // add bccRecipients
            if ( bccRecipients != null ) {
                addRecipients(message, bccRecipients, RecipientType.BCC);
            }

            // comment out for testing on laptop
            Transport.send(message);
        }
        catch ( MessagingException e ) {
            throw new Exception(e.getMessage());
        }

    }
	
    private static void addRecipients( MimeMessage message, Collection recipients, RecipientType type ) throws MessagingException{

        if ( message == null || recipients == null || type == null) {
            return;
        }

        // add recipients
        for (Iterator recipientsIt = recipients.iterator(); recipientsIt.hasNext();) {
            String recipient = (String)recipientsIt.next();
            message.addRecipient(type, new InternetAddress( recipient ));

        }

    }

}
