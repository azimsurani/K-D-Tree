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
}
