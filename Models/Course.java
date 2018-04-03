package Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model for the D2L project that contains all relevant information of a course
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @since April 3, 2018
 * @version 1.0
 */
public class Course implements Serializable
{
	/**
	 * Object ID when sending the object
	 */
	private static final long serialVersionUID = 3L;

	private String courseName;
	
	private int courseNumber;
	
	private ArrayList<Assignment> assignments;
	
	private Boolean isActive;
	
	private StudentList studentList;
	
	
	
	public Course(String courseName, int courseNumber)
	{
		this.courseName = courseName;
		this.courseNumber = courseNumber;
		//TODO construct the rest of the fields in this class
	}
	
	/**
	 * Populates the assignment list
	 */
	public  void setAssignments()
	{
		//TODO add assignments to the array list
	}
	
	/**
	 * Adds assignment to the assignment list
	 * @param newAssignment
	 */
	public void addAssignment(Assignment newAssignment)
	{
		assignments.add(newAssignment);
	}
	
	
	
	//Getters and Setters
	
	public String getCourseName() { return courseName; }
	
	public int getCourseNumber() { return courseNumber; }
	
	public ArrayList<Assignment> getAssignmentList() { return assignments; }
	
	public Boolean isActive() { return isActive; }
	
	public StudentList getStudentList() { return studentList; }
	
	public void activateCourse() { isActive = true; }
	
	public void deactivateCourse() { isActive = false; }
	
	
	
}
