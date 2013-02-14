
package controllers.filter;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import model.common.ModelCollectors;
import model.common.ModelTrees;
import model.util.ViewUtilities;
import views.panels.PanelTreeCollectors;

/**
 *
 * @author skuarch
 */
public class ControllerTreeCollectors extends Controller {
 private PanelTreeCollectors panelTreeCollectors = new PanelTreeCollectors();

    //==========================================================================
    @Override
    public void setupInterface() {

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {

                    createJTreeCollectors();
                    addListeners();

                } catch (Exception e) {
                    NOTIFICATIONS.error("Error creating tree collectors", e);
                }

                return null;
            }
        }.execute();
    }

    //==========================================================================
    public String getSelected() {

        String selected = null;

        try {

            selected = ViewUtilities.getSelectedJTree(panelTreeCollectors.getjTreeCollectors());

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

        return selected;

    } // end getSelected
    
    //==========================================================================
    private void addListeners() {

        final ModelTrees mt = new ModelTrees();

        try {

            //------------------------------------------------------------------
            panelTreeCollectors.getjMenuItemRefresh().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    refreshTreeViews();
                }
            });

            panelTreeCollectors.getjTreeCollectors().addMouseListener(new MouseAdapter() {
                //--------------------------------------------------------------
                @Override
                public void mousePressed(MouseEvent e) {                    
                    ControllerFilterGUI.getInstance().clickTrees();
                }
            });

            //------------------------------------------------------------------
            panelTreeCollectors.getjTextFieldSearch().getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    mt.fireDocumentChangeEvent(panelTreeCollectors.getjTextFieldSearch(), panelTreeCollectors.getjTreeCollectors());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    mt.fireDocumentChangeEvent(panelTreeCollectors.getjTextFieldSearch(), panelTreeCollectors.getjTreeCollectors());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    //mt.fireDocumentChangeEvent(panelTreeViews.getjTextFieldSearch(), panelTreeViews.getjTreeViews());
                }
            });


        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    }

    //==========================================================================
    public void createJTreeCollectors() {

        DefaultMutableTreeNode nodeWait = new DefaultMutableTreeNode("wait");
        final JTree jTree = panelTreeCollectors.getjTreeCollectors();
        jTree.setEnabled(false);
        panelTreeCollectors.getjMenuItemRefresh().setEnabled(false);

        try {

            jTree.setModel(new DefaultTreeModel(nodeWait));             
            jTree.setModel(new ModelCollectors().getCollectorsModelLogs());

        } catch (Exception e) {
            NOTIFICATIONS.error("Error creating collectors tree", e);
        } finally {
            panelTreeCollectors.getjTreeCollectors().setEnabled(true);
            panelTreeCollectors.getjMenuItemRefresh().setEnabled(true);
        }

    } // end createJTreeCollectors

    //==========================================================================
    private void refreshTreeViews() {

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                createJTreeCollectors();
                return null;
            }
        }.execute();

    } // end refreshTreeViews

    //==========================================================================
    public JPanel getPanelTreeCollectors() {
        return panelTreeCollectors;
    }

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
