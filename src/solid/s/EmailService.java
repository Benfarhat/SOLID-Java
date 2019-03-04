package solid.s;

import java.util.Date;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
/*
 * Dernière implémentation de javax.mail datant du 02 mai 2006
 */
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
    private final static String password = "supersecretpassword";
    private final static String fromEmail = "My.super.fake.mail.for.dev@gmail.com";
    
    public void sendEmail(EmailInfo emailInfo) {
        
        Properties props = new Properties();
        
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Authenticator auth = new Authenticator() {

            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                //return super.getPasswordAuthentication();
                return new PasswordAuthentication(fromEmail, password);
            }
            
        };
        
        //Session session = Session.getDefaultInstance(props);
        Session session = Session.getInstance(props, auth);
        
        sendEmail(session, emailInfo.getEmail(), emailInfo.getSubject(), emailInfo.getBody());
        
    }
    
    private void sendEmail(Session session, String toEmail, String subject, String body) {

        try {
            MimeMessage msg = new MimeMessage(session);
            // set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "Fake Mail"));
            msg.setReplyTo(InternetAddress.parse(fromEmail, false));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
          } catch(AuthenticationFailedException afe) {
              /*
               * If you want it to work go here: https://www.google.com/settings/security/lesssecureapps
               * And turn on to allow less secure apps 
               */
            System.out.println("Authentication failed : " + afe.getMessage());
            afe.printStackTrace();
          } catch (Exception e) {
             e.printStackTrace();
          }
        
        
    }
    
}
