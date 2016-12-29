/*Author : Niraj Shah Aman Arora
Description : Show*/

import java.util.Date;

public class Show implements java.io.Serializable{

	//date time
	//movie reference
	private int showID;
	
	private Movie movie;
	private Date showDateTime;
	private Date updateShowDateTime;
	
	private int roomNo;	
	private int totalSeats=50;
	private int availableSeats;
	
	private float showprice;
	
	public Show() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Show(Movie movie, Date showDateTime, int roomNo, float showprice) {
		this.movie = movie;
		this.showDateTime = showDateTime;
		this.roomNo = roomNo;
		this.showprice = showprice;
	}
	
	
	
	public Show(Movie movie, Date showDateTime, int roomNo, int availableSeats, float showprice) {
		this.movie = movie;
		this.showDateTime = showDateTime;
		this.roomNo = roomNo;
		this.availableSeats = availableSeats;
		this.showprice = showprice;
	}



	public int getShowID() {
		return showID;
	}



	public void setShowID(int showID) {
		this.showID = showID;
	}



	public Date getUpdateShowDateTime() {
		return updateShowDateTime;
	}



	public void setUpdateShowDateTime(Date updateShowDateTime) {
		this.updateShowDateTime = updateShowDateTime;
	}



	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Date getShowDateTime() {
		return showDateTime;
	}

	public void setShowDateTime(Date showDateTime) {
		this.showDateTime = showDateTime;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public float getShowprice() {
		return showprice;
	}

	public void setShowprice(float showprice) {
		this.showprice = showprice;
	}

	public int getTotalSeats() {
		return totalSeats;
	}



	@Override
	public String toString() {
		return "\n Show [showID=" + showID + ", movie=" + movie + ", showDateTime=" + showDateTime + ", roomNo=" + roomNo
				+ ", totalSeats=" + totalSeats + ", availableSeats=" + availableSeats + ", showprice=" + showprice
				+ "]";
	}
	
	
	
	
	
	
	
	
}
