package Models;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Model representing the users in the database. There are 2 types of users: professors and students
 * @author Jack
 *
 */
public class User implements Serializable
{
	/**
	 * Object ID when sending the object between the client and server
	 */
	private static final long serialVersionUID = 12L;

	private Login login;
	
	private String firstName;
	
	private String lastName;
	
	private String emailAddress;
	
	private ArrayList<Course> courses;
	
	private int userID;
	
	private char userType;
	
	
	public User (int userID, Login login, String emailAddress, String firstName, String lastName, char userType)
	{
		if(userType != 'S' || userType != 'P')
		{
			//TODO throw invalid user type exception
		}
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.userID = userID;
		this.userType = userType;
	}

        /**
         * Sets the ArrayList of courses.
         * @param courses 
         */
        public void setCourses(ArrayList<Course> courses) {
            this.courses = courses;
        }

	/**
	 * Adds a course to the array list of courses
	 * @param course
	 */
	public void addCourse(Course course)
	{
		courses.add(course);
	}
	
	//Getters and Setters
	
	public Login getLogin() { return login; }
	
	public String getFirstName() { return firstName; }
	
	public String getLastName() { return lastName; }
	
	public String getEmail() { return emailAddress; }
	
	public ArrayList<Course> getCourses() { return courses; }
	
	public char getUserType() { return userType; }
	
	public int getID() { return userID; }
	
	//TODO create setters if we need them
}
