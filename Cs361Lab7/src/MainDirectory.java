import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainDirectory implements Directory {

	private ArrayList<Employee> _employees = new ArrayList<Employee>();
	private Gson _gson = new Gson();

	@Override
	public void add(String json) {

		_employees.addAll(_gson.fromJson(json, new TypeToken<ArrayList<Employee>>(){}.getType()));

		// sort by last name (Code shared by Andy since we are now part of the same group).
		Collections.sort(_employees ,  new Comparator<Employee>() {
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

	@Override
	public void print() {

		if(_employees.isEmpty())
			System.out.println("\n<Empty Directory>\n");
		else {
			System.out.println();
			for (Employee e: _employees) 
				System.out.println(e.toString());
			System.out.println();
		}

	}

	@Override
	public void clear() {
		_employees.clear();
	}

}
