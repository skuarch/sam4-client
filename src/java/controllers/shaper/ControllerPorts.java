package controllers.shaper;

import controllers.global.ControllerNotifications;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import model.beans.CurrentUser;
import model.net.Linker;
import model.util.HashMapUtilities;
import views.tables.GenericTable;
import model.util.ViewUtilities;

/**
 *
 * @author skuarch
 */
public class ControllerPorts {
    
    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    private String collector = null;
    private GenericTable genericTable = null;

    //==========================================================================
    public ControllerPorts(String collector) {
        this.collector = collector;
        genericTable = new GenericTable();
    } // end ControllerTargets

    //==========================================================================
    public void setupInterface() {

        genericTable.getjProgressBar().setIndeterminate(true);
        genericTable.setName("manage ports");
        setEnableButtons(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    genericTable.getjButton1().setVisible(false);
                    addListeners();
                    reloadTable();

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    genericTable.getjProgressBar().setIndeterminate(false);
                    setEnableButtons(true);
                     if (CurrentUser.getInstance().getLevel() == 0) {
                        genericTable.getjButtonAdd().setEnabled(false);
                        genericTable.getjButtonRemove().setEnabled(false);
                        genericTable.getjButtonEdit().setEnabled(false);
                        genericTable.getjButton1().setEnabled(false);
                    }
                }
            }
        }).start();

    } // end setupInterface

    //==========================================================================    
    private void addListeners() {

        try {

            //------------------------------------------------------------------
            genericTable.getjButtonRemove().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove();
                }
            });

            //------------------------------------------------------------------
            genericTable.getjButtonAdd().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    add();
                }
            });

            genericTable.getjButtonEdit().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    edit();
                }
            });
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end addListeners

    //==========================================================================    
    private void add() {
        ControllerAddPort cap = new ControllerAddPort(collector, this);
        cap.setupInterface();
        cap.setVisible(true);
    }

    //==========================================================================    
    private void edit() {

        ControllerEditPort cep = null;
        int row = 0;
        String id = null;

        try {

            row = genericTable.getjTable().getSelectedRow();

            if (row < 0) {
                NOTIFICATIONS.information("Please select a port", true);
                return;
            }

            id = (String) genericTable.getjTable().getValueAt(row, 0);

            cep = new ControllerEditPort(collector, id, this);
            cep.setupInterface();
            cep.setVisible(true);

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end edit

    //==========================================================================    
    private void remove() {

        genericTable.getjProgressBar().setIndeterminate(true);
        setEnableButtons(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int row = 0;
                String id = null;
                HashMap hm = null;
                String message = null;

                try {

                    row = genericTable.getjTable().getSelectedRow();

                    if (row < 0) {
                        NOTIFICATIONS.information("Please select a port", true);
                        return;
                    }

                    id = (String) genericTable.getjTable().getValueAt(row, 0);
                    hm = HashMapUtilities.getHashMapShaper();
                    hm.put("collector", collector);
                    hm.put("request", "delete ports");
                    hm.put("id", id);

                    message = (String) new Linker().sendReceiveObject(hm);

                    //reload table Targets                    
                    reloadTable();

                    NOTIFICATIONS.information(message, true);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    genericTable.getjProgressBar().setIndeterminate(false);
                    setEnableButtons(true);
                }
            }
        }).start();

    } // end remove

    //==========================================================================
    public void reloadTable() {

        HashMap hm = null;
        ArrayList arrayList = null;

        try {

            hm = HashMapUtilities.getHashMapShaper();
            hm.put("collector", collector);
            hm.put("request", "get initial data ports");
            arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

            ViewUtilities.setTableModel((Object[][]) arrayList.get(1), (String[]) arrayList.get(0), genericTable.getjTable());

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end reloadTable

    //==========================================================================    
    public GenericTable getPanel() throws Exception {

        return genericTable;

    } // end getPanel

    //==========================================================================
    private void setEnableButtons(boolean flag) {

        genericTable.getjButtonAdd().setEnabled(flag);
        genericTable.getjButtonEdit().setEnabled(flag);
        genericTable.getjButtonRemove().setEnabled(flag);

    } // end enableButtons
    
} // end class
