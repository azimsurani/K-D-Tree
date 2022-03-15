package assignment;

import java.util.Scanner;

import datastructures.KDTree;
import datastructures.Record;

public class Driver {
	
	private static int n;
	
	public static Scanner sc = new Scanner(System.in);
	
	public static KDTree kdTree;
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to KD-Tree Project!!");
		
		System.out.println("-----------------------------");
		
		System.out.print("\nPlease enter the total number of dimesions/atrribututes : ");
		
		n = sc.nextInt();
		
		kdTree = new KDTree(n);
		
		for(int i=0;i<n;i++) {
			
			System.out.print("Please enter the name of Field "+(i+1)+" : ");
			
			kdTree.addField(sc.next());
			
		}
		
		System.out.println("--------------------------");
		
		while(true) {
			
			System.out.println("--------------------------");
			
			System.out.println("1. Add a record");
			
			System.out.println("2. Delete a record");
			
			System.out.println("3. Display the tree");
			
			System.out.println("4. Exit");
			
			System.out.println("--------------------------");
			
			System.out.print("Please enter your choice : ");
			
			int choice = sc.nextInt();
			
			switch(choice) {
			
				case 1:
					insertRecord();
					
					break;
					
				case 2:
					deleteRecord();
					
					break;
					
				case 3:
					System.out.println("\nThe inorder traversal for current tree is : ");
					
					kdTree.printInorder(kdTree.getRoot());
					
					System.out.println("\n");
					
					break;
				
				case 4:
					System.out.println("Thanks for using KDTree Project!! Have a good day!!");
					
					sc.close();
					
					System.exit(0);
				
				default:
					System.out.println("Invalid choice! Please try again.");
			}
			
		}
		
		
		
	}

	

	private static void insertRecord() {
		
		Record record = new Record();
		
		System.out.println("Please enter the following data : ");
		
		for(String field: kdTree.getFields()) {
			System.out.print(field + " : ");
			record.insert(field, sc.nextInt());
		}
		
		if(kdTree.insertRecord(record))
			System.out.println("Record was inserted successfully");
		else
			System.out.println("Insertion was not successful. Please try again!!");
		
	}
	
	private static void deleteRecord() {
		
		Record record = new Record();
		
		System.out.println("Please enter the following data : ");
		
		for(String field: kdTree.getFields()) {
			System.out.print(field + " : ");
			record.insert(field, sc.nextInt());
		}
		
		if(kdTree.deleteRecord(record))
			System.out.println("Record was deleted successfully");
		else
			System.out.println("Deletion was not successful. Please try again with valid data!!");
		
	}

}
