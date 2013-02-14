package model.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import model.beans.ClusterSniffer;

/**
 * utilities for cluster bean.
 * @author skuarch
 */
public class ClusterUtilities {

    //==========================================================================
    public ClusterUtilities() {
        ClusterSniffer clusterSniffer = new ClusterSniffer();
        clusterSniffer.getCategorie();
    } // end ClusterUtilities

    //==========================================================================    
    public static ClusterSniffer createClusterFromCluster(ClusterSniffer cluster) throws Exception {

        ClusterSniffer newCluster = new ClusterSniffer();
        BeanInfo beanInfo = Introspector.getBeanInfo(cluster.getClass());
        Method getMethod = null;
        Method setMethod = null;

        try {

            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {

                getMethod = propertyDescriptor.getReadMethod();
                setMethod = propertyDescriptor.getWriteMethod();

                if (propertyDescriptor.getName().contains("class")) {
                    continue;
                }

                if (setMethod == null) {
                    continue;
                }

                setMethod.invoke(newCluster, getMethod.invoke(cluster, null));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            beanInfo = null;
            getMethod = null;
            getMethod = null;
        }

        return newCluster;
    } // end createClusterFromCluster

    //==========================================================================
    public static HashMap getHashMap(ClusterSniffer cluster) throws Exception {

        HashMap hashMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(cluster.getClass());
        Method getMethod = null;
        String tmpResult = null;

        try {

            for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {

                if (propertyDescriptor.getName().contains("class")) {
                    continue;
                }

                getMethod = propertyDescriptor.getReadMethod();

                if (getMethod.getName().contains("Formatted")) {
                    hashMap.put(getMethod.invoke(cluster, null), tmpResult);
                }

                tmpResult = (String) getMethod.invoke(cluster, null);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            beanInfo = null;
            getMethod = null;
            getMethod = null;
        }

        return hashMap;
    } // end getHashMap
    
} // end class