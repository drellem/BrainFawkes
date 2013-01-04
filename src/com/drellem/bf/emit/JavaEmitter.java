/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.emit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class JavaEmitter implements Emitter{
    private String outputFile;
    private BufferedWriter ostream;

    
    public JavaEmitter(String outputFile) throws IOException{
        this.outputFile = outputFile;
        this.ostream = new BufferedWriter(new FileWriter(outputFile));
    }
    
    @Override
    public void begin() throws IOException {
        ostream.write("public class Main\n" +
                      "\tprivate byte[] tape = new byte[30000];\n" +
                      "\tprivate int index = 0;\n" +
                      "\tpublic static void main(String[] args){\n"           
                );
    }

    @Override
    public void plus(int amount, int relativeCell) throws IOException {
        ostream.write("\t\ttape[index+" + relativeCell + "] += " + amount + ";\n");
    }

    @Override
    public void minus(int amount, int relativeCell) throws IOException {
        ostream.write("\t\ttape[index" + relativeCell + "] -= " + amount + ";\n");
    }

    @Override
    public void inc(int amount) throws IOException {
        ostream.write("\t\tindex += " + amount + ";\n");
    }

    @Override
    public void dec(int amount) throws IOException {
        ostream.write("\t\tindex -= " + amount + ";\n");
    }

    @Override
    public void get(int relativeCell) throws IOException {
        ostream.write("\t\ttape[index+" + relativeCell + "] = System.in.read();\n");
    }

    @Override
    public void put(int relativeCell) throws IOException {
        ostream.write("\t\tSystem.out.println((char)tape[index+" + relativeCell + "]);\n");
    }

    @Override
    public void loop() throws IOException {
        ostream.write("\t\twhile((int)tape[index] != 0){\n");
    }

    @Override
    public void endLoop() throws IOException {
        ostream.write("\t\t}\n");
    }

    @Override
    public void clear(int relativeCell) throws IOException {
        ostream.write("\t\ttape[index+" + relativeCell + "] = 0;\n");
    }

    @Override
    public void end() throws IOException {
        ostream.write("\t}\n" +
                      "}\n"
                );
    }

    @Override
    public void putConstant(byte b) throws IOException {
        ostream.write("\t\tSystem.out.println(" + (char)b + ");\n");
    }

}
