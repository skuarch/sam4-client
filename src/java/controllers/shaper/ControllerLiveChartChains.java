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
import javax.swing.SwingWorker;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.JTabPaneUtilities;
import org.jfree.data.time.RegularTimePeriod;
import views.charts.ShaperLineChartLive;
import views.dialogs.LiveChartChains;
import model.util.ViewUtilities;

/**
 *
 * @author skuarch
 */
public class ControllerLiveChartChains extends Controller {

    private LiveChartChains liveChartChains = null;
    private String collector = null;
    private HashMap hm = null;
    private boolean shutdown = false;
    private Thread threadLive = null;    
    private String chain = null;
    private String scale = null;
    private String stringSeconds = null;
    private int sleep = 0;

    //==========================================================================
    public ControllerLiveChartChains(String collector) {
        liveChartChains = new LiveChartChains(null, true);
        this.collector = collector;
        hm = HashMapUtilities.getHashMapShaper();
    } // end ControllerLiveChartChains

    //==========================================================================
    @Override
    public void setupInterface() {

        liveChartChains.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                ArrayList arrayList = null;

                try {

                    addListeners();

                    hm.put("collector", collector);
                    hm.put("request", "live chains initial data");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    if (arrayList != null) {
                        ViewUtilities.fillJComboBox(liveChartChains.getjComboBoxChains(), (Object[]) arrayList.get(0));
                    }

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unepected error", e);
                } finally {
                    liveChartChains.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

                return null;
            }
        }.execute();

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

                        JTabPaneUtilities.closeTab(liveChartChains.getjTabbedPane(), nameComponent);

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
                String tmp = null;
                String direction = null;
                JPanel panelChartChains = null;
                ShaperLineChartLive shaperLineChartLive = null;

                try {

                    chain = liveChartChains.getjComboBoxChains().getModel().getSelectedItem().toString();
                    scale = liveChartChains.getjComboBoxScale().getModel().getSelectedItem().toString();
                    stringSeconds = liveChartChains.getjComboBoxSeconds().getModel().getSelectedItem().toString();
                    sleep = Integer.parseInt(stringSeconds) * 1000;

                    if (liveChartChains.getjRadioButtonIncoming().isSelected()) {
                        direction = "incoming";
                    } else {
                        direction = "outcoming";
                    }

                    if (chain.contains("please")) {
                        NOTIFICATIONS.information("please select a chain from list", true);
                        return;
                    }

                    shaperLineChartLive = new ShaperLineChartLive(chain + " " + direction, "", scale, chain);
                    panelChartChains = shaperLineChartLive.getJPanel();
                    panelChartChains.setName(chain);

                    addTab(panelChartChains);

                    while (shutdown) {

                        try {

                            if (!shutdown) {
                                break;
                            }

                            data = new ArrayList();
                            data.add(liveChartChains.getjRadioButtonIncoming().isSelected());
                            data.add(liveChartChains.getjComboBoxScale().getModel().getSelectedItem().toString());
                            data.add(liveChartChains.getjComboBoxChains().getModel().getSelectedItem().toString());

                            hm.put("collector", collector);
                            hm.put("request", "live chains data");
                            hm.put("data", data);
                            arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                            if (arrayList != null) {

                                if (arrayList.get(0).equals(true)) {
                                    break;
                                }

                                rtp = (RegularTimePeriod) arrayList.get(3);
                                tmp = arrayList.get(4).toString();

                                if (rtp != null && tmp != null) {
                                    num = Double.parseDouble(tmp);
                                    shaperLineChartLive.addSeries(rtp, num);
                                }

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

            panel.setName(JTabPaneUtilities.getPanelName(panel.getName(), liveChartChains.getjTabbedPane()));
            panelTitle = JTabPaneUtilities.getPanelTitle(panel.getName(), getCloseLabel(panel.getName()));
            liveChartChains.getjTabbedPane().add(panel);
            liveChartChains.getjTabbedPane().setSelectedIndex(liveChartChains.getjTabbedPane().getTabCount() - 1);
            liveChartChains.getjTabbedPane().setTabComponentAt(liveChartChains.getjTabbedPane().getTabCount() - 1, panelTitle);

        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible add tab", e);
        }

    }

    //==========================================================================
    private void addListeners() {

        liveChartChains.getjButtonReload().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createGraph();
            }
        });

        liveChartChains.getjButtonClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        liveChartChains.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                setVisible(false);
            }
        });

        liveChartChains.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });

    } // end addListeners

    //==========================================================================
    private void setEnabledComponents(boolean flag) {
        liveChartChains.getjButtonReload().setEnabled(flag);
        liveChartChains.getjComboBoxChains().setEnabled(flag);
    } // end setEnabledComponents

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        shutdown = flag;
        liveChartChains.setVisible(flag);
        liveChartChains.getjComboBoxChains().setEnabled(flag);
    } // end setVisible

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end destroyCurrentThread
    
} // end class

