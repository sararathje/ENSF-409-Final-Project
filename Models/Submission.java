package Models;

import java.io.Serializable;
import java.util.Random;

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
     * Submission ID
     */
	private int submissionID;
	
	/**
	 * A string that contains the local path to the file of the submission.
	 * File must be a pdf or a txt file
	 */
	private String path;
	
	/**
	 * Letter grade between 0 and 100;
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
     * Submission time stamp
     */
	private String timeStamp;

    /**
     * Submission title
     */
    private String title;
	
	/**
	 * Constructor for the class Submission
	 * @param localPath
	 */
	public Submission(int assignmentID, int studentID, String localPath, int courseID, String title,
                      String timeStamp)
	{
        generateSubmissionID();
		this.path = localPath;
		this.grade = 0;
		this.assignmentID = assignmentID;
		this.courseID = courseID;
		this.studentID = studentID;
		this.title = title;
		this.timeStamp = timeStamp;
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

    /**
     * Generates pseudo-random 8-digit submission ID
     */
	private void generateSubmissionID() {
        int low = 10000000, high = 99999999;

        Random r = new Random();
        submissionID = r.nextInt(high - low + 1) + low;
    }
	
	
	//Getters and setters

    /**
     * Gets the path.
     * @return path
     */
	public String getPath() { return path;}

    /**
     * Gets the submission grade.
     * @return submission grade
     */
	public int getGrade() { return grade;}

    /**
     * Gets the course ID of the course the submission is for.
     * @return course ID
     */
	public int getCourseID() { return courseID; }

    /**
     * Gets the assignment ID of the assignment the submission is for.
     * @return assignment ID
     */
	public int getAssignmentID() { return assignmentID; }

    /**
     * Gets the student ID for the student submitting the assignment.
     * @return student ID
     */
	public int getStudentID() { return studentID; }

    /**
     * Gets the submission title
     * @return submission title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the submission ID
     * @return submission ID
     */
    public int getSumbmissionID() {
        return submissionID;
    }

    /**
     * Gets the time the submission was submitted
     * @return submission time
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the submission grade
     * @param grade submission grade
     */
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

    /**
     * Sets the submission path.
     * @param newPath submission path
     */
	public void setPath(String newPath) { this.path = newPath; }

}
