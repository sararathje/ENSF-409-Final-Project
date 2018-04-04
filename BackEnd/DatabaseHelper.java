package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Constants.DatabaseInformation;

import Models.*;

public class DatabaseHelper implements DatabaseInformation
{
	public Connection jdbc_connection;
	public PreparedStatement statement;
	
	/**
	 * Constructor for the database controller
	 */
	public DatabaseHelper()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(SQLException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
	}
	
	/**
	 * Checks the database table user table for matching login information
	 * @param login
	 */
	public void authenticate(Login login)
	{
		//TODO may return a user object
	}
	
	/**
	 * Adds a user to the database in the table user table
	 * @param user
	 */
	public void addUser(User user)
	{
		String sql = "INSERT INTO " + userTable +
				" VALUES ( " + user.getID() + ", '" +
				user.getLogin().getUN() + "', '" +
				user.getLogin().getPW() + "', '" +
				user.getEmail() + "', '" +
				user.getFirstName() + "', '" +
				user.getLastName() + "', '" +
				user.getUserType() + "')";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void removeUser(User user)
	{
		//TODO
	}
	
	public void addCourse(Course course)
	{
		//TODO
	}
	
	public void removeCourse(int courseID)
	{
		//TODO
	}
	
	/**
	 * Adds an assignment to the database in the assignment table
	 * @param assignment
	 * @param courseNumber
	 */
	public void addAssignment(Assignment assignment, int courseNumber)
	{
		String sql = "INSERT INTO " + assignmentTable +
				" VALUES ( " + assignment.getID() + ", " + 
				courseNumber + ", '" + 
				assignment.getName() + "', " + 
				assignment.isActive() + ", '" + 
				assignment.getDueDate() + "');";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void removeAssignment(int assignmentID)
	{
		//TODO
	}

	public void addGrade(char Grade)
	{
		//TODO
	}
	
	public void removeGrade()
	{
		//TODO
	}
	
	public void addSubmission(Submission submission)
	{
		//TODO
	}
	
}
