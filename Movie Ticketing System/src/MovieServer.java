import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

/*Author : Niraj Shah Aman Arora
Description : Server*/
public class MovieServer {
		
	private Statement stmt;
	int port;
	
	 private ObjectOutputStream outputToClient;
	 private ObjectInputStream inputFromClient;
	
	Connection connection = null; 
	Statement statement = null;
	
	private ServerSocket serverSocket;
	
	private int thrdCnt = 0;
	private String str;
	
	public MovieServer(int port) {
		this.port = port;
		
		 try {
		      serverSocket = new ServerSocket(8000);
				      System.out.println("Server started ");
		      

		      while (true) {
		        // Listen for a new connection request
		        Socket socketClient = serverSocket.accept();
		        
		     // Create a new thread for the connection
		        HandleAClient task = new HandleAClient(socketClient);
		        
		        thrdCnt ++ ;    
		        System.out.println("Thread No. " + thrdCnt + " is serviced");
		        
		        // Start the new thread
		        new Thread(task).start();
	     
		      }
		    }
		    catch(Exception ex) {
		      ex.printStackTrace();
		    }		    		 
	}
	
	class HandleAClient implements Runnable {
	    private Socket socketThread; // A connected socket

	    /** Construct a thread */
	    public HandleAClient(Socket pSocket) {
	      this.socketThread = pSocket;
	    }

	    /** Run a thread */
	    public void run() {
	    	
	      try {
	        // Create data input and output streams
	        inputFromClient = new ObjectInputStream(
	        		socketThread.getInputStream());
	        outputToClient = new ObjectOutputStream(
	        		socketThread.getOutputStream());
	        
	              	
			        		
		        // Read from input
	        MovieMessage inputMsg = (MovieMessage)inputFromClient.readObject();	
	        		
	        		MovieMessage outputMsg = new MovieMessage();
				       // Object msg = inputFromClient.readObject();
				        //accessDB(msg);
				        
				        initializeDB();
				        
				        if(inputMsg.getMsgAction().equals("create") && inputMsg.getMsgTable().equals("user"))
				        	outputMsg.setMsgRegisteredUser(insertUser(inputMsg.getMsgRegisteredUser()));
				        if(inputMsg.getMsgAction().equals("update") && inputMsg.getMsgTable().equals("user"))
				        	outputMsg.setMsgRegisteredUser(updateUser(inputMsg.getMsgRegisteredUser()));
				        if(inputMsg.getMsgAction().equals("login") && inputMsg.getMsgTable().equals("user"))
				        	outputMsg.setMsgRegisteredUser(viewUser(inputMsg.getMsgRegisteredUser()));				        
				        if(inputMsg.getMsgAction().equals("create") && inputMsg.getMsgTable().equals("reservation")){
				        	
				        	inputMsg.getMsgReservation().getRegUser().setUserID(viewUser(inputMsg.getMsgReservation().getRegUser()).getUserID());
				        	inputMsg.getMsgReservation().getShow().setShowID(viewShowByDate(inputMsg.getMsgReservation().getShow()).getShowID());
				        	inputMsg.getMsgReservation().getShow().getMovie().setMovieID(viewMovieByName(inputMsg.getMsgReservation().getShow().getMovie()).getMovieID());
				        	outputMsg.setMsgReservation(insertReservation(inputMsg.getMsgReservation()));
				        					        	
				        }
				        if(inputMsg.getMsgAction().equals("print") && inputMsg.getMsgTable().equals("reservation"))
				        	outputMsg.setMsgReservation(viewReservation(inputMsg.getMsgReservation()));
				        if(inputMsg.getMsgAction().equals("cancel") && inputMsg.getMsgTable().equals("reservation"))
				        	outputMsg.setMsgReservation(deleteReservation(inputMsg.getMsgReservation()));
			        	if(inputMsg.getMsgAction().equals("display") && inputMsg.getMsgTable().equals("deleteReservation"))  // use max id to get the latest insertion
			        		outputMsg=(viewReservationByUser(inputMsg.getMsgRegisteredUser()));
			        		
				        if(inputMsg.getMsgAction().equals("create") && inputMsg.getMsgTable().equals("show")){
				        	inputMsg.getMsgShow().getMovie().setMovieID(viewMovieByName(inputMsg.getMsgShow().getMovie()).getMovieID());				        	
				        	outputMsg.setMsgShow(insertShow(inputMsg.getMsgShow()));
				        }
			        	if(inputMsg.getMsgAction().equals("display") && inputMsg.getMsgTable().equals("show")){
			        		outputMsg=viewShowList(inputMsg.getMsgShow());
			        	}
			        	if(inputMsg.getMsgAction().equals("display") && inputMsg.getMsgTable().equals("showByMovie")){			        					        	
			        		outputMsg= viewShowByMovieID(viewMovieByName(inputMsg.getMsgShow().getMovie()));
			        		
			        	}
			        	if(inputMsg.getMsgAction().equals("display") && inputMsg.getMsgTable().equals("DeleteShow")){			        					        	
			        		outputMsg= viewDeleteShow(inputMsg.getMsgShow());
			        		
			        	}
			        	//
				        if(inputMsg.getMsgAction().equals("update") && inputMsg.getMsgTable().equals("show")){
				        	inputMsg.getMsgShow().getMovie().setMovieID(viewMovieByName(inputMsg.getMsgShow().getMovie()).getMovieID());				        	
				        	outputMsg.setMsgShow(updateShow(inputMsg.getMsgShow(),"update"));
				        }				        	
		        	    if(inputMsg.getMsgAction().equals("cancel") && inputMsg.getMsgTable().equals("show"))
		        	    	outputMsg.setMsgShow(deleteShow(inputMsg.getMsgShow())); 
				        if(inputMsg.getMsgAction().equals("create") && inputMsg.getMsgTable().equals("movie"))
				        	outputMsg.setMsgMovie(insertMovie(inputMsg.getMsgMovie()));
			        	if(inputMsg.getMsgAction().equals("display") && inputMsg.getMsgTable().equals("movie")){
			        		outputMsg=(viewMovie(inputMsg.getMsgMovie()));
			        	}
			        	if(inputMsg.getMsgAction().equals("display") && inputMsg.getMsgTable().equals("DeleteMovie")){
			        		outputMsg=(viewDeleteMovie(inputMsg.getMsgMovie()));
			        	}

			        	if(inputMsg.getMsgAction().equals("update") && inputMsg.getMsgTable().equals("movie"))
			        		outputMsg.setMsgMovie(updateMovie(inputMsg.getMsgMovie()));
				        if(inputMsg.getMsgAction().equals("cancel") && inputMsg.getMsgTable().equals("movie"))
				        	outputMsg.setMsgMovie(deleteMovie(inputMsg.getMsgMovie()));
				        
						connection.close();
				        				        				        
				        
		        // Write to the file
				        outputToClient.writeObject(outputMsg);
				        System.out.println("A User object is created as " + inputMsg.getMsgAction() + inputMsg.getMsgTable());
			       
			        
			       
			      }
			      catch(IOException e) {
			        System.err.println(e);
			      } 
			      catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			      } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      		finally{
	      			try{
	      				inputFromClient.close();
	      				outputToClient.close();
	      			}
	      			catch(Exception ex){
	      				ex.printStackTrace();
	      			}
	      		}
	    }
	  }
	
	public void initializeDB() throws ClassNotFoundException, SQLException{
		
		 // Load the JDBC driver
			    Class.forName("com.mysql.jdbc.Driver");
			    System.out.println("Driver loaded");

	    // Establish a connection
		    connection = DriverManager.getConnection
		      ("jdbc:mysql://localhost/mydatabase", "root", "");
		    		System.out.println("Database connected");

	}
	
	//***********************************USER ***************************************************
	public RegisteredUser insertUser(RegisteredUser pMsg) throws SQLException{   // create user @ DBA
		
//		Message insertMsg = new Message();
		
		
		try{
			
//			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//			Date myDate = formatter.parse(pMsg.getBirth().toString());
//			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			
			
		// Create a statement	      
//		    String insertQuery = "INSERT INTO `tbluser` (`userID`, `username`, `password`, `fullname`, `email`, `phone`) VALUES "
//		    		+ "(NULL, '" + pMsg.getUserName() +"', '" + pMsg.getUserPass()+"', '" + pMsg.getFullName() +"', '" + pMsg.getEmail() +"', '" + pMsg.getPhoneNo() +"')";
//		    
		 // Create a statement	      
		    String insertQuery = "INSERT INTO `tbluser` (`userID`, `username`, `password`, `fullname`, `email`, `phone`, `role`) VALUES "
		    		+ "(NULL, '" + pMsg.getUserName() +"', '" + pMsg.getUserPass()+"', '" + pMsg.getFullName() +"', '" + pMsg.getEmail() +"', '" + pMsg.getPhoneNo() +"','" + pMsg.getUserType() +"')";
		
	    // Create a PreparedStatement object for executing queries
	    	PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	    	   
	    //Execute the statement
	    	preparedStatement.executeUpdate();
	    	
	    	return pMsg;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Please enter the correct info to insert a user");
			
			return null;
		}
	    	
	    	    	  
	}
	
	public RegisteredUser[] viewUserList(RegisteredUser pMsg) throws SQLException{
		
		 // Create a statement
	    statement = connection.createStatement();
	    RegisteredUser[] viewMsg = null;
	    int viewMsgCount =0;
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "select * FROM `tbluser`";
//	    		+ "where `id`= " + pMsg.getId();
					//  		+ "where lastName "
					//    + " = 'Smith'");

	    // Execute a statement
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)
		    		      + "\t" + resultSet.getString(4)
		    		      + "\t" + resultSet.getString(5)
		    		      + "\t" + resultSet.getString(6)
		  				);
	    		    	   	
