package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import java.awt.Frame;
import javax.swing.JPanel;
import org.jgraph.graph.DefaultGraphCell;

/**
 * Date: 5.3.2011
 * Time: 14:33:26
 * @author Lucky
 */
public class BusinessModelEditClassDialog extends AbstractEditClassDialog {

    public BusinessModelEditClassDialog(
            Frame owner,
            ClassModelGraph graph,
            DefaultGraphCell cell,
            ClassModel classModel) {
        super(owner, graph, cell, classModel);
    }

    @Override
    protected JPanel initAnotationPanel() {
        return null;
    }

    @Override
    protected JPanel initMethodPanel() {
        return null;
    }

}
