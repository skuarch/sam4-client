package controllers.global;

import controllers.Controller;
import controllers.e2e.ControllerEndToEnd;
import controllers.filter.ControllerFilter;
import controllers.filter.ControllerFilterGUI;
import controllers.firewall.ControllerFirewall;
import controllers.firewall.ControllerFirewallSam5;
import controllers.portScanner.ControllerPortScanner;
import controllers.shaper.ControllerShaper;
import controllers.sniffer.ControllerSniffer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.beans.CurrentUser;
import model.beans.GlobalConfiguration;
import views.frames.MainFrame;

/**
 *
 * @author skuarch
 */
public class ControllerMainFrame extends Controller {

    private MainFrame mainFrame = null;
    private ControllerSniffer cs = ControllerSniffer.getInstance();
    private ControllerFirewall cf = ControllerFirewall.getInstance();
    private ControllerPortScanner controllerPortScanner = ControllerPortScanner.getInstance();
    private ControllerEndToEnd e2e = ControllerEndToEnd.getInstance();
    private ControllerShaper shaper = ControllerShaper.getInstance();
    private ControllerFilter controllerFilter = ControllerFilter.getInstance();
    private ControllerFilterGUI cfg = ControllerFilterGUI.getInstance();

    //==========================================================================    
    /**
     * create the instance and set
     * <code>configuration</code>.
     */
    private ControllerMainFrame() {

        try {

            mainFrame = new MainFrame();
            cs.setupInterface();
            cf.setupInterface();
            controllerPortScanner.setupInterface();
            e2e.setupInterface();
            shaper.setupInterface();
            controllerFilter.setupInterface();
            cfg.setupInterface();

            if (CurrentUser.getInstance().getMainServer() == null || CurrentUser.getInstance().getMainServer().length() < 2 || CurrentUser.getInstance().getMainServer().equalsIgnoreCase("0.0.0.0")) {
                mainFrame.getjLabelMessage().setText("Please configure the main server and port in: Configuration >> user configuration");
                setEnableButtons(false);
            }

            if (CurrentUser.getInstance().getMainServerPort() < 2) {
                mainFrame.getjLabelMessage().setText("Please configure the main server port in: Configuration >> user configuration");
                setEnableButtons(false);
            }

            addListeners();

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end ControllerMainFrame

    //==========================================================================
    public void setMessage(String text) {
        mainFrame.getjLabelMessage().setText(text);
    } // end setMessage

    //==========================================================================
    public void setEnableButtons(boolean flag) {
        mainFrame.getjButtonFilter().setEnabled(flag);
        mainFrame.getjButtonScanner().setEnabled(flag);
        mainFrame.getjButtonShaper().setEnabled(flag);
        mainFrame.getjButtonSniffer().setEnabled(flag);
        mainFrame.getjButtonFirewall().setEnabled(flag);
        mainFrame.getjButtonE2E().setEnabled(flag);
    }

    //==========================================================================
    private void addListeners() {

        try {

            //------------------------------------------------------------------
            mainFrame.getjMenuItemUserConfiguration().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ControllerUserConfiguration cc = new ControllerUserConfiguration();
                    cc.setupInterface();
                    cc.setVisible(true);
                }
            });

            //------------------------------------------------------------------
            mainFrame.getjButtonSniffer().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    mainFrame.getjButtonSniffer().setEnabled(false);
                    cs.setVisible(true);
                    mainFrame.getjButtonSniffer().setEnabled(true);

                }
            });

            //------------------------------------------------------------------
            mainFrame.getjButtonFirewall().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*mainFrame.getjButtonFirewall().setEnabled(false);
                    cf.setupInterface();
                    cf.setVisible(true);
                    mainFrame.getjButtonFirewall().setEnabled(true);*/                    
                    new ControllerFirewallSam5().openBrowser();
                    
                }
            });

            //------------------------------------------------------------------
            mainFrame.getjButtonScanner().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getjButtonScanner().setEnabled(false);
                    controllerPortScanner.setVisible(true);
                    mainFrame.getjButtonScanner().setEnabled(true);
                }
            });

            //------------------------------------------------------------------
            mainFrame.getjButtonE2E().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getjButtonE2E().setEnabled(false);
                    e2e.setVisible(true);
                    mainFrame.getjButtonE2E().setEnabled(true);
                }
            });

            //------------------------------------------------------------------
            mainFrame.getjButtonShaper().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.getjButtonShaper().setEnabled(false);
                    shaper.setVisible(true);
                    mainFrame.getjButtonShaper().setEnabled(true);
                }
            });

            //------------------------------------------------------------------
            mainFrame.getjButtonFilter().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    /*mainFrame.getjButtonScanner().setEnabled(false);
                     controllerFilter.setVisible(true);
                     mainFrame.getjButtonScanner().setEnabled(true);*/

                    cfg.setVisible(true);

                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end addListeners

    //==========================================================================
    /**
     * return the instance of this class.
     *
     * @return ControllerMainFrame
     */
    public static ControllerMainFrame getInstance() {
        return NewControllerMainFrameHolder.INSTANCE;
    } // end MainFrame

    //==========================================================================
    /**
     * singleton, this method is only for keep one instance
     */
    private static class NewControllerMainFrameHolder {

        private static final ControllerMainFrame INSTANCE = new ControllerMainFrame();
    } // end NewMainFrameHolder

    public MainFrame getMainFrame() {
        return this.mainFrame;
    }

    //==========================================================================
    @Override
    public void setupInterface() {

        GlobalConfiguration gc = null;

        try {

            gc = GlobalConfiguration.getInstance();

            if (gc.getIsActiveEndToEnd() == 0) {
                mainFrame.getjButtonE2E().setEnabled(false);
            }

            if (gc.getIsActiveFilter() == 0) {
                mainFrame.getjButtonFilter().setEnabled(false);
            }

            if (gc.getIsActiveFirewall() == 0) {
                mainFrame.getjButtonFirewall().setEnabled(false);
            }

            if (gc.getIsActivePortScanner() == 0) {
                mainFrame.getjButtonScanner().setEnabled(false);
            }

            if (gc.getIsActiveShaper() == 0) {
                mainFrame.getjButtonShaper().setEnabled(false);
            }

            if (gc.getIsActiveSniffer() == 0) {
                mainFrame.getjButtonSniffer().setEnabled(false);
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("unexpected error", e);
        }
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        mainFrame.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class
