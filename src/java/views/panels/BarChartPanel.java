package views.panels;

import controllers.global.ControllerNotifications;
import controllers.sniffer.TabsDriver;
import java.awt.BorderLayout;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.SwingWorker;
import model.common.Exporter;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.ViewUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import views.charts.BarChartSelected;
import views.dialogs.Chooser;
import views.dialogs.Detail;
import views.dialogs.LoadingDialog;
import views.helper.DropUtilities;

/**
 *
 * @author skuarch
 */
public class BarChartPanel extends SnifferPanel {

    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    private HashMap hm = null;
    private Linker linker = null;
    private LoadingPanel loadingPanel = new LoadingPanel();
    private HeaderPagination hp = null;
    private Footer footer = null;
    private CategoryDataset dataset = null;
    private BarChartSelected bcs = null;
    private JList list = null;
    private JPanel panelPagination = null;
    private JPanel panelFooter = null;
    private JPanel panelBarChart = null;
    private int totalResult = 0;
    private int limit1 = 1;
    private int limit2 = 25;
    private boolean finishPagination = false;
    private ArrayList arrayList = null;

    //==========================================================================
    public BarChartPanel(HashMap hm) {
        footer = new Footer();
        list = ViewUtilities.getListBarChart();
        this.hm = hm;
        onLoad();
    }

    //==========================================================================
    private void onLoad() {
        this.setLayout(new BorderLayout());
        setDropTarget(new DropTarget(list, this));
    }

    //==========================================================================
    @Override
    public Object getData() {
        return bcs.createChart(dataset);
    } // end getData
    
    //==========================================================================
    @Override
    public HashMap getHashMap() {
        return hm;
    }
 
