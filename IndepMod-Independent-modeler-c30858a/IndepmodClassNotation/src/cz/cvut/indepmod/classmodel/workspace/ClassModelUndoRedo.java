package cz.cvut.indepmod.classmodel.workspace;

import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import org.jgraph.JGraph;
import org.jgraph.graph.GraphUndoManager;
import org.openide.awt.UndoRedo;

/**
 * Date: 7.4.2011
 * Time: 12:40:25
 * @author Lucky
 */
public class ClassModelUndoRedo extends UndoRedo.Manager {

    private JGraph graph;
    private GraphUndoManager undoManager;

    public ClassModelUndoRedo(JGraph graph) {
        this.graph = graph;

        this.init();
    }

    @Override
    public boolean canUndo() {
        return this.undoManager.canUndo();
    }

    @Override
    public synchronized boolean canRedo() {
        return this.undoManager.canRedo();
    }

    @Override
    public synchronized void redo() throws CannotRedoException {
        this.undoManager.redo();
    }

    @Override
    public synchronized void undo() throws CannotUndoException {
        this.undoManager.undo();
    }

    @Override
    public String getRedoPresentationName() {
        return this.undoManager.getRedoPresentationName();
    }

    @Override
    public String getUndoPresentationName() {
        return this.undoManager.getUndoPresentationName();
    }

    @Override
    public synchronized boolean canUndoOrRedo() {
        return super.canUndoOrRedo();
    }

    //================== PRIVATE METHODS =======================================

    private void init() {
        this.undoManager = new GraphUndoManager();
        this.undoManager.setLimit(5);
        
        this.graph.getModel().addUndoableEditListener(this.undoManager);
    }

}
