
package FrontEnd;

import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Models.Dropbox;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class DropBoxView extends JFrame{
   private Dropbox dropbox; 
   
   private ArrayList<SubmissionPanel> panelList;
   
   private Client client;
   
   private JScrollPane view;
   
   private JPanel displayPanel;

    public DropBoxView(Dropbox dropbox, Client client) {
        this.dropbox = dropbox;
        this.client = client;
        setPreferredSize(new Dimension(800, 300));
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
        displayPanel.setBackground(LOGIN_BACKGROUND_COLOUR);
        displayPanel.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
   
        view = new JScrollPane(displayPanel);
        
        add(view);
        showSubmissions();
        
        pack();
        setTitle("Dropbox");
    
    }
   
   private void showSubmissions(){
        for(int i = 0; i < dropbox.getSubmissions().size(); i++){
            displayPanel.add(new SubmissionPanel(dropbox.getSubmissions().get(i), client));
        }
    }
   
   
   
}
