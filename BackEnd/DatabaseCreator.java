package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Constants.DatabaseInformation;

/**
 * Class that creates a database 
 * @author Jack Glass
 *
 */
public class DatabaseCreator implements DatabaseInformation
{
	public Connection jdbc_connection;
	public PreparedStatement statement;
	/**
	 * Connection info, login and password for database connection.
	 */
        
	/**
	 * Constructor for the database controller
	 */
	public DatabaseCreator()
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
	 * creates the user table in the database
	 */
	public void createUsertable()
	{
		String sql = "CREATE TABLE " + userTable + "(" +
			     "USERID INT(8) NOT NULL, " +
			     "USERNAME VARCHAR(20) NOT NULL, " + 
			     "PASSWORD VARCHAR(20) NOT NULL, " + 
			     "EMAIL VARCHAR(50) NOT NULL, " + 
			     "FIRSTNAME VARCHAR(20) NOT NULL, " +
			     "LASTNAME VARCHAR(20) NOT NULL, " +
			     "CLIENTTYPE CHAR(1) NOT NULL)";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			System.out.println("Created Table " + userTable);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	/**
	 * creates the course table in the database
	 */
	public void createCourseTable()
	{
		String sql = "CREATE TABLE " + courseTable + "(" +
			     "COURSEID INT(8) NOT NULL, " +
			     "PROFID INT(8) NOT NULL, " + 
			     "NAME VARCHAR(50) NOT NULL, " + 
			     "ACTIVE BIT(1) NOT NULL)";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			System.out.println("Created Table " + courseTable);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	/**
	 * creates the student enrollment table in the database
	 */
	public void createStudentEnrollmentTable()
	{
		String sql = "CREATE TABLE " + studentEnrollment + "(" +
			     "STUDENTID INT(8) NOT NULL, " +
			     "COURSEID INT(8) NOT NULL)";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			System.out.println("Created Table " + studentEnrollment);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}		
	}
	
	/**
	 * creates the assignment table in the database
	 */
	public void createAssignmentTable()
	{
		String sql = "CREATE TABLE " + assignmentTable + "(" +
			     "ASSIGNMENTID INT(8) NOT NULL, " +
			     "COURSEID INT(8) NOT NULL, " + 
			     "TITLE VARCHAR(50) NOT NULL, " + 
			     "ACTIVE BIT(1) NOT NULL, " +
			     "DUEDATE CHAR(16) NOT NULL)";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			System.out.println("Created Table " + assignmentTable);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	/**
	 * creates the submission table in the database
	 */
	public void createSubmissionTable()
	{
		String sql = "CREATE TABLE " + submissionTable + "(" +
			     "SUBMISSIONID INT(8) NOT NULL, " +
			     "ASSIGNMENTID INT(8) NOT NULL, " + 
			     "STUDENTID INT(8) NOT NULL, " + 
			     "PATH VARCHAR(100) NOT NULL, " + 
			     "TITLE VARCHAR(50) NOT NULL, " +
			     "GRADE INT(3), " +
			     "TIMESTAMP CHAR(16) NOT NULL)";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			System.out.println("Created Table " + submissionTable);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	/**
	 * creates the grade table in the database
	 */
	public void createGradeTable()
	{
		String sql = "CREATE TABLE " + gradeTable + "(" +
			     "ASSIGNMENTID INT(8) NOT NULL, " +
			     "STUDENTID INT(8) NOT NULL, " + 
			     "COURSEID INT(8) NOT NULL, " + 
			     "GRADE INT(3) NOT NULL)";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			System.out.println("Created Table " + gradeTable);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args)
	{
		DatabaseCreator d = new DatabaseCreator();
		//d.createDB();
		d.createAssignmentTable();
		d.createCourseTable();
		d.createGradeTable();
		d.createStudentEnrollmentTable();
		d.createSubmissionTable();
		d.createUsertable();
		
	}
}
