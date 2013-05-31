package controllers.shaper;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.SwingWorker;
import model.beans.CurrentUser;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.ViewUtilities;
import views.dialogs.ShaperOptions;

/**
 *
 * @author skuarch
 */
public class ControllerOptions extends Controller {

    private ShaperOptions shaperOptions = null;
    private HashMap hm = null;
    private String collector = null;

    //==========================================================================
    public ControllerOptions(String collector) {
        shaperOptions = new ShaperOptions(null, true);
        hm = HashMapUtilities.getHashMapShaper();
        this.collector = collector;
    } // end ControllerOptions

    //==========================================================================
    @Override
    public void setupInterface() {

        setEnabledComponents(false);
        shaperOptions.getjProgressBar().setIndeterminate(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                ArrayList arrayList = null;

                try {

                    addListeners();

                    hm.put("collector", collector);
                    hm.put("request", "get initial data options");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    if (arrayList == null) {
                        return null;
                    }

                    shaperOptions.getjTextFieldInboundBandwidth().setText(arrayList.get(0).toString());
                    shaperOptions.getjTextFieldOutboundBandwidth().setText(arrayList.get(1).toString());

                    shaperOptions.getjTextFieldIncomingInterface().setText(arrayList.get(2).toString());
                    shaperOptions.getjTextFieldOutgoingInterface().setText(arrayList.get(3).toString());

                    if (arrayList.get(4).equals(true)) {
                        shaperOptions.getjRadioButtonIMQYes().setSelected(true);
                    } else {
                        shaperOptions.getjRadioButtonIMQNo().setSelected(true);
                    }

                    ViewUtilities.fillJComboBox(shaperOptions.getjComboBoxACKPackets(), (Object[]) arrayList.get(5));
                    ViewUtilities.fillJComboBox(shaperOptions.getjComboBoxClassifier(), (Object[]) arrayList.get(6));
                    ViewUtilities.fillJComboBox(shaperOptions.getjComboBoxDefaultQueuingDiscipline(), (Object[]) arrayList.get(7));

                    shaperOptions.getjTextFieldESFQPerturb().setText(arrayList.get(8).toString());
                    shaperOptions.getjTextFieldESFQLimit().setText(arrayList.get(9).toString());
                    shaperOptions.getjTextFieldESFQDepth().setText(arrayList.get(10).toString());
                    shaperOptions.getjTextFieldESFQDivisor().setText(arrayList.get(11).toString());

                    ViewUtilities.fillJComboBox(shaperOptions.getjComboBoxESFQHash(), (Object[]) arrayList.get(12));


                    if (arrayList.get(13).equals(true)) {
                        shaperOptions.getjRadioButtonTCFilter().setSelected(true);
                    } else {
                        shaperOptions.getjRadioButtonIptables().setSelected(true);
                    }

                    if (arrayList.get(14).equals(true)) {
                        shaperOptions.getjRadioButtonRouter().setSelected(true);
                    } else {
                        shaperOptions.getjRadioButtonBridge().setSelected(true);
                    }

                } catch (Exception e) {
                    NOTIFICATIONS.error(e.getMessage(), e);
                } finally {
                    setEnabledComponents(true);
                    shaperOptions.getjProgressBar().setIndeterminate(false);
                     if (CurrentUser.getInstance().getLevel() == 0) {
                        shaperOptions.getjButton2().setEnabled(false);                        
                    }
                }

                return null;
            }
        }.execute();

    } // end setupInterface
    
    //==========================================================================
    private void addListeners(){
        // close ---------------------------------------------------------------
        shaperOptions.getjButton1().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    shaperOptions.setVisible(false);
                } catch (Exception ex) {
                    NOTIFICATIONS.error(ex.getMessage(), ex);
                }
            }
        });

        //create ---------------------------------------------------------------
        shaperOptions.getjButton2().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {

                    edit();
                } catch (Exception ex) {
                    NOTIFICATIONS.error(ex.getMessage(), ex);
                }

            }
        });

        //change combo
        shaperOptions.getjComboBoxDefaultQueuingDiscipline().addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                changeComboDefaultQueuingDiscipline();
            }
        });
    }
    
    //==========================================================================
    private void edit(){
    
        setEnabledComponents(true);
        shaperOptions.getjProgressBar().setIndeterminate(true);

        new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                
                ArrayList data = new ArrayList();
                int trafficFilter = 1;
                int mode = 1;
                String message = null;

                try {

                    data.add(shaperOptions.getjTextFieldInboundBandwidth().getText());
                    data.add(shaperOptions.getjTextFieldOutboundBandwidth().getText());

                    data.add(shaperOptions.getjTextFieldIncomingInterface().getText());
                    data.add(shaperOptions.getjTextFieldOutgoingInterface().getText());

                    data.add(shaperOptions.getjRadioButtonIMQYes().isSelected());

                    data.add(shaperOptions.getjComboBoxACKPackets().getModel().getSelectedItem());
                    data.add(shaperOptions.getjComboBoxClassifier().getModel().getSelectedItem());
                    data.add(shaperOptions.getjComboBoxDefaultQueuingDiscipline().getModel().getSelectedItem());

                    data.add(shaperOptions.getjTextFieldESFQPerturb().getText());
                    data.add(shaperOptions.getjTextFieldESFQLimit().getText());
                    data.add(shaperOptions.getjTextFieldESFQDepth().getText());
                    data.add(shaperOptions.getjTextFieldESFQDivisor().getText());
                    data.add(shaperOptions.getjComboBoxESFQHash().getModel().getSelectedItem().toString());

                    if (shaperOptions.getjRadioButtonTCFilter().isSelected()) {
                        trafficFilter = 0;
                    }

                    if (shaperOptions.getjRadioButtonRouter().isSelected()) {
                        mode = 0;
                    }

                    data.add(trafficFilter);
                    data.add(mode);
                    
                    hm.put("request", "edit options");
                    hm.put("collector", collector);                    
                    hm.put("data", data);   
                    
                    message = (String) new Linker().sendReceiveObject(hm);

                    NOTIFICATIONS.information(message, true);
                    
                } catch (Exception e) {
                    NOTIFICATIONS.error(e.getMessage(), e);
                } finally {
                    setEnabledComponents(true);
                    shaperOptions.getjProgressBar().setIndeterminate(false);
                    shaperOptions.setVisible(false);
                }

                return null;
            }
        }.execute();
        
    }
    
    //==========================================================================
    private void changeComboDefaultQueuingDiscipline() {

        String selected = null;

        try {

            selected = shaperOptions.getjComboBoxDefaultQueuingDiscipline().getModel().getSelectedItem().toString();
            
            if (selected.equalsIgnoreCase("ESFQ")) {
                setEnableESFQComponents(true);
            } else {
                setEnableESFQComponents(false);
            }

        } catch (Exception e) {
            NOTIFICATIONS.error(e.getMessage(), e);
        }
    }
    
    //==========================================================================
    private void setEnableESFQComponents(boolean flag) {

        shaperOptions.getjComboBoxESFQHash().setEnabled(flag);
        shaperOptions.getjTextFieldESFQDepth().setEnabled(flag);
        shaperOptions.getjTextFieldESFQDivisor().setEnabled(flag);
        shaperOptions.getjTextFieldESFQLimit().setEnabled(flag);
        shaperOptions.getjTextFieldESFQPerturb().setEnabled(flag);

    }


    //==========================================================================
    private void setEnabledComponents(boolean flag) {
        shaperOptions.getjButton2().setEnabled(flag);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        shaperOptions.setVisible(flag);
    } // end setVisible

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end destroyCurrentThread
    
} // end class
