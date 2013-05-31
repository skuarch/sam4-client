package controllers.filter;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.beans.CurrentUser;
import views.dialogs.ChooseOptionCategory;

/**
 *
 * @author skuarch
 */
public class ControllerChooseOptionCategory extends Controller {

    private ChooseOptionCategory chooseOptionCategory = null;
    private String action = null;
    private String collector = null;
    private ControllerCategories controllerCategories = null;
    private String categoryName = null;
    private String listType = null;

    //==========================================================================
    public ControllerChooseOptionCategory(String action, String collector, ControllerCategories controllerCategories, String categoryName) {
        chooseOptionCategory = new ChooseOptionCategory(null, true);
        this.action = action;
        this.collector = collector;
        this.categoryName = categoryName;
        this.controllerCategories = controllerCategories;
    }

    //==========================================================================
    @Override
    public void setupInterface() {

        chooseOptionCategory.getjButton1().setText(action);

        addListeners();

        if (CurrentUser.getInstance().getLevel() == 0) {
            chooseOptionCategory.getjButton1().setEnabled(false);
        }

    }

    //==========================================================================
    private void addListeners() {

        if ("edit".equalsIgnoreCase(action)) {

            chooseOptionCategory.getjButton1().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listType = chooseOptionCategory.getjComboBoxListType().getModel().getSelectedItem().toString();
                    ControllerEditCategorie cec = new ControllerEditCategorie(collector, controllerCategories, categoryName, listType);
                    cec.setupInterface();
                    setVisible(false);
                    cec.setVisible(true);
                }
            });
        }

        if ("add".equalsIgnoreCase(action)) {

            chooseOptionCategory.getjButton1().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listType = chooseOptionCategory.getjComboBoxListType().getModel().getSelectedItem().toString();
                    ControllerAddCategorie cac = new ControllerAddCategorie(collector, controllerCategories, listType);
                    cac.setupInterface();
                    setVisible(false);
                    cac.setVisible(true);
                }
            });

        }

    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        chooseOptionCategory.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class