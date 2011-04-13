/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen;

import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Logger;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.Utilities;

public final class Generator implements ActionListener {

    private static final Logger LOG = Logger.getLogger(ClassModel.class.getName());

    @Override
    public void actionPerformed(ActionEvent e) {
        GeneratorDialog panel = new GeneratorDialog();
        NotifyDescriptor nd = new NotifyDescriptor(panel, "Code Generator", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.QUESTION_MESSAGE, null, NotifyDescriptor.OK_OPTION);
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {

           
            IClassModelModel classModel = Utilities.actionsGlobalContext().lookup(IClassModelModel.class);

            String save_path = panel.getSave_path();
            String format = (String) panel.getSelectedItem();

            if (classModel == null) {
                //Oznam uzivateli, ze neni v zadnem class modelu
                System.out.println("Nemate otevren class model");
            } else {
                //Zacni generovat
                System.out.println("Otevreny classs model ma: " + classModel.getClasses().size() + " trid.");
                try {

                    if (format.compareTo("SQL tables") == 0) {
                        IGen gen = new GenSQL(classModel);
                        gen.generateModel(save_path);
                    } else if (format.compareTo("Java classes") == 0) {
                        IGen gen = new GenJavaClass(classModel);
                        gen.generateModel(save_path);
                    }
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }
}
