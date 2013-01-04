/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.passes;

import com.drellem.bf.ASTree;
import com.drellem.bf.Node;
import com.drellem.bf.passes.OpPass;
import com.drellem.bf.Node.NodeType;

/**
 * Eliminates code like "index[1] += 0"
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class CleanPass implements OpPass{
    private ASTree returnTree = new ASTree();

    @Override
    public ASTree pass(ASTree tree) {
        Node n;
        while(tree.hasNext()){
            n = tree.getNext();
            
            switch(n.getType()){
                
                case PLUS:
                    Node.PlusNode temp = (Node.PlusNode)n;
                    if(temp.getNumTimes()!=0)returnTree.append(n);
                    break;
                    
                case MINUS:
                    Node.MinusNode temp1 = (Node.MinusNode)n;
                    if(temp1.getNumTimes()!=0)returnTree.append(n);
                    break;
                    
                case INC:
                    Node.IncNode temp2 = (Node.IncNode)n;
                    if(temp2.getNumTimes()!=0)returnTree.append(n);
                    break;
                    
                case DEC:
                    Node.DecNode temp3 = (Node.DecNode)n;
                    if(temp3.getNumTimes()!=0)returnTree.append(n);
                    break;
                    
                default:
                    System.err.println("Unrecognized node: " + n.getType());
            }
        }
        
        
        return returnTree;
    }

}
