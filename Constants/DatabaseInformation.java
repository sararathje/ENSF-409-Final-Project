
package Constants;

public interface DatabaseInformation 
{
	/**
	 * Connection information for the database
	 */
	public String connectionInfo = "jdbc:mysql://localhost:3306/D2L",  

                
                //uncomment the one you want.
			  login          = "Jack_Glass",
			  password       = "S16T25R6";

//			  login          = "root",
//			  password       = "Rysql";
//                          
//                          login          ="root", 
//                          password       ="Greenbananas567";

	
	/**
	 * All the table names listed here
	 */
	public String databaseName = "D2L", userTable = "UserTable", courseTable = "CourseTable", studentEnrollment = "StudentEnrollment"
					, assignmentTable = "AssignmentTable", submissionTable = "SubmissionTable", gradeTable = "GradeTable";
	
}
