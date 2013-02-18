package controllers.sniffer;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import model.util.JTabPaneUtilities;
import views.panels.SnifferPanel;
import views.tabs.SnifferNavigator;

/**
 *
 * @author skuarch
 */
public class ControllerNavigator extends FabricTabs {

    private SnifferNavigator navigator = null;

    //==========================================================================
    private ControllerNavigator() {
        navigator = new SnifferNavigator();
    }

    //==========================================================================
    private static class ControllerNavigatorHolder {
        private static final ControllerNavigator INSTANCE = new ControllerNavigator();
    }

    //==========================================================================
    public static ControllerNavigator getInstance() {
        return ControllerNavigatorHolder.INSTANCE;
    }

    //==========================================================================
    public SnifferNavigator getNavigator() {
        return this.navigator;
    }

    //==========================================================================
    @Override
    public void addTab(String tabName, Component component) {

        if (tabName == null || tabName.length() < 1) {
            NOTIFICATIONS.error("Imposible add tab tabName is null or empty", new Exception());
            return;
        }

        if (component == null) {
            NOTIFICATIONS.error("Imposible add tab component is null", new Exception());
            return;
        }

        if (component.getName() == null || component.getName().length() < 1) {
            NOTIFICATIONS.error("Imposible add tab component doesn't have name", new Exception());
            return;
        }

        try {

            JLabel label = getCloseLabel(tabName);
            JPanel panelTitle = JTabPaneUtilities.getPanelTitle(tabName, label);

            navigator.addTab(tabName, component);
            navigator.setTabComponentAt(navigator.getTabCount() - 1, panelTitle);
            navigator.setSelectedIndex(navigator.getTabCount() - 1);

        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible add tab", e);
        }

    } // end addTab

    //==========================================================================
    @Override
    public void closeAllTabs() {
        navigator.removeAll();
    }

    //==========================================================================
    @Override
    public void closeTab(final String nameComponent) {

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                Component component = null;
                SnifferPanel sp = null;
                
                try {
                    
                    JTabPaneUtilities.closeTab(navigator, nameComponent);
                    component = JTabPaneUtilities.getComponent(navigator, nameComponent);
                    
                    if(component instanceof SnifferPanel){
                        sp = (SnifferPanel) component;
                        sp.destroy();
                    }
                    
                    
                } catch (Exception e) {
                    NOTIFICATIONS.error("Error closing tab", e);
                }

                return null;
            }
        }.execute();
    }

    //==========================================================================
    public void checkTabs(String string) {
        System.out.println("no mames " + string + " num tabs " + navigator.getTabCount());        
    }

    //==========================================================================
    @Override
    public Component getComponent(String nameComponent) {

        Component component = null;

        try {
            component = JTabPaneUtilities.getComponent(navigator, nameComponent);
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

        return component;
    }

    //==========================================================================
    @Override
    public Component[] getComponents() {
        return navigator.getComponents();
    }

    //==========================================================================
    @Override
    public JLabel getCloseLabel(final String nameComponent) {
        JLabel closeLabel = null;

        try {

            closeLabel = new JLabel(new ImageIcon(getClass().getResource("/views/images/close_12.gif")));

            closeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    try {

                        closeTab(nameComponent);

                    } catch (Exception ex) {
                        NOTIFICATIONS.error("Error closing tab", ex);
                    }
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Error creating close label", e);
        }

        return closeLabel;

    } // end getCloseLabel
    
} // end class
