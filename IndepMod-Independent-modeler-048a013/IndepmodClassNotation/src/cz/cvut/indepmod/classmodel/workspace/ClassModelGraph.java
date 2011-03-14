package cz.cvut.indepmod.classmodel.workspace;

import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.actions.DeleteAction;
import cz.cvut.indepmod.classmodel.actions.EditAction;
import cz.cvut.indepmod.classmodel.api.ToolChooserModel;
import cz.cvut.indepmod.classmodel.api.ToolChooserModelListener;
import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModel;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelCellFactory;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.DefaultGraphCell;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Logger;
import org.jgraph.graph.CellView;

public class ClassModelGraph extends JGraph {

    private static final Logger LOG = Logger.getLogger(ClassModelGraph.class.getName());

    private Map<Class<? extends ClassModelAbstractAction>, ClassModelAbstractAction> actions;
    private ToolChooserModel selectedTool;
    private DiagramDataModel diagramData;


    public ClassModelGraph(
            Map<Class<? extends ClassModelAbstractAction>, ClassModelAbstractAction> actions,
            ToolChooserModel selectedTool,
            DiagramDataModel diagramData) {
        super(diagramData.getLayoutCache());
        
        this.actions = actions;
        this.selectedTool = selectedTool;
        this.diagramData = diagramData;

        this.initActions();
        this.initEventHandling();
        this.setDoubleBuffered(true);
    }

    public Collection<TypeModel> getAllTypes() {
        Collection<TypeModel> res = new LinkedList<TypeModel>(this.getAllClasses());

        res.addAll(this.diagramData.getStaticDataTypes());
        return res;
    }

    /**
     * Returns collection of all classes that are in the Graph
     * @return Colection of all classes
     */
    public Collection<ClassModel> getAllClasses() {
        Collection<ClassModel> res = new LinkedList<ClassModel>();
        CellView[] cw = this.getGraphLayoutCache().getCellViews();
        for (int i = 0; i < cw.length; i++) {
            DefaultGraphCell cell = (DefaultGraphCell)cw[i].getCell();
            Object userObject = cell.getUserObject();
            if (userObject instanceof ClassModel) {
                res.add((ClassModel) userObject);
            }
        }
        return res;
    }

    public DiagramType getDiagramType() {
        return this.diagramData.getDiagramType();
    }

    public void insertCell(Point p) {
        LOG.fine("adding new cell");
        ToolChooserModel.Tool tool = this.selectedTool.getSelectedTool();
        DefaultGraphCell cell = ClassModelCellFactory.createCell(p, tool);

        this.getGraphLayoutCache().insert(cell);
        this.selectedTool.setSelectedTool(ToolChooserModel.Tool.TOOL_CONTROLL);
    }

    public void selectCell(Object cell) {
        for (Object selection : this.getSelectionCells()) {
            this.removeSelectionCell(selection);
        }

        if (cell != null) {
            this.setSelectionCell(cell);
        }
    }

    private void initActions() {
        this.actions.put(
                EditAction.class,
                new EditAction(this)
        );

        this.actions.put(
                DeleteAction.class,
                new DeleteAction(this));
    }


    private void initEventHandling() {
        this.selectedTool.addListener(new ToolChooserModelListener() {
            @Override
            public void selectedToolChanged(ToolChooserModel.Tool newTool) {
                boolean showPorts = false;
                ToolChooserModel.Tool tool = newTool;

                switch (tool) {
                    case TOOL_ADD_RELATION:
                    case TOOL_ADD_GENERALIZATION:
                    case TOOL_ADD_REALISATION:
                    case TOOL_ADD_COMPOSITION:
                    case TOOL_ADD_AGREGATION:
                        showPorts = true;
                }

                setPortsVisible(showPorts);
                setJumpToDefaultPort(showPorts);
            }
        });

        this.addGraphSelectionListener(new GraphSelectionListener() {
            @Override
            public void valueChanged(GraphSelectionEvent graphSelectionEvent) {
                actions.get(EditAction.class).setEnabled(getSelectionCell() != null);
            }
        });
    }

}
