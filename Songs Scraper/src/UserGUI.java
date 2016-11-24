import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

//*********************************************************************
//* *
//* CIS611 Fall Session 2016 Niraj Shah Aman Arora*
//* *
//* Program Assignment PP04 *
//* *
//* Class Description *
//* * Implements the GUI for the user to interact with the system.
//* *
//* Date Created 11/15/2016
//* *
//* Saved in: PP04.java *
//* *
//*********************************************************************

public class UserGUI extends JPanel{

	 private static String fileName;	 
	 private static String nextURL  ="";

	 private JLabel lblStatus;
	
	 private JButton btnScrape;
	 private JButton exitButton;

	private JTextArea textSongInfo;

	private JScrollPane jpInfo;
	
	public UserGUI() {
		// TODO Auto-generated constructor stub
		initGUI();
		performLayout();
		
		
		exitButton.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	System.exit(0);
	            }
	    });
	    
		
		btnScrape.addActionListener( new java.awt.event.ActionListener() {
		        public void actionPerformed(ActionEvent e){
		        	lblStatus.setText("Status: Waiting...");
		       
		        	String baseURL = "http://www.rollingstone.com";
		    		nextURL  = "/music/lists/the-500-greatest-songs-of-all-time-20110407/smokey-robinson-and-the-miracles-the-tracks-of-my-tears-20110526";
		    		
		    		DateFormat df = new SimpleDateFormat("MMddyyyyHHmm");

		    		// Get the date today using Calendar object.
		    		Date today = Calendar.getInstance().getTime();        
		    		// Using DateFormat format method we can create a string 
		    		// representation of a date with the defined format.
		    		String reportDate = df.format(today);
		    		fileName = "output" + reportDate;
		    		
		    		
		    		Scraper scrap = new Scraper(baseURL,nextURL,fileName);
		    		scrap.parseData();
		    			
		    		
		    		
			    		for(int i = 0;i<50;i++){
			    			//System.out.println(scrap.songList[i].toString());
			    			
			    			textSongInfo.append(scrap.songList[i].toString() + "\n");

			    		}
			    		
			    		lblStatus.setText("Website data is scraped");
			    		btnScrape.setEnabled(false);
			    		
		            }
		    });
		
	}

	 private void initGUI(){
		 
		 	lblStatus = new JLabel("Status: Waiting...");
		 	

		 	textSongInfo = new JTextArea(30, 110);
//		 	textSongInfo.setSize(800, 800);
		 	textSongInfo.setEditable(false);
		 	textSongInfo.setLineWrap(true);
		 	textSongInfo.setWrapStyleWord(true);

	 	   jpInfo = new JScrollPane(textSongInfo);
//	 	   jpInfo.setSize(800, 200);

	       btnScrape = new JButton("Scrape Songs");
		   exitButton = new JButton("Close");
	 }
	 
	 private void performLayout(){
		 
		 JPanel top = new JPanel();
	      JPanel center = new JPanel();
	      JPanel bottom = new JPanel();

		 top.setLayout( new FlowLayout(FlowLayout.CENTER,15,10));
		
		top.add(jpInfo);
		
		center.setPreferredSize( new Dimension( 10, 155 ) );
		center.setLayout( new FlowLayout(FlowLayout.CENTER,15,10)); //new GridLayout(2,2));
		center.add(lblStatus);


		  bottom.setLayout( new FlowLayout());
	      bottom.add(btnScrape);
	      bottom.add(exitButton);

      	  setLayout( new BorderLayout());
	      add(top, "North");
	      add(center, "Center");
	      add(bottom, "South");
		 	
	 }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame f = new JFrame("Rolling Stones Magazine list of the 500 Greatest Songs of All time");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = f.getContentPane();
        
        contentPane.add( new UserGUI());
        f.pack();
        f.setSize(1400, 650);
        f.setVisible(true);		
		
		

	}
	}
