package cz.cvut.indepmod.classmodel.diagramdata;

import cz.cvut.indepmod.classmodel.Globals;
import cz.cvut.indepmod.classmodel.api.model.DiagramType;
import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.diagramdata.langs.Language;
import cz.cvut.indepmod.classmodel.util.ClassModelLibrary;
import cz.cvut.indepmod.classmodel.workspace.ClassModelGraphModel;
import cz.cvut.indepmod.classmodel.workspace.cell.ClassModelCellViewFactory;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.jgraph.graph.GraphLayoutCache;

/**
 * This class stores information which have to be saved. It is information
 * like which type of diagram is used (business, class), used GraphLayoutCache,
 * static data types, ...
 * @author Lucky
 */
public class DiagramDataModel {

    /**
     * Layout cache of the graph. This object storest the information about
     * graph objects
     */
    private GraphLayoutCache layoutCache;

    /**
     * The type of the diagram (class or business diagram)
     */
    private DiagramType diagramType;

    /**
     * Language definition with set of static data types (String, int, ...)
     */
    private Language language;

    /**
     * Set of dynamic data types which were added by the user. These data
     * types are not classes created by the user. This types are types that
     * are not among static data types and among created classes. It is when
     * user want to add an attribut of e.g. JTextField type and type it by hand.
     */
    private Set<IType> dynamicDataTypes;

    /**
     * Set of stereotypes
     */
    private Set<String> stereotypes;

    private int classCounter;

    private int interfaceCounter;

    private int enumerationCounter;

    public DiagramDataModel() {
        this(new GraphLayoutCache(
                new ClassModelGraphModel(),
                new ClassModelCellViewFactory()),
             DiagramType.CLASS, null);
    }

    public DiagramDataModel(GraphLayoutCache cache, DiagramType diagType, String languageName) {
        this.layoutCache = cache;
        this.diagramType = diagType;

        if (this.layoutCache == null) {
            this.layoutCache = new GraphLayoutCache(
                    new ClassModelGraphModel(),
                    new ClassModelCellViewFactory());
        }

        this.language = Globals.getInstance().getLangByName(languageName);
        this.dynamicDataTypes = new HashSet<IType>();
        this.stereotypes = new HashSet<String>();
        this.classCounter = 0;
        this.interfaceCounter = 0;
        this.enumerationCounter = 0;


        this.initDefaultStereotypes();
    }

    /**
     * Return the name of the language.
     * @return
     */
    public String getLanguageName() {
        return this.language.getLangName();
    }

    /**
     * Returns the GraphLayoutCache instance
     * @return
     */
    public GraphLayoutCache getLayoutCache() {
        return layoutCache;
    }

    /**
     * Returns the diagram type
     * @return
     */
    public DiagramType getDiagramType() {
        return diagramType;
    }

    /**
     * Returns a collection of static data types
     * @return
     */
    public Collection<IType> getStaticDataTypes() {
        return new HashSet<IType>(this.language.getStaticDataTypes());
    }

    /**
     * Returns a collection of dynamic data types
     * @return
     */
    public Collection<IType> getDynamicDataTypes() {
        return new HashSet<IType>(this.dynamicDataTypes);
    }

    /**
     * Returns a collection of all data types (static + dynamic)
     * @return
     */
    public Collection<IType> getDataTypes() {
        Collection<IType> res = ClassModelLibrary.joinTypeCollections(this.language.getStaticDataTypes(), this.dynamicDataTypes);
        return res;
    }

    /**
     * Adds new dynamic data type (in case there is not such a type yet)
     * @param type
     */
    public void addDynamicDataType(IType type) {
        if (this.language.getStaticDataTypes().contains(type)) {
            return;
        }
        this.dynamicDataTypes.add(type);
    }

    /**
     * Returns a collection of stereotypes
     * @return
     */
    public Collection<String> getStereotypes() {
        return new HashSet<String>(this.stereotypes);
    }

    /**
     * Adds a new stereotype, but only if it is not already there.
     * @param stereotype
     */
    public void addStereotype(String stereotype) {
        this.stereotypes.add(stereotype);
    }

    /**
     * Returns class number.
     * @return class number
     */
    public int getClassCounter() {
        return classCounter;
    }

    public void setClassCounter(int classCounter) {
        this.classCounter = classCounter;
    }

    /**
     * Returns enumeration counter
     * @return enumeration counter
     */
    public int getEnumerationCounter() {
        return enumerationCounter;
    }

    public void setEnumerationCounter(int enumerationCounter) {
        this.enumerationCounter = enumerationCounter;
    }

    /**
     * Returns interface counter
     * @return interface counter
     */
    public int getInterfaceCounter() {
        return interfaceCounter;
    }

    public void setInterfaceCounter(int interfaceCounter) {
        this.interfaceCounter = interfaceCounter;
    }

    //===================== PRIVATE METHODS ====================================

    private void initDefaultStereotypes() {
        this.stereotypes = new HashSet<String>();
        this.stereotypes.add(""); //Empty stereotype
        this.stereotypes.add("interface");
        this.stereotypes.add("enumeration");
    }
}