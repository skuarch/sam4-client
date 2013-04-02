package controllers.sniffer;

import controllers.Controller;
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
import views.frames.Sniffer;

/**
 *
 * @author skuarch
 */
public class ControllerSniffer extends Controller {
    
    private Sniffer sniffer = new Sniffer();
    private ControllerTreeViews ctv = null;
    private ControllerTreeCollectors ctc = null;

    //==========================================================================
    private ControllerSniffer() {
        ctv = new ControllerTreeViews();
        ctc = new ControllerTreeCollectors();
    }

    //==========================================================================
    public static ControllerSniffer getInstance() {
        return ControllerSnifferHolder.INSTANCE;
    }

    //==========================================================================
    private static class ControllerSnifferHolder {
        
        private static final ControllerSniffer INSTANCE = new ControllerSniffer();
    }

    //==========================================================================
    public void clickTrees() {
        
        HashMap hm = HashMapUtilities.getHashMapSniffer();
        
        String view = null;
        String collector = null;
        String job = null;
        
        try {
            
            view = ctv.getSelected();
            collector = ViewUtilities.getOneBefore(ViewUtilities.getJtreeFromJTabPane(sniffer.getjTabbedPaneCollectors()).getSelectionPath());
            job = ctc.getSelected();
            
            if (!ValidationUtilities.validateClickTreeSniffer(view, collector, job)) {
                return;
            }
            
            hm.put("view", view);
            hm.put("job", job);
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
                        sniffer.getjSplitPaneMain().setRightComponent(component);
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
            
            sniffer.setTitle("Sniffer");
            ctv.setupInterface();
            sniffer.getjTabbedPaneViews().add(ctv.getPanelTreeViews());
            
            ctc.setupInterface();
            sniffer.getjTabbedPaneCollectors().add(ctc.getPanelTreeCollectors());
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
        
    } // end setupInterface

    //==========================================================================
    private void addListeners() {
        
        try {
            
            sniffer.getjMenuItemSearchIPAddress().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchIPAddress();
                }
            });
            
            sniffer.getjMenuItemSearchPort().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchPort();
                }
            });
            
            sniffer.getjMenuItemSearchSubnet().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchSubnet();
                }
            });
            
            sniffer.getjMenuItemCreateReport().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createReport();
                }
            });
            
            
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Unepected error", e);
        }
        
    } // end addListeners

    //==========================================================================
    private void searchIPAddress() {
        
        ControllerSeachIPAddress csipa = null;
        
        try {
            
            csipa = new ControllerSeachIPAddress();
            csipa.setupInterface();
            csipa.setVisible(true);
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
        
    } // end searchIPAddress

    //==========================================================================
    private void searchPort() {
        
        ControllerSearchPort csp = null;
        
        try {
            
            csp = new ControllerSearchPort();
            csp.setupInterface();
            csp.setVisible(true);
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
        
    } // end searchPort

    //==========================================================================
    private void searchSubnet() {
        
        ControllerSeachSubnet css = null;
        
        try {
            
            css = new ControllerSeachSubnet();
            css.setupInterface();
            css.setVisible(true);
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
        
    } // end searchSubnet

    //==========================================================================
    private void createReport() {
        
        String path = null;
        Chooser chooser = new Chooser();
        
        try {       
            
            if(ControllerNavigator.getInstance().getComponents().length < 1){
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
        sniffer.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
} // end class
