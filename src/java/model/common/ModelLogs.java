package model.common;

import java.util.HashMap;
import model.net.Linker;
import model.util.HashMapUtilities;

/**
 *
 * @author skuarch
 */
public class ModelLogs {

    public ModelLogs() {
    }

    //==========================================================================
    public String[] getLogs(String collector) throws Exception {

        String[] jobs = null;
        HashMap hashMap = null;

        try {

            if (collector == null || collector.length() < 1) {
                jobs = new String[1];
                jobs[0] = "no jobs";
            } else {

                hashMap = HashMapUtilities.getHashMapFilter();
                hashMap.put("request", "logs");
                hashMap.put("collector", collector);

                jobs = (String[]) new Linker().sendReceiveObject(hashMap);

                if (jobs == null || jobs.length < 1) {
                    jobs = new String[1];
                    jobs[0] = "no logs";
                }
            }

        } catch (Exception e) {
            throw e;
        }

        return jobs;

    } // end getLogs
}
