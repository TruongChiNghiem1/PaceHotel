package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Guest")
public class Guest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6249669613501457667L;
	@Id
	@Column(nullable = false)
	private String guestID;
	private String fullName, DOB;
	private int gender;
	private String phoneNo, email, IndentityCard, Address;
	private float starPoints;

	@OneToMany(mappedBy = "guest")
	private List<Booking> booking;
	
	@OneToMany(mappedBy = "guest")
	private List<Bill> bill;

	public Guest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Guest(String guestID, String fullName, String dOB, int gender, String phoneNo, String email,
			String indentityCard, String address, float starPoints) {
		super();
		this.guestID = guestID;
		this.fullName = fullName;
		DOB = dOB;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.email = email;
		IndentityCard = indentityCard;
		Address = address;
		this.starPoints = starPoints;
	}

	public Guest(String guestID, String fullName, String dOB, int gender, String phoneNo, String email,
			String indentityCard, String address, float starPoints, List<Booking> booking, List<Bill> bill) {
		super();
		this.guestID = guestID;
		this.fullName = fullName;
		DOB = dOB;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.email = email;
		IndentityCard = indentityCard;
		Address = address;
		this.starPoints = starPoints;
		this.booking = booking;
		this.bill = bill;
	}

	public String getGuestID() {
		return guestID;
	}

	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndentityCard() {
		return IndentityCard;
	}

	public void setIndentityCard(String indentityCard) {
		IndentityCard = indentityCard;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public float getStarPoints() {
		return starPoints;
	}

	public void setStarPoints(float starPoints) {
		this.starPoints = starPoints;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	public List<Bill> getBill() {
		return bill;
	}

	public void setBill(List<Bill> bill) {
		this.bill = bill;
	}

	@Override
	public String toString() {
		return "Guest [guestID=" + guestID + ", fullName=" + fullName + ", DOB=" + DOB + ", gender=" + gender
				+ ", phoneNo=" + phoneNo + ", email=" + email + ", IndentityCard=" + IndentityCard + ", Address="
				+ Address + ", starPoints=" + starPoints + ", booking=" + booking + ", bill=" + bill + "]";
	}
	
	
}
