/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.emit;

import com.drellem.bf.ASTree;
import com.drellem.bf.Node;
import com.drellem.bf.Node.ClearNode;
import com.drellem.bf.Node.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A back-end that interprets instead of compiling. Not the fastest interpreter I'm sure, but pretty darn easy and convenient to write.
 * Not finished yet due to other priorities.
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class Interpret implements Emitter{

    @Override
    public void begin() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void plus(int amount, int relativeCell) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void minus(int amount, int relativeCell) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void inc(int amount) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dec(int amount) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void get(int relativeCell) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void put(int relativeCell) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void loop() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void endLoop() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear(int relativeCell) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void putConstant(int b) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void end() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
  /**  private int tape[] = new int[30000];
    private ArrayList<ASTree> loop = new ArrayList<ASTree>();
    private int index = 0;
    private int nestLevel = 0;
    
    private void interpret(Node n){
        
    }
    @Override
    public void begin() throws IOException {      }

    @Override
    public void plus(int amount, int relativeCell) throws IOException {
        if(nestLevel>0){
            //
            loop.get(nestLevel).getNext();
        }
        tape[index+relativeCell] += amount;
    }

    @Override
    public void minus(int amount, int relativeCell) throws IOException {
        tape[index+relativeCell] -= amount;
    }

    @Override
    public void inc(int amount) throws IOException {
        index += amount;
    }

    @Override
    public void dec(int amount) throws IOException {
        index -= amount;
    }

    @Override
    public void get(int relativeCell) throws IOException {
        tape[index+relativeCell] = System.in.read();
    }

    @Override
    public void put(int relativeCell) throws IOException {
        System.out.print((char)tape[index+relativeCell]);
    }

    @Override
    public void loop() throws IOException {
        nestLevel++;
    }

    @Override
    public void endLoop() throws IOException {
        Node n;
            PlusNode p;
            MinusNode m;
            IncNode i;
            DecNode d;
            GetNode g;
            PutNode pu;
            LoopNode l;
            ClearNode c;
            PutConstNode pc;
        while(tape[index]!=0){
            ASTree t = loop.get(nestLevel);
            
            

        }
    }

    @Override
    public void clear(int relativeCell) throws IOException {
        tape[index+relativeCell] = 0;
    }

    @Override
    public void putConstant(int b) throws IOException {
        if(b==20)System.out.println();
        else System.out.print((char)b);
    }

    @Override
    public void end() throws IOException {
        
    }

    @Override
    public void close() throws IOException {
        
    }
*/
}

