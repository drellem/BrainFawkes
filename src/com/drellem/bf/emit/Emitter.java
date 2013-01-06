/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.emit;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;

/**
 * The interface for a code-emitting back-end which will be called by <code>Generator</code>.
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public interface Emitter extends Closeable{
    /**
     * Writes the header of the target program.
     * @throws IOException 
     */
    public void begin() throws IOException;
    /**
     * Increments the data at the index. Brainfuck: +
     * @param amount The amount to increment the data by.
     * @param relativeCell The cell relative to the current index.
     * @throws IOException 
     */
    public void plus(int amount, int relativeCell) throws IOException;
    /**
     * Decrements the data at the index. Brainfuck: -
     * @param amount The amount to decrement the data by.
     * @param relativeCell The cell relative to the current index.
     * @throws IOException 
     */
    public void minus(int amount, int relativeCell) throws IOException;
    /**
     * Increments the index. Brainfuck: >
     * @param amount The amount to increment the index by.
     * @throws IOException 
     */
    public void inc(int amount) throws IOException;
    /**
     * Decrements the index. Brainfuck: <
     * @param amount The amount to decrement the index by.
     * @throws IOException 
     */
    public void dec(int amount) throws IOException;
    /**
     * Reads one character from STDIN. Brainfuck: ,
     * @param relativeCell The cell to read the character to (relative to the index).
     * @throws IOException 
     */
    public void get(int relativeCell) throws IOException;
    /**
     * Writes a character to STDOUT. Brainfuck: .
     * @param relativeCell The cell to write the character from (relative to the index).
     * @throws IOException 
     */
    public void put(int relativeCell) throws IOException;
    /**
     * Begins a loop. Brainfuck: [
     * @throws IOException 
     */
    public void loop() throws IOException;
    /**
     * Ends a loop. Brainfuck: ]
     * @throws IOException 
     */
    public void endLoop() throws IOException;
    /**
     * Clears the data at the index.
     * @param relativeCell The cell to clear the data of (relative to the index).
     * @throws IOException 
     */
    public void clear(int relativeCell) throws IOException;
    /**
     * Puts a constant character, represented as an integer. (Should be cast to a <code>char</code>.)
     * @param b
     * @throws IOException 
     */
    public void putConstant(int b) throws IOException;
    /**
     * Writes the footer of the target program.
     * @throws IOException 
     */
    public void end() throws IOException;
}
