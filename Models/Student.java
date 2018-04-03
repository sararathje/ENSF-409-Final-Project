package Models;

import java.io.Serializable;

public class Student extends User implements Serializable
{
	/**
	 * Object ID when sending the object
	 */
	private static final long serialVersionUID = 1L;

	private int studentID;
	
	public Student(String firstName, String lastName, String emailAddress,
					char userType, int idNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.userType = userType;
		this.studentID = idNumber;
		setCourses();
	}
	
	public int getID() { return studentID; }
}
