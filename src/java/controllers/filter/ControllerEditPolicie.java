package controllers.filter;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.SwingWorker;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.ViewUtilities;
import views.dialogs.Policy;


/**
 *
 * @author skuarch
 */
public class ControllerEditPolicie extends Controller {

    private Policy policy = null;
    private String collector = null;
    private HashMap hm = null;
    private ControllerPolicies controllerPolicies = null;
    private String policyName = null;

    //==========================================================================
    public ControllerEditPolicie(String collector, ControllerPolicies controllerPolicies, String policyName) {

        policy = new Policy(null, true);
        this.collector = collector;
        hm = HashMapUtilities.getHashMapFilter();
        this.controllerPolicies = controllerPolicies;
        this.policyName = policyName;

    } // end ControllerAddPolicie

    //==========================================================================
    @Override
    public void setupInterface() {

        setEnabledComponents(false);
        policy.getjProgressBar().setIndeterminate(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                ArrayList arrayList = null;
                Object[] categoriesApply = null;

                try {

                    policy.getjTextFieldName().setEditable(false);

                    addListeners();

                    hm.put("collector", collector);
                    hm.put("name", policyName);
                    hm.put("request", "get data policies");
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    policy.getjTextFieldName().setText(policyName);

                    //ip
                    if (arrayList.get(0).equals(true)) {
                        policy.getjRadioButtonIP().setSelected(true);
                        policy.getjTextFieldIP().setText(arrayList.get(1).toString());
                    }

                    //segment
                    if (arrayList.get(2).equals(true)) {
                        policy.getjRadioButtonSegment().setSelected(true);
                        policy.getjTextFieldSegment().setText(arrayList.get(3).toString());
                        policy.getjTextFieldMask().setText(arrayList.get(4) + "");
                    }

                    ViewUtilities.fillJlist(policy.getjListCatagories(), (Object[]) arrayList.get(5));

                    categoriesApply = (Object[]) arrayList.get(6);
                    if (categoriesApply != null) {
                        ViewUtilities.fillJlist(policy.getjListCategoriesApply(), (Object[]) arrayList.get(6));
                    }


                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    setEnabledComponents(true);
                    policy.getjProgressBar().setIndeterminate(false);
                }

                return null;
            }
        }.execute();

    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        policy.getjButtonAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit();
            }
        });

        //----------------------------------------------------------------------
        policy.getjButtonLeft().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    ViewUtilities.passItemsLeftRigth(policy.getjListCategoriesApply(), policy.getjListCatagories());

                } catch (Exception ex) {
                    NOTIFICATIONS.error("Unexpected error", ex);
                }

            }
        });

        //----------------------------------------------------------------------
        policy.getjButtonRight().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    ViewUtilities.passItemsLeftRigth(policy.getjListCatagories(), policy.getjListCategoriesApply());

                } catch (Exception ex) {
                    NOTIFICATIONS.error("Unexpected error", ex);
                }

            }
        });

    } // end addListeners

    //==========================================================================
    private void edit() {

        policy.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                String message = null;
                ArrayList data = new ArrayList();

                try {

                    data.add("null");
                    data.add(policy.getjTextFieldName().getText());
                    data.add(policy.getjRadioButtonIP().isSelected());
                    data.add(policy.getjTextFieldIP().getText());
                    data.add(policy.getjRadioButtonSegment().isSelected());
                    data.add(policy.getjTextFieldSegment().getText());
                    data.add(policy.getjTextFieldMask().getText());
                    data.add(ViewUtilities.getDataJList(policy.getjListCategoriesApply()));

                    hm.put("collector", collector);
                    hm.put("request", "edit policies");
                    hm.put("data", data);

                    message = (String) new Linker().sendReceiveObject(hm);

                    controllerPolicies.reloadTable();
                    NOTIFICATIONS.information(message, true);

                    setVisible(false);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    policy.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

                return null;
            }
        }.execute();

    } // end add

    //==========================================================================
    private void setEnabledComponents(boolean flag) {
        policy.getjTextFieldName().setEnabled(flag);
        policy.getjTextFieldIP().setEnabled(flag);
        policy.getjTextFieldMask().setEnabled(flag);
        policy.getjTextFieldSegment().setEnabled(flag);
        policy.getjButtonAdd().setEnabled(flag);
        policy.getjButtonClose().setEnabled(flag);
        policy.getjButtonLeft().setEnabled(flag);
        policy.getjButtonRight().setEnabled(flag);
        policy.getjListCatagories().setEnabled(flag);
        policy.getjListCategoriesApply().setEnabled(flag);
    } // end setEnabledComponents

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        policy.setVisible(flag);
    } // end setVisible

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end destroyCurrentThread
} // end class