package model.beans;

import java.io.Serializable;

/**
 *
 * @author skuarch
 */
public class ClusterSniffer implements Serializable {

    private String request = "";
    private String view = "";
    private String job = "";   
    private String collector = "";
    private String dates = "";
    private String drillDown = "";
    private String limit = "";
    private String categorie = "";
    private String netMask = "";
    private String subnet = "";
    private String webServerHosts = "";
    private String serviceType = "";
    private String protocolType = "";
    private String networkProtocols = "";
    private String IPProtocols = "";
    private String TCPProtocols = "";
    private String UDPProtocols = "";
    private String liveSeconds = "20";
    private String ipAddress = "";
    private String websites = "";
    private String table = "false";
    private String portNumber = "";
    private String publicIPTalkers = "";

    //==========================================================================
    public ClusterSniffer() {
    } // end Cluster

    //==========================================================================
    //==========================================================================
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }    
    
    public String getRequestFormatted() {
        return "request";
    }
    
    //==========================================================================
    //==========================================================================
    public String getIPProtocols() {
        return IPProtocols;
    }

    public void setIPProtocols(String IPProtocols) {
        this.IPProtocols = IPProtocols;
    }

    public String getIPProtocolsFormatted() {
        return "IP Protocols";
    }

    //==========================================================================
    //==========================================================================
    public String getTCPProtocols() {
        return TCPProtocols;
    }

    public void setTCPProtocols(String TCPProtocols) {
        this.TCPProtocols = TCPProtocols;
    }

    public String getTCPProtocolsFormatted() {
        return "TCP Protocols";
    }

    //==========================================================================
    //==========================================================================
    public String getUDPProtocols() {
        return UDPProtocols;
    }

    public void setUDPProtocols(String UDPProtocols) {
        this.UDPProtocols = UDPProtocols;
    }

    public String getUDPProtocolsFormatted() {
        return "UDP Protocols";
    }

    //==========================================================================
    //==========================================================================
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorieFormatted() {
        return "Categorie";
    }

    //==========================================================================
    //==========================================================================
    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public String getCollectorHostnameFormatted() {
        return "Collector";
    }    

    //==========================================================================
    //==========================================================================
    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getDatesFormatted() {
        return "Dates";
    }

    //==========================================================================
    //==========================================================================
    public String getDrillDown() {
        return drillDown;
    }

    public void setDrillDown(String drillDown) {
        this.drillDown = drillDown;
    }

    public String getDrillDownFormatted() {
        return "Drill down";
    }

    //==========================================================================
    //==========================================================================
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddressFormatted() {
        return "IP address";
    }

    //==========================================================================
    //==========================================================================
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
    

    //==========================================================================
    //==========================================================================
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobFormatted() {
        return "Job";
    }

    //==========================================================================
    //==========================================================================
    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getLimitFormatted() {
        return "Limit";
    }

    //==========================================================================
    //==========================================================================
    public String getNetMask() {
        return netMask;
    }

    public void setNetMask(String netMask) {
        this.netMask = netMask;
    }

    public String getNetMaskFormatted() {
        return "Netmask";
    }

    //==========================================================================
    //==========================================================================
    public String getNetworkProtocols() {
        return networkProtocols;
    }

    public void setNetworkProtocols(String networkProtocols) {
        this.networkProtocols = networkProtocols;
    }

    public String getNetworkProtocolsFormatted() {
        return "Network protocols";
    }

    //==========================================================================
    //==========================================================================
    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public String getPortNumberFormatted() {
        return "Port number";
    }

    //==========================================================================
    //==========================================================================
    public String getPublicIPTalkers() {
        return publicIPTalkers;
    }

    public void setPublicIPTalkers(String publicIPTalkers) {
        this.publicIPTalkers = publicIPTalkers;
    }

    public String getPublicIPTalkersFormatted() {
        return "Public IP talkers";
    }

    //==========================================================================
    //==========================================================================
    public String getLiveSeconds() {
        return liveSeconds;
    }

    public void setLiveSeconds(String liveSeconds) {
        this.liveSeconds = liveSeconds;
    }

    //==========================================================================
    //==========================================================================
    public String getSubnet() {
        return subnet;
    }

    public void setSubnet(String subnet) {
        this.subnet = subnet;
    }

    public String getSubnetFormatted() {
        return "Subnet";
    }

    //==========================================================================
    //==========================================================================
    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getTypeProtocolFormatted() {
        return "Protocol type";
    }

    //==========================================================================
    //==========================================================================
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceTypeFormatted() {
        return "Service type";
    }

    //==========================================================================
    //==========================================================================
    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
    
    public String getViewFormatted() {
        return "View";
    }

    //==========================================================================
    //==========================================================================
    public String getWebServerHosts() {
        return webServerHosts;
    }

    public void setWebServerHosts(String webServerHosts) {
        this.webServerHosts = webServerHosts;
    }
    
    public String getWebServerHostsFormatted() {
        return "Web server hosts";
    }

    //==========================================================================
    //==========================================================================
    public String getWebsites() {
        return websites;
    }

    public void setWebsites(String websites) {
        this.websites = websites;
    }
    
    public String getWebsitesFormatted() {
        return "Websites";
    }
} 