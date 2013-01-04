/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

import com.drellem.bf.Node.LoopNode;
import com.drellem.bf.Node.NodeType;
import java.util.ArrayList;

/**
 * Represents the abstract syntax tree. This is essentially the same as <code>TokenStream</code> except
 * that loop nodes contain their respective nodes. With tokens "Plus,Loop,Plus,End"
 * the equivalent tree would look like "Plus,Loop[Plus]" and going from node to node would
 * look like "Plus,Loop"
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
    /**Reset the location of the pointer before <code>getNext()</code>*/
    public void setLocation(int location){ this.location = location; }
    public Node getNext(){ return nodes.get(++location); }
    public boolean hasNext(){ return location < nodes.size()-1; }
    /**Useful when the AST is representing a <code>LoopNode</code>.*/
    public void setType(Node.NodeType type){ this.type = type; }
    public Node.NodeType getType(){ return this.type; }
}
