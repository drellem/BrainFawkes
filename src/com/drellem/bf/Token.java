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
    private int numTimes;
    private int relativeCell;
    private int f1Loc, f2Loc, pLoc;
    private boolean adding;
    public enum TokenType {
        PLUS, MINUS, INC, DEC, PUT, GET, LOOP, END, CLEAR, MULT
    }
    private TokenType type;
    private String value;
    
    public Token(TokenType type, String value){
        this.type = type;
        this.value = value;
    }
    
    public Token(TokenType type, int numTimes, int relativeCell){
        this.type = type;
        this.numTimes = numTimes;
        this.relativeCell = relativeCell;
    }
    
    public Token(int f1Loc, int f2Loc, int pLoc, boolean adding){
        this.type = TokenType.MULT;
        this.f1Loc = f1Loc;
        this.f2Loc = f2Loc;
        this.pLoc = pLoc;
        this.adding = adding;
    }
    
    
    public TokenType getType(){ return type; }
    public String getValue(){ return value; }
    public int getNumTimes(){ return numTimes; }
    public int getRelativeCell(){ return relativeCell; }
    public int getF1(){  return f1Loc; }
    public int getF2(){ return f2Loc; }
    public int getP(){ return pLoc; }
    public boolean getAdding(){ return adding; }
    
    //p[product+index] += p[product+index]*p[0+index]; p[0+index]=0;
}
