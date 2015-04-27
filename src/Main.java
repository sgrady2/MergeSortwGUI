import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class Main {
	private static JLabel _label1;
	private static JLabel _label2;
	private static ArrayList<PhonebookEntries> fin = new ArrayList<PhonebookEntries>();
	//private static int count = 0;

	public static void main(String[] args) {
		//call readFile to get data to be sorted and buttons for sorting
		readFile();
		System.out.print(mergeSort(readFile()));
		JFrame frame = new JFrame("CIS 212");
		frame.setLayout(new FlowLayout());
		JButton selectionSortButton = new JButton("Selection Sort");
		selectionSortButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				long startTime = System.nanoTime();
				selectionSort(fin);
				long elapsedTime = System.nanoTime() - startTime;
				String longToString2 = String.valueOf(elapsedTime/1000000000);
				_label1.setText(longToString2);
			}
		});
		frame.add(selectionSortButton);
		_label1 = new JLabel("Nothing yet");
		frame.add(_label1);
		JButton mergeSortButton = new JButton("Merge Sort");
		mergeSortButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				long startTime1 = System.nanoTime();
				mergeSort(fin);
				long elapsedTime1 = System.nanoTime() - startTime1;
				String longToString = String.valueOf(elapsedTime1/1000000000);
				_label2.setText(longToString);
			}
		});
		frame.add(mergeSortButton);
		
		
		_label2 = new JLabel("Nothing yet");
		frame.add(_label2);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
		
	
	
	public static ArrayList<PhonebookEntries> readFile(){	
		ArrayList<String[]> data=new ArrayList<String[]>();
	    try {
	    	URL dataSrc = new URL(
	    			"https://www.cs.uoregon.edu/Classes/14F/cis212/assignments/phonebook.txt");
	    URLConnection conn = dataSrc.openConnection();
	    	BufferedReader br = new BufferedReader(
	    			new InputStreamReader(conn.getInputStream())); 
	    	
	    	//BufferedReader br = new BufferedReader(
	    	//		new FileReader("User/mkkspg/Desktop/212/phone.txt"));
	    	
	        String line = br.readLine();
	        //going through the file and reading lines until there are no more or we reach null
	        
	        while (line != null) {
	        		String[] parts=line.split(" ");
	        		
	            line = br.readLine();
	            data.add(parts);
	            
	            
	            	
	            }
	        for (String[] strList: data ){
            	
            	PhonebookEntries current = new PhonebookEntries(strList[0], strList[1], strList[2]);
            	fin.add(current);
	            
	            	
	        }	       
	        
	        br.close();
	        
	    }
	    catch(Exception e){
	    	System.err.print("File reading error!");
	    	System.err.print(e.getMessage());
	    }
	    
	    return fin;
	}
	
	//used pseudocode from http://en.wikipedia.org/wiki/Selection_sort
	//
	public static ArrayList<PhonebookEntries> selectionSort(ArrayList<PhonebookEntries> paramEntry){
		int minItem;
		//making a copy of the list so we don't alter the original in memory
		ArrayList<PhonebookEntries> entries = paramEntry;
		
		for (int i = 0; i< entries.size()-1; i++){
			//assuming the first entry is the smallest
			minItem = i;
			
			for (int j = i + 1; j<entries.size();j++){
				if (entries.get(j).getLast().compareTo(entries.get(minItem).getLast()) < 0 ){
					minItem = j;
				}//endif
			}//endfor
		if (minItem != i){
			Collections.swap(entries, i, minItem);
			//swap the elements
			//entries.set(i, entries.get(minItem));
			//entries.set(minItem, entries.get(i));
		}
			
	}
			
		
		return entries;
	}
	//using pseudocode from http://en.wikipedia.org/wiki/Merge_sort
	// also some help from http://softbase.ipfw.edu/~lubo/LearningJava/sourceCode/Chapter%2014/Merge%20Sort/V2/MergeSort.java
	public static ArrayList<PhonebookEntries> mergeSort(ArrayList<PhonebookEntries> entries){
		//make a copy of the array in entries
		//ArrayList<PhonebookEntries> entries = paramEntry;
		
		//keep the middle an integer
		
		//base case
		if (entries.size() <= 1){
			return entries;
			//otherwise i need to separate the list into left and right
		}
			ArrayList<PhonebookEntries> leftSide = new ArrayList<>(entries.size()/2);
			ArrayList<PhonebookEntries> rightSide = new ArrayList<>(entries.size()-leftSide.size());
			int middle = entries.size()/2;
			for (int i=0;i<middle;i++){
				leftSide.add(entries.get(i));
			}
			for (int i=middle;i<entries.size();i++){
				rightSide.add(entries.get(i));
				
			}
			//recursive call sort the lists until they are of size 1
			leftSide = mergeSort(leftSide);
			rightSide = mergeSort(rightSide);
			//merge these and sort with second merge function
			return merge(leftSide, rightSide);
	}
	//creating a new method with return type void to merge the entries of whole using set to change the actual entries of the list
	//help from http://www.codexpedia.com/java/java-merge-sort-implementation/
	public static ArrayList<PhonebookEntries> merge(ArrayList<PhonebookEntries> left1, ArrayList<PhonebookEntries> right1){
		ArrayList<PhonebookEntries> result = new ArrayList<>();
		
		while(left1.size() >0 || right1.size() >0){
			//compare strings using string value of compareTo function, increment counter and set if entry is less than the other side
			if (left1.size()>0 && right1.size()>0){
				//if the left item is less than right I want to add it to my results and remove it from the left list
				if (left1.get(0).getLast().compareTo(right1.get(0).getLast()) < 0 ){
					result.add(left1.get(0));
					left1.remove(left1.get(0));
			//else do the opposite
				}else{
				result.add(right1.get(0));
				right1.remove(right1.get(0));
			} 
			//keep adding until the lists are empty
		}else if(left1.size() >0){
			result.add(left1.get(0));
			result.remove(left1.get(0));

		}else if (right1.size() > 0){
				result.add(right1.get(0));
				result.remove(right1.get(0));
			}
		}
		return result;

	}
	//returns true if the arraylist is sorted, otherwise returns false
	public static boolean isSorted(ArrayList<PhonebookEntries> paramEntry){
		boolean result = true;
		for (int i=0;i<paramEntry.size()-1;i++){
			PhonebookEntries currentElement = paramEntry.get(i);
			PhonebookEntries nextElement = paramEntry.get(i+1);
			//if the current element is smaller than the next element (sorted order), then
			//keep the result stored as true, otherwise return false if there is an opposite case
			if (currentElement.getLast().compareTo(nextElement.getLast()) < 0){
				//do nothing because result is already set to true
			}else{
				return false;
			}
			}//endelse
		return result;
		}//endmethod
}

