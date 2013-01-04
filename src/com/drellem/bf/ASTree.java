/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

import java.util.ArrayList;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class ASTree {
    private ArrayList<Node> nodes = new ArrayList<Node>();
    int location = -1;
    
    public void append(Node node){ nodes.add(node); }
    public void append(ASTree tree){
        while(tree.hasNext()){
            append(tree.getNext());
        }
    }
    public void setLocation(int location){ this.location = location; }
    public Node getNext(){ return nodes.get(++location); }
    public boolean hasNext(){ return location < nodes.size()-1; }
}
