package Models;

import java.util.ArrayList;

public abstract class User 
{
	protected Login login;
	
	protected String firstName;
	
	protected String lastName;
	
	protected String emailAddress;
	
	protected ArrayList<Course> courses;
	
	protected char userType;
	
	/**
	 * Populates the array list of courses
	 */
	public void setCourses()
	{
		//TODO put all student courses into the arraylist
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
	
	//TODO create setters if we need them
}
