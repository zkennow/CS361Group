public class DirectoryProxy implements Directory {

	private Transport _trans = new Transport();
	
	@Override
	public void add(String JsonString) {
		_trans.addJson(JsonString);
	}
	
	@Override
	public void print() {
		_trans.print();
	}
	
	@Override
	public void clear() {
		_trans.clear();
	}
}
