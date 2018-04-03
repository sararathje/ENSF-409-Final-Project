package BackEnd;

import Models.Email;

public class EmailHelper 
{
	/**
	 * Email draft 
	 */
	private Email email;
	
	/**
	 * Constructor for the emailHelper
	 * @param email
	 */
	public emailHelper(Email email)
	{
		this.email = email;
	}
	
	/**
	 * Sends the email to the recipients on 
	 * the email's recipient list
	 */
	public void sendEmail()
	{
		//TODO figure out how this shit works
	}
}
