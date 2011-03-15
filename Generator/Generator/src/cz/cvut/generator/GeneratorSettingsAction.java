/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.generator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.loaders.DataObject;

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
id = "cz.cvut.generator.GeneratorSettingsAction")
@ActionRegistration(displayName = "#CTL_GeneratorSettingsAction")
@ActionReferences({
    @ActionReference(path = "Loaders/text/IndepModClassNotation/Actions", position = 150, separatorBefore = 125, separatorAfter = 175)
})
@Messages("CTL_GeneratorSettingsAction=Generate")
public final class GeneratorSettingsAction implements ActionListener {

    private final DataObject context;

    public GeneratorSettingsAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
       // DialogDescriptor dd = new DialogDescriptor(ev, null).
         NotifyDescriptor nd = new NotifyDescriptor.Message("Hello Context menu");
         DialogDisplayer.getDefault().notify(nd);
    }
}