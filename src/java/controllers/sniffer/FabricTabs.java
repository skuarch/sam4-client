package controllers.sniffer;

import controllers.global.ControllerNotifications;
import java.awt.Component;
import javax.swing.JLabel;

/**
 *
 * @author skuarch
 */
public abstract class FabricTabs {

    protected static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();

    //==========================================================================
    public FabricTabs() {
    } // end FabricTabs

    public abstract void addTab(final String string, final Component component);
    
    public abstract void closeAllTabs();    

    public abstract void closeTab(final String nameComponent);

    public abstract Component getComponent(final String nameComponent);
    
    public abstract Component[] getComponents();
    
    public abstract JLabel getCloseLabel(final String nameComponent);   
    
} // end class
