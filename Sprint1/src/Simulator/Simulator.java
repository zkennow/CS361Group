package Simulator;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import ChronoTimer.Controller;
/**
 * This is the simulator for the ChronoTimer
 * 
 * @author Nic
 */
public class Simulator {

	public Simulator() { }

	public static void main(String[] args) {

		Controller c = new Controller();

		Scanner stdIn = new Scanner(System.in);

		String str = "bloop-dee-doop";
		while (!(str.equalsIgnoreCase("f") || str.equalsIgnoreCase("c"))) { // ask for input method
			System.out.print("(f)ile read or (c)ommand prompt?:");
			str = stdIn.nextLine();
		}

		if (str.equalsIgnoreCase("c")) { 									// do command prompt input

			SimpleDateFormat format;

			while (!str.equals("EXIT")) {									// loop until EXIT

				str = stdIn.nextLine();

				if (str.trim().equalsIgnoreCase("EXIT"))					// EXIT command
					break;
				
				format = new SimpleDateFormat("hh:mm:ss");
				str = format.format(new Date()) + " " + str;

				c.execute(str);												// execute command

			}

			c.powerOFF();													// power off and exit simulation

		}
		else { 																// do file read input

			Scanner fileScanner;
			try {
				
				//change file name to match test file
				fileScanner = new Scanner(new File("sampRun.txt"));			// hard code name of test file

				while (fileScanner.hasNext()) {								// loop until EXIT
					str = fileScanner.nextLine();
					System.out.println(str);								// print command
					
					if (str.endsWith("EXIT"))								// EXIT command
						break;
					
					c.execute(str);											// try to execute command

				}
				c.powerOFF();												// power off and exit simulation
				fileScanner.close();

			} catch (FileNotFoundException e) {
				System.out.println("Could not open read file");
				
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Error");
			}

		}
		System.out.println("\nSIMULATION TERMINATING");						// simulation end
		stdIn.close();	
	}

}
