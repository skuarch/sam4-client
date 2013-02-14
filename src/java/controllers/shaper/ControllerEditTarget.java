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
import views.dialogs.Target;

/**
 *
 * @author skuarch
 */
public class ControllerEditTarget extends Controller {

    private Target target = null;
    private HashMap hm = null;
    private String collector = null;
    private String id = null;
    private ControllerTargets controllerTargets = null;

    //==========================================================================
    public ControllerEditTarget(String collector, String id, ControllerTargets controllerTargets) {
        target = new Target(null, true);
        hm = HashMapUtilities.getHashMapShaper();
        this.collector = collector;
        this.id = id;
        this.controllerTargets = controllerTargets;
    } // end ControllerEditTarget

    //==========================================================================
    @Override
    public void setupInterface() {

        target.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList arrayList = null;

                try {

                    target.getjButton1().setText("close");
                    target.getjButton1().setIcon(new ImageIcon(getClass().getResource("/views/images/shutdown-mini.png")));
                    target.getjButton2().setText("edit");
                    target.getjButton2().setIcon(new ImageIcon(getClass().getResource("/views/images/edit.png")));

                    addListeners();

                    hm.put("id", id);
                    hm.put("collector", collector);
                    hm.put("request", "get data targets");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    if (arrayList == null) {
                        return;
                    }

                    target.getjTextFieldName().setText((String) arrayList.get(0));

                    target.getjTextFieldName().setText(arrayList.get(0).toString());
                    target.getjTextFieldIP().setText(arrayList.get(1).toString());
                    target.getjTextFieldMac().setText(arrayList.get(2).toString());

                    if (arrayList.get(3).equals("ip true")) {
                        target.getjRadioButtonIP().setSelected(true);
                    }

                    if (arrayList.get(3).equals("mac true")) {
                        target.getjRadioButtonMac().setSelected(true);
                    }

                    if (arrayList.get(3).equals("group true")) {
                        target.getjRadioButtonGroup().setSelected(true);
                    }

                    ViewUtilities.fillJlist(target.getjListGroupLeft(), (Object[]) arrayList.get(4));
                    ViewUtilities.fillJlist(target.getjListGroupRight(), (Object[]) arrayList.get(5));


                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    target.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

            }
        }).start();

    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        try {

            //------------------------------------------------------------------
            target.getjButton1().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });

            //------------------------------------------------------------------
            target.getjButton2().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    try {
                        edit();
                    } catch (Exception ex) {
                        NOTIFICATIONS.error(ex.getMessage(), ex);
                    }

                }
            });

            //left -------------------------------------------------------------
            target.getjButtonLeft().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    try {
                        ViewUtilities.passItemsLeftRigth(target.getjListGroupLeft(), target.getjListGroupRight());
                    } catch (Exception ex) {
                        new Thrower().exception(ex);
                    }

                }
            });

            //Right -----------------------------------------------------------------
            target.getjButtonRight().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    try {
                        ViewUtilities.passItemsLeftRigth(target.getjListGroupRight(), target.getjListGroupLeft());
                    } catch (Exception ex) {
                        NOTIFICATIONS.error(ex.getMessage(), ex);
                    }

                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end addListeners

    //==========================================================================
    private void edit() {

        target.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList data = new ArrayList();
                String message = null;

                try {

                    if (validate()) {

                        data.add(id);
                        data.add(target.getjTextFieldName().getText());

                        if (target.getjRadioButtonIP().isSelected()) {
                            data.add("ip true");
                        }

                        if (target.getjRadioButtonMac().isSelected()) {
                            data.add("mac true");
                        }


                        if (target.getjRadioButtonGroup().isSelected()) {
                            data.add("group true");
                        }

                        data.add(target.getjTextFieldIP().getText());
                        data.add(target.getjTextFieldMac().getText());
                        data.add(ViewUtilities.getDataJList(target.getjListGroupLeft()));
                        data.add(ViewUtilities.getDataJList(target.getjListGroupRight()));

                    }

                    hm.put("id", id);
                    hm.put("collector", collector);
                    hm.put("request", "edit targets");
                    hm.put("data", data);

                    message = (String) new Linker().sendReceiveObject(hm);

                    controllerTargets.reloadTable();
                    NOTIFICATIONS.information(message, true);

                    setVisible(false);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    target.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

            }
        }).start();

    } // end edit

    //==========================================================================    
    public void setEnabledComponents(boolean flag) {
        target.getjButton2().setEnabled(flag);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        target.setVisible(flag);
    } // end setVisible

    //==========================================================================
    private boolean validate() throws Exception {

        boolean flag = false;
        String chainName = null;

        try {

            chainName = target.getjTextFieldName().getText();

            if (chainName == null || chainName.length() < 1) {
                NOTIFICATIONS.error("the name of target is empty", new Exception());
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
