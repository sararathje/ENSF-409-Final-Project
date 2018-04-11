package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Constants.DatabaseInformation;
import java.sql.ResultSet;

import Models.*;
import java.util.ArrayList;

/**
 * Retrieves, sends, and modifies information in a database for a learning tool.
 *
 * @author Sara Rathje, Jack Glass, Rylan Kettles
 * @version 1.0
 * @since April 4, 2018
 */
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
	public User authenticate(Login login)
	{
	    // TODO: Sara, you come clean this up. This is dirty.
            User user = null;
            ResultSet userResult;
            
            String sql = "SELECT * FROM " + userTable + " WHERE " + 
                    "USERNAME = ? AND " + 
                    "PASSWORD = ?";
            
            try {
                statement = jdbc_connection.prepareStatement(sql);
                
                statement.setString(1, login.getUN());
                statement.setString(2, login.getPW());
                
                userResult = statement.executeQuery();
                
               while(userResult.next()){
                    Login userLogin = new Login(userResult.getString("USERNAME"),
                            userResult.getString("PASSWORD"));
                        
                    user = new User(userResult.getInt("USERID"), 
                                userLogin,
                                userResult.getString("EMAIL"),
                                userResult.getString("FIRSTNAME"),
                                userResult.getString("LASTNAME"),
                                userResult.getString("CLIENTTYPE").charAt(0)
                    );
               }

            } catch (SQLException e) {
                e.printStackTrace();
            }
          
            return user;
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
				user.getUserType() + "');";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes a user from the database
	 * @param user
	 */
	public void removeUser(User user)
	{
		String sql = "delete from " + userTable + " where USERID=" 
				+ user.getID();
		
		String bql = "delete from " + submissionTable + " where STUDENTID=" 
				+ user.getID();
		
		String dql = "delete from " + studentEnrollment + " where STUDENTID=" 
				+ user.getID();
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			
			statement = jdbc_connection.prepareStatement(bql);
			statement.executeUpdate();
			
			statement = jdbc_connection.prepareStatement(dql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds course to the database in the course table
	 * @param course
	 */
	public void addCourse(Course course)
	{
		String sql = "INSERT INTO " + courseTable +
				" VALUES ( " + course.getCourseNumber() + ", " +
				course.getProfID() + ", '" +
				course.getCourseName() + "', " +
				course.isActive() + ");";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

    /**
     * Gets course list from the database.
     * @return courselist
     */
	public ArrayList<Course> getCourseList() {
	   ArrayList<Course> list = new ArrayList<>();
	   String sql = "SELECT * FROM " + courseTable;

	   try {
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				list.add(new Course(rs.getString(3), rs.getInt(1), rs.getInt(2),
                        rs.getBoolean(4)));
			}
	   }
	   catch(SQLException ex){
		   ex.printStackTrace();
	   }

	   return list;
	}
	
	/**
	 * Removes course from the database
	 * @param courseID
	 */
	public void removeCourse(int courseID)
	{
		//TODO
	}
	
	/**
	 * Adds an assignment to the database in the assignment table
	 * @param assignment
	 */
	public void addAssignment(Assignment assignment)
	{
		String sql = "INSERT INTO " + assignmentTable +
				" VALUES ( " + assignment.getID() + ", " + 
				assignment.getCourseID() + ", '" + 
				assignment.getName() + "', " + 
				assignment.isActive() + ", '" + 
				assignment.getDueDate().toString() + "');";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes assignment from the database
	 * @param assignmentID
	 */
	public void removeAssignment(int assignmentID)
	{
		String sql = "delete from " + assignmentTable + " where ASSIGNMENTID=" 
					+ assignmentID;
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets an assignment as active in the database. Allows student to view the assignment
	 * @param assignmentID
	 */
	void setAssignmentActive(int assignmentID)
	{
		String sql = "update " + assignmentTable + " set ACTIVE = 1 where ASSIGNMENTID=" 
				+ assignmentID;
	try{
		statement = jdbc_connection.prepareStatement(sql);
		statement.executeUpdate();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	}
	
	/**
	 * Sets an assignment as inactive in the database. Allows student to view the assignment
	 * @param assignmentID
	 */
	void setAssignmentInactive(int assignmentID)
	{
		String sql = "update " + assignmentTable + " set ACTIVE = 0 where ASSIGNMENTID=" 
				+ assignmentID;
	try{
		statement = jdbc_connection.prepareStatement(sql);
		statement.executeUpdate();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	}

	/**
	 * Grades a submission in the database
	 * @param grade
	 * @param submission
	 */
	public void addGrade(int grade, Submission submission)
	{
		String sql = "INSERT INTO " + gradeTable +
				" VALUES ( " + submission.getAssignmentID() + ", " +
				submission.getStudentID() + ", " +
				submission.getCourseID() + ", " +
				grade + ");";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes a grade from the database
	 */
	public void removeGrade()
	{
		//TODO
	}
	
	/**
	 * Adds a submission to the database
	 * @param submission
	 */
	public void addSubmission(Submission submission)
	{
		//TODO
	}
	
	/**
	 * Removes submission from the database
	 * @param submissionID submission ID
	 */
	public void removeSubmission(int submissionID)
	{
		String sql = "delete from " + submissionTable + " where SUBMISSIONID="
				+ submissionID;
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Enrolls student in a course.
	 * @param studentID student ID
	 * @param courseID course ID
	 */
	public void enrollStudent(int studentID, int courseID)
	{
		String sql = "INSERT INTO " + studentEnrollment + " VALUES(?,?)";
		try {
			statement = jdbc_connection.prepareStatement(sql);

			// Specify update parameters
            statement.setInt(1, studentID);
            statement.setInt(2, courseID);

			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		//TODO: Implement enrolling student in GUI
		System.out.println("Enrolled student");
	}
	
	/**
	 * Unenroll student in a course.
	 * @param studentID student ID
     * @param courseID course name
	 */
	public void unenrollStudent(int studentID, int courseID)
	{
		String sql = "delete from " + studentEnrollment + " where STUDENTID = ? AND COURSEID = ?";
		try {
			statement = jdbc_connection.prepareStatement(sql);

			// Specify update parameters
            statement.setInt(1, studentID);
            statement.setInt(2, courseID);

			statement.executeUpdate();
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

    /**
     * Gets a list of students in a course specified by the given parameters.
     * @param courseID course ID
     * @return list of user IDs of students enrolled in the course
     */
	public ArrayList<Integer> getEnrolledStudents(int courseID) {
        ArrayList<Integer> enrolledStudentList = new ArrayList<>();
        String sql = "SELECT * FROM " + studentEnrollment + " WHERE COURSEID = ?";

        try {
            statement = jdbc_connection.prepareStatement(sql);

            statement.setInt(1, courseID);

            ResultSet enrolledStudents = statement.executeQuery();
            while(enrolledStudents.next()){
                enrolledStudentList.add(enrolledStudents.getInt("STUDENTID"));
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        return enrolledStudentList;
    }

    /**
     * Searches for student in user list.
     * @param lastName student last name
     * @param id student ID
     * @return students found in database that match the given last name or ID or both. Otherwise, return null.
     */
	public ArrayList<User> searchForStudent(String lastName, String id) {
	    // TODO: Sara: you come clean this shit up
	    ArrayList<User> matchedStudents = new ArrayList<> ();
        User user;
        ResultSet studentResults;

        Integer idParam = !id.equals("") ? Integer.parseInt(id) : null;
        String lastNameParam = !lastName.equals("") ? lastName : null;

        String sql = "SELECT * FROM " + userTable;

        try {
            // Create query string
            if (!id.equals("")) {
                String query = " WHERE USERID = ?" +
                        " AND LASTNAME = IFNULL(?, LASTNAME) " +
						" AND CLIENTTYPE = ?";
                sql += query;
            } else {
                String query = " WHERE LASTNAME = IFNULL(?, FIRSTNAME)" +
						" AND CLIENTTYPE = ?";
                sql += query;
            }

            // statement
            statement = jdbc_connection.prepareStatement(sql);

            if (!id.equals("")) {
                statement.setInt(1, idParam);
                statement.setString(2, lastNameParam);
                statement.setString(3, "S");
            } else {
                statement.setString(1, lastNameParam);
                statement.setString(2, "S");
            }

            // execute query
            studentResults = statement.executeQuery();

            while(studentResults.next()){
                Login userLogin = new Login(studentResults.getString("USERNAME"),
                        studentResults.getString("PASSWORD"));

                userLogin.setAuthenticated(true);

                user = new User(studentResults.getInt("USERID"),
                        userLogin,
                        studentResults.getString("EMAIL"),
                        studentResults.getString("FIRSTNAME"),
                        studentResults.getString("LASTNAME"),
                        studentResults.getString("CLIENTTYPE").charAt(0));

                matchedStudents.add(user);
            }
        } catch (SQLException e) { e.printStackTrace(); }

        return matchedStudents;
    }
	
	/**
	 * for testing
	 * @param args
	 */
	public static void main(String[] args)
	{
		Login deez = new Login("test", "password");
		Course banana = new Course("Banana", 2345, 4, true);
		
		Assignment nuts = new Assignment("Potato", new Date(1,1,1,1,1), 423, banana.getCourseNumber());

		DatabaseHelper rock = new DatabaseHelper();
		//rock.addCourse(banana);
		//rock.addAssignment(nuts);
		
		rock.setAssignmentActive(nuts.getID());

		System.out.println("woot");
	}
}
