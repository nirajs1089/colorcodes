/*Author : Niraj Shah Aman Arora
Description : RegisteredUser*/
public class RegisteredUser implements java.io.Serializable{

	private String userName;
	private String userPass;
	private String fullName;
	private String email;
	private String phoneNo;
	private String userType;
	private int userID;
	
	
	public RegisteredUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RegisteredUser(String userName, String userPass, String fullName, String email, String phoneNo) {
		this.userName = userName;
		this.userPass = userPass;
		this.fullName = fullName;
		this.email = email;
		this.phoneNo = phoneNo;
	}
	
	
	public RegisteredUser(String userName, String userPass, String fullName, String email, String phoneNo,
			String userType) {
		this.userName = userName;
		this.userPass = userPass;
		this.fullName = fullName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "\n RegisteredUser [fullName=" + fullName + ", email=" + email + ", phoneNo=" + phoneNo + ", userID="
				+ userID + "]";
	}
	
	
}
