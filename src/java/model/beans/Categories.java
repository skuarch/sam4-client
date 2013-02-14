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
@Table(name="categories")
public class Categories implements Serializable {
    
    @Id
    @Column(name="categorie_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long categorieId;
    
    @Column(name="categorie_active")
    private int categorieActive;
    
    @Column(name="categorie_name")
    private String categorieName;
    
    @Column(name="categorie_order")
    private int categorieOrder;
    
    //==========================================================================
    public Categories(){
    
    } // end categorie

    public long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(long categorieId) {
        this.categorieId = categorieId;
    }

    public int getCategorieActive() {
        return categorieActive;
    }

    public void setCategorieActive(int categorieActive) {
        this.categorieActive = categorieActive;
    }

    public String getCategorieName() {
        return categorieName;
    }

    public void setCategorieName(String categorieName) {
        this.categorieName = categorieName;
    }

    public int getCategorieOrder() {
        return categorieOrder;
    }

    public void setCategorieOrder(int categorieOrder) {
        this.categorieOrder = categorieOrder;
    }    
     
} // end class
