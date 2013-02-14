package views.dialogs;

import javax.swing.*;

/**
 *
 * @author skuarch
 */
public class ShaperOptions extends javax.swing.JDialog {

    //==========================================================================
    /**
     * Creates new form ShaperOptions
     */
    public ShaperOptions(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(getContentPane());
        buttonGroupIMQ.add(jRadioButtonIMQNo);
        buttonGroupIMQ.add(jRadioButtonIMQYes);
        
        buttonGroupTrafficFilter.add(jRadioButtonTCFilter);
        buttonGroupTrafficFilter.add(jRadioButtonIptables);
        
        buttonGroupMode.add(jRadioButtonRouter);
        buttonGroupMode.add(jRadioButtonBridge);
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public void setjButton1(JButton jButton1) {
        this.jButton1 = jButton1;
    }

    public JButton getjButton2() {
        return jButton2;
    }

    public void setjButton2(JButton jButton2) {
        this.jButton2 = jButton2;
    }

    public JComboBox getjComboBoxACKPackets() {
        return jComboBoxACKPackets;
    }

    public void setjComboBoxACKPackets(JComboBox jComboBoxACKPackets) {
        this.jComboBoxACKPackets = jComboBoxACKPackets;
    }

    public JComboBox getjComboBoxClassifier() {
        return jComboBoxClassifier;
    }

    public void setjComboBoxClassifier(JComboBox jComboBoxClassifier) {
        this.jComboBoxClassifier = jComboBoxClassifier;
    }

    public JComboBox getjComboBoxDefaultQueuingDiscipline() {
        return jComboBoxDefaultQueuingDiscipline;
    }

    public void setjComboBoxDefaultQueuingDiscipline(JComboBox jComboBoxDefaultQueuingDiscipline) {
        this.jComboBoxDefaultQueuingDiscipline = jComboBoxDefaultQueuingDiscipline;
    }

    public JProgressBar getjProgressBar() {
        return jProgressBar;
    }

    public void setjProgressBar(JProgressBar jProgressBar) {
        this.jProgressBar = jProgressBar;
    }

    public JRadioButton getjRadioButtonBridge() {
        return jRadioButtonBridge;
    }

    public void setjRadioButtonBridge(JRadioButton jRadioButtonBridge) {
        this.jRadioButtonBridge = jRadioButtonBridge;
    }

    public JRadioButton getjRadioButtonIMQNo() {
        return jRadioButtonIMQNo;
    }

    public void setjRadioButtonIMQNo(JRadioButton jRadioButtonIMQNo) {
        this.jRadioButtonIMQNo = jRadioButtonIMQNo;
    }

    public JRadioButton getjRadioButtonIMQYes() {
        return jRadioButtonIMQYes;
    }

    public void setjRadioButtonIMQYes(JRadioButton jRadioButtonIMQYes) {
        this.jRadioButtonIMQYes = jRadioButtonIMQYes;
    }

    public JRadioButton getjRadioButtonIptables() {
        return jRadioButtonIptables;
    }

    public void setjRadioButtonIptables(JRadioButton jRadioButtonIptables) {
        this.jRadioButtonIptables = jRadioButtonIptables;
    }

    public JRadioButton getjRadioButtonRouter() {
        return jRadioButtonRouter;
    }

    public void setjRadioButtonRouter(JRadioButton jRadioButtonRouter) {
        this.jRadioButtonRouter = jRadioButtonRouter;
    }

    public JRadioButton getjRadioButtonTCFilter() {
        return jRadioButtonTCFilter;
    }

    public void setjRadioButtonTCFilter(JRadioButton jRadioButtonTCFilter) {
        this.jRadioButtonTCFilter = jRadioButtonTCFilter;
    }

    public JTextField getjTextFieldInboundBandwidth() {
        return jTextFieldInboundBandwidth;
    }

    public void setjTextFieldInboundBandwidth(JTextField jTextFieldInboundBandwidth) {
        this.jTextFieldInboundBandwidth = jTextFieldInboundBandwidth;
    }

    public JTextField getjTextFieldIncomingInterface() {
        return jTextFieldIncomingInterface;
    }

    public void setjTextFieldIncomingInterface(JTextField jTextFieldIncomingInterface) {
        this.jTextFieldIncomingInterface = jTextFieldIncomingInterface;
    }

    public JTextField getjTextFieldOutboundBandwidth() {
        return jTextFieldOutboundBandwidth;
    }

    public void setjTextFieldOutboundBandwidth(JTextField jTextFieldOutboundBandwidth) {
        this.jTextFieldOutboundBandwidth = jTextFieldOutboundBandwidth;
    }

    public JTextField getjTextFieldOutgoingInterface() {
        return jTextFieldOutgoingInterface;
    }

    public void setjTextFieldOutgoingInterface(JTextField jTextFieldOutgoingInterface) {
        this.jTextFieldOutgoingInterface = jTextFieldOutgoingInterface;
    }

    public JComboBox getjComboBoxESFQHash() {
        return jComboBoxESFQHash;
    }

    public void setjComboBoxESFQHash(JComboBox jComboBoxESFQHash) {
        this.jComboBoxESFQHash = jComboBoxESFQHash;
    }

    public JTextField getjTextFieldESFQDepth() {
        return jTextFieldESFQDepth;
    }

    public void setjTextFieldESFQDepth(JTextField jTextFieldESFQDepth) {
        this.jTextFieldESFQDepth = jTextFieldESFQDepth;
    }

    public JTextField getjTextFieldESFQDivisor() {
        return jTextFieldESFQDivisor;
    }

    public void setjTextFieldESFQDivisor(JTextField jTextFieldESFQDivisor) {
        this.jTextFieldESFQDivisor = jTextFieldESFQDivisor;
    }

    public JTextField getjTextFieldESFQLimit() {
        return jTextFieldESFQLimit;
    }

    public void setjTextFieldESFQLimit(JTextField jTextFieldESFQLimit) {
        this.jTextFieldESFQLimit = jTextFieldESFQLimit;
    }

    public JTextField getjTextFieldESFQPerturb() {
        return jTextFieldESFQPerturb;
    }

    public void setjTextFieldESFQPerturb(JTextField jTextFieldESFQPerturb) {
        this.jTextFieldESFQPerturb = jTextFieldESFQPerturb;
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupIMQ = new javax.swing.ButtonGroup();
        buttonGroupTrafficFilter = new javax.swing.ButtonGroup();
        buttonGroupMode = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jProgressBar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldInboundBandwidth = new javax.swing.JTextField();
        jTextFieldOutboundBandwidth = new javax.swing.JTextField();
        jTextFieldIncomingInterface = new javax.swing.JTextField();
        jTextFieldOutgoingInterface = new javax.swing.JTextField();
        jRadioButtonIMQYes = new javax.swing.JRadioButton();
        jRadioButtonIMQNo = new javax.swing.JRadioButton();
        jComboBoxACKPackets = new javax.swing.JComboBox();
        jComboBoxClassifier = new javax.swing.JComboBox();
        jComboBoxDefaultQueuingDiscipline = new javax.swing.JComboBox();
        jRadioButtonTCFilter = new javax.swing.JRadioButton();
        jRadioButtonIptables = new javax.swing.JRadioButton();
        jRadioButtonRouter = new javax.swing.JRadioButton();
        jRadioButtonBridge = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldESFQPerturb = new javax.swing.JTextField();
        jTextFieldESFQLimit = new javax.swing.JTextField();
        jTextFieldESFQDepth = new javax.swing.JTextField();
        jTextFieldESFQDivisor = new javax.swing.JTextField();
        jComboBoxESFQHash = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("options");
        setResizable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/shutdown-mini.png"))); // NOI18N
        jButton1.setText("close");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/edit.png"))); // NOI18N
        jButton2.setText("edit");

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel1.setText("Bandwidth");

        jLabel2.setText("Inbound Bandwidth:");

        jLabel3.setText("Outbound Bandwidth:");

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel4.setText("Interfaces");

        jLabel5.setText("Incoming Interface:");

        jLabel6.setText("Outgoing Interface:");

        jLabel7.setText("IMQ:");

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel8.setText("SSN QoS Options");

        jLabel9.setText("ACK packets:");

        jLabel10.setText("Classifier:");

        jLabel11.setText("Default Queuing Discipline:");

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel12.setText("SSN Options");

        jLabel13.setText("Traffic filter:");

        jLabel14.setText("Mode:");

        jRadioButtonIMQYes.setText("yes");

        jRadioButtonIMQNo.setText("no");

        jRadioButtonTCFilter.setText("tc-filter");

        jRadioButtonIptables.setText("iptables");

        jRadioButtonRouter.setText("router");

        jRadioButtonBridge.setText("bridge");

        jLabel15.setText("ESFQ Perturb:");

        jLabel16.setText("ESFQ Limit:");

        jLabel17.setText("ESFQ Depth:");

        jLabel18.setText("ESFQ Divisor:");

        jLabel19.setText("ESFQ Hash:");

        jTextFieldESFQPerturb.setEnabled(false);

        jTextFieldESFQLimit.setEnabled(false);

        jTextFieldESFQDepth.setEnabled(false);

        jTextFieldESFQDivisor.setEnabled(false);

        jComboBoxESFQHash.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldInboundBandwidth)
                            .addComponent(jTextFieldOutboundBandwidth)
                            .addComponent(jTextFieldIncomingInterface)
                            .addComponent(jTextFieldOutgoingInterface)
                            .addComponent(jComboBoxACKPackets, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxClassifier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxDefaultQueuingDiscipline, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldESFQPerturb)
                            .addComponent(jTextFieldESFQLimit)
                            .addComponent(jTextFieldESFQDepth)
                            .addComponent(jTextFieldESFQDivisor)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButtonTCFilter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonIptables))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButtonRouter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonBridge))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButtonIMQYes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButtonIMQNo))
                            .addComponent(jComboBoxESFQHash, 0, 225, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldInboundBandwidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldOutboundBandwidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldIncomingInterface, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldOutgoingInterface, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jRadioButtonIMQYes)
                    .addComponent(jRadioButtonIMQNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxACKPackets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxClassifier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBoxDefaultQueuingDiscipline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldESFQPerturb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextFieldESFQLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextFieldESFQDepth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextFieldESFQDivisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBoxESFQHash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jRadioButtonTCFilter)
                    .addComponent(jRadioButtonIptables))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jRadioButtonRouter)
                    .addComponent(jRadioButtonBridge))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupIMQ;
    private javax.swing.ButtonGroup buttonGroupMode;
    private javax.swing.ButtonGroup buttonGroupTrafficFilter;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBoxACKPackets;
    private javax.swing.JComboBox jComboBoxClassifier;
    private javax.swing.JComboBox jComboBoxDefaultQueuingDiscipline;
    private javax.swing.JComboBox jComboBoxESFQHash;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JRadioButton jRadioButtonBridge;
    private javax.swing.JRadioButton jRadioButtonIMQNo;
    private javax.swing.JRadioButton jRadioButtonIMQYes;
    private javax.swing.JRadioButton jRadioButtonIptables;
    private javax.swing.JRadioButton jRadioButtonRouter;
    private javax.swing.JRadioButton jRadioButtonTCFilter;
    private javax.swing.JTextField jTextFieldESFQDepth;
    private javax.swing.JTextField jTextFieldESFQDivisor;
    private javax.swing.JTextField jTextFieldESFQLimit;
    private javax.swing.JTextField jTextFieldESFQPerturb;
    private javax.swing.JTextField jTextFieldInboundBandwidth;
    private javax.swing.JTextField jTextFieldIncomingInterface;
    private javax.swing.JTextField jTextFieldOutboundBandwidth;
    private javax.swing.JTextField jTextFieldOutgoingInterface;
    // End of variables declaration//GEN-END:variables
}
