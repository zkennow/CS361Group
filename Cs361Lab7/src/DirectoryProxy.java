
public class DirectoryProxy implements Directory {

	Transport _trans = new Transport();
	
	public DirectoryProxy() {}
	
	public void add(String JsonString) {
		_trans.addJson(JsonString);
	}
	
	public void print() {
		_trans._main.print();
	}
	
	public void clear() {
		_trans._main.clear();
	}
}
