package controllers.global;

import views.dialogs.EventViewer;
import views.notifications.Alert;

/**
 *
 * @author skuarch
 */
public class ControllerNotifications {

    //==========================================================================
    public ControllerNotifications() {
    } // end ControllerNotifications

    //==========================================================================
    public void information(String message, boolean showAlert) {

        if (message == null || message.length() < 1) {
            return;
        }

        Alert alert = null;

        try {

            EventViewer.getInstance().appendInfoTextConsole(message);

            if (showAlert) {
                alert = new Alert();
                alert.showAlert(0, message);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    } // end error

    //==========================================================================
    public void warning(String message, boolean showAlert) {

        if (message == null || message.length() < 1) {
            return;
        }

        Alert alert = null;

        try {

            EventViewer.getInstance().appendWarmTextConsole(message);

            if (showAlert) {
                alert = new Alert();
                alert.showAlert(1, message);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    } // end error

    //==========================================================================
    public void error(String userError, Exception e) {

        if (userError == null || userError.length() < 1) {
            return;
        }

        if (e == null) {
            return;
        }

        Alert alert = null;

        try {

            alert = new Alert();

            //show alert and save message in eventviewer
            if (e.getMessage() != null) {
                EventViewer.getInstance().appendErrorTextConsole(e.getLocalizedMessage() + " " + e.getStackTrace()[0].getMethodName() + "()" + " " + e.getStackTrace()[0].getClassName() + " Line:" + e.getStackTrace()[0].getLineNumber());
                userError = "<html>An error occurred in the system please try again later<br><br>-" + userError + "<br>" + e.getMessage() + "<html>";
                alert.showAlert(2, userError);
            } else {
                EventViewer.getInstance().appendErrorTextConsole(e.getStackTrace()[0].getMethodName() + "()" + " " + e.getStackTrace()[0].getClassName() + " Line:" + e.getStackTrace()[0].getLineNumber());
                userError = "<html>An error occurred in the system please try again later<br><br>-" + userError + "<html>";
                alert.showAlert(2, userError);
            }

            System.err.println("-----------------------------------stackTrace-----------------------------------");
            e.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    } // end error
} // end class