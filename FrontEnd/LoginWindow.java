package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import Constants.*;

/**
 * Provides data fields and methods to create a user login GUI for a learning platform application.
 *
 * @authors Jack Glass, Rylan Kettles, Sara Rathje
 * @verison 1.0
 * @since April 2, 2018
 */
public class LoginWindow extends JFrame implements ColourSchemeConstants, FontConstants, LabelConstants {
    /**
     * Login panel
     */
    private JPanel loginPanel;

    /**
     * Username input filed
     */
    private JTextField usernameField;

    /**
     * Password input field
     */
    private JPasswordField passwordField;

    /**
     * Sign-in button
     */
    private JButton signInButton;

    /**
     * xPos and yPos for GridBagLayout, respectively
     */
    private int xPos, yPos;

    /**
     * Constructs an object of type LoginWindow.
     */
    public LoginWindow() {
        // Set title
        this.setTitle(LOGIN_TITLE);

        // Add components
        setFrameComponents(this.getContentPane());
        this.pack();
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }

    /**
     * Adds components to the login GUI.
     * @param pane pane to add components on
     */
    private void setFrameComponents(Container pane) {
        loginPanel = new JPanel();
        GridBagConstraints loginPanelConstraints = new LoginWindowConstraints(GridBagConstraints.HORIZONTAL,
                1.0, 1.0, 0, 0, new Insets(10, 10, 10, 10))
                .getConstraints();

        loginPanelConstraints.gridwidth = 4;

        // Set styling
        setLoginStyling(pane);

        // Add components
        addLoginTitle();
        addLoginFields();
        addSignInButton();

        pane.add(loginPanel, loginPanelConstraints);
    }

    /**
     * Adds login title to login GUI.
     */
    private void addLoginTitle() {
        JLabel title = new JLabel(LOGIN_TITLE);

        // Set styling
        title.setFont(PANEL_TITLE_FONT);
        title.setForeground(FOREGROUND_COLOUR);

        // Set constraints
        LoginWindowConstraints titleConstraints = new LoginWindowConstraints(GridBagConstraints.HORIZONTAL, 0.0,
                0.0,xPos, yPos++, new Insets(0, 10,5,0));

        // Add to panel
        loginPanel.add(title, titleConstraints.getConstraints());
    }

    /**
     * Adds login fields to login GUI.
     */
    private void addLoginFields() {
        Insets insets = new Insets(5, 10, 5, 10);

        addInputLabel(USERNAME, new LoginWindowConstraints(GridBagConstraints.HORIZONTAL,
                0.5, 0.0, xPos, yPos++, insets).getConstraints());

        addUsernameField(new LoginWindowConstraints(GridBagConstraints.HORIZONTAL,
                    0.5, 0.0, xPos, yPos++, insets).getConstraints());

        addInputLabel(PASSWORD, new LoginWindowConstraints(GridBagConstraints.HORIZONTAL,
                0.5, 0.0, xPos, yPos++, insets).getConstraints());

        addPasswordField(new LoginWindowConstraints(GridBagConstraints.HORIZONTAL,
                0.5, 0.0, xPos, yPos++, insets).getConstraints());
    }

    /**
     * Adds input label to the login panel.
     * @param labelText label text
     * @param constraints label constraints
     */
    private void addInputLabel(String labelText, GridBagConstraints constraints) {
        // Create and add label
        JLabel inputLabel = new JLabel(labelText);

        // Set styling
        inputLabel.setFont(INPUT_LABEL_FONT);
        inputLabel.setForeground(FOREGROUND_COLOUR);

        constraints.gridwidth = 2;

        // Add to panel
        loginPanel.add(inputLabel, constraints);
    }

    /**
     * Adds username text field to the login panel.
     * @param constraints text field constraints
     */
    private void addUsernameField(GridBagConstraints constraints) {
        usernameField = new JTextField(20);
        usernameField.setFont(INPUT_FONT);
        constraints.gridwidth = 2;

        // inputTextFields.add(inputField);
        loginPanel.add(usernameField, constraints);
    }

    /**
     * Adds password field to the login panel.
     * @param constraints password constraints
     */
    private void addPasswordField(GridBagConstraints constraints) {
        passwordField = new JPasswordField(20);
        passwordField.setFont(INPUT_FONT);
        passwordField.setEchoChar('*');
        constraints.gridwidth = 2;

        loginPanel.add(passwordField, constraints);
    }

    /**
     * Adds Sign-in button to login GUI.
     */
    private void addSignInButton() {
        signInButton = new JButton(SIGNIN);
        signInButton.setFont(BUTTON_FONT);
        signInButton.setBackground(FOREGROUND_COLOUR);
        signInButton.setForeground(LOGIN_BACKGROUND_COLOUR);

        Insets insets = new Insets(10, 0, 5, 10);

        LoginWindowConstraints buttonConstraints = new LoginWindowConstraints(GridBagConstraints.HORIZONTAL,
                0.5, 0.0, ++xPos, yPos, insets);
        GridBagConstraints constraints = buttonConstraints.getConstraints();

        loginPanel.add(signInButton, constraints);
    }

    /**
     * Sets login window styling.
     * @param pane login pane to set styling on
     */
    private void setLoginStyling(Container pane) {
        pane.setLayout(new GridBagLayout());
        pane.setBackground(LOGIN_BACKGROUND_COLOUR);

        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(LOGIN_BACKGROUND_COLOUR);

        // Set xPos and yPos
        xPos = 0;
        yPos = 0;
    }

    /**
     * Adds listener to sign-in button.
     */
    public void addSignInButtonListener(ActionListener listener) {
        signInButton.addActionListener(listener);
    }

    // Placeholder for now just to test what it looks like
    public static void main(String[] args) {
        LoginWindow login = new LoginWindow();
        login.setVisible(true);
    }

    /**
     * Gets login credentials.
     * @return login credentials (username and password)
     */
    public ArrayList<String> getLoginCredentials() {
        ArrayList<String> credentials = new ArrayList<String> ();

        credentials.add(usernameField.getText());
        credentials.add(String.valueOf(passwordField.getPassword()));

        return credentials;
    }
}
