/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package firsthibernate;

import org.hibernate.Session;
import java.util.*;
import org.hibernate.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;




/**
 *
 * @author Pavel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Session session = null;

    try
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();

        TestClass test = new TestClass();
        test.setTestid(1);
        test.setTestname("Pavel");

        session.save(test);        
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
    }
    finally
    {
      session.flush();
      session.close();
    }

    }

}
