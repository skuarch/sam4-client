package views.dialogs;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author skuarch
 */
public class Chooser extends JFileChooser {

    //==========================================================================
    public Chooser() {
        setCurrentDirectory(new File("."));  
    } // end chooser

    //==========================================================================
    public String getPath() throws Exception{
        String name = null;
        int r = showOpenDialog(new JFrame());
        if (r == JFileChooser.APPROVE_OPTION) {
            name = getCurrentDirectory() + "/" + getSelectedFile().getName();            
        }
        return name;
    } // end getPath
} // end class
