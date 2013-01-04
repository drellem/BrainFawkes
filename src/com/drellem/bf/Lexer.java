/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class Lexer {
    private TokenStream tokens = new TokenStream();
    private String inputFile, buildInts = "";
    private BufferedReader istream;
    
    public TokenStream lex(){
        return lex(inputFile);
    }
    
    public TokenStream lex(String inputFile){
        new Compressor().run(inputFile, inputFile + "c");
        inputFile = inputFile + "c";
        return lexCompressed(inputFile);
    }
    
    public TokenStream lexCompressed(String inputFile){
        try {
            this.istream = new BufferedReader(new FileReader(inputFile));
            interpret(istream.read());
            istream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lexer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(Lexer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tokens;
    }
    
    public void interpret(int ic) throws IOException{
        char i;
        char c = (char)ic;
        if(ic==-1)return;
        switch(c){
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':  buildInts = buildInts + c; interpret(istream.read()); break;
            case '+':
                tokens.append(new Token(Token.TokenType.PLUS, buildInts));
                buildInts = "";
                interpret(istream.read());
                break;
            
            case '-':
                tokens.append(new Token(Token.TokenType.MINUS, buildInts));
                buildInts = "";
                interpret(istream.read());
                break;
                
            case '<':
                tokens.append(new Token(Token.TokenType.DEC, buildInts));
                buildInts = "";
                interpret(istream.read());
                break;
                
            case '>':
                tokens.append(new Token(Token.TokenType.INC, buildInts));
                buildInts = "";
                interpret(istream.read());
                break;
                
            case ',':
                tokens.append(new Token(Token.TokenType.GET, ","));
                buildInts = "";
                interpret(istream.read());
                break;
            
            case '.':
                tokens.append(new Token(Token.TokenType.PUT, "."));
                buildInts = "";
                interpret(istream.read());
                break;
                
            case '[':
                tokens.append(new Token(Token.TokenType.LOOP, "["));
                buildInts = "";
                interpret(istream.read());
                break;
                
            case ']':
                tokens.append(new Token(Token.TokenType.END, "]"));
                buildInts = "";
                interpret(istream.read());
                break;
                
            default: //do nothing
        }
    }
}
