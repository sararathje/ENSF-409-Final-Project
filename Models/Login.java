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
         * 
         */
        private volatile boolean authenticated;

	/**
	 * Constructor function for the class login
	 * @param un string that will be the user's username
	 * @param pw string that will be the user's password
	 */
	public Login(String un, String pw)
	{
		this.username = un;
		this.password = pw;
                authenticated = false;
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
        
        public boolean getAuthenticated() {
            System.out.println("was called");
            return authenticated;
            
        }
	
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
        
        public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
           // System.out.println("was called");
    }
}
