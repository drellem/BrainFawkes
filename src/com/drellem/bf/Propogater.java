/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

import com.drellem.bf.util.Index;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class Propogater {
    private byte[] tape;
    private int index = 0;
    private String outputFile;
    private BufferedWriter ostream;
    private ASTree tree;
    private boolean canInterpret = true;
    
    public Propogater(ASTree tree, String outputFile) throws IOException{
        this.tree = tree;
        this.outputFile = outputFile;
        ostream = new BufferedWriter(new FileWriter(outputFile));
    }
    
    
    
   
}
