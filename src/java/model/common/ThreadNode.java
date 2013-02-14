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
public class ThreadNode extends Thread {

    private String server = null;
    private ArrayList<DefaultMutableTreeNode> arrayNodes = null;

    //==========================================================================
    public ThreadNode(String server, ArrayList<DefaultMutableTreeNode> arrayNodes) {
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
        String[] jobs = null;

        try {

            connectivity = new Connectivity().requestConnectivity(server);

            synchronized (arrayNodes) {

                if (connectivity) {

                    //requesting jobs
                    jobs = new ModelJobs().getJobs(server);

                    node = new DefaultMutableTreeNode(server);

                    if (jobs == null || jobs.length < 1) {
                        noConnection = new DefaultMutableTreeNode("without jobs");
                        node.insert(noConnection, 0);
                        arrayNodes.add(node);
                        return;
                    }

                    for (int i = 0; i < jobs.length; i++) {
                        node.insert(new DefaultMutableTreeNode(jobs[i]), i);
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
} // end class

