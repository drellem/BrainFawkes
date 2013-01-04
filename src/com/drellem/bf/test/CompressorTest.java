/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf.test;

import com.drellem.bf.Compressor;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class CompressorTest {

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
            w.write("++++++++++ Initialize counter" +
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
        compressor.run("hello.b", "hello.bc");
    }

}
