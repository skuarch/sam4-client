package model.common;

import java.io.IOException;
import java.util.HashMap;
import model.net.Linker;
import model.util.HashMapUtilities;

/**
 *
 * @author skuarch
 */
public class Connectivity {

    //==========================================================================
    public Connectivity() throws Exception {
    } // end Connectivity    

    //==========================================================================
    public boolean requestConnectivity(String host) throws IOException, Exception {

        if (host == null || host.length() < 1) {
            throw new NullPointerException("host is empty or null");
        }

        boolean flag = false;
        Object object = null;
        HashMap hashMap = HashMapUtilities.getHashMapSniffer();

        try {

            hashMap.put("type", "sniffer");
            hashMap.put("request", "connectivity");
            hashMap.put("collector", host);

            object = new Linker().sendReceiveObject(hashMap);

            if (object != null && object instanceof Boolean) {
                flag = (Boolean) object;

            }

        } catch (IOException ioe) {
            flag = false;
            throw ioe;
        } catch (Exception e) {
            flag = false;
            throw e;
        }

        return flag;

    } // end requestConnectivity
} // end class
