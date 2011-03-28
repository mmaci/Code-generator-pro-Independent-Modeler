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
import org.openide.util.Exceptions;
import org.openide.util.Utilities;

public final class Generator implements ActionListener {
    private static final Logger LOG = Logger.getLogger(ClassModel.class.getName());

    @Override
    public void actionPerformed(ActionEvent e) {
        IClassModelModel classModel = Utilities.actionsGlobalContext().lookup(IClassModelModel.class);
        if (classModel == null) {
            //Oznam uzivateli, ze neni v zadnem class modelu
            System.out.println("Nemate otevren class model");
        } else {
            //Zacni generovat
            System.out.println("Otevreny classs model ma: "+ classModel.getClasses().size() +" trid.");
            GenJavaClass gen = new GenJavaClass(classModel);
            GenSQL genSql = new GenSQL(classModel);
            try {
                gen.generateModel();
                genSql.generateModel();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
            
        }
    }
}
