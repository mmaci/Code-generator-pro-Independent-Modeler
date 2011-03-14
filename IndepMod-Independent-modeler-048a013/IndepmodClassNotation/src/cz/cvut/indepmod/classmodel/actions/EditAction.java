package cz.cvut.indepmod.classmodel.actions;

import cz.cvut.indepmod.classmodel.frames.dialogs.EditRelationDialog;
import cz.cvut.indepmod.classmodel.frames.dialogs.factory.AbstractDialogFactory;
import cz.cvut.indepmod.classmodel.resources.Resources;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelClassCell;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelRelation;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.RelationModel;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;
import org.openide.windows.WindowManager;

/**
 * Created by IntelliJ IDEA.
 * User: Lucky
 * Date: 3.10.2010
 * Time: 19:16:01
 * <p/>
 * This action will be used for editing of the class or relation vertices
 */
public class EditAction extends ClassModelAbstractAction {

    public static final String ACTION_NAME = Resources.getString("action_edit_name");
    private static final Logger LOG = Logger.getLogger(EditAction.class.getName());
    private ClassModelGraph graph;

    public EditAction(ClassModelGraph graph) {
        super(ACTION_NAME, null);
        this.graph = graph;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object o = this.graph.getSelectionCell();
        if (o == null) {
            LOG.severe("Edit Action was performed even is no cell was selected!");
        } else if (o instanceof ClassModelClassCell) {
            this.classEditAction((ClassModelClassCell) o);
        } else if (o instanceof ClassModelRelation) {
            ClassModelRelation edge = (ClassModelRelation) o;
            Object userObj = edge.getUserObject();
            if (userObj instanceof RelationModel) {
                this.relationEditAction((ClassModelRelation) o);
            }
        } else {
            LOG.severe("Edit Action was performed on different vertex than class");
        }
    }

    private void classEditAction(ClassModelClassCell cell) {
        LOG.info("edit of Class");
        try {
            ClassModel model = (ClassModel) cell.getUserObject();
            AbstractDialogFactory factory = AbstractDialogFactory.getFactory(
                    graph.getDiagramType());
            
            factory.createEditClassDialog(graph, cell, model);
        } catch (ClassCastException ex) {
            throw new ClassCastException("User Object of cell is not ClassModel instance!");
        }
    }

    private void relationEditAction(ClassModelRelation relation) {
        LOG.info("edit of Relation");
        try {
            RelationModel model = (RelationModel) relation.getUserObject();
            EditRelationDialog dialog = new EditRelationDialog(
                    WindowManager.getDefault().getMainWindow(),
                    graph,
                    relation,
                    model);
        } catch (ClassCastException ex) {
            throw new ClassCastException("User Object of cell is not RelationModel instance!");
        }
    }
}
