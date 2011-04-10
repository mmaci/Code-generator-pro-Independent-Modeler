package cz.cvut.indepmod.classmodel.frames.dialogs;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

/**
 * Date: 17.10.2010
 * Time: 12:10:02
 * @author Lucky
 */
public abstract class AbstractClassModelDialog extends JDialog {

    public AbstractClassModelDialog(Frame owner, String title) {
        this(owner, title, true);
    }

    public AbstractClassModelDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.initActions();
    }

    public void setSizes() {
        this.pack();

        Rectangle abounds = getBounds();
        this.setSizes(abounds.width, abounds.height);
    }

    public void setSizes(int width, int height) {
        this.setSize(width, height);

        Dimension dim = getToolkit().getScreenSize();
        setLocation((dim.width - width) / 2,
                (dim.height - height) / 2);

        this.setMinimumSize(this.getSize());
        this.setVisible(true);
        this.requestFocus();
    }

    //==================== PRIVATE METHODS =====================================

    private void initActions() {
        this.getRootPane().registerKeyboardAction(
                new DisposeAction(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    //================== INNER CLASS ===========================================
    private class DisposeAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractClassModelDialog.this.dispose();
        }
    }
}
