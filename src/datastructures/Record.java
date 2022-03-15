package datastructures;

import java.util.LinkedHashMap;
import java.util.Map;

public class Record {
	
	private Map<String,Integer> data = new LinkedHashMap<>();
	
	public void insert(String key,Integer value) {
		data.put(key, value);
	}
	
	public Map<String,Integer> getData() {
		return data;
	}
	
	public String toString() {
		String output="{";
		
		for(Map.Entry<String, Integer> entry : data.entrySet()) {
			String key = entry.getKey();
		    Integer value = entry.getValue();
		    output+= " ("+key+","+value+") ";
		}
		return output+"}";
		
	}
	
	public boolean equals(Object object)
    {
        if (object != null && object instanceof Record )
        {
        	Record record = (Record) object;
        	for(Map.Entry<String, Integer> entry : this.data.entrySet()) {
    			String key = entry.getKey();
    		    if(!record.getData().get(key).equals(entry.getValue())) {
    		    	return false;
    		    }
    		}
        }

        return true;
    }
}
