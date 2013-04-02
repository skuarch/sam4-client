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
@Table(name = "users")
public class Users {

    @Id
    @Column (name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column (name="user_name")
    private String name;
    @Column (name="user_password")
    private String password;
    @Column (name="user_level")
    private int level;
    
    @Column(name="user_main_server_port")
    private int mainServerPort;
    
    @Column(name="user_main_server")
    private String mainServer;  
    
    @Column(name="user_main_server_context")
    private String mainServerContext;  

    //==========================================================================
    public Users() {
    } // end Users

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMainServerPort() {
        return mainServerPort;
    }

    public void setMainServerPort(int mainServerPort) {
        this.mainServerPort = mainServerPort;
    }

    public String getMainServer() {
        return mainServer;
    }

    public void setMainServer(String mainServer) {
        this.mainServer = mainServer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMainServerContext() {
        return mainServerContext;
    }

    public void setMainServerContext(String mainServerContext) {
        this.mainServerContext = mainServerContext;
    }
    
} // end class
