package views.frames;

import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import views.tabs.FactoryTabs;

/**
 *
 * @author skuarch
 */
public class Filter2 extends javax.swing.JFrame {

    //==========================================================================
    /**
     * Creates new form Sniffer
     */
    public Filter2() {
        initComponents();
        setLocationRelativeTo(getRootPane());
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/views/images/sam_icon.png")));
    }

    public JTabbedPane getjTabbedPaneViews() {
        return jTabbedPaneViews;
    }

    public JTabbedPane getjTabbedPaneCollectors() {
        return jTabbedPaneCollectors;
    }

    public JSplitPane getjSplitPaneMain() {
        return jSplitPaneMain;
    }

    public JSplitPane getjSplitPaneTrees() {
        return jSplitPaneTrees;
    }

    public JMenu getjMenuTools() {
        return jMenuTools;
    }

    public JMenuItem getjMenuItemOptions() {
        return jMenuItemOptions;
    }
    /*
     public JMenuItem getjMenuItemCreateReport() {
     return jMenuItemCreateReport;
     }
     */

    //==========================================================================
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPaneMain = new javax.swing.JSplitPane();
        jSplitPaneTrees = new javax.swing.JSplitPane();
        jTabbedPaneViews = new FactoryTabs();
        jTabbedPaneCollectors = new FactoryTabs();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuTools = new javax.swing.JMenu();
        jMenuItemOptions = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jSplitPaneMain.setDividerLocation(250);

        jSplitPaneTrees.setDividerLocation(250);
        jSplitPaneTrees.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPaneTrees.setTopComponent(jTabbedPaneViews);
        jSplitPaneTrees.setRightComponent(jTabbedPaneCollectors);

        jSplitPaneMain.setLeftComponent(jSplitPaneTrees);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        jSplitPaneMain.setRightComponent(jPanel1);

        jMenuTools.setText("Menu");

        jMenuItemOptions.setText("options");
        jMenuTools.add(jMenuItemOptions);

        jMenuBar1.add(jMenuTools);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPaneMain, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPaneMain)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemOptions;
    private javax.swing.JMenu jMenuTools;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPaneMain;
    private javax.swing.JSplitPane jSplitPaneTrees;
    private javax.swing.JTabbedPane jTabbedPaneCollectors;
    private javax.swing.JTabbedPane jTabbedPaneViews;
    // End of variables declaration//GEN-END:variables
}
