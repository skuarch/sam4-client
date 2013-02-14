package views.panels;

import controllers.global.ControllerNotifications;
import controllers.sniffer.TabsDriver;
import java.awt.BorderLayout;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.UnexpectedException;
import java.util.HashMap;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.SwingWorker;
import model.common.Exporter;
import model.net.Linker;
import model.util.HashMapUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import views.charts.SimpleLineChart;
import views.dialogs.Chooser;
import views.dialogs.Detail;
import views.dialogs.LoadingDialog;
import views.helper.DropUtilities;

/**
 *
 * @author skuarch
 */
public class LineChartPanel extends SnifferPanel {

    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    private HashMap hm = null;
    private Linker linker = null;
    private LoadingPanel loadingPanel = new LoadingPanel();
    private Footer footer = null;
    private XYDataset dataset = null;
    private SimpleLineChart slc = null;
    private JList list = null;

    //==========================================================================
    public LineChartPanel(HashMap hm) {
        this.hm = hm;
        footer = new Footer();
        list = new JList();
        onLoad();
    }

    //==========================================================================
    private void onLoad() {
        this.setLayout(new BorderLayout());
        hm.put("request", hm.get("view"));
        add(loadingPanel, BorderLayout.CENTER);
        setDropTarget(new DropTarget(list, this));
    }

    //==========================================================================
    @Override
    public Object getData() {
        return slc.createChart(dataset);
    } // end getData

    //==========================================================================
    @Override
    public HashMap getHashMap() {
        return hm;
    }

    //==========================================================================
    public JPanel createPanel() {

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {

                    dataset = (XYDataset) requestData(hm);
                    slc = new SimpleLineChart(dataset, hm.get("view").toString(), "time", "", list);
                    remove(loadingPanel);
                    add(slc.getJPanel(), BorderLayout.CENTER);
                    createFooter();
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
    private void createFooter() {

        add(footer.getFooterLineChart(list), BorderLayout.SOUTH);
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
                        exportChartPdf(slc.createChart(dataset), path);
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
    }

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
            newHM.put("dates", getDataList());

            //set drillDown            
            newHM.put("drillDown", hm.get("view").toString());

            //adding new tab
            new TabsDriver().addTabNavigator(newHM);

        } catch (Exception e) {
            NOTIFICATIONS.error("error in drop", e);
        }

    }

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
    private Object requestData(HashMap hashMap) throws Exception {

        Object object = null;
        linker = new Linker();
        object = linker.sendReceiveObject(hashMap);

        return object;
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
        slc = null;
        list = null;
    }
} // end class
