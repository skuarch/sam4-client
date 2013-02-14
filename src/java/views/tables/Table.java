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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.common.Exporter;
import model.net.Linker;
import model.util.HashMapUtilities;
import views.dialogs.Chooser;
import views.dialogs.Detail;
import views.dialogs.LoadingDialog;
import views.panels.Footer;
import views.panels.LoadingPanel;
import views.panels.SnifferPanel;

/**
 *
 * @author skuarch
 */
public class Table extends SnifferPanel {

    private LoadingPanel loadingPanel = null;
    private ControllerNotifications NOTIFICATIONS = null;
    private DefaultTableModel model = null;
    private JScrollPane scrollPane = null;
    private Thread execute = null;
    private ArrayList arrayList = null;
    private JTable table = null;
    private JPanel panelFooter = null;
    private HashMap hm = HashMapUtilities.getHashMapSniffer();

    //==========================================================================
    public Table(HashMap hm) {
        this.hm = hm;
        arrayList = new ArrayList();
        loadingPanel = new LoadingPanel();
        NOTIFICATIONS = new ControllerNotifications();
        scrollPane = new JScrollPane();
        table = new JTable();        
        onLoad();
    } // end Table

    //==========================================================================
    private void onLoad() {

        setname();
        setLayout(new BorderLayout());
        hm.put("request", hm.get("view"));
        hm.put("isTable", true);

        new Thread(new Runnable() {
            public void run() {
                setLoadingPanel();
                execute();
            }
        }).start();

    } // end onLoad    

    //==========================================================================
    private void execute() {

        remover();
        setLoadingPanel();

        execute = new Thread(new Runnable() {
            public void run() {

                try {

                    requestData();
                    createTable();
                    sortTable();
                    createPanelFooter();
                    remover();
                    add(scrollPane, BorderLayout.CENTER);
                    add(panelFooter, BorderLayout.SOUTH);

                } catch (Exception e) {
                    NOTIFICATIONS.error("error creating table", e);
                } finally {
                    updateUI();
                }
            }
        });
        execute.setName("execute");
        execute.start();

    } // end execute

    //==========================================================================
    private void createPanelFooter() {

        Footer footer = null;

        try {

            footer = new Footer();
            panelFooter = footer.getFooterTable();

            //excel
            footer.addActionListenerExportExcel(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    exportExcel();
                }
            });


            //detail
            footer.addActionListenerDetailsButton(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    HashMap hashMap = HashMapUtilities.getHashMapSniffer();
                    hashMap.put("view", hm.get("view").toString());
                    hashMap.put("job", hm.get("job").toString());
                    hashMap.put("collector", hm.get("collector").toString());
                    hashMap.put("isTable", "true");

                    Detail detail = new Detail(null, true, hashMap);
                    detail.setVisible(true);
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("error creating footer", e);
        }

    } // end createFooter

    //==========================================================================
    private void exportExcel() {

        new Thread(new Runnable() {
            public void run() {

                try {

                    String path = null;
                    Chooser chooser = new Chooser();
                    path = chooser.getPath();

                    if (path == null) {
                        return;
                    } else {
                        exportExcel(hm.get("view").toString(), hm.get("job").toString(), hm.get("collector").toString(), path);
                    }

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                }

            }
        }).start();

    } // end exportExcel

    //==========================================================================
    private void exportExcel(final String view, final String job, final String collector, final String path) {

        final LoadingDialog loadingDialog = new LoadingDialog(null, false);
        loadingDialog.getjProgressBar().setIndeterminate(true);
        loadingDialog.getjLabelMessage().setText("creating file...");
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    new Exporter().exportExcel(view, path, arrayList);
                } catch (Exception e) {
                    NOTIFICATIONS.error(e.getMessage(), e);
                } finally {
                    loadingDialog.setVisible(false);
                }

            }
        }).start();
        loadingDialog.setVisible(true);
    }

    //==========================================================================
    private void remover() {
        try {

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
    private void createTable() {

        String[] columnNames = null;
        Object[][] data = null;

        try {

            columnNames = (String[]) arrayList.get(0);
            data = (Object[][]) arrayList.get(1);

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

            if (object != null) {
                arrayList = (ArrayList) object;
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("error creating table", e);
        }

    } // end requestData

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
    private void setLoadingPanel() {

        add(loadingPanel, BorderLayout.CENTER);
        updateUI();

    } // end setLoadingPanel    

    //==========================================================================    
    public void setname() {
        setName(hm.get("view") + " Table");
    } // end setname

    //==========================================================================
    @Override
    public void destroy() {
        if (execute != null) {
            execute.interrupt();
        }
    } // end destroy

    //==========================================================================    
    public Class getclass() {
        return this.getClass();
    } // end getClass

    //==========================================================================    
    protected void finalize() throws Throwable {

        try {
            destroy();
            loadingPanel = null;
            NOTIFICATIONS = null;
            table = null;
            model = null;
            scrollPane = null;
        } catch (Exception e) {
            e.printStackTrace();
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