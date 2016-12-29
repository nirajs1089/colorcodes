/*Author : Niraj Shah Aman Arora
Description : MovieClient*/
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieClient extends JPanel{

	//**************types of trnx
	
	// 1. user logs in  -> send user object <- send back user object with all values
	//2. user reserves -> user id from the earlier query, 
	// 3. user can cancel the ticket
	
	//***ADMIN
	//insert shows
	//update shows
	//delete shows
	 	
	//insert movies
	//update movies
	//delete movies
	
	private Show[] showPriceList;
	private Reservation[] userTicketList;
	private float showprice;
	private String formType;
	private static JFrame f;
	private static Container contentPane;
	
	private static String loggedUser;
	private static String loggedPass;
	private static String loggedUserRole;
	private static int loggedUserId;
	
	//****************UserRegister**************
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JLabel lblEmail;
	private JLabel lblFullName;
	private JLabel lblphone;
	private JLabel lblStatus;

	private JTextField txtUserName;
	private JTextField txtPassword;
	private JTextField txtEmail;
	private JTextField txtFullName;
	private JTextField txtphone;
	
	private JRadioButton useroption;
	private JRadioButton adminoption ;
	private ButtonGroup rolegroup;
	
	private JButton btnUserRegister;

	//****************UserLogin**************
	private JButton btnUserLogin;
	
	//****************UserUpdate**************
	
	private JButton btnUserUpdate;

	//****************UserRegister**************
	private JButton btnUserBookTicket;
	private JButton btnUserCancelTicket;

	//****************UserBookTicket**************
	
	private JLabel lblMovie;
	private JLabel lblDateTime;
	private JLabel lblQty;
	private JLabel lblAmnt;
	private JLabel lblCreditC;
	private JLabel lblToString;
	
	private JComboBox cmbMovie;
	private JComboBox cmbDateTime;
	private JComboBox cmbQty;

		
	private JTextField txtQty;
	private JTextField txtAmnt;
	private JTextField txtCreditC;

	private JTextArea textAreaInfo;
	private JScrollPane jpScrollInfo;
	
	private JButton btnUserPrintTicket;
	private JButton btnUserHome;

	
	//textarea
	
	//****************UserCancelTicket**************
	private JLabel lblTicket;

	private JComboBox cmbTicket;
	//textarea

	
	//****************Admin Home**************
	
	private JButton btnAdminInsertMovie;
	private JButton btnAdminInsertShow;
	private JButton btnAdminUpdateMovie;
	private JButton btnAdminUpdateShow;
	private JButton btnAdminDeleteMovie;
	private JButton btnAdminDeleteShow;
	private JButton btnadminHome;
	private JButton btnSignOut;


	//****************AdminInsertMovie**************
	private JLabel lblMovieName;
	private JLabel lblRank;

	private JTextField txtMovieName;
	private JTextField txtRank;
	private JComboBox cmbRank;

	

	//*******************AdminInsertShow***************

	private JLabel lblRoom;
	private JLabel lblPrice;
	private JLabel lblAvailSeats;
	
	private JTextField txtRoom;
	private JTextField txtPrice;
	private JTextField txtAvailSeats;
	private JTextField txtMovie;

	
	private JLabel lblday;
	private JLabel lblmonth;
	private JLabel lblyear;
	private JLabel lblhours;
	private JLabel lblminutes;

	private JComboBox cmbday;
	private JComboBox cmbmonth;
	private JComboBox cmbyear;
	private JComboBox cmbhours;
	private JComboBox cmbminutes;
	private JComboBox cmbAvailSeats;


	//****************AdminUpdateMovie**************


	//****************AdminUpdateShow**************
	//****************AdminDeleteMovie**************
	//****************AdminDeleteShow************
	private JComboBox cmbShowDateTime;

	
	private JLabel lblShowDateTime;
	private JTextField txtShowDateTime;

	
	String hostname;
	int port;
	private static MovieClient clientObj;
	
	public MovieClient(String hostname, int port) {
		this.port = port;
		//UserLoginGUI();
		//AdminInsertMovieGUI();
		//userRegisterGUI();
		//UserUpdateGUI();
		//AdminUpdateMovieGUI();
//		UserBookTicketGUI();
//		AdminDeleteMovieGUI();
//		AdminDeleteShowGUI();
		//UserCancelTicketGUI();
//		AdminInsertShowGUI();
		//AdminUpdateShowGUI();
		UserHomeGUI();
	}
	
	
	
	public MovieClient(String formType, String hostname, int port) {
		this.formType = formType;
		this.hostname = hostname;
		this.port = port;
		
		//call the GUI function based on form type
		
		 switch (formType) {
		 
			case "UserRegister": userRegisterGUI();
					 break;
			case "UserLogin":  UserLoginGUI();
			         break;
			case "UserUpdate":  UserUpdateGUI();
			         break;
			case "UserHome":  UserHomeGUI();
			         break;
			case "UserBookTicket":  UserBookTicketGUI();
			         break;
			case "UserCancelTicket":  UserCancelTicketGUI();
			         break;
			case "adminHome":  adminHomeGUI();
			         break;
			case "AdminInsertMovie":  AdminInsertMovieGUI();
			         break;
			case "AdminInsertShow":  AdminInsertShowGUI();
			         break;
			case "AdminUpdateMovie":  AdminUpdateMovieGUI();
			         break;
			case "AdminUpdateShow":  AdminUpdateShowGUI();
			         break;
			case "AdminDeleteMovie":  AdminDeleteMovieGUI();
			         break;
			case "AdminDeleteShow":  AdminDeleteShowGUI();
			         break;		 		 
			default: JOptionPane.showMessageDialog(null, "Cannot open " + formType + " Form");
					break;
		 }
	}

	public String doValidation(String formType){
		//call the GUI function based on form type
		String ValidationMsg = "";
		
		 switch (formType) {
		 
			case "UserRegister": 

				if(txtUserName.getText().length() == 0 || !txtUserName.getText().matches("[a-zA-Z0-9]+")) return "UserName is empty OR not in valid format";
				if(txtPassword.getText().length() == 0 || !txtPassword.getText().matches("[a-zA-Z0-9]+")) return "Password is empty OR not in valid format";
				if(txtEmail.getText().length() == 0 || !txtEmail.getText().matches("^[a-zA-Z0-9.%+-_]+@[a-zA-Z0-9.-]+[.][a-zA-Z]{2,4}$")) return "Email is empty OR not in valid format";
				if(txtFullName.getText().length() == 0 || !txtFullName.getText().matches("[a-zA-Z0-9\\s]+")) return "FullName is empty OR not in valid format";
				if(txtphone.getText().length() == 0 || !txtphone.getText().matches("\\d{3}[-\\s]\\d{3}[-\\s]\\d{4}")) return "phone is empty OR not in valid format (ddd-ddd-dddd)";

				  													
				
					 break;
			case "UserLogin":  
				
				if(txtUserName.getText().length() == 0 || !txtUserName.getText().matches("[a-zA-Z0-9]+")) return "UserName is empty OR not in valid format";
				if(txtPassword.getText().length() == 0 || !txtPassword.getText().matches("[a-zA-Z0-9]+")) return "Password is empty OR not in valid format";

			         break;
			case "UserUpdate":  
				
				if(txtPassword.getText().length() == 0 || !txtPassword.getText().matches("[a-zA-Z0-9]+")) return "Password is empty OR not in valid format";
				if(txtEmail.getText().length() == 0 || !txtEmail.getText().matches("^[a-zA-Z0-9.%+-_]+@[a-zA-Z0-9.-]+[.][a-zA-Z]{2,4}$")) return "Email is empty OR not in valid format";
				if(txtFullName.getText().length() == 0 || !txtFullName.getText().matches("[a-zA-Z0-9\\s]+")) return "FullName is empty OR not in valid format";
				if(txtphone.getText().length() == 0 || !txtphone.getText().matches("\\d{3}[-\\s]\\d{3}[-\\s]\\d{4}")) return "phone is empty OR not in valid format (ddd-ddd-dddd)";

			         break;
			case "UserHome":  
			         break;
			case "UserBookTicket":  
				if(cmbMovie.getSelectedItem().toString().length() == 0 /*|| !cmbMovie.getSelectedItem().toString().matches("[a-zA-Z0-9\\s]+")*/) return "Movie is empty OR not in valid format";
				if(cmbDateTime.getSelectedIndex()<0 /*|| !cmbDateTime.getSelectedItem().toString().matches("[\\d\\s-:]+"*/) return "DateTime is empty OR not in valid format";
				if(cmbQty.getSelectedIndex()<0 /*|| !cmbQty.getSelectedItem().toString().matches("\\d\\d?")*/) return "Qty is empty OR not in valid format";
				if(txtAmnt.getText().length() == 0 || Float.parseFloat(txtAmnt.getText()) <= 0) return "Amnt is empty OR not in valid format";
				if(txtCreditC.getText().length() == 0 || !txtCreditC.getText().matches("\\d{16}")) return "CreditC is empty OR not in valid format (16 digits)";

			         break;
			case "UserCancelTicket":  
			         break;
			case "adminHome":  
			         break;
			case "AdminInsertMovie":  
				if(txtMovieName.getText().length() == 0 || !txtMovieName.getText().matches("[a-zA-Z0-9\\s]+")) return "MovieName is empty OR not in valid format";
			         break;
			case "AdminInsertShow":
				if(txtRoom.getText().length() == 0 || !txtRoom.getText().matches("\\d{1,2}")) return "Room is empty OR not in valid format";
				if(txtPrice.getText().length() == 0 || !txtPrice.getText().matches("[\\d]+[.]?\\d{1,2}")) return "Price is empty OR not in valid format";

				
			         break;
			case "AdminUpdateMovie": 
				if(cmbMovie.getSelectedIndex()<0) return "MovieName is empty OR not in valid format";
			         break;
			case "AdminUpdateShow": 
				if(txtRoom.getText().length() == 0 || !txtRoom.getText().matches("\\d{1,2}")) return "Room is empty OR not in valid format";
				if(txtPrice.getText().length() == 0 || !txtPrice.getText().matches("[\\d]+[.]?\\d{1,2}")) return "Price is empty OR not in valid format";
			         break;
			case "AdminDeleteMovie": 
			         break;
			case "AdminDeleteShow":  
			         break;		 		 
			default: 
					break;
		 }
		
		 return "valid";
	}

	//**************************USER INTERFACE BEGINS ************************
	
	public void userRegisterGUI(){
		initUserRegisterUI();
		doTheUserRegisterLayout();
		
		btnUserRegister.addActionListener(new java.awt.event.ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(insertUser())
						newFrame("User Login","UserLogin");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		btnUserLogin.addActionListener(new java.awt.event.ActionListener(){
			
			   @Override
			   public void actionPerformed(ActionEvent arg0) {
			    // TODO Auto-generated method stub
				   newFrame("User Login","UserLogin");

				    loggedUser="";
					loggedPass="";
					loggedUserId=0;
					
			   }
			   
			  });
		
	}	
	
	public void initUserRegisterUI(){
		lblUserName = new JLabel( "UserName");
		lblPassword = new JLabel( "Password");
		lblEmail = new JLabel( "Email");
		lblFullName = new JLabel( "FullName");
		lblphone = new JLabel( "phone");
		
		txtUserName = new JTextField("",20);
		txtPassword = new JTextField("",20);
		txtEmail = new JTextField("",20);
		txtFullName = new JTextField("",20);
		txtphone = new JTextField("",20);
		
		useroption = new JRadioButton("user",true);
		adminoption = new JRadioButton("admin");       
 
        rolegroup = new ButtonGroup();
		
		btnUserRegister = new JButton("UserRegister");
		btnUserLogin = new JButton("UserLogin");


	}
	
	public void doTheUserRegisterLayout(){
		JPanel top = new JPanel();
		JPanel centre = new JPanel();
		JPanel bottom = new JPanel();
		
		// top
		top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		top.add(lblUserName);
		top.add(txtUserName);
		
		top.add(lblPassword);
		top.add(txtPassword);

		
		top.add(lblEmail);
		top.add(txtEmail);
		
		top.add(lblFullName);
		top.add(txtFullName);
		
		top.add(lblphone);
		top.add(txtphone);
		
		rolegroup.add(useroption);
		rolegroup.add(adminoption);
        
        centre.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
        centre.add(useroption);
        centre.add(adminoption);
		
		//bottom
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		bottom.add(btnUserRegister);
		bottom.add(btnUserLogin);

		
		setLayout(new BorderLayout());
		add(top,"North");
		add(centre,"Center");
		add(bottom,"South");
	}
	
	//*****************************************************
	public void UserLoginGUI(){
	  initUserLoginUI();
	  doTheUserLoginLayout();
	  btnUserLogin.addActionListener(new java.awt.event.ActionListener(){
	
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	    // TODO Auto-generated method stub
		   try {
			if (loginUser()){
				if(useroption.isSelected())
					newFrame("User Home","UserHome");
				else
					newFrame("Admin Home","adminHome");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	  });
	  
	  btnUserRegister.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("New User Registeration","UserRegister");
		   }
		   
		  });

	 } 
	
	public void initUserLoginUI(){
		lblUserName = new JLabel( "UserName");
		lblPassword = new JLabel( "Password");
		
		txtUserName = new JTextField("",20);
		txtPassword = new JTextField("",20);
		
		useroption = new JRadioButton("user",true);
		adminoption = new JRadioButton("admin");
		
		rolegroup = new ButtonGroup();
		
		btnUserLogin = new JButton("User Login");
		btnUserRegister = new JButton("User Register");


		 } 
    
	public void doTheUserLoginLayout(){
		JPanel top = new JPanel();
		JPanel centre = new JPanel();
		JPanel bottom = new JPanel();
		
		// top
		top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		top.add(lblUserName);
		top.add(txtUserName);
		
		top.add(lblPassword);
		top.add(txtPassword);

		rolegroup.add(useroption);
		rolegroup.add(adminoption);
        
        centre.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
        centre.add(useroption);
        centre.add(adminoption);
		
//bottom
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		bottom.add(btnUserLogin);
		bottom.add(btnUserRegister);

		
		setLayout(new BorderLayout());
		add(top,"North");
		add(centre,"Center");
		add(bottom,"South");
	 } 
	 
	 //*********************************************"
	public void UserUpdateGUI(){
	  initUserUpdateUI();
	  doTheUserUpdateLayout();
	  btnUserUpdate.addActionListener(new java.awt.event.ActionListener(){
	
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	    // TODO Auto-generated method stub
		   try {
			updateUser();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	  });
	  
	  btnUserHome.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("User Home","UserHome");
		   }
		   
		  });
	 }  
	
	public void initUserUpdateUI(){
		
		lblPassword = new JLabel( "Password");
		lblEmail = new JLabel( "Email");
		lblFullName = new JLabel( "FullName");
		lblphone = new JLabel( "phone");
		
		txtPassword = new JTextField("",20);
		txtEmail = new JTextField("",20);
		txtFullName = new JTextField("",20);
		txtphone = new JTextField("",20);
		
		btnUserUpdate = new JButton("UserUpdate");
		btnUserHome = new JButton("UserHome");
	
		 } 
	
	public void doTheUserUpdateLayout(){
	  
		JPanel top = new JPanel();		
		JPanel bottom = new JPanel();
		
		// top
				top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
//				top.add(lblUserName);
//				top.add(txtUserName);
				

				
				top.add(lblFullName);
				top.add(txtFullName);
				
				top.add(lblPassword);
				top.add(txtPassword);
				
				top.add(lblEmail);
				top.add(txtEmail);							
				
				top.add(lblphone);
				top.add(txtphone);
				
				//bottom
				bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
				bottom.add(btnUserUpdate);
				bottom.add(btnUserHome);

				
				setLayout(new BorderLayout());
				add(top,"North");
				add(bottom,"South");
	 } 

