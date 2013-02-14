package controllers.shaper;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import model.net.Linker;
import model.util.HashMapUtilities;
import views.dialogs.Chain;

/**
 *
 * @author skuarch
 */
public class ControllerEditChain extends Controller {

    private Chain chain = null;
    private String collector = null;
    private String id = null;
    private ControllerChains controllerChains = null;
    private HashMap hm = null;

    //==========================================================================
    public ControllerEditChain(String collector, String id, ControllerChains controllerChains) {
        this.collector = collector;
        this.id = id;
        this.controllerChains = controllerChains;
        chain = new Chain(null, true);
        hm = HashMapUtilities.getHashMapShaper();
    } // end ControllerEditChain

    //==========================================================================
    @Override
    public void setupInterface() {

        chain.getjProgressBar().setIndeterminate(true);
        setEnableButtons(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList arrayList = null;                
                String chainName = null;
                String status = null;
                String[] serviceLevel = null;
                String[] fallback = null;
                String[] source = null;
                String[] direction = null;
                String[] destination = null;

                try {

                    addListeners();
                    
                    chain.getjButtonSave().setText("edit");
                    chain.getjButtonSave().setIcon(new ImageIcon(getClass().getResource("/views/images/edit.png")));
                    
                    hm.put("request", "get data chains");
                    hm.put("collector", collector);
                    hm.put("id", id);
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    id = (String) arrayList.get(0);
                    chainName = (String) arrayList.get(1);
                    status = (String) arrayList.get(2);
                    serviceLevel = (String[]) arrayList.get(3);
                    fallback = (String[]) arrayList.get(4);
                    source = (String[]) arrayList.get(5);
                    direction = (String[]) arrayList.get(6);
                    destination = (String[]) arrayList.get(7);

                    chain.getjTextFieldChainName().setText(chainName);

                    if (status.equalsIgnoreCase("Y")) {
                        chain.getjRadioButtonStatusActive().setSelected(true);
                    } else {
                        chain.getjRadioButtonStatusInactive().setSelected(true);
                    }


                    chain.getjComboBoxServiceLevel().removeAllItems();
                    for (Object objects : serviceLevel) {
                        chain.getjComboBoxServiceLevel().addItem(objects);
                    }


                    chain.getjComboBoxFallback().removeAllItems();
                    for (Object objects : fallback) {
                        chain.getjComboBoxFallback().addItem(objects);
                    }

                    chain.getjComboBoxSource().removeAllItems();
                    for (Object objects : source) {
                        chain.getjComboBoxSource().addItem(objects);
                    }

                    chain.getjComboBoxDirection().removeAllItems();
                    for (Object objects : direction) {
                        chain.getjComboBoxDirection().addItem(objects);
                    }

                    chain.getjComboBoxDestination().removeAllItems();
                    for (Object objects : destination) {
                        chain.getjComboBoxDestination().addItem(objects);
                    }

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    chain.getjProgressBar().setIndeterminate(false);                    
                    setEnableButtons(true);
                }

            }
        }).start();

    } // end setupInterface

    //==========================================================================
    private void addListeners() {
        
        try {
            
            chain.getjButtonSave().addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    editChain();
                }
            });
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
        
    } // end addListeners
    
    //==========================================================================
    private void editChain(){
        
        chain.getjProgressBar().setIndeterminate(true);
        setEnableButtons(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                StringBuilder data = new StringBuilder();                                
                String message = null;

                try {

                    if (validate()) {

                        data.append(id);
                        data.append("##");
                        data.append(chain.getjTextFieldChainName().getText());
                        data.append("##");
                        data.append(chain.getjRadioButtonStatusActive().isSelected());
                        data.append("##");
                        data.append(chain.getjComboBoxServiceLevel().getModel().getSelectedItem().toString());
                        data.append("##");
                        data.append(chain.getjComboBoxFallback().getModel().getSelectedItem().toString());
                        data.append("##");
                        data.append(chain.getjComboBoxSource().getModel().getSelectedItem().toString());
                        data.append("##");
                        data.append(chain.getjComboBoxDirection().getModel().getSelectedItem().toString());
                        data.append("##");
                        data.append(chain.getjComboBoxDestination().getModel().getSelectedItem().toString());                                              
                        
                    }
                    
                    hm.put("collector", collector);
                    hm.put("request", "edit chains");
                    hm.put("data", data);                    
                    message = (String) new Linker().sendReceiveObject(hm);
                            
                    controllerChains.reloadTable();                    
                    NOTIFICATIONS.information(message, true);                    
                    
                    setVisible(false);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    chain.getjProgressBar().setIndeterminate(false);
                    setEnableButtons(true);
                }
            }
        }).start();
    }
    
    //==========================================================================
    private boolean validate() throws Exception {

        boolean flag = false;
        String chainName = null;

        try {

            chainName = chain.getjTextFieldChainName().getText();

            if (chainName == null || chainName.length() < 1) {
                NOTIFICATIONS.error("the name of chain is empty", new Exception());
                return false;
            }

            flag = true;

        } catch (Exception e) {
            throw e;
        }

        return flag;

    } // end validate
    
    //==========================================================================
    private void setEnableButtons(boolean flag) {
        chain.getjButtonSave().setEnabled(flag);
    } // end 

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        chain.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
} // end class
