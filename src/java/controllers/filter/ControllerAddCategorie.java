package controllers.filter;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.SwingWorker;
import model.net.Linker;
import model.util.HashMapUtilities;
import model.util.ViewUtilities;
import views.dialogs.Categorie;

/**
 *
 * @author skuarch
 */
public class ControllerAddCategorie extends Controller {

    private Categorie categorie = null;
    private String collector = null;
    private HashMap hm = null;
    private ControllerCategories controllerCategories = null;
    private String listType = null;

    //==========================================================================
    public ControllerAddCategorie(String collector, ControllerCategories controllerCategories, String listType) {

        categorie = new Categorie(null, true);
        this.collector = collector;
        hm = HashMapUtilities.getHashMapFilter();
        this.controllerCategories = controllerCategories;
        this.listType = listType;

    } // end ControllerAddPolicie

    //==========================================================================
    @Override
    public void setupInterface() {

        categorie.setName("Edit Categorie");
        setEnabledComponents(false);
        categorie.getjProgressBar().setIndeterminate(true);

        new SwingWorker<Void, Void>() {
            
            @Override
            protected Void doInBackground() throws Exception {

                try {

                    addListeners();
                    
                    categorie.getjButtonAdd().setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/add.gif"))); // NOI18N
                    categorie.getjButtonAdd().setText("add");
                    categorie.getjLabelListType().setText("Add " + listType);
                    categorie.getjButtonAddUrl().setText("Add " + listType);                    

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    setEnabledComponents(true);
                    categorie.getjProgressBar().setIndeterminate(false);
                }

                return null;
            }
        }.execute();

    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        categorie.getjButtonAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });

        categorie.getjButtonAddUrl().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUrl();
            }
        });

        //----------------------------------------------------------------------
        categorie.getjButtonLeft().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    ViewUtilities.passItemsLeftRigth(categorie.getjListRemove(), categorie.getjListCategories());

                } catch (Exception ex) {
                    NOTIFICATIONS.error("Unexpected error", ex);
                }

            }
        });

        //----------------------------------------------------------------------
        categorie.getjButtonRight().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    ViewUtilities.passItemsLeftRigth(categorie.getjListCategories(), categorie.getjListRemove());

                } catch (Exception ex) {
                    NOTIFICATIONS.error("Unexpected error", ex);
                }

            }
        });

    } // end addListeners

    //==========================================================================
    private void add() {

        categorie.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                String message = null;
                ArrayList data = new ArrayList();

                try {

                    if (validate()) {

                        data.add("null");
                        data.add(categorie.getjTextFieldName().getText());
                        data.add(ViewUtilities.getDataJList(categorie.getjListCategories()));
                        data.add(ViewUtilities.getDataJList(categorie.getjListRemove()));
                        data.add(categorie.getjTextFieldRedirect().getText());

                        hm.put("collector", collector);
                        hm.put("request", "save categories");
                        hm.put("listType", listType);
                        hm.put("data", data);

                        message = (String) new Linker().sendReceiveObject(hm);

                        controllerCategories.reloadTable();
                        NOTIFICATIONS.information(message, true);

                        setVisible(false);

                    }

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    categorie.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

                return null;
            }
        }.execute();

    } // end add

    //==========================================================================
    private void addUrl() {

        DefaultListModel defaultListModel = null;
        String url = null;
        ListModel listModel = null;

        try {

            url = categorie.getjTextFieldUrl().getText();

            if (url == null || url.length() < 1) {
                return;
            }

            defaultListModel = new DefaultListModel();
            listModel = categorie.getjListCategories().getModel();

            for (int i = 0; i < listModel.getSize(); i++) {

                if (listModel.getElementAt(i).equals(url)) {
                    NOTIFICATIONS.information("the url/domain " + url + " already exists", true);
                    return;
                }

                defaultListModel.addElement(listModel.getElementAt(i));
            }

            defaultListModel.addElement(url);
            categorie.getjListCategories().setModel(defaultListModel);

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
    }

    //==========================================================================
    private void setEnabledComponents(boolean flag) {
        categorie.getjTextFieldName().setEnabled(flag);
        categorie.getjButtonAdd().setEnabled(flag);
        categorie.getjButtonClose().setEnabled(flag);
        categorie.getjButtonLeft().setEnabled(flag);
        categorie.getjButtonRight().setEnabled(flag);
        categorie.getjListCategories().setEnabled(flag);
        categorie.getjListRemove().setEnabled(flag);
    } // end setEnabledComponents

    //==========================================================================
    private boolean validate() {

        boolean flag = false;

        try {

            if (categorie.getjTextFieldName().getText() == null || categorie.getjTextFieldName().getText().length() < 1) {
                return false;
            }

            if (categorie.getjListCategories().getModel().getSize() < 1) {
                return false;
            }

            flag = true;

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

        return flag;

    } // end validate

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        categorie.setVisible(flag);
    } // end setVisible

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end destroyCurrentThread
} // end ControllerAddCategorie