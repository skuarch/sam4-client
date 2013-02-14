package controllers.filter;

import controllers.Controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import model.common.ModelTrees;
import model.util.ViewUtilities;
import views.panels.PanelTreeViews;

/**
 *
 * @author skuarch
 */
public class ControllerTreeViews extends Controller {

    private PanelTreeViews panelTreeViews = new PanelTreeViews();

    //==========================================================================
    public ControllerTreeViews() {
    } // end controllerTreeViews

    //==========================================================================
    @Override
    public void setupInterface() {

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {

                    panelTreeViews.getjTreeViews().setDragEnabled(false);
                    
                    createJTreeViews();

                } catch (Exception e) {
                    NOTIFICATIONS.error("Error creating view tree", e);
                }

                return null;
            }
        }.execute();

    } // end setupInterface

    //==========================================================================
    public String getSelected() {

        String selected = null;

        try {

            selected = ViewUtilities.getSelectedJTree(panelTreeViews.getjTreeViews());

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

        return selected;

    } // end getSelected

    //==========================================================================
    public void createJTreeViews() {

        JTree jTreeViews = null;
        DefaultMutableTreeNode rootNode = null;
        DefaultTreeModel model = null;
        final ModelTrees mt = new ModelTrees();

        try {

            panelTreeViews.setName("Views");
            jTreeViews = panelTreeViews.getjTreeViews();
            jTreeViews.setEnabled(false);            
            rootNode = new ModelTrees().getRootNodeFilterViews();
            model = new DefaultTreeModel(rootNode);
            jTreeViews.setModel(model);

            //add listener jtree
            jTreeViews.addMouseListener(new MouseAdapter() {
                //--------------------------------------------------------------
                @Override
                public void mouseClicked(MouseEvent e) {                    
                    ControllerFilterGUI.getInstance().clickTrees();
                }
            });

            panelTreeViews.getjTextFieldSearch().getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    mt.fireDocumentChangeEvent(panelTreeViews.getjTextFieldSearch(), panelTreeViews.getjTreeViews());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    mt.fireDocumentChangeEvent(panelTreeViews.getjTextFieldSearch(), panelTreeViews.getjTreeViews());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    //mt.fireDocumentChangeEvent(panelTreeViews.getjTextFieldSearch(), panelTreeViews.getjTreeViews());
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Error creating view tree", e);
        } finally {
            jTreeViews.setEnabled(true);
        }

    } // end createJTreeViews

    //==========================================================================
    public JPanel getPanelTreeViews() {
        return panelTreeViews;
    } // end getPanelTreeViews

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class
