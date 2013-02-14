package controllers.sniffer;

import controllers.Controller;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import views.frames.FloatFrame;

/**
 *
 * @author skuarch
 */
public class ControllerFloatFrame extends Controller {

    private FloatFrame floatFrame = new FloatFrame();
    private Component component = null;
    private String job = null;

    //==========================================================================
    public ControllerFloatFrame(Component component, String job) {
        this.component = component;
        this.job = job;
    } // end ControllerFloatFrame

    //==========================================================================
    private void setComponent() {
        floatFrame.add(component, BorderLayout.CENTER);
    }

    //==========================================================================
    @Override
    public void setupInterface() {

        try {

            floatFrame.setName(component.getName());
            floatFrame.setTitle(component.getName());            
            setComponent();
            addListeners();

        } catch (Exception e) {
            NOTIFICATIONS.error("Error setting up the interface", e);
        }
    } // end setupInterface

    //==========================================================================
    private void addListeners() {

        try {

            floatFrame.getjButtonSwap().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                    
                    new TabsDriver().swapTabIn(component, job);
                    setVisible(false);
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // addListeners

    //==========================================================================
    public void checkTab(String parent) {
        ControllerNavigator.getInstance().checkTabs(parent);
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        floatFrame.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        floatFrame = null;
        component = null;
    }

    //==========================================================================
    @Override
    protected void finalize() throws Throwable {
        try {
            destroyCurrentThread();
        } catch (Exception e) {
            NOTIFICATIONS.error("Unable to clean up in float frame", e);
        } finally {
            super.finalize();
        }
    }
} // end ControllerFloatFrame
