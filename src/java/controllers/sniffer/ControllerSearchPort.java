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
import views.dialogs.SearchPort;
import views.helper.JTextFieldLimit;

/**
 *
 * @author skuarch
 */
class ControllerSearchPort extends Controller {

    private SearchPort searchPort = null;
    private String collector = null;
    private String job = null;
    private String port;

    //==========================================================================
    public ControllerSearchPort() {
        searchPort = new SearchPort(null, true);
    } // end ControllerSeachIPAddress

    //==========================================================================
    @Override
    public void setupInterface() {

        setEnabledComponents(false);
        searchPort.getjProgressBar().setIndeterminate(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {

                    searchPort.getjTextFieldPort().setDocument(new JTextFieldLimit(5));

                    addListeners();

                    ViewUtilities.fillJComboBox(searchPort.getjComboBoxCollectors(), new ModelCollectors().getActivesCollectorsStringArray());

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    setEnabledComponents(true);
                    searchPort.getjProgressBar().setIndeterminate(false);
                }

                return null;
            }
        }.execute();
    }

    //==========================================================================
    private void addListeners() {

        searchPort.getjComboBoxCollectors().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboCollectorsChange();
            }
        });

        searchPort.getjComboBoxJobs().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                comboJobsClick();
            }
        });

        searchPort.getjButtonClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        searchPort.getjButtonSearch().addActionListener(new ActionListener() {
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

            port = searchPort.getjTextFieldPort().getText();
            collector = searchPort.getjComboBoxCollectors().getModel().getSelectedItem().toString();
            job = searchPort.getjComboBoxJobs().getModel().getSelectedItem().toString();

            if (!ViewUtilities.validatePort(port)) {
                NOTIFICATIONS.error("Port is incorrect", new Exception(""));
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
            hm.put("portNumber", port);
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

        searchPort.getjProgressBar().setIndeterminate(true);
        setEnabledComponents(false);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                String selected = null;

                try {

                    selected = searchPort.getjComboBoxCollectors().getModel().getSelectedItem().toString();

                    if (selected == null || selected.contains("select") || selected.equalsIgnoreCase("no collectors")) {
                        return null;
                    }

                    collector = selected;

                    ViewUtilities.fillJComboBox(searchPort.getjComboBoxJobs(), new String[]{"loading"});
                    searchPort.getjComboBoxJobs().removeAllItems();
                    ViewUtilities.fillJComboBox(searchPort.getjComboBoxJobs(), new ModelJobs().getJobs(collector));

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                } finally {
                    searchPort.getjProgressBar().setIndeterminate(false);
                    setEnabledComponents(true);
                }

                return null;
            }
        }.execute();


    } // end comboCollectorsChange

    //==========================================================================
    private void comboJobsClick() {

        try {

            job = searchPort.getjComboBoxJobs().getModel().getSelectedItem().toString();

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end comboJobsClick    

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        searchPort.setVisible(flag);
    }

    //==========================================================================
    private void setEnabledComponents(boolean flag) {
        searchPort.getjTextFieldPort().setEnabled(flag);
        searchPort.getjButtonSearch().setEnabled(flag);
        searchPort.getjComboBoxCollectors().setEnabled(flag);
        searchPort.getjComboBoxJobs().setEnabled(flag);
    } // end setEnabledComponents

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class