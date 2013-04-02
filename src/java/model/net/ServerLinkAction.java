package model.net;

import java.io.IOException;
import model.beans.GlobalConfiguration;

/**
 *
 * @author skuarch
 */
public class ServerLinkAction {
    
    private GlobalConfiguration globalConfiguration = null;
    private ServerLink serverLink = null;
    private String mainServer;
    private int port;
    private String context;
    private String suffix;
    private int timeOut;

    //==========================================================================
    public ServerLinkAction(String suffix) {
        globalConfiguration = GlobalConfiguration.getInstance();
        mainServer = globalConfiguration.getMainServer();
        port = globalConfiguration.getMainServerPort();
        context = globalConfiguration.getContext();
        timeOut = globalConfiguration.getTimeWaitMessage();
        this.suffix = suffix;
        serverLink = new ServerLink(mainServer, port, context, this.suffix, timeOut);
    } // end Linker

    //==========================================================================
    public Object sendReceiveObject(Object object) throws IOException, Exception {

        Object objectReturned = null;

        try {
            
            serverLink.initConnection();            
            serverLink.sendObject(object);            
            objectReturned = serverLink.receiveObject();            
            
        } catch (IOException ioe) {
            throw ioe;
        } catch (Exception e) {
            throw e;
        } finally {
            serverLink.closeConnections();
        }

        return objectReturned;
    } // end sendReceiveObject

    //==========================================================================
    /**
     * wrap for abort
     */
    public void abort() {
        serverLink.abort();
    } // end abort

    //==========================================================================
    public String getSuffix() {
        return suffix;
    }

    //==========================================================================
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    
} // end class
