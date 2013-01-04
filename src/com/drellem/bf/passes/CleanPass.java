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
                    if(lookingFor.equals("inc")){
                        if(numTimes>0){
                            returnTree.append(n.incNode(numTimes));
                        } else if (numTimes < 0) returnTree.append(n.decNode(0-numTimes));
                        lookingFor = "none";
                        numTimes = 0;
                        relativeCell = 0;
                    }
                     //System.out.println("Deleted PLUS");
                    if(lookingFor.equals("plus")){
                        if(relativeCell==temp.getRelativeCell()){
                            System.out.println("Must concatenate!");
                            numTimes += temp.getNumTimes();
                        } else {
                            returnTree.append(n.plusNode(relativeCell, numTimes));
                            relativeCell =temp.getRelativeCell();
                            numTimes = temp.getNumTimes();
                        } 
                    } else {
                        lookingFor="plus";
                        relativeCell=temp.getRelativeCell();
                        numTimes=temp.getNumTimes();
                    }
                    break;
                    
                case MINUS:
                    Node.MinusNode temp1 = (Node.MinusNode)n;
                    if(lookingFor.equals("inc")){
                        if(numTimes>0) returnTree.append(n.incNode(numTimes));
                        else if (numTimes < 0) returnTree.append(n.decNode(0-numTimes));
                        lookingFor = "none";
                        numTimes = 0;
                        relativeCell = 0;
                    }
                    
                     if(lookingFor.equals("plus")){
                        if(relativeCell==temp1.getRelativeCell()){
                            numTimes += temp1.getNumTimes();
                        } else {
                            returnTree.append(n.plusNode(relativeCell, numTimes));
                            relativeCell =temp1.getRelativeCell();
                            numTimes = -temp1.getNumTimes();
                        }
                    } else {
                        lookingFor="plus";
                        relativeCell=temp1.getRelativeCell();
                        numTimes=-temp1.getNumTimes();
                    }
                    break;
                    
                case INC:
                    Node.IncNode temp2 = (Node.IncNode)n;
                    if(lookingFor.equals("plus")){
                        if(numTimes>0) returnTree.append(n.plusNode(relativeCell, numTimes));
                        else if (numTimes<0) returnTree.append(n.minusNode(relativeCell, 0-numTimes));
                        lookingFor = "none";
                        numTimes = 0;
                        relativeCell = 0;
                    }
                    
                        lookingFor = "inc";
                        numTimes += temp2.getNumTimes();
                    
                    break;
                    
                case DEC:
                    Node.DecNode temp3 = (Node.DecNode)n;
                    if(lookingFor.equals("plus")){
                        if(numTimes>0) returnTree.append(n.plusNode(relativeCell, numTimes));
                        else if (numTimes<0) returnTree.append(n.minusNode(relativeCell, 0-numTimes));
                        lookingFor = "none";
                        numTimes = 0;
                        relativeCell = 0;
                    }
                    
                        lookingFor = "inc";
                        numTimes -= temp3.getNumTimes();
                    
                    break;
                    
                case LOOP:
                    Node.LoopNode temp4 = (Node.LoopNode)n;
                    if(lookingFor.equals("plus")){
                        System.err.println("Plus!");
                        if(numTimes>0)returnTree.append(n.plusNode(relativeCell, numTimes));
                        else if (numTimes<0) returnTree.append(n.minusNode(relativeCell, 0-numTimes));
                        
                    } else if (lookingFor.equals("inc")){
                        if(numTimes>0)returnTree.append(n.incNode(numTimes));
                        else if (numTimes<0) returnTree.append(n.decNode(0-numTimes));
                        
                    }
                    lookingFor = "none";
                    numTimes = 0;
                    relativeCell = 0;
                    returnTree.append(pass(n.toTree()));
                    break;
                    
                default:
                    if(lookingFor.equals("plus")){
                        if(numTimes>0)returnTree.append(n.plusNode(relativeCell, numTimes));
                        else if (numTimes<0) returnTree.append(n.minusNode(relativeCell, 0-numTimes));
                        
                    } else if (lookingFor.equals("inc")){
                        if(numTimes>0)returnTree.append(n.incNode(numTimes));
                        else if (numTimes<0) returnTree.append(n.decNode(0-numTimes));
                        
                    }
                    lookingFor = "none";
                        numTimes = 0;
                        relativeCell = 0;
                    //System.err.println("Unrecognized node: " + n.getType());
                    returnTree.append(n);
            }
        }
        if(lookingFor.equals("plus")){
                        if(numTimes>0)returnTree.append(node.plusNode(relativeCell, numTimes));
                        else if (numTimes<0) returnTree.append(node.minusNode(relativeCell, 0-numTimes));
                        
                    } else if (lookingFor.equals("inc")){
                        if(numTimes>0)returnTree.append(node.incNode(numTimes));
                        else if (numTimes<0) returnTree.append(node.decNode(0-numTimes));
                        
                    }
                    lookingFor = "none";
                        numTimes = 0;
                        relativeCell = 0;
                    //System.err.println("Unrecognized node: " + n.getType());
        
        return returnTree;
    }

}
