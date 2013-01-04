/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

/**
 * Represents a <code>Token</code>.
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class Token {
    public enum TokenType {
        PLUS, MINUS, INC, DEC, PUT, GET, LOOP, END
    }
    private TokenType type;
    private String value;
    
    public Token(TokenType type, String value){
        this.type = type;
        this.value = value;
    }
    
    public TokenType getType(){ return type; }
    public String getValue(){ return value; }
}
