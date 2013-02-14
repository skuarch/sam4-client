package model.autentication;

import java.util.ArrayList;
import java.util.List;
import model.beans.Users;
import model.dao.DAO;

/**
 *
 * @author skuarch
 */
public class Autenticate {

    //==========================================================================
    public Autenticate() {
    } // end Autenticate

    //==========================================================================
    public ArrayList doLogin(String user, char[] password) throws Exception {

        // 0 boolean 1 (String) message
        ArrayList result = new ArrayList();
        List list = null;
        String pass = null;

        try {

            if (user == null || user.length() < 1) {
                result.add(false);
                result.add("user is empty");
                return result;
            }

            if (password == null || password.length < 1) {
                result.add(false);
                result.add("password is empty");
                return result;
            }

            pass = new String(password);

            list = new DAO().hsql("from Users where name = '" + user + "' and password = '" + pass + "'");
            /*HashMap hashMap = HashMapUtilities.getHashMapObjectRequester();
            hashMap.put("request", "hsql");
            hashMap.put("hsql", "from Users where name = '" + user + "' and password = '" + pass + "'");
            list = (List) new Linker().sendReceiveObject(hashMap);*/


            if (list.size() == 1) {
                result.add(true);
                result.add("ok");
                result.add((Users) list.get(0));
            } else {
                result.add(false);
                result.add("user or password are incorrect");
            }

        } catch (Exception e) {
            throw e;
        }

        return result;

    } // end doLogin
} // end class
