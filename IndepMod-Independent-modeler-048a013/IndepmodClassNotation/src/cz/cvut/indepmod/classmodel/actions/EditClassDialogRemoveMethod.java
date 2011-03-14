package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractEditClassDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import java.awt.event.ActionEvent;

/**
 * Date: 23.10.2010
 * Time: 12:43:07
 * @author Lucky
 */
public class EditClassDialogRemoveMethod extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_edit_class_dialog_rem_method");
    private ClassModel model;
    private AbstractEditClassDialog dialog;

    public EditClassDialogRemoveMethod(ClassModel model, AbstractEditClassDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        MethodModel method = this.dialog.getSelectedMethod();
        if (method != null) {
            this.model.removeMethod(method);
            this.dialog.updateCell();
        }
    }

}
