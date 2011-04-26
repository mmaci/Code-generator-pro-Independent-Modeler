package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractEditClassDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.MethodCreatorDialog;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import org.openide.windows.WindowManager;

/**
 * Date: 23.10.2010
 * Time: 12:40:57
 * @author Lucky
 */
public class EditClassDialogAddMethod extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_edit_class_dialog_add_method");

    private ClassModel model;
    private AbstractEditClassDialog dialog;

    public EditClassDialogAddMethod(ClassModel model, AbstractEditClassDialog dialog) {
        super(ACTION_NAME, null);
        this.model = model;
        this.dialog = dialog;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Frame window = WindowManager.getDefault().getMainWindow();
        MethodModel method = new MethodCreatorDialog(
                window,
                this.dialog.getAllTypeModel()).getMethod();

        if (method != null) {
            this.model.addMethod(method);
            this.dialog.updateCell();
        }
    }

}
