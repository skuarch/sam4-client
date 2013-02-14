package model.util;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author skuarch
 */
public class IOUtilities {

    //==========================================================================
    /**
     * close the socket and set it to null.
     *
     * @param socket
     */
    public static void closeSocket(Socket socket) {

        try {

            if (socket != null) {
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket = null;
        }

    } // end closeSocket

    //==========================================================================
    /**
     * close the inputStream and set it to null.
     *
     * @param inputStream
     */
    public static void closeInputStream(InputStream inputStream) {

        try {

            if (inputStream != null) {
                inputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream = null;
        }

    } // end closeInputStream

    //==========================================================================
    /**
     * close the outputStream and set it to null.
     *
     * @param outputStream
     */
    public static void closeOutputStream(OutputStream outputStream) {

        try {

            if (outputStream != null) {
                outputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            outputStream = null;
        }

    } // end closeOutputStream

    //==========================================================================
    /**
     * close the fileWriter and set it to null.
     *
     * @param fileWriter
     */
    public static void closeFileWriter(FileWriter fileWriter) {

        try {

            if (fileWriter != null) {
                fileWriter.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileWriter = null;
        }

    } // end closeFileWriter

    //==========================================================================
    /**
     * close the printWriter and set it to null.
     *
     * @param fileWriter
     */
    public static void closePrintWriter(PrintWriter printWriter) {

        try {

            if (printWriter != null) {
                printWriter.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            printWriter = null;
        }

    } // end closeFileWriter    

    //==========================================================================
    /**
     * close ServerSocket and set it to null.
     */
    public static void closeServerSocket(ServerSocket serverSocket) {

        try {

            if (serverSocket != null) {
                serverSocket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket = null;
        }

    } //closeServerSocket
    
    //==========================================================================
    public static void disconnectURL(HttpURLConnection httpURLConnection){
        
        if(httpURLConnection != null){
            httpURLConnection.disconnect();
        }
        
    }
    
} // end class 