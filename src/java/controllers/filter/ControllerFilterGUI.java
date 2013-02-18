package controllers.filter;

import controllers.Controller;
import controllers.sniffer.ControllerNavigator;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import model.common.Exporter;
import model.util.HashMapUtilities;
import model.util.ValidationUtilities;
import model.util.ViewUtilities;
import views.dialogs.Chooser;
import views.frames.Filter2;

/**
 *
 * @author skuarch
 */
public class ControllerFilterGUI extends Controller {

    private Filter2 filter = null;
    private controllers.filter.ControllerTreeViews ctv = null;
    private ControllerTreeCollectors ctc = null;
    private String view = null;
    private String collector = null;
    private String log = null;
    private ControllerFilter cf = null;

    //==========================================================================
    private ControllerFilterGUI() {
        filter = new Filter2();
        ctv = new ControllerTreeViews();
        ctc = new ControllerTreeCollectors();
        cf = ControllerFilter.getInstance();
    }

    //==========================================================================
    public static ControllerFilterGUI getInstance() {
        return ControllerFilterGUI.ControllerFilterHolder.INSTANCE;
    }

    //==========================================================================
    private static class ControllerFilterHolder {
        private static final ControllerFilterGUI INSTANCE = new ControllerFilterGUI();
    }

    //==========================================================================
    public void clickTrees() {

        HashMap hm = HashMapUtilities.getHashMapFilter();

        try {

            view = ctv.getSelected();
            collector = ViewUtilities.getOneBefore(ViewUtilities.getJtreeFromJTabPane(filter.getjTabbedPaneCollectors()).getSelectionPath());
            log = ctc.getSelected();

            if (!ValidationUtilities.validateClickTreeFilter(view, collector, log)) {
                return;
            }

            hm.put("view", view);
            hm.put("log", log);
            hm.put("job", log);
            hm.put("collector", collector);

            // crear las tabs
            new TabsDriver().addTabNavigator(hm);

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end clickTrees

    //==========================================================================
    public void setRightComponent(final Component component) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        filter.getjSplitPaneMain().setRightComponent(component);
                    }
                });
            }
        }).start();
    } // end setRightComponent

    //==========================================================================
    @Override
    public void setupInterface() {

        try {

            addListeners();
            
            cf.setupInterface();

            filter.setTitle("filter");
            ctv.setupInterface();
            filter.getjTabbedPaneViews().add(ctv.getPanelTreeViews());

            ctc.setupInterface();
            filter.getjTabbedPaneCollectors().add(ctc.getPanelTreeCollectors());

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        try {

            filter.getjMenuItemOptions().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {

                        
                        cf.setVisible(true);

                    } catch (Exception ex) {
                        NOTIFICATIONS.error("Unexpected error", ex);
                    }

                }
            });

            /*filter.getjMenuItemCreateReport().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createReport();
                }
            });*/

        } catch (Exception e) {
            NOTIFICATIONS.error("Unepected error", e);
        }

    } // end addListeners

    //==========================================================================
    private void createReport() {

        String path = null;
        Chooser chooser = new Chooser();

        try {

            if (ControllerNavigator.getInstance().getComponents().length < 1) {
                return;
            }

            path = chooser.getPath();

            if (path == null) {
                return;
            } else {
                new Exporter().createPDFReport(path);
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        filter.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
