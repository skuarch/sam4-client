package model.net;

import controllers.global.ControllerMain;

/**
 * wrapper for ConnectPool
 *
 * @author skuarch
 */
public class ConnectAction extends ConnectPool {

    //==========================================================================
    /**
     * create a instance, for get the
     * <code>DataSource</code> is using
     * <code>InitialContext</code>
     */
    public ConnectAction() {
        super(ControllerMain.getDataSource());
    }
} // end
