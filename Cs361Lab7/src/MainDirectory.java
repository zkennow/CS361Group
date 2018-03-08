import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;

import test.Employee;
import test.Participants;


public class MainDirectory {

	ArrayList<Employee> employees = new ArrayList<Employee>();
	Gson gson = new Gson();
	
	public void add(String json) {
		employees = gson.fromJson(json, new TypeToken<ArrayList<Employee>>(){}.getType());
	}
	
	public void print() {
		int numEmployees = employees.size();
		for (int i = 0; i < numEmployees; i++) {
			System.out.println(employees.getIndex(i).toString());
		}
		System.out.println("");
	}
	
	public void clear() {
		employees.clear();
	}
	
}
