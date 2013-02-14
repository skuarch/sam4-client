package controllers.filter;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import model.net.Linker;
import model.util.HashMapUtilities;
import views.panels.BlackList;

/**
 *
 * @author skuarch
 */
public class ControllerBlackList extends Controller {

    private BlackList blackList = null;
    private HashMap hm = null;
    private String collector = null;
    private JProgressBar jProgressBar = null;

    public ControllerBlackList() {
        blackList = new BlackList();
        hm = HashMapUtilities.getHashMapFilter();
    }   
    
    //==========================================================================
    /*private ControllerBlackList() {
        blackList = new BlackList();
        hm = HashMapUtilities.getHashMapFilter();
    }

    //==========================================================================
    public static ControllerBlackList getInstance() {
        return ControllerBlackListHolder.INSTANCE;
    }
    
    //==========================================================================
    private static class ControllerBlackListHolder {

        private static final ControllerBlackList INSTANCE = new ControllerBlackList();
    }*/

    //==========================================================================
    public void setColletor(String collector) {
        this.collector = collector;
    }

    //==========================================================================
    @Override
    public void setupInterface() {

        jProgressBar.setIndeterminate(true);
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                ArrayList arrayList = null;

                try {

                    addListeners();

                    blackList.setName("Black List");
                    blackList.getjLabelCollector().setText(collector);

                    hm.put("collector", collector);
                    hm.put("request", "get data filter options");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    if (arrayList == null) {
                        return null;
                    }

                    if (arrayList.get(0).toString().equalsIgnoreCase("false") || arrayList.get(0).equals(false)) {
                        blackList.getjRadioButtonNo().setSelected(true);
                    } else {
                        blackList.getjRadioButtonYes().setSelected(true);
                    }

                    setEnabledComponents(true);

                    blackList.getjTextFieldUrl().setText(arrayList.get(1).toString());

                    //days
                    if ("5".equalsIgnoreCase(arrayList.get(2).toString())) {
                        blackList.getjRadioButton5().setSelected(true);
                    } else if ("10".equalsIgnoreCase(arrayList.get(2).toString())) {
                        blackList.getjRadioButton10().setSelected(true);
                    } else if ("20".equalsIgnoreCase(arrayList.get(2).toString())) {
                        blackList.getjRadioButton20().setSelected(true);
                    } else if ("30".equalsIgnoreCase(arrayList.get(2).toString())) {
                        blackList.getjRadioButton30().setSelected(true);
                    }

                    //hours
                    blackList.getjComboBoxHours().setSelectedItem((Object) arrayList.get(3));
                    blackList.getjComboBoxHours().getModel().setSelectedItem((Object) arrayList.get(3));

                    blackList.getjLabelUpdated().setText(arrayList.get(4).toString());

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    jProgressBar.setIndeterminate(false);
                }

                return null;
            }
        }.execute();

    }
    //==========================================================================

    private void addListeners() {

        blackList.getjButtonSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

    } // end addListeners

    //==========================================================================
    private void save() {

        jProgressBar.setIndeterminate(true);
        setEnabledComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                ArrayList data = new ArrayList();
                String message = null;

                try {                   

                    data.add(blackList.getjRadioButtonYes().isSelected());
                    data.add(blackList.getjTextFieldUrl().getText());

                    //days
                    if (blackList.getjRadioButton5().isSelected()) {
                        data.add("5");
                    } else if (blackList.getjRadioButton10().isSelected()) {
                        data.add("10");
                    } else if (blackList.getjRadioButton20().isSelected()) {
                        data.add("20");
                    } else if (blackList.getjRadioButton30().isSelected()) {
                        data.add("30");
                    }

                    data.add(blackList.getjComboBoxHours().getModel().getSelectedItem().toString());

                    hm.put("collector", collector);
                    hm.put("request", "save options filter");
                    hm.put("data", data);
                    message = (String) new Linker().sendReceiveObject(hm);

                    NOTIFICATIONS.information(message, true);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Error saving the information", e);
                    return null;
                } finally {
                    jProgressBar.setIndeterminate(false);
                    setEnabledComponents(true);
                }

                return null;
            }
        }.execute();

    } // end save

    //==========================================================================
    public void setJProgressBar(JProgressBar jProgressBar) {
        this.jProgressBar = jProgressBar;
    }

    //==========================================================================
    private void setEnabledComponents(boolean flag) {
        blackList.getjComboBoxHours().setEnabled(flag);
        blackList.getjButtonSave().setEnabled(flag);
        blackList.getjRadioButton5().setEnabled(flag);
        blackList.getjRadioButton10().setEnabled(flag);
        blackList.getjRadioButton20().setEnabled(flag);
        blackList.getjRadioButton30().setEnabled(flag);
        blackList.getjRadioButtonYes().setEnabled(flag);
        blackList.getjRadioButtonNo().setEnabled(flag);
        blackList.getjTextFieldUrl().setEnabled(flag);
    }

    //==========================================================================
    public JPanel getPanel() {
        return blackList;
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
} // end class
