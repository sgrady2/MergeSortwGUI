package com.reader;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Reader extends JFrame{
	private Container container; //define container
	private GridBagLayout layout; //define layout
	GridBagConstraints constraints;
	private JLabel telephoneLabel;
	private JTextField telephoneField;
	private JLabel fnameLabel;
	private JTextField firstNameField;
	private JLabel lnameLabel;
	private JTextField lastNameField;
	private JButton nextbtn;
	private JButton previousbtn;
	ArrayList<String[]> data=new ArrayList<String[]>();
	int current=0;
	
	public Reader(){
		super("Reader");
		
		container = getContentPane();
		layout = new GridBagLayout();
		container.setLayout(layout);
		constraints = new GridBagConstraints();	
		
		
		telephoneLabel=new JLabel("Telephone");
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=1; 
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.BOTH; //fill space
		constraints.weightx=50;
		constraints.weighty=50; 		
		layout.setConstraints(telephoneLabel,constraints);
		container.add(telephoneLabel);
		
		
		telephoneField=new JTextField(); 
		constraints.gridx=1;
		constraints.gridy=0;
		constraints.gridwidth=1; 
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.BOTH; 
		constraints.weightx=100;
		constraints.weighty=50; 
		layout.setConstraints(telephoneField,constraints);
		container.add(telephoneField);
		
		fnameLabel=new JLabel("First name");
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=1; 
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.BOTH; 
		constraints.weightx=50;
		constraints.weighty=50; 		
		layout.setConstraints(fnameLabel,constraints);
		container.add(fnameLabel);
		
		
		firstNameField=new JTextField(); 
		constraints.gridx=1;
		constraints.gridy=1;
		constraints.gridwidth=1; 
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.BOTH;
		constraints.weightx=100;
		constraints.weighty=50; 
		layout.setConstraints(firstNameField,constraints);
		container.add(firstNameField);
		
		lnameLabel=new JLabel("Last name");
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=1; 
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.BOTH; 
		constraints.weightx=50;
		constraints.weighty=50; 		
		layout.setConstraints(lnameLabel,constraints);
		container.add(lnameLabel);
		
		
		lastNameField=new JTextField(); 
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.gridwidth=1; 
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.BOTH; 
		constraints.weightx=50;
		constraints.weighty=50; 
		layout.setConstraints(lastNameField,constraints);
		container.add(lastNameField);
		
		readFile();
		
		previousbtn=new JButton("Previous");
		previousbtn.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
            	current--; //Range check is extra work
            	showData();
            }
        });      
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.gridwidth=1; 
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.BOTH; 
		constraints.weightx=50;
		constraints.weighty=50; 		
		layout.setConstraints(previousbtn,constraints);
		container.add(previousbtn);
		
		
		nextbtn=new JButton("next");
		nextbtn.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	current++; //Range check is extra work
            	showData();
            }
        });
		constraints.gridx=1;
		constraints.gridy=3;
		constraints.gridwidth=1; 
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.BOTH; 
		constraints.weightx=50;
		constraints.weighty=50; 
		layout.setConstraints(nextbtn,constraints);
		container.add(nextbtn);
		
		
		setSize(400,200); 
		setVisible(true);
		
		showData();
	}
	
	
	
	
	
	private void showData(){
		String[] dataLine=data.get(current);
		telephoneField.setText(dataLine[0]);
		firstNameField.setText(dataLine[2]);
		lastNameField.setText(dataLine[1]);
	}
	
	
	
	
	private void readFile(){		
	    try {
	    	URL dataSrc = new URL(
	    			"https://www.cs.uoregon.edu/Classes/14F/cis212/assignments/phonebook.txt");
	    URLConnection conn = dataSrc.openConnection();
	    	BufferedReader br = new BufferedReader(
	    			new InputStreamReader(conn.getInputStream())); 
	    	
	    	//BufferedReader br = new BufferedReader(
	    	//		new FileReader("User/mkkspg/Desktop/212/phone.txt"));
	    	
	        String line = br.readLine();
	        
	        while (line != null) {
	        		String[] parts=line.split(" ");
	        		data.add(parts);
	            line = br.readLine();
	        }	       
	        br.close();
	    }
	    catch(Exception e){
	    	System.err.print("File reading error!");
	    	System.err.print(e.getMessage());
	    }
	    
	}
	
	public static void main(String[] args) {
		Reader r = new Reader();
		r.setResizable(false);
		r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}