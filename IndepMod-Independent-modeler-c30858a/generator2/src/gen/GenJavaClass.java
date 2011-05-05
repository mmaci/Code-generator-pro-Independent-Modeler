package gen;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import cz.cvut.indepmod.classmodel.api.model.Visibility;
import integration.OutputJavaClass;
import java.util.Iterator;
import java.io.*;

/* predela Globlas na enum*/
public class GenJavaClass implements IGen{

    private IClassModelModel myModel;
    private OutputJavaClass out = null;
    private String suffix = ".java";

    public GenJavaClass(IClassModelModel model) {
        System.out.println("predan model...");
        myModel = model;
    }

    @Override
    public void generateModel(String save_path) throws IOException {
        for (IElement iClass : myModel.getClasses()) {
            if (save_path == null){
                save_path = ".";
            }
            generateClass(iClass, save_path);
        }

    }

    private void generateClass(IElement clazz, String save_path) throws IOException {
        out = new OutputJavaClass(save_path + File.separator + clazz.getTypeName().toString() + suffix);

        this.writeJavaVisib(clazz.getVisibility());
        out.write(clazz.getTypeName().toString());
        this.writeGeneralization(clazz);
        this.writeRealization(clazz);
        out.write(Globals.lbrace);
        this.writeAtributes(clazz);
        this.writeComposition(clazz);
        this.writeAggregation(clazz);
        this.writeRelation(clazz);
        this.writeMethods(clazz);
        out.write(Globals.rbrace);
        out.close();
    }

    private void writeGeneralization(IElement clazz) throws IOException {
        for (IRelation relation : clazz.getRelatedClass()) {
            if (relation.getRelationType() == RelationType.GENERALIZATION) {
                if (relation.getEndingClass() != clazz) {
                    out.write("extends");
                    out.write(relation.getEndingClass().getTypeName().toString());
                    break;
                }
            }
        }
    }

    /* kompozice muze mit jenom jeden objekt - predelat */
    private void writeComposition(IElement clazz) throws IOException {
        int numberOfComp = 0;
        for (IRelation relation : clazz.getRelatedClass()) {
            if (relation.getRelationType() == RelationType.COMPOSITION) {
                if (relation.getEndingClass() != clazz) {
                    numberOfComp++;
                    out.write(Globals.pri);
                    out.write(relation.getEndingClass().getTypeName());
                    out.write(relation.getEndingClass().getTypeName().toLowerCase() + numberOfComp);
                    out.write(Globals.semic);
                    out.write(Globals.nl);
                }
            }
        }
    }

    /* agregace muze mit vic objektu, ale jen u zdrojovaho */
    private void writeAggregation(IElement clazz) throws IOException {
        int numberOfAggreg = 0;
        for (IRelation relation : clazz.getRelatedClass()) {
            if (relation.getRelationType() == RelationType.AGREGATION) {
                if (relation.getEndingClass() != clazz) {
                    numberOfAggreg++;
                    out.write(Globals.pri);
                    if (relation.getStartCardinality().getTo() == -1) {
                        out.write("List" + "<"+relation.getEndingClass().getTypeName()+">");
                    } else {
                        out.write(relation.getEndingClass().getTypeName());
                    }
                    out.write(relation.getEndingClass().getTypeName().toLowerCase() + numberOfAggreg);
                    out.write(Globals.semic);
                    out.write(Globals.nl);
                }
            }
        }
    }

    /* tohle je v podstate implementace interfacu */
    private void writeRealization(IElement clazz) throws IOException {
        int numberOfInterfaces = 0;

        for (IRelation relation : clazz.getRelatedClass()) {
            if (relation.getRelationType() == RelationType.REALISATION) {
                if (relation.getEndingClass() != clazz) {
                    numberOfInterfaces++;
                }
            }
        }

        int i = 0;
        for (IRelation relation : clazz.getRelatedClass()) {
            if (relation.getRelationType() == RelationType.REALISATION) {
                if (relation.getEndingClass() != clazz) {
                    i++;
                    out.write("implements");
                    out.write(relation.getEndingClass().getTypeName().toString());
                    if (i < numberOfInterfaces) {
                        out.write(",");
                    }
                }
            }
        }
    }

