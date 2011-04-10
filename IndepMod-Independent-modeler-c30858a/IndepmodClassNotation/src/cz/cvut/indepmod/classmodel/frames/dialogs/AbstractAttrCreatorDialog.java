package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.Globals;
import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.api.model.Visibility;
import cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModel;
import cz.cvut.indepmod.classmodel.frames.dialogs.validation.AbstractDialogValidation;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AttributeModel;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultListModel;
import org.openide.windows.WindowManager;

/**
 * Date: 17.10.2010
 * Time: 14:32:19
 * @author Lucky
 */
public class AbstractAttrCreatorDialog extends AbstractAttrCreatorDialogView {

    private Collection<IType> availableTypes;
    private IAttribute returnValue;
    protected DefaultListModel anotationListModel;

    public AbstractAttrCreatorDialog(Frame owner) {
        this(owner, new ArrayList<IType>(0));
    }

    public AbstractAttrCreatorDialog(Frame owner, Collection<IType> types) {
        super(owner);

        this.availableTypes = types;
        this.returnValue = null;

        this.initComponentBehavior();
        this.initValues();
        this.initAction();
        this.setSizes();
    }

    public IAttribute getReturnValue() {
        return this.returnValue;
    }

    public void setReturnValue(IAttribute attribute) {
        this.returnValue = attribute;
    }

    public List<IAnotation> getAnotationList() {
        List<IAnotation> res = new ArrayList<IAnotation>(this.anotationListModel.getSize());

        for (int i = 0; i < this.anotationListModel.getSize(); i++) {
            res.add((IAnotation) this.anotationListModel.get(i));
        }

        return res;
    }

    public void addAnotation(IAnotation anotation) {
        this.anotationListModel.addElement(anotation);
    }

    public void removeAnotationAt(int index) {
        this.anotationListModel.remove(index);
    }

    private void initAction() {
        this.createButton.addActionListener(new CreateAttributeAction());
        this.addAnotationButton.addActionListener(new AddAnotationAction());
        this.removeAnotationButton.addActionListener(new RemoveAnotationAction());

        this.getRootPane().setDefaultButton(this.createButton);
    }

    private void initValues() {
        this.anotationListModel = new DefaultListModel();
        this.anotationList.setModel(this.anotationListModel);

        this.attributeType.removeAllItems();
        for (IType type : this.availableTypes) {
            this.attributeType.addItem(type);
        }

        this.attributeVisibility.removeAllItems();
        this.attributeVisibility.addItem(Visibility.PUBLIC);
        this.attributeVisibility.addItem(Visibility.PROTECTED);
        this.attributeVisibility.addItem(Visibility.PRIVATE);
        this.attributeVisibility.addItem(Visibility.NONE);
    }

    private void initComponentBehavior() {
        this.attributeType.setEditable(true);
    }

    //==========================================================================
    //============ INNER CLASS =================================================
    //==========================================================================
    private class CreateAttributeAction extends ClassModelAbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            DiagramDataModel diagramData = Globals.getInstance().getActualDiagramData();
            AbstractDialogValidation val = AbstractDialogValidation.getValidation(diagramData.getDiagramType());

            String name = AbstractAttrCreatorDialog.this.getAttributeName();
            Object dataTypeObj = AbstractAttrCreatorDialog.this.getSelectedAttributeType();
            Visibility visibility = AbstractAttrCreatorDialog.this.getSelectedVisibility();

            if (val.validateAttributeName(name)) {
                TypeModel dataType;
                if (dataTypeObj instanceof String) {
                    dataType = new TypeModel((String) dataTypeObj);
                    diagramData.addDynamicDataType(dataType);
                } else {
                    dataType = (TypeModel) dataTypeObj;
                }

                IAttribute attribute = new AttributeModel(dataType, name, visibility);

                for (IAnotation anot : AbstractAttrCreatorDialog.this.getAnotationList()) {
                    attribute.addAnotation(anot);
                }

                AbstractAttrCreatorDialog.this.setReturnValue(attribute);
                AbstractAttrCreatorDialog.this.dispose();
            }
        }
    }

    private class AddAnotationAction extends ClassModelAbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            Frame window = WindowManager.getDefault().getMainWindow();
            IAnotation anot = new AnotationCreatorDialog(window).getAnotation();

            if (anot != null) {
                AbstractAttrCreatorDialog.this.addAnotation(anot);
            }
        }
    }

    private class RemoveAnotationAction extends ClassModelAbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = AbstractAttrCreatorDialog.this.getSelectedAnotationIndex();
            if (index != -1) {
                AbstractAttrCreatorDialog.this.removeAnotationAt(index);
            }
        }
    }
}
