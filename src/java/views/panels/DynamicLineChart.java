package views.panels;

import controllers.global.ControllerNotifications;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import model.net.Linker;
import model.util.HashMapUtilities;
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
    private int seconds = 500;
    private int count = 0;
    private long timestamp = 0;
    private long delay = 0;
    private long startDate = 0;
    private long endDate = 0;
    private long now = 0;
    private long tmp = 0;

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
                HashMap hm2 = null;

                try {

                    hm.put("request", "live bandwidth data");
                    hm.put("liveSeconds", seconds);
                    hm.put("count", count);
                    hm.put("firtsRequest", true);

                    System.out.println("hm1 " + hm);

                    startDate = System.currentTimeMillis();
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);
                    endDate = System.currentTimeMillis();

                    delay = endDate - startDate;

                    slcl = new ShaperLineChartLive("Live Bandwidth Data", "", "", (String[]) arrayList.get(0));
                    add(slcl.getJPanel(), BorderLayout.CENTER);
                    updateUI();
                    slcl.createSeries();
                    slcl.addSeriesList((RegularTimePeriod[]) arrayList.get(1), (Double[]) arrayList.get(2));
                    timestamp = (Long) arrayList.get(3);


                    //ya no es la primera requicision
                    hm2 = HashMapUtilities.createHashMapFromHashMap(hm);
                    hm2.put("firtsRequest", false);

                    while (shutdown) {

                        now = System.currentTimeMillis();
                        tmp = now;
                        
                        while ((tmp - now) < (1000 - delay)) {
                            tmp = System.currentTimeMillis();
                        }

                        if (!shutdown) {
                            break;
                        }

                        //======================================================
                        hm2.put("request", "live bandwidth data");
                        hm2.put("count", count);
                        hm2.put("timestamp", timestamp + ++count);
                        
                        startDate = System.currentTimeMillis();
                        arrayList = (ArrayList) new Linker().sendReceiveObject(hm2);
                        endDate = System.currentTimeMillis();
                        delay = endDate - startDate;

                        slcl.addSeriesList((RegularTimePeriod[]) arrayList.get(1), (Double[]) arrayList.get(2));

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
