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
     * Boolean describing whether a user has been authenticated on login
     */
	private volatile boolean authenticated;

	/**
	 * Constructor function for the class login
	 * @param un string that will be the user's username
	 * @param pw string that will be the user's password
	 */
	public Login(String un, String pw) {
		this.username = un;
		this.password = pw;
		authenticated = false;
	}
	
	/**
	 * Getter function for username
	 * @return username
	 */
	public String getUN() { return username;}
	
	/**
	 * Getter function for password
	 * @return password
	 */
	public String getPW() { return password;}
	
	/**
	 * Setter function for username
	 * @param newUN new username
	 */
	public void setUN(String newUN) { username = newUN;}
	
	/**
	 * Setter function for password
	 * @param newPW new password
	 */
	public void setPW(String newPW) { password = newPW;}

    /**
     * Sets authenticated
     * @param authenticated value to be set
     */
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
    }
}
