package views.panels;

import controllers.global.ControllerNotifications;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import model.net.Linker;
import org.jfree.data.time.RegularTimePeriod;
import views.charts.ShaperLineChartLive;

/**
 *
 * @author skuarch
 */
public class DynamicLineChart extends SnifferPanel {

    private HashMap hm = null;
    private ShaperLineChartLive slcl = null;
    private Thread threadLive = null;
    private boolean shutdown;
    private int seconds = 1000;
    private int count = 0;    
    private long timestamp = 0;

    //==========================================================================
    public DynamicLineChart(HashMap hm) {
        this.hm = hm;
        threadLive = new Thread();
        shutdown = true;
        onLoad();
        doLive();
    } // end DynamicLineChart    

    //==========================================================================
    private void onLoad() {
        setLayout(new BorderLayout());
    }

    //==========================================================================
    private void doLive() {

        threadLive = new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList arrayList;

                try {

                    hm.put("request", "live bandwidth data");
                    hm.put("liveSeconds", seconds);
                    hm.put("count", count);
                    hm.put("firtsRequest", true);

                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);
                    slcl = new ShaperLineChartLive("Live Bandwidth Data", "", "", (String[]) arrayList.get(0));
                    add(slcl.getJPanel(), BorderLayout.CENTER);
                    updateUI();
                    slcl.createSeries();
                    slcl.addSeriesList((RegularTimePeriod[]) arrayList.get(1), (Double[]) arrayList.get(2));
                    timestamp = (Long) arrayList.get(3);

                    hm.remove("firtsRequest");
                    while (shutdown) {

                        hm.put("request", "live bandwidth data");
                        hm.put("count", count);
                        hm.put("timestamp", timestamp + ++count);
                        arrayList = (ArrayList) new Linker().sendReceiveObject(hm);
                        //slcl = new ShaperLineChartLive("Live bandwidth Data", "", "", (String[]) arrayList.get(0));
                        slcl.addSeriesList((RegularTimePeriod[]) arrayList.get(1), (Double[]) arrayList.get(2));

                        threadLive.sleep(seconds);

                        if (!shutdown) {
                            break;
                        }

                    }

                } catch (Exception e) {
                    new ControllerNotifications().error("error please try again", e);
                }
            }
        });
        threadLive.start();
    } // end doLive    

    //==========================================================================
    @Override
    public Object getData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //==========================================================================
    @Override
    public HashMap getHashMap() {
        return hm;
    }

    //==========================================================================
    @Override
    public void destroy() {

        try {

            if (threadLive != null) {
                threadLive.interrupt();
                threadLive.stop();
            }

        } catch (Exception e) {
            new ControllerNotifications().error("Error destroying live data", e);
        } finally {
            shutdown = false;
            threadLive = null;
            slcl = null;
        }

    }

    //==========================================================================
    @Override
    protected void finalize() throws Throwable {
        try {
            destroy();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.finalize();
        }
    }
}
