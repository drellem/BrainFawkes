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
public class Node {
    public ArrayList<Node> childNodes = new ArrayList<Node>();
    public void addNode(Node n){ childNodes.add(n); }
    public void addTree(ASTree tree){
        while(tree.hasNext()){
            addNode(tree.getNext());
        }
    }
    public ASTree toTree(){
        ASTree ret = new ASTree();
        for(Node n : childNodes){
            ret.append(n);
        }
        return ret;
    }
    public int numberOfChildren(){ return childNodes.size(); }
    public NodeType getType(){ return NodeType.NONE; }
    
    public PlusNode plusNode(int relativeCell, int numTimes){ return new PlusNode(relativeCell, numTimes); }
    public MinusNode minusNode(int relativeCell, int numTimes){ return new MinusNode(relativeCell, numTimes); }
    public IncNode incNode(int numTimes){ return new IncNode(numTimes); }
    public DecNode decNode(int numTimes){ return new DecNode(numTimes); }
    public GetNode getNode(int relativeCell){ return new GetNode(relativeCell); }
    public PutNode putNode(int relativeCell){ return new PutNode(relativeCell); }
    public LoopNode loopNode(){ return new LoopNode(); }
    public ClearNode clearNode(int relativeCell){ return new ClearNode(relativeCell); }
    
    
    public class PlusNode extends Node {
        private int relativeCell, numTimes;
        public PlusNode(int relativeCell, int numTimes){
            this.relativeCell = relativeCell;
            this.numTimes = numTimes;
        }
        @Override
        public NodeType getType(){ return NodeType.PLUS; }
        public int getRelativeCell(){ return relativeCell; }
        public int getNumTimes(){ return numTimes; }
    }
    
    public class MinusNode extends Node {
        private int relativeCell, numTimes;
        public MinusNode(int relativeCell, int numTimes){
            this.relativeCell = relativeCell;
            this.numTimes = numTimes;
        }
        @Override
        public NodeType getType(){ return NodeType.MINUS; }
        public int getRelativeCell(){ return relativeCell; }
        public int getNumTimes(){ return numTimes; }
    }
    
    public class IncNode extends Node {    
        private int numTimes;
        public IncNode(int numTimes){
            this.numTimes = numTimes;
        }
        @Override
        public NodeType getType(){ return NodeType.INC; }
        public int getNumTimes(){ return numTimes; }
    }
    
    public class DecNode extends Node {
        private int numTimes;
        public DecNode(int numTimes){
            this.numTimes = numTimes;
        }
        @Override
        public NodeType getType(){ return NodeType.DEC; }
        public int getNumTimes(){ return numTimes; }
    }
    
    public class GetNode extends Node {
        private int relativeCell;
        public GetNode(int relativeCell){
            this.relativeCell = relativeCell;
        }
        @Override
        public NodeType getType(){ return NodeType.GET; }
        public int getRelativeCell(){ return relativeCell; }
    }
    
    public class PutNode extends Node {
        private int relativeCell;
        public PutNode(int relativeCell){
            this.relativeCell = relativeCell;
        }
        @Override
        public NodeType getType(){ return NodeType.PUT; }
        public int getRelativeCell(){ return relativeCell; }
    }
    
    public class LoopNode extends Node {
        public boolean interpretable(){
            for (Node n : childNodes){
                if(n instanceof GetNode)return false;
                if (n instanceof LoopNode){
                    LoopNode ni = (LoopNode)n;
                    if(!ni.interpretable())return false;
                }
            }
            return true;
        }
        @Override
        public NodeType getType(){ return NodeType.LOOP; }
    }
    
    public class ClearNode extends Node {
        private int relativeCell;
        public ClearNode(int relativeCell){
            this.relativeCell = relativeCell;
        }
        @Override
        public NodeType getType(){ return NodeType.CLEAR; }
        public int getRelativeCell(){ return relativeCell; }
    }
    
    public enum NodeType {
        PLUS, MINUS, INC, DEC, GET, PUT, LOOP, CLEAR, NONE
    }
}
