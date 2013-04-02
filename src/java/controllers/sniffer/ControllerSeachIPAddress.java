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
import views.dialogs.SearchIpAddress;
import views.helper.JTextFieldLimit;

/**
 *
 * @author skuarch
 */
public class ControllerSeachIPAddress extends Controller {

    private SearchIpAddress searchIpAddress = null;
    private String collector = null;
    private String job = null;
    private String ip = null;

    //==========================================================================
    public ControllerSeachIPAddress() {
        searchIpAddress = new SearchIpAddress(null, true);
    } // end ControllerSeachIPAddress

    //==========================================================================
    @Override
    public void setupInterface() {

        setEnabledComponents(false);
        searchIpAddress.getjProgressBar().setIndeterminate(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {

                    searchIpAddress.getjTextFieldIP1().setDocument(new JTextFieldLimit(3));
                    searchIpAddress.getjTextFieldIP2().setDocument(new JTextFieldLimit(3));
                    searchIpAddress.getjTextFieldIP3().setDocument(new JTextFieldLimit(3));
                    searchIpAddress.getjTextFieldIP4().setDocument(new JTextFieldLimit(3));

                    addListeners();

                    ViewUtilities.fillJComboBox(searchIpAddress.getjComboBoxCollectors(), new ModelCollectors().getActivesCollectorsStringArray());

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    setEnabledComponents(true);
                    searchIpAddress.getjProgressBar().setIndeterminate(false);
                }

                return null;
            }
        }.execute();
    }

    //==========================================================================
    private void addListeners() {

        searchIpAddress.getjComboBoxCollectors().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboCollectorsChange();
            }
        });

        searchIpAddress.getjComboBoxJobs().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                comboJobsClick();
            }
        });

        searchIpAddress.getjButtonClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        searchIpAddress.getjButtonSearch().addActionListener(new ActionListener() {
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

            ip = searchIpAddress.getjTextFieldIP1().getText() + "." + searchIpAddress.getjTextFieldIP2().getText() + "." + searchIpAddress.getjTextFieldIP3().getText() + "." + searchIpAddress.getjTextFieldIP4().getText();
            collector = searchIpAddress.getjComboBoxCollectors().getModel().getSelectedItem().toString();
            job = searchIpAddress.getjComboBoxJobs().getModel().getSelectedItem().toString();

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

            hm.put("collector", collector);
            hm.put("job", job);
            hm.put("ipAddress", ip);
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

        searchIpAddress.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                String selected = null;

                try {

                    selected = searchIpAddress.getjComboBoxCollectors().getModel().getSelectedItem().toString();

                    if (selected == null || selected.contains("select") || selected.equalsIgnoreCase("no collectors")) {
                        return null;
                    }

                    collector = selected;

                    ViewUtilities.fillJComboBox(searchIpAddress.getjComboBoxJobs(), new String[]{"loading"});
                    searchIpAddress.getjComboBoxJobs().removeAllItems();
                    ViewUtilities.fillJComboBox(searchIpAddress.getjComboBoxJobs(), new ModelJobs().getJobs(collector));

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    searchIpAddress.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

                return null;
            }
        }.execute();


    } // end comboCollectorsChange

    //==========================================================================
    private void comboJobsClick() {

        try {

            job = searchIpAddress.getjComboBoxJobs().getModel().getSelectedItem().toString();

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end comboJobsClick    

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        searchIpAddress.setVisible(flag);
    }

    //==========================================================================
    private void setEnabledComponents(boolean flag) {
        searchIpAddress.getjTextFieldIP1().setEnabled(flag);
        searchIpAddress.getjTextFieldIP2().setEnabled(flag);
        searchIpAddress.getjTextFieldIP3().setEnabled(flag);
        searchIpAddress.getjTextFieldIP4().setEnabled(flag);
        searchIpAddress.getjButtonSearch().setEnabled(flag);
        searchIpAddress.getjComboBoxCollectors().setEnabled(flag);
        searchIpAddress.getjComboBoxJobs().setEnabled(flag);
    } // end setEnabledComponents

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
} // end class