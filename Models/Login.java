package Models;

import java.io.Serializable;

public class Login implements Serializable
{
	/**
	 * Object ID when sending the object
	 */
	private static final long serialVersionUID = 4L;

	/**
	 * String representing the user's user name
	 */
	private String username;
	
	/**
	 * String representing the user's password
	 */
	private String password;
	
	/**
	 * Constructor function for the class login
	 * @param un string that will be the user's username
	 * @param pw string that will be the user's password
	 */
	public Login(String un, String pw)
	{
		this.username = un;
		this.password = pw;
	}
	
	/**
	 * Getter function for username
	 * @return
	 */
	public String getUN() { return username;}
	
	/**
	 * Getter function for password
	 * @return
	 */
	public String getPW() { return password;}
	
	/**
	 * Setter function for username
	 * @param newUN
	 */
	public void setUN(String newUN) { username = newUN;}
	
	/**
	 * Setter function for password
	 * @param newPW
	 */
	public void setPW(String newPW) { password = newPW;}
}
