package FrontEnd;

import Constants.ColourSchemeConstants;
import Constants.FontConstants;
import Constants.LabelConstants;

import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.MessageConstants.EMPTY_NEW_COURSE_FIELDS;
import static Constants.MessageConstants.INVALID_COURSE_ID;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import Models.Email;

public class EmailWindow extends JDialog implements ColourSchemeConstants, FontConstants, LabelConstants
{

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 123L;
	
	JButton sendB, cancelB;
	
	JLabel subjectL, textL, emailAddressL, emailPWL;
	
	JTextArea subjectTA, textTA, emailPWTA;
	
	Client client;
	
	Email email;
	
	public EmailWindow(java.awt.Frame parent, boolean modal, Client client)
	{
		super(parent, modal);
		this.client = client;
		email = new Email(client.getAuthenticatedUser().getEmail(), null);
		
		initializeComp();
		setListener();
	}
	
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
	}
	
	private void setListener()
	{
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
                	EmailWindow.this.client.sendEmail(EmailWindow.this.email);
                	dispose();
                }       
        }});

        cancelB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });
	}

}
