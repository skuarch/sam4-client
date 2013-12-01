package controllers.firewall;

import controllers.Controller;
import model.common.StartBrowser;

/**
 *
 * @author skuarch
 */
public class ControllerFirewallSam5 extends Controller {

    public void openBrowser() {

        try {
            
            String url = "http://192.168.208.9:8080/sam5";                        
            new StartBrowser().openBrowser(url);
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    }

    @Override
    public void setupInterface() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setVisible(boolean flag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
