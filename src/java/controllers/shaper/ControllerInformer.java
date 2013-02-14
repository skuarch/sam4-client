package controllers.shaper;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.SwingWorker;
import model.net.Linker;
import model.util.HashMapUtilities;
import views.dialogs.ShaperInformer;

/**
 *
 * @author skuarch
 */
public class ControllerInformer extends Controller {

    private ShaperInformer shaperInformer = null;
    private String type = null;
    private HashMap hm = null;
    private String collector = null;

    //==========================================================================
    public ControllerInformer(String type, String collector) {

        shaperInformer = new ShaperInformer(null, true);
        this.type = type;
        this.collector = collector;
        hm = HashMapUtilities.getHashMapShaper();
    } // end ControllerInformer

    //==========================================================================
    @Override
    public void setupInterface() {

        shaperInformer.getjProgressBar().setIndeterminate(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                ArrayList arrayList = null;

                try {

                    addListeners();

                    hm.put("collector", collector);
                    hm.put("request", "get default data " + type);

                    arrayList = (ArrayList) new Linker().sendReceiveObject(hm);

                    if (arrayList == null) {
                        return null;
                    }

                    shaperInformer.getjTextArea().setText(arrayList.get(0).toString());

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    shaperInformer.getjProgressBar().setIndeterminate(false);
                }

                return null;
            }
        }.execute();

    }

    //==========================================================================
    private void addListeners() {
        shaperInformer.getjButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    } // end addListeners

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        shaperInformer.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
    }
} // end class