package model.beans;

/**
 *
 * @author skuarch
 */
public class CurrentUser {

    private long id;
    private String name;
    private String password;
    private int level;
    private String mainServer;
    private int mainServerPort;
    private String context;
    
    //==========================================================================
    private CurrentUser() {
    }
    
    //==========================================================================
    public static CurrentUser getInstance() {
        return CurrentUserHolder.INSTANCE;
    }
    
    //==========================================================================
    private static class CurrentUserHolder {
        private static final CurrentUser INSTANCE = new CurrentUser();
    }

    //==========================================================================
    public long getId() {
        return id;
    }

    //==========================================================================
    public void setId(long id) {
        this.id = id;
    }
    
    //==========================================================================
    public int getLevel() {
        return level;
    }

    //==========================================================================
    public void setLevel(int level) {
        this.level = level;
    }

    //==========================================================================
    public String getName() {
        return name;
    }

    //==========================================================================
    public void setName(String name) {
        this.name = name;
    }

    //==========================================================================
    public String getPassword() {
        return password;
    }

    //==========================================================================
    public void setPassword(String password) {
        this.password = password;
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