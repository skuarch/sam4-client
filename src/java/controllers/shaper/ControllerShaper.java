package controllers.shaper;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.SwingWorker;
import model.beans.CurrentUser;
import model.common.ModelCollectors;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.JTabPaneUtilities;
import model.util.ViewUtilities;
import views.frames.Shaper;

/**
 *
 * @author skuarch
 */
public class ControllerShaper extends Controller {

    private Shaper shaper = null;
    private String collector = null;
    private ControllerChains controllerChains = null;
    private ControllerPipes controllerPipes = null;
    private ControllerFilters controllerFilters = null;
    private ControllerTargets controllerTargets = null;
    private ControllerPorts controllerPorts = null;
    private ControllerProtocols controllerProtocols = null;
    private ControllerServiceLevel controllerServiceLevel = null;

    //==========================================================================
    private ControllerShaper() {
        shaper = new Shaper();
    } // end ControllerShaper

    //==========================================================================
    public static ControllerShaper getInstance() {
        return ControllerShaperHolder.INSTANCE;
    } // end ControllerShaper

    //==========================================================================
    private static class ControllerShaperHolder {
        private static final ControllerShaper INSTANCE = new ControllerShaper();
    } // end ControllerShaperHolder

    //==========================================================================
    @Override
    public void setupInterface() {

        try {

            ViewUtilities.fillJComboBox(shaper.getjComboBoxCollectors(), new ModelCollectors().getActivesCollectorsStringArray());
            addListeners();
            
            if(CurrentUser.getInstance().getLevel() == 0){
                shaper.getjMenuItemRulesLoad().setEnabled(false);
                shaper.getjMenuItemRulesUnload().setEnabled(false);
                shaper.getjMenuItemRulesLoadDebug().setEnabled(false);
                shaper.getjMenuItemStopCollector().setEnabled(false);
                shaper.getjMenuItemStartCollector().setEnabled(false);
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        //----------------------------------------------------------------------
        shaper.getjComboBoxCollectors().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeCombo();
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemManageChains().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageChains();
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemManagePipes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managePipes();
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemFilters().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageFilters();
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemSettingsTargets().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingTargets();
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemSettingsPorts().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingPorts();
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemSettingsProtocols().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingProtocols();
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemSettingsServiceLevel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingServiceLevel();
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemSettingsOptions().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingOptions();
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemRulesShow().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerInformer ci = new ControllerInformer("show", collector);
                ci.setupInterface();
                ci.setVisible(true);
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemRulesLoad().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerInformer ci = new ControllerInformer("load", collector);
                ci.setupInterface();
                ci.setVisible(true);
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemRulesLoadDebug().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerInformer ci = new ControllerInformer("load debug", collector);
                ci.setupInterface();
                ci.setVisible(true);
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemRulesUnload().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerInformer ci = new ControllerInformer("unload", collector);
                ci.setupInterface();
                ci.setVisible(true);
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemRulesOverview().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerInformer ci = new ControllerInformer("overview", collector);
                ci.setupInterface();
                ci.setVisible(true);
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemMonitoringChains().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerLiveChartChains clcc = new ControllerLiveChartChains(collector);
                clcc.setupInterface();
                clcc.setVisible(true);
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemMonitoringPipes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerLiveChartPipes clcp = new ControllerLiveChartPipes(collector);
                clcp.setupInterface();
                clcp.setVisible(true);
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemMonitoringBandwidth().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerLiveChartBandwidth clcb = new ControllerLiveChartBandwidth(collector);
                clcb.setupInterface();
                clcb.setVisible(true);
            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemStopCollector().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                stopStartCollector("stop");

            }
        });

