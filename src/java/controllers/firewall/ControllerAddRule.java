package controllers.firewall;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import model.net.Linker;
import model.util.HashMapUtilities;
import views.dialogs.AddRule;

/**
 *
 * @author skuarch
 */
public class ControllerAddRule extends Controller {

    private AddRule addRule = null;
    private String collector = null;
    private HashMap hm = null;

    //==========================================================================
    public ControllerAddRule(String collector) {
        this.addRule = new AddRule(null, true);
        this.collector = collector;
        hm = HashMapUtilities.getHashMapFirewall();
    }

    //==========================================================================
    @Override
    public void setupInterface() {
        try {

            hm.put("collector", collector);
            addListeners();

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
    }

    //==========================================================================
    private void addListeners() {

        try {

            addRule.getjButtonAdd().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addRule();
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end addListeners

    //==========================================================================
    private void addRule() {
        
        addRule.getjProgressBar().setIndeterminate(true);
        enableComponents(false);
        
        new Thread(new Runnable() {
            @Override
            public void run() {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        String strReturn = null;

                        try {

                            hm.put("request", "add rule");                            
                            hm.put("flow", addRule.getjComboBox1().getModel().getSelectedItem());
                            hm.put("direction", addRule.getjComboBox2().getModel().getSelectedItem());
                            hm.put("protocol", addRule.getjComboBox3().getModel().getSelectedItem());
                            hm.put("fromIp", addRule.getjTextFieldIP1().getText());
                            hm.put("fromPort", addRule.getjTextFieldPort1().getText());
                            hm.put("toIp", addRule.getjTextFieldIP2().getText());
                            hm.put("toPort", addRule.getjTextFieldPort2().getText());

                            strReturn = (String) new Linker().sendReceiveObject(hm);

                            if (strReturn != null) {
                                NOTIFICATIONS.information(strReturn, true);
                                ControllerFirewall.getInstance().reloadTables();
                                setVisible(false);
                            }

                        } catch (Exception e) {
                            NOTIFICATIONS.error("Unexpected error", e);
                        } finally {
                            addRule.getjProgressBar().setIndeterminate(false);
                            enableComponents(true);
                        }

                    }
                });
            }
        }).start();
    }

    //==========================================================================
    private void enableComponents(boolean flag) {
        addRule.getjComboBox1().setEnabled(flag);
        addRule.getjComboBox2().setEnabled(flag);
        addRule.getjComboBox3().setEnabled(flag);
        addRule.getjTextFieldIP1().setEnabled(flag);
        addRule.getjTextFieldPort1().setEnabled(flag);
        addRule.getjTextFieldIP2().setEnabled(flag);
        addRule.getjTextFieldPort2().setEnabled(flag);
        addRule.getjButtonAdd().setEnabled(flag);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        addRule.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class
