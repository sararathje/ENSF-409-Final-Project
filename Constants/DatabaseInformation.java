
package Constants;

public interface DatabaseInformation 
{
	/**
	 * Connection information for the database
	 */
	public String connectionInfo = "jdbc:mysql://localhost:3306/D2L",  
			  login          = "Jack_Glass",//root
			  password       = "S16T25R6";//Rysql
	
	/**
	 * All the table names listed here
	 */
	public String databaseName = "D2L", userTable = "UserTable", courseTable = "CourseTable", studentEnrollment = "StudentEnrollment"
					, assignmentTable = "AssignmentTable", submissionTable = "SubmissionTable", gradeTable = "GradeTable";
	
}
