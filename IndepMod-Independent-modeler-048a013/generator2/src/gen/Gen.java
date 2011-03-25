package gen;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import cz.cvut.indepmod.classmodel.api.model.Visibility;
import integration.OutputJavaClass;
import java.io.IOException;

/* predela Globlas na enum*/

public class Gen {

    private IClassModelModel myModel;
    private OutputJavaClass out = null;
    private String suffix = ".java";

    public Gen(IClassModelModel model) {
        System.out.println("predan model...");
        myModel = model;
    }

    public void generateModel() throws IOException {
        System.out.println("generovani...");

        for (IClass iClass : myModel.getClasses()) {
            generateClass(iClass);
        }
    }

    private void generateClass(IClass clazz) throws IOException {

        out = new OutputJavaClass(clazz.getTypeName().toString() + suffix);
        this.writeJavaVisib(clazz.getVisibility());
        out.write(clazz.getTypeName().toString());
        this.writeGeneralization(clazz);
        this.writeRealization(clazz);
        out.write(Globals.lbrace);
        out.write(Globals.nl);
        this.writeAtributes(clazz);
        this.writeComposition(clazz);
        this.writeAggregation(clazz);
        this.writeMethods(clazz);
        out.write(Globals.rbrace);
        out.close();
    }

    private void writeGeneralization(IClass clazz) throws IOException {
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
    private void writeComposition(IClass clazz) throws IOException {
        int numberOfComp = 0;
        for (IRelation relation : clazz.getRelatedClass()) {
            if (relation.getRelationType() == RelationType.COMPOSITION) {
                if (relation.getEndingClass() != clazz) {
                    numberOfComp++;
                    out.write(Globals.pri);
                    out.write(relation.getEndingClass().getTypeName());
                    out.write(relation.getEndingClass().getTypeName() + numberOfComp);
                    out.write(Globals.semic);
                    out.write(Globals.nl);
                }
            }
        }
    }

    /* agregace muze mit vic objektu, ale jen u zdrojovaho */
    private void writeAggregation(IClass clazz) throws IOException {
        int numberOfAggreg = 0;
        for (IRelation relation : clazz.getRelatedClass()) {
            if (relation.getRelationType() == RelationType.AGREGATION) {
                if (relation.getEndingClass() != clazz) {
                    numberOfAggreg++;
                    out.write(Globals.pri);
                    if (relation.getStartCardinality().getTo() == -1) {
                        out.write("Collection");
                    } else {
                        out.write(relation.getEndingClass().getTypeName());
                    }
                    out.write(relation.getEndingClass().getTypeName() + numberOfAggreg);
                    out.write(Globals.semic);
                    out.write(Globals.nl);
                }
            }
        }
    }

    /* tohle je v podstate implementace interfacu */
    private void writeRealization(IClass clazz) throws IOException {
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

    private void writeMethods(IClass clazz) throws IOException {
        for (IMethod iMethod : clazz.getMethodModels()) {
            this.writeJavaVisib(iMethod.getVisibility());
            out.write(iMethod.getType().toString());
            out.write(iMethod.getName());
            out.write("(");
            out.write(")");
            out.write(Globals.nl);
            out.write(Globals.lbrace);
            this.writeAtributes(clazz);
            out.write(Globals.rbrace);
        }

    }

    private void writeAtributes(IClass clazz) throws IOException {
        for (IAttribute attribute : clazz.getAttributeModels()) {
            for (IAnotation anot : attribute.getAnotations()) {
                out.write("@");
                out.write(anot.getName());
                out.write("(");
                for (IAnotationValue anotValue : anot.getAttributes()) {
                    out.write(anotValue.getName());
                    /* dopnit jejich hodnoty - jeste se v tom nevyznam */
                    out.write(",");
                }
                out.write(")");
                out.write(Globals.nl);
            }
            this.writeJavaVisib(attribute.getVisibility());
            out.write(attribute.getType().toString());
            out.write(attribute.getName().toString());
            out.write(Globals.semic);
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
