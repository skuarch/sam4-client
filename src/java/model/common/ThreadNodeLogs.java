package model.common;

import controllers.global.ControllerNotifications;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import model.util.Thrower;
import views.dialogs.EventViewer;

/**
 *
 * @author skuarch
 */
public class ThreadNodeLogs extends Thread {

    private String server = null;
    private ArrayList<DefaultMutableTreeNode> arrayNodes = null;

    //==========================================================================
    public ThreadNodeLogs(String server, ArrayList<DefaultMutableTreeNode> arrayNodes) {
        this.server = server;
        this.arrayNodes = arrayNodes;
    } // end ThreadNode

    //==========================================================================
    @Override
    public void run() {

        if (server == null) {
            new Thrower().exception(new NullPointerException("server is null"));
            return;
        }

        if (arrayNodes == null) {
            new Thrower().exception(new NullPointerException("arrayNodes is null"));
            return;
        }

        DefaultMutableTreeNode node = null;
        boolean connectivity = false;
        DefaultMutableTreeNode noConnection = null;
        String[] logs = null;

        try {

            connectivity = new Connectivity().requestConnectivity(server);

            synchronized (arrayNodes) {

                if (connectivity) {

                    //requesting logs
                    logs = new ModelLogs().getLogs(server);

                    node = new DefaultMutableTreeNode(server);

                    if (logs == null || logs.length < 1) {
                        noConnection = new DefaultMutableTreeNode("without logs");
                        node.insert(noConnection, 0);
                        arrayNodes.add(node);
                        return;
                    }

                    for (int i = 0; i < logs.length; i++) {
                        node.insert(new DefaultMutableTreeNode(logs[i]), i);
                    }

                    arrayNodes.add(node);

                } else {

                    //does not exist connectivity
                    EventViewer.getInstance().appendWarmTextConsole("no response from server " + server);
                    node = new DefaultMutableTreeNode(server);
                    noConnection = new DefaultMutableTreeNode("without connection");
                    node.insert(noConnection, 0);

                    arrayNodes.add(node);
                }

            }

        } catch (SocketTimeoutException ste) {
            new ControllerNotifications().error("No response from server " + ste.getMessage(), ste);
        } catch (Exception e) {
            new ControllerNotifications().error("we have a problems with the remote server " + server + "  " + e.getMessage(), e);
            //new Thrower().exception(e);
        }

    } // end run
}
