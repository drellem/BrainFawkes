/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drellem.bf;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Miller <a href="mailto:gate46dmiller@gmail.com">gate46dmiller@gmail.com</a>
 */
public class Compressor {
    private BufferedReader istream;
    private BufferedWriter ostream;
    private int additiveValue = 0;
    private int lbrackets = 0;
    private int rbrackets = 0;

    public void run(String inputFile, String outputFile) {
        try {
            this.istream = new BufferedReader(new FileReader(inputFile));
            this.ostream = new BufferedWriter(new FileWriter(outputFile));
            /*
            while (i != -1){
                System.out.print((char)i);
                i = istream.read();
            }
            System.out.print(i);
            * */
            interpret(istream.read());
            if(lbrackets != rbrackets)
                System.err.println("Unbalanced brackets: " + lbrackets + " left, " + rbrackets + " right.");
            
            istream.close();
            ostream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Compressor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(Compressor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
            Logger.getLogger(Compressor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void interpret(int ic)throws Exception{
      char i;
      int ibl;
      char c = (char)ic;
      if(ic!=-1){
        switch(c){
            
            case '+':
                additiveValue = 1;
                ibl = istream.read();
                i = (char)ibl;
                while(i != '>' && i != '<' && i != '.' && i != ',' && i != '[' && i != ']' && ibl != -1){
                    switch(i){
                        case '+': additiveValue++; ibl = istream.read(); i = (char)ibl; break;
                        case '-': additiveValue--; ibl = istream.read(); i = (char)ibl; break;
                        default:
                            ibl = istream.read(); i = (char)ibl; break;
                    }
                }
                if(Math.abs(additiveValue)>1){
                if(additiveValue<0){
                    ostream.append(new Integer(Math.abs(additiveValue)).toString() + '-');
                } else if (additiveValue>0){
                    ostream.append(new Integer(additiveValue).toString() + '+');
                }
                } else if(additiveValue==1)ostream.append('+');
                else ostream.append('-');
                interpret(ibl);
                break;
                
            case '-':
                additiveValue = -1;
                ibl = istream.read();
                i = (char)ibl;
                while(i != '>' && i != '<' && i != '.' && i != ',' && i != '[' && i != ']' && ibl != -1){
                    switch(i){
                        case '+': additiveValue++; ibl = istream.read(); i = (char)ibl; break;
                        case '-': additiveValue--; ibl = istream.read(); i = (char)ibl; break;
                        default:
                            ibl = istream.read(); i = (char)ibl; break;
                    }
                }
                if(Math.abs(additiveValue)>1){
                if(additiveValue<0){
                    ostream.append(new Integer(Math.abs(additiveValue)).toString() + '-');
                } else if (additiveValue>0){
                    ostream.append(new Integer(additiveValue).toString() + '+');
                }
                } else if(additiveValue==1)ostream.append('+');
                else ostream.append('-');
                interpret(ibl);
                break;
            
            case '>':
                additiveValue = 1;
                ibl = istream.read();
                i = (char)ibl;
                while(i != '+' && i != '-' && i != '.' && i != ',' && i != '[' && i != ']' && ibl != -1){
                    switch(i){
                        case '>': additiveValue++; ibl = istream.read(); i = (char)ibl; break;
                        case '<': additiveValue--; ibl = istream.read(); i = (char)ibl; break;
                        default:
                            ibl = istream.read(); i = (char)ibl; break;
                    }
                }
                if(Math.abs(additiveValue)>1){
                if(additiveValue<0){
                    ostream.append(new Integer(Math.abs(additiveValue)).toString() + '<');
                } else if (additiveValue>0){
                    ostream.append(new Integer(additiveValue).toString() + '>');
                }
                } else if(additiveValue==1)ostream.append('>');
                else ostream.append('<');
                interpret(ibl);
                break;
            
            case '<':
                additiveValue = -1;
                ibl = istream.read();
                i = (char)ibl;
                while(i != '+' && i != '-' && i != '.' && i != ',' && i != '[' && i != ']' && ibl != -1){
                    switch(i){
                        case '>': additiveValue++; ibl = istream.read(); i = (char)ibl; break;
                        case '<': additiveValue--; ibl = istream.read(); i = (char)ibl; break;
                        default:
                            ibl = istream.read(); i = (char)ibl; break;
                    }
                }
                if(Math.abs(additiveValue)>1){
                if(additiveValue<0){
                    ostream.append(new Integer(Math.abs(additiveValue)).toString() + '<');
                } else if (additiveValue>0){
                    ostream.append(new Integer(additiveValue).toString() + '>');
                }
                } else if(additiveValue==1)ostream.append('>');
                else ostream.append('<');
                //System.out.println("Finished: " + i);
                interpret(ibl);
                break;
                
            case '[': lbrackets++; rbrackets--; //to adjust for fallthrough
            case ']': rbrackets++;
            case ',':
            case '.':
                ostream.append(c); interpret(istream.read()); break;
                
            default:
                interpret(istream.read()); break;
        }
    }
  }
    
}
