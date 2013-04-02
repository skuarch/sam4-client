package controllers.e2e;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.JTabPaneUtilities;
import model.util.ViewUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import views.charts.BarChartHorizontal;
import views.frames.EndToEnd;
import views.panels.LoadingPanel;

/**
 *
 * @author skuarch
 */
public class ControllerEndToEnd extends Controller {

    private EndToEnd e2e = null;
    private DefaultTreeModel modelSource = null;
    private DefaultTreeModel modelDestination = null;
    private DefaultMutableTreeNode genericRootNode = null;
    private HashMap hm = null;
    private LoadingPanel loadingPanel = null;
    private SwingWorker sw = null;

    //==========================================================================
    private ControllerEndToEnd() {
        e2e = new EndToEnd();
        hm = HashMapUtilities.getHashMapEndToEnd();
        loadingPanel = new LoadingPanel();
    } // end ControllerEndToEnd

    //==========================================================================
    public static ControllerEndToEnd getInstance() {        
        return ControllerEnd2EndHolder.INSTANCE;
    } // end ControllerEndToEnd

    //==========================================================================
    private static class ControllerEnd2EndHolder {

        private static final ControllerEndToEnd INSTANCE = new ControllerEndToEnd();
    } // end ControllerEnd2EndHolder

    //==========================================================================
    @Override
    public void setupInterface() {

        try {

            genericRootNode = ViewUtilities.rootNodeCollectors();

            modelSource = new DefaultTreeModel(genericRootNode);
            e2e.getjTreeSource().setModel(modelSource);

            modelDestination = new DefaultTreeModel(genericRootNode);
            e2e.getjTreeDestination().setModel(modelDestination);

            addListeners();

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
    } // end setupInterface

    //==========================================================================
    private void execute() {

        e2e.getjTabbedPane().add(loadingPanel);

        sw = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        BarChartHorizontal bch = null;
                        DefaultCategoryDataset dataset = null;
                        String source = null;
                        String destination = null;
                        String nameComponent = null;
                        JPanel panel = null;
                        JPanel panelTitle = null;

                        try {

                            source = ViewUtilities.getSelectedJTree(e2e.getjTreeSource());
                            destination = ViewUtilities.getSelectedJTree(e2e.getjTreeDestination());
                            destination = destination.replace("<", "");
                            destination = destination.replace(">", "");
                            nameComponent = source + "->" + destination;
                            nameComponent = JTabPaneUtilities.getPanelName(nameComponent, e2e.getjTabbedPane());

                            dataset = (DefaultCategoryDataset) requestData(source, destination);

                            bch = new BarChartHorizontal(dataset, "", "hops", "milliseconds", new JList());
                            panel = bch.getPanel();
                            panel.setName(nameComponent);

                            panelTitle = JTabPaneUtilities.getPanelTitle(nameComponent, getCloseLabel(nameComponent));

                            e2e.getjTabbedPane().remove(loadingPanel);
                            e2e.getjTabbedPane().add(nameComponent, panel);
                            e2e.getjTabbedPane().setTabComponentAt(e2e.getjTabbedPane().getTabCount() - 1, panelTitle);
                            e2e.getjTabbedPane().setSelectedIndex(e2e.getjTabbedPane().getTabCount() - 1);
                            e2e.getjTabbedPane().updateUI();

                        } catch (Exception e) {
                            NOTIFICATIONS.error("Unexpected error", e);
                        }
                    }
                });

                return null;
            }
        };

        sw.execute();

    } // end execute   

    //==========================================================================
    public JLabel getCloseLabel(final String nameComponent) {
        JLabel closeLabel = null;

        try {

            closeLabel = new JLabel(new ImageIcon(getClass().getResource("/views/images/close_12.gif")));

            closeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    try {

                        closeTab(nameComponent);

                    } catch (Exception ex) {
                        NOTIFICATIONS.error("Error closing tab", ex);
                    }
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Error creating close label", e);
        }

        return closeLabel;

    } // end getCloseLabel

    //=========================================================================
    private void closeTab(final String nameComponent) {

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {
                    JTabPaneUtilities.closeTab(e2e.getjTabbedPane(), nameComponent);
                } catch (Exception e) {
                    NOTIFICATIONS.error("Error closing tab", e);
                }

                return null;
            }
        }.execute();
    }

    //==========================================================================
    private Object requestData(String source, String destination) {

        Object object = null;

        try {

            hm.put("collector", source);
            hm.put("source", source);
            hm.put("destination", destination);

            object = new Linker().sendReceiveObject(hm);

            if (object == null) {
                NOTIFICATIONS.error("The remote server is returning a bad response", null);
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("Error requesting data", e);
        }

        return object;

    } // end requestData

    //==========================================================================
    private void addListeners() {

        try {

            e2e.getjButtonAdd().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (validationAdd()) {
                        remodelTreeDestination();
                    }
                }
            });

            e2e.getjButtonExecute().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    execute();
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end addListeners

    //==========================================================================
    private void remodelTreeDestination() {

        DefaultMutableTreeNode rootNode = null;

        try {

            rootNode = (DefaultMutableTreeNode) modelDestination.getRoot();
            rootNode.add(new DefaultMutableTreeNode("<" + e2e.getjTextFieldAdd().getText() + ">"));
            modelDestination = new DefaultTreeModel(rootNode);
            e2e.getjTreeDestination().setModel(modelDestination);
            e2e.getjTextFieldAdd().setText("");

        } catch (Exception e) {
            NOTIFICATIONS.error("Error creating tree destination", e);
        }

    } // end remodelTreeDestination

    //==========================================================================
    private boolean validationAdd() {

        boolean flag = false;
        String text = null;

        try {

            text = e2e.getjTextFieldAdd().getText();

            if (text == null || text.length() < 1 || text.contains(" ")) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("Error in validation", e);
        }

        return flag;

    } // end validationAdd

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        e2e.setVisible(flag);
    } // end setVisible

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end destroyCurrentThread
} // end class
