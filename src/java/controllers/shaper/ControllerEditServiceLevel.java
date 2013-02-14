package controllers.shaper;

import controllers.Controller;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.SwingWorker;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.ViewUtilities;
import views.dialogs.ServiceLevel;


/**
 *
 * @author skuarch
 */
public class ControllerEditServiceLevel extends Controller {

    private ServiceLevel serviceLevel = null;
    private String collector;
    private ControllerServiceLevel controllerServiceLevel;
    private HashMap hm = null;
    private String id = null;

    //==========================================================================
    public ControllerEditServiceLevel(String collector, String id, ControllerServiceLevel controllerServiceLevel) {
        serviceLevel = new ServiceLevel(null, true);
        hm = HashMapUtilities.getHashMapShaper();
        this.collector = collector;
        this.controllerServiceLevel = controllerServiceLevel;
        this.id = id;
    } // end ControllerAddServiceLevel

    //==========================================================================
    @Override
    public void setupInterface() {

        serviceLevel.setTitle("Edd Service Level");
        setEnabledComponents(false);
        serviceLevel.getjProgressBar().setIndeterminate(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                ArrayList arrayList = null;

                try {

                    serviceLevel.getjButton1().setText("close");
                    serviceLevel.getjButton2().setText("edit");

                    serviceLevel.getjPanelHTB().setVisible(false);
                    serviceLevel.getjPanelCBQ().setVisible(false);
                    serviceLevel.getjPanelHFSC().setVisible(false);
                    serviceLevel.getjPanelESFQ().setVisible(false);
                    serviceLevel.getjPanelNETEM().setVisible(false);
                    serviceLevel.setSize(new Dimension(590, 510));

                    addListeners();

                    hm.put("collector", collector);
                    hm.put("id", id);
                    hm.put("request", "get data service level");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    if (arrayList == null) {
                        return null;
                    }

                    serviceLevel.getjTextFieldName().setText(arrayList.get(0).toString());
                    ViewUtilities.fillJComboBox(serviceLevel.getjComboBoxClasifier(), (Object[]) arrayList.get(1));
                    serviceLevel.getjTextFieldInBandwidth().setText(arrayList.get(2).toString());
                    serviceLevel.getjTextFieldInBandwidthCeil().setText(arrayList.get(3).toString());
                    serviceLevel.getjTextFieldInBandwidthBurst().setText(arrayList.get(4).toString());
                    serviceLevel.getjTextFieldOutBandwidth().setText(arrayList.get(5).toString());
                    serviceLevel.getjTextFieldOutBandwidthCeil().setText(arrayList.get(6).toString());
                    serviceLevel.getjTextFieldOutBandwidthBurst().setText(arrayList.get(7).toString());
                    ViewUtilities.fillJComboBox(serviceLevel.getjComboBoxPriority(), (Object[]) arrayList.get(8));

                    serviceLevel.getjTextFieldCBQInBandwidth().setText(arrayList.get(9).toString());
                    ViewUtilities.fillJComboBox(serviceLevel.getjComboBoxInPriority(), (Object[]) arrayList.get(10));

                    serviceLevel.getjTextFieldCBQOutBandwidth().setText(arrayList.get(11).toString());
                    ViewUtilities.fillJComboBox(serviceLevel.getjComboBoxOutPriority(), (Object[]) arrayList.get(12));

                    if (arrayList.get(13).equals(true)) {
                        serviceLevel.getjRadioButtonBoundedYes().setSelected(true);
                    } else {
                        serviceLevel.getjRadioButtonBoundedNo().setSelected(true);
                    }

                    serviceLevel.getjTextFieldInWorkUnit().setText(arrayList.get(14).toString());
                    serviceLevel.getjTextFieldInMaxDelay().setText(arrayList.get(15).toString());
                    serviceLevel.getjTextFieldInulRate().setText(arrayList.get(16).toString());
                    serviceLevel.getjTextFieldHFSCInulRate().setText(arrayList.get(17).toString());
                    serviceLevel.getjTextFieldOutWorkUnit().setText(arrayList.get(18).toString());
                    serviceLevel.getjTextFieldOutMaxDelay().setText(arrayList.get(19).toString());
                    serviceLevel.getjTextFieldOutRate().setText(arrayList.get(20).toString());
                    serviceLevel.getjTextFieldOutulRate().setText(arrayList.get(21).toString());


                    ViewUtilities.fillJComboBox(serviceLevel.getjComboBoxQueuingDiscipline(), (Object[]) arrayList.get(22));
                    serviceLevel.getjTextFieldPerturb().setText(arrayList.get(23).toString());

                    serviceLevel.getjTextFieldLimit().setText(arrayList.get(24).toString());
                    serviceLevel.getjTextFieldDepth().setText(arrayList.get(25).toString());
                    serviceLevel.getjTextFieldDivisor().setText(arrayList.get(26).toString());
                    ViewUtilities.fillJComboBox(serviceLevel.getjComboBoxHash(), (Object[]) arrayList.get(27));
                    serviceLevel.getjTextFieldDelay().setText(arrayList.get(28).toString());
                    serviceLevel.getjTextFieldJitter().setText(arrayList.get(29).toString());
                    serviceLevel.getjTextFieldCorrelation().setText(arrayList.get(30).toString());
                    ViewUtilities.fillJComboBox(serviceLevel.getjComboBoxDistribution(), (Object[]) arrayList.get(31));
                    serviceLevel.getjTextFieldPacketloss().setText(arrayList.get(32).toString());
                    serviceLevel.getjTextFieldDuplication().setText(arrayList.get(33).toString());


                    serviceLevel.getjTextFieldGap().setText(arrayList.get(34).toString());
                    serviceLevel.getjTextFieldReorderPercentage().setText(arrayList.get(35).toString());
                    serviceLevel.getjTextFieldReorderCorrelation().setText(arrayList.get(36).toString());


                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    setEnabledComponents(true);
                    serviceLevel.getjProgressBar().setIndeterminate(false);
                }

                return null;
            }
        }.execute();

    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        //save -----------------------------------------------------------------
        serviceLevel.getjButton2().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });

        //close ----------------------------------------------------------------
        serviceLevel.getjButton1().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                serviceLevel.setVisible(false);
            }
        });

        //Clasifier-------------------------------------------------------------
        serviceLevel.getjComboBoxClasifier().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                changeBoxClasifier();
            }
        });

        //QueuingDiscipline-----------------------------------------------------
        serviceLevel.getjComboBoxQueuingDiscipline().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                changeBoxQueuingDiscipline();
            }
        });

    } // end addListeners   

    //==========================================================================
    private void add() {
        
        setEnabledComponents(false);
        serviceLevel.getjProgressBar().setIndeterminate(true);

        new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                
                ArrayList data = new ArrayList();
                String message = null;

                try {

                    data.add(id);
                    
                    data.add(serviceLevel.getjTextFieldName().getText());
                    data.add(serviceLevel.getjComboBoxClasifier().getModel().getSelectedItem().toString());
                    data.add(serviceLevel.getjTextFieldInBandwidth().getText());
                    data.add(serviceLevel.getjTextFieldInBandwidthCeil().getText());
                    data.add(serviceLevel.getjTextFieldInBandwidthBurst().getText());
                    data.add(serviceLevel.getjTextFieldOutBandwidth().getText());
                    data.add(serviceLevel.getjTextFieldOutBandwidthCeil().getText());
                    data.add(serviceLevel.getjTextFieldOutBandwidthBurst().getText());
                    data.add(serviceLevel.getjComboBoxPriority().getModel().getSelectedItem().toString());

                    data.add(serviceLevel.getjTextFieldCBQInBandwidth().getText());
                    data.add(serviceLevel.getjComboBoxInPriority().getModel().getSelectedItem().toString());

                    data.add(serviceLevel.getjTextFieldCBQOutBandwidth().getText());
                    data.add(serviceLevel.getjComboBoxOutPriority().getModel().getSelectedItem().toString());

                    data.add(serviceLevel.getjRadioButtonBoundedYes().isSelected());

                    data.add(serviceLevel.getjTextFieldInWorkUnit().getText());
                    data.add(serviceLevel.getjTextFieldInMaxDelay().getText());
                    data.add(serviceLevel.getjTextFieldInulRate().getText());
                    data.add(serviceLevel.getjTextFieldHFSCInulRate().getText());
                    data.add(serviceLevel.getjTextFieldOutWorkUnit().getText());
                    data.add(serviceLevel.getjTextFieldOutMaxDelay().getText());
                    data.add(serviceLevel.getjTextFieldOutRate().getText());
                    data.add(serviceLevel.getjTextFieldOutulRate().getText());

                    data.add(serviceLevel.getjComboBoxQueuingDiscipline().getModel().getSelectedItem().toString());
                    data.add(serviceLevel.getjTextFieldPerturb().getText());

                    data.add(serviceLevel.getjTextFieldLimit().getText());
                    data.add(serviceLevel.getjTextFieldDepth().getText());
                    data.add(serviceLevel.getjTextFieldDivisor().getText());
                    data.add(serviceLevel.getjComboBoxHash().getModel().getSelectedItem().toString());
                    data.add(serviceLevel.getjTextFieldDelay().getText());
                    data.add(serviceLevel.getjTextFieldJitter().getText());
                    data.add(serviceLevel.getjTextFieldCorrelation().getText());
                    data.add(serviceLevel.getjComboBoxDistribution().getModel().getSelectedItem().toString());
                    data.add(serviceLevel.getjTextFieldPacketloss().getText());
                    data.add(serviceLevel.getjTextFieldDuplication().getText());

                    data.add(serviceLevel.getjTextFieldGap().getText());
                    data.add(serviceLevel.getjTextFieldReorderPercentage().getText());
                    data.add(serviceLevel.getjTextFieldReorderCorrelation().getText());

                    hm.put("data", data);
                    hm.put("collector", collector);
                    hm.put("request", "edit service level");
                    
                    message = (String) new Linker().sendReceiveObject(hm);

                    controllerServiceLevel.reloadTable();
                    NOTIFICATIONS.information(message, true);

                    setVisible(false);

                } catch (Exception e) {
                    throw e;
                } finally {
                    setEnabledComponents(true);
                    serviceLevel.getjProgressBar().setIndeterminate(false);
                }

                return null;
            }
        }.execute();
        
        
    } // end add

    //==========================================================================
    private void changeBoxQueuingDiscipline() {

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                String selected = null;

                try {

                    selected = serviceLevel.getjComboBoxQueuingDiscipline().getModel().getSelectedItem().toString();                    

                    serviceLevel.getjPanelNETEM().setVisible(false);
                    serviceLevel.getjPanelESFQ().setVisible(false);

                    if (selected.equalsIgnoreCase("ESFQ")) {
                        serviceLevel.getjPanelESFQ().setVisible(true);
                        serviceLevel.repaint();
                        return null;
                    }

                    if (selected.equalsIgnoreCase("NETEM")) {
                        serviceLevel.getjPanelNETEM().setVisible(true);
                        serviceLevel.repaint();
                        return null;
                    }

                } catch (Exception e) {
                    NOTIFICATIONS.error(e.getMessage(), e);
                }

                return null;
            }
        }.execute();

    } // end BoxQueuingDiscipline

    //==========================================================================
    private void changeBoxClasifier() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                String selected = null;

                try {

                    selected = serviceLevel.getjComboBoxClasifier().getModel().getSelectedItem().toString();                    

                    serviceLevel.getjPanelHTB().setVisible(false);
                    serviceLevel.getjPanelCBQ().setVisible(false);
                    serviceLevel.getjPanelHFSC().setVisible(false);

                    if (selected.equalsIgnoreCase("HTB")) {
                        serviceLevel.getjPanelHTB().setVisible(true);
                        serviceLevel.repaint();
                        return null;
                    }

                    if (selected.equalsIgnoreCase("CBQ")) {
                        serviceLevel.getjPanelCBQ().setVisible(true);
                        serviceLevel.repaint();
                        return null;
                    }

                    if (selected.equalsIgnoreCase("HFSC")) {
                        serviceLevel.getjPanelHFSC().setVisible(true);
                        serviceLevel.repaint();
                        return null;
                    }



                } catch (Exception e) {
                    NOTIFICATIONS.error(e.getMessage(), e);
                }

                return null;
            }
        }.execute();
    } // end changeBoxClasifier

    //==========================================================================    
    public void setEnabledComponents(boolean flag) {
        serviceLevel.getjButton2().setEnabled(flag);
    }

    //==========================================================================
    @Override
    public void setVisible(final boolean flag) {
        serviceLevel.setVisible(flag);
    } // end setVisible

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end destroyCurrentThread
    
} // end class