//*********************************************"
	public void UserHomeGUI(){
	  initUserHomeUI();
	  doTheUserHomeLayout();
	  btnUserBookTicket.addActionListener(new java.awt.event.ActionListener(){
	
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
		   		  
		   newFrame("Book Ticket by User","UserBookTicket");
	   }
	   
	  });
	  
	  btnUserCancelTicket.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
			   newFrame("User Cancel Ticket","UserCancelTicket");
		   }
		   
		  });
	  
	  btnUserUpdate.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
			   newFrame("User Update Info","UserUpdate");
		   }
		   
		  });
	  
	  btnSignOut.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("User Login","UserLogin");

			    loggedUser="";
				loggedPass="";
				loggedUserId=0;

		   }
		   
		  });
	 }  
	
	public void initUserHomeUI(){
	
		lblStatus = new JLabel( "Status");

		btnUserBookTicket = new JButton("UserBookTicket");
		btnUserCancelTicket = new JButton("UserCancelTicket");
		btnUserUpdate = new JButton("UserUpdate");
		btnSignOut = new JButton("SignOut");

		 } 
		
	public void doTheUserHomeLayout(){
		  
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		
		// top
		top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		top.add(btnUserBookTicket);
		
		//bottom
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		bottom.add(btnUserCancelTicket);
		bottom.add(btnUserUpdate);
		bottom.add(btnSignOut);
		
		setLayout(new BorderLayout());
		add(top,"North");
		add(bottom,"South");
		 } 
	
	//*********************************************"
	public void UserBookTicketGUI(){
	  initUserBookTicketUI();
	  doTheUserBookTicketLayout();
	  
	  btnUserBookTicket.addActionListener(new java.awt.event.ActionListener(){
	
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	    // TODO Auto-generated method stub
		   try {
			if(insertReservation())
				textAreaInfo.setText(printReservation().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	  });
	  
	  btnUserHome.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("User Home","UserHome");
		   }
		   
		  });
	  
	       
	  cmbMovie.addActionListener(new java.awt.event.ActionListener(){
	    		
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	   
		   String[] shwList;
		   cmbDateTime.removeAllItems();
				try {
					shwList = displayShowByMovie();
					for(int i =0 ;i<shwList.length;i++){
						
						cmbDateTime.addItem(shwList[i]);
					}
		
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
	   }
	   
	  });
	       
	       cmbDateTime.addActionListener(new java.awt.event.ActionListener(){
	    		
	    	   @Override
	    	   public void actionPerformed(ActionEvent arg0) {
	    	   	    
	    		   cmbQty.removeAllItems();
	    		   
	    		   try {
	    				for(int i =0 ;i<showPriceList.length;i++){
							
	    					//if the selected show equals to the one in the array
							if (cmbDateTime.getSelectedItem().toString().equals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(showPriceList[i].getShowDateTime()))){
								
								//display the count of avail seats only
								for(int seats =1 ;seats<=showPriceList[i].getAvailableSeats();seats++){
									cmbQty.addItem(seats);
								}
								//update the amnt auto when show is selected
								txtAmnt.setText(String.valueOf(Float.parseFloat(cmbQty.getSelectedItem().toString())*showPriceList[i].getShowprice())); 
								showprice = showPriceList[i].getShowprice();
								
							}
						}	
	    		   } catch (Exception e) {
	    				// TODO Auto-generated catch block
	    				
	    			}
	    	   }
	    	   
	    	  });
	       
	       cmbQty.addActionListener(new java.awt.event.ActionListener(){
	    		
	    	   @Override
	    	   public void actionPerformed(ActionEvent arg0) {
	    	   	    
	    		   try {
	    				
						
						txtAmnt.setText(String.valueOf(Float.parseFloat(cmbQty.getSelectedItem().toString())*showprice)); 								
					
				
					}	
			       catch (Exception e) {
					// TODO Auto-generated catch block
					
			       }
	    		   
	    		  
	    	   }
	    	   
	    	  });
	 } 
	
	public void initUserBookTicketUI(){
	
		lblMovie = new JLabel( "Movie");
		lblDateTime = new JLabel( "DateTime");
		lblQty = new JLabel( "Qty");
		lblAmnt = new JLabel( "Amnt");
		lblCreditC = new JLabel( "CreditC");
		
		
		txtAmnt = new JTextField("",20);
		txtCreditC = new JTextField("",20);
		
		  textAreaInfo = new JTextArea(20, 25);
	      textAreaInfo.setEditable(false);
	      textAreaInfo.setLineWrap(true);
	      textAreaInfo.setWrapStyleWord(true);

	     jpScrollInfo = new JScrollPane(textAreaInfo);

		btnUserBookTicket = new JButton("UserBookTicket");
		btnUserPrintTicket = new JButton("UserPrintTicket");
		btnUserHome = new JButton("UserHome");

		cmbMovie = new JComboBox();
		cmbDateTime = new JComboBox();
		cmbQty = new JComboBox();
		
		try {
			String[] mvList = displayMovie(); 
			//String[] shwList = displayShowByMovie();
			
			for(int i =0 ;i<mvList.length;i++){
				
				cmbMovie.addItem(mvList[i]);
			}
		/*	for(int i =0 ;i<shwList.length;i++){
				
				cmbDateTime.addItem(shwList[i]);
			}*/
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
} 
		
	public void doTheUserBookTicketLayout(){
	  
		JPanel top = new JPanel();
		JPanel centre = new JPanel();
		JPanel bottom = new JPanel();
		
		// top
		top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		top.add(lblMovie);
		top.add(cmbMovie);
		
		top.add(lblDateTime);
		top.add(cmbDateTime);
		
		top.add(lblQty);
		top.add(cmbQty);
		
		top.add(lblAmnt);
		top.add(txtAmnt);
		
		top.add(lblCreditC);
		top.add(txtCreditC);
		
		centre.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		centre.add(jpScrollInfo);
		
		//bottom
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		bottom.add(btnUserBookTicket);
		bottom.add(btnUserHome);
		
		setLayout(new BorderLayout());
		add(top,"North");
		add(centre,"Center");
		add(bottom,"South");
		
		
	 } 
//*********************************************"
	
public void UserCancelTicketGUI(){
	  initUserCancelTicketUI();
	  doTheUserCancelTicketLayout();
	  btnUserCancelTicket.addActionListener(new java.awt.event.ActionListener(){
	
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	    // TODO Auto-generated method stub
		   try {
			deleteReservation();
			 String[] tickList;
				cmbTicket.removeAllItems();
					try {
						tickList = displayUserReservation();
						for(int i =0 ;i<tickList.length;i++){
							
							cmbTicket.addItem(tickList[i]);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	  });
	 
	  btnUserHome.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("User Home","UserHome");
		   }
		   
		  });
	  
	  cmbTicket.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		   
			   //display to string info
			   for(int i =0 ;i<userTicketList.length;i++){
					
					//if the selected show equals to the one in the array
					if (cmbTicket.getSelectedItem().toString().equals(String.valueOf(userTicketList[i].getTicketNo()))){
										
						//update the amnt auto when show is selected
						textAreaInfo.setText(userTicketList[i].toString()); 												
					}
			   }
		   }
		   
		  });
	  
	 } 

