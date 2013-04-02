package controllers.shaper;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.Thrower;
import model.util.ViewUtilities;
import views.dialogs.Filter;

/**
 *
 * @author skuarch
 */
public class ControllerEditFilter extends Controller {

    private Filter filter = null;
    private HashMap hm = null;
    private String collector = null;
    private String id = null;
    private ControllerFilters controllerFilters = null;

    //==========================================================================
    public ControllerEditFilter(String collector, String id, ControllerFilters controllerFilters) {
        filter = new Filter(null, true);
        hm = HashMapUtilities.getHashMapShaper();
        this.collector = collector;
        this.id = id;
        this.controllerFilters = controllerFilters;
    } // end ControllerEditFilter

    //==========================================================================
    @Override
    public void setupInterface() {

        filter.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList arrayList = null;
                String status = null;
                String[] protocols = null;
                String[] unused = null;
                String[] used = null;
                String[] tos = null;
                String[] source = null;
                String[] direction = null;
                String[] destination = null;

                try {

                    filter.getjButtonClose().setText("close");
                    filter.getjButtonClose().setIcon(new ImageIcon(getClass().getResource("/views/images/shutdown-mini.png")));
                    filter.getjButtonSave().setText("edit");
                    filter.getjButtonSave().setIcon(new ImageIcon(getClass().getResource("/views/images/edit.png")));

                    addListeners();

                    hm.put("id", id);
                    hm.put("collector", collector);
                    hm.put("request", "get data filters");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    if (arrayList == null) {
                        return;
                    }

                    filter.getjTextFieldName().setText((String) arrayList.get(0));

                    status = (String) arrayList.get(1);
                    protocols = (String[]) arrayList.get(2);
                    unused = (String[]) arrayList.get(3);
                    used = (String[]) arrayList.get(4);
                    tos = (String[]) arrayList.get(5);
                    source = (String[]) arrayList.get(6);
                    direction = (String[]) arrayList.get(7);
                    destination = (String[]) arrayList.get(8);

                    if (status.equalsIgnoreCase("true")) {
                        filter.getjRadioButtonActive().setSelected(true);
                    }

                    if (status.equalsIgnoreCase("false")) {
                        filter.getjRadioButtonInactive().setSelected(true);
                    }

                    filter.getjComboBoxProtocols().removeAllItems();
                    for (String string : protocols) {
                        filter.getjComboBoxProtocols().addItem(string);
                    }

                    ViewUtilities.fillJlist(filter.getjListUnused(), unused);

                    ViewUtilities.fillJlist(filter.getjListUsed(), used);

                    filter.getjComboBoxTos().removeAllItems();
                    for (String string : tos) {
                        filter.getjComboBoxTos().addItem(string);
                    }

                    filter.getjComboBoxSource().removeAllItems();
                    for (String string : source) {
                        filter.getjComboBoxSource().addItem(string);
                    }

                    filter.getjComboBoxDirection().removeAllItems();
                    for (String string : direction) {
                        filter.getjComboBoxDirection().addItem(string);
                    }

                    filter.getjComboBoxDestination().removeAllItems();
                    for (String string : destination) {
                        filter.getjComboBoxDestination().addItem(string);
                    }

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    filter.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

            }
        }).start();

    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        try {

            filter.getjButtonClose().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    filter.setVisible(false);
                }
            });

            filter.getjButtonSave().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    try {
                        edit();
                    } catch (Exception ex) {
                        new Thrower().exception(ex);
                    }

                }
            });

            filter.getjButtonLeft().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    try {
                        ViewUtilities.passItemsLeftRigth(filter.getjListUsed(), filter.getjListUnused());
                    } catch (Exception ex) {
                        new Thrower().exception(ex);
                    }
                }
            });

            filter.getjButtonRight().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        ViewUtilities.passItemsLeftRigth(filter.getjListUnused(), filter.getjListUsed());
                    } catch (Exception ex) {
                        new Thrower().exception(ex);
                    }

                }
            });


        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    }

    //==========================================================================
    private void edit() {

        filter.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList data = new ArrayList();
                String message = null;

                try {

                    if (validate()) {

                        data.add(id);
                        data.add(filter.getjTextFieldName().getText());
                        data.add(filter.getjRadioButtonActive().isSelected());
                        data.add(filter.getjComboBoxProtocols().getModel().getSelectedItem());
                        data.add(ViewUtilities.getDataJList(filter.getjListUnused()));
                        data.add(ViewUtilities.getDataJList(filter.getjListUsed()));
                        data.add(filter.getjComboBoxTos().getModel().getSelectedItem());
                        data.add(filter.getjComboBoxSource().getModel().getSelectedItem());
                        data.add(filter.getjComboBoxDirection().getModel().getSelectedItem());
                        data.add(filter.getjComboBoxDestination().getModel().getSelectedItem());

                    }

                    hm.put("id", id);
                    hm.put("collector", collector);
                    hm.put("request", "edit filters");
                    hm.put("data", data);

                    message = (String) new Linker().sendReceiveObject(hm);

                    controllerFilters.reloadTable();
                    NOTIFICATIONS.information(message, true);

                    setVisible(false);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    filter.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

            }
        }).start();

    } // end edit

    //==========================================================================    
    public void setEnabledComponents(boolean flag) {
        filter.getjButtonSave().setEnabled(flag);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        filter.setVisible(flag);
    } // end setVisible

    //==========================================================================
    private boolean validate() throws Exception {

        boolean flag = false;
        String chainName = null;

        try {

            chainName = filter.getjTextFieldName().getText();

            if (chainName == null || chainName.length() < 1) {
                NOTIFICATIONS.error("the name of filter is empty", new Exception());
                return false;
            }

            flag = true;

        } catch (Exception e) {
            throw e;
        }

        return flag;

    } // end validate

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end destroyCurrentThread
} // end class
