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
<<<<<<< HEAD
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
=======
import integration.OutputJavaClass;
import java.io.*;
>>>>>>> origin/master
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
<<<<<<< HEAD

=======
    private OutputJavaClass output = null;
    private String suffix = ".sql";
    
    // Struktury
    private Set<String> Tables;
    private HashMap<String, Set<String>> Attributes;
    private HashMap<String, Set<String>> PrimaryKeys;       
    private HashMap<String, Set<FK>> ForeignKeys;
    private HashMap<String, Set<String>> Unique;
    
    // ForeignKey structure
    /**
     * table
     *      reference_table
     *              attribute REFERENCE TO reference_table(attribute)
     */
    public class FK
    {    
        public String ref_table;
        public HashMap<String, String> attr;   
        public FK(String tb)
        {
            ref_table = tb;
            attr = new HashMap<String, String>();
        }
    }         
    
>>>>>>> origin/master
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

<<<<<<< HEAD
        for (IElement iClass : myModel.getClasses()) {
            generateSql(output, iClass);
=======
        genTables();
        writeSqlTables(save_path, true);
                
        System.out.println("Generovani SQL dokonceno.");        
    }          
    
// ---------------------- METODY PRO GENEROVANI STRUKTURY ----------------------
    
    /**
     * Vybere jmena vsech tabulek a vlozi je do seznamu.
     * Soucasne i rodicovska funkce dalsich veci jako atributy, zavislosti, atd.
     */
    public void genTables()
    {
        System.out.println("Zacatek generovani tabulek.");
        
        Tables = new HashSet<String>();
        Attributes = new HashMap<String, Set<String>>();
        PrimaryKeys = new HashMap<String, Set<String>>();       
        ForeignKeys = new HashMap<String, Set<FK>>();
        Unique = new HashMap<String, Set<String>>();
        
        for (IElement element : myModel.getClasses())
        {
            Tables.add(element.toString()); System.out.println("Tabulka "+ element.toString() + ": ");
            genAttributes(element);        
            genRelations(element);         
>>>>>>> origin/master
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
<<<<<<< HEAD
    private void generateSql(BufferedWriter output, IElement c) throws IOException {
        try {        
            writeSqlTable(output, c);
            writeSqlRelations(output, c);
        } catch (IOException e) {
            throw e; // pripadnou psani preda dal
=======
    public void genAttributes(IElement element)
    {
        Set<String> attrSet = new HashSet<String>();   // seznam atributu
        Set<String> pkSet = new HashSet<String>();     // seznam primarnich klicu
        for (IAttribute iAttribute : element.getAttributeModels())   // projde vsechny atributy kazde tridy
        {                      
            attrSet.add(iAttribute.getName()); System.out.println("Atribut " + iAttribute.getName());
            if (isPrimaryKey(iAttribute.getName()))
            {
                pkSet.add(iAttribute.getName());
            }
>>>>>>> origin/master
        }
    }
    
    /**
     * pokud se jedna o primarni klic, vrati true, jinak false
     * 
     * @param input
     * @return 
     */
<<<<<<< HEAD
=======
    public void genRelations(IElement element)
    {
        for (IRelation relation : element.getRelatedClass()) 
        {
            IElement start = relation.getStartingClass();
            IElement end = relation.getEndingClass();
            RelationType type = relation.getRelationType();
            Cardinality startCardinality = filterCardinality(relation.getStartCardinality());
            Cardinality endCardinality = filterCardinality(relation.getEndCardinality());
            
            switch (type) 
            {
                /**
                 * RELACE
                 */
                case RELATION:
                    // N-N relace
                    // 1-1 relace
                    if ((startCardinality == Cardinality.ZERO_N && endCardinality == Cardinality.ZERO_N)
                     || (startCardinality == Cardinality.ONE_ONE && endCardinality == Cardinality.ONE_ONE)) 
                    {
                        String tableName = start.toString() + "_" + end.toString(); // jmeno relacni tabulky
                        
                        Tables.add(tableName); System.out.println("Tabulka " + tableName); // prida mezi tabulky
                                                
                        Set<String> relAttr = new HashSet<String>();
                        Set<String> relUnique = new HashSet<String>();
                        Set<FK> relForeignKeys = new HashSet<FK>();                        
                            
                            // vytvori atributy a unique constraints pro relacni tabulku z primarnich klicu startovni relace                                              
                            FK foreignKey = new FK(start.toString());  
                            Set<String> pks = PrimaryKeys.get(start.toString());
                            if (pks != null)
                            {
                                for (String pk : pks)   
                                {
                                    String attrName = start.toString() + "_" + pk;  // jmeno atributu
                                    relAttr.add(attrName); System.out.println("Atribut " + attrName);                         // atributy
                                    relUnique.add(attrName); System.out.println("Unique " + attrName);                        // unique constraints                                                       
                                    foreignKey.attr.put(attrName, pk); System.out.println("Cizi klic " + attrName + " - " + pk);              // cizi klic
                                }                        
                                relForeignKeys.add(foreignKey);
                            }
                        
                        
                            // vytvori atributy a unique constraints pro relacni tabulku z primarnich klicu koncove relace                                               
                            foreignKey = new FK(end.toString());
                            pks = PrimaryKeys.get(end.toString());
                            if (pks != null)
                            {
                                for (String pk: pks)
                                {
                                    String attrName = end.toString() + "_" + pk;    // jmeno atributu
                                    relAttr.add(attrName); System.out.println("Atribut " + attrName);                         // atributy
                                    relUnique.add(attrName); System.out.println("Unique " + attrName);                       // unique constraints
                                    foreignKey.attr.put(attrName, pk); System.out.println("Cizi klic " + attrName + " - " + pk);             // cizi klic                                                        
                                }                        
                                relForeignKeys.add(foreignKey);
                            }
                        
                        
                        // prida jednotlive struktury do globalnich map
                        Attributes.put(tableName, relAttr);
                        Unique.put(tableName, relUnique);
                        ForeignKeys.put(tableName, relForeignKeys);
                        break;
                    }
                    // 1-N relace
                    if (startCardinality == Cardinality.ONE_ONE && endCardinality == Cardinality.ZERO_N) 
                    {                        
                        // prida mezi atributy N entity primarni klice 1 entity a udela zaznam o cizim klici                        
                        Set<String> endAttr = Attributes.get(end.toString());
                        Set<FK> relForeignKeys = new HashSet<FK>();
                        FK foreignKey = new FK(start.toString());  
                        Set<String> pks = PrimaryKeys.get(start.toString());
                        if (pks != null)
                        {
                            for (String pk: PrimaryKeys.get(start.toString()))
                            {
                                String attrName = start.toString() + "_" + pk;
                                endAttr.add(attrName);  System.out.println("Atribut " + attrName);                         
                                foreignKey.attr.put(attrName, pk); System.out.println("Cizi klic " + attrName + " - " + pk);  
                            }
                            relForeignKeys.add(foreignKey);
                        }
                        
                        // udela zaznam o cizim klici                        
                        ForeignKeys.put(end.toString(), relForeignKeys);
                        break;
                    }
                    break;
                    
                /**
                 * Kompozice
                 */
                case COMPOSITION:                        
                    break;
            }
        }
    }
    
// --------------------------- METODY PRO VYPIS SQL ----------------------------
    
    public void writeSqlTables(String save_path, boolean drop) throws IOException
    {
        if (save_path == null)
            save_path = ".";
        output = new OutputJavaClass(save_path + File.separator + "pokus" + suffix);                
        
        // drop
        if (drop)
        {
            for (String table : Tables)        
                output.write("DROP TABLE " + table.toString() + ";" + Globals.nl);
        }
        output.write(Globals.nl);
        
        // create
        for (String table : Tables)
        {
            output.write("CREATE TABLE " + table.toString() + " (" + Globals.nl);
            writeSQLAttributes(table);
            writeSQLConstraints(table);
            output.write(");" + Globals.nl);
        }        
        
        output.close();
    }
    
    public void writeSQLAttributes(String table) throws IOException
    {
        for (String attr : Attributes.get(table))        
            output.write(attr + "," + Globals.nl);        
    }
    
    public void writeSQLConstraints(String table) throws IOException
    {   
        // primarni klice
        output.write("CONSTRAINT PK_" + table + " "); // jmeno constraintu
        output.write("PRIMARY KEY (");
        Set<String> pks = PrimaryKeys.get(table);
        if (pks != null)
        {
            for (String pk : pks)
            {
                output.write(pk + ", ");
            }
        }
        output.write(")");
                
        output.write(Globals.nl);
        
        // unikatni atributy
        output.write("CONSTRAINT UNQ_" + table + " "); // jmeno uniqu
        output.write("UNIQUE (");
        Set<String> unqs = Unique.get(table);
        if (unqs != null)
        {
            for (String unq : Unique.get(table))
            {
                output.write(unq + ", ");
            }
        }
        output.write(")");
        
        output.write(Globals.nl);
        
        // cizi klice
        int count = 1;
        Set<FK> fks = ForeignKeys.get(table);
        if (fks != null)
        {
            for (FK fk : fks)
            {            
                output.write("CONSTRAINT FK_" + table + "_" + count); // jmeno constraintu                
                output.write(" FOREIGN KEY ("); // atributy
                for (String foreignKey : fk.attr.keySet())
                    output.write(foreignKey + ", ");

                output.write("REFERENCES " + fk.ref_table + "("); // reference
                for (String foreignKey : fk.attr.keySet())
                    output.write(fk.attr.get(foreignKey));

                count++;
            } 
        }
    }
    
    
// ----------------------------- POMOCNE METODY --------------------------------
    
>>>>>>> origin/master
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
