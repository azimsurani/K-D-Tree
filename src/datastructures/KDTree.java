package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class KDTree {
	
	private int n;
	
	private List<String> fields;
	
	Node root=null;
	
	public KDTree(int n) {
		this.n = n;
		fields = new ArrayList<>(n);
	}
	
	public void addField(String field) {
		fields.add(field);
	}
	
	public List<String> getFields() {
		return fields;
	}

	public Boolean insertRecord(Record record) {
		
		if(root==null) {
			root = new Node(true);
			root.insertRecord(record);
			return true;
		}
		
		Map<String,Integer> data = record.getData();
		
		Node temp = root;
		Node parent = null;
		int depth = 0;
		String flag = null;
		
		while(temp!=null && !temp.isLeaf()) {
			
			NodeIdentifier identifier = temp.getIdentifier();
			
			parent = temp;
			
			if(data.get(identifier.getKey()) < identifier.getValue()) {
				temp=temp.left;
				flag="left";
			}else {
				temp = temp.right;
				flag="right";
			}
			depth++;
			
		}
		
		if(temp==null) {
			
			if(flag.equals("left")) {
				parent.left = new Node(true);
				parent.left.insertRecord(record);
			}else {
				parent.right = new Node(true);
				parent.right.insertRecord(record);
			}
		}else {
			
			if(temp.getDataSize()<2) {
				temp.insertRecord(record);
			}else {
				
				String newKey = fields.get(depth%n);
				
				Record[] leafData = temp.getRecords();
				
				//Find Median Value
				int a = data.get(newKey);
				int b = leafData[0].getData().get(newKey);
				int c = leafData[1].getData().get(newKey);
				
				int array[] = {data.get(newKey),  leafData[0].getData().get(newKey),  leafData[1].getData().get(newKey)};
				
				Arrays.sort(array);
				
				if(a==array[0]) {
					
					if(b==array[1]) {
						// a < b < c
						temp = new Node(false); // Create new node with median identifier key value
						temp.setIdentifier(newKey, b);
						
						temp.left = new Node(true);
						temp.left.insertRecord(record);
						
						temp.right = new Node(true);
						temp.right.insertRecord(leafData[0]);
						temp.right.insertRecord(leafData[1]);
					}else {
						// a < c < b
						temp = new Node(false); // Create new node with median identifier key value
						temp.setIdentifier(newKey, c);
						
						temp.left = new Node(true);
						temp.left.insertRecord(record);
						
						temp.right = new Node(true);
						temp.right.insertRecord(leafData[0]);
						temp.right.insertRecord(leafData[1]);
					}
				}else if(b==array[0]) {
					
					if(a==array[1]) {
						//  b < a < c
						temp = new Node(false); // Create new node with median identifier key value
						temp.setIdentifier(newKey, a);
						
						temp.left = new Node(true);
						temp.left.insertRecord(leafData[0]);
						
						temp.right = new Node(true);
						temp.right.insertRecord(record);
						temp.right.insertRecord(leafData[1]);
					}else {
					//  b < c < a
						temp = new Node(false); // Create new node with median identifier key value
						temp.setIdentifier(newKey, c);
						
						temp.left = new Node(true);
						
						temp.left.insertRecord(leafData[0]);
						
						temp.right = new Node(true);
						temp.right.insertRecord(leafData[1]);
						temp.right.insertRecord(record);						
					}
				}else {
					
					if(a==array[1]) {
						//  c < a < b
						temp = new Node(false); // Create new node with median identifier key value
						temp.setIdentifier(newKey, a);
						
						temp.left = new Node(true);
						temp.left.insertRecord(leafData[1]);
						
						temp.right = new Node(true);
						temp.right.insertRecord(record);
						temp.right.insertRecord(leafData[0]);
					}else {
						//  c < b < a
						temp = new Node(false); // Create new node with median identifier key value
						temp.setIdentifier(newKey, b);
						
						temp.left = new Node(true);
						temp.left.insertRecord(leafData[1]);
						
						temp.right = new Node(true);
						temp.right.insertRecord(leafData[0]);
						temp.right.insertRecord(record);
					}
					
				}
				
			}
			
			if(parent==null) {
				root = temp;
			}else {
				if(flag.equals("left")) {
					parent.left = temp;
				}else {
					parent.right = temp;
				}
			}
			
		}
		
		
		return true;
		
	}
	
	public Node getRoot() {
		return root;
	}
	
	/* Given a binary tree, print its nodes in inorder*/
    public void printInorder(Node node)
    {
        if (node == null)
            return;
 
        /* first recur on left child */
        printInorder(node.left);
 
        /* then print the data of node */
        System.out.print(node+ " <-> ");
 
        /* now recur on right child */
        printInorder(node.right);
    }
	

}
