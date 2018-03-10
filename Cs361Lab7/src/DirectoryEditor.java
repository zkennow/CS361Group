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
		boolean consoleInput = true;
		Gson gson = new Gson();
		String input;
		String[]data;

		System.out.println("-- Hello --\n");

		do {											// get input type ('c' or 'f')
			System.out.print("[C]onsole or [F]ile: ");
			input = stdIn.next();
		}while(!(equal(input, "C") || equal(input, "F")));

		if(equal(input, "F")) {							// if file input
			System.out.print("Name of file: ");			// get name of file
			input = stdIn.next();						

			try {
				stdIn = new Scanner(new File(input));
				consoleInput = false;
			} catch (FileNotFoundException e) {
				stdIn.close();
				System.out.println("File not Found!!!");
				e.printStackTrace();
			}
		}

		while(consoleInput || stdIn.hasNextLine() ) {

			if(consoleInput) 
				System.out.print("Enter command (or exit): ");

			input = stdIn.next();						// get command

			if(equal("Add", input)) {

				if(consoleInput)
					stdIn.nextLine();					// clear enter character

				while(consoleInput || stdIn.hasNextLine()) {

					if(consoleInput)
						System.out.print("Employee (First Last Dept Phone), or (END): ");

					input = stdIn.nextLine().trim();	// get next line

					if(equal(input, "End")){
														// Convert to Json & add it to MainDirectory
						DirProxy.add(gson.toJson(collection));
						collection.clear();				// clear local collection
						break;
					}

					data = input.trim().split(" ");		// split input string into employee data

					if(data.length == 4) 				// if valid Employee data add it to collection
						collection.add(new Employee(data[0], data[1], data[2], data[3]));
				}
			}

			if(equal("Print", input))					// Print
				DirProxy.print();
														// Clear
			if(equal("Clear", input) || equal("clr", input))				
				DirProxy.clear();

			if(equal("Exit", input))					// Exit
				break;

		} 												// end of while

		System.out.println("\n-- Goodbye --");
		stdIn.close();

	}

	private static boolean equal(String s, String t) {
		return s == null? false: s.equalsIgnoreCase(t); 
	}
}
