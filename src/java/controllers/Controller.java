package controllers;

import controllers.global.ControllerNotifications;

/**
 * 
 * @author skuarch
 */
public abstract class Controller {    
    
    protected static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    
    //==========================================================================
    /**
     * create a instance.
     */
    public Controller(){
    } // end Controller
    
    //==========================================================================
    /**
     * 
     */
    public abstract void setupInterface();   
    
    //==========================================================================
    /**
     * 
     * @param flag boolean
     */
    public abstract void setVisible(boolean flag);
    
    //==========================================================================
    /**
     * this method is for do the clean. <br/>
     * please call this method in a finalize method.
     */
    public abstract void destroyCurrentThread();

} // end class