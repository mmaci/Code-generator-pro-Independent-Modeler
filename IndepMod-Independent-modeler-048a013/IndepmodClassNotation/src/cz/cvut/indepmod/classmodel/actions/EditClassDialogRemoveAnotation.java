package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractEditClassDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.event.ActionEvent;

/**
 * Date: 25.11.2010
 * Time: 18:14:49
 * @author Lucky
 */
public class EditClassDialogRemoveAnotation extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_edit_class_dialog_rem_anot");
    private ClassModel model;
    private AbstractEditClassDialog dialog;

    public EditClassDialogRemoveAnotation(ClassModel model, AbstractEditClassDialog dialog) {
        super(ACTION_NAME, null);

        this.model = model;
        this.dialog = dialog;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        AnotationModel anotation = this.dialog.getSelectedAnotation();
        if (anotation != null) {
            this.model.removeAnotation(anotation);
            this.dialog.updateCell();
        }
    }

}
