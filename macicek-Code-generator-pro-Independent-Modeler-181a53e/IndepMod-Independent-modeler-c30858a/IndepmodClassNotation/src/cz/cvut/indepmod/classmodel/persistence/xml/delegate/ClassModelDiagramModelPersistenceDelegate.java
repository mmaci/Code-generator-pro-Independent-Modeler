package cz.cvut.indepmod.classmodel.persistence.xml.delegate;

import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModel;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.Statement;

/**
 * Date: 19.2.2011
 * Time: 12:43:47
 * @author Lucky
 */
public class ClassModelDiagramModelPersistenceDelegate extends DefaultPersistenceDelegate {

    @Override
    protected void initialize(Class<?> type, Object oldInstance, Object newInstance, Encoder out) {
        DiagramDataModel c = (DiagramDataModel)oldInstance;

        for (IType dataType : c.getDataTypes()) {
            out.writeStatement(new Statement(oldInstance, "addDynamicDataType", new Object[] {dataType}));
        }

        for (String stereotype : c.getStereotypes()) {
            out.writeStatement(new Statement(oldInstance, "addStereotype", new Object[] {stereotype}));
        }

        out.writeStatement(new Statement(oldInstance, "setClassCounter", new Object[] {c.getClassCounter()}));
        out.writeStatement(new Statement(oldInstance, "setEnumerationCounter", new Object[] {c.getEnumerationCounter()}));
        out.writeStatement(new Statement(oldInstance, "setInterfaceCounter", new Object[] {c.getInterfaceCounter()}));
    }

    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
        DiagramDataModel c = (DiagramDataModel)oldInstance;
        return new Expression(
                oldInstance,
                oldInstance.getClass(),
                "new",
                new Object[]{c.getLayoutCache(), c.getDiagramType(), c.getLanguageName()});
    }
}
