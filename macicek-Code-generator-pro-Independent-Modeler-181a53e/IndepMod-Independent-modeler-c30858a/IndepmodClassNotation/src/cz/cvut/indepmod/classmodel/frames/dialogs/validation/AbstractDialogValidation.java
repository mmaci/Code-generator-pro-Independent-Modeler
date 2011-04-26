package cz.cvut.indepmod.classmodel.frames.dialogs.validation;

import cz.cvut.indepmod.classmodel.Globals;
import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AbstractElementModel;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 * Date: 9.4.2011
 * Time: 9:04:41
 * @author Lucky
 */
public abstract class AbstractDialogValidation {

    private static ClassModelValidation cmValidation = ClassModelValidation.getInstance();
    private static BusinessModelValidation bmValidation = BusinessModelValidation.getInstance();

    public static AbstractDialogValidation getValidation() {
        DiagramType type = Globals.getInstance().getActualDiagramData().getDiagramType();
        return getValidation(type);
    }

    public static AbstractDialogValidation getValidation(DiagramType diagramType) {
        switch (diagramType) {
            case CLASS:
                return cmValidation;
            case BUSINESS:
                return bmValidation;
            default:
                throw new IllegalArgumentException("Unknown diagram type!");
        }
    }
    
    //==========================================================================
    //==========================================================================
    //==========================================================================

    public abstract boolean validateClassName(String className, AbstractElementModel elModel);

    public abstract boolean validateAttributeName(String attributeName);

    public abstract boolean validateAnnotationAttributeName(String anotName);

    public abstract boolean validateAnnotationName(String name);

    //==========================================================================
    //==========================================================================
    //==========================================================================

    public boolean validateAnnotationAttributeValue(String value) {
        if (value.isEmpty()) {
            String msg = Resources.getString("error_edit_annotation_attribute_value_validation");
            this.showErrorMessage(msg);
            return false;
        }
        return true;
    }

    //==========================================================================
    //==========================================================================
    //==========================================================================

    protected void showErrorMessage(String msg) {
        NotifyDescriptor nd = new NotifyDescriptor.Message(
                msg,
                NotifyDescriptor.WARNING_MESSAGE);
        DialogDisplayer.getDefault().notify(nd);
    }
}
