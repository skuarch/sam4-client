package views.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.util.DateUtilities;
import model.util.ViewUtilities;

/**
 *
 * @author skuarch
 */
public class EventViewer extends JDialog {

    private static EventViewer INSTANCE = null;
    private String textConsole = "";
    private JScrollPane scrollTextArea = null;
    private JTextArea textArea = null;
    private JButton refreshButton = null;
    private JButton cleanButton = null;    
    private JButton onTopButton = null;
    private JButton shutdownButton = null;

    //==========================================================================
    private EventViewer(JFrame frame, boolean isModal) {
        super(frame, isModal);        
        textArea = new JTextArea();
        scrollTextArea = new JScrollPane(textArea);
        refreshButton = ViewUtilities.getRefreshButton();
        cleanButton = ViewUtilities.getCleanButton();        
        onTopButton = ViewUtilities.getOnTopButton();
        shutdownButton = ViewUtilities.getShutdownButton();

        initComponents();
        setLocationRelativeTo(getRootPane());
        setIconImage(Toolkit.getDefaultToolkit().getImage(EventViewer.class.getResource("/views/images/sam_icon.png")));
    } // end EventViewer

     //==========================================================================
    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventViewer(null, false);
        }
    }

    //==========================================================================
    public static EventViewer getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    //==========================================================================
    private void initComponents() {

        try {

            setTitle("Event viewer");
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(600, 500));
            JToolBar toolBar = new JToolBar();
            toolBar.add(refreshButton);
            toolBar.add(cleanButton);            
            toolBar.add(onTopButton);
            toolBar.add(shutdownButton);
            toolBar.setFloatable(false);
            add(toolBar, BorderLayout.NORTH);

            //textArea             
            textArea.setEditable(false);
            add(scrollTextArea, BorderLayout.CENTER);

            pack();
            addListeners();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //==========================================================================
    public void appendInfoTextConsole(String text) {
        try {
            System.out.println(DateUtilities.getCurrentDate() + " INFO: " + text);
            textArea.append(DateUtilities.getCurrentDate() + " INFO: " + text + "\n");
            textConsole += DateUtilities.getCurrentDate() + " INFO: " + text + "\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end appendInfoTextConsole

    //==========================================================================
    public void appendWarmTextConsole(String text) {
        try {
            System.out.println(DateUtilities.getCurrentDate() + " WARNING: " + text);
            textArea.append(DateUtilities.getCurrentDate() + " WARNING: " + text + "\n");
            textConsole += DateUtilities.getCurrentDate() + " WARNING: " + text + "\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end appendWarmTextConsole

    //==========================================================================
    public String getText() {
        return textConsole;
    } // end getText

    //==========================================================================
    public void appendErrorTextConsole(String text) {
        try {
            System.out.println(DateUtilities.getCurrentDate() + " ERROR: " + text);
            textArea.append(DateUtilities.getCurrentDate() + " ERROR: " + text + "\n");
            textConsole += DateUtilities.getCurrentDate() + " ERROR: " + text + "\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end appendErrorTextConsole

    //==========================================================================
    private void addListeners() {

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                javax.swing.SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        try {

                            refreshButton.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    textArea.setText("refreshing");
                                    textArea.setText(textConsole);
                                }
                            });

                            cleanButton.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    textConsole = "";
                                    textArea.setText(textConsole);
                                }
                            });

                            onTopButton.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    if (isAlwaysOnTop()) {
                                        setAlwaysOnTop(false);
                                    } else {
                                        setAlwaysOnTop(true);
                                    }
                                }
                            });                            

                            shutdownButton.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent ae) {
                                    setVisible(false);
                                }
                            });

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        t.setName("EventViewer.addListeners");
        t.start();

    } // end addListeners
} // end class