public void initUserCancelTicketUI(){
	
	lblTicket = new JLabel( "TicketNo");

	cmbTicket = new JComboBox();
	String[] tickList;
	cmbTicket.removeAllItems();
		try {
			tickList = displayUserReservation();
			for(int i =0 ;i<tickList.length;i++){
				
				cmbTicket.addItem(tickList[i]);
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
	btnUserCancelTicket = new JButton("UserCancelTicket");
	btnUserHome = new JButton("UserHome");
	
	textAreaInfo = new JTextArea(25, 30);
    textAreaInfo.setEditable(false);
    textAreaInfo.setLineWrap(true);
    textAreaInfo.setWrapStyleWord(true);

    jpScrollInfo = new JScrollPane(textAreaInfo);
	 } 

public void doTheUserCancelTicketLayout(){

	JPanel top = new JPanel();
	JPanel centre = new JPanel();
	JPanel bottom = new JPanel();
	
	// top
	top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	top.add(lblTicket);
	top.add(cmbTicket);	
	
	//bottom
	bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	bottom.add(btnUserCancelTicket);
	bottom.add(btnUserHome);
	
	centre.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	//centre.add(textAreaInfo);
	centre.add(jpScrollInfo);
	
	setLayout(new BorderLayout());
	add(top,"North");
	add(centre,"Center");
	add(bottom,"South");
	
	 } 

//*********************************************"
	
public void adminHomeGUI(){
	  initadminHomeUI();
	  doTheadminHomeLayout();
	  btnAdminInsertMovie.addActionListener(new java.awt.event.ActionListener(){
	
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	    // TODO Auto-generated method stub
		   newFrame("Admin InsertMovie","AdminInsertMovie");

	   }
	   
	  });
	   
	  btnAdminInsertShow.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("Admin InsertShow","AdminInsertShow");

		   }
		   
		  });

	  btnAdminUpdateMovie.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("Admin UpdateMovie","AdminUpdateMovie");

		   }
		   
		  });
	  
	  btnAdminUpdateShow.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("Admin UpdateShow","AdminUpdateShow");

		   }
		   
		  });
	  
	  btnAdminDeleteMovie.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("Admin DeleteMovie","AdminDeleteMovie");

		   }
		   
		  });
	  
	  btnAdminDeleteShow.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("Admin DeleteShow","AdminDeleteShow");

		   }
		   
		  });
	  
	  btnSignOut.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("User Login","UserLogin");

			    loggedUser="";
				loggedPass="";
				loggedUserId=0;

		   }
		   
		  });
	  
	  
	 } 

