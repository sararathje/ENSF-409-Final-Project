package FrontEnd;

import Constants.*;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import static Constants.FontConstants.BUTTON_FONT;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Sara: honestly not really sure how this will work right now, but we'll see how this goes
// Rylan: i made this extend JFrame so that the student and proffessor gui's can inherit from it
// ..... not exactly sure how it will work together with the login.
public class UserGUI extends JFrame {
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
     * Login window
     */
    LoginWindow loginWindow;

    public UserGUI() {
        client = new Client();

        // Show login window on initial startup
        loginWindow = new LoginWindow();
        loginWindow.setVisible(true);
        
        //
        courseList = new CourseListView();
        createRefreshButton();

        // Add login window listeners
        addLoginWindowListeners();
        // addOtherListeners
    }
    
    /**
     * Creates the refresh button for the client list of the GUI.
     */
    private void createRefreshButton(){
        //JPanel bottom = new JPanel();
        //bottom.setPreferredSize(new Dimension(1000, 100));
       // bottom.setBackground(LOGIN_BACKGROUND_COLOUR);
        refresh = new JButton("Refresh");
        refresh.setFont(BUTTON_FONT);
        refresh.setPreferredSize(new Dimension(100, 50));
        //bottom.add(refresh);
        //return refresh;
    }

    /**
     * Adds listeners to login window.
     */
    private void addLoginWindowListeners() {
        loginWindow.addSignInButtonListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> credentials = loginWindow.getLoginCredentials();

                // Send credentials to server
                client.sendAuthenticationInformation(credentials.get(0), credentials.get(1));
            }
        });
    }

    public static void main(String[] args) {
        UserGUI userGUI = new UserGUI();
    }
}
