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
class ControllerAddPipe extends Controller {

    private ControllerPipes controllerPipes = null;
    private Pipes pipe = null;
    private String collector = null;
    private HashMap hm = null;

    //==========================================================================
    public ControllerAddPipe(String collector, ControllerPipes controllerPipes) {
        pipe = new Pipes(null, true);
        this.controllerPipes = controllerPipes;
        this.collector = collector;
        hm = HashMapUtilities.getHashMapShaper();
    }

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

                    pipe.getjButton2().setText("add");
                    pipe.getjButton2().setIcon(new ImageIcon(getClass().getResource("/views/images/add.gif")));

                    addListeners();

                    hm.put("collector", collector);
                    hm.put("request", "get default data pipes");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    pipe.getjRadioButtonInactive().setSelected(true);

                    ViewUtilities.fillJComboBox(pipe.getjComboBoxChain(), (String[]) arrayList.get(0));
                    ViewUtilities.fillJComboBox(pipe.getjComboBoxDirection(), (String[]) arrayList.get(1));
                    ViewUtilities.fillJlist(pipe.getjListFilterLeft(), (String[]) arrayList.get(2));
                    ViewUtilities.fillJlist(pipe.getjListFilterRight(), (String[]) arrayList.get(3));
                    ViewUtilities.fillJComboBox(pipe.getjComboBoxServiceLevel(), (String[]) arrayList.get(4));

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    setEnabledComponents(true);
                    pipe.getjProgressBar().setIndeterminate(false);
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
                    add();
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
    private void add() {

        pipe.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                String message = null;
                ArrayList data = new ArrayList();

                try {

                    data.add("null");
                    data.add(pipe.getjTextFieldName().getText());
                    data.add(pipe.getjRadioButtonActive().isSelected());
                    data.add(pipe.getjComboBoxChain().getSelectedItem());
                    data.add(pipe.getjComboBoxDirection().getSelectedItem());
                    data.add(ViewUtilities.getDataJList(pipe.getjListFilterLeft()));
                    data.add(ViewUtilities.getDataJList(pipe.getjListFilterRight()));
                    data.add(pipe.getjComboBoxServiceLevel().getSelectedItem());

                    hm.put("collector", collector);
                    hm.put("request", "save pipes");
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

    } // end add

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
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class