    private void writeRelation(IElement clazz) throws IOException {
        int numberOfRelEndClass = 0;
        int numberOfRelStartClass = 0;
        for (IRelation relation : clazz.getRelatedClass()) {
            if (relation.getRelationType() == RelationType.RELATION) {
                if (relation.getEndingClass() != clazz) {
                    numberOfRelEndClass++;
                    out.write(Globals.pri);
                    if (relation.getEndCardinality().getTo() == -1) {
                        out.write("List" + "<"+relation.getEndingClass().getTypeName()+">");
                    } else {
                        out.write(relation.getEndingClass().getTypeName());
                    }
                    out.write(relation.getEndingClass().getTypeName().toLowerCase() + numberOfRelEndClass);
                    out.write(Globals.semic);
                    out.write(Globals.nl);
                }
                if (relation.getStartingClass() != clazz) {
                    numberOfRelStartClass++;
                    out.write(Globals.pri);
                    if (relation.getStartCardinality().getTo() == -1) {
                        out.write("List" + "<"+relation.getEndingClass().getTypeName()+">");
                    } else {
                        out.write(relation.getStartingClass().getTypeName());
                    }
                    out.write(relation.getStartingClass().getTypeName().toLowerCase() + numberOfRelStartClass);
                    out.write(Globals.semic);
                    out.write(Globals.nl);
                }
            }
        }
    } 
    
    private void writeMethods(IElement clazz) throws IOException {
        for (IMethod iMethod : clazz.getMethodModels()) {
            this.writeJavaVisib(iMethod.getVisibility());
            out.write(iMethod.getType().toString());
            out.write(iMethod.getName());
            out.write("(");

            for (Iterator it = iMethod.getAttributeModels().iterator(); it.hasNext();) {
                IAttribute attrib = (IAttribute) it.next();
                out.write(attrib.getType().getTypeName());
                out.write(attrib.getName());
                if (it.hasNext()) {
                    out.write(Globals.colon);
                }
            }

            out.write(")");
            out.write(Globals.lbrace);
            this.writeAtributes(clazz);
            out.write(Globals.rbrace);
        }
    }

    private void writeAtributes(IElement clazz) throws IOException {
        for (IAttribute attribute : clazz.getAttributeModels()) {
            this.writeAnotations(attribute);
            this.writeJavaVisib(attribute.getVisibility());
            out.write(attribute.getType().toString());
            out.write(attribute.getName().toString());
            out.write(Globals.semic);
            out.write(Globals.nl);
        }
    }

    private void writeAnotations(IAttribute attribute) throws IOException {
        System.out.println(attribute.getAnotations().size());
        for (IAnotation anot : attribute.getAnotations()) {
            out.write("@" + anot.getName() + "(");
            for (Iterator it = anot.getAttributes().iterator(); it.hasNext();) {
                IAnotationValue anotValue = (IAnotationValue) it.next();
                out.write(anotValue.getName());
                out.write("=\"");
                for (Iterator it1 = anotValue.getValues().iterator(); it1.hasNext();) {
                    String value = (String) it1.next();
                    out.write(value);
                    if (it1.hasNext()) {
                        out.write(Globals.colon);
                    }
                }
                out.write("\"");
                if (it.hasNext()) {
                    out.write(Globals.colon);
                }
            }
            out.write(")");
            out.write(Globals.nl);
        }
    }

    private void writeJavaVisib(Visibility UMLVis) throws IOException {
        if (UMLVis == Visibility.PUBLIC) {
            out.write(Globals.pub);
        } else if (UMLVis == Visibility.PRIVATE) {
            out.write(Globals.pri);
        } else if (UMLVis == Visibility.PROTECTED) {
            out.write(Globals.pro);
        } else if (UMLVis == Visibility.NONE) {
        } else {
            Exception exception = new Exception("Error in visibility class");
        }
    }
}
