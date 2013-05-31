package controllers.firewall;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.beans.CurrentUser;
import model.common.ModelCollectors;
import model.net.Linker;
import model.util.ViewUtilities;
import views.frames.Firewall;

/**
 *
 * @author skuarch
 */
public class ControllerFirewall extends Controller {

    private Firewall firewall = null;
    private DefaultTableModel dtm1 = null;
    private DefaultTableModel dtm2 = null;

    //==========================================================================
    private ControllerFirewall() {
    }

    //==========================================================================
    public static ControllerFirewall getInstance() {
        return ControllerSnifferHolder.INSTANCE;
    }

    //==========================================================================
    private static class ControllerSnifferHolder {

        private static final ControllerFirewall INSTANCE = new ControllerFirewall();
    }

    //==========================================================================
    @Override
    public void setupInterface() {

        HashMap hashMap = new HashMap();

        try {

            firewall = new Firewall();

            hashMap.put("request", "get status");
            hashMap.put("type", "firewall");

            firewall.getjComboBoxCollectors().removeAllItems();
            String[] selectSomething = {"please select a collector"};
            ViewUtilities.fillJComboBox(firewall.getjComboBoxCollectors(), selectSomething);
            ViewUtilities.fillJComboBox(firewall.getjComboBoxCollectors(), new ModelCollectors().getActivesCollectorsStringArray());

            addListeners();           
            

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
    }

    //==========================================================================
    private void addListeners() {

        //----------------------------------------------------------------------
        firewall.getjButtonAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerAddRule car = new ControllerAddRule(firewall.getjComboBoxCollectors().getModel().getSelectedItem().toString());
                car.setupInterface();
                car.setVisible(true);
            }
        });

        //----------------------------------------------------------------------
        firewall.getjComboBoxCollectors().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboChange();
            }
        });

        //----------------------------------------------------------------------
        firewall.getjCheckBoxEnabled().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setStatus();
            }
        });

        //----------------------------------------------------------------------
        firewall.getjButtonRemove().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeRule();
            }
        });
    } // end addListeners

    //==========================================================================
    private void removeRule() {

        enabledComponents(false);
        firewall.getjProgressBar().setIndeterminate(true);

        new Thread(new Runnable() {
            @Override
            public void run() {

                HashMap hashMap = new HashMap();
                String collector = null;
                int row = 0;
                String to = null;
                String action = null;
                String from = null;
                String messageReturn = null;

                try {

                    collector = collector = firewall.getjComboBoxCollectors().getModel().getSelectedItem() + "";

                    if (collector.contains("select")) {
                        return;
                    }

                    row = firewall.getjTableRules().getSelectedRow();

                    if (row < 0) {
                        NOTIFICATIONS.information("Please select a rule", true);
                        return;
                    }

                    to = firewall.getjTableRules().getValueAt(row, 0).toString();
                    action = firewall.getjTableRules().getValueAt(row, 1).toString();
                    from = firewall.getjTableRules().getValueAt(row, 2).toString();

                    hashMap.put("request", "delete rule");
                    hashMap.put("type", "firewall");
                    hashMap.put("collector", collector);
                    hashMap.put("to", to);
                    hashMap.put("action", action);
                    hashMap.put("from", from);

                    messageReturn = (String) new Linker().sendReceiveObject(hashMap);

                    NOTIFICATIONS.information(messageReturn, true);
                    reloadTables();

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    firewall.getjProgressBar().setIndeterminate(false);
                    enabledComponents(true);
                }
            }
        }).start();
    }

    //==========================================================================
    public void reloadTables() {

        firewall.getjProgressBar().setIndeterminate(true);
        enabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String collector = null;

                        try {

                            collector = firewall.getjComboBoxCollectors().getModel().getSelectedItem() + "";

                            if (collector.contains("select")) {
                                return;
                            }

                            clearTables();
                            fillTables(requestInitialData(collector));

                        } catch (Exception e) {
                            NOTIFICATIONS.error("Unexpected error", e);
                        } finally {
                            firewall.getjProgressBar().setIndeterminate(false);
                            enabledComponents(true);
                        }
                    }
                });
            }
        }).start();

    } // end reloadTables

    //==========================================================================
    private void setStatus() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        HashMap hashMap = new HashMap();

                        try {

                            if (!firewall.getjCheckBoxEnabled().isSelected()) {
                                enabledComponents(false);
                                clearTables();
                            } else {
                                enabledComponents(true);
                                reloadTables();
                            }

                            hashMap.put("request", "set status");
                            hashMap.put("status", firewall.getjCheckBoxEnabled().isSelected() + "");
                            hashMap.put("type", "firewall");
                            hashMap.put("collector", firewall.getjComboBoxCollectors().getModel().getSelectedItem() + "");

                            new Linker().sendReceiveObject(hashMap);

                        } catch (Exception e) {
                            NOTIFICATIONS.error("Unexpected error", e);
                        }
                    }
                });
            }
        }).start();

    } // end setStatus

    //==========================================================================
    private void comboChange() {

        firewall.getjCheckBoxEnabled().setEnabled(true);
        enabledComponents(false);
        firewall.getjProgressBar().setIndeterminate(true);

        new Thread(new Runnable() {
            @Override
            public void run() {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        String collector = null;
                        ArrayList arrayList = null;

                        try {

                            //delete tables, for show other new tables
                            clearTables();

                            //uncheck the option enable
                            firewall.getjCheckBoxEnabled().setSelected(false);

                            //get the collector 
                            collector = firewall.getjComboBoxCollectors().getModel().getSelectedItem() + "";

                            if (collector == null || collector.length() < 1 || collector.contains("select")) {
                                enabledComponents(false);
                                return;
                            }

                            //requesting data
                            arrayList = requestInitialData(collector);

                            if (arrayList == null || arrayList.size() < 4) {
                                return;
                            }

                            //enable o disable components
                            if (arrayList.get(0).equals(false)) {
                                enabledComponents(false);
                                firewall.getjCheckBoxEnabled().setSelected(false);
                                return;
                            } else {
                                enabledComponents(true);
                                firewall.getjCheckBoxEnabled().setSelected(true);

                            }

                            fillTables(arrayList);

                        } catch (Exception e) {
                            NOTIFICATIONS.error("Unexpected error", e);
                            enabledComponents(false);
                        } finally {
                            firewall.getjProgressBar().setIndeterminate(false);
                            if (CurrentUser.getInstance().getLevel() == 0) {
                                firewall.getjButtonAdd().setEnabled(false);
                                firewall.getjButtonRemove().setEnabled(false);
                                firewall.getjCheckBoxEnabled().setEnabled(false);
                                
                            }
                        }
                    }
                });
            }
        }).start();

    } // end comboChange

    //==========================================================================
    private ArrayList requestInitialData(String collector) {

        HashMap hashMap = null;
        ArrayList arrayList = null;

        try {

            hashMap = new HashMap();
            hashMap.put("type", "firewall");
            hashMap.put("request", "get initial data");
            hashMap.put("collector", collector);

            //requesting data
            arrayList = (ArrayList) new Linker().sendReceiveObject(hashMap);

            if (arrayList == null || arrayList.size() < 4) {
                return null;
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

        return arrayList;

    } // end requestInitialData

    //==========================================================================
    private void fillTables(ArrayList arrayList) {

        Object[][] data = null;

        try {

            //fill table rules
            data = (Object[][]) arrayList.get(2);

            if (data.length < 1) {
                firewall.getjButtonRemove().setEnabled(false);
            } else {
                firewall.getjButtonRemove().setEnabled(true);
            }

            dtm1 = createModel((String[]) arrayList.get(1), data);
            firewall.getjTableRules().setModel(dtm1);
            firewall.getjTableRules().updateUI();
            firewall.getjScrollPane1().updateUI();

            //fill table ports and show
            dtm2 = createModel((String[]) arrayList.get(3), (Object[][]) arrayList.get(4));
            firewall.getjTablePorts().setModel(dtm2);
            firewall.getjTablePorts().updateUI();
            firewall.getjScrollPane2().updateUI();

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
    } // end fillTables

    //==========================================================================
    private void clearTables() {

        firewall.getjTableRules().setModel(new DefaultTableModel());
        firewall.getjTableRules().updateUI();
        firewall.getjScrollPane1().updateUI();

        firewall.getjTablePorts().setModel(new DefaultTableModel());
        firewall.getjTablePorts().updateUI();
        firewall.getjScrollPane2().updateUI();

    }

    //==========================================================================
    private DefaultTableModel createModel(String[] columnNames, Object[][] data) {

        DefaultTableModel model = null;

        try {

            model = new DefaultTableModel(data, columnNames) {
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }
            }; //  end getColumnClass

        } catch (Exception e) {
            NOTIFICATIONS.error("Error creating model", e);
        }

        return model;

    }

    //==========================================================================
    private void enabledComponents(boolean flag) {

        firewall.getjTableRules().setEnabled(flag);
        firewall.getjTablePorts().setEnabled(flag);
        firewall.getjButtonAdd().setEnabled(flag);
        firewall.getjButtonRemove().setEnabled(flag);

    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        firewall.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class
