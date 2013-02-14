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
public class ControllerCategories {

    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    private GenericTable genericTable = null;
    private String collector = null;
    private JProgressBar jProgressBar = null;
    private HashMap hm = null;

    //==========================================================================
    public ControllerCategories(String collector, JProgressBar jProgressBar) {
        genericTable = new GenericTable();
        this.collector = collector;
        this.jProgressBar = jProgressBar;
        hm = HashMapUtilities.getHashMapFilter();
    } // end ControllerPolicies

    //==========================================================================
    public void setupInterface() {

        genericTable.setName("categories");
        jProgressBar.setIndeterminate(true);
        setEnableComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {

                    genericTable.getjProgressBar().setVisible(false);
                    genericTable.getjButton1().setText("enabled");
                    genericTable.getjButtonRemove().setText("disabled");

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
                    disabled();
                }
            });

            genericTable.getjButton1().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    enabled();
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end addListeners

    //==========================================================================
    private void add() {

        ControllerChooseOptionCategory ccoc = new ControllerChooseOptionCategory("add", collector, this, null);
        ccoc.setupInterface();
        ccoc.setVisible(true);

    } // end add

    //==========================================================================
    private void edit() {

        int row = 0;
        String categorieName = null;
        String status = null;

        try {

            row = genericTable.getjTable().getSelectedRow();

            if (row < 0) {
                NOTIFICATIONS.information("Please select a categorie", true);
                return;
            }

            categorieName = (String) genericTable.getjTable().getValueAt(row, 1);
            status = (String) genericTable.getjTable().getValueAt(row, 2);

            if (status.equalsIgnoreCase("  ---  ")) {
                return;
            }

            ControllerChooseOptionCategory ccoc = new ControllerChooseOptionCategory("edit", collector, this, categorieName);
            ccoc.setupInterface();
            ccoc.setVisible(true);

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
    } // end edit

    //==========================================================================
    private void disabled() {

        jProgressBar.setIndeterminate(true);
        setEnableComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                int row = 0;
                String id = null;
                String message = null;
                String status = null;

                try {

                    row = genericTable.getjTable().getSelectedRow();

                    if (row < 0) {
                        NOTIFICATIONS.information("Please select a categories", true);
                        return null;
                    }

                    id = (String) genericTable.getjTable().getValueAt(row, 1);
                    status = (String) genericTable.getjTable().getValueAt(row, 2);

                    if (status.equalsIgnoreCase("  ---  ")) {
                        return null;
                    }

                    if (status.equalsIgnoreCase("Inactive")) {
                        return null;
                    }

                    hm = HashMapUtilities.getHashMapFilter();
                    hm.put("collector", collector);
                    hm.put("request", "disabled categories");
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

    } // end disabled

    //==========================================================================
    private void enabled() {

        jProgressBar.setIndeterminate(true);
        setEnableComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                int row = 0;
                String id = null;
                String message = null;
                String status = null;

                try {

                    row = genericTable.getjTable().getSelectedRow();

                    if (row < 0) {
                        NOTIFICATIONS.information("Please select a categories", true);
                        return null;
                    }

                    id = (String) genericTable.getjTable().getValueAt(row, 1);
                    status = (String) genericTable.getjTable().getValueAt(row, 2);

                    if (status.equalsIgnoreCase("  ---  ")) {
                        return null;
                    }

                    if (status.equalsIgnoreCase("Active")) {
                        return null;
                    }

                    hm = HashMapUtilities.getHashMapFilter();
                    hm.put("collector", collector);
                    hm.put("request", "enabled categories");
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

    } // end enabled

    //==========================================================================
    public void reloadTable() {

        ArrayList arrayList = null;

        try {

            hm.put("collector", collector);
            hm.put("request", "get categories");
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