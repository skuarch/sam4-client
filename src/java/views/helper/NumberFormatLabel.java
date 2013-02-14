package views.helper;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;

/**
 *
 * @author Macario Gaxiola (mac) - macario.gaxiola@gdc-cala.com.mx
 */
public class NumberFormatLabel extends DecimalFormat {

    private String B = " B";
    private String KB = " KB";
    private String MB = " MB";
    private String GB = " GB";
    private static final double K = 1024;
    private static final double M = K * K;
    private static final double G = M * K;
    private double upperBound;
    private String label;
    private double constant;

    public NumberFormatLabel(double upperBound, boolean inBits) {

        this.upperBound = upperBound;
        if (inBits) {
            B = " b";
            KB = " Kb";
            MB = " Mb";
            GB = " Gb";
        }
        
        updateUpperBound(this.upperBound);

    }

    private void calculateLabel(double upperBound) {
        if (upperBound > 1024 && upperBound < M) {
            label = KB;
            constant = K;
        } else if (upperBound > M && upperBound < G) {
            label = MB;
            constant = M;
        } else if (upperBound > G) {
            label = GB;
            constant = G;
        } else {
            label = B;
            constant = 1;
        }
    }

    private void updateUpperBound(double upperBound) {
        calculateLabel(upperBound);
    }

    @Override
    public StringBuffer format(double number, StringBuffer appendTo, FieldPosition position) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        return appendTo.append(numberFormat.format(number / constant)).append(label);
    }
} // end class NumberFormatLabel