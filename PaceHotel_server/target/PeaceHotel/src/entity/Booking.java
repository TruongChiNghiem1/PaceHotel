package entity;

import java.sql.Date;

public class Booking {
	private int bookingID;
	private int qtyRoom, numAdults, numChild;
	private String note, guestID, employeeID, RoomNo;
//	private Date bookingDT, arrivalDT, departureDT;
	public Booking(int bookingID, int qtyRoom, int numAdults, int numChild, String note, String guestID,
			String employeeID, String roomNo) {
		super();
		this.bookingID = bookingID;
		this.qtyRoom = qtyRoom;
		this.numAdults = numAdults;
		this.numChild = numChild;
		this.note = note;
		this.guestID = guestID;
		this.employeeID = employeeID;
		RoomNo = roomNo;
	}
	public int getBookingID() {
		return bookingID;
	}
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	public int getQtyRoom() {
		return qtyRoom;
	}
	public void setQtyRoom(int qtyRoom) {
		this.qtyRoom = qtyRoom;
	}
	public int getNumAdults() {
		return numAdults;
	}
	public void setNumAdults(int numAdults) {
		this.numAdults = numAdults;
	}
	public int getNumChild() {
		return numChild;
	}
	public void setNumChild(int numChild) {
		this.numChild = numChild;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getGuestID() {
		return guestID;
	}
	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getRoomNo() {
		return RoomNo;
	}
	public void setRoomNo(String roomNo) {
		RoomNo = roomNo;
	}
	@Override
	public String toString() {
		return "Booking [bookingID=" + bookingID + ", qtyRoom=" + qtyRoom + ", numAdults=" + numAdults + ", numChild="
				+ numChild + ", note=" + note + ", guestID=" + guestID + ", employeeID=" + employeeID + ", RoomNo="
				+ RoomNo + "]";
	}
	
	
	
	
	
}
