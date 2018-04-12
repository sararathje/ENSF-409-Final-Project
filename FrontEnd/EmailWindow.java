package FrontEnd;

import Constants.ColourSchemeConstants;
import Constants.FontConstants;
import Constants.LabelConstants;

import static Constants.MessageConstants.INVALID_COURSE_ID;
import static Constants.MessageConstants.MESSAGE_SENT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import Models.Email;
import Models.User;

public class EmailWindow extends JDialog implements ColourSchemeConstants, FontConstants, LabelConstants
{

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 123L;
	
	/**
	 * Buttons that either send an email or close the frame
	 */
	JButton sendB, cancelB;
	
	/**
	 * Labels for the frame
	 */
	JLabel subjectL, textL, emailAddressL, emailPWL;
	
	/**
	 * Text area where the subject, message and email password are written
	 */
	JTextArea subjectTA, textTA, emailPWTA;
	
	/**
	 * Client that communicates with the server
	 */
	Client client;
	
	/**
	 * Email model that will be sent to the server
	 */
	Email email;
	
	/**
	 * List of user who will receive the email
	 */
	ArrayList<User> emailReceivers;
	
	/**
	 * Constructor for the email window
	 * @param parent JFrame that calls this window
	 * @param modal Sets whether or not other frames can be accessed while this window is up
	 * @param client Client that connects to the server
	 * @param emailReceivers An array list of users that will receive the email
	 */
	public EmailWindow(CoursePage parent, boolean modal, Client client, ArrayList<User> emailReceivers)
	{
		super(parent, modal);
		this.client = client;
		email = new Email(client.getAuthenticatedUser().getEmail(), null);
		this.emailReceivers = emailReceivers;
		
		initializeComp();
		setListener();
		this.pack();
        this.setVisible(true);
	}
	
	/**
	 * Initializes all components of the window
	 */
	private void initializeComp()
	{
		sendB = new JButton("Send");
		sendB.setForeground(FOREGROUND_COLOUR);
		cancelB = new JButton("Cancel");
		cancelB.setForeground(FOREGROUND_COLOUR);
		
		subjectL = new JLabel("Subject Line");
		subjectL.setForeground(FOREGROUND_COLOUR);
		textL = new JLabel("Text of the Email");
		textL.setForeground(FOREGROUND_COLOUR);
		emailAddressL = new JLabel(this.client.getAuthenticatedUser().getEmail());
		emailAddressL.setForeground(FOREGROUND_COLOUR);
		emailPWL = new JLabel("PW:");
		emailPWL.setForeground(FOREGROUND_COLOUR);
		
		subjectTA = new JTextArea();
		textTA = new JTextArea();
		textTA.setSize(200, 200);
		emailPWTA = new JTextArea();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Send Email");
		
		BoxLayout layout = new BoxLayout(getContentPane(), javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(layout);
		
		JPanel pane1 = new JPanel();
		pane1.add(subjectL);
		pane1.add(subjectTA);
		
		JPanel pane2 = new JPanel();
		pane2.add(textL);
		pane2.add(textTA);
		
		JPanel pane3 = new JPanel();
		pane3.add(emailAddressL);
		pane3.add(emailPWL);
		pane3.add(emailPWTA);
		
		JPanel pane4 = new JPanel();
		pane4.add(sendB);
		pane4.add(cancelB);
		
		getContentPane().add(pane1);
		getContentPane().add(pane2);
		getContentPane().add(pane3);
		getContentPane().add(pane4);

		getContentPane().setBackground(LOGIN_BACKGROUND_COLOUR);
	}
	
	/**
	 * Adds a the listeners to the frame for the 2 JButtons on the frame
	 */
	private void setListener()
	{
		//Collects information from the text areas and sends an email through the database
		sendB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) 
            {
                if(EmailWindow.this.emailPWTA.getText().isEmpty() || EmailWindow.this.emailPWTA.getText().equals(""))
                {
                	JOptionPane.showMessageDialog(getContentPane(), INVALID_COURSE_ID, "",
                            JOptionPane.WARNING_MESSAGE);
                	return;
                }
                else
                {
                	EmailWindow.this.email.compose(EmailWindow.this.subjectTA.getText(), EmailWindow.this.textTA.getText());
                	EmailWindow.this.email.setSenderPW(EmailWindow.this.emailPWTA.getText());
                	
            		for(int i = 0; i < EmailWindow.this.emailReceivers.size(); i++)
            		{
            			EmailWindow.this.email.addRecipient(EmailWindow.this.emailReceivers.get(i).getEmail());
            		}
                	
                	EmailWindow.this.client.sendEmail(EmailWindow.this.email);
                    JOptionPane.showMessageDialog(getContentPane(), MESSAGE_SENT, "",
                            JOptionPane.PLAIN_MESSAGE);

                    dispose();
                }       
        }});

		//Closes the frame
        cancelB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });
	}

}
