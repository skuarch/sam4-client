package controllers.shaper;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.Thrower;
import views.dialogs.Port;

/**
 *
 * @author skuarch
 */
public class ControllerAddPort extends Controller {

    private ControllerPorts controllerPorts = null;
    private Port port = null;
    private String collector = null;
    private HashMap hm = null;

    //==========================================================================
    public ControllerAddPort(String collector, ControllerPorts controllerPorts) {
        port = new Port(null, true);
        this.controllerPorts = controllerPorts;
        this.collector = collector;
        hm = HashMapUtilities.getHashMapShaper();
    }

    //==========================================================================
    @Override
    public void setupInterface() {

        port.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList arrayList = null;

                try {

                    //basic configuration 
                    port.getjButton1().setText("close");
                    port.getjButton1().setIcon(new ImageIcon(getClass().getResource("/views/images/shutdown-mini.png")));
                    port.getjButton2().setText("add");
                    port.getjButton2().setIcon(new ImageIcon(getClass().getResource("/views/images/add.gif")));

                    addListeners();

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    port.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }
            }
        }).start();

    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        port.getjButton1().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                port.setVisible(false);
            }
        });

        port.getjButton2().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    add();
                } catch (Exception ex) {
                    new Thrower().exception(ex);
                }

            }
        });
    }

    //==========================================================================
    private void add() {

        port.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                String message = null;
                ArrayList data = new ArrayList();

                try {

                    data.add("null");
                    data.add(port.getjTextFieldName().getText());
                    data.add(port.getjTextFieldDescription().getText());
                    data.add(port.getjTextFieldNumber().getText());

                    hm.put("collector", collector);
                    hm.put("request", "save ports");
                    hm.put("data", data);

                    message = (String) new Linker().sendReceiveObject(hm);

                    controllerPorts.reloadTable();
                    NOTIFICATIONS.information(message, true);

                    setVisible(false);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    port.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }
            }
        }).start();

    } // end add

    //==========================================================================    
    public void setEnabledComponents(boolean flag) {
        port.getjButton2().setEnabled(flag);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        port.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end ControllerAddPort