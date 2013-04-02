package model.common;

import com.lowagie.text.*;
import com.lowagie.text.pdf.DefaultFontMapper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import controllers.sniffer.ControllerNavigator;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import model.beans.CurrentUser;
import model.beans.GlobalConfiguration;
import model.net.Linker;
import model.util.DateUtilities;
import model.util.HashMapUtilities;
import model.util.PDFUtilities;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.JFreeChart;
import views.panels.SnifferPanel;
import views.tabs.SnifferSubNavigator;

/**
 *
 * @author skuarch
 */
public class Exporter {

    //==========================================================================
    public Exporter() {
    }

    //==========================================================================
    public void exportExcel(String view, String job, String collector, String path) throws Exception {

        ArrayList arrayList = null;
        HSSFRow headers = null;
        HSSFRow rows = null;
        String stringTmp = null;
        Double doubleTmp = null;
        Boolean booleanTmp = false;
        int intTmp = 0;
        Float floatTmp = null;
        Date dateTmp = null;
        String[] columnNames = null;
        Object[][] data = null;
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
        FileOutputStream fileOut = null;

        HashMap hashMap = HashMapUtilities.getHashMapSniffer();
        hashMap.put("view", view);
        hashMap.put("job", job);
        hashMap.put("collector", collector);
        hashMap.put("request", view + " Table");
        hashMap.put("isTable", "true");

        arrayList = (ArrayList) new Linker().sendReceiveObject(hashMap);                
        columnNames = (String[]) arrayList.get(1);        
        data = (Object[][]) arrayList.get(2);
        wb = new HSSFWorkbook();
        sheet = wb.createSheet(view); //sheet name

        // encabezado
        headers = sheet.createRow((short) 0); //columnas

        //poniendo las columnas
        for (int i = 0; i < columnNames.length; i++) {
            headers.createCell((int) i).setCellValue(columnNames[i]);
        }

        // poniendo los datos
        for (int d = 0; d < data.length; d++) {

            rows = sheet.createRow((short) d + 1);

            for (int c = 0; c < columnNames.length; c++) {
                System.out.println(data[d][c]);
                if (data[d][c] instanceof String) {
                    stringTmp = (String) data[d][c];
                    rows.createCell((int) c).setCellValue(stringTmp);
                }

                if (data[d][c] instanceof Double) {
                    doubleTmp = (Double) data[d][c];
                    rows.createCell((int) c).setCellValue(doubleTmp);
                }

                if (data[d][c] instanceof Boolean) {
                    booleanTmp = (Boolean) data[d][c];
                    rows.createCell((int) c).setCellValue(booleanTmp);
                }

                if (data[d][c] instanceof Integer) {
                    intTmp = (Integer) data[d][c];
                    rows.createCell((int) c).setCellValue(intTmp);
                }

                if (data[d][c] instanceof Float) {
                    floatTmp = (Float) data[d][c];
                    rows.createCell((int) c).setCellValue(floatTmp);
                }

                if (data[d][c] instanceof Date) {
                    dateTmp = (Date) data[d][c];
                    rows.createCell((int) c).setCellValue(dateTmp);
                }
            } // nested for
        } // firts for

        if (!path.contains("xls")) {
            path += ".xls";
        }
        fileOut = new FileOutputStream(path);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();


    }

