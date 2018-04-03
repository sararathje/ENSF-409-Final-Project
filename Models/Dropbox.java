package Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model for the D2L project that contains information about the dropbox
 * in which assignment submissions are submitted
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @since April 3, 2018
 * @version 1.0
 */
public class Dropbox implements Serializable
{

	/**
	 * Object ID when sending the object
	 */
	private static final long serialVersionUID = 9L;

	/**
	 * Array list that keeps track of all student submissions
	 */
	private ArrayList<Submission> submissions;
	
	/**
	 * Determines if student can still submit their assignments to the dropbox
	 */
	private Boolean isActive;
	
	
	public Dropbox()
	{
		submissions = new ArrayList<Submission>();
		isActive = true;
	}
	
	//Getters and setters
	
	public ArrayList<Submission> getSubmissions() { return submissions; }
	
	public Boolean isActive() { return isActive; }
	
	public void openDropbox() { isActive = true; }
	
	public void closeDropbox() { isActive = false; }
}
