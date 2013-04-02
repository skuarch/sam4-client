package model.common;

import java.util.Properties;
import javax.naming.InitialContext;

/**
 *
 * @author skuarch
 */
public class Context {

    //==========================================================================
    public Context() {
    }

    //==========================================================================
    public InitialContext getInitialContext() throws Exception {

        Properties properties = null;
        InitialContext initialContext = null;

        try {

            properties = new Properties();
            properties.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            properties.put("java.naming.factory.url.pkgs","com.sun.enterprise.naming");            
            initialContext = new InitialContext(properties);            

        } catch (Exception e) {
            throw e;
        }

        return initialContext;

    }
}
