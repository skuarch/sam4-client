package controllers.global;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.SwingWorker;
import model.beans.CurrentUser;
import model.beans.GlobalConfiguration;
import model.beans.Users;
import model.dao.DAO;
import views.dialogs.UserConfiguration;

/**
 *
 * @author skuarch
 */
public class ControllerUserConfiguration extends Controller {

    private UserConfiguration uc = new UserConfiguration(null, true);

    //==========================================================================
    @Override
    public void setupInterface() {
        try {

            uc.setTitle("User configuration");
            uc.getjTextFieldMainServer().setText(GlobalConfiguration.getInstance().getMainServer());
            uc.getjTextFieldPort().setText(GlobalConfiguration.getInstance().getMainServerPort() + "");
            addListeners();

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
    } //end setupInterface

    //==========================================================================
    private void addListeners() {

        try {

            uc.getjButtonTest().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    testConnection();
                }
            });

            uc.getjButtonClose().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    uc.setVisible(false);
                }
            });

            uc.getjButtonSave().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveConfiguration();
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end addListeners

    //==========================================================================
    private void testConnection() {

        uc.getjButtonSave().setEnabled(false);
        uc.getjTextAreaOut().setText("");
        uc.getjButtonTest().setEnabled(false);
        uc.getjProgressBar().setIndeterminate(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                String mainServer = null;
                int port;
                String context = null;
                URL url = null;
                HttpURLConnection hurlc = null;

                try {

                    mainServer = uc.getjTextFieldMainServer().getText();
                    if (mainServer == null || mainServer.length() < 1) {
                        NOTIFICATIONS.warning("ip address or hostname is required", true);
                        return null;
                    }

                    port = Integer.parseInt(uc.getjTextFieldPort().getText());
                    if (port == 0 || mainServer.length() < 1) {
                        NOTIFICATIONS.warning("port is required", true);
                        return null;
                    }

                    context = CurrentUser.getInstance().getContext();
                    url = new URL("http://" + mainServer + ":" + port + "/" + context + "/connection");
                    uc.getjTextAreaOut().append("trying to connect\n");
                    hurlc = (HttpURLConnection) url.openConnection();

                    if (hurlc == null) {
                        uc.getjTextAreaOut().append("imposible stablish connection with " + mainServer + "\n");
                    } else {
                        hurlc.connect();
                        uc.getjTextAreaOut().append("connection " + mainServer + " succesfully\n");
                        uc.getjButtonSave().setEnabled(true);
                    }

                    if (hurlc.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        uc.getjTextAreaOut().append("imposible stablish connection with " + mainServer + "\n");
                        return null;
                    }

                } catch (NumberFormatException nfe) {
                    NOTIFICATIONS.warning("Is required a number of port", true);
                } catch (Exception e) {
                    uc.getjTextAreaOut().append("error in connection " + e.getMessage() + "\n");
                } finally {
                    uc.getjButtonTest().setEnabled(true);
                    uc.getjProgressBar().setIndeterminate(false);
                    url = null;
                    hurlc = null;
                }

                return null;
            }
        }.execute();

    } // end testConnection

    //==========================================================================
    private void saveConfiguration() {

        String mainServer = null;
        int port = 0;
        Users user = null;
        GlobalConfiguration globalConfiguration = null;

        try {

            user = (Users) new DAO().get(CurrentUser.getInstance().getId(), Users.class);

            mainServer = uc.getjTextFieldMainServer().getText();
            port = Integer.parseInt(uc.getjTextFieldPort().getText());

            //saving data in db
            user.setMainServer(mainServer);
            user.setMainServerPort(port);
            new DAO().update(user);

            //set globalConfiguration
            globalConfiguration = GlobalConfiguration.getInstance();
            globalConfiguration.setMainServer(mainServer);
            globalConfiguration.setMainServerPort(port);

            //change CurrentUser
            CurrentUser.getInstance().setMainServer(mainServer);
            CurrentUser.getInstance().setMainServerPort(port);

            ControllerMainFrame.getInstance().setEnableButtons(true);
            ControllerMainFrame.getInstance().setMessage("");
            uc.setVisible(false);

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // saveConfiguration

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        uc.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class
