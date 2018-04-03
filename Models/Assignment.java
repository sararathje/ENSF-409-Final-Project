package Models;

import java.io.Serializable;


/**
 * Model for the D2L project that contains all relevant information of an assignment
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @since April 3, 2018
 * @version 1.0
 */
public class Assignment implements Serializable{

	/**
	 * Object ID when sending the object
	 */
	private static final long serialVersionUID = 5L;

	/**
	 * Stores all assignment submissions
	 */
	private Dropbox dropbox;
	
	/**
	 * Name of the assignment
	 */
	private String name;
	
	/**
	 * When the assignment is due
	 */
	private Date dueDate;
	
	/**
	 * Determines if the student can see the assignment
	 */
	private Boolean isActive;
	
	/**
	 * Constructor for the class assignment
	 * @param name
	 * @param dueDate
	 */
	public Assignment(String name, Date dueDate)
	{
		dropbox = new Dropbox();
		this.name = name;
		this.dueDate = dueDate;
		isActive = false;
	}
	
	//Getters and setters
	
	public String getName() { return name;}
	
	public Date getDueDate() { return dueDate; }
	
	public Dropbox getDropbox() { return dropbox;}
	
	public void activateAssignment() { isActive = true;}
	
	public void deactivateAssignment() { isActive = false;}
}
