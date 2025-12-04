package CDI;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;
import jakarta.enterprise.context.Dependent;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Dependent
public class mail  implements Serializable{

    public void sendEmail(String uemail,Date orderDate) {
        try {
            String host = "smtp.gmail.com";
            final String from = "pkrana020803@gmail.com"; // your Gmail account
            final String password1 = "blhu pehf zzgv bclp"; // your Gmail app-specific password
            String port = "465";
            String to = uemail;

            Properties prop = new Properties();
            prop.put("mail.smtp.user", from);
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.port", port);
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.debug", "true");
            prop.put("mail.smtp.socketFactory.port", port);
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.put("mail.smtp.socketFactory.fallback", "false");

            Session session1 = Session.getInstance(prop, new jakarta.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password1);
                }
            });

            session1.setDebug(true);

            MimeMessage message = new MimeMessage(session1);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Flex Fit Club");

            String htmlcode = "<p style='font-size:16px'> Thank You.</p><h3><h4> Your Order placed successfully:"
                    + "On"+orderDate+"</h4>";
            message.setContent(htmlcode, "text/html");
            
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException ex1) {
            ex1.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
//        mail emailSender = new mail();
//        emailSender.sendEmail("recipient@example.com");
    }
}
    
