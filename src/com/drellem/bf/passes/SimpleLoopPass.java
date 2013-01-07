/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.passes;

import com.drellem.bf.*;
import com.drellem.bf.Token.TokenType;
import com.drellem.bf.Node.*;
/**
 * A pass that tries to turn simple loops into multiplication.
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class SimpleLoopPass implements OpPass{
    private TokenStream lStream = new TokenStream();
    public int nestLevel = 0;

    @Override
    public ASTree pass(ASTree tree) {
        int currentLoop = 0;
        ASTree returnTree = new ASTree();
        TokenStream im = new TokenStream();
        TokenStream loop = new TokenStream();
        lStream = toTokenStream(tree);
        Token t;
        while(lStream.hasNext()){
            t = lStream.getNext();
            if(t.getType()==TokenType.LOOP){
                im.append(loop);
                currentLoop++; 
                continue;
            } else if(t.getType()==TokenType.END){
                currentLoop--;
                im.append(parse(loop));
                continue;
            }
            loop.append(t);
        }
        return returnTree;
    }
    
    private TokenStream parse(TokenStream stream){
        TokenStream returnStream = new TokenStream();
        TokenStream iStream = new TokenStream();
        Token t;
        int incCounter = 0;
        while(stream.hasNext()){
            t = stream.getNext();
            switch(t.getType()){
                case INC:
                    incCounter += t.getNumTimes();
                    break;
                    
                case DEC:
                    incCounter -= t.getNumTimes();
                    break;
                    
                case GET:
                    if(t.getRelativeCell()==0)return stream.clone();
                
                default: iStream.append(t);
                    
            }
            if(incCounter!=0)return stream.clone(); //not a simple loop
            
            while(iStream.hasNext()){
                t = iStream.getNext();
                switch(t.getType()){
                    case PLUS:
                        returnStream.append(new Token())
                }
            }
        }
        return returnStream;
    }
    
    private ASTree innerPass(ASTree tree){
        ASTree returnTree = new ASTree();
        ASTree lTree = new ASTree();
        Node n;
        
        while(tree.hasNext()){
            n = tree.getNext();
            if(n instanceof LoopNode){
                returnTree.append(lTree);
                lTree = new ASTree();
            }
        }
        
        return returnTree;
    }
    
    public TokenStream toTokenStream(ASTree tree){
        TokenStream loopStream = new TokenStream();
        
        Node n;
        PlusNode p;
        MinusNode m;
        IncNode i;
        DecNode d;
        GetNode g;
        PutNode pu;
        ClearNode c;
        LoopNode l;
        
        while(tree.hasNext()){
            n = tree.getNext();
            switch(n.getType()){
                case PLUS:
                    p = (PlusNode)n;
                    loopStream.append(new Token(TokenType.PLUS, p.getNumTimes(), p.getRelativeCell()));
                    break;
                    
                case MINUS:
                    m = (MinusNode)n;
                    loopStream.append(new Token(TokenType.MINUS, m.getNumTimes(), m.getRelativeCell()));
                    break;
                    
                case INC:
                    i = (IncNode)n;
                    loopStream.append(new Token(TokenType.INC, i.getNumTimes(), 0));
                    break;
                    
                case DEC:
                    d = (DecNode)n;
                    loopStream.append(new Token(TokenType.DEC, d.getNumTimes(), 0));
                    break;
                    
                case GET:
                    g = (GetNode)n;
                    loopStream.append(new Token(TokenType.GET, 0, g.getRelativeCell()));
                    break;
                    
                case PUT:
                    pu = (PutNode)n;
                    loopStream.append(new Token(TokenType.PUT, 0, pu.getRelativeCell()));
                    break;
                    
                case CLEAR:
                    c = (ClearNode)n;
                    loopStream.append(new Token(TokenType.CLEAR, 0, c.getRelativeCell()));
                    break;
                    
                case LOOP:
                    nestLevel++;
                    loopStream.append(new Token(TokenType.LOOP, 0, 0));
                    loopStream.append(toTokenStream(n.toTree()));
                    break;
            }
        }
        return loopStream;
    }
    
    private ASTree toTree(boolean inLoop, TokenStream t){
        ASTree tree = new ASTree();
        Token token;
        while(t.hasNext()){
            token = t.getNext();
            
            switch(token.getType()){
                case PLUS:
                    
            }
        }
        return tree;
    }
}
