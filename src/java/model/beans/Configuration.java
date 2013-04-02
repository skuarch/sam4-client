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
    @Column(name="configuration_active_sniffer")
    private int isActiveSniffer;
    @Column(name="configuration_active_shaper")
    private int isActiveShaper;
    @Column(name="configuration_active_filter")
    private int isActiveFilter;
    @Column(name="configuration_active_port_scanner")
    private int isActivePortScanner;
    @Column(name="configuration_active_e2e")
    private int isActiveEndToEnd;
    @Column(name="configuration_active_firewall")
    private int isActiveFirewall;
    
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

    public int getIsActiveSniffer() {
        return isActiveSniffer;
    }

    public void setIsActiveSniffer(int isActiveSniffer) {
        this.isActiveSniffer = isActiveSniffer;
    }

    public int getIsActiveShaper() {
        return isActiveShaper;
    }

    public void setIsActiveShaper(int isActiveShaper) {
        this.isActiveShaper = isActiveShaper;
    }

    public int getIsActiveFilter() {
        return isActiveFilter;
    }

    public void setIsActiveFilter(int isActiveFilter) {
        this.isActiveFilter = isActiveFilter;
    }

    public int getIsActivePortScanner() {
        return isActivePortScanner;
    }

    public void setIsActivePortScanner(int isActivePortScanner) {
        this.isActivePortScanner = isActivePortScanner;
    }

    public int getIsActiveEndToEnd() {
        return isActiveEndToEnd;
    }

    public void setIsActiveEndToEnd(int isActiveEndToEnd) {
        this.isActiveEndToEnd = isActiveEndToEnd;
    }

    public int getIsActiveFirewall() {
        return isActiveFirewall;
    }

    public void setIsActiveFirewall(int isActiveFirewall) {
        this.isActiveFirewall = isActiveFirewall;
    }
    
} // end class