//	    	  t = resultSet.getInt("id");
	    	 
	    	  
	    		//String userName, String userPass, String fullName, String email, String phoneNo) {
//	    						char sex, Date birth
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
					viewMsg[viewMsgCount] = new RegisteredUser(
												resultSet.getString("username"),
												resultSet.getString("password"),
												resultSet.getString("fullname"),
												resultSet.getString("email"),
												resultSet.getString("phone"));
					viewMsgCount++;
	    		   }
	    		    return viewMsg;
		
		}
	
	public RegisteredUser viewUserByID(RegisteredUser pMsg) throws SQLException{
		
		 // Create a statement
	    statement = connection.createStatement();
	    RegisteredUser viewMsg = null;
	    int viewMsgCount =0;
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "select * FROM `tbluser`"
	    		+ "where `userID`= " + pMsg.getUserID();
					//  		+ "where lastName "
					//    + " = 'Smith'");

	    // Execute a statement
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)
		    		      + "\t" + resultSet.getString(4)
		    		      + "\t" + resultSet.getString(5)
		    		      + "\t" + resultSet.getString(6)
		  				);
	    		    	   	
//	    	  t = resultSet.getInt("id");
	    	 
	    	  
	    		//String userName, String userPass, String fullName, String email, String phoneNo) {
