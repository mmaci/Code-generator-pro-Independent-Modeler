package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractEditClassDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.AnotationCreatorDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import org.openide.windows.WindowManager;

/**
 * Date: 25.11.2010
 * Time: 17:57:34
 * @author Lucky
 */
public class EditClassDialogAddAnotation extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_edit_class_dialog_add_anot");
    private static final Logger LOG = Logger.getLogger(EditClassDialogAddAnotation.class.getName());
    private ClassModel model;
    private AbstractEditClassDialog dialog;

    public EditClassDialogAddAnotation(ClassModel model, AbstractEditClassDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Frame window = WindowManager.getDefault().getMainWindow();
        AnotationModel anot = new AnotationCreatorDialog(window).getAnotation();

        if (anot != null) {
            this.model.addAnotation(anot);
            this.dialog.updateCell();
            LOG.info("Added anotation");
        } else {
            LOG.info("Anotation was not added");
        }
    }
}
