package controllers.global;

import controllers.Controller;
import model.common.Context;
import model.dao.DAO;
import views.dialogs.EventViewer;
import views.splashscreen.SplashScreen;

/**
 *
 * @author skuarch
 */
public class ControllerSplashScreen extends Controller {

    private SplashScreen ss = null;
    private EventViewer eventViewer = null;

    //==========================================================================
    public ControllerSplashScreen() {
        ss = new SplashScreen();
    } // end ControllerSplashScreen

    //==========================================================================
    @Override
    public void setupInterface() {

        try {

            ss.setVisible(true);
            loader();
            ss.setVisible(false);

        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible show splash screen", e);
        }

    } // end setupInterface

    //==========================================================================
    private void loader() throws Exception {

        ControllerLogin cl = null;
        
        try {
            
            new DAO().getAll("Collectors");
            new DAO().getAll("Users");   
            
            cl = new ControllerLogin();
            cl.setupInterface();
            
            new Context().getInitialContext();
            eventViewer = EventViewer.getInstance();
            eventViewer.setVisible(false);
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Somethig is wrong in loader,<br>-Please report to administrator", e);
            System.exit(0);
        } finally {
            eventViewer = null;
            cl = null;
        }

    } // end loader    

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        try {
            
            eventViewer = null;
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
    }

    //==========================================================================
    @Override
    protected void finalize() throws Throwable {

        try {
            NOTIFICATIONS.information("clean up ControllerSplashScreen", false);
            destroyCurrentThread();
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        } finally {
            super.finalize();
        }

    } // end finalize
} // end class
