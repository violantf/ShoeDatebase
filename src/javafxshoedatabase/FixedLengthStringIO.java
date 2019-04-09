/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filehandler;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 *
 * @author owner
 */
class FixedLengthStringIO {
    
    public static String readFixedLengthString(int size, DataInput in) throws IOException {
        char c[] = new char[size];
        for (int i=0; i<size; i++) {
            c[i] = in.readChar();
        }
        return new String(c);
    }
    
    public static void writeFixedLengthString(String s, int size, DataOutput out) throws IOException {
        char cBuffer[] = new char[size];
        s.getChars(0, s.length(), cBuffer, 0);
        for(int i = s.length(); i < cBuffer.length; i++) {
            cBuffer[i] = ' ';
        }
        String newS= new String(cBuffer);
        out.writeChars(newS);
    }
    
}
