package gen;

import gen.genEnums.Cardinality;
import gen.genEnums.DBType;
import cz.cvut.indepmod.classmodel.api.model.IAttribute;
import cz.cvut.indepmod.classmodel.api.model.IAnotation;
import cz.cvut.indepmod.classmodel.api.model.ICardinality;
import cz.cvut.indepmod.classmodel.api.model.IElement;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;
import cz.cvut.indepmod.classmodel.api.model.IRelation;
import cz.cvut.indepmod.classmodel.api.model.IType;
import cz.cvut.indepmod.classmodel.api.model.RelationType;
import integration.OutputJavaClass;
import java.io.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;


/**
 * @Author Pavel Macenauer
 */
public class GenSQL implements IGen{

    private IClassModelModel myModel;
    private OutputJavaClass output = null;
    private String suffix = ".sql";
    private boolean debug = true;
    private String file_name = "generated";
    
    // Struktury
    private Set<String> Tables;
    private HashMap<String, Set<sAttribute>> Attributes;
    private HashMap<String, Set<sAttribute>> PrimaryKeys;       
    private HashMap<String, Set<FK>> ForeignKeys;
    private HashMap<String, Set<String>> Unique;
    
    // ForeignKey structure
    /**
     * table
     *      reference_table
     *              attribute REFERENCE TO reference_table(attribute)
     */
    private class FK
    {    
        public String ref_table;
        public HashMap<String, String> attr;   
        public FK(String tb)
        {
            ref_table = tb;
            attr = new HashMap<String, String>();
        }
    }  
    
    private class sAttribute
    {
        public String name;
        public String type;
        public sAttribute(String n, String t)
        {
            name = n;
            type = t;
        }
    }
        
    
    public GenSQL(IClassModelModel model) {
        if (debug) System.out.println("Predan model.");
        myModel = model;
    }

    @Override
    public void generateModel(String save_path) throws IOException {
        
        if (debug) System.out.println("Zacatek generovani struktur.");
        genTables();
        if (debug) System.out.println("Generovani struktur dokonceno.");
        if (debug) System.out.println("Zacatek generovani SQL vystupu.");
        writeSqlTables(save_path, true);
        if (debug) System.out.println("Konec generovani SQL vystupu.");
                        
    }          
    
// ---------------------- METODY PRO GENEROVANI STRUKTURY ----------------------
    
    /**
     * Vybere jmena vsech tabulek a vlozi je do seznamu.
     * Soucasne i rodicovska funkce dalsich veci jako atributy, zavislosti, atd.
     */
    private void genTables()
    {
        if (debug) System.out.println("Zacatek generovani tabulek.");
        
        Tables = new HashSet<String>();
        Attributes = new HashMap<String, Set<sAttribute>>();
        PrimaryKeys = new HashMap<String, Set<sAttribute>>();       
        ForeignKeys = new HashMap<String, Set<FK>>();
        Unique = new HashMap<String, Set<String>>();
        
        for (IElement element : myModel.getClasses())
        {
            Tables.add(element.toString()); if (debug) System.out.println("\t" + "Tabulka: "+ element.toString());
            genAttributes(element);        
            genRelations(element);         
        }
    }        
    
    /**
     * Vybere jmena vsech atributu dane tridy a prida je do seznamu vazaneho na danou tridu     
     */
    private void genAttributes(IElement element)
    {
        Set<sAttribute> attrSet = new HashSet<sAttribute>();    // seznam atributu
        Set<sAttribute> pkSet = new HashSet<sAttribute>();              // seznam primarnich klicu
        for (IAttribute iAttribute : element.getAttributeModels())   // projde vsechny atributy kazde tridy
        {    
            sAttribute tmpAttr = new sAttribute(iAttribute.getName(), convertToDBType(iAttribute.getType(), DBType.GENERAL));
            attrSet.add(tmpAttr); if (debug) System.out.println("\t\t" + "Atribut: " + tmpAttr.type + " " + tmpAttr.name);
            
            // detekce primarniho klice
            if (isPrimaryKey(iAttribute))
            {                
                pkSet.add(tmpAttr); if (debug) System.out.println("\t\t\t" + "Primarni klic: " + tmpAttr.name);
            }
        }
        // vytvori seznamy atributu a primarni klicu vazane na jmena dane tabulky
        Attributes.put(element.toString(), attrSet);
        PrimaryKeys.put(element.toString(), pkSet);
    }
    
