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
public class ControllerAddFilter extends Controller {

    private ControllerFilters controllerFilters = null;
    private Filter filter = null;
    private String collector = null;
    private HashMap hm = null;

    //==========================================================================
    public ControllerAddFilter(String collector, ControllerFilters controllerFilters) {
        filter = new Filter(null, true);
        this.controllerFilters = controllerFilters;
        this.collector = collector;
        hm = HashMapUtilities.getHashMapShaper();
    }

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
                    
                    //basic configuration 
                    filter.getjButtonClose().setText("close");
                    filter.getjButtonClose().setIcon(new ImageIcon(getClass().getResource("/views/images/shutdown-mini.png")));
                    filter.getjButtonSave().setText("add");
                    filter.getjButtonSave().setIcon(new ImageIcon(getClass().getResource("/views/images/add.gif")));
                    
                    addListeners();

                    //data
                    hm.put("collector", collector);
                    hm.put("request", "get default data filters");                    
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    status = (String) arrayList.get(0);
                    protocols = (String[]) arrayList.get(1);
                    unused = (String[]) arrayList.get(2);
                    used = (String[]) arrayList.get(3);
                    tos = (String[]) arrayList.get(4);
                    source = (String[]) arrayList.get(5);
                    direction = (String[]) arrayList.get(6);
                    destination = (String[]) arrayList.get(7);

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

        filter.getjButtonClose().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filter.setVisible(false);
            }
        });

        filter.getjButtonSave().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    add();
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
    }

    //==========================================================================
    private void add() {

        filter.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                String message = null;
                ArrayList data = new ArrayList();

                try {

                    data.add(null);
                    data.add(filter.getjTextFieldName().getText());
                    data.add(filter.getjRadioButtonActive().isSelected());
                    data.add(filter.getjComboBoxProtocols().getModel().getSelectedItem());
                    data.add(ViewUtilities.getDataJList(filter.getjListUnused()));
                    data.add(ViewUtilities.getDataJList(filter.getjListUsed()));
                    data.add(filter.getjComboBoxTos().getModel().getSelectedItem());
                    data.add(filter.getjComboBoxSource().getModel().getSelectedItem());
                    data.add(filter.getjComboBoxDirection().getModel().getSelectedItem());
                    data.add(filter.getjComboBoxDestination().getModel().getSelectedItem());

                    hm.put("collector", collector);
                    hm.put("request", "save filters");
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

    } // end add

    //==========================================================================    
    public void setEnabledComponents(boolean flag) {
        filter.getjButtonSave().setEnabled(flag);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        filter.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class