/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.util;

/**
 * Hack-around.
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class Index {
    private int index;
    
    public Index(int index){ this.index = index; }
    
    public int value(){ return index; }
    public void set(int index){ this.index = index; }
    
}
