package model.common;

import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import model.beans.FilterViews;
import model.dao.DAO;
import model.net.ConnectAction;
import views.helper.HighlightTreeCellRenderer;

/**
 *
 * @author skuarch
 */
public class ModelTrees {

    private final HighlightTreeCellRenderer renderer = new HighlightTreeCellRenderer();

    //==========================================================================
    public ModelTrees() {
    } // end ModelTrees

    //==========================================================================
    public DefaultMutableTreeNode getRootNodeTreeViews() throws Exception {

        ResultSet resultSetCategories = null;
        ResultSet resultSetSubCategories = null;
        DefaultMutableTreeNode rootNode = null;
        ConnectAction connectAction = null;
        DefaultMutableTreeNode categorie = null;

        try {

            connectAction = new ConnectAction();
            rootNode = new DefaultMutableTreeNode("Views");

            resultSetCategories = connectAction.executeQuery("select * from categories where categorie_active = 1 order by categorie_order asc");
            resultSetSubCategories = connectAction.executeQuery("select * from subcategories where subcategorie_active = 1 order by subcategorie_categorie_id,subcategorie_order  asc");

            while (resultSetCategories.next()) {

                categorie = new DefaultMutableTreeNode(resultSetCategories.getString("categorie_name"));
                rootNode.add(categorie);
                resultSetSubCategories.beforeFirst();

                while (resultSetSubCategories.next()) {

                    if (resultSetSubCategories.getInt("subcategorie_categorie_id") == resultSetCategories.getInt("categorie_id")) {
                        //subcategorie                        
                        categorie.add(new DefaultMutableTreeNode(resultSetSubCategories.getString("subcategorie_name")));
                    }

                } // while subcategories

            } // while categories            

        } catch (Exception e) {
            throw e;
        } finally {
            ConnectAction.DataBaseUtilities.closeResultSet(resultSetCategories);
            ConnectAction.DataBaseUtilities.closeResultSet(resultSetSubCategories);
            connectAction = null;
        }

        return rootNode;

    } // end DefaultMutableTreeNode

    //==========================================================================
    public DefaultMutableTreeNode getRootNodeFilterViews() throws Exception {

        DefaultMutableTreeNode rootNode = null;
        List<FilterViews> viewList = null;
        DefaultMutableTreeNode filterViews = null;

        try {

            rootNode = new DefaultMutableTreeNode("Views");
            viewList = new DAO().getAll("FilterViews");

            for (FilterViews fv : viewList) {

                rootNode.add(new DefaultMutableTreeNode(fv.getName()));

            }

        } catch (Exception e) {
            throw e;
        }

        return rootNode;

    } // end getTreeViewsFilter

    //==========================================================================
    public void fireDocumentChangeEvent(JTextField jTextField, JTree jTree) {

        String q = jTextField.getText();
        renderer.q = q;
        TreePath root = jTree.getPathForRow(0);
        collapseAll(jTree, root);
        if (!q.isEmpty()) {
            //searchTree(jTreeViews, root, q);
            searchTree(jTree, jTextField);
        } else {
            //collapseAll(jTreeViews, root);
            collapseAll(jTree);
        }
        //tree.repaint();
    }

    //==========================================================================
    public void collapseAll(JTree tree, TreePath parent) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (!node.isLeaf() && node.getChildCount() >= 0) {
            Enumeration e = node.children();
            while (e.hasMoreElements()) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                collapseAll(tree, path);
            }
        }
        tree.collapsePath(parent);
    }

    //==========================================================================
    public void searchTree(JTree jTree, JTextField jTextField) {

        DefaultMutableTreeNode node = searchNode(jTextField.getText(), (DefaultMutableTreeNode) jTree.getModel().getRoot());
        DefaultTreeModel dtm = null;

        if (node != null) {

            dtm = (DefaultTreeModel) jTree.getModel();
            TreeNode[] nodes = dtm.getPathToRoot(node);
            TreePath path = new TreePath(nodes);
            jTree.scrollPathToVisible(path);
            jTree.setSelectionPath(path);

        }

    }

    //==========================================================================
    public DefaultMutableTreeNode searchNode(String nodeStr, DefaultMutableTreeNode rootNode) {
        DefaultMutableTreeNode node = null;
        Enumeration e = rootNode.breadthFirstEnumeration();
        while (e.hasMoreElements()) {
            node = (DefaultMutableTreeNode) e.nextElement();
            //if (nodeStr.equalsIgnoreCase(node.getUserObject().toString())) {
            //if (node.getUserObject().toString().contains(nodeStr)) {
            if (node.getUserObject().toString().toLowerCase().contains(nodeStr.toLowerCase())) {
                return node;
            }
        }
        return null;
    }

    //==========================================================================
    public void collapseAll(JTree jTree) {
        int row = jTree.getRowCount() - 1;
        while (row >= 0) {
            jTree.collapseRow(row);
            row--;
        }
    } // end collapseAll
} // end class
