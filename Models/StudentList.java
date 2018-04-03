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
	
	private ArrayList<Student> students;
	
	public StudentList()
	{
		//TODO populate the arraylist of students
	}
	
	public Student searchStudent(String lastName)
	{
		//TODO implement this shit
	}
	
	public Student searchStudent(int studentID)
	{
		//TODO implement this shit
	}
	
	/**
	 * adds a student to the 
	 * @param newStudent
	 */
	public void enrollStudent(Student newStudent)
	{
		students.add(newStudent);
	}
	
	public void unenrollStudent(Student student)
	{
		//TODO implement this shit
	}
}
