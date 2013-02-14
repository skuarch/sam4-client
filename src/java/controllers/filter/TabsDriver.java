package controllers.filter;

import controllers.global.ControllerNotifications;
import controllers.sniffer.ControllerSubNavigator;
import controllers.sniffer.ControllerTreeCollectors;
import controllers.sniffer.PanelCreator;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.util.JTabPaneUtilities;
import views.tabs.SnifferNavigator;
import views.tabs.SnifferSubNavigator;

/**
 * this class is for to handle the controllers,
 * <code>ControllerNavigator</code> and
 * <code>ControllerSubNavigator</code>
 *
 * @author skuarch
 */
public class TabsDriver {

    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    private static final ControllerNavigator CN = ControllerNavigator.getInstance();
    private static final controllers.filter.ControllerTreeViews CTV = new controllers.filter.ControllerTreeViews();
    private static final ControllerTreeCollectors CTC = new ControllerTreeCollectors();
    private static final ControllerFilterGUI CF = ControllerFilterGUI.getInstance();

    //==========================================================================
    /**
     * create a instance.
     */
    public TabsDriver() {
    } // end controllerTabs

    //==========================================================================    
    /**
     * add tab in navigator. the view becomes a
     * <code>Subnavigator</code> and job is the name of the tab.
     *
     * @param view String this String will be become * *
     * to <code>SubNavigator</code>
     * @param job String this is the name of the tab
     */
    //public void addTabNavigator(final String view, final String job, final String collector) {
    public void addTabNavigator(final HashMap hm) {

        if (hm == null) {
            NOTIFICATIONS.error("hashmap is null", new NullPointerException());
            return;
        }
        
        if(hm.get("view") == null || hm.get("view").equals("not applicable")){
            NOTIFICATIONS.error("view is null", new NullPointerException());
            return;
        }
        
        if(hm.get("job") == null || hm.get("job").equals("not applicable")){
            NOTIFICATIONS.error("job is null", new NullPointerException());
            return;
        }
        
        if(hm.get("collector") == null || hm.get("collector").equals("not applicable")){
            NOTIFICATIONS.error("collector is null", new NullPointerException());
            return;
        }
        

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JPanel panel = null;
                String panelName = null;
                SnifferNavigator navigator = CN.getNavigator();
                SnifferSubNavigator subNavigator = null;
                ControllerSubNavigator csn = new ControllerSubNavigator();                                

                try {

                    // crear el hashmap                       

                    //checar si el navegador tiene algo
                    //si no tiene nada se le agrega un subnavigator            
                    if (navigator.getTabCount() < 1) {
                        CF.setRightComponent(navigator);
                        subNavigator = new SnifferSubNavigator();
                    } else {

                        subNavigator = (SnifferSubNavigator) CN.getComponent(hm.get("job").toString());

                        if (subNavigator == null) {
                            subNavigator = new SnifferSubNavigator();
                        }

                    }                    
                    
                    panelName = JTabPaneUtilities.getPanelName(hm.get("view").toString(), subNavigator);                    
                    panel = (JPanel) new PanelCreator().createPanelFilter(hm);
                    
                    panel.setName(panelName);
                    subNavigator.setName(hm.get("job").toString());
                    csn.setSubNavigator(subNavigator);                   
                    csn.addTab(panelName, panel);                    
                    CN.addTab(hm.get("job").toString(), subNavigator);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                }
            }
        });

    } // end addTabNavigator 

    //==========================================================================
    public void swapTabIn(final Component component, final String job) {

        SnifferSubNavigator subNavigator = null;
        ControllerSubNavigator csn = new ControllerSubNavigator();
        JPanel panelTitleSubNavigator = null;
        String panelName = null;

        try {

            subNavigator = (SnifferSubNavigator) CN.getComponent(job);

            if (subNavigator == null) {
                subNavigator = new SnifferSubNavigator();
            }

            subNavigator.setName(job);
            panelName = JTabPaneUtilities.getPanelName(component.getName(), subNavigator);  
            component.setName(panelName);
            csn.setSubNavigator(subNavigator);
            panelTitleSubNavigator = JTabPaneUtilities.getPanelTitle(component.getName(), csn.getCloseLabel(component.getName()));
            panelTitleSubNavigator.add(JTabPaneUtilities.getLabelComponentOutBox(component, job));
            csn.addTab(component.getName(), component);
            CN.addTab(job, subNavigator);

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end swapTabIn
} // end class
