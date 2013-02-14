package model.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name="subcategories")
public class Subcategories implements Serializable {
    
    @Id
    @Column(name="subcategorie_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long subCategorieId;
    
    @Column(name="subcategorie_active")
    private int subCategorieIsActive;
    
    @Column(name="subcategorie_name")
    private String subCategorieName;
    
    @Column(name="subcategorie_categorie_id")
    private long categorieId;
    
    @Column(name="subcategorie_order")
    private int subCategorieOrder;
    
    @Column(name="subcategorie_chart_type")
    private int subCategorieChartType;
    
    //==========================================================================
    public Subcategories(){    
    } // end Subcategories

    public long getSubCategorieId() {
        return subCategorieId;
    }

    public void setSubCategorieId(long subCategorieId) {
        this.subCategorieId = subCategorieId;
    }

    public int getSubCategorieIsActive() {
        return subCategorieIsActive;
    }

    public void setSubCategorieIsActive(int subCategorieIsActive) {
        this.subCategorieIsActive = subCategorieIsActive;
    }

    public String getSubCategorieName() {
        return subCategorieName;
    }

    public void setSubCategorieName(String subCategorieName) {
        this.subCategorieName = subCategorieName;
    }

    public long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(long categorieId) {
        this.categorieId = categorieId;
    }

    public int getSubCategorieOrder() {
        return subCategorieOrder;
    }

    public void setSubCategorieOrder(int subCategorieOrder) {
        this.subCategorieOrder = subCategorieOrder;
    }

    public int getSubCategorieChartType() {
        return subCategorieChartType;
    }

    public void setSubCategorieChartType(int subCategorieChartType) {
        this.subCategorieChartType = subCategorieChartType;
    }    
    
} // end class
