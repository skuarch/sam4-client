package views.notifications;

import javax.swing.JOptionPane;

/**
 *
 * @author skuarch
 */
public class Alert {

    //==========================================================================
    public Alert(){        
    } // end Alert2
    
    //==========================================================================
    public void showAlert(int type, String message){
    
        if(type == 0){
            JOptionPane.showMessageDialog(null,message,"INFORMATION",JOptionPane.INFORMATION_MESSAGE);
        }else if(type == 1){
            JOptionPane.showMessageDialog(null,message,"WARNING",JOptionPane.WARNING_MESSAGE);
        }else if(type == 2){
            JOptionPane.showMessageDialog(null,message,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        
    }

} 