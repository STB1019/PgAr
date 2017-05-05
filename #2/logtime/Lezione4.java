/**
 * 
 */
package com.unibs.ieeesb.pgar;

/**
 * @author RAD
 *
 */
public class lezione4 {
	public static void main(String [] args){
		if(args.length<=0){
			System.err.println("Set limit to count");
			return ;
		}
		int limit=(int) 1e8;
		LogTime logtime=new LogTime();
		logtime.getTime();
		for(int i=0; i<limit; i++);
		logtime.getTime();
		for(int i=0; i<limit; i++){
			if (i==1e2)System.out.println("1e2\t"+logtime.showTime());
			if (i==1e4)System.out.println("1e4\t"+logtime.showTime());
			if (i==1e6)System.out.println("1e6\t"+logtime.showTime());
		}
		logtime.getTime();
		for(int i=0; i<limit; i++){
			if (i==1e2)System.err.println("1e2\t"+logtime.showTime());
			if (i==1e4)System.err.println("1e4\t"+logtime.showTime());
			if (i==1e6)System.err.println("1e6\t"+logtime.showTime());
		}
		logtime.getTime();
		for(int i=0; i<limit; i++){
			System.err.println(i);
			if (i==1e2)System.err.println("1e2\t"+logtime.showTime());
			if (i==1e4)System.err.println("1e4\t"+logtime.showTime());
			if (i==1e6)System.err.println("1e6\t"+logtime.showTime());
		}
		logtime.getTime();
		for(int i=0; i<limit; i++){
			System.out.println(i);
			if (i==1e2)System.err.println("1e2\t"+logtime.showTime());
			if (i==1e4)System.err.println("1e4\t"+logtime.showTime());
			if (i==1e6)System.err.println("1e6\t"+logtime.showTime());
		}
		logtime.getTime();
		logtime.getDelta(LogTime.MILLI);
		logtime.getDelta(LogTime.SECOND);
		logtime.getDelta(LogTime.MINUTE);
	}
}

