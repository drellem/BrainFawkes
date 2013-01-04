/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

import com.drellem.bf.Node.LoopNode;
import com.drellem.bf.Node.NodeType;
import java.util.ArrayList;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class ASTree {
    private ArrayList<Node> nodes = new ArrayList<Node>();
    int location = -1;
    private NodeType type;
    private Node n = new Node();
    
    public void append(Node node){ nodes.add(node); }
    public void append(ASTree tree){
        LoopNode node = n.loopNode();
        while(tree.hasNext())
            node.addNode(tree.getNext());
        nodes.add(node);
    }
    public void setLocation(int location){ this.location = location; }
    public Node getNext(){ return nodes.get(++location); }
    public boolean hasNext(){ return location < nodes.size()-1; }
    public void setType(Node.NodeType type){ this.type = type; }
    public Node.NodeType getType(){ return this.type; }
}
