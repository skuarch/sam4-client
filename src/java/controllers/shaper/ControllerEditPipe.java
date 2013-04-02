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
import views.dialogs.Pipes;

/**
 *
 * @author skuarch
 */
public class ControllerEditPipe extends Controller {

    private Pipes pipe = null;
    private HashMap hm = null;
    private String collector = null;
    private String id = null;
    private ControllerPipes controllerPipes = null;

    //==========================================================================
    public ControllerEditPipe(String collector, String id, ControllerPipes controllerPipes) {
        pipe = new Pipes(null, true);
        hm = HashMapUtilities.getHashMapShaper();
        this.collector = collector;
        this.id = id;
        this.controllerPipes = controllerPipes;
    } // end ControllerEditPipe

    //==========================================================================
    @Override
    public void setupInterface() {

        pipe.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList arrayList = null;

                try {

                    pipe.getjButton1().setText("close");
                    pipe.getjButton1().setIcon(new ImageIcon(getClass().getResource("/views/images/shutdown-mini.png")));

                    pipe.getjButton2().setText("edit");
                    pipe.getjButton2().setIcon(new ImageIcon(getClass().getResource("/views/images/edit.png")));

                    addListeners();

                    hm.put("id", id);
                    hm.put("collector", collector);
                    hm.put("request", "get data pipes");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    pipe.getjTextFieldName().setText(arrayList.get(0).toString());

                    if (arrayList.get(1).equals(true)) {
                        pipe.getjRadioButtonActive().setSelected(true);
                    } else {
                        pipe.getjRadioButtonInactive().setSelected(true);
                    }

                    ViewUtilities.fillJComboBox(pipe.getjComboBoxChain(), (String[]) arrayList.get(2));
                    ViewUtilities.fillJComboBox(pipe.getjComboBoxDirection(), (String[]) arrayList.get(3));
                    ViewUtilities.fillJlist(pipe.getjListFilterLeft(), (String[]) arrayList.get(4));
                    ViewUtilities.fillJlist(pipe.getjListFilterRight(), (String[]) arrayList.get(5));
                    ViewUtilities.fillJComboBox(pipe.getjComboBoxServiceLevel(), (String[]) arrayList.get(6));

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    pipe.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

            }
        }).start();

    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        //save -----------------------------------------------------------------
        pipe.getjButton2().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    edit();
                } catch (Exception ex) {
                    new Thrower().exception(ex);
                }
            }
        });

        //close ----------------------------------------------------------------
        pipe.getjButton1().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        //left -----------------------------------------------------------------
        pipe.getjButtonLeft().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    ViewUtilities.passItemsLeftRigth(pipe.getjListFilterLeft(), pipe.getjListFilterRight());
                } catch (Exception ex) {
                    new Thrower().exception(ex);
                }

            }
        });

        //Right -----------------------------------------------------------------
        pipe.getjButtonRight().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    ViewUtilities.passItemsLeftRigth(pipe.getjListFilterRight(), pipe.getjListFilterLeft());
                } catch (Exception ex) {
                    NOTIFICATIONS.error(ex.getMessage(), ex);
                }

            }
        });

    }

    //==========================================================================
    private void edit() {

        pipe.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList data = new ArrayList();
                String message = null;

                try {

                    if (validate()) {

                        data.add(id);
                        data.add(pipe.getjTextFieldName().getText());
                        data.add(pipe.getjRadioButtonActive().isSelected());
                        data.add(pipe.getjComboBoxChain().getSelectedItem());
                        data.add(pipe.getjComboBoxDirection().getSelectedItem());
                        data.add(ViewUtilities.getDataJList(pipe.getjListFilterLeft()));
                        data.add(ViewUtilities.getDataJList(pipe.getjListFilterRight()));
                        data.add(pipe.getjComboBoxServiceLevel().getSelectedItem());

                    }
                    
                    hm.put("id", id);
                    hm.put("collector", collector);
                    hm.put("request", "edit pipes");
                    hm.put("data", data);
                    
                    message = (String) new Linker().sendReceiveObject(hm);
                            
                    controllerPipes.reloadTable();                    
                    NOTIFICATIONS.information(message, true);                    
                    
                    setVisible(false);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    pipe.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

            }
        }).start();

    } // end edit

    //==========================================================================    
    public void setEnabledComponents(boolean flag) {
        pipe.getjButton2().setEnabled(flag);
        pipe.getjButtonLeft().setEnabled(flag);
        pipe.getjButtonRight().setEnabled(flag);
        pipe.getjComboBoxChain().setEnabled(flag);
        pipe.getjComboBoxDirection().setEnabled(flag);
        pipe.getjComboBoxServiceLevel().setEnabled(flag);
        pipe.getjRadioButtonActive().setEnabled(flag);
        pipe.getjRadioButtonInactive().setEnabled(flag);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        pipe.setVisible(flag);
    } // end setVisible

    //==========================================================================
    private boolean validate() throws Exception {

        boolean flag = false;
        String chainName = null;

        try {

            chainName = pipe.getjTextFieldName().getText();

            if (chainName == null || chainName.length() < 1) {
                NOTIFICATIONS.error("the name of pipe is empty", new Exception());
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

