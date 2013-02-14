package model.common;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import model.beans.Collectors;
import model.dao.DAO;

/**
 *
 * @author skuarch
 */
public class ModelCollectors {

    //==========================================================================
    public ModelCollectors() {
    } // end ModelCollectors

    //==========================================================================
    public ArrayList<Collectors> getCollectors() throws Exception {

        ArrayList<Collectors> collectors = null;

        try {

            collectors = (ArrayList<Collectors>) new DAO().getAll("Collectors");

        } catch (Exception e) {
            throw e;
        }

        return collectors;

    } // end getCollectors

    //==========================================================================
    public ArrayList<Collectors> getActivesCollectors() throws Exception {

        ArrayList<Collectors> collectors = null;
        ArrayList<Collectors> activeCollectors = null;

        try {

            collectors = getCollectors();

            if (collectors.size() > 0) {
                activeCollectors = new ArrayList<Collectors>();

                for (int i = 0; i < collectors.size(); i++) {

                    if (collectors.get(i).getActive() == 1) {
                        activeCollectors.add(collectors.get(i));
                    }
                }
            }

            collectors.clear();

        } catch (Exception e) {
            throw e;
        } finally {
            collectors = null;
        }

        return activeCollectors;

    } // end getActivesCollectors

    //==========================================================================
    public Collectors[] getActivesCollectorsArray() throws Exception {

        ArrayList<Collectors> collectors = null;
        Collectors[] activesCollectors = null;
        List<Collectors> collectorsList = new ArrayList<Collectors>();

        try {

            collectors = getCollectors();

            for (int i = 0; i < collectors.size(); i++) {
                if (collectors.get(i).getActive() == 1) {
                    collectorsList.add(collectors.get(i));
                }
            }

            activesCollectors = new Collectors[collectorsList.size()];

            for (int i = 0; i < collectorsList.size(); i++) {
                activesCollectors[i] = collectorsList.get(i);
            }

        } catch (Exception e) {
            throw e;
        }

        return activesCollectors;

    } // end getActivesCollectorsArray

    //==========================================================================
    public String[] getActivesCollectorsStringArray() throws Exception {

        ArrayList<Collectors> collectors = null;
        String[] activesCollectors = null;
        List<Collectors> collectorsList = new ArrayList<Collectors>();

        try {

            collectors = getCollectors();

            for (int i = 0; i < collectors.size(); i++) {
                if (collectors.get(i).getActive() == 1) {
                    collectorsList.add(collectors.get(i));
                }
            }

            activesCollectors = new String[collectorsList.size()];

            for (int i = 0; i < collectorsList.size(); i++) {
                activesCollectors[i] = collectorsList.get(i).getName();
            }

        } catch (Exception e) {
            throw e;
        }

        return activesCollectors;

    } // end getActivesCollectorsArray  

    //==========================================================================
    public DefaultTreeModel getCollectorsModel() throws Exception {

        ArrayList<Collectors> collectors = null;
        DefaultMutableTreeNode rootNode = null;
        DefaultTreeModel model = null;
        Thread[] threadCollectors = null;
        ArrayList<DefaultMutableTreeNode> arrayNodes = null;

        try {

            rootNode = new DefaultMutableTreeNode("collectors");
            model = new DefaultTreeModel(rootNode);
            collectors = new ModelCollectors().getActivesCollectors();

            if (collectors == null || collectors.size() < 1) {
                rootNode = new DefaultMutableTreeNode("without collectors");
                model = new DefaultTreeModel(rootNode);
                return model;
            }

            threadCollectors = new Thread[collectors.size()];
            arrayNodes = new ArrayList<DefaultMutableTreeNode>();

            // run each thread and save
            for (int i = 0; i < collectors.size(); i++) {
                threadCollectors[i] = new ThreadNode(collectors.get(i).getName(), arrayNodes);
                threadCollectors[i].start();
            }

            // check if all the threads ended
            for (int i = 0; i < threadCollectors.length; i++) {
                if (threadCollectors[i].isAlive()) {
                    threadCollectors[i].join();
                } else {
                    continue;
                }
            }

            // change node wait to servers
            rootNode = new DefaultMutableTreeNode("servers");
            model = new DefaultTreeModel(rootNode);

            //put nodes into the jtree
            for (int i = 0; i < arrayNodes.size(); i++) {
                model.insertNodeInto(arrayNodes.get(i), rootNode, i);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            collectors = null;
        }

        return model;

    } // end getCollectorsModel
    
    
    //==========================================================================
    public DefaultTreeModel getCollectorsModelLogs() throws Exception {

        ArrayList<Collectors> collectors = null;
        DefaultMutableTreeNode rootNode = null;
        DefaultTreeModel model = null;
        Thread[] threadCollectors = null;
        ArrayList<DefaultMutableTreeNode> arrayNodes = null;

        try {

            rootNode = new DefaultMutableTreeNode("collectors");
            model = new DefaultTreeModel(rootNode);
            collectors = new ModelCollectors().getActivesCollectors();

            if (collectors == null || collectors.size() < 1) {
                rootNode = new DefaultMutableTreeNode("without collectors");
                model = new DefaultTreeModel(rootNode);
                return model;
            }

            threadCollectors = new Thread[collectors.size()];
            arrayNodes = new ArrayList<DefaultMutableTreeNode>();

            // run each thread and save
            for (int i = 0; i < collectors.size(); i++) {
                threadCollectors[i] = new ThreadNodeLogs(collectors.get(i).getName(), arrayNodes);
                threadCollectors[i].start();
            }

            // check if all the threads ended
            for (int i = 0; i < threadCollectors.length; i++) {
                if (threadCollectors[i].isAlive()) {
                    threadCollectors[i].join();
                } else {
                    continue;
                }
            }

            // change node wait to servers
            rootNode = new DefaultMutableTreeNode("servers");
            model = new DefaultTreeModel(rootNode);

            //put nodes into the jtree
            for (int i = 0; i < arrayNodes.size(); i++) {
                model.insertNodeInto(arrayNodes.get(i), rootNode, i);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            collectors = null;
        }

        return model;

    } // end getCollectorsModelLogs
} // end class
