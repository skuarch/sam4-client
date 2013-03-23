package views.charts;

import controllers.global.ControllerNotifications;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author skuarch
 */
public class ShaperLineChartLive extends ApplicationFrame {

    private static final ControllerNotifications NOTIFICATIONS = null;
    private TimeSeriesCollection dataset = null;
    private String title = null;
    private String titlex = null;
    private String titley = null;
    private TimeSeries series = null;
    private String[] nameSeries = null;
    private ArrayList<TimeSeries> seriesList = new ArrayList<TimeSeries>();
    private TimeSeries s2 = null;

    //==========================================================================
    public ShaperLineChartLive(String title, String titlex, String titley, String seriesName1) {
        super(title);
        this.title = title;
        this.titlex = titlex;
        this.titley = titley;
        this.series = new TimeSeries(seriesName1);
        dataset = new TimeSeriesCollection(this.series);
    } //  end ShaperLineChartLive

    //==========================================================================
    public ShaperLineChartLive(String title, String titlex, String titley, String[] nameSeries) {
        super(title);
        this.title = title;
        this.titlex = titlex;
        this.titley = titley;
        this.nameSeries = nameSeries;
        dataset = new TimeSeriesCollection();
    }

    //==========================================================================
    public void addSeries(final RegularTimePeriod regularTimePeriod, final double number) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                series.addOrUpdate(regularTimePeriod, number);
            }
        });
        t.start();
    }

    //==========================================================================
    public void addSeriesList(final RegularTimePeriod regularTimePeriod, final Double[] number) {

        Thread t;
        t = new Thread(new Runnable() {
            public void run() {

                for (int i = 0; i < seriesList.size(); i++) {
                    seriesList.get(i).addOrUpdate(regularTimePeriod, (double) number[i]);
                }

            }
        });
        t.start();

    }

    //==========================================================================
    public void addSeriesList(final RegularTimePeriod[] regularTimePeriod, final Double[] number) {

        Thread t;
        t = new Thread(new Runnable() {
            public void run() {

                for (int i = 0; i < seriesList.size(); i++) {
                    seriesList.get(i).addOrUpdate(regularTimePeriod[i], (double) number[i]);                    
                    System.out.println(regularTimePeriod[i] + "   " + (double) number[i]);
                }

            }
        });
        t.start();

    }

    //==========================================================================
    public void createSeries() {
        try {

            for (String name : nameSeries) {

                s2 = new TimeSeries(name);
                seriesList.add(s2);
                dataset.addSeries(s2);

            }

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    }

    //==========================================================================
    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYAreaChart(
                title,
                titlex,
                titley,
                dataset,
                PlotOrientation.VERTICAL,
                true, // legend
                false, // tool tips
                false // URLs
                );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        ValueAxis domainAxis = new DateAxis("Time");
        plot.setNoDataMessage("waiting for data...");
        //domainAxis.setFixedAutoRange(5000.0);  // 5 seconds
        domainAxis.setLowerMargin(1.0);
        domainAxis.setUpperMargin(1.0);
        domainAxis.setFixedAutoRange(3600000.0);  // 60 seconds 900000
        plot.setDomainAxis(domainAxis);
        plot.setForegroundAlpha(0.5f);

        XYItemRenderer renderer = plot.getRenderer();
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"),
                new DecimalFormat("#,###")));

        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    //==========================================================================
    public JPanel getJPanel() {
        ChartPanel cp = new ChartPanel(createChart(dataset));
        cp.setDomainZoomable(true);
        cp.setRangeZoomable(false);
        return cp;
    }
} // end class
