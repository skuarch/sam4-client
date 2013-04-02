package views.tables;

import controllers.global.ControllerNotifications;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.common.Exporter;
import model.net.Linker;
import views.dialogs.Chooser;
import views.dialogs.Detail;
import views.dialogs.LoadingDialog;
import views.panels.Footer;
import views.panels.HeaderPagination;
import views.panels.LoadingPanel;
import views.panels.SnifferPanel;

/**
 *
 * @author skuarch
 */
public class TablePagination extends SnifferPanel {

    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    private Thread execute = null;
    private JPanel loadingPanel = null;
    private JPanel panelFooter = null;
    private JPanel panelPagination = null;
    private int limit1 = 1;
    private int limit2 = 100;
    private JScrollPane scrollPane = null;
    private int totalResult = 0;
    private ArrayList arrayList = null;
    private JTable table = null;
    private DefaultTableModel model = null;
    private boolean finishPagination = false;
    private HashMap hm = null;

    //==========================================================================
    public TablePagination(HashMap hm) {
        this.hm = hm;
        loadingPanel = new LoadingPanel();
        table = new JTable();

        onLoad();
    } // end TablePagination

    //==========================================================================
    private void onLoad() {
        setname();
        setLimits();
        setLayout(new BorderLayout());
        hm.put("request", hm.get("view").toString());
        hm.put("isTable", true);
        execute();
    }

    //==========================================================================
    private void setLimits() {
        if (hm.get("limits").equals("not applicable")) {
            hm.put("limits", limit1 + "," + limit2);
        } else {
            limit1 = Integer.parseInt(hm.get("limits").toString().split(",")[0]);
            limit2 = Integer.parseInt(hm.get("limits").toString().split(",")[1]);
        }
    }

    //==========================================================================
    private void execute() {

        remover();
        setLoadingPanel();

        execute = new Thread(new Runnable() {
            public void run() {

                try {

                    requestData();
                    totalResult = (Integer) arrayList.get(0);
                    
                    createTable();
                    sortTable();
                    createPanelFooter();
                    createPanelPagination();
                    remover();
                    add(scrollPane, BorderLayout.CENTER);
                    add(panelFooter, BorderLayout.SOUTH);
                    add(panelPagination, BorderLayout.NORTH);

                } catch (Exception e) {
                    NOTIFICATIONS.error("error creating panel", e);
                } finally {
                    updateUI();
                }

            }
        });
        execute.start();
        execute.setName("TablePagination.execute");
    }