//	    						char sex, Date birth
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
					viewMsg = new RegisteredUser(
												resultSet.getString("username"),
												resultSet.getString("password"),
												resultSet.getString("fullname"),
												resultSet.getString("email"),
												resultSet.getString("phone"));
					
	    		   }
	    		    return viewMsg;
		
		}
	
	public RegisteredUser viewUser(RegisteredUser pMsg) throws SQLException{  //logs in @Reg user
		
		 // Create a statement
	    statement = connection.createStatement();
	    RegisteredUser viewMsg = null;
	  
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "select * FROM `tbluser`"
	    		+ " where `username`= '" + pMsg.getUserName() + "'" 
				+ " and `password`= '" + pMsg.getUserPass()+"'"
	    		+ " and `role`= '" + pMsg.getUserType()+"'";
					//    + " = 'Smith'");

	    // Execute a statement
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)
		    		      + "\t" + resultSet.getString(4)
		    		      + "\t" + resultSet.getString(5)
		    		      + "\t" + resultSet.getString(6)
		    		      + "\t" + resultSet.getString(7)
		  				);
	    		    	   	
		    	  
		    	  
//	    	  t = resultSet.getInt("id");
	    	 
	    	  
	    		//String userName, String userPass, String fullName, String email, String phoneNo) {
