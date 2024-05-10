package entity;

public class Service_room {
	int serviceID, billID, qty, bookingID;

	public Service_room(int serviceID, int billID, int qty, int bookingID) {
		super();
		this.serviceID = serviceID;
		this.billID = billID;
		this.qty = qty;
		this.bookingID = bookingID;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public int getBillID() {
		return billID;
	}

	public void setBillID(int billID) {
		this.billID = billID;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

	@Override
	public String toString() {
		return "Service_room_DAO [serviceID=" + serviceID + ", billID=" + billID + ", qty=" + qty + ", bookingID="
				+ bookingID + "]";
	}
	
}
