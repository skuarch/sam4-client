package views.tabs;

import controllers.global.ControllerNotifications;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author skuarch
 */
public class FactoryTabs extends JTabbedPane {
    
    protected final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    
    //==========================================================================
    public FactoryTabs(){
        super();
    } // end FactoryTabs
    
    //==========================================================================
    /**
     * updateUI with thread.
     */
    @Override
    public void updateUI() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        FactoryTabs.super.updateUI();
                    }
                });
            }
        }).start();
    } // end updateUI
    
} // end class