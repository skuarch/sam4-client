package model.util;

import controllers.global.ControllerNotifications;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import model.beans.Collectors;
import model.common.ModelCollectors;

/**
 *
 * @author skuarch
 */
public class ViewUtilities {

    //==========================================================================
    private ViewUtilities() {
    }

    //==========================================================================
    public static JButton getShutdownButton() {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/shutdown.png")));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    } // end getShutdownButton

    //==========================================================================
    public static JButton getCleanButton() {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/clear.png")));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    } // end getClearButton

    //==========================================================================
    public static JButton getOnTopButton() {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/onTop.png")));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    } // end getOnTopButton

    //==========================================================================
    public static JButton getRefreshButton() {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/refresh.png")));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    } // end getRefreshButton

    //==========================================================================
    public static JButton getFloatButton() {
        JButton buttonFloat = new JButton();
        buttonFloat.setPreferredSize(new Dimension(10, 10));
        buttonFloat.setBackground(Color.blue);
        return buttonFloat;
    } // end getFloatButton

    //==========================================================================
    public static JButton getNextButton() {
        JButton next = new JButton();
        next.setPreferredSize(new Dimension(25, 25));
        next.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/next.png")));
        return next;
    } // end getNextButton

    //==========================================================================
    public static JButton getBackButton() {
        JButton back = new JButton();
        back.setPreferredSize(new Dimension(25, 25));
        back.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/back.png")));
        return back;
    } // end getBackButton

    //==========================================================================
    public static JButton getOpenButton() {
        JButton open = new JButton();
        open.setPreferredSize(new Dimension(18, 18));
        open.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/newWindow.png")));
        return open;
    } // end getOpenButton    

    //==========================================================================
    public static JLabel getWhiteLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLUE);
        return label;
    } // end getWhiteLabel

    //==========================================================================
    public static JButton getExportExcelButton() {
        JButton export = new JButton("");
        export.setToolTipText("export to excel");
        export.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/table-export.png")));
        return export;
    }

    //==========================================================================
    public static JButton getTableButton() {
        JButton table = new JButton("");
        table.setToolTipText("show table");
        table.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/table.png")));
        return table;
    }

    //==========================================================================
    public static JButton getDetailButton() {
        JButton detail = new JButton("");
        detail.setToolTipText("details");
        detail.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/detail.png")));
        return detail;
    }

    //==========================================================================
    public static JButton getPdfButton() {
        JButton pdf = new JButton("");
        pdf.setToolTipText("pdf");
        pdf.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/pdf.png")));
        return pdf;
    } // end getPdfButton

    //==========================================================================
    public static JList getListLineChart() {
        JList list = new JList();
        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setVisibleRowCount(-1);
        list.setBackground(Color.WHITE);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        return list;
    } // end getListVertical

    //==========================================================================
    public static JList getListBarChart() {
        JList list = new JList();
        list.setVisibleRowCount(-1);
        list.setBackground(Color.WHITE);
        return list;
    } // end getListHorinzontal

    //==========================================================================
    public static DefaultComboBoxModel getDefaultComboBoxModelCollectors() throws Exception {

        DefaultComboBoxModel defaultComboBoxModel = null;
        String[] collectors = new String[]{"no collectors"};
        ArrayList<Collectors> servers = null;

        try {

            servers = new ModelCollectors().getActivesCollectors();

            if (servers.size() > 0) {

                collectors = new String[servers.size() + 1];
                collectors[0] = "select a collector";

                for (int i = 1; i < collectors.length; i++) {
                    collectors[i] = servers.get(i - 1).getName();
                }

            } else {

                collectors = new String[]{"no collectors"};

            }


            defaultComboBoxModel = new DefaultComboBoxModel(collectors);

        } catch (Exception e) {
            throw e;
        }

        return defaultComboBoxModel;

    } // end getComboBoxServers

    //==========================================================================
    public static JTree getJtreeFromJTabPane(JTabbedPane jTabbedPane) throws Exception {

        if (jTabbedPane == null || jTabbedPane.getComponents().length < 1) {
            throw new Exception("jTabbedPane is null or doesn't have components");
        }

        JPanel panel = null;
        JScrollPane scrollPane = null;
        JViewport viewport = null;
        JTree jtree = null;

        try {

            panel = (JPanel) jTabbedPane.getComponentAt(0);
            scrollPane = (JScrollPane) panel.getComponent(1);
            viewport = (JViewport) scrollPane.getComponent(0);
            jtree = (JTree) viewport.getComponent(0);

        } catch (Exception e) {
            throw e;
        }

        return jtree;
    } // end getJtreeFromJTabPane

    //==========================================================================
    public static String getSelectedJTree(JTree jTree) throws Exception {

        if (jTree == null) {
            throw new Exception("jTree is null");
        }

        String selected = null;
        TreePath treePath = null;

        try {

            treePath = jTree.getSelectionPath();
            if (treePath == null) {
                return null;
            } else {
                selected = treePath.getLastPathComponent().toString();
            }

        } catch (Exception e) {
            throw e;
        }

        return selected;

    } // end getSelectedJTree

    //==========================================================================
    public static String getOneBefore(TreePath treePath) throws Exception {

        if (treePath == null) {
            return null;
        }

        String rtn = null;
        int length = 0;

        try {

            rtn = treePath.toString();
            rtn = rtn.replace("[", "");
            rtn = rtn.replace("]", "");
            length = rtn.split(",").length;

            if (rtn.contains(",")) {

                //tree root
                if (length == 1) {
                    rtn = rtn.split(",")[0];
                }

                //tree second level
                if (length == 2) {
                    rtn = rtn.split(",")[1];
                }

                if (length == 3) {
                    rtn = rtn.split(",")[1];
                }

            }

            //without space
            if (rtn.startsWith(" ")) {
                rtn = rtn.replaceFirst(" ", "");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            length = 0;
            treePath = null;
        }

        return rtn;

    } //  end getOneBefore

    //==========================================================================
    public static DefaultMutableTreeNode rootNodeCollectors() throws Exception {

        DefaultMutableTreeNode rootNode = null;
        Collectors[] collectors = null;

        try {

            rootNode = new DefaultMutableTreeNode("collectors");
            collectors = new ModelCollectors().getActivesCollectorsArray();

            for (int i = 0; i < collectors.length; i++) {
                rootNode.add(new DefaultMutableTreeNode(collectors[i].getName()));
            }

        } catch (Exception e) {
            throw e;
        }

        return rootNode;

    } // end rootNodeCollectors 
    //==========================================================================
    /*public static JButton getShutdownButton() {

     JButton button = null;

     try {

     button = new JButton();
     button.setIcon(new ImageIcon(SwingUtilities.class.getResource("/views/images/shutdown.png")));
     button.setHorizontalTextPosition(SwingConstants.CENTER);
     button.setVerticalTextPosition(SwingConstants.BOTTOM);

     } catch (Exception e) {
     new ControllerNotifications().error("error creating button shutdown", e);
     }

     return button;
     } // end getShutdownButton*/

    //==========================================================================
    public static JButton getEnableButton() {
        JButton button = null;

        try {

            button = new JButton();
            button.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/ok.png")));
            //button.setHorizontalTextPosition(SwingConstants.CENTER);
            //button.setVerticalTextPosition(SwingConstants.BOTTOM);

        } catch (Exception e) {
            new ControllerNotifications().error("error creating button enable", e);
        }

        return button;
    } // end getEnableButton

    //==========================================================================
    public static JButton getEditButton() {
        JButton button = null;

        try {

            button = new JButton();
            button.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/edit.png")));
            //button.setHorizontalTextPosition(SwingConstants.CENTER);
            ///button.setVerticalTextPosition(SwingConstants.BOTTOM);

        } catch (Exception e) {
            new ControllerNotifications().error("error creating button edit", e);
        }

        return button;
    } // end getEditButton

    //==========================================================================
    public static JButton getDeleteButton() {
        JButton button = null;

        try {

            button = new JButton();
            button.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/delete.png")));
            //button.setHorizontalTextPosition(SwingConstants.CENTER);
            //button.setVerticalTextPosition(SwingConstants.BOTTOM);

        } catch (Exception e) {
            new ControllerNotifications().error("error creating button delete", e);
        }

        return button;
    } // end getDeleteButton

    //==========================================================================
    /*public static JButton getCleanButton() {

     JButton button = null;

     try {

     button = new JButton();
     button.setIcon(new ImageIcon(SwingUtilities.class.getResource("/views/images/clear.png")));
     button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
     button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

     } catch (Exception e) {
     new ControllerNotifications().error("error creating button clear", e);
     }

     return button;
     } // end getClearButton

     //==========================================================================
     /*public static JButton getOnTopButton() {

     JButton button = null;

     try {

     button = new JButton();
     button.setIcon(new ImageIcon(SwingUtilities.class.getResource("/views/images/onTop.png")));
     button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
     button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

     } catch (Exception e) {
     new ControllerNotifications().error("error creating button on top", e);
     }

     return button;
     } // end getOnTopButton*/
    //==========================================================================
    public static JButton getMailButton() {

        JButton button = null;

        try {

            button = new JButton();
            button.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/forward.png")));
            button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        } catch (Exception e) {
            new ControllerNotifications().error("error creating button mail", e);
        }

        return button;
    } // end getMailButton

    //==========================================================================
    /*public static JButton getRefreshButton() {

     JButton button = null;

     try {

     button = new JButton();
     button.setIcon(new ImageIcon(SwingUtilities.class.getResource("/views/images/refresh.png")));
     button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
     button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

     } catch (Exception e) {
     new ControllerNotifications().error("error creating button refresh", e);
     }

     return button;
     } // end getRefreshButton*/
    //==========================================================================
    /*public static JButton getFloatButton() {
     JButton buttonFloat = new JButton();
     buttonFloat.setPreferredSize(new Dimension(10, 10));
     buttonFloat.setBackground(Color.blue);
     return buttonFloat;
     } // end getFloatButton

     //==========================================================================
     public JButton getNextButton() {
     JButton next = new JButton();
     next.setPreferredSize(new Dimension(25, 25));
     next.setIcon(new ImageIcon(getClass().getResource("/views/images/next.png")));
     return next;
     } // end getNextButton

     //==========================================================================
     public JButton getBackButton() {
     JButton back = new JButton();
     back.setPreferredSize(new Dimension(25, 25));
     back.setIcon(new ImageIcon(getClass().getResource("/views/images/back.png")));
     return back;
     } // end getBackButton*/
    //==========================================================================
    /*public JButton getOpenButton() {
     JButton open = new JButton();
     open.setPreferredSize(new Dimension(18, 18));
     open.setIcon(new ImageIcon(getClass().getResource("/views/images/newWindow.png")));
     return open;
     } // end getOpenButton*/
    //==========================================================================
    /*public static JLabel getWhiteLabel(String text) {
     JLabel label = new JLabel(text);
     label.setForeground(Color.BLUE);
     return label;
     } // end getWhiteLabel*/
    //==========================================================================
    /*public JButton getExportExcelButton() {
     JButton export = new JButton("");
     export.setToolTipText("export to excel");
     export.setIcon(new ImageIcon(getClass().getResource("/views/images/table-export.png")));
     return export;
     }*/
    //==========================================================================
    /*public JButton getTableButton() {
     JButton table = new JButton("");
     table.setToolTipText("show table");
     table.setIcon(new ImageIcon(getClass().getResource("/views/images/table.png")));
     return table;
     }*/
    //==========================================================================
    /*public JButton getDetailButton() {

     JButton detail = new JButton("");
     detail.setToolTipText("details");
     detail.setIcon(new ImageIcon(getClass().getResource("/views/images/detail.png")));

     return detail;
     }*/
    //==========================================================================
    /*public JButton getPdfButton() {

     JButton pdf = new JButton("");
     pdf.setToolTipText("pdf");
     pdf.setIcon(new ImageIcon(getClass().getResource("/views/images/pdf.png")));
     return pdf;
     } // end getPdfButton*/
    //==========================================================================
    /*public static JList getListLineChart() {
     JList list = new JList();
     list.setLayoutOrientation(JList.VERTICAL_WRAP);
     list.setVisibleRowCount(-1);
     list.setBackground(Color.WHITE);
     list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
     return list;
     } // end getListVertical*/
    //==========================================================================
    /*public static JList getListBarChart() {
     JList list = new JList();
     list.setVisibleRowCount(-1);
     list.setBackground(Color.WHITE);
     return list;
     } // end getListHorinzontal*/
    //==========================================================================
    /*public DefaultComboBoxModel getDefaultComboBoxModelCollectors() {

     DefaultComboBoxModel defaultComboBoxModel = null;
     String[] collectors = new String[]{"no collectors"};
     Collectors[] servers = null;

     try {

     servers = new ModelCollectors().getActivesCollectorsArray();

     if (servers.length > 0) {

     collectors = new String[servers.length + 1];
     collectors[0] = "select a collector";

     for (int i = 1; i < collectors.length; i++) {
     collectors[i] = servers[i - 1].getName();
     }

     } else {

     collectors = new String[]{"no collectors"};

     }

     } catch (Exception e) {
     new ControllerNotifications().error("error creation combobox collectors", e);
     } finally {
     return defaultComboBoxModel = new DefaultComboBoxModel(collectors);
     }

     } // end getComboBoxServers*/
    //==========================================================================
    public boolean validateNetMask(String netMask) {

        boolean flag = false;
        int prefix = 0;

        try {

            prefix = Integer.parseInt(netMask);

            if (prefix > 32 || prefix < 0) {
                flag = false;
            } else {
                flag = true;
            }

        } catch (NumberFormatException nfe) {
            return false;
        } catch (Exception e) {
            new ControllerNotifications().error("error validating subnet", e);
        }

        return flag;

    } // end validateSubnet

    //==========================================================================
    public static Object[] getDataJList(JList jList) throws Exception {

        Object[] objects = null;
        ListModel lm = null;

        try {

            lm = jList.getModel();
            objects = new Object[lm.getSize()];

            for (int i = 0; i < lm.getSize(); i++) {
                objects[i] = lm.getElementAt(i);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            lm = null;
        }

        return objects;

    } // end getDataList

    //==========================================================================
    public static void passItemsLeftRigth(JList left, JList rigth) throws Exception {

        Object[] objectsLeft = null;
        Object[] objectsAllLeft = null;
        Object[] objectsRight = null;
        DefaultListModel defaultListModelRight = new DefaultListModel();
        DefaultListModel defaultListModelLeft = new DefaultListModel();

        try {

            objectsLeft = left.getSelectedValues();
            objectsRight = new Object[rigth.getModel().getSize()];

            //keep right data
            for (int i = 0; i < objectsRight.length; i++) {
                defaultListModelRight.addElement(rigth.getModel().getElementAt(i));
            }

            //add new data to right
            for (int i = 0; i < objectsLeft.length; i++) {
                defaultListModelRight.addElement(objectsLeft[i]);
            }

            rigth.clearSelection();
            rigth.setModel(defaultListModelRight);

            //drop off selected data in left
            objectsAllLeft = new Object[left.getModel().getSize()];
            for (int i = 0; i < left.getModel().getSize(); i++) {
                objectsAllLeft[i] = left.getModel().getElementAt(i);
            }

            for (int i = 0; i < objectsAllLeft.length; i++) {

                for (int o = 0; o < objectsLeft.length; o++) {
                    if (objectsLeft[o].equals(objectsAllLeft[i])) {
                        continue;
                    } else {
                        defaultListModelLeft.addElement(objectsAllLeft[i]);
                    }
                }

            }

            if (objectsLeft.length > 0) {
                left.setModel(defaultListModelLeft);
            }

        } catch (Exception e) {
            throw e;
        }

    } // end passItemsJlist

    //==========================================================================
    public static void fillJlist(JList list, Object[] objects) throws Exception {

        DefaultListModel dlm = new DefaultListModel();

        try {

            list.removeAll();

            for (Object objs : objects) {
                dlm.addElement(objs);
            }

            list.setModel(dlm);
            list.repaint();

        } catch (Exception e) {
            throw e;
        }

    } // end fillJlist

    //==========================================================================
    public static void fillJComboBox(JComboBox jComboBox, Object[] objects) throws Exception {

        try {

            for (Object object : objects) {
                jComboBox.addItem(object);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            objects = null;
        }

    } // end fillJComboBox

    //==========================================================================
    public void deleteTableRow(int row, JTable table) {

        DefaultTableModel model = null;

        try {
            model = (DefaultTableModel) table.getModel();

            if (model != null) {
                model.removeRow(row);
                model.fireTableDataChanged();
                table.repaint();
            }
        } catch (Exception e) {
            new ControllerNotifications().error("error deleting row", e);
        }

    } // end deleteTableRow

    //==========================================================================
    /**
     * null the current table model and set the new model.
     *
     * @param tableModel TableModel
     * @throws Exception
     */
    public static void setTableModel(DefaultTableModel tableModel, JTable table) throws Exception {

        if (tableModel == null) {
            throw new NullPointerException("tableModel is null");
        }

        TableModel tmpModel = null;

        try {

            tmpModel = table.getModel();
            tmpModel = null;
            table.setModel(tableModel);

            tableModel.fireTableDataChanged();
            table.repaint();

        } catch (Exception e) {
            throw e;
        }

    } // end setTableModel

    //==========================================================================
    /**
     * null the current table model and set the new model.
     *
     * @param data Object[][]
     * @param columnNames String[]
     * @throws Exception
     */
    public static void setTableModel(final Object[][] data, final String[] columnNames, final JTable table) throws Exception {

        if (columnNames == null) {
            throw new NullPointerException("columNames is null");
        }
        
        /* this validation is not necesary, the table may be is without data
         if (data == null) {
         throw new NullPointerException("data is null");
         }*/

        new Thread(new Runnable() {
            @Override
            public void run() {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        TableModel tmpModel = null;
                        DefaultTableModel dtm = null;

                        try {

                            tmpModel = table.getModel();
                            tmpModel = null;
                            dtm = new DefaultTableModel(data, columnNames);
                            table.setModel(dtm);
                            dtm.fireTableDataChanged();
                            table.repaint();

                        } catch (Exception e) {
                            new Thrower().exception(e);
                        }

                    }
                });

            }
        }).start();

    } // end setTableModel

    //==========================================================================
    public static boolean validatePort(int port) {

        boolean flag = false;

        try {

            if (port < 1 || port > 65535) {
                flag = false;
            } else {
                flag = true;
            }

        } catch (Exception e) {
            flag = false;
        }

        return flag;
    } // end validatePort

    //==========================================================================
    public static boolean validatePort(String port) {

        boolean flag = false;
        int num;

        try {

            if (port == null || port.equalsIgnoreCase("") || port.length() < 1) {
                flag = false;
            } else {
                num = Integer.parseInt(port);
                flag = validatePort(num);
            }

        } catch (Exception e) {
            flag = false;
        }

        return flag;
    } // end validatePort

    //==========================================================================
    public static boolean validateCollector(String collector) {

        boolean flag = false;

        try {

            if (collector == null || collector.length() < 1 || "no collectors".equalsIgnoreCase(collector)) {
                flag = false;
            } else {
                flag = true;
            }

        } catch (Exception e) {
            flag = false;
        }

        return flag;
    } // end validateCollector

    //==========================================================================
    public static boolean validateJob(String job) {

        boolean flag = false;

        try {

            if (job == null || job.length() < 1 || "no jobs".equalsIgnoreCase(job)) {
                flag = false;
            } else {
                flag = true;
            }

        } catch (Exception e) {
            flag = false;
        }

        return flag;
    } // end validateJob

    //==========================================================================
    public static boolean validaIPAddress(String ipAddress) {

        boolean flag = false;

        try {

            if (ipAddress == null || ipAddress.length() < 1 || ipAddress.equals("") || !new IPAddressValidator().validate(ipAddress)) {
                flag = false;
            } else {
                flag = true;
            }

        } catch (Exception e) {
            flag = false;
        }

        return flag;

    } // end validaIPAddress

    //==========================================================================
    public static boolean validateSubnet(String subnet) {

        boolean flag = false;
        int num = 0;

        try {

            if (subnet == null || subnet.length() < 1) {
                flag = false;
            } else {
                flag = true;
            }

            num = Integer.parseInt(subnet);

            if (num < 1 || num > 255) {
                flag = false;
            }


        } catch (Exception e) {
            flag = false;
        }

        return flag;
    } // end validateSubnet
} // end class
