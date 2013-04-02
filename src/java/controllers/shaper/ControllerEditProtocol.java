package controllers.shaper;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import model.net.Linker;
import model.util.HashMapUtilities;
import views.dialogs.Protocol;

/**
 *
 * @author skuarch
 */
public class ControllerEditProtocol extends Controller {

    private Protocol protocol = null;
    private HashMap hm = null;
    private String collector = null;
    private String id = null;
    private ControllerProtocols controllerProtocols = null;

    //==========================================================================
    public ControllerEditProtocol(String collector, String id, ControllerProtocols controllerProtocols) {
        protocol = new Protocol(null, true);
        hm = HashMapUtilities.getHashMapShaper();
        this.collector = collector;
        this.id = id;
        this.controllerProtocols = controllerProtocols;
    } // end ControllerEditFilter

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

                    protocol.getjButton1().setText("close");
                    protocol.getjButton1().setIcon(new ImageIcon(getClass().getResource("/views/images/shutdown-mini.png")));
                    protocol.getjButton2().setText("edit");
                    protocol.getjButton2().setIcon(new ImageIcon(getClass().getResource("/views/images/edit.png")));

                    addListeners();

                    hm.put("id", id);
                    hm.put("collector", collector);
                    hm.put("request", "get data protocols");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    if (arrayList == null) {
                        return;
                    }

                    protocol.getjTextFieldName().setText(arrayList.get(0).toString());
                    protocol.getjTextFieldNumber().setText(arrayList.get(1).toString());

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

        try {

            protocol.getjButton1().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    protocol.setVisible(false);
                }
            });

            protocol.getjButton2().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    edit();
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    }

    //==========================================================================
    private void edit() {

        protocol.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList data = new ArrayList();
                String message = null;

                try {

                    if (validate()) {

                        data.add(id);
                        data.add(protocol.getjTextFieldName().getText());                        
                        data.add(protocol.getjTextFieldNumber().getText());

                    }

                    hm.put("id", id);
                    hm.put("collector", collector);
                    hm.put("request", "edit protocols");
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

    } // end edit

    //==========================================================================    
    public void setEnabledComponents(boolean flag) {
        protocol.getjButton2().setEnabled(flag);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        protocol.setVisible(flag);
    } // end setVisible

    //==========================================================================
    private boolean validate() throws Exception {

        boolean flag = false;
        String chainName = null;

        try {

            chainName = protocol.getjTextFieldName().getText();

            if (chainName == null || chainName.length() < 1) {
                NOTIFICATIONS.error("the name of protocol is empty", new Exception());
                return false;
            }

            flag = true;

        } catch (Exception e) {
            throw e;
        }

        return flag;

    } // end validate

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end destroyCurrentThread
    
} // edn class
