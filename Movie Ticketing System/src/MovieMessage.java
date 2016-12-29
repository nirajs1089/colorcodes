/*Author : Niraj Shah Aman Arora
Description : MovieMessage*/
public class MovieMessage implements java.io.Serializable{
	
	//references to an object
	private Movie msgMovie;
	private Show msgShow;
	private Reservation msgReservation;
	private RegisteredUser msgRegisteredUser;
	private String msgTable;
	private String msgAction;
	
	//references to an object array
	private Movie[] msgMovieList;
	private Show[] msgShowList;
	private Reservation[] msgReservationList;
	private RegisteredUser[] msgRegisteredUserList;
	
	//references to an object count of array size
	private int msgMovieCount;
	private int msgShowCount;
	private int msgReservationCount;
	private int msgRegisteredUserCount;
	
	
	
	public MovieMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieMessage(Movie msgMovie, Show msgShow, Reservation msgReservation, RegisteredUser msgRegisteredUser,
			String msgTable, String msgAction) {
		this.msgMovie = msgMovie;
		this.msgShow = msgShow;
		this.msgReservation = msgReservation;
		this.msgRegisteredUser = msgRegisteredUser;
		this.msgTable = msgTable;
		this.msgAction = msgAction;
	}
	public Movie getMsgMovie() {
		return msgMovie;
	}
	public void setMsgMovie(Movie msgMovie) {
		this.msgMovie = msgMovie;
	}
	public Show getMsgShow() {
		return msgShow;
	}
	public void setMsgShow(Show msgShow) {
		this.msgShow = msgShow;
	}
	public Reservation getMsgReservation() {
		return msgReservation;
	}
	public void setMsgReservation(Reservation msgReservation) {
		this.msgReservation = msgReservation;
	}
	public RegisteredUser getMsgRegisteredUser() {
		return msgRegisteredUser;
	}
	public void setMsgRegisteredUser(RegisteredUser msgRegisteredUser) {
		this.msgRegisteredUser = msgRegisteredUser;
	}
	public String getMsgTable() {
		return msgTable;
	}
	public void setMsgTable(String msgTable) {
		this.msgTable = msgTable;
	}
	public String getMsgAction() {
		return msgAction;
	}
	public void setMsgAction(String msgAction) {
		this.msgAction = msgAction;
	}
	public Movie[] getMsgMovieList() {
		return msgMovieList;
	}
	public void setMsgMovieList(Movie[] msgMovieList) {
		this.msgMovieList = msgMovieList;
	}
	public Show[] getMsgShowList() {
		return msgShowList;
	}
	public void setMsgShowList(Show[] msgShowList) {
		this.msgShowList = msgShowList;
	}
	public Reservation[] getMsgReservationList() {
		return msgReservationList;
	}
	public void setMsgReservationList(Reservation[] msgReservationList) {
		this.msgReservationList = msgReservationList;
	}
	public RegisteredUser[] getMsgRegisteredUserList() {
		return msgRegisteredUserList;
	}
	public void setMsgRegisteredUserList(RegisteredUser[] msgRegisteredUserList) {
		this.msgRegisteredUserList = msgRegisteredUserList;
	}
	public int getMsgMovieCount() {
		return msgMovieCount;
	}
	public void setMsgMovieCount(int msgMovieCount) {
		this.msgMovieCount = msgMovieCount;
	}
	public int getMsgShowCount() {
		return msgShowCount;
	}
	public void setMsgShowCount(int msgShowCount) {
		this.msgShowCount = msgShowCount;
	}
	public int getMsgReservationCount() {
		return msgReservationCount;
	}
	public void setMsgReservationCount(int msgReservationCount) {
		this.msgReservationCount = msgReservationCount;
	}
	public int getMsgRegisteredUserCount() {
		return msgRegisteredUserCount;
	}
	public void setMsgRegisteredUserCount(int msgRegisteredUserCount) {
		this.msgRegisteredUserCount = msgRegisteredUserCount;
	}
	
	
}
