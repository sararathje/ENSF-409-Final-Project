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

	private Dropbox dropbox;
	
	private String name;
	
	private Date dueDate;
	
	private Boolean isActive;
	
}
