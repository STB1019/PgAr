package test;
import Solution.Solution;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class eXtreme {
	InputStream bk_input,input;
	PrintStream bk_output,output;
	ByteArrayOutputStream outbyte;
	private void init() {
		
	}
	
	@Test
	public void runMain() {
		try {
			InputStream bk_input = System.in;
			InputStream input=new FileInputStream(new File("input"));
			BufferedReader test = new BufferedReader(new FileReader(new File("output")));
		    PrintStream bk_output = System.out;
		    ByteArrayOutputStream outbyte=new ByteArrayOutputStream();
		    PrintStream output = new PrintStream(outbyte);
		    String line="";
		    System.setIn(input);
		    System.setOut(output);
			System.err.println("Main Avviato");
			Solution.main(null);
			System.out.flush();
			System.err.println("Main Terminato");
			System.setOut(bk_output);
		    System.setIn(bk_input);
		    input.close();
		    output.close();
		    for(String s : outbyte.toString().split("\n")) {
		    		line=test.readLine();
		    		if((line==null)||(line.compareTo(s)!=0))
		    			fail();
		    }
		}catch(Exception e) {
			System.err.println("-> "+e.toString());
		}
	}

}