//	    						char sex, Date birth
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
		    	  if(resultSet.getString("username").length() != 0)
					viewMsg = new RegisteredUser(
												resultSet.getString("username"),
												resultSet.getString("password"),
												resultSet.getString("fullname"),
												resultSet.getString("email"),
												resultSet.getString("phone"),
												resultSet.getString("role"));
		    	  viewMsg.setUserID(resultSet.getInt("userID"));
	    		   }
	    
	    if(viewMsg != null)
	    	return viewMsg;
	    else
	    	return null;
		
		}
	
	public RegisteredUser updateUser(RegisteredUser pMsg) throws SQLException{ //Update by @Reg user
		
//		Message updateMsg = new Message();
		
		try{
			
				// Create a statement	   
			    String updateQuery = "UPDATE `tbluser` SET `email`='" + pMsg.getEmail() + "',`password`='" + pMsg.getUserPass() + "',`fullname`='" + pMsg.getFullName() + "',`email`='" + pMsg.getEmail() + "',`phone`='" + pMsg.getPhoneNo() + "'" +
			    					" WHERE `username`= '"+ pMsg.getUserName() + "'";  
			    					  
		    // Create a PreparedStatement object for executing queries
		    	PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		    	   
		    //Execute the statement
		    	preparedStatement.executeUpdate();    	
		    	
		    	return pMsg;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Please enter the correct info to update a User");
			
			return null;
		}
	}
	
	//***********************************SHOW***************************************************
	public Show insertShow(Show pMsg) throws SQLException{//set the movie id before calling this method // create Show @ DBA
		 	
		try{
			
//			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//			Date myDate = formatter.parse(pMsg.getBirth().toString());
//			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			String inDate = "";
			
		    SimpleDateFormat ft = 
	 	    	      new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		    
		    inDate = ft.format(pMsg.getShowDateTime());
			
		// Create a statement	      
		    String insertQuery = "INSERT INTO `tblshow` (`showID`, `movieRef`, `roomNo`, `showDate`, `showPrice`, `totalSeats`, `availableSeats`) VALUES "
		    		+ "(NULL, '" + pMsg.getMovie().getMovieID() +"', '" + pMsg.getRoomNo() +"', '" + inDate +"','" + pMsg.getShowprice() +"', '" + pMsg.getTotalSeats() +"', '" + pMsg.getAvailableSeats() +"');";
		    
//		                     sent 
//		    String insertQuery = "INSERT INTO `tblshow` (`showID`, `movieRef`, `roomNo`, `showDate`, `showPrice`, `totalSeats`, `availableSeats`) VALUES (NULL, '6', '13', '2016-12-08 09:30:30', '10', '50', '45')";
		    
	    // Create a PreparedStatement object for executing queries
	    	PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	    	   
	    //Execute the statement
	    	preparedStatement.executeUpdate();
	    	
	    	return pMsg;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Cannot insert a Show at " + pMsg.getShowDateTime().toString());
			
			return null;
		}
	    	
	    	    	  
	}
	
	public MovieMessage viewShowList(Show pMsg) throws SQLException{// view ShowList @ DBA for update or delete // view ShowList @ Reg user to reserve
		
		 // Create a statement
	    statement = connection.createStatement();

	    int viewMsgCount =0;
	    int viewMsgSize = 0;
	    MovieMessage viewMsg = new MovieMessage();
	    
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "select * FROM `tblshow`";
	    String viewCountQuery = "SELECT count(showID) FROM `tblshow`";
	    
	    // Execute a statement
	    ResultSet resultCountSet = statement.executeQuery(viewCountQuery);
	    while (resultCountSet.next()){
	    	viewMsgSize =resultCountSet.getInt(1);
	    }
	    
	    Show[] viewMsgList=new Show[viewMsgSize];
	    // Execute a statement
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)
		    		      + "\t" + resultSet.getString(4)
		    		      + "\t" + resultSet.getString(5)
		    		      + "\t" + resultSet.getString(6)
		    		      + "\t" + resultSet.getString(7)
		  				);
	    		    	   	
		    	  
		    	  
		    	  Movie showMovie = new Movie();
		    	  showMovie.setMovieID(resultSet.getInt("movieRef"));
	    	  
		
		    	  
		    	  //Movie movie, Date showDateTime, int roomNo, float showprice
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
					try {
						viewMsgList[viewMsgCount] = new Show(
													viewMovieByID(showMovie),  //movie ref
													new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("showDate")),
													resultSet.getInt("roomNo"),
													resultSet.getInt("availableSeats"),
													resultSet.getFloat("showPrice"));
						
						viewMsgList[viewMsgCount].setShowID(resultSet.getInt("showID"));
				    	  viewMsgCount++;
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
									
	    	}
	    viewMsg.setMsgShowCount(viewMsgCount);
		viewMsg.setMsgShowList(viewMsgList);
	    
	    return viewMsg;
		
		}
	
	public Show viewShowByID(Show pMsg) throws SQLException{
		
		 // Create a statement
	    statement = connection.createStatement();
	    Show viewMsg = null;
	    int viewMsgCount =0;
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "select * FROM `tblshow`"
	    		+ "where `showID`= " + pMsg.getShowID();
					//  		+ "where lastName "
					//    + " = 'Smith'");

	    // Execute a statement
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)
		    		      + "\t" + resultSet.getString(4)
		    		      + "\t" + resultSet.getString(5)
		    		      + "\t" + resultSet.getString(6)
		    		      + "\t" + resultSet.getString(7)
		  				);
	    		    	   	
		    	  
		    	  
		    	  Movie showMovie = new Movie();
		    	  showMovie.setMovieID(resultSet.getInt("movieRef"));
	    	  
		    	 // Date dta = new SimpleDateFormat("MM/dd/yyyy HH:MM:SS").parse(resultSet.getString("showDate"));
		    	  
		    	  //Movie movie, Date showDateTime, int roomNo, float showprice
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
					try {
						viewMsg = new Show(
													viewMovieByID(showMovie),  //movie ref
													new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("showDate")), //MM/dd/yyyy HH:MM:SS
													resultSet.getInt("roomNo"),
													resultSet.getInt("availableSeats"),
													resultSet.getFloat("showPrice"));
						viewMsg.setShowID(resultSet.getInt("showID"));
						viewMsg.getMovie().setMovieID(resultSet.getInt("movieRef"));
						
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
	    	}
	    return viewMsg;
		
		}
	
	public Show viewShowByDate(Show pMsg) throws SQLException{// delete and update @ DBA // reserve for @ Reg user
		
		 // Create a statement
	    statement = connection.createStatement();
	    Show viewMsg = null;
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "select * FROM `tblshow`"
	    		+ "where `showDate`= '" + dateToString(pMsg.getShowDateTime())+ "'";
					//  		+ "where lastName "
					//    + " = 'Smith'");

	    // Execute a statement	
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)
		    		      + "\t" + resultSet.getString(4)
		    		      + "\t" + resultSet.getString(5)
		    		      + "\t" + resultSet.getString(6)
		    		      + "\t" + resultSet.getString(7)
		  				);
	    		    	   	
		    	  Movie showMovie = new Movie();
		    	  showMovie.setMovieID(resultSet.getInt("movieRef"));
	    	 
	    	  
		    	  //Movie movie, Date showDateTime, int roomNo, float showprice
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
					try {
						viewMsg = new Show(
									viewMovieByID(showMovie),  //movie ref
									new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("showDate")),
									resultSet.getInt("roomNo"),
									resultSet.getFloat("showPrice"));
						viewMsg.setShowID(resultSet.getInt("showID"));
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		    	}
	    		    return viewMsg;
		
		}
	
	public MovieMessage viewShowByMovieID(Movie pMsg) throws SQLException{
		
		 // Create a statement
	    statement = connection.createStatement();
	    int viewMsgCount =0;
	    int viewMsgSize = 0;
	    MovieMessage viewMsg = new MovieMessage();
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "select * FROM `tblshow`"
	    		+ "where `movieRef`= " + pMsg.getMovieID();
	    String viewCountQuery = "SELECT count(showID) FROM `tblshow`"
	    		+ "where `movieRef`= " + pMsg.getMovieID();

	    // Execute a statement
	    ResultSet resultCountSet = statement.executeQuery(viewCountQuery);
	    while (resultCountSet.next()){
	    	viewMsgSize =resultCountSet.getInt(1);
	    }
	    
	    Show[] viewMsgList=new Show[viewMsgSize];
	    
	    // Execute a statement
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)
		    		      + "\t" + resultSet.getString(4)
		    		      + "\t" + resultSet.getString(5)
		    		      + "\t" + resultSet.getString(6)
		    		      + "\t" + resultSet.getString(7)
		  				);
	    		    	   	
		    	  
		    	  
		    	  Movie showMovie = new Movie();
		    	  showMovie.setMovieID(resultSet.getInt("movieRef"));
	    	  
		    	 // Date dta = new SimpleDateFormat("MM/dd/yyyy HH:MM:SS").parse(resultSet.getString("showDate"));
		    	  
		    	  //Movie movie, Date showDateTime, int roomNo, float showprice
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
					try {
						viewMsgList[viewMsgCount] = new Show(
													viewMovieByID(showMovie),  //movie ref
													new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("showDate")), //MM/dd/yyyy HH:MM:SS
													resultSet.getInt("roomNo"),
													resultSet.getInt("availableSeats"),
													resultSet.getFloat("showPrice"));
						
						viewMsgList[viewMsgCount].setShowID(resultSet.getInt("showID"));
				    	 viewMsgCount++;
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
	    	}
	    
	    viewMsg.setMsgShowCount(viewMsgCount);
		viewMsg.setMsgShowList(viewMsgList);
		
	    return viewMsg;
		
		}
	
	public MovieMessage viewDeleteShow(Show pMsg) throws SQLException{
		
		 // Create a statement
	    statement = connection.createStatement();
	    int viewMsgCount =0;
	    int viewMsgSize = 0;
	    MovieMessage viewMsg = new MovieMessage();
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "SELECT * FROM `tblshow` WHERE `showID` NOT in (SELECT `showRef`FROM `tblreservation`)";

	    String viewCountQuery = "SELECT COUNT(*) FROM `tblshow` WHERE `showID` NOT in (SELECT `showRef`FROM `tblreservation`)";


	    // Execute a statement
	    ResultSet resultCountSet = statement.executeQuery(viewCountQuery);
	    while (resultCountSet.next()){
	    	viewMsgSize =resultCountSet.getInt(1);
	    }
	    
	    Show[] viewMsgList=new Show[viewMsgSize];
	    
	    // Execute a statement
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)
		    		      + "\t" + resultSet.getString(4)
		    		      + "\t" + resultSet.getString(5)
		    		      + "\t" + resultSet.getString(6)
		    		      + "\t" + resultSet.getString(7)
		  				);
	    		    	   	
		    	  
		    	  
		    	  Movie showMovie = new Movie();
		    	  showMovie.setMovieID(resultSet.getInt("movieRef"));
	    	  
		    	 // Date dta = new SimpleDateFormat("MM/dd/yyyy HH:MM:SS").parse(resultSet.getString("showDate"));
		    	  
		    	  //Movie movie, Date showDateTime, int roomNo, float showprice
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
					try {
						viewMsgList[viewMsgCount] = new Show(
													viewMovieByID(showMovie),  //movie ref
													new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("showDate")), //MM/dd/yyyy HH:MM:SS
													resultSet.getInt("roomNo"),
													resultSet.getInt("availableSeats"),
													resultSet.getFloat("showPrice"));
						
						viewMsgList[viewMsgCount].setShowID(resultSet.getInt("showID"));
				    	 viewMsgCount++;
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
	    	}
	    
	    viewMsg.setMsgShowCount(viewMsgCount);
		viewMsg.setMsgShowList(viewMsgList);
		
	    return viewMsg;
		
		}
	
	public MovieMessage viewReservationByUser(RegisteredUser pMsg) throws SQLException{
		
		 // Create a statement
	    statement = connection.createStatement();
	    int viewMsgCount =0;
	    int viewMsgSize = 0;
	    MovieMessage viewMsg = new MovieMessage();
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "SELECT * FROM `tblreservation` WHERE `userRef` = '"+ pMsg.getUserID() + "' AND `showRef` NOT IN (SELECT `showID`FROM `tblshow` where (DATEDIFF(CURDATE(),DATE(showDate)) = 0 AND HOUR(TIMEDIFF(CURTIME(),TIME(showDate))) = 0 AND MINUTE(CURTIME()) > MINUTE(TIME(showDate))))";

	    String viewCountQuery = "SELECT COUNT(*) FROM `tblreservation` WHERE `userRef` = '"+ pMsg.getUserID() + "' AND `showRef` NOT IN (SELECT `showID`FROM `tblshow` where (DATEDIFF(CURDATE(),DATE(showDate)) = 0 AND HOUR(TIMEDIFF(CURTIME(),TIME(showDate))) = 0 AND MINUTE(CURTIME()) > MINUTE(TIME(showDate))))";


	    // Execute a statement
	    ResultSet resultCountSet = statement.executeQuery(viewCountQuery);
	    while (resultCountSet.next()){
	    	viewMsgSize =resultCountSet.getInt(1);
	    }
	    
	    Reservation[] viewMsgList=new Reservation[viewMsgSize];
	    
	    // Execute a statement
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)
		    		      + "\t" + resultSet.getString(4)
		    		      + "\t" + resultSet.getString(5)
		    		      + "\t" + resultSet.getString(6)
		    		      + "\t" + resultSet.getString(7)
		  				);
	    		    	   	
		    	  RegisteredUser reserveduser =  new RegisteredUser();
		    	  reserveduser.setUserID(resultSet.getInt("userRef"));  //from DB
		    	  
		    	  Show  reservedShow = new Show();
		    	  reservedShow.setShowID(resultSet.getInt("showRef")); //from DB
		    	  
	    		//RegisteredUser regUser, Show show, String ccCard, int ticketQty, float ticketAmount,Date ticketDate) {
