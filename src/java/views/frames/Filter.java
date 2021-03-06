package views.frames;

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;

/**
 *
 * @author skuarch
 */
public class Filter extends javax.swing.JFrame {

    /**
     * Creates new form Filter
     */
    public Filter() {
        initComponents();
        setLocationRelativeTo(getRootPane());
        setIconImage(Toolkit.getDefaultToolkit().getImage(Filter.class.getResource("/views/images/sam_icon.png")));
        setTitle("Filter");
    }

    public JButton getjButtonBlackList() {
        return jButtonBlackList;
    }

    public JButton getjButtonCategories() {
        return jButtonCategories;
    }

    public JButton getjButtonClose() {
        return jButtonClose;
    }

    public JButton getjButtonPolicies() {
        return jButtonPolicies;
    }

    public JComboBox getjComboBoxCollectors() {
        return jComboBoxCollectors;
    }

    public JProgressBar getjProgressBar() {
        return jProgressBar;
    }

    public JTabbedPane getjTabbedPane() {
        return jTabbedPane;
    }


    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxCollectors = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonPolicies = new javax.swing.JButton();
        jButtonCategories = new javax.swing.JButton();
        jButtonBlackList = new javax.swing.JButton();
        jTabbedPane = new javax.swing.JTabbedPane();
        jProgressBar = new javax.swing.JProgressBar();
        jButtonClose = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jComboBoxCollectors.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "please select a collector" }));

        jButtonPolicies.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/security.png"))); // NOI18N
        jButtonPolicies.setText("policies");
        jButtonPolicies.setEnabled(false);

        jButtonCategories.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/categories.png"))); // NOI18N
        jButtonCategories.setText("categories");
        jButtonCategories.setEnabled(false);

        jButtonBlackList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/black.png"))); // NOI18N
        jButtonBlackList.setText("black list");
        jButtonBlackList.setEnabled(false);

        jButtonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/shutdown-mini.png"))); // NOI18N
        jButtonClose.setText("close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/shutdown-mini.png"))); // NOI18N
        jMenuItem1.setText("close");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jTabbedPane)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxCollectors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonPolicies)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCategories)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBlackList)))
                        .addGap(0, 305, Short.MAX_VALUE))
                    .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxCollectors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPolicies)
                    .addComponent(jButtonCategories)
                    .addComponent(jButtonBlackList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBlackList;
    private javax.swing.JButton jButtonCategories;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonPolicies;
    private javax.swing.JComboBox jComboBoxCollectors;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane;
    // End of variables declaration//GEN-END:variables
}
