package controllers.filter;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingWorker;
import model.common.ModelCollectors;
import model.util.JTabPaneUtilities;
import model.util.ViewUtilities;
import views.frames.Filter;

/**
 *
 * @author skuarch
 */
public class ControllerFilter extends Controller {

    private Filter filter = null;
    private String collector = null;
    ControllerFilterOptions cfo = null;

    //==========================================================================
    private ControllerFilter() {
        filter = new Filter();
        cfo = ControllerFilterOptions.getInstance();
        onLoad();
    } // end ControllerFilter

    //==========================================================================
    public static ControllerFilter getInstance() {
        return ControllerFilterHolder.INSTANCE;
    } // end getInstance

    //==========================================================================
    private static class ControllerFilterHolder {

        private static final ControllerFilter INSTANCE = new ControllerFilter();
    } // end ControllerFilterHolder

    //==========================================================================
    private void onLoad() {

        try {
            ViewUtilities.fillJComboBox(filter.getjComboBoxCollectors(), new ModelCollectors().getActivesCollectorsStringArray());
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    }

    //==========================================================================
    @Override
    public void setupInterface() {

        filter.getjProgressBar().setIndeterminate(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {
                    
                    addListeners();

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    filter.getjProgressBar().setIndeterminate(false);
                }

                return null;
            }
        }.execute();

    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        try {

            //------------------------------------------------------------------
            filter.getjComboBoxCollectors().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    comboChange();
                }
            });

            //------------------------------------------------------------------
            filter.getjButtonPolicies().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    policies();
                }
            });

            filter.getjButtonCategories().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    categories();
                }
            });

            filter.getjButtonBlackList().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    blackList();
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end addListener

    //==========================================================================
    private void comboChange() {

        String selected = null;

        try {

            filter.getjTabbedPane().removeAll();

            selected = filter.getjComboBoxCollectors().getModel().getSelectedItem() + "";

            if (selected == null || selected.length() < 1 || selected.contains("please")) {
                setEnabledComponents(false);
                return;
            }

            collector = selected;
            setEnabledComponents(true);

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end comboChange

    //==========================================================================
    private void policies() {

        int tabNum = 0;
        ControllerPolicies controllerPolicies = new ControllerPolicies(collector, filter.getjProgressBar());

        try {

            tabNum = JTabPaneUtilities.getComponentNum(filter.getjTabbedPane(), "policies");

            if (tabNum == -1) {

                controllerPolicies.setupInterface();
                filter.getjTabbedPane().add(controllerPolicies.getPanel());
                filter.getjTabbedPane().setSelectedIndex(filter.getjTabbedPane().getTabCount() - 1);

            } else {

                filter.getjTabbedPane().setSelectedIndex(tabNum);

            }

        } catch (Exception ex) {
            NOTIFICATIONS.error("Unexpected error", ex);
        }

    } // end policies

    //==========================================================================
    private void categories() {

        int tabNum = 0;
        ControllerCategories controllerCategories = new ControllerCategories(collector, filter.getjProgressBar());

        try {

            tabNum = JTabPaneUtilities.getComponentNum(filter.getjTabbedPane(), "categories");

            if (tabNum == -1) {

                controllerCategories.setupInterface();
                filter.getjTabbedPane().add(controllerCategories.getPanel());
                filter.getjTabbedPane().setSelectedIndex(filter.getjTabbedPane().getTabCount() - 1);

            } else {

                filter.getjTabbedPane().setSelectedIndex(tabNum);

            }

        } catch (Exception ex) {
            NOTIFICATIONS.error("Unexpected error", ex);
        }

    } // end policies

    //==========================================================================
    private void blackList() {

        int tabNum = 0;
        //ControllerBlackList controllerBlackList = ControllerBlackList.getInstance();
        ControllerBlackList controllerBlackList = new ControllerBlackList();

        try {

            tabNum = JTabPaneUtilities.getComponentNum(filter.getjTabbedPane(), "black list");

            if (tabNum == -1) {

                controllerBlackList.setColletor(collector);
                controllerBlackList.setJProgressBar(filter.getjProgressBar());
                controllerBlackList.setupInterface();                
                filter.getjTabbedPane().add(controllerBlackList.getPanel());
                filter.getjTabbedPane().setSelectedIndex(filter.getjTabbedPane().getTabCount() - 1);

            } else {

                filter.getjTabbedPane().setSelectedIndex(tabNum);

            }

        } catch (Exception ex) {
            NOTIFICATIONS.error("Unexpected error", ex);
        }

    } // end blackList
    
    //==========================================================================
    private void setEnabledComponents(boolean flag) {

        filter.getjButtonPolicies().setEnabled(flag);
        filter.getjButtonCategories().setEnabled(flag);
        filter.getjButtonBlackList().setEnabled(flag);

    } // end setEnabledComponents

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {

        try {
            filter.setVisible(flag);
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }


    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class
