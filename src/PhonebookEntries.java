
public class PhonebookEntries {
	private String _number;
	private String _first;
	private String _last;
	
	public PhonebookEntries(String number, String first, String last){
		
		_number = number;
		_first = first;
		_last = last;
		
	}
	@Override 
	public String toString(){
		return _number+_first+_last;
	}
	public String getNum(){
		return _number;
	}
	
	public String getLast(){
		return _last;
	}
	public String getFirst(){
		return _first;
	}
	

}
