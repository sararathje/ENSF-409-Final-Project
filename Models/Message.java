package Models;

import java.io.Serializable;

/**
 * Model for the D2L project that contains information about an email message
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @since April 3, 2018
 * @version 1.0
 */
public class Message implements Serializable
{

	/**
	 * Object ID when sending the object
	 */
	private static final long serialVersionUID = 12L;
	
	private String subject;
	
	private String text;
	
	public Message(String subject, String text)
	{
		this.subject = subject;
		this.text = text;
	}
	
	
	//Getters and Setters
	public String getSubject() { return subject;}
	
	public String getText() { return text;}
	
	public void setSubject(String subject) { this.subject = subject; }
	
	public void setText(String text) { this.text = text; }

}
