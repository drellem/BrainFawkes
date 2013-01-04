/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public interface Emitter {
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
    public void putConstant(byte b) throws IOException;
    public void end() throws IOException;
}
