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

	public static void main(String[] args) {

		Controller chronoTimer = new Controller();

		Scanner stdIn = new Scanner(System.in);

		String userInput = "bloop-dee-doop";
		while (!(userInput.equalsIgnoreCase("f") || userInput.equalsIgnoreCase("c"))) { 
			System.out.print("(f)ile or (c)onsole?:");							// ask for input method
			userInput = stdIn.nextLine();
		}
		
		System.out.println();

		if (userInput.equalsIgnoreCase("c")) { 									// do console prompt input

			String command;
			SimpleDateFormat format;

			while (!userInput.equals("EXIT")) {									// loop until EXIT

				userInput = stdIn.nextLine();
				
				format = new SimpleDateFormat("hh:mm:ss");
				command = format.format(new Date()) + "	" + userInput;			// prepend time to command

				chronoTimer.execute(command);									// execute command

			}

		}
		else { 																	// do file read input
			System.out.print("Enter file name: ");								// prompt for file name
			String fileName = stdIn.next();
			Scanner fileScanner;
			try {
				
				fileScanner = new Scanner(new File(fileName));					// open scanner for file

				while (fileScanner.hasNext()) {									// loop until EXIT
					userInput = fileScanner.nextLine();
					System.out.println(userInput);								// print command
					
					if (userInput.endsWith("EXIT"))								// EXIT command
						break;
					
					chronoTimer.execute(userInput);								// try to execute command

				}
				fileScanner.close();

			} catch (FileNotFoundException e) {
				System.out.println("Could not open read file");
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		chronoTimer.powerOFF();												// power off and exit simulation
		System.out.println("\nSIMULATION TERMINATING");						// simulation end
		stdIn.close();	
	}

}