    /**
     * Vygeneruje constrainty, tabulky, atributy podle typu jednotlivych relaci     
     */
    private void genRelations(IElement element)
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
                        
                        Tables.add(tableName); if (debug) System.out.println("\t" + "Tabulka: " + tableName); // prida mezi tabulky
                                                
                        Set<sAttribute> relAttr = new HashSet<sAttribute>();
                        Set<String> relUnique = new HashSet<String>();
                        Set<FK> relForeignKeys = new HashSet<FK>();                        
                            
                            // vytvori atributy a unique constraints pro relacni tabulku z primarnich klicu startovni relace                                              
                            FK foreignKey = new FK(start.toString());  
                            Set<sAttribute> pks = PrimaryKeys.get(start.toString());
                            if (pks != null)
                            {
                                for (sAttribute pk : pks)   
                                {
                                    String attrName = start.toString() + "_" + pk.name;  // jmeno atributu
                                    sAttribute tmpAttr = new sAttribute(attrName, pk.type);                                    
                                    relAttr.add(tmpAttr);                   if (debug) System.out.println("\t\t" + "Atribut: " + attrName);                         // atributy
                                    relUnique.add(attrName);                if (debug) System.out.println("\t\t" + "Unique: " + attrName);                        // unique constraints                                                       
                                    foreignKey.attr.put(attrName, pk.name); if (debug) System.out.println("\t\t" + "Cizi klic: " + attrName + " od " + pk.name);              // cizi klic
                                }                        
                                relForeignKeys.add(foreignKey);
                            }
                        
                        
                            // vytvori atributy a unique constraints pro relacni tabulku z primarnich klicu koncove relace                                               
                            foreignKey = new FK(end.toString());
                            pks = PrimaryKeys.get(end.toString());
                            if (pks != null)
                            {
                                for (sAttribute pk : pks)   
                                {
                                    String attrName = end.toString() + "_" + pk.name;  // jmeno atributu
                                    sAttribute tmpAttr = new sAttribute(attrName, pk.type);                                    
                                    relAttr.add(tmpAttr);                   if (debug) System.out.println("\t\t" + "Atribut: " + attrName);                         // atributy
                                    relUnique.add(attrName);                if (debug) System.out.println("\t\t" + "Unique: " + attrName);                        // unique constraints                                                       
                                    foreignKey.attr.put(attrName, pk.name); if (debug) System.out.println("\t\t" + "Cizi klic: " + attrName + " od " + pk.name);              // cizi klic
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
                        Set<sAttribute> endAttr = Attributes.get(end.toString());
                        Set<FK> relForeignKeys = new HashSet<FK>();
                        FK foreignKey = new FK(start.toString());  
                        Set<sAttribute> pks = PrimaryKeys.get(start.toString());
                        if (pks != null)
                        {
                            for (sAttribute pk: PrimaryKeys.get(start.toString()))
                            {
                                String attrName = start.toString() + "_" + pk.name;
                                sAttribute tmpAttr = new sAttribute (attrName, pk.type);
                                endAttr.add(tmpAttr);                       if (debug) System.out.println("\t\t" + "Atribut: " + attrName);                         
                                foreignKey.attr.put(attrName, pk.name);     if (debug) System.out.println("\t\t" + "Cizi klic: " + attrName + " od " + pk.name);  
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
    
    private void writeSqlTables(String save_path, boolean drop) throws IOException
    {
        if (save_path == null)
            save_path = ".";
        output = new OutputJavaClass(save_path + File.separator + file_name + suffix);                
        
        // drop
        if (drop)
        {
            for (String table : Tables)        
                output.write("DROP TABLE " + table.toString() + ";" + Globals.nl);
        }
        output.write("\n");
        
        // create
        for (String table : Tables)
        {
            output.write("CREATE TABLE " + table.toString() + " (" + Globals.nl);
            writeSQLAttributes(table);
            writeSQLConstraints(table);
            output.write("\n" + ");" + "\n\n");
        }        
        
        output.close();
    }
    
    private void writeSQLAttributes(String table) throws IOException
    {
        Iterator itr = Attributes.get(table).iterator();
        while (itr.hasNext())        
        {       
            sAttribute attr = (sAttribute)itr.next();
            output.write("\t" + attr.name + " " + attr.type);
            if (itr.hasNext())
            {
                output.write (",");
                output.write("\n");
            }
        }
    }
    
    private void writeSQLConstraints(String table) throws IOException
    {               
        Iterator itr;
        
        // primarni klice
        if (PrimaryKeys.get(table) != null)
        {            
            output.write(",");
            output.write("\n");
            
            output.write("\t" + "CONSTRAINT PK_" + table + " "); // jmeno constraintu
            output.write("PRIMARY KEY (");
            
            itr = PrimaryKeys.get(table).iterator();
            while (itr.hasNext())        
            {       
                sAttribute attr = (sAttribute)itr.next();
                output.write(attr.name);
                if (itr.hasNext())
                    output.write (", ");            
            }                
            output.write(")");  
        }
        
        // unikatni atributy
        if (Unique.get(table) != null)
        {
            output.write(",");
            output.write("\n");            
            
            output.write("\t" + "CONSTRAINT UNQ_" + table + " "); // jmeno uniqu
            output.write("UNIQUE (");

            itr = Unique.get(table).iterator();
            while (itr.hasNext())        
            {       
                String unq = (String)itr.next();
                output.write(unq);
                if (itr.hasNext())
                    output.write (", ");            
            }                
            output.write(")");                   
        }
        // cizi klice
        int count = 1;        
        
        if (ForeignKeys.get(table) != null)
        {
            output.write(",");
            output.write("\n");            
            
            itr = ForeignKeys.get(table).iterator();
            while (itr.hasNext())
            {
                FK fk = (FK)itr.next();
                output.write("\t" + "CONSTRAINT FK_" + table + "_" + count); // jmeno constraintu                
                output.write(" FOREIGN KEY ("); // atributy

                Iterator itrKey = fk.attr.keySet().iterator();
                while (itrKey.hasNext())
                {
                    String foreignKey = (String)itrKey.next();
                    output.write(foreignKey);
                    if (itrKey.hasNext())
                         output.write(", ");
                }

                output.write(") ");            
                output.write("REFERENCES " + fk.ref_table + "("); // reference

                itrKey = fk.attr.keySet().iterator();
                while (itrKey.hasNext())
                {
                    String foreignKey = (String)itrKey.next();
                    output.write(foreignKey);
                    if (itrKey.hasNext())
                         output.write(", ");
                }
                output.write(")");
                
                if (itr.hasNext())
                {
                    output.write(",");
                    output.write("\n");
                }

                count++;
            }
        }
    }
    
    
// ----------------------------- POMOCNE METODY --------------------------------
    
    private boolean isPrimaryKey(IAttribute attr)
    {                
        for (IAnotation anot : attr.getAnotations())
        {
            if (anot.getName().equals("PK"))
                return true;
        }            
        return false;           
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
    
    private String convertToDBType(IType attr_type, DBType db_type)
    {
        String tmpAttr = attr_type.getTypeName();
        if (tmpAttr.equals("int"))
        {
            switch (db_type)
            {
                case GENERAL:
                    return "INT(11)";                                                            
            }           
        }
        else
        if (tmpAttr.equals("String"))
        {
            switch (db_type)
            {
                case GENERAL:
                    return "TEXT";
            }                
        }
        else
        if (tmpAttr.equals("double"))
        {
            switch (db_type)
            {
                case GENERAL:
                    return "DOUBLE";
            }                
        }
        else
        if (tmpAttr.equals("boolean"))
        {
            switch (db_type)
            {
                case GENERAL:
                    return "BOOLEAN";                                                 
            }    
        }
        return tmpAttr;
    }   
}
