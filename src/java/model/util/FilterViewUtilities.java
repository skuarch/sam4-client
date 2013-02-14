package model.util;

import model.beans.FilterViews;
import model.dao.DAO;

/**
 *
 * @author skuarch
 */
public class FilterViewUtilities {
    
    //==========================================================================
    public static FilterViews getFilterViewsFromView(String view) throws Exception {

        return (FilterViews) new DAO().hsql("from FilterViews where filter_view_name = '" + view + "'").get(0);

    }    
    
}
