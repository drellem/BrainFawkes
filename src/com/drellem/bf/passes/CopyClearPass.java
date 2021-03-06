/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.passes;

import com.drellem.bf.ASTree;
import com.drellem.bf.Node;
import com.drellem.bf.Node.NodeType;

/**
 * Converts <code>[-]</code> and <code>[+]</code> to a <code>ClearNode</code>.
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class CopyClearPass implements OpPass{
    private ASTree returnTree = new ASTree();
    
    @Override
    public ASTree pass(ASTree tree) {
        while(tree.hasNext()){
            Node n = tree.getNext();
            
            switch(n.getType()){
                
                case LOOP:
                    if(n.numberOfChildren()==1){
                        if(n.childNodes.get(0) instanceof Node.PlusNode || n.childNodes.get(0) instanceof Node.MinusNode){
                            returnTree.append(n.clearNode(0));
                        } else returnTree.append(n);
                    } else returnTree.append(n);
                    break;
                    
                    
                default:
                    returnTree.append(n);
                    
            }
        }
        return returnTree;
    }

}
