package datastructures;

public class Node implements PrintableNode{
	
	private Boolean isLeaf;
	
	private NodeIdentifier identifier;
	
	private Record[] leafData;
	
	private int dataSize=0;
	
	Node left;
	Node right;
	
	public Node(Boolean isLeaf){
		
		this.isLeaf = isLeaf;
		
		if(isLeaf) {
			leafData = new Record[2];
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
		if(dataSize<2) {
			leafData[dataSize] = record;
			dataSize++;
		}
	}

	@Override
	public PrintableNode getLeft() {
		// TODO Auto-generated method stub
		return this.left;
	}

	@Override
	public PrintableNode getRight() {
		// TODO Auto-generated method stub
		return this.right;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		if(isLeaf) {
			String output = "["+this.leafData[0] + "]";
			for(int i=1;i<this.dataSize;i++) {
				output+=  "\n["+this.leafData[i] + "]";
			}
			return output;
		}
		else
			return this.identifier.toString();
	}
	
}
