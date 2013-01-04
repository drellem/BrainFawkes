/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.passes;

import com.drellem.bf.ASTree;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public interface OpPass {
    public ASTree pass(ASTree tree);
}