//	    						char sex, Date birth
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
					try {
						viewMsgList[viewMsgCount] = new Reservation(viewUserByID(reserveduser), //link user for details
																viewShowByID(reservedShow),  // link show for details
																resultSet.getString("cardInfo"),
																resultSet.getInt("ticketQty"),
																resultSet.getFloat("ticketAmount"),  
																new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("ticketDate")));
						
						viewMsgList[viewMsgCount].setTicketNo(resultSet.getInt("ticketNo"));
						viewMsgList[viewMsgCount].getRegUser().setUserID(resultSet.getInt("userRef"));
				    	 viewMsgCount++;
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
	    	}
	    
	    viewMsg.setMsgReservationCount(viewMsgCount);
		viewMsg.setMsgReservationList(viewMsgList);
		
	    return viewMsg;
		
		}
	
	public Show updateShow(Show pMsg,String updateSource) throws SQLException{//set the  movie id (view movie by name) using the movie name before calling this function //update show @ DBA
		
//		Message updateMsg = new Message();
		
		try{
			
			String updateQuery;
			
		    if(updateSource.equals("update")){
		    	// Create a statement updates on date time and not ID	    
			     updateQuery = "UPDATE `tblshow` SET `movieRef`='"+ pMsg.getMovie().getMovieID() + "',`showPrice`='"+ pMsg.getShowprice() + "',`roomNo`='"+ pMsg.getRoomNo() + "',`availableSeats`='"+ pMsg.getAvailableSeats() + "',`showDate`='"+ dateToString(pMsg.getUpdateShowDateTime()) + "'" + 
			    					" WHERE `showDate`= '"+ dateToString(pMsg.getShowDateTime()) +"'"; 
		    }
		    else{
		    	// Create a statement updates on date time and not ID	    
			     updateQuery = "UPDATE `tblshow` SET `movieRef`='"+ pMsg.getMovie().getMovieID() + "',`showPrice`='"+ pMsg.getShowprice() + "',`roomNo`='"+ pMsg.getRoomNo() + "',`availableSeats`='"+ pMsg.getAvailableSeats() + "',`showDate`='"+ dateToString(pMsg.getShowDateTime()) + "'" + 
			    					" WHERE `showDate`= '"+ dateToString(pMsg.getShowDateTime()) +"'"; 
		    }
				
			    					  
		    // Create a PreparedStatement object for executing queries
		    	PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		    	   
		    //Execute the statement
		    	preparedStatement.executeUpdate();    	
		    	
		    	return pMsg;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Cannot update a Show at " + pMsg.getUpdateShowDateTime().toString());
			
			return null;
		}
	}
	
	public Show deleteShow(Show pMsg) throws SQLException{//delete show @ DBA
		
//		Message deleteMsg = new Message();
		
		try{
		// Create a statement	   
	    String updateQuery = "DELETE FROM `tblshow` WHERE `showDate`= '"+ dateToString(pMsg.getShowDateTime()) +"'" ;//`showID`= 3";
	    					    
    // Create a PreparedStatement object for executing queries
    	PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
    	   
    //Execute the statement
    	preparedStatement.executeUpdate();
    	
    	return pMsg;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Please enter the correct info to delete a Show");
			
			return null;
		}
    	
	}

	//***********************************MOVIE ***************************************************
	public Movie insertMovie(Movie pMsg) throws SQLException{//insert Movie @ DBA
		
		
		try{
			
//			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//			Date myDate = formatter.parse(pMsg.getBirth().toString());
//			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			
			
		// Create a statement	      
		    String insertQuery = "INSERT INTO `tblmovie` (`movieID`, `movieName`, `movieRank`) VALUES "
		    		+ "(NULL, '" + pMsg.getMovieName() +"', '" + pMsg.getMovieRank()+"');";
				    
	    // Create a PreparedStatement object for executing queries
	    	PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	    	   
	    //Execute the statement
	    	preparedStatement.executeUpdate();
	    	
	    	return pMsg;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Please enter the correct info to insert a Movie");
			
			return null;
		}
	    	
	    	    	  
	}
	
	public MovieMessage viewMovie(Movie pMsg) throws SQLException{//delete/update Movie @ DBA//reserve @Reg user // display all movies
		
		 // Create a statement
	    statement = connection.createStatement();
	    
	    int viewMsgCount = 0;
	    int viewMsgSize = 0;
	    MovieMessage viewMsg = new MovieMessage();
	    
	    
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "select * FROM `tblmovie`";
	    String viewCountQuery = "SELECT count(movieID) FROM `tblmovie`";

	    // Execute a statement
	    ResultSet resultCountSet = statement.executeQuery(viewCountQuery);
	    while (resultCountSet.next()){
	    	viewMsgSize =resultCountSet.getInt(1);
	    }
	    
	    Movie[] viewMsgList=new Movie[viewMsgSize];
	    //*********************************************************	    
	    ResultSet resultSet = statement.executeQuery(viewQuery);
	    
	    // Iterate through the result and print the student names
	    while (resultSet.next()){
						    	  System.out.println(resultSet.getString(1)
						    		      + "\t" + resultSet.getString(2) 
						    		      + "\t" + resultSet.getString(3)		    		     
						  				);
					    		    	   		    	 	    	
					    		//String movieName, int movieRank
						    	  	//	    						char sex, Date birth
									 //dt = (Date)formatter.parse(resultSet.getString("birth"));
						    	  viewMsgList[viewMsgCount] = new Movie(
																resultSet.getString("movieName"),
																resultSet.getInt("movieRank"));
									
						    	  viewMsgList[viewMsgCount].setMovieID(resultSet.getInt("movieID"));
						    	  viewMsgCount++;
		    	  
	    		    	}
	    
	    		viewMsg.setMsgMovieCount(viewMsgCount);
	    		viewMsg.setMsgMovieList(viewMsgList);
	    		    return viewMsg;		
		}
	
	public Movie viewMovieByID(Movie pMsg) throws SQLException{  // to get movie object(id) for show object
		
		 // Create a statement
	    statement = connection.createStatement();
	    Movie viewMsg = null;
	    int viewMsgCount = 0;
	    
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "select * FROM `tblmovie`"
	    		+ "where `movieID`= " + pMsg.getMovieID();
					//  		+ "where lastName "
					//    + " = 'Smith'");

	    // Execute a statement
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)		    		     
		  				);
	    		    	   	
