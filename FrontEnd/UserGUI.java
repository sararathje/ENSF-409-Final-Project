package FrontEnd;

import Constants.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Sara: honestly not really sure how this will work right now, but we'll see how this goes
public class UserGUI {
    /**
     * Client
     */
    Client client;

    /**
     * Login window
     */
    LoginWindow loginWindow;

    public UserGUI() {
        client = new Client();

        // Show login window on initial startup
        loginWindow = new LoginWindow();
        loginWindow.setVisible(true);

        // Add login window listeners
        addLoginWindowListeners();
        // addOtherListeners
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
