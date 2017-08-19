package test;
 
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;
import tutorial.Solution;

public class tSolution {
    ByteArrayOutputStream baos;
    PrintStream ps,old;
	private void init(){
	    baos = new ByteArrayOutputStream();
	    ps = new PrintStream(baos);
	    old = System.out;
	    System.setOut(ps);
	}
	
	@Test
	public void sample(){
		init();
        Solution.main(insertCSV("src/test/input"));
        assertTrue("Output non corretto", checkSol(insertCSV("src/test/output")));
	}
	/**
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	private String[] insertCSV(String file){
		return insertCSV(new File(file));
	}
	/**
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	private String[] insertCSV(File file){
		ArrayList<String> args= new ArrayList<String>();
		try{
		Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
        	args.add(scanner.next());
        	System.setIn(new ByteArrayInputStream(args.get(args.size()-1).getBytes(StandardCharsets.UTF_8)));
        }
        scanner.close();
		}
		catch(Exception e){
			System.err.println(e);
			args.add("Errore");
		}
		String []temp=new String[args.size()];
		for(int i=0; i< temp.length;i++){
			temp[i]=args.get(i);
		}
		return temp;
	}
	/**
	 * 
	 * @param args
	 * @return
	 */
	private boolean checkSol(String []args){
		String []value=new String[baos.toString().split("\n").length];
		String []tmp=new String[args[0].toString().split("\n").length];
		tmp=args[0].toString().split("\n");
    	value=baos.toString().split("\n");
	    System.out.flush();
	    System.setOut(old);
	    if(args[0].split("\n").length!=value.length)
	    	return false;
	    for(int i=0;i<value.length;i++)
	    	if(value[i].compareTo(tmp[i])!=0)	
	    		return false;
		return true;
	}

}