//	    	  t = resultSet.getInt("id");
	    	 
	    	  
	    		//String movieName, int movieRank
//	    						char sex, Date birth
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
					viewMsg = new Movie(
												resultSet.getString("movieName"),
												resultSet.getInt("movieRank"));
	    		    	}
	    		    return viewMsg;
		
		}
	
	public Movie viewMovieByName(Movie pMsg) throws SQLException{  // to get movie object(id) for show object
		
		 // Create a statement
	    statement = connection.createStatement();
	    Movie viewMsg = null;
	    int viewMsgCount = 0;
	    
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "select * FROM `tblmovie`"
	    		+ " where `movieName`= '" + pMsg.getMovieName() + "'";
					//  		+ "where lastName "
					//    + " = 'Smith'");

	    // Execute a statement
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)		    		     
		  				);
	    		    	   	
//	    	  t = resultSet.getInt("id");
	    	 
	    	  
	    		//String movieName, int movieRank
//	    						char sex, Date birth
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
					viewMsg = new Movie(
												resultSet.getString("movieName"),
												resultSet.getInt("movieRank"));
					viewMsg.setMovieID(resultSet.getInt("movieID"));
		    	}
	    
	    
	    		    return viewMsg;
		
		}
	
	public MovieMessage viewDeleteMovie(Movie pMsg) throws SQLException{//delete/update Movie @ DBA//reserve @Reg user // display all movies
		
		 // Create a statement
	    statement = connection.createStatement();
	    
	    int viewMsgCount = 0;
	    int viewMsgSize = 0;
	    MovieMessage viewMsg = new MovieMessage();
	    
	    
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "SELECT `movieID`, `movieName`, `movieRank` FROM `tblmovie` WHERE `movieID` NOT in (SELECT `movieRef`FROM `tblshow`)";
	    String viewCountQuery = "SELECT COUNT(*) FROM `tblmovie` WHERE `movieID` NOT in (SELECT `movieRef`FROM `tblshow`)";

	    // Execute a statement
	    ResultSet resultCountSet = statement.executeQuery(viewCountQuery);
	    while (resultCountSet.next()){
	    	viewMsgSize =resultCountSet.getInt(1);
	    }
	    
	    Movie[] viewMsgList=new Movie[viewMsgSize];
	    //*********************************************************	    
	    ResultSet resultSet = statement.executeQuery(viewQuery);
	    
	    // Iterate through the result and print the student names
	    while (resultSet.next()){
						    	  System.out.println(resultSet.getString(1)
						    		      + "\t" + resultSet.getString(2) 
						    		      + "\t" + resultSet.getString(3)		    		     
						  				);
					    		    	   		    	 	    	
					    		//String movieName, int movieRank
						    	  	//	    						char sex, Date birth
									 //dt = (Date)formatter.parse(resultSet.getString("birth"));
						    	  viewMsgList[viewMsgCount] = new Movie(
																resultSet.getString("movieName"),
																resultSet.getInt("movieRank"));
									
						    	  viewMsgList[viewMsgCount].setMovieID(resultSet.getInt("movieID"));
						    	  viewMsgCount++;
		    	  
	    		    	}
	    
	    		viewMsg.setMsgMovieCount(viewMsgCount);
	    		viewMsg.setMsgMovieList(viewMsgList);
	    		    return viewMsg;		
		}
	
	public Movie updateMovie(Movie pMsg) throws SQLException{//update Movie @ DBA
		
//		Message updateMsg = new Message();
		
		try{
			
				// Create a statement	   \\ using the movie name and not the id
			    String updateQuery = "UPDATE `tblmovie` SET `movieRank`='"+ pMsg.getMovieRank() + "'" +  //`movieName`='"+ pMsg.getMovieName() + "',
			    					" WHERE `movieName`= '"+ pMsg.getMovieName() + "'";  //"+ pMsg.getId()
			    					  
		    // Create a PreparedStatement object for executing queries
		    	PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		    	   
		    //Execute the statement
		    	preparedStatement.executeUpdate();    	
		    	
		    	return pMsg;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Please enter the correct info to update a Movie");
			
			return null;
		}
	}
	
	public Movie deleteMovie(Movie pMsg) throws SQLException{//delete Movie @ DBA
		
//		Message deleteMsg = new Message();
		
		try{
		// Create a statement	   
	    String updateQuery = "DELETE FROM `tblmovie` WHERE `movieName`= '"+ pMsg.getMovieName() + "'";
	    					    
    // Create a PreparedStatement object for executing queries
    	PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
    	   
    //Execute the statement
    	preparedStatement.executeUpdate();
    	
    	return pMsg;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Please enter the correct info to delete a Movie");
			
			return null;
		}
    	
	}

	//***********************************RESERVATION ***************************************************
    public Reservation insertReservation(Reservation pMsg) throws SQLException{  //set the user id(viewUser) and the show id(viewshow) before calling this function //create Reservation @Reg user
		
//		Message insertMsg = new Message();
		
		
		try{
			
//			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//			Date myDate = formatter.parse(pMsg.getBirth().toString());
//			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			
			
		// Create a statement	      
		    String insertQuery = "INSERT INTO `tblreservation` (`ticketNo`, `userRef`, `showRef`, `cardInfo`, `ticketQty`, `ticketAmount`, `ticketDate`) VALUES "
		    		+ "(NULL, '" + pMsg.getRegUser().getUserID() +"', '" + pMsg.getShow().getShowID()+"', '" + pMsg.getCcCard() +"', '" + pMsg.getTicketQty()+"', '" + pMsg.getTicketAmount()+"','" + dateToString(pMsg.getTicketDate()) +"')";
				  
	    // Create a PreparedStatement object for executing queries
	    	PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
	    	   
	    //Execute the statement
	    	preparedStatement.executeUpdate();
	    	
	   //get the avail seats for the show
	    	Show rsvShow = viewShowByID(pMsg.getShow());
	    	rsvShow.setAvailableSeats(rsvShow.getAvailableSeats()-pMsg.getTicketQty());
	    //update it with the new avail seats	
	    	updateShow(rsvShow,"book");
	    	
	    	return pMsg;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Please enter the correct info to create a Reservation");
			
			return null;
		}
	    	
	    	    	  
	}
	
	public Reservation viewReservation(Reservation pMsg) throws SQLException{//print Reservation @Reg user
		
		 // Create a statement
	    statement = connection.createStatement();
	    Reservation viewMsg = null;
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date dt;
	    int t = 0;
	    
	    String viewQuery = "SELECT * FROM `tblreservation` WHERE `ticketNo` = (SELECT MAX(`ticketNo`) FROM `tblreservation`)";
	    
//	    String viewQuery = "select * FROM `tblreservation`"
//	    		+ "where `ticketNo`= " + pMsg.getTicketNo();
					//  		+ "where lastName "
					//    + " = 'Smith'");

	    // Execute a statement
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)
		    		      + "\t" + resultSet.getString(4)
		    		      + "\t" + resultSet.getString(5)
		    		      + "\t" + resultSet.getString(6)
		    		      + "\t" + resultSet.getString(7)
		  				);
	    		    	   	
