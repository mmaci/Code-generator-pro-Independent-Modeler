package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.Frame;
import org.jgraph.graph.DefaultGraphCell;

/**
 * Date: 5.3.2011
 * Time: 14:32:31
 * @author Lucky
 */
public class ClassModelEditClassDialog extends AbstractEditClassDialog {

    public ClassModelEditClassDialog(
            Frame owner,
            ClassModelGraph graph,
            DefaultGraphCell cell,
            ClassModel classModel) {
        super(owner, graph, cell, classModel);
    }

    
}
