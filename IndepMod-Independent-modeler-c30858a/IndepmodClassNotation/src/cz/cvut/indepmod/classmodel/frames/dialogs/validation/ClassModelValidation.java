package cz.cvut.indepmod.classmodel.frames.dialogs.validation;

import cz.cvut.indepmod.classmodel.Globals;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;

/**
 * Date: 9.4.2011
 * Time: 9:08:57
 * @author Lucky
 */
public class ClassModelValidation extends AbstractDialogValidation {

    private static ClassModelValidation instance;

    public static ClassModelValidation getInstance() {
        if (instance == null) {
            instance = new ClassModelValidation();
        }
        return instance;
    }

    private ClassModelValidation() {
    }

    @Override
    public boolean validateClassName(String className, AbstractElementModel elModel) {
        if (!className.matches("^([A-Za-z][0-9A-Za-z_]*::)?[A-Za-z][0-9A-Za-z_]*$")) {
            String msg = Resources.getString("error_edit_element_name_validation");
            this.showErrorMessage(msg);
            return false;
        }

        ClassModelGraph graph = Globals.getInstance().getAcualGraph();
        if (!elModel.getTypeName().equals(className) && !graph.isElementNameFree(className)) {
            String msg = Resources.getString("error_edit_element_name_exists");
            this.showErrorMessage(msg);
            return false;
        }
        return true;
    }

    @Override
    public boolean validateAttributeName(String attribute) {
        if (!attribute.matches("^[A-Za-z][0-9A-Za-z_]*$")) {
            String msg = Resources.getString("error_edit_attribute_name_validation");
            this.showErrorMessage(msg);
            return false;
        }

        return true;
    }

    @Override
    public boolean validateAnnotationAttributeName(String anotName) {
        if (!anotName.matches("^[A-Za-z][0-9A-Za-z_]*$")) {
            String msg = Resources.getString("error_edit_annotation_attribute_name_validation");
            this.showErrorMessage(msg);
            return false;
        }

        return true;
    }

    @Override
    public boolean validateAnnotationName(String name) {
        if (!name.matches("^[A-Za-z][0-9A-Za-z_]*$")) {
            String msg = Resources.getString("error_edit_annotation_name_validation");
            this.showErrorMessage(msg);
            return false;
        }

        return true;
    }
}
