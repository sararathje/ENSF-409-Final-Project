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
	 * Creates a database and populates a table with client information
	 */
	public void createDB()
	{
		try {
			statement = jdbc_connection.prepareStatement("CREATE DATABASE " + databaseName);
			statement.executeUpdate();
			System.out.println("Created Database " + databaseName);
		} 
		catch( SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates a table on the database with the information on the text file
	 */
	public void createTable()
	{
		String sql = "CREATE TABLE " + tableName + "(" +
			     "ID INT(4) NOT NULL, " +
			     "FIRSTNAME VARCHAR(20) NOT NULL, " + 
			     "LASTNAME VARCHAR(20) NOT NULL, " + 
			     "ADDRESS VARCHAR(50) NOT NULL, " + 
			     "POSTALCODE CHAR(7) NOT NULL, " +
			     "PHONENUMBER CHAR(12) NOT NULL, " +
			     "CLIENTTYPE CHAR(1) NOT NULL)";
	try{
		statement = jdbc_connection.prepareStatement(sql);
		statement.executeUpdate();
		System.out.println("Created Table " + tableName);
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}	
	}
	
	public void addAssignment(Assignment assignment, int courseNumber)
	{
		//TODO
	}
	
	public void removeStudent(Student student)
	{
		//TODO
	}
	
	public void authenticate(Login login)
	{
		//TODO
	}
	
	public void addCourse(Course course)
	{
		//TODO
	}
	
	public void addGrade(char Grade)
	{
		//TODO
	}
	
	public void addSubmission(Submission submission)
	{
		//TODO
	}
	
}
