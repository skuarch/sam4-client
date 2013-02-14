package model.util;

import java.net.InetAddress;

/**
 *
 * @author skuarch
 */
public class NetUtilities {

    //==========================================================================
    public static boolean ping(String ipAddress) throws Exception {

        boolean flag;

        InetAddress in = InetAddress.getByName(ipAddress);

        if (in.isReachable(2000)) {
            flag = false;
        } else {
            flag = true;
        }

        return flag;
    } // end ping
} // end NetUtilities
