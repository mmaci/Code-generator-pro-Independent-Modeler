package integration;

import gen.Globals;
import java.io.Closeable;
import java.io.FileWriter;
import java.io.IOException;
import org.openide.util.Exceptions;

public class OutputJavaClass implements Closeable {

    private FileWriter w = null;
    private int numOfTabs = 0;
    private boolean tabs= true;

    public OutputJavaClass(String fileName) throws IOException {
        w = new FileWriter(fileName);
    }

    public void write(String text) throws IOException {
        if (text.equals(Globals.lbrace)) {
            numOfTabs++;
        } else if (text.equals(Globals.rbrace)) {
            numOfTabs--;
        }

        if (text.equals(Globals.nl)) {
            w.write(text);
            tabs=true;
        } else {
            if (tabs){
                this.writeTabs();
            }
            if (!text.equals(Globals.semic) && !tabs) {
                w.write(" " + text);
            } else {
                w.write(text);
            }
            tabs = false;
        }
    }

    private void writeTabs() throws IOException {
        for (int i = 0; i < numOfTabs; i++) {
            w.write("\t");
        }
    }

    @Override
    public void close() {
        try {
            w.close();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
