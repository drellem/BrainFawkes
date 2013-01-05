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
 * Eliminates code like "index[1] += 0" and concatenates subsequent plusses, minuses, etc. like when compressing.
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class CleanPass implements OpPass{
    
    private String lookingFor = "none";
    private int numTimes = 0;
    private int relativeCell = 0;
    private Node node = new Node();

    @Override
    public ASTree pass(ASTree tree) {
        ASTree returnTree = new ASTree();
        Node n;
        while(tree.hasNext()){
            n = tree.getNext();
            switch(n.getType()){
                
                case PLUS:
                    Node.PlusNode temp = (Node.PlusNode)n;
                    if(temp.getNumTimes()!=0)returnTree.append(temp);
                    break;
                    
                case MINUS:
                    Node.MinusNode temp1 = (Node.MinusNode)n;
                    System.out.println("Minus:" + temp1.getNumTimes());
                    if(temp1.getNumTimes()!=0)returnTree.append(temp1);
                    break;
                    
                case INC:
                    Node.IncNode temp2 = (Node.IncNode)n;
                    if(temp2.getNumTimes()!=0)returnTree.append(temp2);
                    break;
                    
                case DEC:
                    Node.DecNode temp3 = (Node.DecNode)n;
                    if(temp3.getNumTimes()!=0)returnTree.append(temp3);
                    break;
                    
                case LOOP:
                    returnTree.append(pass(n.toTree()));
                    break;
                    
                default:
                    returnTree.append(n);
            }
        }
        return returnTree;
    }
}
