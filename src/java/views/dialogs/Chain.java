package views.dialogs;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author skuarch
 */
public class Chain extends javax.swing.JDialog {

    //==========================================================================
    /**
     * Creates new form Chain
     */
    public Chain(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        setLocationRelativeTo(getContentPane());
        setIconImage(Toolkit.getDefaultToolkit().getImage(Chain.class.getResource("/views/images/sam_icon.png")));  
        buttonGroup1.add(jRadioButtonStatusActive);
        buttonGroup1.add(jRadioButtonStatusInactive);

    } // end Chain

    //==========================================================================
    public JTextField getjTextFieldChainName() {
        return jTextFieldChainName;
    }

    //==========================================================================
    public void setjTextFieldChainName(JTextField jTextFieldChainName) {
        this.jTextFieldChainName = jTextFieldChainName;
    }

    //==========================================================================
    public ButtonGroup getButtonGroup1() {
        return buttonGroup1;
    }

    //==========================================================================
    public void setButtonGroup1(ButtonGroup buttonGroup1) {
        this.buttonGroup1 = buttonGroup1;
    }

    //==========================================================================
    public JButton getjButtonSave() {
        return jButtonSave;
    }

    //==========================================================================
    public void setjButtonSave(JButton jButtonSave) {
        this.jButtonSave = jButtonSave;
    }

    //==========================================================================
    public JComboBox getjComboBoxDestination() {
        return jComboBoxDestination;
    }

    //==========================================================================
    public void setjComboBoxDestination(JComboBox jComboBoxDestination) {
        this.jComboBoxDestination = jComboBoxDestination;
    }

    //==========================================================================
    public JComboBox getjComboBoxDirection() {
        return jComboBoxDirection;
    }

    //==========================================================================
    public void setjComboBoxDirection(JComboBox jComboBoxDirection) {
        this.jComboBoxDirection = jComboBoxDirection;
    }

    //==========================================================================
    public JComboBox getjComboBoxFallback() {
        return jComboBoxFallback;
    }

    //==========================================================================
    public void setjComboBoxFallback(JComboBox jComboBoxFallback) {
        this.jComboBoxFallback = jComboBoxFallback;
    }

    //==========================================================================
    public JComboBox getjComboBoxServiceLevel() {
        return jComboBoxServiceLevel;
    }

    //==========================================================================
    public void setjComboBoxServiceLevel(JComboBox jComboBoxServiceLevel) {
        this.jComboBoxServiceLevel = jComboBoxServiceLevel;
    }

    //==========================================================================
    public JComboBox getjComboBoxSource() {
        return jComboBoxSource;
    }

    //==========================================================================
    public void setjComboBoxSource(JComboBox jComboBoxSource) {
        this.jComboBoxSource = jComboBoxSource;
    }

    //==========================================================================
    public JRadioButton getjRadioButtonStatusActive() {
        return jRadioButtonStatusActive;
    }

    //==========================================================================
    public void setjRadioButtonStatusActive(JRadioButton jRadioButtonStatusActive) {
        this.jRadioButtonStatusActive = jRadioButtonStatusActive;
    }

    //==========================================================================
    public JRadioButton getjRadioButtonStatusInactive() {
        return jRadioButtonStatusInactive;
    }

    //==========================================================================
    public void setjRadioButtonStatusInactive(JRadioButton jRadioButtonStatusInactive) {
        this.jRadioButtonStatusInactive = jRadioButtonStatusInactive;
    }

    //==========================================================================
    public void setChainName(String text) {
        jTextFieldChainName.setText(text);
    } // end setChainName

    //==========================================================================
    public void isStatusActive(boolean flag) {
        jRadioButtonStatusActive.setSelected(flag);

    }

    //==========================================================================    
    public void addListenerButtonSave(ActionListener actionListener){
        jButtonSave.addActionListener(actionListener);
    }

    //==========================================================================
    public void setIndeterminateProgressBar(boolean flag) {
        jProgressBar.setIndeterminate(flag);
    }

    public JProgressBar getjProgressBar() {
        return jProgressBar;
    }

    //==========================================================================
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldChainName = new javax.swing.JTextField();
        jRadioButtonStatusActive = new javax.swing.JRadioButton();
        jRadioButtonStatusInactive = new javax.swing.JRadioButton();
        jComboBoxServiceLevel = new javax.swing.JComboBox();
        jComboBoxFallback = new javax.swing.JComboBox();
        jComboBoxSource = new javax.swing.JComboBox();
        jComboBoxDirection = new javax.swing.JComboBox();
        jComboBoxDestination = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jProgressBar = new javax.swing.JProgressBar();
        jButtonClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Chain");
        setResizable(false);

        jLabel1.setText("Name:");

        jLabel2.setText("Status:");

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel6.setText("General");

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/add.gif"))); // NOI18N
        jButtonSave.setText("add");

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel9.setText("Bandwidth");

        jLabel3.setText("Service Level");

        jLabel4.setText("Fallback");

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel5.setText("Targets");

        jLabel7.setText("Affecting");

        jRadioButtonStatusActive.setText("active");

        jRadioButtonStatusInactive.setSelected(true);
        jRadioButtonStatusInactive.setText("inactive");

        jComboBoxServiceLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "loading" }));

        jComboBoxFallback.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "loading" }));

        jLabel8.setText("Source");

        jLabel10.setText("Destination");

        jButtonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/shutdown-mini.png"))); // NOI18N
        jButtonClose.setText("close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 395, Short.MAX_VALUE)
                        .addComponent(jButtonClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSave))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButtonStatusActive)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButtonStatusInactive)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jComboBoxServiceLevel, 0, 441, Short.MAX_VALUE)
                                    .addComponent(jTextFieldChainName)
                                    .addComponent(jComboBoxFallback, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jComboBoxSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxDirection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jComboBoxDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldChainName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jRadioButtonStatusActive)
                    .addComponent(jRadioButtonStatusInactive))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxServiceLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxFallback, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDirection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDestination, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonClose))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxDestination;
    private javax.swing.JComboBox jComboBoxDirection;
    private javax.swing.JComboBox jComboBoxFallback;
    private javax.swing.JComboBox jComboBoxServiceLevel;
    private javax.swing.JComboBox jComboBoxSource;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JRadioButton jRadioButtonStatusActive;
    private javax.swing.JRadioButton jRadioButtonStatusInactive;
    private javax.swing.JTextField jTextFieldChainName;
    // End of variables declaration//GEN-END:variables
} // end class
