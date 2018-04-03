package Models;

import java.io.Serializable;

public class Professor extends User implements Serializable
{

	/**
	 * Object ID when sending the object
	 */
	private static final long serialVersionUID = 2L;

	private int professorID;
	
	public Professor(String firstName, String lastName, String emailAddress,
			char userType, int idNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.userType = userType;
		this.professorID = idNumber;
		setCourses();
	}
	
	public int getID() { return professorID; }
}
