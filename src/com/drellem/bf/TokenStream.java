/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class TokenStream {
    private ArrayList<Token> tokens = new ArrayList<Token>();
    private int location = -1;
    
    public TokenStream(){}
    public TokenStream(Token[] tokens){
        this.tokens.addAll(Arrays.asList(tokens));
    }
    
    public void append(Token token){
        tokens.add(token);
    }
    
    public void setLocation(int location){
        this.location = location;
    }
    
    public Token getNext(){
        return tokens.get(++location);
    }
    
    public boolean hasNext(){
        return (location < tokens.size()-1);
    }
}
