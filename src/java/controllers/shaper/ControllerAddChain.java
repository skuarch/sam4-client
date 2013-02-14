package controllers.shaper;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import model.net.Linker;
import model.util.HashMapUtilities;
import views.dialogs.Chain;

/**
 *
 * @author skuarch
 */
public class ControllerAddChain extends Controller {

    private ControllerChains controllerChains = null;
    private Chain chain = null;
    private String collector = null;    
    private HashMap hm = null;

    //==========================================================================
    public ControllerAddChain(String collector, ControllerChains controllerChains) {
        this.collector = collector;
        this.controllerChains = controllerChains;
        chain = new Chain(null, true);
        hm = HashMapUtilities.getHashMapShaper();
    } // end ControllerAddChain

    //==========================================================================
    @Override
    public void setupInterface() {

        chain.getjProgressBar().setIndeterminate(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                
                ArrayList arrayList = null;
                String[] serviceLevel = null;
                String[] fallback = null;
                String[] source = null;
                String[] direction = null;
                String[] destination = null;

                try {

                    addListeners();

                    hm.put("collector", collector);
                    hm.put("request", "get default data chains");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    serviceLevel = (String[]) arrayList.get(0);
                    fallback = (String[]) arrayList.get(1);
                    source = (String[]) arrayList.get(2);
                    direction = (String[]) arrayList.get(3);
                    destination = (String[]) arrayList.get(4);

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
                    saveChain();
                }
            });


        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end addListeners    

    //==========================================================================
    private void saveChain() {

        chain.getjProgressBar().setIndeterminate(true);
        setEnableButtons(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                StringBuilder data = new StringBuilder();                
                String message = null;

                try {

                    if (validate()) {

                        data.append("null");
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
                    hm.put("request", "save chains");
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

    } // end saveChain

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
