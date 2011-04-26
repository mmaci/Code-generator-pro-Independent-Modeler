/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gen;

import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.ICardinality;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author radek
 */
public class GenSQL implements IGen{

    private IClassModelModel myModel;

    public GenSQL(IClassModelModel model) {
        System.out.println("predan model...");
        myModel = model;
    }

    @Override
    public void generateModel(String save_path) throws IOException {
        System.out.println("generovani...");
        String filename = "generated_db_schema.sql"; // jmeno souboru do ktereho budem zapisovat
        FileWriter fstream = new FileWriter(save_path + File.separator + filename);
        BufferedWriter output = new BufferedWriter(fstream);

        for (IElement iClass : myModel.getClasses()) {
            generateSql(output, iClass);
        }
        output.close();
    }


    /**
     * Otevreni souboru pro zapis a zacatek generovani sql
     *
     * @param filename
     * @param c
     * @throws IOException
     */
    private void generateSql(BufferedWriter output, IElement c) throws IOException {
        try {        
            writeSqlTable(output, c);
            writeSqlRelations(output, c);
        } catch (IOException e) {
            throw e; // pripadnou psani preda dal
        }
    }
    
    /**
     * pokud se jedna o primarni klic, vrati true, jinak false
     * 
     * @param input
     * @return 
     */
    public boolean isPrimaryKey(String input)
    {                
        if (input.startsWith("pk_"))
            return true;
        return false;           
    }
    
    /**
     * Vytahne z tridy primarni klice, ktere jsou identifikovany prefixem pk_
     * 
     * @param c 
     */
    public List<IAttribute> getPrimaryKeys(IElement c)
    {
        List<IAttribute> PrimaryKeys = new LinkedList<IAttribute>();
        for (IAttribute attribute : c.getAttributeModels()) {
            if (this.isPrimaryKey(attribute.getName()))
                PrimaryKeys.add(attribute);
        }
        return PrimaryKeys;
    }
    
    /**
     * VYpise jmena primarnich klicu ve tvaru jmeno1, jmeno2, jmeno3, ...
     * 
     * @param output
     * @param c
     * @throws IOException 
     */
    public void writePrimaryKeys(BufferedWriter output, IElement c) throws IOException
    {
        List<IAttribute> PrimaryKeys = getPrimaryKeys(c);
        for (Iterator<IAttribute> it = PrimaryKeys.iterator(); it.hasNext();) 
        {
            IAttribute iAttribute = it.next();
            output.write(iAttribute.getName());
            if (it.hasNext())
                output.write(", ");                                
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

        output.write("DROP TABLE " + c.toString() + ";");
        output.write(Globals.nl);
        output.write("CREATE TABLE " + c.toString() + " (");
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
            output.write(attribute.getName() + " " + attribute.getType().toString() + ",");
            output.write(Globals.nl);
        }
        output.write("PRIMARY KEY (");
            this.writePrimaryKeys(output, c);
        output.write(")");
    }

    private Cardinality filterCardinality(ICardinality cardin) {
        Cardinality result = Cardinality.ERROR;

        if (cardin.getFrom() == 1 && cardin.getTo() == -1) {
            result = Cardinality.ONE_N;
        } else if (cardin.getFrom() == 1 && cardin.getTo() == 1) {
            result = Cardinality.ONE_ONE;
        } else if (cardin.getFrom() == 0 && cardin.getTo() == -1) {
            result = Cardinality.ZERO_N;
        }

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
        for (IRelation rel : c.getRelatedClass()) {
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
            switch (type) {
                case RELATION:
                    // N-N relace
                    if ((start_rel == Cardinality.ZERO_N && end_rel == Cardinality.ZERO_N)
                            || (start_rel == Cardinality.ONE_ONE && end_rel == Cardinality.ONE_ONE)) {
                        output.write("DROP TABLE " + start.toString() + "_" + end.toString() + ";");
                        output.write(Globals.nl);
                        output.write("CREATE TABLE " + start.toString() + "_" + end.toString() + " (");
                        output.write(Globals.nl);
                        output.write(start.toString());
                        output.write("_jmeno_pk_1" + " " + "typ_pk1");
                        output.write(Globals.nl);
                        output.write(end.toString());
                        output.write("_jmeno_pk_2" + " " + "typ_pk2");
                        output.write(Globals.nl);
                        output.write(");");
                        break;
                    }
                    // 1-N relace
                    if (start_rel == Cardinality.ONE_ONE && end_rel == Cardinality.ZERO_N) {
                        output.write("ALTER TABLE " + end.toString() + " ADD (");
                        output.write(start.toString());
                        output.write("_jmeno_pk_1" + " " + "typ_pk1");
                        output.write(Globals.nl);
                        output.write(");");
                    }
                    break;
                case COMPOSITION:
                    output.write("ALTER TABLE " + end.toString() + " ADD (");
                    output.write(Globals.nl);
                    // output.write(pk.getName()+" "+pk.getType().toString()+",");
                    output.write(Globals.nl);
                    output.write("PRIMARY KEY (");
                        this.writePrimaryKeys(output, c);
                    output.write(")");
                    output.write(Globals.nl);
                    output.write(");");
                    break;
            }
            // ---
            output.write(Globals.nl);

        }
    }        
            
}
