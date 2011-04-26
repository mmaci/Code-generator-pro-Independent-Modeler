package gen;

import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.IAnotationValue;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IMethod;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.ICardinality;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import cz.cvut.indepmod.classmodel.api.model.Visibility;
import integration.OutputJavaClass;
import java.util.Iterator;
import java.io.*;

/* predela Globlas na enum*/
public class Gen implements IGen{

    private IClassModelModel myModel;
    private OutputJavaClass out = null;
    private String suffix = ".java";

    public Gen(IClassModelModel model) {
        System.out.println("predan model...");
        myModel = model;
    }

    @Override
    public void generateModel(String save_path) throws IOException {
        System.out.println("generovani...");
        String filename = "test.sql"; // jmeno souboru do ktereho budem zapisovat
        FileWriter fstream = new FileWriter(filename);
        BufferedWriter output = new BufferedWriter(fstream);

        for (IElement iClass : myModel.getClasses()) {
            generateClass(iClass);
            generateSql(output, iClass);
        }
        output.close();
    }

    private void generateClass(IElement clazz) throws IOException {        
        out = new OutputJavaClass(clazz.getTypeName().toString() + suffix);
        
        this.writeAtributes(clazz);        
        this.writeJavaVisib(clazz.getVisibility());
        out.write(clazz.getTypeName().toString());
        this.writeGeneralization(clazz);
        this.writeRealization(clazz);
        out.write(Globals.lbrace);  
        this.writeAtributes(clazz);
        this.writeComposition(clazz);
        this.writeAggregation(clazz);
        this.writeMethods(clazz);
        out.write(Globals.rbrace);
        out.close();
    }
    
    /**
     * Otevreni souboru pro zapis a zacatek generovani sql
     * 
     * @param filename
     * @param c
     * @throws IOException 
     */
    private void generateSql(BufferedWriter output, IElement c) throws IOException {                
        try
        {
            writeSqlTable(output, c);
            writeSqlRelations(output, c);
        }
        catch (IOException e)
        {
            throw e; // pripadnou psani preda dal
        }        
    }
    
    /**
     * Vypis jednotlivych tabulek
     * 
     * @param output
     * @param c
     * @throws IOException 
     */
    private void writeSqlTable(BufferedWriter output, IElement c) throws IOException {
        output.write(Globals.nl);
        // ---
        
        output.write("DROP TABLE "+c.toString()+";");
        output.write(Globals.nl);
        output.write("CREATE TABLE "+c.toString()+" (");        
        output.write(Globals.nl);        
        writeSqlAttributes(output, c);        
        output.write(Globals.nl);
        output.write(");");
        
        // ---
        output.write(Globals.nl);
    }
    
        /**
        * Vypis jednotlivych atributu pro jednotlive tabulky
        * 
        * @param output
        * @param c
        * @throws IOException 
        */
        private void writeSqlAttributes(BufferedWriter output, IElement c) throws IOException {
            for (IAttribute attribute : c.getAttributeModels()) {            
                output.write(attribute.getName()+" "+attribute.getType().toString()+",");
                output.write(Globals.nl);
            }
            output.write("PRIMARY KEY (");
            output.write(")");
        }   
    
    private Cardinality filterCardinality(ICardinality cardin){
        Cardinality result = Cardinality.ERROR;
        
        if (cardin.getFrom() == 1 && cardin.getTo() == -1)
            result = Cardinality.ONE_N;
        else
        if (cardin.getFrom() == 1 && cardin.getTo() == 1)
            result = Cardinality.ONE_ONE;
        else
        if (cardin.getFrom() == 0 && cardin.getTo() == -1)
            result = Cardinality.ZERO_N;
        
        return result;
    }
    
    /**
     * Vypis sql pro jednotlive relace
     * 
     * @param output
     * @param c
     * @throws IOException 
     */
    private void writeSqlRelations(BufferedWriter output, IElement c) throws IOException {
        for (IRelation rel : c.getRelatedClass()){
            // zkraceni jednotlivych nazvu pro prehlednost
            IElement start = rel.getStartingClass();
            IElement end = rel.getEndingClass();
            RelationType type = rel.getRelationType();
            Cardinality start_rel = filterCardinality(rel.getStartCardinality());
            Cardinality end_rel = filterCardinality(rel.getEndCardinality());            
            
            /**
             * Primarni klic je treba predelat na seznam, neb kazda tabulka
             * muze byt identifikovana vice primarnimi klici.
             * 
             * Hacek je v tom, ze momentalne nema IM implementovat system, jak
             * rozlisovat primarni klice
             */
            // IAttribute pk_1;
            // IAttribute pk_2;
            
            output.write(Globals.nl);
            // ---
            switch(type)
            {               
                case RELATION:
                    // N-N relace
                    if ((start_rel == Cardinality.ZERO_N && end_rel == Cardinality.ZERO_N) ||
                        (start_rel == Cardinality.ONE_ONE && end_rel == Cardinality.ONE_ONE)  )
                    {
                        output.write("DROP TABLE "+start.toString()+ "_"+end.toString()+";");
                        output.write(Globals.nl);
                        output.write("CREATE TABLE "+start.toString()+ "_"+end.toString()+" (");
                        output.write(Globals.nl);
                        output.write(start.toString());
                        output.write("_jmeno_pk_1"+" "+"typ_pk1");
                        output.write(Globals.nl);
                        output.write(end.toString());
                        output.write("_jmeno_pk_2"+" "+"typ_pk2");
                        output.write(Globals.nl);
                        output.write(");");
                        break;
                    }
                    // 1-N relace
                    if (start_rel == Cardinality.ONE_ONE && end_rel == Cardinality.ZERO_N)
                    {
                        output.write("ALTER TABLE "+end.toString()+" ADD (");
                        output.write(start.toString());
                        output.write("_jmeno_pk_1"+" "+"typ_pk1");
                        output.write(Globals.nl);
                        output.write(");");
                    }
                    break;
                case COMPOSITION:
                    output.write("ALTER TABLE "+end.toString()+" ADD (");                       
                    output.write(Globals.nl);
                        // output.write(pk.getName()+" "+pk.getType().toString()+",");
                        output.write(Globals.nl);
                        output.write("PRIMARY KEY (");
                        // output.write(pk.getName());
                        output.write(")");
                    output.write(Globals.nl);
                    output.write(");");
                    break;                    
            }
            // ---
            output.write(Globals.nl);
        
        }
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
                    out.write(relation.getEndingClass().getTypeName() + numberOfComp);
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
                if (it.hasNext()){
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
            out.write("@"+anot.getName() + "(");
            for (Iterator it = anot.getAttributes().iterator(); it.hasNext();) {
                IAnotationValue anotValue = (IAnotationValue) it.next();
                out.write(anotValue.getName());
                out.write("=\"");
                for (Iterator it1 = anotValue.getValues().iterator(); it1.hasNext();) {
                    String value =  (String) it1.next();
                    out.write(value);
                    if (it1.hasNext()){
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
