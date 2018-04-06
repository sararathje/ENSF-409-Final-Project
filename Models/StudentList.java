package Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model for the D2L project that contains all relevant information of a student
 * list which is responsible for student enrollment in a course
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @since April 3, 2018
 * @version 1.0
 */
public class StudentList implements Serializable 
{

	/**
	 * Object ID when sending the object
	 */
	private static final long serialVersionUID = 6L;
	
	private ArrayList<User> students;
	
	public StudentList()
	{
		//TODO populate the arraylist of students
	}
	
	public User searchStudent(String lastName)
	{
		//TODO implement this shit
        // NOTE: This is just a placeholder to get the program to run
        return new User(1, new Login("", ""), "", "", "", 'C');
	}
	
	public User searchStudent(int studentID)
	{
		//TODO implement this shit
        // NOTE: This is just a placeholder to get the program to run
        return new User(1, new Login("", ""), "", "", "", 'C');
	}
	
	/**
	 * adds a student to the 
	 * @param newStudent
	 */
	public void enrollStudent(User newStudent)
	{
		students.add(newStudent);
	}
	
	public void unenrollStudent(User student)
	{
		//TODO implement this shit
	}
}
