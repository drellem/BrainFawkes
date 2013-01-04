/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

import com.drellem.bf.Node.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The final phase in compilation. Parses the tree to propagate constants and perform last-minute optimizations.
 * It generates code by calling a pluggable, language-specific back-end that implements <code>Emitter</code>.
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class Generater {
    private byte[] tape = new byte[30000];
    private int index = 0;
    private ASTree tree;
    private Emitter e;
    private String outputFile;
    private BufferedWriter ostream;
    private boolean canInterpret = true;
    
    public Generater(ASTree tree, Emitter e, String outputFile) throws IOException{
        this.tree = tree;
        this.e = e;
        this.outputFile = outputFile;
        ostream = new BufferedWriter(new FileWriter(outputFile));
    }
    
    public void generate() throws IOException{
        Node node;
        PlusNode temp1;
        MinusNode temp2;
        IncNode temp3;
        DecNode temp4;
        GetNode temp5;
        PutNode temp6;
        ClearNode temp7;
        LoopNode temp8;
        
        while(tree.hasNext()){
            node = tree.getNext();
            
            switch(node.getType()){
                
                case PLUS:
                    temp1 = (PlusNode)node;
                    if(canInterpret) 
                        tape[index+temp1.getRelativeCell()] += temp1.getNumTimes();
                     else 
                        e.plus(temp1.getNumTimes(), temp1.getRelativeCell());
                     break;
                    
                case MINUS:
                    temp2 = (MinusNode)node;
                    if(canInterpret)
                        tape[index+temp2.getRelativeCell()] -= temp2.getNumTimes();
                    else
                        e.minus(temp2.getNumTimes(), temp2.getRelativeCell());
                    break;
                    
                case INC:
                    temp3 = (IncNode)node;
                    if(canInterpret)
                        index += temp3.getNumTimes();
                    else
                        e.inc(temp3.getNumTimes());
                    break;
                    
                case DEC:
                    temp4 = (DecNode)node;
                    if(canInterpret)
                        index -= temp4.getNumTimes();
                    else
                        e.dec(temp4.getNumTimes());
                    break;
                    
                case GET:
                    temp5 = (GetNode)node;
                    canInterpret = false;
                    e.get(temp5.getRelativeCell());
                    break;
                    
                case PUT:
                    temp6 = (PutNode)node;
                    if(canInterpret)
                        e.putConstant(tape[index+temp6.getRelativeCell()]);
                    else
                        e.put(temp6.getRelativeCell());
                    break;
                    
                case CLEAR:
                    temp7 = (ClearNode)node;
                    if(canInterpret)
                        tape[index+temp7.getRelativeCell()] = 0;
                    else
                        e.clear(temp7.getRelativeCell());
                    break;
                    
                case LOOP:
                    temp8 = (LoopNode)node;
                    if(canInterpret){
                        if(!temp8.interpretable()){
                            canInterpret = false;
                        } else {
                            while(tape[index]!=0){
                                for(Node n : node.childNodes){
                                    cleanInterpret(n);
                                }
                            }
                        }
                            
                    } else {
                        
                    }
                        
                    
                    
                    
            }
        }
    }
    
    public void cleanInterpret(Node n){
        switch(n.getType()){
            case PLUS:
                //TODO Code logic!
        }
    }
}
