package controllers.sniffer;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.SwingWorker;
import model.common.ModelCollectors;
import model.common.ModelJobs;
import model.util.HashMapUtilities;
import model.util.ViewUtilities;
import views.dialogs.SearchSubnet;
import views.helper.JTextFieldLimit;

/**
 *
 * @author skuarch
 */
public class ControllerSeachSubnet extends Controller {

    private SearchSubnet searchSubnet = null;
    private String collector = null;
    private String job = null;
    private String ip = null;
    private String subnet = null;

    //==========================================================================
    public ControllerSeachSubnet() {
        searchSubnet = new SearchSubnet(null, true);
    } // end ControllerSeachSubnet

    //==========================================================================
    @Override
    public void setupInterface() {

        setEnabledComponents(false);
        searchSubnet.getjProgressBar().setIndeterminate(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {

                    searchSubnet.getjTextFieldIP1().setDocument(new JTextFieldLimit(3));
                    searchSubnet.getjTextFieldIP2().setDocument(new JTextFieldLimit(3));
                    searchSubnet.getjTextFieldIP3().setDocument(new JTextFieldLimit(3));
                    searchSubnet.getjTextFieldIP4().setDocument(new JTextFieldLimit(3));
                    searchSubnet.getjTextFieldSubnet().setDocument(new JTextFieldLimit(2));

                    addListeners();

                    ViewUtilities.fillJComboBox(searchSubnet.getjComboBoxCollectors(), new ModelCollectors().getActivesCollectorsStringArray());

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    setEnabledComponents(true);
                    searchSubnet.getjProgressBar().setIndeterminate(false);
                }

                return null;
            }
        }.execute();
    }

    //==========================================================================
    private void addListeners() {

        searchSubnet.getjComboBoxCollectors().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboCollectorsChange();
            }
        });

        searchSubnet.getjComboBoxJobs().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                comboJobsClick();
            }
        });

        searchSubnet.getjButtonClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        searchSubnet.getjButtonSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

    } // end addListeners

    //==========================================================================
    private void search() {

        HashMap hm = HashMapUtilities.getHashMapSniffer();

        try {

            ip = searchSubnet.getjTextFieldIP1().getText() + "." + searchSubnet.getjTextFieldIP2().getText() + "." + searchSubnet.getjTextFieldIP3().getText() + "." + searchSubnet.getjTextFieldIP4().getText();
            collector = searchSubnet.getjComboBoxCollectors().getModel().getSelectedItem().toString();
            job = searchSubnet.getjComboBoxJobs().getModel().getSelectedItem().toString();
            subnet = searchSubnet.getjTextFieldSubnet().getText();

            if (!ViewUtilities.validaIPAddress(ip)) {
                NOTIFICATIONS.error("ip is incorrect", new Exception(""));
                return;
            }

            if (!ViewUtilities.validateCollector(collector)) {
                NOTIFICATIONS.error("collector is incorrect", new Exception(""));
                return;
            }

            if (!ViewUtilities.validateJob(job)) {
                NOTIFICATIONS.error("job is incorrect", new Exception(""));
                return;
            }
            
            if (!ViewUtilities.validateSubnet(subnet)) {
                NOTIFICATIONS.error("subnet is incorrect", new Exception(""));
                return;
            }
            

            hm.put("collector", collector);
            hm.put("job", job);
            hm.put("ipAddress", ip);
            hm.put("subnet",subnet);
            hm.put("view", "Bandwidth Over Time Bits");
            hm.put("request", "Bandwidth Over Time Bits");

            new TabsDriver().addTabNavigator(hm);
            setVisible(false);

        } catch (Exception e) {
            NOTIFICATIONS.error("Unepexted error", e);
        }

    } // end search

    //==========================================================================
    private void comboCollectorsChange() {

        searchSubnet.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                String selected = null;

                try {

                    selected = searchSubnet.getjComboBoxCollectors().getModel().getSelectedItem().toString();

                    if (selected == null || selected.contains("select") || selected.equalsIgnoreCase("no collectors")) {
                        return null;
                    }

                    collector = selected;

                    ViewUtilities.fillJComboBox(searchSubnet.getjComboBoxJobs(), new String[]{"loading"});
                    searchSubnet.getjComboBoxJobs().removeAllItems();
                    ViewUtilities.fillJComboBox(searchSubnet.getjComboBoxJobs(), new ModelJobs().getJobs(collector));

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    searchSubnet.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

                return null;
            }
        }.execute();


    } // end comboCollectorsChange

    //==========================================================================
    private void comboJobsClick() {

        try {

            job = searchSubnet.getjComboBoxJobs().getModel().getSelectedItem().toString();

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end comboJobsClick    

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        searchSubnet.setVisible(flag);
    }

    //==========================================================================
    private void setEnabledComponents(boolean flag) {
        searchSubnet.getjTextFieldIP1().setEnabled(flag);
        searchSubnet.getjTextFieldIP2().setEnabled(flag);
        searchSubnet.getjTextFieldIP3().setEnabled(flag);
        searchSubnet.getjTextFieldIP4().setEnabled(flag);
        searchSubnet.getjButtonSearch().setEnabled(flag);
        searchSubnet.getjComboBoxCollectors().setEnabled(flag);
        searchSubnet.getjComboBoxJobs().setEnabled(flag);
        searchSubnet.getjTextFieldSubnet().setEnabled(flag);
    } // end setEnabledComponents

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
} // end class