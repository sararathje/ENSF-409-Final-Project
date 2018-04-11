package Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model for the D2L project that contains information about an email draft
 * that can be sent
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @since April 3, 2018
 * @version 1.0
 */
public class Email implements Serializable
{

	/**
	 * Object ID when sending the object
	 */
	private static final long serialVersionUID = 11L;

	/**
	 * List of email address to which the email will be sent
	 */
	private ArrayList<String> receivers;
	
	/**
	 * Email address of the sender
	 */
	private String sender;
	
	/**
	 * Password for the sender's email provider
	 */
	private String senderPassword;
	
	/**
	 * Subject line of the email
	 */
	private String subject;
	
	/**
	 * The contents of the message of the email
	 */
	private String message;
	
	
	public Email(String senderEmail, String senderPassword)
	{
		receivers = new ArrayList<String>();
		this.sender = senderEmail;
		this.senderPassword = senderPassword;
	}
	
	public void compose(String subject, String message)
	{
		this.subject = subject;
		this.message = message;
	}
	
	/**
	 * Adds a recipient of the email
	 * @param emailAddress
	 */
	public void addRecipient(String emailAddress)
	{
		receivers.add(emailAddress);
	}
	
	
	//Getters and Setters
	
	public ArrayList<String> getReceivers() { return receivers; }
	
	public String getSender() { return sender; }
	
	public String getMessage() { return message; }
	
	public String getSubject() { return subject; } 
	
	public String getSenderPW() { return senderPassword; }
}
