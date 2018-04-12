package BackEnd;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import Models.Email;

// email: theonebooty@gmail.com
// PW: BigBooty
// Johnny Booty

/**
 * Sends emails to the recipients on the email list
 * @author Jack Glass
 *
 */
public class EmailHelper 
{	
	/**
	 * Sends the email to the recipients on 
	 * the email's recipient list
	 */
	public void sendEmail(Email email)
	{
		//Sets up the properties needed for the email
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.starttls.enable", "true"); // Using TLS
		properties.setProperty("mail.smtp.auth", "true"); // Authenticate
		properties.setProperty("mail.smtp.host", "smtp.gmail.com"); // Using Gmail Account
		properties.setProperty("mail.smtp.port", "587"); // TLS uses port 587

		//Starts a session with the email
		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator(){
				 protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication(email.getSender(), email.getSenderPW());
				 }
				});
		
		try {
			//Create message and set the sender email address
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email.getSender()));
			
			//Add recipients to the message
			ArrayList<String> receivers = email.getReceivers();
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receivers.get(0)));
			for(int i = 1; i < receivers.size(); i++)
			{
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(receivers.get(i)));
			}
			message.setSubject(email.getSubject());
			message.setText(email.getMessage());
			
			Transport.send(message); // Send the Email Message
			} catch (MessagingException e) {
			e.printStackTrace();
			}
	}
	
	public static void main(String [] args)
	{
		Email disEmail = new Email("theonebooty@gmail.com", "BigBooty");
		disEmail.compose("Test", "This is a test message from the one true booty");
		disEmail.addRecipient("livecity212@gmail.com");
		
		EmailHelper eh = new EmailHelper();
		eh.sendEmail(disEmail);
	}
}