public void initadminHomeUI(){
	
	btnAdminInsertMovie = new JButton("InsertMovie");
	btnAdminInsertShow = new JButton("InsertShow");
	btnAdminUpdateMovie = new JButton("UpdateMovie");
	btnAdminUpdateShow = new JButton("UpdateShow");
	btnAdminDeleteMovie = new JButton("DeleteMovie");
	btnAdminDeleteShow = new JButton("DeleteShow");
	btnSignOut = new JButton("SignOut");

	
	 } 

public void doTheadminHomeLayout(){
	JPanel top = new JPanel();
	JPanel centre = new JPanel();
	JPanel bottom = new JPanel();
	
	// top
	top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	top.add(btnAdminInsertMovie);
	top.add(btnAdminInsertShow);
	
	centre.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	centre.add(btnAdminUpdateMovie);
	centre.add(btnAdminUpdateShow);
	
//bottom
	bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	bottom.add(btnAdminDeleteMovie);
	bottom.add(btnAdminDeleteShow);
	bottom.add(btnSignOut);
	
	setLayout(new BorderLayout());
	add(top,"North");
	add(centre,"Center");
	add(bottom,"South");
	 } 

//*********************************************"
	

public void AdminInsertMovieGUI(){
	  initAdminInsertMovieUI();
	  doTheAdminInsertMovieLayout();
	  btnAdminInsertMovie.addActionListener(new java.awt.event.ActionListener(){
	
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	    // TODO Auto-generated method stub
		   try {
			insertMovie();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot insert movie");
		}
	   }
	   
	  });
	  
	  btnadminHome.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("Admin Home","adminHome");
		   }
		   
		  });
	 } 

public void initAdminInsertMovieUI(){
	lblMovieName = new JLabel( "MovieName");
	lblRank = new JLabel( "Rank");

	txtMovieName = new JTextField("",20);
	cmbRank = new JComboBox();
	
	for(int i =1 ;i<=5;i++){
		cmbRank.addItem(i);
	}
	

	btnAdminInsertMovie = new JButton("AdminInsertMovie");
	btnadminHome = new JButton("adminHome");

	 } 

public void doTheAdminInsertMovieLayout(){
	  
	
	JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	
	// top
	top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	top.add(lblMovieName);
	top.add(txtMovieName);
	
	top.add(lblRank);
	top.add(cmbRank);

//bottom
	bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	bottom.add(btnAdminInsertMovie);
	bottom.add(btnadminHome);
	
	setLayout(new BorderLayout());
	add(top,"North");
	add(bottom,"South");
	
	 } 

//*********************************************"
	

public void AdminInsertShowGUI(){
	  initAdminInsertShowUI();
	  doTheAdminInsertShowLayout();
	  btnAdminInsertShow.addActionListener(new java.awt.event.ActionListener(){
	
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	    // TODO Auto-generated method stub
	    try {
			insertShow();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	  });
	  
	  btnadminHome.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("Admin Home","adminHome");
		   }
		   
		  });
	 }  

public void initAdminInsertShowUI(){

	lblMovie = new JLabel( "Movie");
	lblRoom = new JLabel( "Room");
	lblPrice = new JLabel( "Price");
	lblAvailSeats = new JLabel( "AvailSeats");
	lblDateTime = new JLabel( "DateTime");
	
	txtRoom = new JTextField("",20);
	txtPrice = new JTextField("",20);
	txtAvailSeats = new JTextField("",20);
	txtShowDateTime = new JTextField("",20);

	lblday = new JLabel( "day");
	lblmonth = new JLabel( "month");
	lblyear = new JLabel( "year");
	lblhours = new JLabel( "hours");
	lblminutes = new JLabel( "minutes");
	
	InitshowCombo();

	btnAdminInsertShow = new JButton("AdminInsertShow");
	btnadminHome = new JButton("adminHome");

	 } 

public void doTheAdminInsertShowLayout(){
	  
	JPanel top = new JPanel();
	JPanel centre = new JPanel();
	JPanel bottom = new JPanel();
	
	// top
	top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	top.add(lblMovie);
	top.add(cmbMovie);
	
	top.add(lblRoom);
	top.add(txtRoom);
	
	top.add(lblPrice);
	top.add(txtPrice);
	
	top.add(lblAvailSeats);
	top.add(cmbAvailSeats);
	
	centre.add(lblday);
	centre.add(cmbday);
	
	centre.add(lblmonth);
	centre.add(cmbmonth);
	
	centre.add(lblyear);
	centre.add(cmbyear);
	
	centre.add(lblhours);
	centre.add(cmbhours);
	
	centre.add(lblminutes);
	centre.add(cmbminutes);
	
	//bottom
	bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	bottom.add(btnAdminInsertShow);
	bottom.add(btnadminHome);
	
	setLayout(new BorderLayout());
	add(top,"North");
	add(centre,"Center");
	add(bottom,"South");
	
	 } 

private void InitshowCombo(){

	cmbMovie = new JComboBox();
	String[] shwList;
	cmbMovie.removeAllItems();

				try {
					String[] mvList = displayMovie(); 
					//String[] shwList = displayShowByMovie();
					
					for(int i =0 ;i<mvList.length;i++){
						
						cmbMovie.addItem(mvList[i]);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	cmbAvailSeats = new JComboBox();
	for(int i = 1;i<=50;i++){
		cmbAvailSeats.addItem(i);
	}
	
	cmbday = new JComboBox();
	for(int i = 1;i<=31;i++){
		cmbday.addItem(i);
	}
	
	cmbmonth = new JComboBox();
	for(int i = 1;i<=12;i++){
		cmbmonth.addItem(i);
	}
	
	cmbyear = new JComboBox();
	for(int i = 2016;i<=2018;i++){
		cmbyear.addItem(i);
	}
	cmbhours = new JComboBox();
	for(int i = 0;i<=23;i++){
		cmbhours.addItem(i);
	}
	cmbminutes = new JComboBox();
	for(int i = 0;i<=60;i=i+15){
		cmbminutes.addItem(i);
	}
}
//*********************************************"
	

public void AdminUpdateMovieGUI(){
	  initAdminUpdateMovieUI();
	  doTheAdminUpdateMovieLayout();
	  btnAdminUpdateMovie.addActionListener(new java.awt.event.ActionListener(){
	
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	    // TODO Auto-generated method stub
		   try {
			   updateMovie();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot update movie");
		}
	   }
	   
	  });
	  
	  btnadminHome.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("Admin Home","adminHome");
		   }
		   
		  });
	 }  

