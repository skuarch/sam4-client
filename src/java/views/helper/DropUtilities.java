package views.helper;

import controllers.global.ControllerNotifications;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDropEvent;

/**
 *
 * @author skuarch
 */
public class DropUtilities {

    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();

    //==========================================================================
    public DropUtilities() {
    } // end DropUtilities

    //==========================================================================
    public static String getStringFromDrop(DropTargetDropEvent dtde) {

        String stringDrop = null;
        Transferable transferable = null;
        String[] string = null;
        String tmp = null;

        try {

            transferable = dtde.getTransferable();
            DataFlavor[] dataFlavors = transferable.getTransferDataFlavors();
            dtde.acceptDrop(DnDConstants.ACTION_COPY);
            string = new String[dataFlavors.length];

            for (int i = 0; i < dataFlavors.length; i++) {

                if (dataFlavors[i].getRepresentationClass().equals(Class.forName("java.lang.String"))) {

                    tmp = (String) transferable.getTransferData(dataFlavors[i]);
                    string[i] = tmp;
                }

            } // end for

            stringDrop = string[dataFlavors.length - 1];

        } catch (Exception e) {
            NOTIFICATIONS.error("error in drop", e);
        }

        return stringDrop;

    } // end getStringFromDrop

    //=========================================================================
    public static boolean checkDrop(String selected) {

        if (selected == null || selected.equalsIgnoreCase("")) {
            NOTIFICATIONS.error("selected is null or empty", new NullPointerException());
            return false;
        }

        boolean flag = false;

        try {

            if (selected.equalsIgnoreCase("Bandwidth")) {
                flag = false;
            } else if (selected.equalsIgnoreCase("TCP-UDP")) {
                flag = false;
            } else if (selected.equalsIgnoreCase("Protocols")) {
                flag = false;
            } else if (selected.equalsIgnoreCase("Generic")) {
                flag = false;
            } else if (selected.equalsIgnoreCase("Host")) {
                flag = false;
            } else if (selected.equalsIgnoreCase("Conversations")) {
                flag = false;
            } else if (selected.equalsIgnoreCase("Web")) {
                flag = false;
            } else if (selected.equalsIgnoreCase("QoS")) {
                flag = false;
            } else if (selected.equalsIgnoreCase("Tools")) {
                flag = false;
            } else {
                flag = true;
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("error in checkDrop ", e);
        }

        return flag;

    } // end checkDrop
}
