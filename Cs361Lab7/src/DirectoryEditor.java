import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;

public class DirectoryEditor {

	public static void main(String[] args) {

		DirectoryProxy DirProxy = new DirectoryProxy();
		ArrayList<Employee> collection = new ArrayList<Employee>();
		Scanner stdIn = new Scanner(System.in);
		Gson gson = new Gson();
		String input;
		String[]data;

		System.out.println("-- Hello --\n");

		// get input type ('c' or 'f')
		do {
			System.out.print("[C]onsole or [F]ile: ");
			input = stdIn.next();
		}while(!(equal(input, "C") || equal(input, "F")));

		// Console
		if(equal(input, "C")){

			do {
				
				System.out.print("Enter command (or exit): ");
				input = stdIn.next();

				if(equal("add", input)) {

					while(true) {

						input = stdIn.nextLine();		// get next line
						data = input.split(" ");		// split input string into employee data

						if(data.length == 4) 			// if valid Employee data
							collection.add(new Employee(data[0], data[1], data[2], data[3]));

						if(equal(input, "END")){
														// Convert to Json & add it to Directory
							DirProxy.add(gson.toJson(collection));
							break;
						}
					}
				}

				if(equal("Print", input))				// Print
					DirProxy.print();

				if(equal("Clear", input))				// Clear
					DirProxy.clear();

			}while(!equal("Exit",input));				// Exit

		}

		// File
		else {
			
			System.out.print("Name of file: ");
			input = stdIn.nextLine();
			
			try {
				stdIn = new Scanner(new File(input));
			} catch (FileNotFoundException e) {
				System.out.println("File not Found!!!");
				e.printStackTrace();
			}
			
			while(stdIn.hasNextLine()) {
				
				input = stdIn.nextLine();

				if(equal("add", input)) {

					while(stdIn.hasNextLine()) {

						input = stdIn.nextLine();		// get next line
						data = input.split(" ");		// split input string into employee data

						if(data.length == 4) 			// if valid Employee data
							collection.add(new Employee(data[0], data[1], data[2], data[3]));

						if(equal(input, "END")){
														// Convert to Json & add it to Directory
							DirProxy.add(gson.toJson(collection));
							break;
						}
					}
				}

				if(equal("Print", input))				// Print
					DirProxy.print();

				if(equal("Clear", input))				// Clear
					DirProxy.clear();
			}
		}
		
		System.out.println("\n-- Goodbye --");
		stdIn.close();
		
	}

	private static boolean equal(String s, String t) {
		return s == null? false: s.equalsIgnoreCase(t); 
	}
}