public void initAdminUpdateMovieUI(){

	lblMovieName = new JLabel( "MovieName");
	lblRank = new JLabel( "Rank");

	cmbMovie = new JComboBox();
	
	
	try {
		String[] mvList = displayMovie(); 
		for(int i =0 ;i<mvList.length;i++){
			
			cmbMovie.addItem(mvList[i]);
		}
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	cmbRank = new JComboBox();
	
	for(int i =1 ;i<=5;i++){
		cmbRank.addItem(i);
	}
	


	btnAdminUpdateMovie = new JButton("AdminUpdateMovie");
	btnadminHome = new JButton("adminHome");

	
	 } 

public void doTheAdminUpdateMovieLayout(){
	  
	JPanel top = new JPanel();	
	JPanel bottom = new JPanel();
	
	// top
	top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	top.add(lblMovieName);
	top.add(cmbMovie);
	
	top.add(lblRank);
	top.add(cmbRank);
		
	//bottom
	bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	bottom.add(btnAdminUpdateMovie);
	bottom.add(btnadminHome);
	
	setLayout(new BorderLayout());
	add(top,"North");
	add(bottom,"South");
	 } 

//*********************************************"
	
public void AdminUpdateShowGUI(){
	  initAdminUpdateShowUI();
	  doTheAdminUpdateShowLayout();
	  btnAdminUpdateShow.addActionListener(new java.awt.event.ActionListener(){
	
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	    // TODO Auto-generated method stub
		   try {
			if(updateShow()){
				String[] shwList;
				cmbShowDateTime.removeAllItems();
					shwList = displayShow();
					for(int i =0 ;i<shwList.length;i++){
						
						cmbShowDateTime.addItem(shwList[i]);
					}
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	  });
	  
	  cmbShowDateTime.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   
			   try {
   				for(int i =0 ;i<showPriceList.length;i++){
						
   					//if the selected show equals to the one in the array
						if (cmbShowDateTime.getSelectedItem().toString().equals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(showPriceList[i].getShowDateTime()))){
							
						  //showPriceList
							txtRoom.setText(String.valueOf(showPriceList[i].getRoomNo()));
							txtPrice.setText(String.valueOf(showPriceList[i].getShowprice()));
							cmbAvailSeats.setSelectedIndex(showPriceList[i].getAvailableSeats()-1);						
							
						}
					}	
   		   } catch (Exception e) {
   				// TODO Auto-generated catch block
   				
   			}
			 
		   }
		   
		  });
	  
	  btnadminHome.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("Admin Home","adminHome");
		   }
		   
		  });
	 }  

public void initAdminUpdateShowUI(){
	lblMovie = new JLabel( "Movie");
	lblRoom = new JLabel( "Room");
	lblPrice = new JLabel( "Price");
	lblAvailSeats = new JLabel( "AvailSeats");
	lblDateTime = new JLabel( "DateTime");

	lblday = new JLabel( "day");
	lblmonth = new JLabel( "month");
	lblyear = new JLabel( "year");
	lblhours = new JLabel( "hours");
	lblminutes = new JLabel( "minutes");
	
	cmbMovie = new JComboBox();
	cmbShowDateTime = new JComboBox();
	cmbShowDateTime.removeAllItems();
	String[] shwList;
	
	try {
		shwList = displayShow();
		for(int i =0 ;i<shwList.length;i++){
			
			cmbShowDateTime.addItem(shwList[i]);
		}
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	txtRoom = new JTextField("",20);
	txtPrice = new JTextField("",20);
	txtAvailSeats = new JTextField("",20);
	
	InitshowCombo();
	
	btnAdminUpdateShow = new JButton("AdminUpdateShow");
	btnadminHome = new JButton("adminHome");

	 } 

public void doTheAdminUpdateShowLayout(){
	JPanel top = new JPanel();
	JPanel centre = new JPanel();
	JPanel bottom = new JPanel();
	
	// top
	top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	
	top.add(lblDateTime);
	top.add(cmbShowDateTime);
	
	top.add(lblMovie);
	top.add(cmbMovie);
	
	top.add(lblRoom);
	top.add(txtRoom);
	
	top.add(lblPrice);
	top.add(txtPrice);
	
	top.add(lblAvailSeats);
	top.add(cmbAvailSeats);
	
	centre.add(lblday);
	centre.add(cmbday);
	
	centre.add(lblmonth);
	centre.add(cmbmonth);
	
	centre.add(lblyear);
	centre.add(cmbyear);
	
	centre.add(lblhours);
	centre.add(cmbhours);
	
	centre.add(lblminutes);
	centre.add(cmbminutes);
	
	//bottom
	bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	bottom.add(btnAdminUpdateShow);
	bottom.add(btnadminHome);
	
	setLayout(new BorderLayout());
	add(top,"North");
	add(centre,"Center");
	add(bottom,"South");
	 } 

//*********************************************"
	

public void AdminDeleteMovieGUI(){
	  initAdminDeleteMovieUI();
	  doTheAdminDeleteMovieLayout();
	  btnAdminDeleteMovie.addActionListener(new java.awt.event.ActionListener(){
	
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	    // TODO Auto-generated method stub
		   try {
			deleteMovie();
			cmbMovie.removeAllItems();
			String[] mvList;
			
				mvList = displayDeleteMovie();
				for(int i =0 ;i<mvList.length;i++){
					
					cmbMovie.addItem(mvList[i]);
				}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	  });
	  
	  btnadminHome.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("Admin Home","adminHome");
		   }
		   
		  });
	 } 

