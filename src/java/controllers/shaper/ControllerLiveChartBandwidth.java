package controllers.shaper;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.JTabPaneUtilities;
import org.jfree.data.time.RegularTimePeriod;
import views.charts.ShaperLineChartLive;
import views.dialogs.LiveChartBandwidth;

/**
 *
 * @author skuarch
 */
public class ControllerLiveChartBandwidth extends Controller {

    private LiveChartBandwidth liveChartBandwidth = null;
    private String collector = null;
    private HashMap hm = null;
    private boolean shutdown = false;
    private Thread threadLive = null;
    private String scale = null;
    private String stringSeconds = null;
    private int sleep = 0;

    //==========================================================================
    public ControllerLiveChartBandwidth(String collector) {
        liveChartBandwidth = new LiveChartBandwidth(null, true);
        this.collector = collector;
        hm = HashMapUtilities.getHashMapShaper();
    } // end ControllerLiveChartBandwidth

    //==========================================================================
    @Override
    public void setupInterface() {
        addListeners();
    } // end setupInterface

    //==========================================================================    
    public JLabel getCloseLabel(final String nameComponent) {

        JLabel closeLabel = null;

        try {

            closeLabel = new JLabel(new ImageIcon(getClass().getResource("/views/images/close_12.gif")));

            closeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    try {

                        JTabPaneUtilities.closeTab(liveChartBandwidth.getjTabbedPane(), nameComponent);

                    } catch (Exception ex) {
                        NOTIFICATIONS.error("Error closing tab", ex);
                    }
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible close tab", e);
        }

        return closeLabel;

    } // end getCloseLabel

    //==========================================================================
    private void createGraph() {

        live();

    } // end createGraph

    //==========================================================================
    private void live() {

        threadLive = new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList data = null;
                ArrayList arrayList = null;
                RegularTimePeriod rtp = null;
                double num = 0;
                double[] tmp = null;
                JPanel panelChartChains = null;
                ShaperLineChartLive shaperLineChartLive = null;
                String[] names = null;
                Object[] objects = null;
                Double[] values = null;

                try {

                    scale = liveChartBandwidth.getjComboBoxScale().getModel().getSelectedItem().toString();
                    stringSeconds = liveChartBandwidth.getjComboBoxSeconds().getModel().getSelectedItem().toString();
                    sleep = Integer.parseInt(stringSeconds) * 1000;

                    data = new ArrayList();
                    data.add(scale);

                    hm.put("collector", collector);
                    hm.put("request", "live bandwidth data");
                    hm.put("data", data);
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    //shaperLineChartLive = new ShaperLineChartLive("Bandwidth", "", scale, "Bandwidth");                    
                    shaperLineChartLive = new ShaperLineChartLive(scale, scale, scale, (String[]) arrayList.get(2));
                    shaperLineChartLive.createSeries();
                    panelChartChains = shaperLineChartLive.getJPanel();
                    panelChartChains.setName("Bandwidth");

                    addTab(panelChartChains);

                    while (shutdown) {

                        try {

                            if (!shutdown) {
                                break;
                            }

                            data = new ArrayList();
                            data.add(scale);

                            hm.put("collector", collector);
                            hm.put("request", "live bandwidth data");
                            hm.put("data", data);
                            arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                            if (arrayList != null) {

                                if (arrayList.get(0).equals(true)) {
                                    break;
                                }

                                rtp = (RegularTimePeriod) arrayList.get(3);

                                
                                    shaperLineChartLive.addSeriesList(rtp, (Double[]) arrayList.get(4));
                                

                                arrayList = null;
                                rtp = null;
                            }

                        } catch (Exception e) {
                            NOTIFICATIONS.error("error", e);
                        } finally {
                            arrayList = null;
                            rtp = null;
                            num = 0;
                            tmp = null;
                        }

                        threadLive.sleep(sleep);

                    } //  end while

                } catch (InterruptedException ie) {
                    threadLive = null;
                } catch (Exception e) {
                    NOTIFICATIONS.error("error in chart live", e);
                } finally {
                    threadLive = null;
                }
            }
        });

        threadLive.start();
    }

    //==========================================================================
    private void addTab(JPanel panel) {

        JPanel panelTitle = null;

        try {

            panel.setName(JTabPaneUtilities.getPanelName(panel.getName(), liveChartBandwidth.getjTabbedPane()));
            panelTitle = JTabPaneUtilities.getPanelTitle(panel.getName(), getCloseLabel(panel.getName()));
            liveChartBandwidth.getjTabbedPane().add(panel);
            liveChartBandwidth.getjTabbedPane().setSelectedIndex(liveChartBandwidth.getjTabbedPane().getTabCount() - 1);
            liveChartBandwidth.getjTabbedPane().setTabComponentAt(liveChartBandwidth.getjTabbedPane().getTabCount() - 1, panelTitle);

        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible add tab", e);
        }

    }

    //==========================================================================
    private void addListeners() {

        liveChartBandwidth.getjButtonReload().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGraph();
            }
        });

        liveChartBandwidth.getjButtonClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        liveChartBandwidth.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });

    } // end addListeners

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        shutdown = flag;
        liveChartBandwidth.setVisible(flag);
    } // end setVisible

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end destroyCurrentThread
} // end class

