package datastructures;

import java.util.HashMap;
import java.util.Map;

public class Record {
	
	private Map<String,Integer> data = new HashMap<>();
	
	public void insert(String key,Integer value) {
		data.put(key, value);
	}
	
	public Map<String,Integer> getData() {
		return data;
	}
	
	public String toString() {
		String output="";
		
		for(Map.Entry<String, Integer> entry : data.entrySet()) {
			String key = entry.getKey();
		    Integer value = entry.getValue();
		    output+= "("+key+","+value+") ";
		}
		return output;
		
	}
}
