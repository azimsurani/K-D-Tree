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
	
	
	/**
     * Print a tree
     */
    public void print()
    {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<PrintableNode> level = new ArrayList<PrintableNode>();
        List<PrintableNode> next = new ArrayList<PrintableNode>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (PrintableNode n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getText();
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeft());
                    next.add(n.getRight());

                    if (n.getLeft() != null) nn++;
                    if (n.getRight() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<PrintableNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '-' : '-';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }

}
