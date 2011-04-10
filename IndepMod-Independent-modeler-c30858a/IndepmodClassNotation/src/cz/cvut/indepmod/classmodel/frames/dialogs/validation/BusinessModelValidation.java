package cz.cvut.indepmod.classmodel.frames.dialogs.validation;

import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;

/**
 * Date: 9.4.2011
 * Time: 9:16:34
 * @author Lucky
 */
public class BusinessModelValidation extends AbstractDialogValidation {

    private static BusinessModelValidation instance;

    public static BusinessModelValidation getInstance() {
        if (instance == null) {
            instance = new BusinessModelValidation();
        }
        return instance;
    }

    private BusinessModelValidation() {

    }

    @Override
    public boolean validateClassName(String className, AbstractElementModel elModel) {
        if (className.isEmpty()) {
            String msg = Resources.getString("error_edit_element_name_validation");
            this.showErrorMessage(msg);
            return false;
        }

        return true;
    }

    @Override
    public boolean validateAttributeName(String attribute) {
        if (attribute.isEmpty()) {
            String msg = Resources.getString("error_edit_attribute_name_validation");
            this.showErrorMessage(msg);
            return false;
        }

        return true;
    }

    @Override
    public boolean validateAnnotationAttributeName(String anotName) {
        if (anotName.isEmpty()) {
            String msg = Resources.getString("error_edit_annotation_attribute_name_validation");
            this.showErrorMessage(msg);
            return false;
        }

        return true;
    }

    @Override
    public boolean validateAnnotationName(String name) {
        if (name.isEmpty()) {
            String msg = Resources.getString("error_edit_annotation_name_validation");
            this.showErrorMessage(msg);
            return false;
        }

        return true;
    }

}