//	    	  t = resultSet.getInt("id");
		    	  RegisteredUser reserveduser =  new RegisteredUser();
		    	  reserveduser.setUserID(resultSet.getInt("userRef"));
		    	  
		    	  Show  reservedShow = new Show();
		    	  reservedShow.setShowID(resultSet.getInt("showRef"));
	    	  
	    		//String userName, String userPass, String fullName, String email, String phoneNo) {
//	    						char sex, Date birth
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
					try {
						viewMsg = new Reservation(viewUserByID(reserveduser), //link user for details
									viewShowByID(reservedShow),  // link show for details
									resultSet.getString("cardInfo"),
									resultSet.getInt("ticketQty"),
									resultSet.getFloat("ticketAmount"),  
									new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultSet.getString("ticketDate")));  //MM/dd/yyyy HH:MM:SS
						viewMsg.setTicketNo(resultSet.getInt("ticketNo"));
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		    	}
	    		    return viewMsg;
		
		}
	
	public Reservation[] viewReservationList(Reservation pMsg) throws SQLException{// cancel/ print a ticket for @Reguser
		
		 // Create a statement
	    statement = connection.createStatement();
	    Reservation viewMsg[] = null;
	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
	    int viewMsgCount = 0;
	    Date dt;
	    int t = 0;
	   
	    
	    String viewQuery = "select * FROM `tblreservation`"
	    		+ "where `userRef`= " + pMsg.getRegUser().getUserID();
					//  		+ "where lastName "
					//    + " = 'Smith'");

	    // Execute a statement
	    ResultSet resultSet = statement.executeQuery
	      (viewQuery);


	    // Iterate through the result and print the student names
	    while (resultSet.next()){
		    	  System.out.println(resultSet.getString(1)
		    		      + "\t" + resultSet.getString(2) 
		    		      + "\t" + resultSet.getString(3)
		    		      + "\t" + resultSet.getString(4)
		    		      + "\t" + resultSet.getString(5)
		    		      + "\t" + resultSet.getString(6)
		    		      + "\t" + resultSet.getString(7)
		  				);
	    		    	   	
		    	  RegisteredUser reserveduser =  new RegisteredUser();
		    	  reserveduser.setUserID(resultSet.getInt("userRef"));  //from DB
		    	  
		    	  Show  reservedShow = new Show();
		    	  reservedShow.setShowID(resultSet.getInt("showRef")); //from DB
		    	  
	    		//RegisteredUser regUser, Show show, String ccCard, int ticketQty, float ticketAmount,Date ticketDate) {
//	    						char sex, Date birth
					 //dt = (Date)formatter.parse(resultSet.getString("birth"));
					try {
						viewMsg[viewMsgCount] = new Reservation(viewUserByID(reserveduser), //link user for details
																viewShowByID(reservedShow),  // link show for details
																resultSet.getString("cardInfo"),
																resultSet.getInt("ticketQty"),
																resultSet.getFloat("ticketAmount"),  
																new SimpleDateFormat("MM/dd/yyyy HH:MM:SS").parse(resultSet.getString("ticketDate")));
						viewMsgCount++;	
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		    	}
	    		    return viewMsg;
		
		}
	
	public Reservation updateReservation(Reservation pMsg) throws SQLException{// set the show and user id before calling the function //update @Reguser
		
//		Message updateMsg = new Message();
		
		try{
			
				// Create a statement	   
			    String updateQuery = "UPDATE `tblreservation` SET `ticketNo`='" + pMsg.getTicketNo() + "',`userRef`='" + pMsg.getRegUser().getUserID() + "',`showRef`='" + pMsg.getShow().getShowID() + "',`cardInfo`='" + pMsg.getCcCard() + "',`ticketQty`='" + pMsg.getTicketQty() + "',`ticketAmount`='" + pMsg.getTicketAmount() + "',`ticketDate`='" + pMsg.getTicketDate() +
			    					"' WHERE `ticketNo`= '"+ pMsg.getTicketNo() +"'";  //"+ pMsg.getId()
			    					  
		    // Create a PreparedStatement object for executing queries
		    	PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
		    	   
		    //Execute the statement
		    	preparedStatement.executeUpdate();    	
		    	
		    	return pMsg;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Please enter the correct info to update a Reservation");
			
			return null;
		}
	}

	public Reservation deleteReservation(Reservation pMsg) throws SQLException{//cancel @Reguser
		
	
		try{
		// Create a statement	   
	    String updateQuery = "DELETE FROM `tblreservation` WHERE `ticketNo`= '"+ pMsg.getTicketNo() +"'";
	    					    
    // Create a PreparedStatement object for executing queries
    	PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
    	   
    //Execute the statement
    	preparedStatement.executeUpdate();
    	
    	return pMsg;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Please enter the correct info to delete a Reservation");
			
			return null;
		}
    	
	}

	public String dateToString(Date dt){
		
		String inDate = "";
		
	    SimpleDateFormat ft = 
 	    	      new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	    
	    inDate = ft.format(dt);
		
	    return inDate;
	}
	
	//*****************************************************************************************************
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovieServer server = new MovieServer(8000);	
	}

}
