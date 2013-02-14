package views.tabs;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

/**
 *
 * @author skuarch
 */
public class SnifferNavigator extends FactoryTabs {
    
    //==========================================================================
    public SnifferNavigator(){
        super();
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);  
    }
    
    //==========================================================================
    @Override
    public void updateUI() {
        super.updateUI();
    }

    //==========================================================================
    @Override
    public void addChangeListener(ChangeListener l) {
        
    }    
    
} // end class
