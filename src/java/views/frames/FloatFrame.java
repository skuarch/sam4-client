package views.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author skuarch
 */
public class FloatFrame extends JFrame {
    
    private JButton jButtonClose = new JButton();
    private JButton jButtonOnTop = new JButton();
    private JButton jButtonSwap = new JButton();
    private JPanel panel = new JPanel();

    //==========================================================================
    public FloatFrame() {        
        onLoad();
    } // end FloatFrame   

    //==========================================================================
    private void onLoad() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(FloatFrame.class.getResource("/views/images/sam_icon.png")));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(650, 550));
        setLocationRelativeTo(getRootPane());
        jButtonSwap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/swap.png")));
        jButtonOnTop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/onTop.png")));
        jButtonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/images/shutdown.png")));
        panel.add(jButtonSwap);
        panel.add(jButtonOnTop);
        panel.add(jButtonClose);
        add(panel, BorderLayout.NORTH);
        pack();
    } // end onLoad

    public JButton getjButtonClose() {
        return jButtonClose;
    }

    public void setjButtonClose(JButton jButtonClose) {
        this.jButtonClose = jButtonClose;
    }

    public JButton getjButtonOnTop() {
        return jButtonOnTop;
    }

    public void setjButtonOnTop(JButton jButtonOnTop) {
        this.jButtonOnTop = jButtonOnTop;
    }

    public JButton getjButtonSwap() {
        return jButtonSwap;
    }

    public void setjButtonSwap(JButton jButtonSwap) {
        this.jButtonSwap = jButtonSwap;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
} // end class