public void initAdminDeleteMovieUI(){
	lblMovie = new JLabel( "Movie");
	cmbMovie = new JComboBox();
	cmbMovie.removeAllItems();

		String[] mvList;
		try {
			mvList = displayDeleteMovie();
			for(int i =0 ;i<mvList.length;i++){
				
				cmbMovie.addItem(mvList[i]);
			}
			
		btnAdminDeleteMovie = new JButton("AdminDeleteMovie");
		btnadminHome = new JButton("adminHome");

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//String[] shwList = displayShowByMovie();
		
		

	 } 

public void doTheAdminDeleteMovieLayout(){
	  
	JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	
	// top
	top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	top.add(lblMovie);
	top.add(cmbMovie);
	
	//bottom
	bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	bottom.add(btnAdminDeleteMovie);
	bottom.add(btnadminHome);
	
	
	setLayout(new BorderLayout());
	add(top,"North");
	add(bottom,"South");
	
	
	 } 
//*********************************************"
	
public void AdminDeleteShowGUI(){
	  initAdminDeleteShowUI();
	  doTheAdminDeleteShowLayout();
	  btnAdminDeleteShow.addActionListener(new java.awt.event.ActionListener(){
	
	   @Override
	   public void actionPerformed(ActionEvent arg0) {
	    // TODO Auto-generated method stub
		   try {
			deleteShow();
			cmbShowDateTime.removeAllItems();
			String[] shList;
			
				shList = displayDeleteShow();
				for(int i =0 ;i<shList.length;i++){
					
					cmbShowDateTime.addItem(shList[i]);
				}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	  });
	  
	  btnadminHome.addActionListener(new java.awt.event.ActionListener(){
			
		   @Override
		   public void actionPerformed(ActionEvent arg0) {
		    // TODO Auto-generated method stub
			   newFrame("Admin Home","adminHome");
		   }
		   
		  });
	 }  

public void initAdminDeleteShowUI(){

	lblShowDateTime = new JLabel( "ShowDateTime");
	cmbShowDateTime = new JComboBox();
	cmbShowDateTime.removeAllItems();
	
	String[] shList;

	try {
		shList = displayDeleteShow();
		for(int i =0 ;i<shList.length;i++){
			
			cmbShowDateTime.addItem(shList[i]);
		}


	btnAdminDeleteShow = new JButton("AdminDeleteShow");
	btnadminHome= new JButton("adminHome");

	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	
	 } 

public void doTheAdminDeleteShowLayout(){


	JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	
	// top
	top.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	top.add(lblShowDateTime);
	top.add(cmbShowDateTime);	
	
	//bottom
	bottom.setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
	bottom.add(btnAdminDeleteShow);
	bottom.add(btnadminHome);
	
	setLayout(new BorderLayout());
	add(top,"North");
	add(bottom,"South");
	
	 } 

//*********************************************"

	
	//******************************USER INTERFACE ENDS ***************************
	//**************************************************
	public boolean insertUser() throws ParseException{
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	 	        
	 	        String userRole = "user";
	 	        
	 	        if(useroption.isSelected())
	 	        	userRole = "user";      
	 	        else
	 	        	userRole = "admin";
	 	        
	 if(doValidation("UserRegister").equals("valid")){     
    // Create a Message object and send to the server
	 	        		//String userName, String userPass, String fullName, String email, String phoneNo)
	 	       RegisteredUser usr = new RegisteredUser(txtUserName.getText(),txtPassword.getText(),txtFullName.getText(),txtEmail.getText(),txtphone.getText(),userRole);
	 	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,null,null,usr,"user","create");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       
	 	       if(msgResponse.getMsgRegisteredUser()!=null){
	 	    	  JOptionPane.showMessageDialog(null, "User with Name "  + msgResponse.getMsgRegisteredUser().getFullName() + " is Created");
	 	    	  return true;
	 	       }
	 }
	 else{
		 JOptionPane.showMessageDialog(null, doValidation("UserRegister"));
		 return false;
	 }
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly");
	   	return false;
	   }
		 return false;	    	 
	}
	
	public boolean loginUser() throws ParseException{
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	 	        
	 	     
	 	        String userRole = "user";
	 	        
	 	        if(useroption.isSelected())
	 	        	userRole = "user";      
	 	        else
	 	        	userRole = "admin";
	
	 	        // Create a Message object and send to the server
	 	        		//String userName, String userPass, String fullName, String email, String phoneNo)
//	 	       RegisteredUser usr = new RegisteredUser("codeuser3","codepass1","codeName1","shahemail1@g.com","133-456-7890");
	 	      
        if(doValidation("UserLogin").equals("valid")){
	 	        
	 	      RegisteredUser usr = new RegisteredUser(txtUserName.getText(),txtPassword.getText(),"","","",userRole);
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,null,null,usr,"user","login");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       
	 	       if(msgResponse.getMsgRegisteredUser()!=null){
	 	    	  JOptionPane.showMessageDialog(null, userRole + " with Name "  + msgResponse.getMsgRegisteredUser().getFullName() + " is found");
	 	    	loggedUser = msgResponse.getMsgRegisteredUser().getUserName();
	 	    	loggedPass = msgResponse.getMsgRegisteredUser().getUserPass(); 
	 	    	loggedUserId = msgResponse.getMsgRegisteredUser().getUserID();
	 	    	loggedUserRole = msgResponse.getMsgRegisteredUser().getUserType();
	 	    	
	 	    	  return true;
	 	       }
	 	    	else{
	 	    		JOptionPane.showMessageDialog(null, "incorrect credentials");
	 	    		return false;
	 	    	}
        }
        else{
        	JOptionPane.showMessageDialog(null, doValidation("UserLogin"));
        }
	 	       
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly");
	   }
	    	
		 return false;
	}
	
	public void updateUser() throws ParseException{
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	

   if(doValidation("UserUpdate").equals("valid")){   
	 	        // Create a Message object and send to the server
	 	        		//String userName, String userPass, String fullName, String email, String phoneNo)
	 	       RegisteredUser usr = new RegisteredUser(loggedUser,txtPassword.getText(),txtFullName.getText(),txtEmail.getText(),txtphone.getText());
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	       	MovieMessage msgSend = new MovieMessage(null,null,null,usr,"user","update");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       
	 	       if(msgResponse.getMsgRegisteredUser()!=null)
	 	    	  JOptionPane.showMessageDialog(null, "User with Name "  + msgResponse.getMsgRegisteredUser().getFullName() + " is updated");
	 	    	else
	 	    		 JOptionPane.showMessageDialog(null, "User not found");
	 	       
   }
   else{
		 JOptionPane.showMessageDialog(null, doValidation("UserUpdate"));
	 }
   
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly");
	   }
	    	    	 
	}

	//**************************************************
	
	public void insertMovie() throws ParseException{
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
       if(doValidation("AdminInsertMovie").equals("valid")){   
      
	 	        // Create a Message object and send to the server
	 	        		//String userName, String userPass, String fullName, String email, String phoneNo)
	 	       Movie mv = new Movie(txtMovieName.getText(),Integer.parseInt(cmbRank.getSelectedItem().toString()));
	 	 	     
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(mv,null,null,null,"movie","create");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       
	 	       if(msgResponse.getMsgMovie()!=null){
	 	    	  JOptionPane.showMessageDialog(null, "Movie with Name "  + msgResponse.getMsgMovie().getMovieName() + " is inserted");
	 	       }
       }
		  	 else{
		  		 JOptionPane.showMessageDialog(null, doValidation("AdminInsertMovie"));
		  		
		  	 }
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly");
	   }
	    	    	 
	}
	
	public void updateMovie() throws ParseException{
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
       if(doValidation("AdminUpdateMovie").equals("valid")){ 
	 	        // Create a Message object and send to the server
	 	        		//String userName, String userPass, String fullName, String email, String phoneNo)
	 	       Movie mv = new Movie(cmbMovie.getSelectedItem().toString(),Integer.parseInt(cmbRank.getSelectedItem().toString()));
	 
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(mv,null,null,null,"movie","update");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       
	 	       if(msgResponse.getMsgMovie()!=null){
	 	    	  JOptionPane.showMessageDialog(null, "Movie with Name "  + msgResponse.getMsgMovie().getMovieName() + " is updated");
	 	       }
       }
  	 else{
  		 JOptionPane.showMessageDialog(null, doValidation("AdminUpdateMovie"));
  		
  	 }

	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
	    	    	 
	}
  
	public void deleteMovie() throws ParseException{
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	
	 	        // Create a Message object and send to the server
	 	        		//String userName, String userPass, String fullName, String email, String phoneNo)
	 	       Movie mv = new Movie(cmbMovie.getSelectedItem().toString(),1);
	 
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(mv,null,null,null,"movie","cancel");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       
	 	       if(msgResponse.getMsgMovie()!=null){
	 	    	  JOptionPane.showMessageDialog(null, "Movie with Name "  + msgResponse.getMsgMovie().getMovieName() + " is deleted");
	 	       }
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
	    	    	 
	}
 
	public String[] displayMovie() throws ParseException{
		
		String[] msgTable= new String[5];
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	
	 	        // Create a Message object and send to the server
	 	        		//String userName, String userPass, String fullName, String email, String phoneNo)
	 	       Movie mv = new Movie("All",0);
	 	      
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(mv,null,null,null,"movie","display");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	      msgTable= new String[msgResponse.getMsgMovieCount()];	 	      
	 	       
	 	       for(int i =0;i<msgResponse.getMsgMovieCount();i++){
	 	    	  msgTable[i] = msgResponse.getMsgMovieList()[i].getMovieName();
	 	    	  System.out.println("Movie " + msgResponse.getMsgMovieList()[i].getMovieName());
	 	       }	 	      
	 	       
	 	       if(msgResponse.getMsgMovie()!=null){
	 	    	  //JOptionPane.showMessageDialog(null, "Movie with Name "  + msgResponse.getMsgMovie().getMovieName() + " is displayed");
	 	       }
	 	       
	 	     
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
		 return msgTable;
	    	    	 
	}
	
	public String[] displayDeleteMovie() throws ParseException{
		
		String[] msgTable= new String[5];
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	
	 	        // Create a Message object and send to the server
	 	        		//String userName, String userPass, String fullName, String email, String phoneNo)
	 	       Movie mv = new Movie("All",0);
	 	      
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(mv,null,null,null,"DeleteMovie","display");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	      msgTable= new String[msgResponse.getMsgMovieCount()];	 	      
	 	       
	 	       for(int i =0;i<msgResponse.getMsgMovieCount();i++){
	 	    	  msgTable[i] = msgResponse.getMsgMovieList()[i].getMovieName();
	 	    	  System.out.println("Movie " + msgResponse.getMsgMovieList()[i].getMovieName());
	 	       }	 	      
	 	       
	 	       if(msgResponse.getMsgMovie()!=null){
	 	    	  //JOptionPane.showMessageDialog(null, "Movie with Name "  + msgResponse.getMsgMovie().getMovieName() + " is displayed");
	 	       }
	 	       
	 	     
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
		 return msgTable;
	    	    	 
	}
