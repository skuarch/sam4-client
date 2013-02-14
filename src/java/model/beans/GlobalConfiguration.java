package model.beans;

/**
 *
 * @author skuarch
 */
public class GlobalConfiguration extends Configuration {

    private String mainServer;
    private int mainServerPort;
    private String context;

    //==========================================================================
    private GlobalConfiguration() {
        super();
    }

    //==========================================================================
    public static GlobalConfiguration getInstance() {
        return GlobalConfiguration.GlobalConfigurationHolder.INSTANCE;
    }

    //==========================================================================
    private static class GlobalConfigurationHolder {
        private static final GlobalConfiguration INSTANCE = new GlobalConfiguration();
    }

    public String getMainServer() {
        return mainServer;
    }

    public void setMainServer(String mainServer) {
        this.mainServer = mainServer;
    }

    public int getMainServerPort() {
        return mainServerPort;
    }

    public void setMainServerPort(int mainServerPort) {
        this.mainServerPort = mainServerPort;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
