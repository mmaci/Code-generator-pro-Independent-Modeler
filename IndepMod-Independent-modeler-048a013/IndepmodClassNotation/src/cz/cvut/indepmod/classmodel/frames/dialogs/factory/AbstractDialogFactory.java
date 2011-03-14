package cz.cvut.indepmod.classmodel.frames.dialogs.factory;

import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.frames.dialogs.AbstractEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import org.jgraph.graph.DefaultGraphCell;

/**
 * Date: 5.3.2011
 * Time: 10:30:14
 * @author Lucky
 */
public abstract class AbstractDialogFactory {

    private static AbstractDialogFactory classModelDialogFactory = null;
    private static AbstractDialogFactory businessModelDialogFactory = null;

    public static AbstractDialogFactory getFactory(DiagramType diagramType) {
        switch (diagramType) {
            case CLASS:
                if (classModelDialogFactory == null) {
                    classModelDialogFactory = new ClassModelDialogFactory();
                }
                return classModelDialogFactory;
            case BUSINESS:
                if (businessModelDialogFactory == null) {
                    businessModelDialogFactory = new BusinessModelDialogFactory();
                }
                return businessModelDialogFactory;
            default:
                return null; //This won't happen
        }
    }

    public abstract AbstractEditClassDialog createEditClassDialog(
            ClassModelGraph graph,
            DefaultGraphCell cell, 
            ClassModel model);
}
