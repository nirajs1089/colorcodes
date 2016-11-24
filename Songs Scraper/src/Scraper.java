import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
//*********************************************************************
//* *
//* CIS611 Fall Session 2016 Niraj Shah Aman Arora*
//* *
//* Program Assignment PP04 *
//* *
//* Class Description *
//* * Implements the logic of scraping (reading) data from the web link
//* *
//* Date Created 11/15/2016
//* *
//* Saved in: PP04.java *
//* *
//*********************************************************************

public class Scraper {


	private String url;
	private String baseUrl;
	private static String fileName;
	private PrintWriter printWriter;
	
	private java.net.URL urlweb;
	 Scanner input;
	 String line;
	 	 
	
    private boolean flagRanking= false;
	private boolean flagWriter= false;
	private boolean flagProducer= false;
	private boolean flagReleaseMonth= false;
	private boolean flagReleaseYear= false;
	private boolean flagDesc= false;
	private boolean flagNext= false;
	private static String nextURL  ="";
	
    private String strRanking= "";
	private String strWriter= "";
	private String strProducer= "";
	private String strReleaseMonth= "";
	private String strReleaseYear= "";
	private String strDesc= "";
	private String strNext= "";
	
	protected Song[] songList = new Song[50];
	private File file;;
	
	public Scraper(String baseUrl,String url, String fileName) {
		super();
		this.baseUrl = baseUrl;
		this.url = url;
		this.fileName = fileName;
	}
	
	public void connectToWeb(String pUrl){
		
		System.out.println(pUrl);
		
		
		
		try {
			urlweb = new java.net.URL(pUrl);
			input = new Scanner(urlweb.openStream());
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
//		try {
//	          url = new URL(pUrl);
//	         URLConnection urlConnection = url.openConnection();
//	         connection = null;
//	         if(urlConnection instanceof HttpURLConnection) {
//	            connection = (HttpURLConnection) urlConnection;
//	         }else {
//	            System.out.println("Please enter an HTTP URL.");
//	            return;
//	         }	         	      	         	        
//	         
//	      }catch(IOException e) {
//	         e.printStackTrace();
//	      }
	   
	}

	public String readData(){
		
		Song objSng;
		String fileData ="";
		String matched = "";
		 while(input.hasNext()) {
				line =  input.nextLine();
//				if(line.contains("</strong>Art Garfunkel"))
//					System.out.println(line);	
				
				matched = matchType(line);
			 }
		
		
		  try {
			  objSng = new Song(Integer.parseInt(strRanking),strWriter,strProducer,strReleaseMonth+strReleaseYear,strNext,strDesc);
				 songList[(50-Song.remainingObjects)-1]  = objSng;
				 
				 fileData = strRanking + "," + strWriter+ "," + strProducer+ "," + strReleaseMonth+strReleaseYear+ "," + strNext+ "," + strDesc;
				 writeFile(objSng.toString(),fileName + "ToString");
				 writeFile(fileData,fileName);
				 
				resetFlag();
		  } catch (Exception e) {
				// TODO Auto-generated catch block
			  JOptionPane.showMessageDialog(null, "Cannot fetch data from the provided website");
			  System.exit(0);
			}
		  
		  return matched;
	}
	
	private String matchType(String inText){
		
		String matched="";
					
		String patternRanking = "(?<=<h2><span>)[\\d]?\\d.";
		String patternWriter = "(?<=Writer[s]?:[\\s]?</strong>(&#xA0;)?)[a-zA-Z,\\s]*+";
		String patternProducer = "(?<=Producer[s]?:(&#xA0;)?[\\s]?</strong>)[a-zA-z,\\s]+"; //[\\s]*?
		String patternReleaseMonth= "(?<=Released:[\\s]?</strong>)[\\s]*?[a-zA-Z]+";
		String patternReleaseYear= "(?<=&apos;)[\\s]*?\\d\\d";
		String patternDesc = "(?<=<p>).+(?=<a href=)";  //
		String patternNext = "(?<=<li><a href=\").*?(?=\" rel=\"next)";
			
		if(!flagRanking){
			matched = matchPattern(inText,patternRanking);
			if(matched.length()>0){
				flagRanking = true;
				strRanking= matched.replace(".","");
			}
		}
		//*******************************************************
		if(!flagWriter){
			matched = matchPattern(inText,patternWriter);
			if(matched.length()>0){
				flagWriter = true;
				strWriter= matched.trim();
			}
		}
		//*******************************************************
		if(!flagProducer){
			matched = matchPattern(inText,patternProducer);
			if(matched.length()>0){
				flagProducer = true;
				 strProducer= matched.trim();
			}
		}
		//*******************************************************
		if(!flagReleaseMonth){
			matched = matchPattern(inText,patternReleaseMonth);
			if(matched.length()>0){
				flagReleaseMonth = true;
				strReleaseMonth=matched.trim();
			}
		}
		//*******************************************************
		if(!flagReleaseYear){
			matched = matchPattern(inText,patternReleaseYear);
			if(matched.length()>0){
				flagReleaseYear = true;
				strReleaseYear= matched.trim();
			}
		}	
		//*******************************************************
		if(!flagDesc){
			matched = matchPattern(inText,patternDesc);
			if(matched.length()>0){
				flagDesc = true;
				if(matched.length()>15)
					strDesc= matched.substring(0, 15).trim();
				else
					strDesc= matched.trim();
			}
		}
		//*******************************************************		
		if(!flagNext){
			matched = matchPattern(inText,patternNext);
			if(matched.length()>0){
				flagNext = true;
				nextURL = matched;
				strNext= matched.trim();
			}
		}
				
			return matched;	
	}
	
    private String matchPattern(String inText,String strpattern){

		Pattern pattern;
		Matcher matcher;
		String matched="";
		
		 pattern = Pattern.compile(strpattern);  //
		 matcher = pattern.matcher(inText);
		
				while (matcher.find()){
					//System.out.println(matcher.group());
					matched = matcher.group();
				}
				
			return matched;	
	}

    private void resetFlag(){
    	flagRanking= false;
    	flagWriter= false;
    	flagProducer= false;
    	flagReleaseMonth= false;
    	flagReleaseYear= false;
    	flagDesc= false;
    	flagNext= false;
    }

	
	public void writeFile(String trans,String outputFileName) {
		// write/append any new transaction (purchase) data to the data.txt file
		outputFileName = outputFileName + ".txt";
    	 
			try {
				
				File file = new File (outputFileName);
	    	    printWriter = new PrintWriter (new FileOutputStream(outputFileName, true));

				printWriter.println (trans);
				
				 printWriter.close ();   
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "The data cannot be written to the File");
			}
    	    
    	       
	}
	
	protected void parseData(){
			
		
		String baseURL = baseUrl;
		nextURL  = url;
		
		for(int i = 0;i<50;i++){
			connectToWeb(baseURL+nextURL);	
			readData();
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Scraper [url=" + url + ", fileName=" + fileName + "]";
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
