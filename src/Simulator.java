package Simulator;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import ATM.*;

public class Simulator {
	
	public Simulator() { }
	
	public void display(String str) {
		System.out.println((str));
	}
	
	public static void main(String[] args) {
	
		ATM atm = new ATM(new Simulator());

		Scanner stdIn = new Scanner(System.in);
		String str;

		str = "bloop-dee-doop";
		while (!(str.equalsIgnoreCase("f") || str.equalsIgnoreCase("c"))) {
			System.out.print("(f)ile read or (c)ommand prompt?:");
			str = stdIn.nextLine();
		}

		atm.start();
		
		if (str.equalsIgnoreCase("c")) {
			
			SimpleDateFormat format;
			
			while (!str.equals("EXIT")) {

				atm.getStatement();
				str = stdIn.nextLine();
				format = new SimpleDateFormat("hh:mm:ss");
				str = format.format(new Date()) + " " + str;
				atm.execute(str);
			}
		}
		else {
			//do file read
			try {
				Scanner fileScanner = new Scanner(new File("transactions.txt"));
				
				while (fileScanner.hasNext()) {
					atm.getStatement();
					str = fileScanner.nextLine();
					System.out.println(str);
					atm.execute(str.trim());
				}
				
				fileScanner.close();
				
			} catch (Exception ex) {
				System.out.println("Could not open read file");
			}
			
		}
		stdIn.close();	
	}

}
