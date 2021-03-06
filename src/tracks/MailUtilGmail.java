package tracks;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MailUtilGmail {

    public static void sendMail(String to, String from,
            String subject, String body, boolean bodyIsHTML)
            throws MessagingException {


    	final String username = "ejackli2hw6";
		final String password = "j@v@cl@ss";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(subject);
	        if (bodyIsHTML) {
	            message.setContent(body, "text/html");
	        } else {
	            message.setText(body);
	        }

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    }
}
