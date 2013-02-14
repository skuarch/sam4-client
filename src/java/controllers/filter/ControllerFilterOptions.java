package controllers.filter;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.SwingWorker;
import model.net.Linker;
import model.util.HashMapUtilities;
import views.dialogs.FilterOptions;

/**
 *
 * @author skuarch
 */
public class ControllerFilterOptions extends Controller {

    private FilterOptions filterOptions = null;
    private HashMap hm = null;
    private String collector = null;

    //==========================================================================
    private ControllerFilterOptions() {
        filterOptions = new FilterOptions(null, true);
        hm = HashMapUtilities.getHashMapFilter();
    }

    //==========================================================================
    public static ControllerFilterOptions getInstance() {
        return ControllerFilterOptions.ControllerFilterOptionsHolder.INSTANCE;
    }

    //==========================================================================
    private static class ControllerFilterOptionsHolder {

        private static final ControllerFilterOptions INSTANCE = new ControllerFilterOptions();
    }

    //==========================================================================
    public void setColletor(String collector) {
        this.collector = collector;
    }

    //==========================================================================
    @Override
    public void setupInterface() {

        filterOptions.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                ArrayList arrayList = null;

                try {

                    if (collector == null) {
                        NOTIFICATIONS.warning("please select a collector", true);
                        setVisible(false);
                        return null;
                    }

                    addListeners();

                    filterOptions.getjLabelCollector().setText(collector);

                    hm.put("collector", collector);
                    hm.put("request", "get data filter options");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);
                    System.out.println("el array " + arrayList);
                    if (arrayList == null) {
                        setVisible(false);
                        return null;
                    }

                    if (arrayList.get(0).equals("false")) {
                        filterOptions.getjRadioButtonNo().setSelected(true);
                    } else {
                        filterOptions.getjRadioButtonYes().setSelected(true);
                    }

                    setEnabledComponents(true);

                    filterOptions.getjTextFieldUrl().setText(arrayList.get(1).toString());

                    //days
                    if ("5".equalsIgnoreCase(arrayList.get(2).toString())) {
                        filterOptions.getjRadioButton5().setSelected(true);
                    } else if ("10".equalsIgnoreCase(arrayList.get(2).toString())) {
                        filterOptions.getjRadioButton10().setSelected(true);
                    } else if ("20".equalsIgnoreCase(arrayList.get(2).toString())) {
                        filterOptions.getjRadioButton20().setSelected(true);
                    } else if ("30".equalsIgnoreCase(arrayList.get(2).toString())) {
                        filterOptions.getjRadioButton30().setSelected(true);
                    }

                    //hours
                    filterOptions.getjComboBoxHours().setSelectedItem((Object) arrayList.get(3));

                    filterOptions.getjLabelUpdated().setText(arrayList.get(4).toString());

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    filterOptions.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

                return null;
            }
        }.execute();

    }

    //==========================================================================
    private void addListeners() {

        filterOptions.getjButtonSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

    } // end addListeners

    //==========================================================================
    private void save() {

        filterOptions.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                ArrayList data = new ArrayList();
                String message = null;

                try {

                    if (!filterOptions.getjRadioButtonYes().isSelected()) {
                        setVisible(false);
                        return null;
                    }

                    data.add(filterOptions.getjRadioButtonYes().isSelected());
                    data.add(filterOptions.getjTextFieldUrl().getText());

                    //days
                    if (filterOptions.getjRadioButton5().isSelected()) {
                        data.add("5");
                    } else if (filterOptions.getjRadioButton10().isSelected()) {
                        data.add("10");
                    } else if (filterOptions.getjRadioButton20().isSelected()) {
                        data.add("20");
                    } else if (filterOptions.getjRadioButton30().isSelected()) {
                        data.add("30");
                    }

                    data.add(filterOptions.getjComboBoxHours().getModel().getSelectedItem().toString());

                    hm.put("collector", collector);
                    hm.put("request", "save options filter");
                    hm.put("data", data);
                    message = (String) new Linker().sendReceiveObject(hm);

                    NOTIFICATIONS.information(message, true);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Error saving the information", e);
                    return null;
                } finally {
                    filterOptions.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

                return null;
            }
        }.execute();

    } // end save

    //==========================================================================
    private void setEnabledComponents(boolean flag) {
        filterOptions.getjComboBoxHours().setEnabled(flag);
        filterOptions.getjButtonSave().setEnabled(flag);
        filterOptions.getjRadioButton5().setEnabled(flag);
        filterOptions.getjRadioButton10().setEnabled(flag);
        filterOptions.getjRadioButton20().setEnabled(flag);
        filterOptions.getjRadioButton30().setEnabled(flag);
        filterOptions.getjRadioButtonYes().setEnabled(flag);
        filterOptions.getjRadioButtonNo().setEnabled(flag);
        filterOptions.getjTextFieldUrl().setEnabled(flag);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        filterOptions.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class