    //==========================================================================
    public JPanel createPanel() {

        remover();
        add(loadingPanel, BorderLayout.CENTER);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {

                    requestData(hm);
                    totalResult = (Integer) arrayList.get(0);
                    dataset = (CategoryDataset) arrayList.get(1);

                    createChart();
                    createPanelFooter();
                    createPanelPagination();

                    remover();

                    add(panelBarChart, BorderLayout.CENTER);
                    add(panelFooter, BorderLayout.SOUTH);
                    add(panelPagination, BorderLayout.NORTH);

                    updateUI();

                } catch (Exception e) {
                    NOTIFICATIONS.error("imposible create panel", e);
                }

                return null;
            }
        }.execute();

        return this;
    }

    //==========================================================================
    private void createChart() {
        bcs = new BarChartSelected(dataset, hm.get("view").toString(), "", "", list);
        panelBarChart = bcs.getPanel();
    }

    //==========================================================================
    private void createPanelPagination() {

        try {

            hp = new HeaderPagination();
            panelPagination = hp.getHeaderBarChart();

            if (finishPagination) {
                limit2 = limit1 - 1 + 25;
                finishPagination = false;
            }

            if (totalResult <= 25) {
                hp.enableAllComponents(false);
            }

            if (totalResult > 24) {
                hp.enableNextButton(true);
            }

            if (totalResult <= 1) {
                hp.enableAllComponents(false);
            }

            if (limit1 <= 1) {
                limit1 = 1;
                limit2 = 25;
                hp.enableBackButton(false);
            }

            if (limit2 > totalResult) {
                limit2 = totalResult;
                finishPagination = true;
                hp.enableNextButton(false);
            }


            hp.setLabelText(limit1 + " to " + limit2 + " of " + totalResult);

            //next
            hp.addActionListenerNextButton(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    limit1 += 25;
                    limit2 += 25;
                    hm.put("limits", limit1 + "," + limit2);
                    createPanel();
                }
            });

            //back
            hp.addActionListenerBackButton(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    limit1 -= 25;
                    limit2 -= 25;
                    hm.put("limits", limit1 + "," + limit2);
                    createPanel();
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("error creating header", e);
        }

    } // end createPanelPagination    

    //==========================================================================
    private void createPanelFooter() {

        Footer footer = null;

        try {

            footer = new Footer();
            panelFooter = footer.getFooterBarChart(list);

            footer.getExportExcelButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        String path = null;
                        Chooser chooser = new Chooser();
                        path = chooser.getPath();

                        if (path == null) {
                            return;
                        } else {
                            exportExcel(path);
                        }

                    } catch (Exception ex) {
                        NOTIFICATIONS.error("Unexpected error", ex);
                    }
                }
            });

            footer.getPdfButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String path = null;
                    Chooser chooser = new Chooser();
                    
                    try {                        
                        
                        path = chooser.getPath();
                        if (path != null || path.length() > 0) {
                            exportChartPdf(bcs.createChart(dataset), path);
                        }
                        
                    } catch (Exception ex) {
                        NOTIFICATIONS.error("Unexpected error", ex);
                    }
                }
            });

            footer.getTableButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    HashMap hashmap = HashMapUtilities.createHashMapFromHashMap(hm);
                    hashmap.put("view", hm.get("view") + " Table");
                    new TabsDriver().addTabNavigator(hashmap);
                }
            });

            footer.getDetailsButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Detail(null, true, hm).setVisible(true);
                }
            });
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    }

    //==========================================================================
    private void remover() {
        try {

            remove(loadingPanel);

            if (panelPagination != null) {
                remove(panelPagination);
            }

            if (panelFooter != null) {
                remove(panelFooter);
            }

            if (bcs != null) {
                remove(panelBarChart);
            }
        } catch (Exception e) {
            NOTIFICATIONS.error("error removing panels", e);
        } finally {
            removeAll();
        }
    } // remover

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
    }

    //==========================================================================
    private void exportChartPdf(final JFreeChart chart, final String path) {

        final LoadingDialog loadingDialog = new LoadingDialog(null, false);
        loadingDialog.getjProgressBar().setIndeterminate(true);
        loadingDialog.getjLabelMessage().setText("creating file...");

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    new Exporter().exportChartToPDF(chart, path);
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
    @Override
    public void drop(DropTargetDropEvent dtde) {

        if (dtde == null) {
            NOTIFICATIONS.error("dtde is null", new NullPointerException("dtde is null"));
            return;
        }

        if (list.getModel().getSize() < 1) {
            NOTIFICATIONS.error("please select something from chart above", new UnexpectedException("please select something from chart above"));
            return;
        }

        String selected = null;
        HashMap newHM = null;

        try {

            selected = DropUtilities.getStringFromDrop(dtde);

            if (!DropUtilities.checkDrop(selected)) {
                return;
            }

            //create a new hashmap
            newHM = HashMapUtilities.createHashMapFromHashMap(hm);

            //setView
            newHM.put("view", selected);

            //set dates
            setOption(newHM, hm.get("view").toString());

            //set drillDown            
            newHM.put("drillDown", hm.get("view").toString());

            //adding new tab
            new TabsDriver().addTabNavigator(newHM);

        } catch (Exception e) {
            NOTIFICATIONS.error("error in drop", e);
        }

    }

    //==========================================================================
    private void setOption(HashMap newHM, String view) {

        try {

            if (view.equalsIgnoreCase("Network Protocols")) {
                newHM.put("networkProtocol", getDataList());
                newHM.put("typeProtocol", view);
            } else if (view.equalsIgnoreCase("IP Protocols")) {
                newHM.put("ipProtocol", getDataList());
                newHM.put("typeProtocol", view);
            } else if (view.equalsIgnoreCase("TCP Protocols")) {
                newHM.put("tcpProtocol", getDataList());
                newHM.put("typeProtocol", view);
            } else if (view.equalsIgnoreCase("UDP Protocols")) {
                newHM.put("udpProtocol", getDataList());
                newHM.put("typeProtocol", view);
            } else if (view.equalsIgnoreCase("IP Talkers Bytes")) {
                newHM.put("ipAddress", getDataList());
            } else if (view.equalsIgnoreCase("IP Talkers Sources Bytes")) {
                newHM.put("ipAddress", getDataList());
            } else if (view.equalsIgnoreCase("IP Talkers Destinations Bytes")) {
                newHM.put("ipAddress", getDataList());
            } else if (view.equalsIgnoreCase("Websites")) {
                newHM.put("websites", getDataList());
            } else if (view.equalsIgnoreCase("Web Server Hosts")) {
                newHM.put("webserverhost", getDataList());
            } else if (view.equalsIgnoreCase("Type of Service")) {
                newHM.put("typeService", getDataList());
            } else if (view.equalsIgnoreCase("Top Ports")) {
                newHM.put("portNumber", getDataList());
            } else if (view.equalsIgnoreCase("TCP Ports")) {
                newHM.put("portNumber", getDataList());
            } else if (view.equalsIgnoreCase("UDP Ports")) {
                newHM.put("portNumber", getDataList());
            } else if (view.equalsIgnoreCase("Hostname Talkers Bytes")) {
                newHM.put("hostname", getDataList());
            } else if (view.equalsIgnoreCase("Hostname Talkers Destination Bytes")) {
                newHM.put("hostname", getDataList());
            } else if (view.equalsIgnoreCase("Hostname Talkers Sources Bytes")) {
                newHM.put("hostname", getDataList());
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("error setting ip or protocols", e);
        }

    } // end setProtocolsIps

    //==========================================================================
    /**
     * return the items in list separated by commas.
     *
     * @return String
     */
    private String getDataList() {

        StringBuffer data = null;
        int size = 0;
        ListModel model = null;

        try {

            size = list.getModel().getSize();

            if (size < 1) {
                return null;
            }

            model = list.getModel();
            data = new StringBuffer();

            for (int i = 0; i < size; i++) {
                data.append(model.getElementAt(i).toString());
                data.append(",");
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("error getting data from list", e);
        }

        return data.toString();
    } // end getDataList

    //==========================================================================
    private void requestData(HashMap hashMap) throws Exception {

        Object object = null;

        try {

            hm.put("request", hm.get("view"));
            linker = new Linker();
            object = linker.sendReceiveObject(hashMap);

            if (object != null) {
                arrayList = (ArrayList) object;
            } else {
                throw new Exception("Error receiving data from server");
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("error creating table", e);
        }

    }

    //==========================================================================
    @Override
    public void destroy() {
        linker.abort();
        hm = null;
        linker = null;
        loadingPanel = new LoadingPanel();
        footer = null;
        dataset = null;
        bcs = null;
        list = null;
    }
} // end class
