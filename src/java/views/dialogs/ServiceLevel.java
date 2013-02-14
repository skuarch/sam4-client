package views.dialogs;

import java.awt.Toolkit;
import javax.swing.*;

/**
 *
 * @author skuarch
 */
public class ServiceLevel extends javax.swing.JDialog {

    //==========================================================================
    /**
     * Creates new form ServiceLevel
     */
    public ServiceLevel(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(getContentPane());
        setIconImage(Toolkit.getDefaultToolkit().getImage(ServiceLevel.class.getResource("/views/images/sam_icon.png")));  
        buttonGroupCQB.add(jRadioButtonBoundedYes);
        buttonGroupCQB.add(jRadioButtonBoundedNo);
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

    public JComboBox getjComboBoxClasifier() {
        return jComboBoxClasifier;
    }

    public void setjComboBoxClasifier(JComboBox jComboBoxClasifier) {
        this.jComboBoxClasifier = jComboBoxClasifier;
    }
    
    public JComboBox getjComboBoxQueuingDiscipline() {
        return jComboBoxQueuingDiscipline;
    }

    public void setjComboBoxQueuingDiscipline(JComboBox jComboBoxQueuingDiscipline) {
        this.jComboBoxQueuingDiscipline = jComboBoxQueuingDiscipline;
    }

    public JProgressBar getjProgressBar() {
        return jProgressBar;
    }

    public void setjProgressBar(JProgressBar jProgressBar) {
        this.jProgressBar = jProgressBar;
    }   

    public JTextField getjTextFieldName() {
        return jTextFieldName;
    }

    public void setjTextFieldName(JTextField jTextFieldName) {
        this.jTextFieldName = jTextFieldName;
    }
    
    public JPanel getjPanelHTB() {
        return jPanelHTB;
    }

    public void setjPanelHTB(JPanel jPanelHTB) {
        this.jPanelHTB = jPanelHTB;
    }

    public JTextField getjTextFieldInMaxDelay() {
        return jTextFieldInMaxDelay;
    }

    public void setjTextFieldInMaxDelay(JTextField jTextFieldInMaxDelay) {
        this.jTextFieldInMaxDelay = jTextFieldInMaxDelay;
    }

    public JTextField getjTextFieldInWorkUnit() {
        return jTextFieldInWorkUnit;
    }

    public void setjTextFieldInWorkUnit(JTextField jTextFieldInWorkUnit) {
        this.jTextFieldInWorkUnit = jTextFieldInWorkUnit;
    }

    public JTextField getjTextFieldInulRate() {
        return jTextFieldInulRate;
    }

    public void setjTextFieldInulRate(JTextField jTextFieldInulRate) {
        this.jTextFieldInulRate = jTextFieldInulRate;
    }

    public JTextField getjTextFieldOutMaxDelay() {
        return jTextFieldOutMaxDelay;
    }

    public void setjTextFieldOutMaxDelay(JTextField jTextFieldOutMaxDelay) {
        this.jTextFieldOutMaxDelay = jTextFieldOutMaxDelay;
    }

    public JTextField getjTextFieldOutRate() {
        return jTextFieldOutRate;
    }

    public void setjTextFieldOutRate(JTextField jTextFieldOutRate) {
        this.jTextFieldOutRate = jTextFieldOutRate;
    }

    public JTextField getjTextFieldOutWorkUnit() {
        return jTextFieldOutWorkUnit;
    }

    public void setjTextFieldOutWorkUnit(JTextField jTextFieldOutWorkUnit) {
        this.jTextFieldOutWorkUnit = jTextFieldOutWorkUnit;
    }

    public JTextField getjTextFieldOutulRate() {
        return jTextFieldOutulRate;
    }

    public void setjTextFieldOutulRate(JTextField jTextFieldOutulRate) {
        this.jTextFieldOutulRate = jTextFieldOutulRate;
    }

    public JComboBox getjComboBoxInPriority() {
        return jComboBoxInPriority;
    }

    public void setjComboBoxInPriority(JComboBox jComboBoxInPriority) {
        this.jComboBoxInPriority = jComboBoxInPriority;
    }

    public JComboBox getjComboBoxOutPriority() {
        return jComboBoxOutPriority;
    }

    public void setjComboBoxOutPriority(JComboBox jComboBoxOutPriority) {
        this.jComboBoxOutPriority = jComboBoxOutPriority;
    }

    public JPanel getjPanelCBQ() {
        return jPanelCBQ;
    }

    public void setjPanelCBQ(JPanel jPanelCBQ) {
        this.jPanelCBQ = jPanelCBQ;
    }

    public JPanel getjPanelHFSC() {
        return jPanelHFSC;
    }

    public void setjPanelHFSC(JPanel jPanelHFSC) {
        this.jPanelHFSC = jPanelHFSC;
    }

    public JTextField getjTextFieldCBQInBandwidth() {
        return jTextFieldCBQInBandwidth;
    }

    public void setjTextFieldCBQInBandwidth(JTextField jTextFieldCBQInBandwidth) {
        this.jTextFieldCBQInBandwidth = jTextFieldCBQInBandwidth;
    }

    public JTextField getjTextFieldCBQOutBandwidth() {
        return jTextFieldCBQOutBandwidth;
    }

    public void setjTextFieldCBQOutBandwidth(JTextField jTextFieldCBQOutBandwidth) {
        this.jTextFieldCBQOutBandwidth = jTextFieldCBQOutBandwidth;
    }

    public JRadioButton getjRadioButtonBoundedNo() {
        return jRadioButtonBoundedNo;
    }

    public void setjRadioButtonBoundedNo(JRadioButton jRadioButtonBoundedNo) {
        this.jRadioButtonBoundedNo = jRadioButtonBoundedNo;
    }

    public JRadioButton getjRadioButtonBoundedYes() {
        return jRadioButtonBoundedYes;
    }

    public void setjRadioButtonBoundedYes(JRadioButton jRadioButtonBoundedYes) {
        this.jRadioButtonBoundedYes = jRadioButtonBoundedYes;
    }

    public JTextField getjTextFieldHFSCInulRate() {
        return jTextFieldHFSCInulRate;
    }

    public void setjTextFieldHFSCInulRate(JTextField jTextFieldHFSCInulRate) {
        this.jTextFieldHFSCInulRate = jTextFieldHFSCInulRate;
    }

    public JPanel getjPanelContainer() {
        return jPanelHTB;
    }

    public void setjPanelContainer(JPanel jPanelContainer) {
        this.jPanelHTB = jPanelContainer;
    }

    public JComboBox getjComboBoxPriority() {
        return jComboBoxPriority;
    }

    public void setjComboBoxPriority(JComboBox jComboBoxPriority) {
        this.jComboBoxPriority = jComboBoxPriority;
    }

    public JTextField getjTextFieldInBandwidth() {
        return jTextFieldInBandwidth;
    }

    public void setjTextFieldInBandwidth(JTextField jTextFieldInBandwidth) {
        this.jTextFieldInBandwidth = jTextFieldInBandwidth;
    }

    public JTextField getjTextFieldInBandwidthBurst() {
        return jTextFieldInBandwidthBurst;
    }

    public void setjTextFieldInBandwidthBurst(JTextField jTextFieldInBandwidthBurst) {
        this.jTextFieldInBandwidthBurst = jTextFieldInBandwidthBurst;
    }

    public JTextField getjTextFieldInBandwidthCeil() {
        return jTextFieldInBandwidthCeil;
    }

    public void setjTextFieldInBandwidthCeil(JTextField jTextFieldInBandwidthCeil) {
        this.jTextFieldInBandwidthCeil = jTextFieldInBandwidthCeil;
    }

    public JTextField getjTextFieldOutBandwidth() {
        return jTextFieldOutBandwidth;
    }

    public void setjTextFieldOutBandwidth(JTextField jTextFieldOutBandwidth) {
        this.jTextFieldOutBandwidth = jTextFieldOutBandwidth;
    }

    public JTextField getjTextFieldOutBandwidthBurst() {
        return jTextFieldOutBandwidthBurst;
    }

    public void setjTextFieldOutBandwidthBurst(JTextField jTextFieldOutBandwidthBurst) {
        this.jTextFieldOutBandwidthBurst = jTextFieldOutBandwidthBurst;
    }

    public JTextField getjTextFieldOutBandwidthCeil() {
        return jTextFieldOutBandwidthCeil;
    }

    public void setjTextFieldOutBandwidthCeil(JTextField jTextFieldOutBandwidthCeil) {
        this.jTextFieldOutBandwidthCeil = jTextFieldOutBandwidthCeil;
    }

    public JComboBox getjComboBoxDistribution() {
        return jComboBoxDistribution;
    }

    public void setjComboBoxDistribution(JComboBox jComboBoxDistribution) {
        this.jComboBoxDistribution = jComboBoxDistribution;
    }

    public JComboBox getjComboBoxHash() {
        return jComboBoxHash;
    }

    public void setjComboBoxHash(JComboBox jComboBoxHash) {
        this.jComboBoxHash = jComboBoxHash;
    }

    public JPanel getjPanelESFQ() {
        return jPanelESFQ;
    }

    public void setjPanelESFQ(JPanel jPanelESFQ) {
        this.jPanelESFQ = jPanelESFQ;
    }

    public JPanel getjPanelNETEM() {
        return jPanelNETEM;
    }

    public void setjPanelNETEM(JPanel jPanelNETEM) {
        this.jPanelNETEM = jPanelNETEM;
    }

    public JTextField getjTextFieldCorrelation() {
        return jTextFieldCorrelation;
    }

    public void setjTextFieldCorrelation(JTextField jTextFieldCorrelation) {
        this.jTextFieldCorrelation = jTextFieldCorrelation;
    }

    public JTextField getjTextFieldDelay() {
        return jTextFieldDelay;
    }

    public void setjTextFieldDelay(JTextField jTextFieldDelay) {
        this.jTextFieldDelay = jTextFieldDelay;
    }

    public JTextField getjTextFieldDepth() {
        return jTextFieldDepth;
    }

    public void setjTextFieldDepth(JTextField jTextFieldDepth) {
        this.jTextFieldDepth = jTextFieldDepth;
    }

    public JTextField getjTextFieldDivisor() {
        return jTextFieldDivisor;
    }

    public void setjTextFieldDivisor(JTextField jTextFieldDivisor) {
        this.jTextFieldDivisor = jTextFieldDivisor;
    }

    public JTextField getjTextFieldDuplication() {
        return jTextFieldDuplication;
    }

    public void setjTextFieldDuplication(JTextField jTextFieldDuplication) {
        this.jTextFieldDuplication = jTextFieldDuplication;
    }

    public JTextField getjTextFieldGap() {
        return jTextFieldGap;
    }

    public void setjTextFieldGap(JTextField jTextFieldGap) {
        this.jTextFieldGap = jTextFieldGap;
    }

    public JTextField getjTextFieldJitter() {
        return jTextFieldJitter;
    }

    public void setjTextFieldJitter(JTextField jTextFieldJitter) {
        this.jTextFieldJitter = jTextFieldJitter;
    }

    public JTextField getjTextFieldLimit() {
        return jTextFieldLimit;
    }

    public void setjTextFieldLimit(JTextField jTextFieldLimit) {
        this.jTextFieldLimit = jTextFieldLimit;
    }

    public JTextField getjTextFieldPacketloss() {
        return jTextFieldPacketloss;
    }

    public void setjTextFieldPacketloss(JTextField jTextFieldPacketloss) {
        this.jTextFieldPacketloss = jTextFieldPacketloss;
    }

    public JTextField getjTextFieldPerturb() {
        return jTextFieldPerturb;
    }

    public void setjTextFieldPerturb(JTextField jTextFieldPerturb) {
        this.jTextFieldPerturb = jTextFieldPerturb;
    }

    public JTextField getjTextFieldReorderCorrelation() {
        return jTextFieldReorderCorrelation;
    }

    public void setjTextFieldReorderCorrelation(JTextField jTextFieldReorderCorrelation) {
        this.jTextFieldReorderCorrelation = jTextFieldReorderCorrelation;
    }

    public JTextField getjTextFieldReorderPercentage() {
        return jTextFieldReorderPercentage;
    }

    public void setjTextFieldReorderPercentage(JTextField jTextFieldReorderPercentage) {
        this.jTextFieldReorderPercentage = jTextFieldReorderPercentage;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupCQB = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jProgressBar = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jComboBoxClasifier = new javax.swing.JComboBox();
        jPanelHTB = new javax.swing.JPanel();
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
        jTextFieldInBandwidth = new javax.swing.JTextField();
        jTextFieldInBandwidthCeil = new javax.swing.JTextField();
        jTextFieldInBandwidthBurst = new javax.swing.JTextField();
        jTextFieldOutBandwidth = new javax.swing.JTextField();
        jTextFieldOutBandwidthCeil = new javax.swing.JTextField();
        jTextFieldOutBandwidthBurst = new javax.swing.JTextField();
        jComboBoxPriority = new javax.swing.JComboBox();
        jPanelHFSC = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldInWorkUnit = new javax.swing.JTextField();
        jTextFieldInMaxDelay = new javax.swing.JTextField();
        jTextFieldInulRate = new javax.swing.JTextField();
        jTextFieldHFSCInulRate = new javax.swing.JTextField();
        jTextFieldOutWorkUnit = new javax.swing.JTextField();
        jTextFieldOutMaxDelay = new javax.swing.JTextField();
        jTextFieldOutRate = new javax.swing.JTextField();
        jTextFieldOutulRate = new javax.swing.JTextField();
        jPanelCBQ = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jTextFieldCBQInBandwidth = new javax.swing.JTextField();
        jRadioButtonBoundedYes = new javax.swing.JRadioButton();
        jRadioButtonBoundedNo = new javax.swing.JRadioButton();
        jTextFieldCBQOutBandwidth = new javax.swing.JTextField();
        jComboBoxInPriority = new javax.swing.JComboBox();
        jComboBoxOutPriority = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBoxQueuingDiscipline = new javax.swing.JComboBox();
        jPanelESFQ = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextFieldPerturb = new javax.swing.JTextField();
        jTextFieldLimit = new javax.swing.JTextField();
        jTextFieldDepth = new javax.swing.JTextField();
        jTextFieldDivisor = new javax.swing.JTextField();
        jComboBoxHash = new javax.swing.JComboBox();
        jPanelNETEM = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jTextFieldDelay = new javax.swing.JTextField();
        jTextFieldJitter = new javax.swing.JTextField();
        jTextFieldCorrelation = new javax.swing.JTextField();
        jComboBoxDistribution = new javax.swing.JComboBox();
        jTextFieldPacketloss = new javax.swing.JTextField();
        jTextFieldDuplication = new javax.swing.JTextField();
        jTextFieldGap = new javax.swing.JTextField();
        jTextFieldReorderPercentage = new javax.swing.JTextField();
        jTextFieldReorderCorrelation = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/shutdown-mini.png"))); // NOI18N
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/add.gif"))); // NOI18N
        jButton2.setText("jButton2");

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel1.setText("General");

        jLabel2.setText("Name:");

        jLabel3.setText("Clasifier:");

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel4.setText("HTB-Inbound");

        jLabel5.setText("In-Bandwidth:");

        jLabel6.setText("In-Bandwidth ceil:");

        jLabel7.setText("In-Bandwidth burst:");

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel8.setText("HTB-Outbound");

        jLabel9.setText("Out-Bandwidth:");

        jLabel10.setText("Out-Bandwidth ceil:");

        jLabel11.setText("Out-Bandwidth burst:");

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel12.setText("HTB-Parameters");

        jLabel13.setText("Priority:");

        javax.swing.GroupLayout jPanelHTBLayout = new javax.swing.GroupLayout(jPanelHTB);
        jPanelHTB.setLayout(jPanelHTBLayout);
        jPanelHTBLayout.setHorizontalGroup(
            jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHTBLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHTBLayout.createSequentialGroup()
                        .addGroup(jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldInBandwidth, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                            .addComponent(jTextFieldInBandwidthCeil)))
                    .addGroup(jPanelHTBLayout.createSequentialGroup()
                        .addGroup(jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(202, 202, 202)
                        .addGroup(jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldOutBandwidthBurst, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldOutBandwidthCeil, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldOutBandwidth, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldInBandwidthBurst)
                            .addComponent(jComboBoxPriority, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelHTBLayout.setVerticalGroup(
            jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHTBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldInBandwidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldInBandwidthCeil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldInBandwidthBurst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldOutBandwidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldOutBandwidthCeil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldOutBandwidthBurst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHTBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBoxPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel25.setText("HFSC-Inbound");

        jLabel16.setText("In-Work-Unit:");

        jLabel17.setText("In-Max-Delay:");

        jLabel18.setText("In-Rate:");

        jLabel19.setText("In-ul-Rate:");

        jLabel20.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel20.setText("HFSC-Outbound");

        jLabel21.setText("Out-Work-Unit");

        jLabel22.setText("Out-Max-Delay:");

        jLabel23.setText("Out-Rate:");

        jLabel24.setText("Out-ul-Rate:");

        javax.swing.GroupLayout jPanelHFSCLayout = new javax.swing.GroupLayout(jPanelHFSC);
        jPanelHFSC.setLayout(jPanelHFSCLayout);
        jPanelHFSCLayout.setHorizontalGroup(
            jPanelHFSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHFSCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHFSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel25)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelHFSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldOutRate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(jTextFieldOutMaxDelay, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldOutWorkUnit, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldHFSCInulRate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldInulRate, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldInMaxDelay, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldInWorkUnit, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldOutulRate))
                .addContainerGap())
        );
        jPanelHFSCLayout.setVerticalGroup(
            jPanelHFSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHFSCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHFSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextFieldInWorkUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHFSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextFieldInMaxDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHFSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextFieldInulRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHFSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldHFSCInulRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHFSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextFieldOutWorkUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHFSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextFieldOutMaxDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHFSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextFieldOutRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelHFSCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextFieldOutulRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel50.setText("Bounded:");

        jLabel51.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel51.setText("CBQ-Inbound");

        jLabel52.setText("In-Bandwidth:");

        jLabel53.setText("In-Priority:");

        jLabel54.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel54.setText("CBQ-Outbound");

        jLabel55.setText("Out-Bandwidth:");

        jLabel56.setText("Out-Priority:");

        jRadioButtonBoundedYes.setText("yes");

        jRadioButtonBoundedNo.setText("no");

        javax.swing.GroupLayout jPanelCBQLayout = new javax.swing.GroupLayout(jPanelCBQ);
        jPanelCBQ.setLayout(jPanelCBQLayout);
        jPanelCBQLayout.setHorizontalGroup(
            jPanelCBQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCBQLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCBQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCBQLayout.createSequentialGroup()
                        .addGroup(jPanelCBQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCBQLayout.createSequentialGroup()
                                .addGroup(jPanelCBQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel51)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel53)
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel56))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelCBQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldCBQOutBandwidth, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBoxInPriority, javax.swing.GroupLayout.Alignment.TRAILING, 0, 222, Short.MAX_VALUE)
                                    .addComponent(jTextFieldCBQInBandwidth, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBoxOutPriority, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanelCBQLayout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanelCBQLayout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButtonBoundedYes, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jRadioButtonBoundedNo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        jPanelCBQLayout.setVerticalGroup(
            jPanelCBQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCBQLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCBQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelCBQLayout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel52))
                    .addComponent(jTextFieldCBQInBandwidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCBQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxInPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelCBQLayout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel54)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCBQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jTextFieldCBQOutBandwidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCBQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jComboBoxOutPriority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCBQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCBQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButtonBoundedYes)
                        .addComponent(jRadioButtonBoundedNo))
                    .addComponent(jLabel50))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel14.setText("Queuing Discipline");

        jLabel15.setText("Queuing Discipline:");

        jLabel33.setText("Perturb:");

        jLabel34.setText("Limit:");

        jLabel35.setText("Depth:");

        jLabel36.setText("Divisor:");

        jLabel38.setText("Hash:");

        javax.swing.GroupLayout jPanelESFQLayout = new javax.swing.GroupLayout(jPanelESFQ);
        jPanelESFQ.setLayout(jPanelESFQLayout);
        jPanelESFQLayout.setHorizontalGroup(
            jPanelESFQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelESFQLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelESFQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel38))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelESFQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldDivisor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldDepth, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldLimit, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldPerturb, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBoxHash, 0, 219, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelESFQLayout.setVerticalGroup(
            jPanelESFQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelESFQLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelESFQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextFieldPerturb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelESFQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTextFieldLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelESFQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextFieldDepth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelESFQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextFieldDivisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelESFQLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jComboBoxHash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel37.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel37.setText("Network delays");

        jLabel39.setText("Delay:");

        jLabel40.setText("Jitter:");

        jLabel41.setText("Correlation:");

        jLabel42.setText("Distribution:");

        jLabel43.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel43.setText("Others functions");

        jLabel44.setText("Packetloss:");

        jLabel45.setText("Duplication:");

        jLabel46.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel46.setText("Re-Ordering");

        jLabel47.setText("Gap:");

        jLabel48.setText("Reorder percentage:");

        jLabel49.setText("Reorder correlation:");

        javax.swing.GroupLayout jPanelNETEMLayout = new javax.swing.GroupLayout(jPanelNETEM);
        jPanelNETEM.setLayout(jPanelNETEMLayout);
        jPanelNETEMLayout.setHorizontalGroup(
            jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNETEMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(jLabel44)
                    .addComponent(jLabel37)
                    .addComponent(jLabel43)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47)
                    .addComponent(jLabel48)
                    .addComponent(jLabel49))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldReorderPercentage, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldGap, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldDuplication, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldPacketloss, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBoxDistribution, javax.swing.GroupLayout.Alignment.TRAILING, 0, 227, Short.MAX_VALUE)
                    .addComponent(jTextFieldCorrelation, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldJitter, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldDelay, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldReorderCorrelation)))
        );
        jPanelNETEMLayout.setVerticalGroup(
            jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNETEMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelNETEMLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jTextFieldJitter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jTextFieldCorrelation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jComboBoxDistribution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jTextFieldPacketloss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jTextFieldDuplication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jTextFieldGap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jTextFieldReorderPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelNETEMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jTextFieldReorderCorrelation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                                .addComponent(jComboBoxQueuingDiscipline, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxClasifier, 0, 232, Short.MAX_VALUE)
                                    .addComponent(jTextFieldName))))
                        .addGap(16, 16, 16))))
            .addComponent(jPanelHTB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelCBQ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelHFSC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelESFQ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelNETEM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxClasifier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelHTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelCBQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelHFSC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBoxQueuingDiscipline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelESFQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelNETEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(179, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupCQB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBoxClasifier;
    private javax.swing.JComboBox jComboBoxDistribution;
    private javax.swing.JComboBox jComboBoxHash;
    private javax.swing.JComboBox jComboBoxInPriority;
    private javax.swing.JComboBox jComboBoxOutPriority;
    private javax.swing.JComboBox jComboBoxPriority;
    private javax.swing.JComboBox jComboBoxQueuingDiscipline;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelCBQ;
    private javax.swing.JPanel jPanelESFQ;
    private javax.swing.JPanel jPanelHFSC;
    private javax.swing.JPanel jPanelHTB;
    private javax.swing.JPanel jPanelNETEM;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JRadioButton jRadioButtonBoundedNo;
    private javax.swing.JRadioButton jRadioButtonBoundedYes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldCBQInBandwidth;
    private javax.swing.JTextField jTextFieldCBQOutBandwidth;
    private javax.swing.JTextField jTextFieldCorrelation;
    private javax.swing.JTextField jTextFieldDelay;
    private javax.swing.JTextField jTextFieldDepth;
    private javax.swing.JTextField jTextFieldDivisor;
    private javax.swing.JTextField jTextFieldDuplication;
    private javax.swing.JTextField jTextFieldGap;
    private javax.swing.JTextField jTextFieldHFSCInulRate;
    private javax.swing.JTextField jTextFieldInBandwidth;
    private javax.swing.JTextField jTextFieldInBandwidthBurst;
    private javax.swing.JTextField jTextFieldInBandwidthCeil;
    private javax.swing.JTextField jTextFieldInMaxDelay;
    private javax.swing.JTextField jTextFieldInWorkUnit;
    private javax.swing.JTextField jTextFieldInulRate;
    private javax.swing.JTextField jTextFieldJitter;
    private javax.swing.JTextField jTextFieldLimit;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldOutBandwidth;
    private javax.swing.JTextField jTextFieldOutBandwidthBurst;
    private javax.swing.JTextField jTextFieldOutBandwidthCeil;
    private javax.swing.JTextField jTextFieldOutMaxDelay;
    private javax.swing.JTextField jTextFieldOutRate;
    private javax.swing.JTextField jTextFieldOutWorkUnit;
    private javax.swing.JTextField jTextFieldOutulRate;
    private javax.swing.JTextField jTextFieldPacketloss;
    private javax.swing.JTextField jTextFieldPerturb;
    private javax.swing.JTextField jTextFieldReorderCorrelation;
    private javax.swing.JTextField jTextFieldReorderPercentage;
    // End of variables declaration//GEN-END:variables
}
