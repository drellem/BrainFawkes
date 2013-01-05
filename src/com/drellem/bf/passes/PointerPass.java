/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.passes;

import com.drellem.bf.ASTree;
import com.drellem.bf.Node;
import com.drellem.bf.Node.NodeType;

/**
 * Propagates pointers.
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class PointerPass implements OpPass{
    private boolean nest = false;
    Node no = new Node();

    @Override
    public ASTree pass(ASTree tree) {
        ASTree returnTree = new ASTree();
        Node n;
        int relativeIndex = 0;
        while(tree.hasNext()){
            n = tree.getNext();
            switch(n.getType()){
                
                case PLUS:
                    Node.PlusNode temp = (Node.PlusNode)n;
                    returnTree.append(n.plusNode(relativeIndex, temp.getNumTimes()));
                    break;
                    
                case MINUS:
                    Node.MinusNode temp1 = (Node.MinusNode)n;
                    returnTree.append(n.minusNode(relativeIndex, temp1.getNumTimes()));
                    break;
                    
                case INC:
                    Node.IncNode temp2 = (Node.IncNode)n;
                    relativeIndex += temp2.getNumTimes();
                    break;
                    
                case DEC:
                    Node.DecNode temp3 = (Node.DecNode)n;
                    relativeIndex -= temp3.getNumTimes();
                    break;
                    
                case GET:
                    returnTree.append(n.incNode(relativeIndex));
                    relativeIndex = 0;
                    returnTree.append(n);
                    break;
                    
                case PUT:
                    Node.PutNode temp4 = (Node.PutNode)n;
                    returnTree.append(n.putNode(relativeIndex));
                    break;
                    
                case LOOP:
                    returnTree.append(n.incNode(relativeIndex));
                    relativeIndex = 0;
                    nest = true;
                    n.addNode(n.incNode(relativeIndex));
                    returnTree.append(pass(n.toTree()));
                    break;
                    
                case CLEAR:
                    Node.ClearNode temp5 = (Node.ClearNode)n;
                    returnTree.append(n.clearNode(relativeIndex));
                    break;
                    
                default:
                    System.err.println("Unrecognized node: " + n.getType());
            }
        }
        nest = false;
        returnTree.append(no.incNode(relativeIndex));
        return returnTree;
    }
    
}
