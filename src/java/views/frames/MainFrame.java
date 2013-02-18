package views.frames;

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author skuarch
 */
public class MainFrame extends javax.swing.JFrame {

    //==========================================================================
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        setLocationRelativeTo(getRootPane());
        setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/views/images/sam_icon.png")));
        //setExtendedState(MAXIMIZED_BOTH);
        setTitle("Sispro Application Manager");
    }

    public JLabel getjLabelMessage() {
        return jLabelMessage;
    }

    public JButton getjButtonE2E() {
        return jButtonE2E;
    }

    public JButton getjButtonFilter() {
        return jButtonFilter;
    }

    public JButton getjButtonFirewall() {
        return jButtonFirewall;
    }

    public JButton getjButtonScanner() {
        return jButtonScanner;
    }

    public JButton getjButtonShaper() {
        return jButtonShaper;
    }

    public JMenu getjMenuConfiguration() {
        return jMenuConfiguration;
    }

    public JMenu getjMenuFile() {
        return jMenuFile;
    }

    public JMenuItem getjMenuItemExit() {
        return jMenuItemExit;
    }

    public JMenuItem getjMenuItemUserConfiguration() {
        return jMenuItemUserConfiguration;
    }

    public JButton getjButtonSniffer() {
        return jButtonSniffer;
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

        jButtonFilter = new javax.swing.JButton();
        jButtonScanner = new javax.swing.JButton();
        jButtonShaper = new javax.swing.JButton();
        jButtonSniffer = new javax.swing.JButton();
        jButtonFirewall = new javax.swing.JButton();
        jButtonE2E = new javax.swing.JButton();
        jLabelMessage = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuConfiguration = new javax.swing.JMenu();
        jMenuItemUserConfiguration = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jButtonFilter.setBackground(new java.awt.Color(255, 255, 255));
        jButtonFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/FilterTraffic.png"))); // NOI18N

        jButtonScanner.setBackground(new java.awt.Color(255, 255, 255));
        jButtonScanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/PortScanner.png"))); // NOI18N

        jButtonShaper.setBackground(new java.awt.Color(255, 255, 255));
        jButtonShaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/Shaper.png"))); // NOI18N

        jButtonSniffer.setBackground(new java.awt.Color(255, 255, 255));
        jButtonSniffer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/NetworkAnalyzer.png"))); // NOI18N

        jButtonFirewall.setBackground(new java.awt.Color(255, 255, 255));
        jButtonFirewall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/Firewall.png"))); // NOI18N

        jButtonE2E.setBackground(new java.awt.Color(255, 255, 255));
        jButtonE2E.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/EndToEnd.png"))); // NOI18N

        jLabelMessage.setForeground(new java.awt.Color(255, 51, 51));

        jMenuFile.setText("File");

        jMenuItemExit.setText("exit");
        jMenuFile.add(jMenuItemExit);

        jMenuBar1.add(jMenuFile);

        jMenuConfiguration.setText("Configuration");

        jMenuItemUserConfiguration.setText("user configuration");
        jMenuConfiguration.add(jMenuItemUserConfiguration);

        jMenuBar1.add(jMenuConfiguration);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonFilter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonScanner)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonShaper))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonSniffer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonFirewall)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonE2E)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabelMessage)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonShaper)
                    .addComponent(jButtonScanner)
                    .addComponent(jButtonFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSniffer)
                    .addComponent(jButtonFirewall)
                    .addComponent(jButtonE2E))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonE2E;
    private javax.swing.JButton jButtonFilter;
    private javax.swing.JButton jButtonFirewall;
    private javax.swing.JButton jButtonScanner;
    private javax.swing.JButton jButtonShaper;
    private javax.swing.JButton jButtonSniffer;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuConfiguration;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemUserConfiguration;
    // End of variables declaration//GEN-END:variables
}