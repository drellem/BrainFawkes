/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.test;

import com.drellem.bf.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class OptTest {
    public static void main(String[] args){
    BufferedWriter w = null;
        BufferedReader r = null;
        File f = new File("hello.bc");
        Compressor compressor = new Compressor();
        try {
            f.createNewFile();
            w = new BufferedWriter(new FileWriter("hello.b"));
            r = new BufferedReader(new FileReader("hello.bc"));
            w.write(">++++++++++ Initialize counter" +
                    "[" +
                    ">+++++ ++" +
                    ">+++++ +++++" +
                    ">++" +
                    ">+" +
                    "<<<<-" +
                    "]" +
                    ">++." +
                    ">+." +
                    "+++++ ++." +
                    "." +
                    "+++." +
                    "----- -." +
                    "----- ---." +
                    ">+." +
                    ">."  
                    );
            w.flush();
            //System.out.print(r.readLine());
            
        } catch (IOException ex) {
            Logger.getLogger(CompressorTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                w.close();
                r.close();
            } catch (IOException ex) {
                System.out.println("Error!");
                Logger.getLogger(CompressorTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        TokenStream t = new Lexer().lex("hello.b");
        Optimizer opt = new Optimizer(t);
        ASTree tree = opt.run();
        Node n;
        /**
        while(tree.hasNext()){
            n = tree.getNext();
            System.out.println("Type:" + n.getType().toString());
            for (Node no : n.childNodes){
                System.out.println("->Type:"+no.getType().toString());
            }
        }
        * */
    }
}
