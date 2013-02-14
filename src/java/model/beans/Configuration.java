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
@Table(name = "configuration")
public class Configuration implements Serializable {

    @Id
    @Column(name = "configuration_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "configuration_time_wait_message")
    private int timeWaitMessage;
    @Column(name = "configuration_time_wait_connectivity")
    private int timeWaitConnectivity;
    @Column(name="configuration_project_name")
    private String projectName;
    
    //==========================================================================
    public Configuration() {
    } // end Configuration

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTimeWaitMessage() {
        return timeWaitMessage;
    }

    public void setTimeWaitMessage(int timeWaitMessage) {
        this.timeWaitMessage = timeWaitMessage;
    }

    public int getTimeWaitConnectivity() {
        return timeWaitConnectivity;
    }

    public void setTimeWaitConnectivity(int timeWaitConnectivity) {
        this.timeWaitConnectivity = timeWaitConnectivity;
    }   

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }   
    
    
} // end class
