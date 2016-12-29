/*Author : Niraj Shah Aman Arora
Description : Reservation*/
import java.util.Date;

public class Reservation implements java.io.Serializable{

	private int ticketNo;
	private RegisteredUser regUser;
	private Show show;
	private String ccCard;  //16 digits
	private int ticketQty;
	private float ticketAmount;
	private Date ticketDate;
	
	
	public Reservation(RegisteredUser regUser, Show show, String ccCard, int ticketQty, float ticketAmount,
			Date ticketDate) {
		this.regUser = regUser;
		this.show = show;
		this.ccCard = ccCard;
		this.ticketQty = ticketQty;
		this.ticketAmount = ticketAmount;
		this.ticketDate = ticketDate;
	}
	public int getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}
	public RegisteredUser getRegUser() {
		return regUser;
	}
	public void setRegUser(RegisteredUser regUser) {
		this.regUser = regUser;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	public String getCcCard() {
		return ccCard;
	}
	public void setCcCard(String ccCard) {
		this.ccCard = ccCard;
	}
	public int getTicketQty() {
		return ticketQty;
	}
	public void setTicketQty(int ticketQty) {
		this.ticketQty = ticketQty;
	}
	public float getTicketAmount() {
		return ticketAmount;
	}
	public void setTicketAmount(float ticketAmount) {
		this.ticketAmount = ticketAmount;
	}
	public Date getTicketDate() {
		return ticketDate;
	}
	public void setTicketDate(Date ticketDate) {
		this.ticketDate = ticketDate;
	}
	@Override
	public String toString() {
		return "\n Reservation [ticketNo=" + ticketNo + ", regUser=" + regUser + ", show=" + show + ", ticketQty="
				+ ticketQty + ", ticketAmount=" + ticketAmount + ", ticketDate=" + ticketDate + "]";
	}

	
	
	
}
