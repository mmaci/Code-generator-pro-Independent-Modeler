package cz.cvut.indepmod.classmodel;

import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.diagramdata.DiagramDataModel;
import cz.cvut.indepmod.classmodel.diagramdata.langs.Language;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraph;
import cz.cvut.indepmod.classmodel.workspace.cell.model.classModel.TypeModel;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.openide.util.Utilities;

/**
 * Date: 20.3.2011
 * Time: 9:01:24
 * @author Lucky
 */
public class Globals {
    
    private static Globals instance;

    private Set<Language> langs;

    private Globals() {
        this.initLangs();
    }

    public static Globals getInstance() {
        if (instance == null) {
            instance = new Globals();
        }
        return instance;
    }

    /**
     * Returns the collection of existing language names
     * @return the collection of existing language names
     */
    public Collection<String> getLanguageNames() {
        Set<String> langNames = new HashSet<String>();
        for (Language l : this.langs) {
            langNames.add(l.getLangName());
        }
        return langNames;
    }

    /**
     * Returns the language according to its name
     * @param langName the name of the language
     * @return instance of Language or null if there is no such a language
     */
    public Language getLangByName(String langName) {
        if (langName == null) {
            return this.langs.iterator().next();
        }

        for (Language l : this.langs) {
            if (l.getLangName().equals(langName)) {
                return l;
            }
        }
        return null;
    }

    /**
     * Returns the DiagramDataModel instance of active workspace. This instance
     * is gathered from the lookup of active workspace.
     * @return Actual instance of DiagramDataModel
     */
    public DiagramDataModel getActualDiagramData() {
        DiagramDataModel res = Utilities.actionsGlobalContext().lookup(DiagramDataModel.class);
        return res;
    }

    public ClassModelGraph getAcualGraph() {
        ClassModelGraph res = Utilities.actionsGlobalContext().lookup(ClassModelGraph.class);
        return res;
    }


    //=====================PRIVATE METHODS======================================

    private void initLangs() {
        this.langs = new HashSet<Language>();

        Set<IType> javaTypes = new HashSet<IType>();
        javaTypes.add(new TypeModel("Object"));
        javaTypes.add(new TypeModel("String"));
        javaTypes.add(new TypeModel("int"));
        javaTypes.add(new TypeModel("char"));
        javaTypes.add(new TypeModel("boolean"));
        javaTypes.add(new TypeModel("long"));
        javaTypes.add(new TypeModel("double"));
        javaTypes.add(new TypeModel("float"));
        javaTypes.add(new TypeModel("void"));
        javaTypes.add(new TypeModel(""));
        this.langs.add(new Language("Java", javaTypes));
    }

}
