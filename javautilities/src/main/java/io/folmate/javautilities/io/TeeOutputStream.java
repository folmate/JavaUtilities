package io.folmate.javautilities.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeeOutputStream extends OutputStream {
    public TeeOutputStream(OutputStream... output){
        Collections.addAll(outputs, output);
    }
    final List<OutputStream> outputs = new ArrayList<>();
    @Override
    public void write(int b) throws IOException {
        for (OutputStream out : outputs) {
            out.write(b);
        }
    }
    @Override
    public void write(byte[] b) throws IOException {
        for (OutputStream out : outputs) {
            out.write(b);
        }
    }
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (OutputStream out : outputs) {
            out.write(b, off, len);
        }
    }
    @Override
    public void flush() throws IOException {
        for (OutputStream out : outputs) {
            out.flush();
        }
    }
    @Override
    public void close() throws IOException {
        for (OutputStream out : outputs) {
            out.close();
        }
    }
    public static OutputStream to(File file) {
        try{
            return new FileOutputStream(file);
        } catch (FileNotFoundException e){
            return null;
        }
    }
    public static OutputStream to(File file, boolean append){
        try{
            return new FileOutputStream(file, append);
        } catch (FileNotFoundException e){
            return null;
        }
    }
    public static OutputStream to(String file){
        try{
            return new FileOutputStream(file);
        } catch (FileNotFoundException e){
            return null;
        }
    }
    public static OutputStream to(String file, boolean append){
        try{
            return new FileOutputStream(file,append);
        } catch (FileNotFoundException e){
            return null;
        }
    }
    public static OutputStream stdout(){
        return System.out;
    }
    public static OutputStream stderr(){
        return System.err;
    }
    public static PrintWriter writer(OutputStream... out){
        return new PrintWriter(new TeeOutputStream(out));
    }
    public static PrintWriter writer(boolean autoFlush, OutputStream... out){
        return new PrintWriter(new TeeOutputStream(out), autoFlush);
    }
}