    //==========================================================================
    @Override
    public void updateUI() {

        new Thread(new Runnable() {
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        TablePagination.super.updateUI();
                    }
                });
            }
        }).start();


    } // end updateUI

    //==========================================================================
    private void createPanelPagination() {

        HeaderPagination hp = null;

        try {

            hp = new HeaderPagination();
            panelPagination = hp.getHeaderBarChart();

            if (finishPagination) {
                limit2 = limit1 - 1 + 100;
                finishPagination = false;
            }

            if (totalResult <= 100) {
                hp.enableAllComponents(false);
            }

            if (totalResult > 99) {
                hp.enableNextButton(true);
            }

            if (totalResult <= 1) {
                hp.enableAllComponents(false);
            }

            if (limit1 <= 1) {
                limit1 = 1;
                limit2 = 100;
                hp.enableBackButton(false);
            }

            if (limit2 > totalResult) {
                //limit2 = totalResult;
                finishPagination = true;
                hp.enableNextButton(false);
            }

            hp.setLabelText(limit1 + " to " + limit2 + " of " + totalResult);

            //next
            hp.addActionListenerNextButton(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    limit1 += 100;
                    limit2 += 100;
                    hm.put("limits", limit1 + "," + limit2);
                    execute();
                }
            });

            //back
            hp.addActionListenerBackButton(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    limit1 -= 100;
                    limit2 -= 100;
                    hm.put("limits", limit1 + "," + limit2);
                    execute();
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("error creating header", e);
        }

    } // end createPanelPagination    

    //=========================================================================    
    private void sortTable() {

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {

                    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
                    table.setRowSorter(sorter);

                } catch (Exception e) {
                    NOTIFICATIONS.error("error sorting table", e);
                }
            }
        });

        t.start();

    } // end sortTable

    //==========================================================================
    private void remover() {
        try {
            if (panelPagination != null) {
                remove(panelPagination);
            }

            if (panelFooter != null) {
                remove(panelFooter);
            }

            if (scrollPane != null) {
                remove(scrollPane);
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("error removing panels", e);
        } finally {
            removeAll();
        }
    }

    //==========================================================================
    private void createPanelFooter() {

        Footer footer = null;

        try {

            footer = new Footer();
            panelFooter = footer.getFooterTable();

            //excel
            footer.addActionListenerExportExcel(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {

                        String path = null;
                        Chooser chooser = new Chooser();
                        path = chooser.getPath();

                        if (path == null) {
                            return;
                        } else {

                            if (hm.get("log") != null) {
                                exportExcelFilter(path);
                            } else {
                                exportExcel(path);
                            }

                        }

                    } catch (Exception ex) {
                        NOTIFICATIONS.error("Unexpected error", ex);
                    }
                }
            });


            //detail
            footer.addActionListenerDetailsButton(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Detail detail = new Detail(null, true, hm);
                    detail.setVisible(true);
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("error creating footer", e);
        }

    } // end createFooter

    //==========================================================================
    private void exportExcel(final String path) {

        final LoadingDialog loadingDialog = new LoadingDialog(null, false);
        loadingDialog.getjProgressBar().setIndeterminate(true);
        loadingDialog.getjLabelMessage().setText("creating file...");
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    new Exporter().exportExcel(hm.get("view").toString(), hm.get("job").toString(), hm.get("collector").toString(), path);
                } catch (Exception e) {
                    NOTIFICATIONS.error(e.getMessage(), e);
                } finally {
                    loadingDialog.setVisible(false);
                }

            }
        }).start();
        loadingDialog.setVisible(true);

    } // end exportExcel

    //==========================================================================
    private void exportExcelFilter(final String path) {

        final LoadingDialog loadingDialog = new LoadingDialog(null, false);
        loadingDialog.getjProgressBar().setIndeterminate(true);
        loadingDialog.getjLabelMessage().setText("creating file...");
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    new Exporter().exportExcelFilter(hm.get("view").toString(), hm.get("job").toString(), hm.get("collector").toString(), path);
                } catch (Exception e) {
                    NOTIFICATIONS.error(e.getMessage(), e);
                } finally {
                    loadingDialog.setVisible(false);
                }

            }
        }).start();
        loadingDialog.setVisible(true);

    } // end exportExcel

    //==========================================================================
    private void createTable() {

        String[] columnNames = null;
        Object[][] data = null;

        try {

            columnNames = (String[]) arrayList.get(1);
            data = (Object[][]) arrayList.get(2);

            model = new DefaultTableModel(data, columnNames) {
                public boolean isCellEditable(int rowIndex, int mColIndex) {
                    return false;
                }

                @Override
                public Class<?> getColumnClass(int column) {
                    Class<?> c = null;
                    try {
                        c = getValueAt(0, column).getClass();
                    } catch (Exception e) {
                        NOTIFICATIONS.error("error creating table", e);
                    }
                    return c;
                }
            }; //  end getColumnClass

            //setting the new model to table
            table.setModel(model);
            scrollPane = new JScrollPane(table);

        } catch (Exception e) {
            NOTIFICATIONS.error("error creating table", e);
        }
    } // end createTable

    //==========================================================================
    private void requestData() {

        Object object = null;

        try {

            object = new Linker().sendReceiveObject(hm);
            System.out.println("limits " + hm.get("limits"));
            if (object != null) {
                arrayList = (ArrayList) object;
            } else {
                throw new Exception("Error receiving data from server");
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("error creating table", e);
        }

    } // end requestData

    //==========================================================================
    private void setLoadingPanel() {

        new Thread(new Runnable() {
            public void run() {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        add(loadingPanel, BorderLayout.CENTER);
                        updateUI();
                    }
                });
            }
        }).start();

    } // end setLoadingPanel

    //==========================================================================
    public void setname() {
        setName(hm.get("view") + " Table");
    }

    //==========================================================================
    @Override
    public void destroy() {

        if (execute != null) {
            execute.interrupt();
        }

    } // end destroy

    //==========================================================================    
    public Class getclass() {
        return getClass();
    }

    //==========================================================================
    @Override
    protected void finalize() throws Throwable {

        try {
            destroy();
            execute = null;
            loadingPanel = null;
            panelFooter = null;
            panelPagination = null;
            limit1 = 1;
            limit2 = 100;
            scrollPane = null;
            totalResult = 0;
            arrayList = null;
            table = null;
            model = null;
        } catch (Exception e) {
            NOTIFICATIONS.error("error in finalize", e);
        } finally {
            super.finalize();
        }

    } // end finalize

    //==========================================================================    
    @Override
    public HashMap getHashMap() {
        return hm;
    }

    //==========================================================================    
    @Override
    public Object getData() {
        return arrayList;
    } // end getData
} // end class