    //==========================================================================
    public void exportExcelFilter(String view, String job, String collector, String path) throws Exception {

        ArrayList arrayList = null;
        HSSFRow headers = null;
        HSSFRow rows = null;
        String stringTmp = null;
        Double doubleTmp = null;
        Boolean booleanTmp = false;
        int intTmp = 0;
        Float floatTmp = null;
        Date dateTmp = null;
        String[] columnNames = null;
        Object[][] data = null;
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
        FileOutputStream fileOut = null;

        HashMap hashMap = HashMapUtilities.getHashMapFilter();
        hashMap.put("view", view);
        hashMap.put("job", job);
        hashMap.put("log", job);
        hashMap.put("collector", collector);
        hashMap.put("request", view);
        hashMap.put("isTable", "true");

        arrayList = (ArrayList) new Linker().sendReceiveObject(hashMap);

        columnNames = (String[]) arrayList.get(1);
        data = (Object[][]) arrayList.get(2);

        wb = new HSSFWorkbook();
        sheet = wb.createSheet(view); //sheet name

        // encabezado
        headers = sheet.createRow((short) 0); //columnas

        //poniendo las columnas
        for (int i = 0; i < columnNames.length; i++) {
            headers.createCell((int) i).setCellValue(columnNames[i]);
        }

        // poniendo los datos
        for (int d = 0; d < data.length; d++) {

            rows = sheet.createRow((short) d + 1);

            for (int c = 0; c < columnNames.length; c++) {

                if (data[d][c] instanceof String) {
                    stringTmp = (String) data[d][c];
                    rows.createCell((int) c).setCellValue(stringTmp);
                }

                if (data[d][c] instanceof Double) {
                    doubleTmp = (Double) data[d][c];
                    rows.createCell((int) c).setCellValue(doubleTmp);
                }

                if (data[d][c] instanceof Boolean) {
                    booleanTmp = (Boolean) data[d][c];
                    rows.createCell((int) c).setCellValue(booleanTmp);
                }

                if (data[d][c] instanceof Integer) {
                    intTmp = (Integer) data[d][c];
                    rows.createCell((int) c).setCellValue(intTmp);
                }

                if (data[d][c] instanceof Float) {
                    floatTmp = (Float) data[d][c];
                    rows.createCell((int) c).setCellValue(floatTmp);
                }

                if (data[d][c] instanceof Date) {
                    dateTmp = (Date) data[d][c];
                    rows.createCell((int) c).setCellValue(dateTmp);
                }
            } // nested for
        } // firts for

        if (!path.contains("xls")) {
            path += ".xls";
        }
        fileOut = new FileOutputStream(path);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();


    }

    //==========================================================================
    public void exportExcel(String view, String path, ArrayList arrayList) throws Exception {

        HSSFRow headers = null;
        HSSFRow rows = null;
        String stringTmp = null;
        Double doubleTmp = null;
        Boolean booleanTmp = false;
        int intTmp = 0;
        Float floatTmp = null;
        Date dateTmp = null;
        String[] columnNames = null;
        Object[][] data = null;
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
        FileOutputStream fileOut = null;

        columnNames = (String[]) arrayList.get(0);
        data = (Object[][]) arrayList.get(1);

        wb = new HSSFWorkbook();
        sheet = wb.createSheet(view); //sheet name

        // encabezado
        headers = sheet.createRow((short) 0); //columnas

        //poniendo las columnas
        for (int i = 0; i < columnNames.length; i++) {
            headers.createCell((int) i).setCellValue(columnNames[i]);
        }

        // poniendo los datos
        for (int d = 0; d < data.length; d++) {

            rows = sheet.createRow((short) d + 1);

            for (int c = 0; c < columnNames.length; c++) {

                if (data[d][c] instanceof String) {
                    stringTmp = (String) data[d][c];
                    rows.createCell((int) c).setCellValue(stringTmp);
                }

                if (data[d][c] instanceof Double) {
                    doubleTmp = (Double) data[d][c];
                    rows.createCell((int) c).setCellValue(doubleTmp);
                }

                if (data[d][c] instanceof Boolean) {
                    booleanTmp = (Boolean) data[d][c];
                    rows.createCell((int) c).setCellValue(booleanTmp);
                }

                if (data[d][c] instanceof Integer) {
                    intTmp = (Integer) data[d][c];
                    rows.createCell((int) c).setCellValue(intTmp);
                }

                if (data[d][c] instanceof Float) {
                    floatTmp = (Float) data[d][c];
                    rows.createCell((int) c).setCellValue(floatTmp);
                }

                if (data[d][c] instanceof Date) {
                    dateTmp = (Date) data[d][c];
                    rows.createCell((int) c).setCellValue(dateTmp);
                }
            } // nested for
        } // firts for

        if (!path.contains("xls")) {
            path += ".xls";
        }
        fileOut = new FileOutputStream(path);
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();


    }

