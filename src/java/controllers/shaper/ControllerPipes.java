package controllers.shaper;

import controllers.global.ControllerNotifications;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import model.beans.CurrentUser;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.ViewUtilities;
import views.tables.GenericTable;

/**
 *
 * @author skuarch
 */
public class ControllerPipes {

    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    private String collector = null;
    private GenericTable genericTable = null;

    //==========================================================================
    public ControllerPipes(String collector) {
        this.collector = collector;
        genericTable = new GenericTable();
    } // end ControllerPipes

    //==========================================================================
    public void setupInterface() {

        genericTable.getjProgressBar().setIndeterminate(true);
        genericTable.setName("manage pipes");
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
        ControllerAddPipe cap = new ControllerAddPipe(collector, this);
        cap.setupInterface();
        cap.setVisible(true);
    }

    //==========================================================================    
    private void edit() {

        ControllerEditPipe cep = null;
        int row = 0;
        String id = null;

        try {

            row = genericTable.getjTable().getSelectedRow();

            if (row < 0) {
                NOTIFICATIONS.information("Please select a pipe", true);
                return;
            }

            id = (String) genericTable.getjTable().getValueAt(row, 0);

            cep = new ControllerEditPipe(collector, id, this);
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
                        NOTIFICATIONS.information("Please select a pipe", true);
                        return;
                    }

                    id = (String) genericTable.getjTable().getValueAt(row, 0);
                    hm = HashMapUtilities.getHashMapShaper();
                    hm.put("collector", collector);
                    hm.put("request", "delete pipes");
                    hm.put("id", id);

                    message = (String) new Linker().sendReceiveObject(hm);

                    //reload table pipes                    
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
            hm.put("request", "get initial data pipes");
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
