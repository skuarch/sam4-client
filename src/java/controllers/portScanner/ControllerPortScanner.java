package controllers.portScanner;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import model.common.ModelCollectors;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.ViewUtilities;
import views.frames.PortScanner;

/**
 *
 * @author skuarch
 */
public class ControllerPortScanner extends Controller {

    private PortScanner portScanner = null;
    private Thread sw = null;

    //==========================================================================
    private ControllerPortScanner() {
        portScanner = new PortScanner();
    } // end ControllerPortScanner

    //==========================================================================
    public static ControllerPortScanner getInstance() {
        return ControllerPortScannerHolder.INSTANCE;
    } // end ControllerPortScanner    

    //==========================================================================
    private static class ControllerPortScannerHolder {

        private static final ControllerPortScanner INSTANCE = new ControllerPortScanner();
    } // end ControllerPortScannerHolder

    //==========================================================================
    @Override
    public void setupInterface() {
        try {

            ViewUtilities.fillJComboBox(portScanner.getjComboBoxCollectors(), new ModelCollectors().getActivesCollectorsStringArray());
            portScanner.getjButtonStart().setEnabled(true);

            addListeners();

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
    }

    //==========================================================================
    private void addListeners() {

        portScanner.getjButtonStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        portScanner.getjButtonStop().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

    } // end addListeners

    //==========================================================================
    private void start() {

        portScanner.getjProgressBar().setIndeterminate(true);
        portScanner.getjButtonStart().setEnabled(false);
        portScanner.getjButtonStop().setEnabled(true);
        portScanner.getjTextFieldIP().setEnabled(false);

        sw = new Thread(new Runnable() {
            @Override
            public void run() {

                HashMap hm = null;
                ArrayList arrayList = null;
                String collector = null;
                String target = null;

                try {

                    collector = portScanner.getjComboBoxCollectors().getModel().getSelectedItem().toString();

                    if (collector.contains("please") || collector.length() < 1 || collector.equalsIgnoreCase("")) {
                        NOTIFICATIONS.error("please select a collector", new Exception());
                        return;
                    }

                    target = portScanner.getjTextFieldIP().getText();

                    if (target.length() < 1 || target.equalsIgnoreCase("")) {
                        NOTIFICATIONS.error("the target is required", new Exception());
                        return;
                    }

                    hm = HashMapUtilities.getHashMapPortScanner();
                    hm.put("collector", collector);
                    hm.put("target", target);
                    hm.put("request", "scan all ports");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    if (arrayList == null) {
                        return;
                    }

                    for (Object object : arrayList) {
                        portScanner.getjTextArea().append(object + "\n");
                    }


                } catch (Exception ex) {
                    NOTIFICATIONS.error("Unexpected error", ex);
                } finally {
                    portScanner.getjProgressBar().setIndeterminate(false);
                    portScanner.getjButtonStart().setEnabled(true);
                    portScanner.getjButtonStop().setEnabled(false);
                    portScanner.getjTextFieldIP().setEnabled(true);
                }

            }
        });

        sw.start();

    } // end start

    //==========================================================================
    private void stop() {

        if (sw != null) {
            sw.interrupt();
        }

        sw = null;
        portScanner.getjTextArea().append("the scanner was stopped");
        portScanner.getjProgressBar().setIndeterminate(false);
        portScanner.getjButtonStart().setEnabled(true);
        portScanner.getjButtonStop().setEnabled(false);

    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        portScanner.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class
