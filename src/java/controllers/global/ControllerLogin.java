package controllers.global;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.SwingWorker;
import model.autentication.Autenticate;
import model.beans.Configuration;
import model.beans.CurrentUser;
import model.beans.GlobalConfiguration;
import model.beans.Users;
import model.dao.DAO;
import model.util.HashMapUtilities;
import views.frames.Login;

/**
 *
 * @author skuarch
 */
public class ControllerLogin extends Controller {

    private Login login = null;
    private SwingWorker sw = null;

    //==========================================================================
    public ControllerLogin() {
        login = new Login();
    } // end ControllerLogin

    //==========================================================================
    @Override
    public void setupInterface() {

        sw = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {

                    login.setMessage("welcome to sispro sniffer network");
                    addListeners();

                } catch (Exception e) {
                    NOTIFICATIONS.error("Error creating window", e);
                }

                return null;

            }
        };
        sw.execute();

    } // end setupInterface  

    //==========================================================================
    private void addListeners() {

        try {

            login.getLoginButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    authentication();

                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible add listeners", e);
        }

    } // end addListeners

    //==========================================================================
    private void authentication() {

        login.setMessage("wait...");
        login.setEnabled(false);

        ControllerMainFrame cmf = null;
        String userName = login.getUser();
        char[] password = login.getPassword();
        ArrayList arrayList = null;
        String message = null;
        boolean flag = false;
        CurrentUser user = CurrentUser.getInstance();
        Users tmpUsers = null;
        Configuration configuration = null;
        GlobalConfiguration globalConfiguration = null;
        HashMap hashMap = HashMapUtilities.getHashMapObjectRequester();

        try {

            arrayList = new Autenticate().doLogin(userName, password);

            flag = (Boolean) arrayList.get(0);

            if (!flag) {
                message = (String) arrayList.get(1);
                login.setMessage(message);
                login.setUser("");
                login.setPassword("");
            } else {

                message = (String) arrayList.get(1);
                login.setMessage(message);
                login.setVisible(false);

                //create user
                tmpUsers = (Users) arrayList.get(2);
                user.setName(userName);
                user.setLevel(tmpUsers.getLevel());
                user.setId(tmpUsers.getIdUser());
                user.setMainServer(tmpUsers.getMainServer());
                user.setMainServerPort(tmpUsers.getMainServerPort());
                user.setContext(tmpUsers.getMainServerContext());

                //create a globalConfiguration
                configuration = (Configuration) new DAO().get(1, Configuration.class);
                /*hashMap.put("request", "get Configurations");
                 hashMap.put("id", 1);
                 configuration = (Configuration) new Linker().sendReceiveObject(hashMap);*/

                globalConfiguration = GlobalConfiguration.getInstance();
                globalConfiguration.setMainServer(tmpUsers.getMainServer());
                globalConfiguration.setMainServerPort(tmpUsers.getMainServerPort());
                globalConfiguration.setContext(tmpUsers.getMainServerContext());
                globalConfiguration.setId(configuration.getId());
                globalConfiguration.setTimeWaitConnectivity(configuration.getTimeWaitConnectivity());
                globalConfiguration.setTimeWaitMessage(configuration.getTimeWaitMessage());
                globalConfiguration.setProjectName(configuration.getProjectName());

                //open login window
                cmf = ControllerMainFrame.getInstance();
                cmf.setupInterface();
                cmf.setVisible(true);

            }

        } catch (Exception ex) {
            NOTIFICATIONS.error("Unexpected error", ex);
            ex.printStackTrace();
        } finally {
            login.setEnabled(true);
        }

    } // end authentication

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        login.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {

        try {
            login = null;
            sw = null;
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end destroyCurrentThread

    //==========================================================================
    @Override
    protected void finalize() throws Throwable {

        try {
            NOTIFICATIONS.information("clean up ControllerLogin", false);
            destroyCurrentThread();
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        } finally {
            super.finalize();
        }

    } // end finalize
} // end class
