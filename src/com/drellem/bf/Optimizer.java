/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

import com.drellem.bf.passes.OpPass;
import com.drellem.bf.passes.PointerPass;
import com.drellem.bf.Token.TokenType;
import com.drellem.bf.passes.CleanPass;
import com.drellem.bf.passes.CopyClearPass;

/**
 * Runs various optimization passes through the syntax tree.
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class Optimizer {
    private TokenStream tokens;
    private ASTree tree = new ASTree();
    private Node node = new Node();
    private OpPass[] passes = { new CopyClearPass(), new PointerPass(), new CleanPass() };
    
    public Optimizer(TokenStream tokens){
        this.tokens = tokens;
    }
    /**
     * Runs the optimization passes.
     * @return The resultant syntax tree.
     */
    public ASTree run(){
        tree = parse(false, tokens);
        System.out.println(passes.length);
        
        for (OpPass p : passes){
            tree = p.pass(tree);
        }
        
        Node n;
        while(tree.hasNext()){
            n = tree.getNext();
            System.out.println("Type:" + n.getType().toString());
            for (Node no : n.childNodes){
                System.out.println("->Type:"+no.getType().toString());
            }
        }
        //tree = passes[2].pass(tree);
        return tree;
    }
    
    /**
     * Turns a <code>TokenStream</code> into an <code>ASTree</code>.
     * @param inLoop Tells the method if it is being called recursively.
     * @param tokens
     * @return
     */
    public ASTree parse(boolean inLoop, TokenStream tokens){
        ASTree tree = new ASTree();
        while(tokens.hasNext()){
            Token t = tokens.getNext();
            switch(t.getType()){
                case PLUS:
                    if(t.getValue().equals("")){
                        tree.append(node.plusNode(0,1));
                        break;
                    }
                    tree.append(node.plusNode(0, Integer.parseInt(t.getValue())));
                    break;
                    
                case MINUS:
                    if(t.getValue().equals("")){
                        tree.append(node.minusNode(0,1));
                        break;
                    }
                    tree.append(node.minusNode(0, Integer.parseInt(t.getValue())));
                    break;
                    
                case INC:
                    if(t.getValue().equals("")){
                        tree.append(node.incNode(1));
                        break;
                    }
                    tree.append(node.incNode(Integer.parseInt(t.getValue())));
                    break;
                    
                case DEC:
                    if(t.getValue().equals("")){
                        tree.append(node.decNode(1));
                        break;
                    }
                    tree.append(node.decNode(Integer.parseInt(t.getValue())));
                    break;
                    
                case PUT:
                    tree.append(node.putNode(0));
                    break;
                    
                case GET:
                    tree.append(node.getNode(0));
                    break;
                    
                case LOOP:
                    Node n = node.loopNode();
                    n.addTree(parse(true, tokens));
                    tree.append(n);
                    break;
                    
                case END:
                    if(!inLoop){
                        System.err.println("Fatal error: unexpected ']' token");
                        System.exit(1);
                    }
                    else return tree;
                    break;
                    
                default:
                    System.err.println("Unrecognized token type " + t.getClass() + " with value " + t.getValue());
                    System.exit(0);
                    break;
                    
                    
            }
        }
        return tree;
    }
}
