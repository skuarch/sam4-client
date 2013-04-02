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
import views.dialogs.Protocol;

/**
 *
 * @author skuarch
 */
public class ControllerAddProtocol extends Controller {
    
    private ControllerProtocols controllerProtocols = null;
    private Protocol protocol = null;
    private String collector = null;
    private HashMap hm = null;

    //==========================================================================
    public ControllerAddProtocol(String collector, ControllerProtocols controllerProtocols) {
        protocol = new Protocol(null, true);
        this.controllerProtocols = controllerProtocols;
        this.collector = collector;
        hm = HashMapUtilities.getHashMapShaper();
    }

    //==========================================================================
    @Override
    public void setupInterface() {

        protocol.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList arrayList = null;

                try {

                    //basic configuration 
                    protocol.getjButton1().setText("close");
                    protocol.getjButton1().setIcon(new ImageIcon(getClass().getResource("/views/images/shutdown-mini.png")));
                    protocol.getjButton2().setText("add");
                    protocol.getjButton2().setIcon(new ImageIcon(getClass().getResource("/views/images/add.gif")));

                    addListeners();

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    protocol.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }
            }
        }).start();

    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        protocol.getjButton1().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                protocol.setVisible(false);
            }
        });

        protocol.getjButton2().addActionListener(new ActionListener() {
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

        protocol.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                String message = null;
                ArrayList data = new ArrayList();

                try {

                    data.add("null");
                    data.add(protocol.getjTextFieldName().getText());
                    data.add(protocol.getjTextFieldNumber().getText());

                    hm.put("collector", collector);
                    hm.put("request", "save protocols");
                    hm.put("data", data);

                    message = (String) new Linker().sendReceiveObject(hm);

                    controllerProtocols.reloadTable();
                    NOTIFICATIONS.information(message, true);

                    setVisible(false);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    protocol.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }
            }
        }).start();

    } // end add

    //==========================================================================    
    public void setEnabledComponents(boolean flag) {
        protocol.getjButton2().setEnabled(flag);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        protocol.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end ControllerAddProtocol