/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

import java.util.ArrayList;

/**
 * Represents a node of the syntax tree (<code>ASTree</code>).
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
    /**
     * Represents the <code>Node</code>, which is presumably a <code>LoopNode</code>, into a tree.
     * @return 
     */
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
    
    /**
     * <code>+</code>
     */
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
    /**
     * <code>-</code>
     */
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
    /**
     * <code>></code>
     */
    public class IncNode extends Node {    
        private int numTimes;
        public IncNode(int numTimes){
            this.numTimes = numTimes;
        }
        @Override
        public NodeType getType(){ return NodeType.INC; }
        public int getNumTimes(){ return numTimes; }
    }
    /**
     * <code><</code>
     */
    public class DecNode extends Node {
        private int numTimes;
        public DecNode(int numTimes){
            this.numTimes = numTimes;
        }
        @Override
        public NodeType getType(){ return NodeType.DEC; }
        public int getNumTimes(){ return numTimes; }
    }
    /**
     * <code>,</code>
     */
    public class GetNode extends Node {
        private int relativeCell;
        public GetNode(int relativeCell){
            this.relativeCell = relativeCell;
        }
        @Override
        public NodeType getType(){ return NodeType.GET; }
        public int getRelativeCell(){ return relativeCell; }
    }
    /**
     * <code>.</code>.
     */
    public class PutNode extends Node {
        private int relativeCell;
        public PutNode(int relativeCell){
            this.relativeCell = relativeCell;
        }
        @Override
        public NodeType getType(){ return NodeType.PUT; }
        public int getRelativeCell(){ return relativeCell; }
    }
    /**
     * <code>[</code>...<code>]</code>.
     */
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
        
        /**
         * Determines if the loop is a simple loop.
         * @return False if not, and false if pointer propagation has not been done. True otherwise.
         */
        public boolean isSimpleLoop(){
            for(Node n : childNodes){
                if(n instanceof GetNode || n instanceof LoopNode || n instanceof IncNode || n instanceof DecNode)return false;
            }
            return true;
        }
        @Override
        public NodeType getType(){ return NodeType.LOOP; }
    }
    /**
     * Clears the current cell: <code>[-]</code>.
     */
    public class ClearNode extends Node {
        private int relativeCell;
        public ClearNode(int relativeCell){
            this.relativeCell = relativeCell;
        }
        @Override
        public NodeType getType(){ return NodeType.CLEAR; }
        public int getRelativeCell(){ return relativeCell; }
    }
    
    public class PutConstNode extends PutNode {
        public PutConstNode(int relativeCell){ super(relativeCell);}
    }
    
    public class MultNode extends Node {
        private int f1Loc, f2Loc, pLoc;
        private boolean adding;
        /**
         * 
         * @param f1Loc The relative index of the first factor.
         * @param f2Loc The relative index of the second factor.
         * @param pLoc  The relative index to store the product in.
         * @param adding True if index <b>+=</b> f1Loc*f2Loc.
         */
        public MultNode(int f1Loc, int f2Loc, int pLoc, boolean adding){
            this.f1Loc = f1Loc;
            this.f2Loc = f2Loc;
            this.pLoc = pLoc;
            this.adding = adding;
        }
        
        public int getF1(){ return f1Loc; }
        public int getF2(){ return f2Loc; }
        public int getP(){ return pLoc; }
        public boolean adding(){ return adding; }
    }

    public enum NodeType {
        PLUS, MINUS, INC, DEC, GET, PUT, LOOP, CLEAR, NONE
    }
}
