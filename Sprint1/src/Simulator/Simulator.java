package Simulator;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import ChronoTimer.Controller;

public class Simulator {
	
	public Simulator() { }
	
	public void display(String str) {
		System.out.println((str));
	}
	
	public static void main(String[] args) {
	
		Controller c = new Controller();
		
		Scanner stdIn = new Scanner(System.in);
		String str;

		str = "bloop-dee-doop";
		while (!(str.equalsIgnoreCase("f") || str.equalsIgnoreCase("c"))) { //ask for input method
			System.out.print("(f)ile read or (c)ommand prompt?:");
			str = stdIn.nextLine();
		}

		
		
		if (str.equalsIgnoreCase("c")) { 									//do command prompt input
			
			SimpleDateFormat format;
			
			while (!str.equals("EXIT")) {									//loop until EXIT

				str = stdIn.nextLine();
				if (str.trim().equalsIgnoreCase("EXIT"))					//EXIT command
					break;
				format = new SimpleDateFormat("hh:mm:ss");
				str = format.format(new Date()) + " " + str;
				
				c.execute(str);												//try to execute command
				
			}
			c.powerOFF();													//power off and exit simulation
		}
		else { 																//do file read input
			try {
				Scanner fileScanner = new Scanner(new File("test.txt")); 	//change file name to match test file
				
				while (fileScanner.hasNext()) {								//loop until EXIT
					str = stdIn.nextLine();
					if (str.endsWith("EXIT"))								//EXIT command
						break;
					c.execute(str);											//try to execute command
			
				}
				c.powerOFF();										//power off and exit simulation
				fileScanner.close();
				
			} catch (Exception ex) {
				System.out.println("Could not open read file");
			}
			
		}
		System.out.println("\nSIMULATION TERMINATING");						//simulation end
		stdIn.close();	
	}

}
