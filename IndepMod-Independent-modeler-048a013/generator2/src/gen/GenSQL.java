/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gen;

import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.ICardinality;
import cz.cvut.indepmod.classmodel.api.model.IClass;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import integration.OutputJavaClass;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author radek
 */
public class GenSQL {

    private IClassModelModel myModel;
    private OutputJavaClass out = null;
    private String suffix = ".java";

    public GenSQL(IClassModelModel model) {
        System.out.println("predan model...");
        myModel = model;
    }

    public void generateModel() throws IOException {
        System.out.println("generovani...");
        String filename = "test.sql"; // jmeno souboru do ktereho budem zapisovat
        FileWriter fstream = new FileWriter(filename);
        BufferedWriter output = new BufferedWriter(fstream);

        for (IClass iClass : myModel.getClasses()) {
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
    private void generateSql(BufferedWriter output, IClass c) throws IOException {
        try {
            writeSqlTable(output, c);
            writeSqlRelations(output, c);
        } catch (IOException e) {
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
    private void writeSqlTable(BufferedWriter output, IClass c) throws IOException {
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
    private void writeSqlAttributes(BufferedWriter output, IClass c) throws IOException {
        for (IAttribute attribute : c.getAttributeModels()) {
            output.write(attribute.getName() + " " + attribute.getType().toString() + ",");
            output.write(Globals.nl);
        }
        output.write("PRIMARY KEY (");
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
    private void writeSqlRelations(BufferedWriter output, IClass c) throws IOException {
        for (IRelation rel : c.getRelatedClass()) {
            // zkraceni jednotlivych nazvu pro prehlednost
            IClass start = rel.getStartingClass();
            IClass end = rel.getEndingClass();
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
}
