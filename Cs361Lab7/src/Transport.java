public class Transport {
	
	protected MainDirectory _main = new MainDirectory();

	public Transport(){}

	protected void addJson(String jstr){
		_main.add(jstr);
	}
	
	protected void print() {
		_main.print();
	}
	
	protected void clear() {
		_main.clear();
	}

}
