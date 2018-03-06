
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
	
	public void setName(String name) {
		_name = name;
	}
	
	public void setLastName(String lastName) {
		_lname = lastName;
	}
	
	public void setDept(String dept) {
		_dept = dept;
	}
	
	public void setPhone(String phone) {
		_phone = phone;
	}
	
	public String getName() {
		return _name;
	}
	
	public String getLastName() {
		return _lname;
	}
	
	public String getDept() {
		return _dept;
	}
	
	public String getPhone() {
		return _phone;
	}
	
	@Override
	public String toString() {
		return _lname + ", " + _name + " " + _dept + " " + _phone;
	}
	
}
