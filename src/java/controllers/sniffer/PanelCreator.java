package controllers.sniffer;

import controllers.global.ControllerNotifications;
import java.awt.Component;
import java.util.HashMap;
import model.beans.FilterViews;
import model.beans.Subcategories;
import model.util.FilterViewUtilities;
import model.util.SubCategoriesUtilities;
import views.panels.BarChartPanel;
import views.panels.DynamicLineChart;
import views.panels.LineChartPanel;
import views.tables.Table;
import views.tables.TablePagination;

/**
 *
 * @author skuarch
 */
public class PanelCreator {

    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();

    //==========================================================================
    public PanelCreator() {
    } // end panelCreator

    //==========================================================================    
    public Component createPanelSniffer(HashMap hashmap) {

        if (hashmap == null) {
            throw new NullPointerException("hashmap is null");
        }

        Subcategories subcategories = null;
        Component component = null;

        try {

            //1 line
            //2 bar
            //3 table
            //4 table pagination
            //5 line dynamic

            subcategories = SubCategoriesUtilities.getSubcategorieFromView(hashmap.get("view").toString());

            if (subcategories == null) {
                NOTIFICATIONS.error("Subcategorie doesn't exists", new Exception());
            }

            //line
            if (subcategories.getSubCategorieChartType() == 1) {
                return new LineChartPanel(hashmap).createPanel();
            }

            //bar
            if (subcategories.getSubCategorieChartType() == 2) {
                return new BarChartPanel(hashmap).createPanel();
            }

            //table
            if (subcategories.getSubCategorieChartType() == 3) {
                return new Table(hashmap);
            }

            //table pagination
            if (subcategories.getSubCategorieChartType() == 4) {
                return new TablePagination(hashmap);
            }
            
            //chart line dynamic
            if (subcategories.getSubCategorieChartType() == 5) {
                return new DynamicLineChart(hashmap);
            }


        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible create panel", e);
        }

        return component;

    } // end 

    //==========================================================================    
    public Component createPanelFilter(HashMap hashmap) {

        if (hashmap == null) {
            throw new NullPointerException("hashmap is null");
        }

        FilterViews filterViews = null;
        Component component = null;

        try {

            //1 line
            //2 bar
            //3 table
            //4 table pagination

            filterViews = FilterViewUtilities.getFilterViewsFromView(hashmap.get("view").toString());

            if (filterViews == null) {
                NOTIFICATIONS.error("Subcategorie doesn't exists", new Exception());
            }

            //line
            if (filterViews.getType() == 1) {
                return new LineChartPanel(hashmap).createPanel();
            }

            //bar
            if (filterViews.getType() == 2) {
                return new BarChartPanel(hashmap).createPanel();
            }

            //table
            if (filterViews.getType() == 3) {
                return new Table(hashmap);
            }

            //table pagination
            if (filterViews.getType() == 4) {
                return new TablePagination(hashmap);
            }


        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible create panel", e);
        }

        return component;

    } // end 
} // end class
