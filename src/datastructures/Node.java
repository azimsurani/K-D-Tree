package datastructures;

public class Node{
	
	private Boolean isLeaf;
	
	private NodeIdentifier identifier;
	
	private Record[] leafData;
	
	private int dataSize=0;
	
	Node left;
	Node right;
	
	public Node(Boolean isLeaf){
		
		this.isLeaf = isLeaf;
		
		if(isLeaf) {
			leafData = new Record[Constants.MAX_DATA_PER_LEAF];
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
	
	public Record[] getRecords() {
		return this.leafData;
	}
	
	public void insertRecord(Record record) {
		if(dataSize<Constants.MAX_DATA_PER_LEAF) {
			leafData[dataSize] = record;
			dataSize++;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(isLeaf) {
			String output = "["+this.leafData[0] + "]";
			for(int i=1;i<this.dataSize;i++) {
				output+=  ",["+this.leafData[i] + "]";
			}
			return output;
		}
		else
			return this.identifier.toString();
	}
	
}
