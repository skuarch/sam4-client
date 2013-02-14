package model.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;
import model.util.IOUtilities;

/**
 * this class is for send and reveice object from main server<br/> the flow of
 * this class is.<br/> 1.- open connection.<br/> 2.- send object.<br/> 3.-
 * receive object.<br/> 4.- close connections.<br/> you must to close the
 * connection for prevent bad behavior.
 *
 * @author skuarch
 */
class ServerLink {

    private boolean shutdown = false;
    private Object objectReturned = null;
    private URL url = null;
    private HttpURLConnection hurlc = null;
    private ObjectOutputStream objectOutputStream = null;
    private OutputStream outputStream = null;
    private InputStream inputStream = null;
    private ObjectInputStream objectInputStream = null;
    private Exception exception = null;
    private String mainServer;
    private int port;
    private String context;
    private String suffix;
    private int timeOut;

    //==========================================================================
    /**
     * create a instance.
     *
     * @param mainServer String
     * @param port int
     * @param context String
     * @param suffix String
     * @param timeOut int
     */
    protected ServerLink(String mainServer, int port, String context, String suffix, int timeOut) {
        this.mainServer = mainServer;
        this.port = port;
        this.context = context;
        this.suffix = suffix;
        this.timeOut = timeOut;
    } // end ServerLink    

    //==========================================================================
    /**
     * set up the connection
     *
     * @throws MalformedURLException
     * @throws IOException
     * @throws UnexpectedException
     */
    protected void initConnection() throws MalformedURLException, IOException, UnexpectedException {

        if (mainServer == null || mainServer.length() < 1) {
            throw new NullPointerException("Please configure the main server in<br/>configuration >> user configuration");
        }

        if (port == 0) {
            throw new NullPointerException("Please configure the main server port in<br/>configuration >> user configuration");
        }

        if (context == null || context.length() < 1) {
            throw new NullPointerException("Please configure the main server context in<br/>configuration >> user configuration");
        }

        if (timeOut < 1000) {
            throw new UnexpectedException("time out is less than 1000 miliseconds");
        }

        url = new URL("http://" + mainServer + ":" + port + "/" + context + suffix);
        hurlc = (HttpURLConnection) url.openConnection();
        hurlc.setConnectTimeout(timeOut);
        hurlc.setReadTimeout(timeOut);

        hurlc.setRequestMethod("POST");
        hurlc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        hurlc.setRequestProperty("Content-Language", "en-US");
        hurlc.setRequestProperty("access", "1");
        hurlc.setDoOutput(true);

    } // end initConnection

    //==========================================================================
    /**
     * send object to main server
     *
     * @param object Object
     * @throws IOException
     */
    protected void sendObject(Object object) throws IOException {

        if (object == null) {
            throw new NullPointerException("object is null");
        }

        outputStream = hurlc.getOutputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);

        //sending object
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();

    } // end sendObject   

    //==========================================================================
    /**
     * receive object from main server.
     *
     * @return Object
     * @throws IOException
     * @throws Exception
     */
    protected Object receiveObject() throws IOException, Exception {        

        //receiving response
        inputStream = hurlc.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

        while (!shutdown) {
            objectReturned = objectInputStream.readObject();
            if (objectReturned != null) {
                break;
            }
        }

        //checking the response
        if (objectReturned == null) {
            throw new NullPointerException("the return from main server is null");
        }

        //if the response is a exception, throw it.
        if (objectReturned.getClass().toString().contains("Exception")) {
            exception = (Exception) objectReturned;
            throw exception;
        }

        return objectReturned;

    } // end receiveObject  

    //==========================================================================
    /**
     * abort the send and receive.
     */
    protected void abort() {
        shutdown = true;
    } // abort

    //==========================================================================
    /**
     * close the connections
     */
    protected void closeConnections() {
        IOUtilities.disconnectURL(hurlc);
        IOUtilities.closeOutputStream(outputStream);
        IOUtilities.closeOutputStream(objectOutputStream);
        IOUtilities.closeInputStream(inputStream);
        IOUtilities.closeInputStream(objectInputStream);
        url = null;
        hurlc = null;
        exception = null;
    } // end closeConnections
    
} // end class