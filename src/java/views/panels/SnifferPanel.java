package views.panels;

import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author skuarch
 */
public abstract class SnifferPanel extends JPanel implements DropTargetListener {

    //==========================================================================
    public SnifferPanel() {
        super();
    }

    //==========================================================================
    @Override
    public void updateUI() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        SnifferPanel.super.updateUI();
                    }
                });
            }
        }).start();

    } // end updateUI

    //==========================================================================
    public abstract Object getData();

    //==========================================================================
    public abstract HashMap getHashMap();

    //==========================================================================
    public abstract void destroy();

    //==========================================================================
    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    //==========================================================================
    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    //==========================================================================
    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    //==========================================================================
    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    //==========================================================================
    @Override
    public void drop(DropTargetDropEvent dtde) {
    }
    
} // end class
