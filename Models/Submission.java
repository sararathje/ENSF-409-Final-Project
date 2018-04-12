package Models;

import java.io.Serializable;

/**
 * Model for the D2L project that contains information about the student's
 * submission for an assignment. Note that the submission name is
 * assignmentID_studentID
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @since April 3, 2018
 * @version 1.0
 */
public class Submission implements Serializable
{

	/**
	 * Object ID when sending the object
	 */
	private static final long serialVersionUID = 10L;
	
	/**
	 * A string that contains the local path to the file of the submission.
	 * File must be a pdf or a txt file
	 */
	private String path;
	
	/**
	 * Grade between 0 and 100;
	 */
	private int grade;
	
	/**
	 * The assignment that the submission belongs to
	 */
	private int assignmentID;
	
	/**
	 * The course in which the assignment was assigned
	 */
	private int courseID;
	
	/**
	 * The id of the student who submitted the submission
	 */
	private int studentID;
	
	/**
	 * Constructor for the class Submission
	 * @param localPath
	 */
	public Submission(String localPath, int assignmentID, int courseID, int studentID)
	{
		this.path = localPath;
		this.grade = 0;
		this.assignmentID = assignmentID;
		this.courseID = courseID;
		this.studentID = studentID;
	}
	
	/**
	 * Uploads the submission file to the server database
	 */
	public void uploadSubmission()
	{
		//TODO figure out how to send files and stuff
	}
	
	/**
	 * Downloads the submission file from the server database
	 */
	public void downloadSubmission()
	{
		//TODO figure out how to send files and stuff
	}
	
	
	//Getters and setters
	
	public String getPath() { return path;}
	
	public int getGrade() { return grade;}
	
	public int getCourseID() { return courseID; }
	
	public int getAssignmentID() { return assignmentID; }
	
	public int getStudentID() { return studentID; }
	
	public void setGrade(int grade)
	{
		if(grade > 100 || grade < 0)
		{
			//TODO invalid grade exception??
			return;
		}
		else
		{
			this.grade = grade;
		}
	}

	public void setPath(String newPath) { this.path = newPath; }

}
