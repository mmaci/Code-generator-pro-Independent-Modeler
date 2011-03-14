package gen;

import cz.cvut.indepmod.classmodel.api.*;
import cz.cvut.indepmod.classmodel.api.model.*;
import cz.cvut.indepmod.classmodel.*;
import java.util.Collection;
import java.util.Iterator;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Item;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import cz.cvut.indepmod.classmodel.*;

public class MapClass {

    private IClassModelModel classmodel = null;
    private DiagramType dt = null;

    public MapClass() {
        classmodel = Lookup.getDefault().lookup(IClassModelModel.class);
        System.out.println(classmodel.toString());
    }

    public void generate() {
        if (classmodel != null) {
            dt = classmodel.getDiagramType();
        } else {
            Exception e = new Exception("object classmodel == null");
            e.printStackTrace();
        }
    }
}
