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
        PLUS, MINUS, INC, DEC, PUT, GET, LOOP, END, CLEAR, MULT, EXP
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
    
    public Token(TokenType t, int numTimes, int relativeCell, boolean adding){
        if(t==TokenType.MULT)
        this.type = TokenType.MULT;
        else if(t==TokenType.EXP)
            this.type = TokenType.EXP;
        else {
            System.err.println("Token.Token(TokenType,int,int,boolean), t==" + t.toString());
            System.exit(1);
        }
        this.numTimes = numTimes;
        this.relativeCell = relativeCell;
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
    
    //p[product+index] += numTimes*p[0+index]; p[0+index]=0;
}
