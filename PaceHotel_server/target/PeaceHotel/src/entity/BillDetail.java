package entity;

public class BillDetail {
	private int billDetailID;
	private String serviceID;
	private int billID, qtyService,bookingID;
	public BillDetail(int billDetailID, String serviceID, int billID, int qtyService, int bookingID) {
		super();
		this.billDetailID = billDetailID;
		this.serviceID = serviceID;
		this.billID = billID;
		this.qtyService = qtyService;
		this.bookingID = bookingID;
	}
	public int getBillDetailID() {
		return billDetailID;
	}
	public void setBillDetailID(int billDetailID) {
		this.billDetailID = billDetailID;
	}
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public int getBillID() {
		return billID;
	}
	public void setBillID(int billID) {
		this.billID = billID;
	}
	public int getQtyService() {
		return qtyService;
	}
	public void setQtyService(int qtyService) {
		this.qtyService = qtyService;
	}
	public int getBookingID() {
		return bookingID;
	}
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	@Override
	public String toString() {
		return "BillDetail [billDetailID=" + billDetailID + ", serviceID=" + serviceID + ", billID=" + billID
				+ ", qtyService=" + qtyService + ", bookingID=" + bookingID + "]";
	}
	
	
}
