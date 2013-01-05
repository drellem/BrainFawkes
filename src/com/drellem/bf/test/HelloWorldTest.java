/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.test;

import com.drellem.bf.*;
import com.drellem.bf.emit.JavaEmitter;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class HelloWorldTest {
    private static Generator generator;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedWriter w = null;
        BufferedReader r = null;
        File f = new File("hello.bc");
        Compressor compressor = new Compressor();
        try {
            f.createNewFile();
            w = new BufferedWriter(new FileWriter("hello.b"));
            r = new BufferedReader(new FileReader("hello.bc"));
            w.write("+++++ +++++             initialize counter (cell #0) to 10"+
"[                       use loop to set the next four cells to 70/100/30/10"+
   " > +++++ ++              add  7 to cell #1"+
    "> +++++ +++++           add 10 to cell #2 "+
    "> +++"+
     "    > +                     add  1 to cell #4"+
    "<<<< -                  decrement counter (cell #0)"+
"]                   "+
"> ++ .                  print 'H'"+
"> + .                   print 'e'"+
"+++++ ++ .              print 'l'"+
".                       print 'l'"+
"+++ .                   print 'o'"+
"> ++ .                  print ' '"+
"<< +++++ +++++ +++++ .  print 'W'"+
"> .                     print 'o'"+
"+++ .                   print 'r'"+
"----- - .               print 'l'"+
"----- --- .             print 'd'"+
"> + .                   print '!'"+
"> .                     print '\n'"  
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
        try {
            //Node n;
            /**
            while(tree.hasNext()){
                n = tree.getNext();
                System.out.println("Type:" + n.getType().toString());
                for (Node no : n.childNodes){
                    System.out.println("->Type:"+no.getType().toString());
                }
            }
            * */
            generator = new Generator(tree, new JavaEmitter("Main.java"), "Main.java");
            generator.generate();
        } catch (IOException ex) {
            Logger.getLogger(HelloWorldTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
