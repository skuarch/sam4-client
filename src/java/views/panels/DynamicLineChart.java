package views.panels;

import controllers.global.ControllerNotifications;
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
    private int seconds = 30000;

    //==========================================================================
    public DynamicLineChart(HashMap hm) {
        this.hm = hm;
        threadLive = new Thread();
        shutdown = true;
        doLive();
    } // end DynamicLineChart    

    //==========================================================================
    private void doLive() {

        threadLive = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList arrayList;

                try {

                    hm.put("request", "live bandwidth data");
                    hm.put("liveSeconds", seconds);
                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);
                    slcl = new ShaperLineChartLive("Live bandwidth Data", "", "", (String[]) arrayList.get(0));
                    slcl.createSeries();
                    slcl.addSeriesList((RegularTimePeriod[]) arrayList.get(1), (Double[]) arrayList.get(2));

                    while (shutdown) {

                        threadLive.sleep(seconds);

                        if (!shutdown) {
                            break;
                        }

                        hm.put("request", "live bandwidth data");
                        arrayList = (ArrayList) new Linker().sendReceiveObject(hm);
                        slcl = new ShaperLineChartLive("Live bandwidth Data", "", "", (String[]) arrayList.get(0));
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
            threadLive.interrupt();
            threadLive.stop();
            shutdown = false;
            threadLive = null;
        } catch (Exception e) {
        }

    }

    //==========================================================================
    @Override
    protected void finalize() throws Throwable {
        try {
            destroy();
        } catch (Exception e) {
        } finally {
            super.finalize();
        }
    }
}
