package model.util;

import model.beans.Subcategories;
import model.dao.DAO;

/**
 *
 * @author skuarch
 */
public class SubCategoriesUtilities {

    //==========================================================================
    public static Subcategories getSubcategorieFromView(String view) throws Exception {

        return (Subcategories) new DAO().hsql("from Subcategories where subcategorie_name = '" + view + "'").get(0);

    }
} // end class
