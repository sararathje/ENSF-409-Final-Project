package FrontEnd;

import javax.swing.*;

/**
 * Opens a file chooser window.
 *
 * @author Sara Rathje
 * @version 1.0
 * @since April 12, 2018
 */
public class FileSelector {
    /**
     * File chooser
     */
    JFileChooser fileChooser;

    /**
     * Absolute path of the file chosen
     */
    String absolutePath;

    /**
     * Constructs an object of type FileSelector.
     */
    public FileSelector() {
        fileChooser = new JFileChooser();
        absolutePath = "";

        handleFileChoose();
    }

    /**
     * Handle file choose
     */
    private void handleFileChoose() {
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    /**
     * Gets the absolute file path.
     * @return absolute file path
     */
    public String getAbsoluteFilePath() {
        return absolutePath;
    }
}
