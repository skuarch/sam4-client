package model.util;

import controllers.global.ControllerNotifications;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author skuarch
 */
public class HashMapUtilities {

    //==========================================================================
    public HashMapUtilities() {
    } // end hashMapsUtilities

    //==========================================================================
    public static HashMap getHashMapSniffer() {

        HashMap hashMap = new HashMap();
        hashMap.put("request", "");
        hashMap.put("view", "not applicable");
        hashMap.put("type", "sniffer");
        hashMap.put("collector", "");
        hashMap.put("job", "");
        hashMap.put("dates", "not applicable");
        hashMap.put("drillDown", "not applicable");
        hashMap.put("limits", "not applicable");
        hashMap.put("netmask", "not applicable");
        hashMap.put("subnet", "not applicable");
        hashMap.put("webserverhost", "not applicable");
        hashMap.put("typeService", "not applicable");
        hashMap.put("typeProtocol", "not applicable");
        hashMap.put("networkProtocol", "not applicable");
        hashMap.put("ipProtocol", "not applicable");
        hashMap.put("tcpProtocol", "not applicable");
        hashMap.put("udpProtocol", "not applicable");
        hashMap.put("liveSeconds", "not applicable");
        hashMap.put("ipAddress", "not applicable");
        hashMap.put("websites", "not applicable");
        hashMap.put("portNumber", "not applicable");
        hashMap.put("hostname", "not applicable");
        hashMap.put("isTable", "false");

        return hashMap;

    } // end getHashMapSniffer

    //==========================================================================
    public static HashMap getHashMapFirewall() {

        HashMap hashMap = new HashMap();
        hashMap.put("request", "");
        hashMap.put("type", "firewall");
        hashMap.put("collector", "");
        hashMap.put("flow", "not applicable");
        hashMap.put("direction", "not applicable");
        hashMap.put("protocol", "not applicable");
        hashMap.put("fromIp", "not applicable");
        hashMap.put("fromPort", "not applicable");
        hashMap.put("toIp", "not applicable");
        hashMap.put("toPort", "not applicable");

        return hashMap;

    } // end getHashMapSniffer

    //==========================================================================
    public static HashMap getHashMapEndToEnd() {

        HashMap hashMap = new HashMap();
        hashMap.put("request", "");
        hashMap.put("type", "e2e");
        hashMap.put("collector", "");
        hashMap.put("source", "not applicable");
        hashMap.put("destination", "not applicable");

        return hashMap;

    } // end getHashMapEndToEnd

    //==========================================================================
    public static HashMap getHashMapShaper() {

        HashMap hashMap = new HashMap();
        hashMap.put("request", "");
        hashMap.put("type", "shaper");
        hashMap.put("collector", "");

        return hashMap;
    }

    //==========================================================================
    public static HashMap getHashMapFilter() {

        HashMap hashMap = new HashMap();
        hashMap.put("request", "");
        hashMap.put("type", "filter");
        hashMap.put("collector", "");
        hashMap.put("view", "not applicable");                
        hashMap.put("job", "");
        hashMap.put("limits", "not applicable");

        return hashMap;
    }

    //==========================================================================
    public static HashMap getHashMapPortScanner() {

        HashMap hashMap = new HashMap();
        hashMap.put("request", "");
        hashMap.put("type", "portScanner");
        hashMap.put("collector", "");
        hashMap.put("target", "");

        return hashMap;
    }

    //==========================================================================
    public static HashMap getHashMapObjectRequester() {

        HashMap hashMap = new HashMap();
        hashMap.put("request", "");
        hashMap.put("type", "objectRequester");

        return hashMap;
    }

    //==========================================================================
    public static HashMap createHashMapFromHashMap(HashMap hashMap) {

        HashMap newHashMap = new HashMap();
        Iterator it = null;

        try {

            it = hashMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                newHashMap.put(e.getKey(), e.getValue());
            }

        } catch (Exception e) {
            new ControllerNotifications().error("Unexpected error", e);
        }

        return newHashMap;

    } // end createHashMapFromHashMap

    //==========================================================================
    public static String[][] hashMapsToArray(HashMap hashMap) throws Exception {

        if (hashMap == null) {
            throw new NullPointerException("hashmap is null");
        }

        String[][] array = {
            {"view", hashMap.get("view") + ""},
            {"job", hashMap.get("job") + ""},
            {"collector", hashMap.get("collector") + ""},
            {"dates", hashMap.get("dates") + ""},
            {"drill down", hashMap.get("drillDown") + ""},
            {"limits", hashMap.get("limits") + ""},
            {"netmask", hashMap.get("netmask") + ""},
            {"subnet", hashMap.get("subnet") + ""},
            {"web servers hosts", hashMap.get("webserverhost") + ""},
            {"type service", hashMap.get("typeService") + ""},
            {"type protocol", hashMap.get("typeProtocol") + ""},
            {"network protocols", hashMap.get("networkProtocol") + ""},
            {"ip protocols", hashMap.get("ipProtocol") + ""},
            {"tcp protocols", hashMap.get("tcpProtocol") + ""},
            {"udp protocols", hashMap.get("udpProtocol") + ""},
            {"live seconds", hashMap.get("liveSeconds") + ""},
            {"ip address", hashMap.get("ipAddress") + ""},
            {"websites", hashMap.get("websites") + ""},
            {"is table", hashMap.get("isTable") + ""},
            {"port number", hashMap.get("portNumber") + ""},
            {"hostname", hashMap.get("hostname") + ""}
        };

        return array;
    }
} // end class
