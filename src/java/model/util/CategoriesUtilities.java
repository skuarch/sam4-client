package model.util;

import java.util.ArrayList;
import model.beans.Categories;
import model.dao.DAO;

/**
 *
 * @author skuarch
 */
public class CategoriesUtilities {
    
    private static ArrayList<Categories> categories;
    
    //==========================================================================
    static {
        
        try {
            categories = (ArrayList<Categories>) new DAO().getAll("Categories");
        } catch (Exception e) {
            new Thrower().exception(e);
        }
        
    } // end static

    //==========================================================================
    /**
     * compare the param with all categories, if categorie exits return true.
     * @param categorie String
     * @return boolean
     * @throws Exception 
     */
    public static boolean exitsCategorie(String categorie) throws Exception {
        
        boolean flag = false;       
        
        for (Categories cat : categories) {
            if(cat.getCategorieName().equalsIgnoreCase(categorie)){
                flag = true;
                break;
            }
        }       
        
        return flag;
    } // end exitsCategorie
    
} // end class

