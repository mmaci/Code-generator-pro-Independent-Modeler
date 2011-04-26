package gen;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


public class GenXML implements IGen {
    
    private IClassModelModel myModel;
    private String suffix = ".xml";
    private String filename = null;
    private FileWriter writer = null;
    private BufferedWriter output = null; 
    private int tabs = 0;
    
    GenXML(IClassModelModel classModel) {
        System.out.println("predan model...");
        myModel = classModel;
    }

    @Override
    public void generateModel(String save_path) throws IOException {
        filename = myModel.getClass().getName();
        writer = new FileWriter(save_path + File.separator + filename + suffix);
        output = new BufferedWriter(writer);
        write("<ELEMENTS>");
        tabs++;
        for(IElement element: myModel.getClasses()){
            writeElement(element); 
        }
        tabs--;
        write("</ELEMENTS>");
        output.close();
        writer.close();
    }
    
    private void writeElement(IElement element) throws IOException {
        write("<ELEMENT name=\"" + element.getTypeName() + "\" " +
                "type=\"" + element.getElementType().name() + "\" " + 
                "visibility=\"" + element.getVisibility().name() + "\" " +
                "abstract=\"" + element.isAbstract() + "\">");
        tabs++;
        writeRelations(element.getRelatedClass());
        writeAnotations(element.getAnotations());
        writeAttributes(element.getAttributeModels());
        writeMethods(element.getMethodModels());
        tabs--;
        write("</ELEMENT>");
    } 
    
    private void writeRelations(Collection<IRelation> relatedClass) throws IOException {
        write("<RELATIONS>");
        tabs++;
        for (IRelation relation : relatedClass) {
            write("<RELATION name=\"" + relation.getRelationName() + "\" " +
                    "from=\"" + relation.getStartingClass().getTypeName() + "\" " +
                    "to=\"" + relation.getEndingClass().getTypeName() + "\" " +
                    "type=\"" + relation.getRelationType() + "\" " +
                    "start=\"" + relation.getStartCardinality() + "\" " +
                    "end=\"" + relation.getEndCardinality() + "\"/>");
        }
        tabs--;
        write("</RELATIONS>");
    }
    
    private void writeAnotations(Set<IAnotation> anotations) throws IOException{
        write("<ANOTATIONS>");
        tabs++;
        IAnotation anotation;
        for (Iterator it = anotations.iterator(); it.hasNext();) {
            anotation = (IAnotation) it.next();
            write("<ANOTATION name=\"" + anotation.getName() + "\">");
            tabs++;
            writeAnotaionValues(anotation.getAttributes());
            tabs--;
            write("</ANOTATION>");
        }
        tabs--;
        write("</ANOTATIONS>");
    }  
    
    private void writeAnotations(Collection<IAnotation> anotations) throws IOException {
        IAnotation anotation;
        for (Iterator it = anotations.iterator(); it.hasNext();) {
            anotation = (IAnotation) it.next();
            write("<ANOTATION name=\"" + anotation.getName() + "\">");
            tabs++;
            writeAnotaionValues(anotation.getAttributes());
            tabs--;
            write("</ANOTATION>");
        };
    }
    
    private void writeAnotaionValues(Collection<IAnotationValue> attributes) throws IOException {
        IAnotationValue value;
        String radek = null;
        for (Iterator it = attributes.iterator(); it.hasNext();) {
            value = (IAnotationValue) it.next();
            radek = "<ATTRIBUTE name=\"" + value.getName() + "\" value=\"{";
            for(String val :value.getValues()){
                radek += val + ", "; 
            }
            radek = radek.substring(0, radek.length()-2) + "}\"/>";
            write(radek);
        }
    }
    
    private void writeAttributes(Set<IAttribute> attributeModels) throws IOException {
        write("<ATTRIBUTES>");
        tabs++;
        IAttribute attr;
        for (Iterator it = attributeModels.iterator(); it.hasNext();) {
            attr = (IAttribute) it.next();
            writeAnotations(attr.getAnotations());
            tabs++;
            writeAttribute(attr);
            tabs--;
        }
        tabs--;
        write("</ATTRIBUTES>");
    }
    
    private void writeAttribute(IAttribute attr) throws IOException{
        tabs++;
        write("<ATTRIBUTE name=\"" + attr.getName() + "\" " +
                "type=\"" + attr.getType().getTypeName() + "\" " +
                "visibility=\"" + attr.getVisibility().name() + "\"/>");
        tabs--;                
    }
    
    private void writeMethods(Set<IMethod> methodModels) throws IOException {
        write("<METHODS>");
        tabs++;
        IMethod attr;
        for (Iterator it = methodModels.iterator(); it.hasNext();) {
            attr = (IMethod) it.next();
            writeMethod(attr);
        }
        tabs--;
        write("</METHODS>");
    }
    
    private void writeMethod(IMethod attr) throws IOException {
        write("<METHOD name=\"" + attr.getName() + "\" " +
                "type=\"" + attr.getType() + "\" " +
                "visibility=\"" + attr.getVisibility().name() + "\">");
        tabs++;
        writeAttributes(attr.getAttributeModels());
        tabs--;
        write("</METHOD>");
    }
    
    private void write(String radek) throws IOException {
        writeTabs();
        output.write(radek + "\n");
    }
    
    private void writeTabs() throws IOException{
        for (int i = 0; i < tabs; i++) {
            output.write("\t");
        }
    }
}
