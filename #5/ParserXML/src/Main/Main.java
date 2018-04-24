package Main;

import java.util.Random;
import java.util.Vector;

import support.Read;
import support.Write;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Read read_xml=new Read();
		read_xml.explore("input.xml");
		
		Vector<User> diary=new Vector<User>();
		for(int i=0;i<5;i++) {
			diary.add(new User());
			diary.lastElement().setName("Gastaldo");
			diary.lastElement().setSurname("Frinzi");
		}
		Write write_xml=new Write();
		try {
			write_xml.write(diary, "output.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
