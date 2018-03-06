
public class Employee {

	private String _name;
	private String _lname;
	private String _dept;
	private String _phone;
	
	
	public Employee(String name, String lastName, String dept, String phone) {
		_name = name;
		_lname = lastName;
		_dept = dept;
		_phone = phone;
	}
	
	@Override
	public String toString() {
		return _lname + ", " + _name + " " + _dept + " " + _phone;
	}
	
}
