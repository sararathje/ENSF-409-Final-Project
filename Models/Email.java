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
	 * The contents of the message of the email
	 */
	private Message message;
	
	
	public Email(String senderEmail)
	{
		receivers = new ArrayList<String>();
		this.sender = senderEmail;
	}
	
	public void compose()
	{
		//TODO figure how to compose an email. Probably will be done once the email GUI is implemented
	}
	
	public void send()
	{
		//TODO figure out how the email stuff works
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
	
	public Message getMessage() { return message; }
}
