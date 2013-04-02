package controllers.global;

import controllers.Controller;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.swing.JFrame;
import javax.swing.UIManager;
import views.dialogs.EventViewer;

/**
 *
 * @author skuarch
 */
public class ControllerMain extends Controller {

    @Resource(mappedName = "jdbc/sam_4")
    private static DataSource dataSource;

    //==========================================================================
    public static DataSource getDataSource() {
        return dataSource;
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("sam version 4");
            new ControllerMain().setupInterface();
        } catch (Exception e) {
            NOTIFICATIONS.error("please report this problem with the administrator", e);
        }
    }
    //==========================================================================
    /**
     * This method displays a message before the program ends.
     */
    private Thread shutdownThread = new Thread() {
        //======================================================================
        @Override
        public void run() {
            //on exit            
            EventViewer.getInstance().appendInfoTextConsole("** program finished **");
        } // end run
    }; //end shutdownThread

    //==========================================================================
    @Override
    public void setupInterface() {
        ControllerSplashScreen css = null;
        ControllerLogin cl = null;

        try {

            Runtime.getRuntime().addShutdownHook(new ControllerMain().shutdownThread);

            //setting theme
            JFrame.setDefaultLookAndFeelDecorated(true);

            for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(laf.getName())) {
                    UIManager.setLookAndFeel(laf.getClassName());
                }
            }

            //show splashscreen
            css = new ControllerSplashScreen();
            css.setupInterface();

            //show login
            cl = new ControllerLogin();
            cl.setupInterface();
            cl.setVisible(true);


        } catch (Exception e) {
            e.printStackTrace();
        }
    } //end main

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
