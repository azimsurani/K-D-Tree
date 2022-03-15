package datastructures;

import java.util.ArrayList;
import java.util.List;

public class Node{
	
	private Boolean isLeaf;
	
	private NodeIdentifier identifier;
	
	private List<Record> leafData;
	
	private int dataSize=0;
	
	Node left;
	Node right;
	
	public Node(Boolean isLeaf){
		
		this.isLeaf = isLeaf;
		
		if(isLeaf) {
			leafData = new ArrayList<>(Constants.MAX_DATA_PER_LEAF);
		}

	}
	
	public Boolean isLeaf() {
		return isLeaf;
	}
	
	public void setIdentifier(String key,int value) {
		this.identifier = new NodeIdentifier(key, value);
	}
	
	public NodeIdentifier getIdentifier() {
		return identifier;
	}
	
	public int getDataSize() {
		return dataSize;
	}
	
	public List<Record> getRecords() {
		return this.leafData;
	}
	
	public void insertRecord(Record record) {
		if(dataSize<Constants.MAX_DATA_PER_LEAF) {
			leafData.add(record);
			dataSize++;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(isLeaf) {
			String output = "["+this.leafData.get(0) + "]";
			for(int i=1;i<this.dataSize;i++) {
				output+=  ",["+this.leafData.get(i) + "]";
			}
			return output;
		}
		else
			return this.identifier.toString();
	}
	
	public Boolean removeRecord(Record record) {
		if(leafData.contains(record)) {
			leafData.remove(record);
			this.dataSize--;
			return true;
		}
		return false;
	}
	
}
