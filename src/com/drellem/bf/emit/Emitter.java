/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.emit;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public interface Emitter extends Closeable{
    public void begin() throws IOException;
    public void plus(int amount, int relativeCell) throws IOException;
    public void minus(int amount, int relativeCell) throws IOException;
    public void inc(int amount) throws IOException;
    public void dec(int amount) throws IOException;
    public void get(int relativeCell) throws IOException;
    public void put(int relativeCell) throws IOException;
    public void loop() throws IOException;
    public void endLoop() throws IOException;
    public void clear(int relativeCell) throws IOException;
    public void putConstant(int b) throws IOException;
    public void end() throws IOException;
}
