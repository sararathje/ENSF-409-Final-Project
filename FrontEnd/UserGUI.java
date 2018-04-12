package FrontEnd;

import Constants.*;
import Models.Course;

import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import static Constants.FontConstants.BUTTON_FONT;
import static Constants.FontConstants.TITLE_FONT;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.*;

public class UserGUI extends JFrame implements ColourSchemeConstants, FontConstants, LabelConstants{
    /**
     * Client
     */
    protected Client client;
    
    /**
     * The list of courses on the GUI
     */
    protected CourseListView courseList;
    
    /**
    * The refresh button for the list of courses on the GUI
    */
    protected JButton refresh; 
    
    /**
     * the panel on the South area of the frame
     */
    protected JPanel bottom;

    public UserGUI(Client client) {
        this.client = client;

        //Set frame format
        setPreferredSize(new Dimension(1500, 1000));
        setLayout(new BorderLayout());
        setBackground(LOGIN_BACKGROUND_COLOUR);
        
        // Create the courseList
        courseList = new CourseListView(client);
        initializeCourseListView();
        add("Center", courseList);
       
        //add the refresh button
        addRefreshButton();

        //add 'My Courses' title to top
        addTitle();

        //add blank space to the left and the right
        addBorders();
        pack();
        setCloseOptions();
    }
    
	/**
	 * Sets the options for when the user presses exit
	 */
	private void setCloseOptions()
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				//JFrame frame = (JFrame) e.getSource();
				int result = JOptionPane.showConfirmDialog((JFrame) e.getSource(), "Are you sure you want to exit the application?",
				"Exit Application", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				client.quit();
				}
			}
		});
	}
	
    /**
     * Creates the refresh button for the course list of the GUI.
     */
    private void addRefreshButton(){
        refresh = new JButton("Refresh");
        refresh.setFont(BUTTON_FONT);
        refresh.setPreferredSize(new Dimension(100, 50));
        bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(1000, 100));
        bottom.setBackground(LOGIN_BACKGROUND_COLOUR);
        bottom.add(refresh);
        this.add("South", bottom);
        
        refresh.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ArrayList<CoursePanel> newList = new ArrayList<>();
                // empty out the current course list.
                courseList.setCourseList(newList);
                initializeCourseListView();
            }
        });
    }

    /**
     * Adds a title to the GUI.
     */
    private void addTitle(){
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(1000, 100));
        top.setBackground(LOGIN_BACKGROUND_COLOUR);
        JLabel title = new JLabel("My Courses");
        title.setFont(TITLE_FONT);
        title.setForeground(FOREGROUND_COLOUR);
        top.add(title);
        this.add("North", top);
    }
    
    /**
     * Adds blank space to either side of the GUI for aesthetic purposes.
     */
    private void addBorders(){
        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(100, 800));
        right.setBackground(LOGIN_BACKGROUND_COLOUR);
        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(100, 800));
        left.setBackground(LOGIN_BACKGROUND_COLOUR);
        add("East", right);
        add("West", left);
    }
     
      /**
     * Adds a course to the courseList
     * @param courseName the name of the course
     */
    public void addCourse(String courseName, Client client) {
        courseList.addCourse(courseName, client);
    }

    /**
     * Gets the courseList
     * @return the courseList
     */
    public CourseListView getCourseList() {
        return courseList;
    }
    
    /**
     * Initializes the displayPanel with courses.
     */
    private void initializeCourseListView() {
        client.getCourseInfo();
        ArrayList<Course> userCourseList = client.getAuthenticatedUser().getCourses();

        // Clear the current course list
        // Might be redundant
        courseList.getCourseList().clear();

        for(int i = 0; i < userCourseList.size(); i++) {
            Course course = userCourseList.get(i);
            // Course description is: name + " " + ID
            String courseDescription = course.getCourseName() + " " + Integer.toString(course.getCourseNumber());

            // Add course to the view
            addCourse(courseDescription, client);
        }
    }

    /**
     * Gets the courseListView
     * @return courseListView
     */
    public CourseListView getCourseListView() {
        return courseList;
    }
}
