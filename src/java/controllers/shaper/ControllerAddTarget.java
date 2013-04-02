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
import views.dialogs.Target;
import model.util.ViewUtilities;

/**
 *
 * @author skuarch
 */
public class ControllerAddTarget extends Controller {

    private ControllerTargets controllerTargets = null;
    private Target target = null;
    private String collector = null;
    private HashMap hm = null;

    //==========================================================================
    public ControllerAddTarget(String collector, ControllerTargets controllerTargets) {
        target = new Target(null, true);
        this.controllerTargets = controllerTargets;
        this.collector = collector;
        hm = HashMapUtilities.getHashMapShaper();
    }

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

                    //basic configuration 
                    target.getjButton1().setText("close");
                    target.getjButton1().setIcon(new ImageIcon(getClass().getResource("/views/images/shutdown-mini.png")));
                    target.getjButton2().setText("add");
                    target.getjButton2().setIcon(new ImageIcon(getClass().getResource("/views/images/add.gif")));

                    addListeners();

                    //data
                    hm.put("collector", collector);
                    hm.put("request", "get default data targets");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    ViewUtilities.fillJlist(target.getjListGroupLeft(), (Object[]) arrayList.get(0));
                    ViewUtilities.fillJlist(target.getjListGroupRight(), (Object[]) arrayList.get(1));

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

        target.getjButton1().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
            }
        });

        target.getjButton2().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    add();
                } catch (Exception ex) {
                    new Thrower().exception(ex);
                }

            }
        });

        //left -----------------------------------------------------------------
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
    }

    //==========================================================================
    private void add() {

        target.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                String message = null;
                ArrayList data = new ArrayList();

                try {

                    data.add("null");
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

                    hm.put("collector", collector);
                    hm.put("request", "save targets");
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

    } // end add

    //==========================================================================    
    public void setEnabledComponents(boolean flag) {
        target.getjButton2().setEnabled(flag);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        target.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class