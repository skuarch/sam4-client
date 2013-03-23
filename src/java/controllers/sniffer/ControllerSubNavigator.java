package controllers.sniffer;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.util.JTabPaneUtilities;
import views.tabs.SnifferSubNavigator;

/**
 *
 * @author skuarch
 */
public class ControllerSubNavigator extends FabricTabs {

    private SnifferSubNavigator subNavigator = new SnifferSubNavigator();

    //==========================================================================
    public ControllerSubNavigator() {
    }

    //==========================================================================
    @Override
    public void addTab(final String tabName, final Component component) {
System.out.println("antes SubNavigator"  +  component.getClass());
        if (tabName == null || tabName.length() < 1) {
            NOTIFICATIONS.error("Imposible add new tab", new Exception("tabName is null"));
            return;
        }

        if (component == null || component.getName() == null || component.getName().length() < 1) {
            NOTIFICATIONS.error("Imposible add new tab", new Exception("component is null or doesn't have name"));
            return;
        }

        try {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {

                            JPanel panelTitle = null;

                            try {

                                panelTitle = JTabPaneUtilities.getPanelTitle(tabName, getCloseLabel(tabName));
                                //panelTitle.add(JTabPaneUtilities.getLabelComponentOutBox(component, tabName));
                                panelTitle.add(getLabelComponentOutBox(component, subNavigator.getName()));
                                component.setName(tabName);
                                subNavigator.addTab(tabName, component);
                                subNavigator.setTabComponentAt(subNavigator.getTabCount() - 1, panelTitle);
                                subNavigator.setSelectedIndex(subNavigator.getTabCount() - 1);

                            } catch (Exception e) {
                                NOTIFICATIONS.error("Imposible add new tab", e);
                            }
                        }
                    });
                }
            }).start();

        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible add tab", e);
        }

    }

    //==========================================================================
    @Override
    public void closeAllTabs() {
        subNavigator.removeAll();
    }

    //==========================================================================
    @Override
    public void closeTab(String nameComponent) {
        try {
            System.out.println("nameComponent Sub" + nameComponent);
            JTabPaneUtilities.closeTab(subNavigator, nameComponent);
        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible close tab", e);
        }
    }

    //==========================================================================
    @Override
    public Component getComponent(String nameComponent) {

        Component component = null;

        try {

            component = JTabPaneUtilities.getComponent(subNavigator, nameComponent);

        } catch (Exception e) {
            NOTIFICATIONS.error(nameComponent + " doesn't exists", e);
        }

        return component;
    }

    //==========================================================================
    @Override
    public Component[] getComponents() {
        return subNavigator.getComponents();
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
                        closeSubnavigatorNoTabs();

                    } catch (Exception ex) {
                        NOTIFICATIONS.error("Error closing tab", ex);
                    }
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible close tab", e);
        }

        return closeLabel;

    } // end getCloseLabel

    //==========================================================================
    public void setSubNavigator(SnifferSubNavigator subNavigator) {
        this.subNavigator = subNavigator;
    }

    //==========================================================================
    public JLabel getLabelComponentOutBox(final Component component, final String tabName) {

        JLabel label = new JLabel(new ImageIcon(JTabPaneUtilities.class.getResource("/views/images/external.png")));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                ControllerFloatFrame cff = new ControllerFloatFrame(component, tabName);
                                cff.setupInterface();
                                cff.setVisible(true);
                                ControllerNavigator.getInstance().checkTabs(tabName);
                                closeSubnavigatorNoTabs();
                            }
                        });
                    }
                }).start();
            }
        });
        return label;
    } // end getLabelComponentOutBox

    //==========================================================================}
    /**
     * close subnavigator if doesn't have tabs
     */
    private void closeSubnavigatorNoTabs() {
        //check if the subnavigator has a tab, 
        //if subnavigator doesn't have tabs close tab in navigator
        if (subNavigator.getTabCount() < 1) {
            ControllerNavigator.getInstance().closeTab(subNavigator.getName());
        }
    }
} // end class
