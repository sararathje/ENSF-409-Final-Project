package BackEnd;

/**
 * Retrieves, sends, and modifies information in a database for a learning tool.
 *
 * @author Sara Rathje, Rylan Kettles, Jack Glass
 * @version 1.0
 * @since April 4, 2018
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Constants.DatabaseInformation;
import java.sql.ResultSet;

import Models.*;
import java.util.ArrayList;
import java.util.Iterator;

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
        
        public ArrayList<Assignment> getAssignmentList() {
	   ArrayList<Assignment> list = new ArrayList<>();
	   String sql = "SELECT * FROM " + assignmentTable;

	   try {
			ResultSet rs = statement.executeQuery(sql);
                        //rs.next();
                       //Date newDate = parseDate(rs.getString(5));// issue here
			while(rs.next()){
                                Date newDate = parseDate(rs.getString(5));
				list.add(new Assignment(rs.getString(3), newDate, rs.getInt(1), rs.getInt(2),rs.getBoolean(4)));
			}
	   }
	   catch(SQLException ex){
		   ex.printStackTrace();
	   }

	   return list;
	}
        
        private Date parseDate(String input){
            String[] ints = input.split(",");
            int day = Integer.parseInt(ints[0]);
            int month = Integer.parseInt(ints[1]);
            int year = Integer.parseInt(ints[2]);
            int hour = Integer.parseInt(ints[3]);
            int minute = Integer.parseInt(ints[4]);
            
            Date date = new Date(day, month, year, hour, minute);
            return date;
        }

    /**
     * Gets course list from the database.
     * @param user user to get courses for
     * @return courselist
     */
	public ArrayList<Course> getCourseList(User user) {
	    return user.getUserType() == 'P' ? getProfessorCourses(user) : getStudentCourses(user);
	}

    /**
     * Gets list of courses professor has created.
     * @param prof professor
     * @return list of courses professor has created
     */
	private ArrayList<Course> getProfessorCourses(User prof) {
        ArrayList<Course> list = new ArrayList<>();
        // NOTE: This will only work for professors right now, and will not work for students
        String sql = "SELECT * FROM " + courseTable + " WHERE PROFID = ?";
        ResultSet rs;

        try {
            statement = jdbc_connection.prepareStatement(sql);

            // Specify update parameters
            statement.setInt(1, prof.getID());

            rs = statement.executeQuery();
            while(rs.next()) {
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
     * Gets list of courses a student is enrolled in.
     * @param student student
     * @return list of courses a student is enrolled in
     */
    private ArrayList<Course> getStudentCourses(User student) {
        ArrayList<Course> list = new ArrayList<>();

        String sql = "SELECT * FROM " + studentEnrollment + " WHERE STUDENTID = ?";
        ResultSet courseIDList;

        try {
            statement = jdbc_connection.prepareStatement(sql);

            // Specify update parameters
            statement.setInt(1, student.getID());

            courseIDList = statement.executeQuery();
            while(courseIDList.next()) {
                list.add(getCourseById(courseIDList.getInt("COURSEID")));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }

        return list;
    }

    /**
     * Gets the course by ID
     * @param courseID course ID
     * @return course with corresponding ID
     */
    private Course getCourseById(int courseID) throws SQLException {
        // Note that this only works for a course with unique ID.
        String sql = "SELECT * FROM " + courseTable + " WHERE COURSEID = ?";
        ResultSet courseIDList;

        statement = jdbc_connection.prepareStatement(sql);

        // Specify update parameters
        statement.setInt(1, courseID);

        courseIDList = statement.executeQuery();
        courseIDList.next();

        return new Course(courseIDList.getString(3), courseIDList.getInt(1),
                courseIDList.getInt(2), courseIDList.getBoolean(4));
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
	 * @param submissionID
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
     * Gets list of enrolled students in course from database
     * @param courseID course ID
     * @return list of students in course
     */
    public ArrayList<User> getEnrolledStudents(int courseID) {
        ArrayList<Integer> studentIDs = getStudentIDInCourse(courseID);
        ArrayList<User> studentList = new ArrayList<>();

        // Get students matching the IDs
        Iterator<Integer> iterator = studentIDs.iterator();

        try {
            while(iterator.hasNext()) {
                ResultSet students;
                Integer studentID = iterator.next();

                String sql = "SELECT * FROM " + userTable + " WHERE USERID = ?";

                statement = jdbc_connection.prepareStatement(sql);
                statement.setInt(1, studentID);
                students = statement.executeQuery();

                while(students.next()) {
                    Login userLogin = new Login(students.getString("USERNAME"),
                            students.getString("PASSWORD"));

                    userLogin.setAuthenticated(true);

                    studentList.add(new User(students.getInt("USERID"),
                            userLogin,
                            students.getString("EMAIL"),
                            students.getString("FIRSTNAME"),
                            students.getString("LASTNAME"),
                            students.getString("CLIENTTYPE").charAt(0)));
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    private ArrayList<Integer> getStudentIDInCourse(int courseID) {
        ArrayList<Integer> studentIDList = new ArrayList<>();
        ResultSet studentIDs;
        String sql = "SELECT * FROM " + studentEnrollment + " WHERE COURSEID = ?";

        try {
            statement = jdbc_connection.prepareStatement(sql);
            statement.setInt(1, courseID);
            studentIDs = statement.executeQuery();

            while(studentIDs.next()) {
                studentIDList.add(studentIDs.getInt("STUDENTID"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return studentIDList;
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
		
		Assignment nuts = new Assignment("Potato", new Date(1,1,1,1,1), 423, banana.getCourseNumber(), false);

		DatabaseHelper rock = new DatabaseHelper();
		//rock.addCourse(banana);
		//rock.addAssignment(nuts);
		
		rock.setAssignmentActive(nuts.getID());

		System.out.println("woot");
	}
}
