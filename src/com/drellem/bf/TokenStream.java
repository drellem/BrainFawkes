/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The token stream of given Brainfuck code.
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
    
    public void append(TokenStream stream){
        while(stream.hasNext()) append(stream.getNext());
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
    
    @Override
    public TokenStream clone(){
        TokenStream ret = new TokenStream();
        ret.append(this);
        return ret;
    }
}
