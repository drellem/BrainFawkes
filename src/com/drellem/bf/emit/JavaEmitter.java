/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.emit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Outputs Java source code.
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class JavaEmitter implements Emitter{
    private String outputFile = "Main.java";
    private BufferedWriter ostream;

    
    public JavaEmitter() throws IOException{
        this.ostream = new BufferedWriter(new FileWriter(outputFile));
    }
    
    @Override
    public void begin() throws IOException {
        ostream.write("public class Main {\n" +
                      "\tprivate static byte[] tape = new byte[30000];\n" +
                      "\tprivate static int index = 0;\n" +
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
    public void putConstant(int b) throws IOException {
        if(b==10)ostream.write("\t\tSystem.out.println();\n");
        else ostream.write("\t\tSystem.out.print(\"" + (char)b + "\");\n");
    }

    @Override
    public void close() throws IOException {
        ostream.close();
    }
}