    //==========================================================================
    public void exportChartToPDF(JFreeChart chart, String path) throws Exception {

        int height = 500;
        int width = 600;
        PdfWriter writer = null;
        Document document = new Document(PageSize.LETTER, 50, 50, 50, 50);
        PdfContentByte contentByte = null;
        PdfTemplate template = null;
        Graphics2D graphics2d = null;
        Rectangle2D rectangle2d = null;

        try {


            if (!path.contains("pdf")) {
                path += ".pdf";
            }

            writer = PdfWriter.getInstance(document, new FileOutputStream(path));
            writer.setPageSize(PageSize.LETTER);
            document.open();

            document.add(new Paragraph("Sispro Application Manager"));
            contentByte = writer.getDirectContent();
            template = contentByte.createTemplate(width, height);
            graphics2d = template.createGraphics(width, height, new DefaultFontMapper());
            rectangle2d = new Rectangle2D.Double(0, 0, width, height);
            chart.draw(graphics2d, rectangle2d);
            graphics2d.dispose();
            contentByte.addTemplate(template, 0, writer.getVerticalPosition(true) - height);

        } catch (Exception ex) {
            throw ex;
        } finally {
            document.close();
        }

    } // end exportPDF

    //==========================================================================
    public void createPDFReport(String path) throws Exception {

        if (path == null || path.length() < 1) {
            throw new Exception("path is null");
        }

        GlobalConfiguration globalConfiguration = null;
        Component[] subs = null;
        SnifferSubNavigator SnifferSubNavigator = null;
        Component[] tabs = null;
        SnifferPanel snifferPanel = null;
        HashMap hm = null;
        Document document = null;
        PdfWriter pdfWriter = null;
        HeaderFooter headerFooter = null;
        Image image = null;
        //Watermark watermark = null;
        String stringHeader = null;
        PdfContentByte pdfContentByte = null;
        PdfTemplate pdfTemplate = null;
        Graphics2D graphics2d = null;
        Rectangle2D rectangle2d = null;
        int height = 400;
        int width = 525;
        JFreeChart chart = null;
        Image imageChart = null;
        Table table = null;
        ArrayList arrayList = null;
        String columnNames[] = null;
        Object[][] data = null;
        Cell cell = null;
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD, new Color(0, 0, 0));
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, new Color(0, 0, 0));
        Phrase pHeader = null;
        Anchor anchor = null;
        Paragraph paragraph = null;

        try {

            //jobs
            subs = ControllerNavigator.getInstance().getComponents();

            if (subs != null) {

                globalConfiguration = GlobalConfiguration.getInstance();

                document = new Document(PageSize.LETTER, 50, 50, 50, 50);

                if (!path.contains(".pdf")) {
                    path += ".pdf";
                }

                pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(path));
                image = Image.getInstance(Exporter.class.getResource("/views/images/watermark.png"));
                image.setAbsolutePosition(270, 60);
                //watermark = new Watermark(image, 250, 350);
                stringHeader = "Sispro Application Manager   [ " + globalConfiguration.getProjectName() + " ]      page: ";

                //firts page                
                pHeader = new Phrase(stringHeader, FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(175, 175, 175)));
                headerFooter = new HeaderFooter(pHeader, true);
                document.setHeader(headerFooter);
                //document.add(watermark);

                document.open();

                document.add(new Paragraph("Report information", FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD, new Color(0, 0, 0))));
                document.add(new Paragraph("Report created on " + DateUtilities.getCurrentDate(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(175, 175, 175))));
                document.add(new Paragraph("Client information", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(0, 0, 0))));
                document.add(new Paragraph(globalConfiguration.getProjectName(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(175, 175, 175))));
                document.add(new Paragraph("Analyst information", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(0, 0, 0))));
                document.add(new Paragraph("Report created by: " + CurrentUser.getInstance().getName(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(175, 175, 175))));

                //index
                document.newPage();
                paragraph = new Paragraph("Content\n", FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, new Color(175, 175, 175)));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraph);

                for (int i = 0; i < subs.length; i++) {

                    if (subs[i] instanceof SnifferSubNavigator) {
                        SnifferSubNavigator = (SnifferSubNavigator) subs[i];
                        anchor = new Anchor("\n" + subs[i].getName() + "\n");
                        document.add(anchor);
                        tabs = SnifferSubNavigator.getComponents();
                        for (int j = 0; j < tabs.length; j++) {

                            if (tabs[j] instanceof SnifferPanel) {
                                snifferPanel = (SnifferPanel) tabs[j];
                                anchor = new Anchor(" -" + snifferPanel.getName() + "\n");
                                anchor.setReference("#" + snifferPanel.getName());
                                document.add(anchor);
                            }

                        }
                    }
                }

                //createReport
                for (int i = 0; i < subs.length; i++) {

                    if (subs[i] instanceof SnifferSubNavigator) {

                        document.newPage();

                        //document.add(new Paragraph(subs[i].getName(), FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(175, 175, 175))));

                        SnifferSubNavigator = (SnifferSubNavigator) subs[i];

                        //tabs
                        tabs = SnifferSubNavigator.getComponents();

                        for (int p = 0; p < tabs.length; p++) {

                            if (tabs[p] instanceof SnifferPanel) {

                                snifferPanel = (SnifferPanel) tabs[p];
                                hm = snifferPanel.getHashMap();
                                Anchor a = new Anchor(snifferPanel.getName());
                                a.setName(snifferPanel.getName());
                                document.add(a);

                                //information
                                document.add(PDFUtilities.tableHashMap(hm));

                                //table or chart
                                if (hm.get("isTable").equals(true)) {

                                    //creating table****************************                                    
                                    arrayList = (ArrayList) snifferPanel.getData();
                                    columnNames = (String[]) arrayList.get(0);
                                    data = (Object[][]) arrayList.get(1);
                                    table = new Table(columnNames.length, data.length);
                                    table.setPadding(2);
                                    table.setSpacing(2);
                                    //table.setSpaceBetweenCells(0);

                                    //columns
                                    for (int j = 0; j < columnNames.length; j++) {
                                        cell = new Cell(new Paragraph(columnNames[j], headerFont));
                                        cell.setHeader(true);
                                        table.addCell(cell);
                                    }
                                    table.endHeaders();

                                    //data
                                    for (int d = 0; d < data.length; d++) {

                                        for (int c = 0; c < columnNames.length; c++) {
                                            cell = new Cell(new Paragraph(data[d][c] + "", cellFont));
                                            table.addCell(cell);
                                        }

                                    }

                                    document.add(table);
                                    document.newPage();

                                } else {

                                    //creating chart****************************
                                    chart = (JFreeChart) snifferPanel.getData();
                                    chart.setBackgroundPaint(new Color(255, 255, 255, 0));
                                    pdfContentByte = pdfWriter.getDirectContent();
                                    pdfTemplate = pdfContentByte.createTemplate(width, height);
                                    graphics2d = pdfTemplate.createGraphics(width, height, new DefaultFontMapper());
                                    rectangle2d = new Rectangle2D.Double(0, 0, width, height);
                                    chart.draw(graphics2d, rectangle2d);
                                    graphics2d.dispose();
                                    imageChart = Image.getInstance(pdfTemplate);
                                    document.add(imageChart);
                                    document.newPage();
                                }
                            }

                        } // end for tabs

                    }

                } // end for subs


            } // end if subs


        } catch (Exception e) {
            throw e;
        } finally {

            if (document.isOpen()) {
                document.close();
            }

        }

    } // end createPdfReport    
} // end class
