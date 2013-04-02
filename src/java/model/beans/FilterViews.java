package model.beans;

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
@Table(name = "filter_views")
public class FilterViews {

    @Id
    @Column(name = "filter_view_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "filter_view_name")
    private String name;
    
    @Column(name = "filter_view_active")    
    private int active;
    
    @Column(name = "filter_view_chart_type")
    private int type;
    
    //==========================================================================
    public FilterViews() {        
    } // end FilterViews  

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }    
    
} // end class
