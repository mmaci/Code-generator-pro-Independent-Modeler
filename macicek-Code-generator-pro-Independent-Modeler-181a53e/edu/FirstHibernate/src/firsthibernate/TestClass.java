/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package firsthibernate;

/**
 *
 * @author Pavel
 */
public class TestClass {
    private String Testname;
    private int Testid;

    public TestClass(){}
    
    public TestClass(int i, String n)
    {
        Testid = i;
        Testname = n;
    }

    public int getTestid()
    {
        return Testid;
    }

    public String getTestname()
    {
        return Testname;
    }

    public void setTestid(int i)
    {
        Testid = i;
    }

    public void setTestname(String tn)
    {
        Testname = tn;
    }

}
