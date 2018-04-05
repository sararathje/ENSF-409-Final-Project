package FrontEnd;

import Constants.*;
import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import static Constants.FontConstants.BUTTON_FONT;
import static Constants.FontConstants.TITLE_FONT;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.*;


// Sara: honestly not really sure how this will work right now, but we'll see how this goes
// Rylan: i made this extend JFrame so that the student and proffessor gui's can inherit from it
// ..... not exactly sure how it will work together with the login.
public class UserGUI extends JFrame implements ColourSchemeConstants, FontConstants, LabelConstants{
    /**
     * Client
     */
    Client client;
    
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
   
    /**
     * Login window
     */
    LoginWindow loginWindow;

    public UserGUI() {
        client = new Client();

        // Show login window on initial startup
        loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
        
        //Set frame format
        setPreferredSize(new Dimension(1500, 1000));
        setLayout(new BorderLayout());
        setBackground(LOGIN_BACKGROUND_COLOUR);
        
        // Create the courseList
        courseList = new CourseListView();
        add("Center", courseList);
       
        //add the refresh button
        addRefreshButton();

        //add 'My Courses' title to top
        addTitle();
        
       
        //add blank space to the left and the right
        addBorders();
        pack();
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        
        // Add login window listeners
       // addLoginWindowListeners();
        // addOtherListeners
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
    public void addCourse(String courseName){
        courseList.addCourse(courseName);
        this.validate();
    }

    /**
     * Gets the courseList
     * @return the courseList
     */
    public CourseListView getCourseList() {
        return courseList;
    }
    /**
     * Adds listeners to login window.
     */
//    private void addLoginWindowListeners() {
//        loginWindow.addSignInButtonListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                ArrayList<String> credentials = loginWindow.getLoginCredentials();
//
//                // Send credentials to server
//                client.sendAuthenticationInformation(credentials.get(0), credentials.get(1));
//            }
//        });
//    }

    public static void main(String[] args) {
        UserGUI userGUI = new UserGUI();
    }
}
