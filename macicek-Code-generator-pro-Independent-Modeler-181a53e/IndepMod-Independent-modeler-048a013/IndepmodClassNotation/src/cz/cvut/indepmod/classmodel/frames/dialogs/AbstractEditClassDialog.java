package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.actions.CancelEditClassDialog;
import cz.cvut.indepmod.classmodel.actions.EditClassDialogAddAnotation;
import cz.cvut.indepmod.classmodel.actions.EditClassDialogAddAttribute;
import cz.cvut.indepmod.classmodel.actions.EditClassDialogAddMethod;
import cz.cvut.indepmod.classmodel.actions.EditClassDialogRemoveAnotation;
import cz.cvut.indepmod.classmodel.actions.EditClassDialogRemoveAttribute;
import cz.cvut.indepmod.classmodel.actions.EditClassDialogRemoveMethod;
import cz.cvut.indepmod.classmodel.actions.SaveEditClassDialog;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ClassModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.MethodModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.ModelListener;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.awt.Frame;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListModel;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

/**
 * Date: 5.3.2011
 * Time: 18:13:34
 * @author Lucky
 */
public class AbstractEditClassDialog extends AbstractEditClassDialogView implements ModelListener {

    private static final int DEFAULT_WIDTH = 350;
    private static final int DEFAULT_HEIGTH = 450;

    private ClassModelGraph graph;
    private DefaultGraphCell cell;
    private ClassModel classModel;

    private DefaultListModel attributeListModel;
    private DefaultListModel methodListModel;
    private DefaultListModel anotationListModel;

    public AbstractEditClassDialog(
            Frame owner,
            ClassModelGraph graph,
            DefaultGraphCell cell,
            ClassModel classModel) {
        super(owner);

        this.graph = graph;
        this.cell = cell;
        this.classModel = classModel;

        this.attributeListModel = new DefaultListModel();
        this.methodListModel = new DefaultListModel();
        this.anotationListModel = new DefaultListModel();

        this.initValues();
        this.initAction();
        this.initHandlers();
        this.setSizes(DEFAULT_WIDTH, DEFAULT_HEIGTH);
    }


    /**
     * Returns Selected Anotation in the attribute list
     * @return selected anotation
     */
    public AnotationModel getSelectedAnotation() {
        return (AnotationModel) this.anotationList.getSelectedValue();
    }

    /**
     * Returns Selected Attribute in the attribute list
     * @return selected attribute
     */
    public AttributeModel getSelectedAttribute() {
        return (AttributeModel) this.attributeList.getSelectedValue();
    }

    /**
     * Returns selected Method in the methods list
     * @return selected method
     */
    public MethodModel getSelectedMethod() {
        return (MethodModel) this.methodList.getSelectedValue();
    }

    /**
     * Returns filled name of the class
     * @return name of the class which is filled in the dialog
     */
    public String getClassName() {
        return this.classNameField.getText();
    }

    /**
     * This method is called when there is a change in the model and thus the
     * cell's view should be updated (e.g. attribute action adds an attribute)
     */
    public void updateCell() {
        Map<Object, Object> attributes = new HashMap<Object, Object>();
        GraphConstants.setResize(attributes, true);
        this.graph.getGraphLayoutCache().editCell(this.cell, attributes);
    }

    /**
     * Returns all Types from the graph
     * @return All types which are used in the graph
     */
    public Collection<TypeModel> getAllTypeModel() {
        return this.graph.getAllTypes();
    }

    @Override
    public void dispose() {
        this.classModel.removeListener(this);
        super.dispose();
    }

    @Override
    public void modelChanged() {
        this.loadAttributeListValues();
        this.loadMethodsListValues();
        this.loadAnotationListValues();
    }

    /**
     * Initializes event handlers
     */
    private void initHandlers() {
        this.classModel.addListener(this);
    }

    /**
     * Initializes values in the dialog according to the class model
     */
    private void initValues() {
        String typeName = this.classModel.getTypeName();

        this.classNameField.setText(typeName);
        this.classNameField.setSelectionStart(0);
        this.classNameField.setSelectionEnd(typeName.length());

        this.attributeList.setModel(this.attributeListModel);
        this.methodList.setModel(this.methodListModel);
        this.anotationList.setModel(this.anotationListModel);

        this.loadAnotationListValues();
        this.loadAttributeListValues();
        this.loadMethodsListValues();
    }

    /**
     * Loads list of anotations into the anotation list which is situated in the
     * dialog
     */
    private void loadAnotationListValues() {
        Set<AnotationModel> anots = this.classModel.getAnotations();
        this.anotationListModel.clear();
        for (AnotationModel anot : anots) {
            this.anotationListModel.addElement(anot);
        }
    }

    /**
     * Loads list of attributes into the attribute list which is situated in the
     * dialog
     */
    private void loadAttributeListValues() {
        Set<AttributeModel> attributes = this.classModel.getAttributeModels();
        this.attributeListModel.clear();
        for (AttributeModel attr : attributes) {
            this.attributeListModel.addElement(attr);
        }
    }

    /**
     * Loads list of attributes into the attribute list which is situated in the
     * dialog
     */
    private void loadMethodsListValues() {
        Set<MethodModel> methods = this.classModel.getMethodModels();
        this.methodListModel.clear();
        for (MethodModel method : methods) {
            this.methodListModel.addElement(method);
        }
    }

    /**
     * Initializes actions (for saving, canceling, ...)
     */
    private void initAction() {
        //this.editAttributeButton.addActionListener(new ClassModelEditClassDialogEditAttribute(this));
        this.removeAttributeButton.addActionListener(new EditClassDialogRemoveAttribute(this.classModel, this));
        this.addAnotationButton.addActionListener(new EditClassDialogAddAnotation(classModel, this));
        this.addAttributeButton.addActionListener(new EditClassDialogAddAttribute(this.classModel, this));
        this.addMethodButton.addActionListener(new EditClassDialogAddMethod(this.classModel, this));
        this.removeAnotationButton.addActionListener(new EditClassDialogRemoveAnotation(this.classModel, this));
        this.removeMethodButton.addActionListener(new EditClassDialogRemoveMethod(this.classModel, this));
        this.saveButton.addActionListener(new SaveEditClassDialog(this.classModel, this));
        this.cancelButton.addActionListener(new CancelEditClassDialog(this));
    }
}
