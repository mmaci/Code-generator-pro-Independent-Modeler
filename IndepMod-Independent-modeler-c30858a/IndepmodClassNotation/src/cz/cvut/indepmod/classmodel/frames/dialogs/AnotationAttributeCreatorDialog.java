package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.actions.ClassModelAbstractAction;
import cz.cvut.indepmod.classmodel.frames.dialogs.validation.AbstractDialogValidation;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.AnotationAttributeModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;

/**
 * Date: 4.12.2010
 * Time: 17:33:28
 * @author Lucky
 */
public class AnotationAttributeCreatorDialog extends AnotationAttributeCreatorDialogView {

    private AnotationAttributeModel returnValue;

    public AnotationAttributeCreatorDialog(Frame owner) {
        super(owner);

        this.returnValue = null;
        this.initAction();
        this.setSizes();
    }

    public AnotationAttributeModel getReturnValue() {
        return returnValue;
    }

    private void initAction() {
        this.createButton.addActionListener(new CreateAction());
        this.addValueButton.addActionListener(new AddValueAction());
        this.removeValueButton.addActionListener(new RemoveValueAction());

        this.getRootPane().setDefaultButton(createButton);
    }

    //==========================================================================
    //======================== INNER CLASS =====================================
    //==========================================================================
    private class CreateAction extends ClassModelAbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractDialogValidation val = AbstractDialogValidation.getValidation();
            String name = anotAtrName.getText();

            if (val.validateAnnotationAttributeName(name)) {
                returnValue = new AnotationAttributeModel(name);

                Object[] objs = valueListModel.toArray();
                for (int i = 0; i < objs.length; i++) {
                    returnValue.addValue((String) objs[i]);
                }

                dispose();
            }
        }
    }

    private class AddValueAction extends ClassModelAbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractDialogValidation val = AbstractDialogValidation.getValidation();
            String name = valueName.getText();

            if (val.validateAnnotationAttributeValue(name)) {
                valueListModel.addElement(name);
                valueName.setText("");
                valueName.requestFocus();
            }
        }
    }

    private class RemoveValueAction extends ClassModelAbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = valueList.getSelectedIndex();
            if (index != -1) {
                valueListModel.remove(index);
            }
        }
    }
}
