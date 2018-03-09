import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainDirectory {

	ArrayList<Employee> employees = new ArrayList<Employee>();
	Gson gson = new Gson();

	public void add(String json) {

		employees.addAll(gson.fromJson(json, new TypeToken<ArrayList<Employee>>(){}.getType()));

		// sort by last name (Code shared by Andy since we are now part of the same group).
		Collections.sort(employees ,  new Comparator<Employee>() {
			@Override
			public int compare(Employee s1, Employee s2) {
				int ret = s1.getLastName().compareToIgnoreCase(s2.getLastName());
				if(ret != 0){
					return ret;
				}
				return s1.getFirstName().compareToIgnoreCase(s2.getFirstName());
			}
		});

	}

	public void print() {

		if(employees.isEmpty())
			System.out.println("<Empty Directory>");
		else {
			for (Employee e: employees) {
				System.out.println(e.toString());
			}
		}

	}

	public void clear() {
		employees.clear();
	}

}
