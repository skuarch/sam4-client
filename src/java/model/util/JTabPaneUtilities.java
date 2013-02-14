package model.util;

import controllers.sniffer.ControllerFloatFrame;
import controllers.sniffer.ControllerNavigator;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import views.panels.SnifferPanel;

/**
 * Utilities for JTabPane.
 *
 * @author skuarch
 */
public class JTabPaneUtilities {

    //==========================================================================
    /**
     * create a panel with a close label.<br/> this method is used for to put a
     * <code>JPanel</code> in a tab.
     *
     * @param title String with the name of tab
     * @param closeLabel label for close the tab
     * @return JPanel
     * @throws Exception
     */
    public static JPanel getPanelTitle(String title, JLabel closeLabel) throws Exception {

        if (title == null || title.equalsIgnoreCase("")) {
            throw new Exception("title is empty or null");
        }

        JPanel panelTitle = new JPanel();

        try {

            panelTitle.setOpaque(false);

            //close button
            panelTitle.add(closeLabel);

            //adding label            
            panelTitle.add(new JLabel(title));

        } catch (Exception e) {
            throw e;
        }

        return panelTitle;
    } // end getTabTitle

    //==========================================================================
    /**
     * close tab using its name.
     *
     * @param jTabbedPane JTabbedPane
     * @param nameComponent String with the name of component
     * @throws Exception
     */
    public static void closeTab(JTabbedPane jTabbedPane, String nameComponent) throws Exception {

        if (jTabbedPane == null) {
            throw new NullPointerException("jTabbedPane is null");
        }

        if (nameComponent == null || nameComponent.length() < 1) {
            throw new NullPointerException("nameComponent is null");
        }

        Component[] components = null;
        SnifferPanel sp = null;

        try {

            components = jTabbedPane.getComponents();

            for (int i = 0; i < components.length; i++) {

                if (components[i].getName() == null) {
                    continue;
                }

                if (components[i].getName().equalsIgnoreCase(nameComponent)) {
                    if(components[i] instanceof SnifferPanel){
                        sp = (SnifferPanel) components[i];
                        sp.destroy();
                    }
                    jTabbedPane.remove(jTabbedPane.getComponent(i));
                    sp = null;
                }
            }

        } finally {
            components = null;
        }

    } // end closeTab

    //==========================================================================
    /**
     * get the component using its name
     *
     * @param jTabbedPane
     * @param nameComponent
     * @return
     * @throws Exception
     */
    public static Component getComponent(JTabbedPane jTabbedPane, String nameComponent) throws Exception {

        if (jTabbedPane == null) {
            throw new NullPointerException("jTabbedPane is null");
        }

        if (nameComponent == null || nameComponent.length() < 1) {
            throw new NullPointerException("the nameComponent is null");
        }

        Component component = null;
        Component[] components = null;

        try {

            components = jTabbedPane.getComponents();

            if (components == null || components.length < 1) {
                return null;
            }

            for (Component c : components) {

                if (c == null || c.getName() == null || c.getName().length() < 1) {
                    continue;
                }

                if (c.getName().equalsIgnoreCase(nameComponent)) {
                    component = c;
                    break;
                }
            }

        } finally {
            components = null;
        }

        return component;

    } // end getComponent

    //==========================================================================
    /**
     * return the position of the component, if the component doesn't exist
     * return -1
     *
     * @param jTabbedPane JTabbedPane
     * @param nameComponent String
     * @return int
     * @throws Exception
     */
    public static int getComponentNum(JTabbedPane jTabbedPane, String nameComponent) throws Exception {

        if (jTabbedPane == null) {
            throw new NullPointerException("jTabbedPane is null");
        }

        if (nameComponent == null || nameComponent.length() < 1) {
            throw new NullPointerException("the nameComponent is null");
        }

        Component[] components = null;
        int numComponent = -1;

        try {

            components = jTabbedPane.getComponents();

            for (int i = 0; i < components.length; i++) {
                if (components[i].getName() == null) {
                    continue;
                }

                if (components[i].getName().equalsIgnoreCase(nameComponent)) {
                    numComponent = i;
                    break;
                }
            }

        } finally {
            components = null;
        }

        return numComponent;

    } // end getComponentNum

    //==========================================================================
    /**
     * return a number with the name of components that are similars.
     *
     * @param jTabbedPane
     * @param nameComponent Strinf with the name of component
     * @return
     * @throws Exception
     */
    public static int componentsSameName(JTabbedPane jTabbedPane, String nameComponent) throws Exception {

        if (jTabbedPane == null) {
            throw new NullPointerException("jTabbedPane is null");
        }

        if (nameComponent == null || nameComponent.length() < 1) {
            throw new NullPointerException("the nameComponent is null");
        }

        Component[] components = null;
        int nComponent = 0;
        String tmpName = null;
        int nullComponents = 0;

        try {

            components = jTabbedPane.getComponents();

            for (int i = 0; i < components.length; i++) {

                tmpName = components[i].getName();

                if (tmpName == null) {
                    nullComponents++;
                    continue;
                }

                if (tmpName.contains(nameComponent)) {
                    nComponent++;
                }

                tmpName = null;
            }

        } finally {
            components = null;
            tmpName = null;
        }

        return nComponent;

    } // end componentsSameName

    //==========================================================================
    /**
     * create a name of panel, example if one panel has the name BW and you want
     * add another panel with the same name (BW) this method checks how many
     * panels has the similar name and returns a new name in this case returns
     * (BW 1)
     *
     * @param view
     * @param jTabbedPane
     * @return
     * @throws Exception
     */
    public static String getPanelName(String view, JTabbedPane jTabbedPane) throws Exception {

        if (view == null || jTabbedPane == null) {
            throw new NullPointerException("jtabbedPane or view is null");
        }

        String panelName = null;
        int sameName = 0;
        Component[] components = null;
        sameName = JTabPaneUtilities.componentsSameName(jTabbedPane, view);

        try {
            if (sameName > 0) {
                panelName = view + " " + sameName;
            } else {
                panelName = view;
                return panelName;
            }

            components = jTabbedPane.getComponents();
            for (int i = 0; i < components.length; i++) {
                
                if (components[i].getName() == null || components[i].getName().length() < 1) {
                    continue;
                }

                if (components[i].getName().equalsIgnoreCase(panelName)) {
                    panelName = view + " " + (++sameName);
                    i=0;
                }
            }

        } finally {
            components = null;
        }

        return panelName;
    } // end getPanelName

    //==========================================================================
    /**
     * this label is for be a part of the title of tab. <br/> when this label is
     * pressed, this opens a new frame with the component int he middle
     *
     * @param component component for show in the new frame
     * @param job String with the name of tab.
     * @return
     */
    public static JLabel getLabelComponentOutBox(final Component component, final String tabName) {
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
                            }
                        });
                    }
                }).start();
            }
        });
        return label;
    } // end getLabelComponentOutBox
} // end class
