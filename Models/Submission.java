package Models;

import java.io.Serializable;

/**
 * Model for the D2L project that contains information about the student's
 * submission for an assignment
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
	 * Letter grade between 0 and 100;
	 */
	private int grade;
	
	/**
	 * Constructor for the class Submission
	 * @param localPath
	 */
	public Submission(String localPath)
	{
		this.path = localPath;
		grade = '\0';
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
	
	public void setGrade(char grade)
	{
		if(grade > 'F' || grade < 'A')
		{
			//TODO invalid grade exception??
			return;
		}
		else
		{
			this.grade = grade;
		}
	}


}
