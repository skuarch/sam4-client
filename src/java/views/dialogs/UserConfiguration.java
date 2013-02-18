package views.dialogs;

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import views.frames.MainFrame;

/**
 *
 * @author skuarch
 */
public class UserConfiguration extends JDialog {

    //==========================================================================
    /**
     * Creates new form UserConfiguration
     */
    public UserConfiguration(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(getRootPane());
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/views/images/sam_icon.png")));
        setTitle("user configuration");
    }

    public JButton getjButtonClose() {
        return jButtonClose;
    }

    public JButton getjButtonSave() {
        return jButtonSave;
    }

    public JButton getjButtonTest() {
        return jButtonTest;
    }

    public JTextArea getjTextAreaOut() {
        return jTextAreaOut;
    }

    public JTextField getjTextFieldMainServer() {
        return jTextFieldMainServer;
    }

    public JTextField getjTextFieldPort() {
        return jTextFieldPort;
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

        jLabel1 = new javax.swing.JLabel();
        jTextFieldMainServer = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPort = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaOut = new javax.swing.JTextArea();
        jButtonTest = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jProgressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Main server ip, hostname or domain");

        jLabel2.setText("Main server port");

        jTextFieldPort.setText("8080");

        jTextAreaOut.setColumns(20);
        jTextAreaOut.setRows(5);
        jTextAreaOut.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaOut);

        jButtonTest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/test.png"))); // NOI18N
        jButtonTest.setText("test connection");

        jButtonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/cancel.png"))); // NOI18N
        jButtonClose.setText("close");

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/disk.png"))); // NOI18N
        jButtonSave.setText("save");
        jButtonSave.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldMainServer)
                    .addComponent(jTextFieldPort)
                    .addComponent(jScrollPane1)
                    .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 78, Short.MAX_VALUE)
                        .addComponent(jButtonClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTest)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldMainServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTest)
                    .addComponent(jButtonClose)
                    .addComponent(jButtonSave))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonTest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaOut;
    private javax.swing.JTextField jTextFieldMainServer;
    private javax.swing.JTextField jTextFieldPort;
    // End of variables declaration//GEN-END:variables
}