package com.bsi.ms.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * System.Out输出重定向至Log4j2
 * 
 *
 * @author Xie Shaohu
 */
public class SystemOut2Log4j extends PrintStream {
	
	private Logger log = LogManager.getLogger(SystemOut2Log4j.class);

	private static PrintStream instance = new SystemOut2Log4j(System.out);
	
	/**
	 * @param out
	 */
	public SystemOut2Log4j(OutputStream out) {
		super(out);
	}

	public static void redirectSystemOut() {
        System.setOut(instance);
    }
	
	public PrintStream append(char c) {
        // Ignore
        return this;
    }


    public PrintStream append(CharSequence csq, int start, int end) {
        // Ignore
        return this;
    }


    public PrintStream append(CharSequence csq) {
        // Ignore
        return this;
    }


    public boolean checkError() {
        // Ignore
        return false;
    }


    protected void clearError() {
        // Ignore
    }


    public void close() {
        // Ignore
    }


    public void flush() {
        // Ignore
    }


    public PrintStream format(Locale l, String format, Object... args) {
        // Ignore
        return this;
    }


    public PrintStream format(String format, Object... args) {
        // Ignore
        return this;
    }


    public void print(boolean b) {
        println(b);
    }


    public void print(char c) {
        println(c);
    }


    public void print(char[] s) {
        println(s);
    }


    public void print(double d) {
        println(d);
    }


    public void print(float f) {
        println(f);
    }


    public void print(int i) {
        println(i);
    }


    public void print(long l) {
        println(l);
    }


    public void print(Object obj) {
        println(obj);
    }


    public void print(String s) {
        println(s);
    }


    public PrintStream printf(Locale l, String format, Object... args) {
        // Ignore
        return this;
    }


    public PrintStream printf(String format, Object... args) {
        // Ignore
        return this;
    }


    public void println() {
        // Ignore
    }


    public void println(boolean x) {
    	if (log.isDebugEnabled())
    		log.debug(Boolean.valueOf(x).toString());
    }


    public void println(char x) {
    	if (log.isDebugEnabled())
    		log.debug(Character.valueOf(x).toString());
    }


    public void println(char[] x) {
    	if (log.isDebugEnabled())
    		log.debug(x == null ? null : new String(x));
    }


    public void println(double x) {
    	if (log.isDebugEnabled())
    		log.debug(Double.valueOf(x).toString());
    }


    public void println(float x) {
    	if (log.isDebugEnabled())
    		log.debug(Float.valueOf(x).toString());
    }


    public void println(int x) {
    	if (log.isDebugEnabled())
    		log.debug(Integer.valueOf(x).toString());
    }


    public void println(long x) {
        log.debug(String.valueOf(x));
    }


    public void println(Object x) {
        log.debug(String.valueOf(x));
    }


    public void println(String x) {
        log.debug(x);
    }


    protected void setError() {
        // Ignore
    }


    public void write(byte[] buf, int off, int len) {
        // Ignore
    }


    public void write(int b) {
        // Ignore
    }


    public void write(byte[] b) throws IOException {
        // Ignore
    }
}
