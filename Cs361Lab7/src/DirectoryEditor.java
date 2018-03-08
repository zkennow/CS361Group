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
		
		do {

			input = stdIn.nextLine();

			if(equal("add", input)) {

				while(!equal("END", input)) {

					input = stdIn.nextLine();		// get next line
					data = input.split(" ");		// split input string into employee data

					if(data.length == 4) 			// if valid Employee data
						collection.add(new Employee(data[0], data[1], data[2], data[3]));

				}
													// Convert to Json & add it to Directory
				DirProxy.add(gson.toJson(collection));
			}
			
			if(equal("Print", input))				// Print
				DirProxy.print();

			if(equal("Clear", input))				// Clear
				DirProxy.clear();

		}while(!equal("Exit",input));				// Exit
		
		System.out.println("\n-- Goodbye --");
		stdIn.close();
	}
	
	private static boolean equal(String s, String t) {
		return s == null? false: s.equalsIgnoreCase(t); 
	}
}
