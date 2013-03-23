package views.charts;

import controllers.global.ControllerNotifications;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JList;
import javax.swing.JPanel;
import model.util.DateUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.event.AxisChangeListener;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import views.helper.NumberFormatLabel;

/**
 *
 * @author skuarch
 */
public class SimpleLineChart extends ApplicationFrame implements AxisChangeListener {

    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    private XYDataset dataset = null;
    private double x1;
    private double x2;
    private String title = null;
    private String titlex = null;
    private String titley = null;
    private DateAxis dateAxis = null;
    private JList list = null;

    //==========================================================================
    public SimpleLineChart(XYDataset dataset,String title, String titlex, String titley, JList list) {
        super(title);
        this.dataset = dataset;
        this.title = title;
        this.titlex = titlex;
        this.titley = titley;
        this.list = list;
    }
    
    //==========================================================================
    public void setDataSet(XYDataset dataset){
        this.dataset = dataset;
    }
    
    //==========================================================================
    public JFreeChart createChart(XYDataset dataset) {

        NumberAxis yAxis = null;        
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
        plot.setNoDataMessage("no data");
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        plot.setDomainAxis(domainAxis);
        plot.setForegroundAlpha(0.5f);

        dateAxis = (DateAxis) plot.getDomainAxis();
        dateAxis.addChangeListener(this);

        XYItemRenderer renderer = plot.getRenderer();
        renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"),
                new DecimalFormat("#,###")));

        yAxis = (NumberAxis) plot.getRangeAxis();
        double upperBound = yAxis.getRange().getUpperBound();      
        
        if (title.contains("Bits")) {
            yAxis.setNumberFormatOverride(new NumberFormatLabel(upperBound, true));
        } else {
            yAxis.setNumberFormatOverride(new NumberFormatLabel(upperBound, false));
        }

        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    } // end createChart

    //==========================================================================
    private void handlerZoom() {

        Object[] data = null;

        try {

            if (x1 > 0 && x2 > 0) {
                data = new Object[2];
                data[0] = DateUtilities.getDate(x1);
                data[1] = DateUtilities.getDate(x2);
                list.setListData(data);
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("error in zoom", e);
        }

    } // end handlerZoom

    //==========================================================================
    @Override
    public void axisChanged(AxisChangeEvent ace) {
        x1 = dateAxis.getLowerBound();
        x2 = dateAxis.getUpperBound();
        handlerZoom();
    } // end axisChanged
    
    //==========================================================================
    public JPanel getJPanel() {
        ChartPanel cp = new ChartPanel(createChart(dataset));
        cp.setDomainZoomable(true);
        cp.setRangeZoomable(false);
        cp.setPreferredSize(new Dimension(100, 100));
        setContentPane(cp);
        return cp;
    }    
} // end class
