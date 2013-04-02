package controllers.filter;

import controllers.global.ControllerNotifications;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.ViewUtilities;
import views.tables.GenericTable;

/**
 *
 * @author skuarch
 */
public class ControllerPolicies {

    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    private GenericTable genericTable = null;
    private String collector = null;
    private JProgressBar jProgressBar = null;
    private HashMap hm = null;

    //==========================================================================
    public ControllerPolicies(String collector, JProgressBar jProgressBar) {
        genericTable = new GenericTable();
        this.collector = collector;
        this.jProgressBar = jProgressBar;
        hm = HashMapUtilities.getHashMapFilter();
    } // end ControllerPolicies

    //==========================================================================
    public void setupInterface() {

        genericTable.setName("Policies");
        jProgressBar.setIndeterminate(true);
        setEnableComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {

                    genericTable.getjButton1().setVisible(false);                    
                    genericTable.getjProgressBar().setVisible(false);

                    addListeners();

                    reloadTable();

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    jProgressBar.setIndeterminate(false);
                    setEnableComponents(true);
                }

                return null;
            }
        }.execute();

    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        try {

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

            genericTable.getjButtonRemove().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove();
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end addListeners

    //==========================================================================
    private void add() {
        ControllerAddPolicie controllerAddPolicie = new ControllerAddPolicie(collector, this);
        controllerAddPolicie.setupInterface();
        controllerAddPolicie.setVisible(true);
    } // end add

    //==========================================================================
    private void edit() {

        ControllerEditPolicie controllerEditPolicie = null;
        int row = 0;
        String policyName = null;

        try {

            row = genericTable.getjTable().getSelectedRow();

            if (row < 0) {
                NOTIFICATIONS.information("Please select a policy", true);
                return;
            }

            policyName = (String) genericTable.getjTable().getValueAt(row, 1);

            controllerEditPolicie = new ControllerEditPolicie(collector, this, policyName);
            controllerEditPolicie.setupInterface();
            controllerEditPolicie.setVisible(true);

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end edit

    //==========================================================================
    private void remove() {

        jProgressBar.setIndeterminate(true);
        setEnableComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                int row = 0;
                String id = null;
                String message = null;

                try {

                    row = genericTable.getjTable().getSelectedRow();

                    if (row < 0) {
                        NOTIFICATIONS.information("Please select a policy", true);
                        return null;
                    }

                    id = (String) genericTable.getjTable().getValueAt(row, 1);
                    hm = HashMapUtilities.getHashMapFilter();
                    hm.put("collector", collector);
                    hm.put("request", "delete policies");
                    hm.put("id", id);

                    message = (String) new Linker().sendReceiveObject(hm);
                    
                    reloadTable();

                    NOTIFICATIONS.information(message, true);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    jProgressBar.setIndeterminate(false);
                    setEnableComponents(true);
                }

                return null;
            }
        }.execute();

    } // end remove

    //==========================================================================
    public void reloadTable() {

        ArrayList arrayList = null;

        try {

            hm.put("collector", collector);
            hm.put("request", "get policies");
            arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

            ViewUtilities.setTableModel((Object[][]) arrayList.get(1), (String[]) arrayList.get(0), genericTable.getjTable());

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end reloadTable    

    //==========================================================================
    private void setEnableComponents(boolean flag) {

        genericTable.getjButtonAdd().setEnabled(flag);
        genericTable.getjButtonEdit().setEnabled(flag);
        genericTable.getjButtonRemove().setEnabled(flag);

    } // end setEnableComponents

    //==========================================================================    
    public GenericTable getPanel() throws Exception {
        return genericTable;
    } // end getPanel
    
} // end class