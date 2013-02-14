package views.splashscreen;

import controllers.global.ControllerNotifications;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.*;

/**
 *
 * @author skuarch
 */
public class SplashScreen extends JFrame {

    private JProgressBar progressbar = null;
    private JLabel imageLabel = null;
    private ControllerNotifications NOTIFICATIONS = new ControllerNotifications();

    //==========================================================================
    public SplashScreen() {

        progressbar = new JProgressBar();
        imageLabel = new JLabel();
        initComponents();

    } // end SplashScreen2

    //==========================================================================
    private void initComponents() {

        try {

            setIconImage(Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/views/images/sam_icon.png")));
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setUndecorated(true);
            setTitle("Sispro Sniffer Network");
            getRootPane().setWindowDecorationStyle(JRootPane.NONE);
            setLayout(new BorderLayout());

            imageLabel.setIcon(new ImageIcon(SplashScreen.class.getResource("/views/images/splashscreen.png")));
            add(imageLabel, BorderLayout.CENTER);
            progressbar.setIndeterminate(true);
            add(progressbar, BorderLayout.SOUTH);
            progressbar.setString("loading");

            pack();
            setLocationRelativeTo(getContentPane());

        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible show splash screen", e);
        }
    } // end initComponents
} // end class