        //----------------------------------------------------------------------
        shaper.getjMenuItemStartCollector().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                stopStartCollector("start");

            }
        });

    } // end addListeners

    //==========================================================================
    private void stopStartCollector(String text) {

        HashMap hm = HashMapUtilities.getHashMapShaper();
        String message = null;

        try {

            hm.put("collector", collector);
            hm.put("request", text + " collector");
            message = (String) new Linker().sendReceiveObject(hm);

            NOTIFICATIONS.information(message, true);

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
    }

    //==========================================================================
    private void changeCombo() {

        shaper.getjLabelMessage().setText("wait");

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                String selected = null;
                ArrayList arrayList = null;
                HashMap hm = null;
                boolean isEnabled = false;

                try {

                    shaper.getjTabbedPane().removeAll();
                    selected = shaper.getjComboBoxCollectors().getModel().getSelectedItem().toString();

                    if (selected.contains("select")) {

                        setEnableMenus(false);
                        return null;

                    } else {

                        collector = selected;
                        hm = HashMapUtilities.getHashMapShaper();
                        hm.put("collector", collector);
                        hm.put("request", "is enabled");
                        arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                        isEnabled = (Boolean) arrayList.get(0);

                        if (isEnabled) {
                            setEnableMenus(true);
                            shaper.getjLabelMessage().setText("please select an option from menu");
                        } else {
                            setEnableMenus(false);
                            NOTIFICATIONS.information(arrayList.get(1).toString(), true);
                            shaper.getjLabelMessage().setText("");
                        }
                    }

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                }

                return null;
            }
        }.execute();

    } // end changeCombo

    //==========================================================================
    private void setEnableMenus(boolean flag) {

        try {

            shaper.getjMenuManage().setEnabled(flag);
            shaper.getjMenuSettings().setEnabled(flag);
            shaper.getjMenuRulesRules().setEnabled(flag);
            shaper.getjMenuMonitoring().setEnabled(flag);
            shaper.getjMenuTools().setEnabled(flag);

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end setEnableMenus

    //==========================================================================
    private void manageChains() {

        int tabNum = 0;
        controllerChains = new ControllerChains(collector);
        controllerChains.setupInterface();

        try {

            tabNum = JTabPaneUtilities.getComponentNum(shaper.getjTabbedPane(), "manage chains");

            if (tabNum == -1) {

                shaper.getjTabbedPane().add(controllerChains.getPanel());
                shaper.getjTabbedPane().setSelectedIndex(shaper.getjTabbedPane().getTabCount() - 1);

            } else {

                shaper.getjTabbedPane().setSelectedIndex(tabNum);

            }

        } catch (Exception ex) {
            NOTIFICATIONS.error("Unexpected error", ex);
        }

    } // end manageChains   

    //==========================================================================
    private void managePipes() {

        int tabNum = 0;
        controllerPipes = new ControllerPipes(collector);
        controllerPipes.setupInterface();

        try {

            tabNum = JTabPaneUtilities.getComponentNum(shaper.getjTabbedPane(), "manage pipes");

            if (tabNum == -1) {

                shaper.getjTabbedPane().add(controllerPipes.getPanel());
                shaper.getjTabbedPane().setSelectedIndex(shaper.getjTabbedPane().getTabCount() - 1);

            } else {
                shaper.getjTabbedPane().setSelectedIndex(tabNum);
            }

        } catch (Exception ex) {
            NOTIFICATIONS.error("Unexpected error", ex);
        }

    } // end managePipes

    //==========================================================================
    private void manageFilters() {

        int tabNum = 0;
        controllerFilters = new ControllerFilters(collector);
        controllerFilters.setupInterface();

        try {

            tabNum = JTabPaneUtilities.getComponentNum(shaper.getjTabbedPane(), "manage filters");

            if (tabNum == -1) {

                shaper.getjTabbedPane().add(controllerFilters.getPanel());
                shaper.getjTabbedPane().setSelectedIndex(shaper.getjTabbedPane().getTabCount() - 1);

            } else {
                shaper.getjTabbedPane().setSelectedIndex(tabNum);
            }

        } catch (Exception ex) {
            NOTIFICATIONS.error("Unexpected error", ex);
        }

    } // end manageFilters

    //==========================================================================
    private void settingTargets() {

        int tabNum = 0;
        controllerTargets = new ControllerTargets(collector);
        controllerTargets.setupInterface();

        try {

            tabNum = JTabPaneUtilities.getComponentNum(shaper.getjTabbedPane(), "manage targets");

            if (tabNum == -1) {

                shaper.getjTabbedPane().add(controllerTargets.getPanel());
                shaper.getjTabbedPane().setSelectedIndex(shaper.getjTabbedPane().getTabCount() - 1);

            } else {
                shaper.getjTabbedPane().setSelectedIndex(tabNum);
            }

        } catch (Exception ex) {
            NOTIFICATIONS.error("Unexpected error", ex);
        }

    } // end settingTargets

    //==========================================================================
    private void settingPorts() {

        int tabNum = 0;
        controllerPorts = new ControllerPorts(collector);
        controllerPorts.setupInterface();

        try {

            tabNum = JTabPaneUtilities.getComponentNum(shaper.getjTabbedPane(), "manage ports");

            if (tabNum == -1) {

                shaper.getjTabbedPane().add(controllerPorts.getPanel());
                shaper.getjTabbedPane().setSelectedIndex(shaper.getjTabbedPane().getTabCount() - 1);

            } else {
                shaper.getjTabbedPane().setSelectedIndex(tabNum);
            }

        } catch (Exception ex) {
            NOTIFICATIONS.error("Unexpected error", ex);
        }

    } // end settingTargets

    //==========================================================================
    private void settingProtocols() {

        int tabNum = 0;
        controllerProtocols = new ControllerProtocols(collector);
        controllerProtocols.setupInterface();

        try {

            tabNum = JTabPaneUtilities.getComponentNum(shaper.getjTabbedPane(), "manage protocols");

            if (tabNum == -1) {

                shaper.getjTabbedPane().add(controllerProtocols.getPanel());
                shaper.getjTabbedPane().setSelectedIndex(shaper.getjTabbedPane().getTabCount() - 1);

            } else {
                shaper.getjTabbedPane().setSelectedIndex(tabNum);
            }

        } catch (Exception ex) {
            NOTIFICATIONS.error("Unexpected error", ex);
        }

    } // end settingProtocols

    //==========================================================================
    private void settingServiceLevel() {

        int tabNum = 0;
        controllerServiceLevel = new ControllerServiceLevel(collector);
        controllerServiceLevel.setupInterface();

        try {

            tabNum = JTabPaneUtilities.getComponentNum(shaper.getjTabbedPane(), "manage service level");

            if (tabNum == -1) {

                shaper.getjTabbedPane().add(controllerServiceLevel.getPanel());
                shaper.getjTabbedPane().setSelectedIndex(shaper.getjTabbedPane().getTabCount() - 1);

            } else {
                shaper.getjTabbedPane().setSelectedIndex(tabNum);
            }

        } catch (Exception ex) {
            NOTIFICATIONS.error("Unexpected error", ex);
        }

    } // end settingServiceLevel

    //==========================================================================
    private void settingOptions() {
        ControllerOptions controllerOptions = new ControllerOptions(collector);
        controllerOptions.setupInterface();
        controllerOptions.setVisible(true);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        shaper.setVisible(true);
    } // end setVisible

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end destroyCurrentThread
    
} // end class