//	//**************************************************
	
	public void insertShow() throws ParseException{
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	 if(doValidation("AdminInsertShow").equals("valid")){
		 
	 	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	      String dateInString = cmbyear.getSelectedItem().toString() + "-" + cmbmonth.getSelectedItem().toString() + "-" + cmbday.getSelectedItem().toString() + 
	 		 	    " " + cmbhours.getSelectedItem().toString() + ":" + cmbminutes.getSelectedItem().toString() + ":00" ;
	 	     Date dt = sdf.parse(dateInString);
	 	      
	 	    Movie mv = new Movie(cmbMovie.getSelectedItem().toString(),1);
	 	        // Create a Message object and send to the server
	 	        		//Movie movie, Date showDateTime, int roomNo, float showprice
	 	       Show shw = new Show(mv,dt,Integer.parseInt(txtRoom.getText()),Integer.parseInt(cmbAvailSeats.getSelectedItem().toString()),Float.parseFloat(txtPrice.getText()));
	 	 	      
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,shw,null,null,"show","create");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       
	 	       if(msgResponse.getMsgShow()!=null){
	 	    	  JOptionPane.showMessageDialog(null, "Show at "  + msgResponse.getMsgShow().getShowDateTime().toString() + " is inserted");
	 	       }
	 }
	 else{
		 JOptionPane.showMessageDialog(null, doValidation("AdminInsertShow"));
	 }

	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
	    	    	 
	}
		
	public boolean updateShow() throws ParseException{
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());

       if(doValidation("AdminUpdateShow").equals("valid")){ 
	 	       
 	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	        String dateInString = cmbyear.getSelectedItem().toString() + "-" + cmbmonth.getSelectedItem().toString() + "-" + cmbday.getSelectedItem().toString() + 
	 		 	    " " + cmbhours.getSelectedItem().toString() + ":" + cmbminutes.getSelectedItem().toString() + ":00" ;
	 	     Date updatedt = sdf.parse(dateInString);
	 	     Date dt = sdf.parse(cmbShowDateTime.getSelectedItem().toString());
	 	      
	 	    Movie mv = new Movie(cmbMovie.getSelectedItem().toString(),1);
	 	        // Create a Message object and send to the server
	 	        		//Movie movie, Date showDateTime, int roomNo, float showprice
	 	       Show shw = new Show(mv,dt,Integer.parseInt(txtRoom.getText()),Integer.parseInt(cmbAvailSeats.getSelectedItem().toString()),Float.parseFloat(txtPrice.getText()));
	 	       shw.setUpdateShowDateTime(updatedt);
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,shw,null,null,"show","update");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       
	 	       if(msgResponse.getMsgShow()!=null){
	 	    	  JOptionPane.showMessageDialog(null, "Show at "  + msgResponse.getMsgShow().getShowDateTime().toString() + " is updated");
	 	    	  return true;
	 	       }
       }
  	 else{
  		 JOptionPane.showMessageDialog(null, doValidation("AdminUpdateShow"));
  		 return false;
  		
  	 }
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
		 return false; 	    	 
	}
		
	public String[] displayAllShow() throws ParseException{
		
		String[] msgTable= new String[5];
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	 	       Movie mv = new Movie("Air Force One",1);
	 	       
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	     String dateInString = "2016-08-24 10:20:00";
	 	     Date dt = sdf.parse(dateInString);
//	 	      dt.setYear(2016);
//	 	      dt.setMonth(10);
	 	      
	 	 
	 	        // Create a Message object and send to the server
	 	        		//Movie movie, Date showDateTime, int roomNo, float showprice
	 	       Show shw = new Show(mv,dt,15,(float)12.00);
	 
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,shw,null,null,"show","display");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       msgTable= new String[msgResponse.getMsgShowCount()];	 	      
	 	       
	 	       for(int i =0;i<msgResponse.getMsgShowCount();i++){
	 	    	  msgTable[i] = df.format(msgResponse.getMsgShowList()[i].getShowDateTime());
	 	    	  System.out.println("Show " + df.format(msgResponse.getMsgShowList()[i].getShowDateTime()));
	 	       }	 	      
	 	       
	 	       
	 	       if(msgResponse.getMsgShow()!=null){
	 	    	  //JOptionPane.showMessageDialog(null, "Show at "  + msgResponse.getMsgShow().getShowDateTime().toString() + " is updated");
	 	       }
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
		 return msgTable;	 
	}
	
	public String[] displayDeleteShow() throws ParseException{
		
		String[] msgTable= new String[5];
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	 	       Movie mv = new Movie("Air Force One",1);
	 	       
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	     String dateInString = "2016-08-24 10:20:00";
	 	     Date dt = sdf.parse(dateInString);
//	 	      dt.setYear(2016);
//	 	      dt.setMonth(10);
	 	      
	 	 
	 	        // Create a Message object and send to the server
	 	        		//Movie movie, Date showDateTime, int roomNo, float showprice
	 	       Show shw = new Show(mv,dt,15,(float)12.00);
	 
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,shw,null,null,"DeleteShow","display");
	 	       
	 	       toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       msgTable= new String[msgResponse.getMsgShowCount()];	 	      
	 	       
	 	       for(int i =0;i<msgResponse.getMsgShowCount();i++){
	 	    	  msgTable[i] = df.format(msgResponse.getMsgShowList()[i].getShowDateTime());
	 	    	  System.out.println("Show " + df.format(msgResponse.getMsgShowList()[i].getShowDateTime()));
	 	       }	 	      
	 	       
	 	       
	 	       if(msgResponse.getMsgShow()!=null){
	 	    	  //JOptionPane.showMessageDialog(null, "Show at "  + msgResponse.getMsgShow().getShowDateTime().toString() + " is updated");
	 	       }
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
		 return msgTable;	 
	}

	public String[] displayShowByMovie() throws ParseException{
		
		String[] msgTable= new String[5];
		showPriceList= new Show[5];
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	 	      
	 	       
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	     String dateInString = "2016-08-24 10:20:00";
	 	     Date dt = sdf.parse(dateInString);
//	 	      dt.setYear(2016);
//	 	      dt.setMonth(10);
	 	      
	 	    Movie mv = new Movie(cmbMovie.getSelectedItem().toString(),1);  //pass movie name in here
	 	        // Create a Message object and send to the server
	 	        		//Movie movie, Date showDateTime, int roomNo, float showprice
	 	       Show shw = new Show(mv,dt,15,(float)12.00);
	 
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,shw,null,null,"showByMovie","display");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       msgTable= new String[msgResponse.getMsgShowCount()];	
	 	       showPriceList= new Show[msgResponse.getMsgShowCount()];	
	 	       
	 	       for(int i =0;i<msgResponse.getMsgShowCount();i++){
	 	    	  msgTable[i] = df.format(msgResponse.getMsgShowList()[i].getShowDateTime());
	 	    	  System.out.println("Show " + df.format(msgResponse.getMsgShowList()[i].getShowDateTime()));
	 	       }	 	      
	 	       
	 	      showPriceList = msgResponse.getMsgShowList();
	 	       
	 	       if(msgResponse.getMsgShow()!=null){
	 	    	  //JOptionPane.showMessageDialog(null, "Show at "  + msgResponse.getMsgShow().getShowDateTime().toString() + " is updated");
	 	       }
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
		 return msgTable;	 
	}
	
	public String[] displayShow() throws ParseException{
		
		String[] msgTable= new String[5];
		showPriceList= new Show[5];
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	 	      
	 	       
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	     String dateInString = "2016-08-24 10:20:00";
	 	     Date dt = sdf.parse(dateInString);
//	 	      dt.setYear(2016);
//	 	      dt.setMonth(10);
	 	      
//	 	    Movie mv = new Movie(cmbMovie.getSelectedItem().toString(),1);  //pass movie name in here
//	 	        // Create a Message object and send to the server
//	 	        		//Movie movie, Date showDateTime, int roomNo, float showprice
//	 	       Show shw = new Show(mv,dt,15,(float)12.00);
	 	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,null,null,null,"show","display");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       msgTable= new String[msgResponse.getMsgShowCount()];	
	 	       showPriceList= new Show[msgResponse.getMsgShowCount()];	
	 	       
	 	       for(int i =0;i<msgResponse.getMsgShowCount();i++){
	 	    	  msgTable[i] = df.format(msgResponse.getMsgShowList()[i].getShowDateTime());
	 	    	  System.out.println("Show " + df.format(msgResponse.getMsgShowList()[i].getShowDateTime()));
	 	       }	 	      
	 	       
	 	      showPriceList = msgResponse.getMsgShowList();
	 	       
	 	       if(msgResponse.getMsgShow()!=null){
	 	    	  //JOptionPane.showMessageDialog(null, "Show at "  + msgResponse.getMsgShow().getShowDateTime().toString() + " is updated");
	 	       }
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
		 return msgTable;	 
	}
	
	public void deleteShow() throws ParseException{
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	 	       Movie mv = new Movie("Air Force One",1);
	 	       
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	     String dateInString = cmbShowDateTime.getSelectedItem().toString();
	 	     Date dt = sdf.parse(dateInString);
//	 	      dt.setYear(2016);
//	 	      dt.setMonth(10);
	 	      
	 	 
	 	        // Create a Message object and send to the server
	 	        		//Movie movie, Date showDateTime, int roomNo, float showprice
	 	       Show shw = new Show(mv,dt,0,(float)0.00);
	 
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,shw,null,null,"show","cancel");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       
	 	       if(msgResponse.getMsgMovie()!=null){
	 	    	  JOptionPane.showMessageDialog(null, "Show at "  + msgResponse.getMsgShow().getShowDateTime().toString() + " is cancelled");
	 	       }
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
	    	    	 
	}
	
	//**************************************************
	public boolean insertReservation() throws ParseException{
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	

      if(doValidation("UserBookTicket").equals("valid")){      
	 	       
	 	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	    // String showddateInString = ;  //"2016-12-08 09:30:30"; //12 id  //must be from the show timings only
	 	     Date showdt = sdf.parse(cmbDateTime.getSelectedItem().toString());	 	     
	 	     Date tickdt = sdf.parse(sdf.format(new Date()));
	 	     
	 	     
	 	    
	 	    Movie mv = new Movie(cmbMovie.getSelectedItem().toString(),1);
	 	    Show shw = new Show(mv,showdt,Integer.parseInt(cmbQty.getSelectedItem().toString()),Float.parseFloat(txtAmnt.getText()));
	 	    RegisteredUser usr = new RegisteredUser(loggedUser,loggedPass,"","","",loggedUserRole); // 5 id using username
	 	   // int qty = 1;
	 	        // Create a Message object and send to the server
	 	        		//RegisteredUser regUser, Show show, String ccCard, int ticketQty, float ticketAmount,Date ticketDate
	 	    Reservation rsv = new Reservation(usr,shw,txtCreditC.getText(),Integer.parseInt(cmbQty.getSelectedItem().toString()),Float.parseFloat(txtAmnt.getText()),tickdt);
	 
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,null,rsv,null,"reservation","create");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       
	 	       if(msgResponse.getMsgReservation()!=null){
	 	    	  JOptionPane.showMessageDialog(null, "Reservation with "  + msgResponse.getMsgReservation().getTicketNo() + " is inserted");
	 	    	  return true;
	 	       }
	 	     }
	 		 else{
	 			 JOptionPane.showMessageDialog(null, doValidation("UserBookTicket"));
	 			return false;
	 			 
	 		 }
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
		 return false;    	 
	}
	
	public Reservation printReservation() throws ParseException{
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	 	       
	 	       
	 	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	     String dateInString = "2016-12-07 09:30:30"; //12 id  //must be from the show timings only
	 	     Date dt = sdf.parse(dateInString);
	 	    
	 	    Movie mv = new Movie("Independence Day",1);
	 	    Show shw = new Show(mv,dt,15,(float)12.00);
	 	    RegisteredUser usr = new RegisteredUser("codeuser1","codepass1","codeName1","shahemail1@g.com","133-456-7890"); // 5 id
	 	    int qty = 1;
	 	        // Create a Message object and send to the server
	 	        		//RegisteredUser regUser, Show show, String ccCard, int ticketQty, float ticketAmount,Date ticketDate
	 	    Reservation rsv = new Reservation(usr,shw,"1234567890123456",qty,(qty*shw.getShowprice()),dt);
	 
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,null,rsv,null,"reservation","print");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       
	 	       if(msgResponse.getMsgReservation()!=null){
	 	    	  //JOptionPane.showMessageDialog(null, "Reservation with ticket " + msgResponse.getMsgReservation().getTicketNo() + " for " + msgResponse.getMsgReservation().getShow().getMovie().getMovieName() + " is printed");
	 	    	   return msgResponse.getMsgReservation();
	 	       } 
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
		 
		 return null;
	}
	
	public void deleteReservation() throws ParseException{
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	 	       
	 	       
	 	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	     String dateInString = "2016-08-24 10:20:00"; //12 id
	 	     Date dt = sdf.parse(dateInString);
	 	    
	 	    Movie mv = new Movie("Independence Day",1);
	 	    Show shw = new Show(mv,dt,15,(float)12.00);
	 	    RegisteredUser usr = new RegisteredUser("codeuser1","codepass1","codeName1","shahemail1@g.com","133-456-7890"); // 5 id
	 	    int qty = 1;
	 	        // Create a Message object and send to the server
	 	        		//RegisteredUser regUser, Show show, String ccCard, int ticketQty, float ticketAmount,Date ticketDate
	 	    Reservation rsv = new Reservation(usr,shw,"1234567890123456",qty,(qty*shw.getShowprice()),dt);
	 	   rsv.setTicketNo(Integer.parseInt(cmbTicket.getSelectedItem().toString()));
	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,null,rsv,null,"reservation","cancel");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       
	 	       if(msgResponse.getMsgMovie()!=null){
	 	    	  JOptionPane.showMessageDialog(null, "Reservation at "  + msgResponse.getMsgReservation().toString() + " is cancelled");
	 	       }
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
	    	    	 
	}
	
	
	public String[] displayUserReservation() throws ParseException{
		
		String[] msgTable= new String[5];
		userTicketList= new Reservation[5];
		
		 try {
	 	        // Establish connection with the server
	 	        Socket socket = new Socket(this.hostname, this.port);
	 	        
	 	        // Create an output stream to the server
	 	        ObjectOutputStream toServer =
	 	          new ObjectOutputStream(socket.getOutputStream());
	 	        
	 	       // Create an output stream to the server
	 	        ObjectInputStream fromServer =
	 	          new ObjectInputStream(socket.getInputStream());
	
	 	       Movie mv = new Movie("Air Force One",1);
	 	       
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	     String dateInString = "2016-08-24 10:20:00";
	 	     Date dt = sdf.parse(dateInString);
//	 	      dt.setYear(2016);
//	 	      dt.setMonth(10);
	 	      
	 	    RegisteredUser usr = new RegisteredUser(loggedUser,loggedPass,"","","");
	 	    usr.setUserID(loggedUserId);
	 	    
	 	        // Create a Message object and send to the server	 	        		 	 	       
	 	       //Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,String msgTable, String msgAction
				
	 	      MovieMessage msgSend = new MovieMessage(null,null,null,usr,"deleteReservation","display");
	 	       
	 	        toServer.writeObject(msgSend);
	 	        
	 	       MovieMessage msgResponse = (MovieMessage)fromServer.readObject();
	 	       msgTable= new String[msgResponse.getMsgReservationCount()];	 	 //for ticket no only      
	 	       userTicketList= new Reservation[msgResponse.getMsgReservationCount()];	//for tostring display of ticket selected
	 	      
	 	       for(int i =0;i<msgResponse.getMsgReservationCount();i++){
	 	    	  msgTable[i] = String.valueOf(msgResponse.getMsgReservationList()[i].getTicketNo());	 	    	
	 	    	  System.out.println("Reservation " + String.valueOf(msgResponse.getMsgReservationList()[i].getTicketNo()));
	 	       }	 	      
	 	       
	 	      userTicketList = msgResponse.getMsgReservationList();
	 	      
	 	       if(msgResponse.getMsgShow()!=null){
	 	    	  //JOptionPane.showMessageDialog(null, "Show at "  + msgResponse.getMsgShow().getShowDateTime().toString() + " is updated");
	 	       }
	 	       
	 	    }       	 	        
	   catch (Exception ex) {
	   	JOptionPane.showMessageDialog(null, "Please input all fields properly such as date in yyyy-MM-dd format");
	   }
		 return msgTable;	 
	}
    
	private void newFrame(String title,String formType){
		
		   contentPane.remove(0);
		   contentPane.add(new MovieClient(formType, "localhost", 8000));
		   contentPane.repaint();
		   contentPane.doLayout();		
		   f.setSize(1400, 300);
		   f.setTitle(title);
		   f.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		clientObj = new MovieClient("UserLogin","localhost",8000);
//			clientObj.printReservation();
		
		// TODO Auto-generated method stub
		f = new JFrame("Movie Ticketing System");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = f.getContentPane();
			
		contentPane.add(clientObj);
		f.setSize(1400, 300); //width,height
		f.setVisible(true);
		f.setBackground(Color.YELLOW);		
	}

}
