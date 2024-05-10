package entity;

import java.util.Date;

public class BillEntity {
	private int billID;
	private float subTotal;
	private Date PaymentDate;
	private float disscount;
	private String guestID;
	public BillEntity(int billID, float subTotal, Date paymentDate, float disscount, String guestID) {
		super();
		this.billID = billID;
		this.subTotal = subTotal;
		PaymentDate = paymentDate;
		this.disscount = disscount;
		this.guestID = guestID;
	}
	public int getBillID() {
		return billID;
	}
	public void setBillID(int billID) {
		this.billID = billID;
	}
	public float getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	public Date getPaymentDate() {
		return PaymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		PaymentDate = paymentDate;
	}
	public float getDisscount() {
		return disscount;
	}
	public void setDisscount(float disscount) {
		this.disscount = disscount;
	}
	public String getGuestID() {
		return guestID;
	}
	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}
	@Override
	public String toString() {
		return "Bill [billID=" + billID + ", subTotal=" + subTotal + ", PaymentDate=" + PaymentDate + ", disscount="
				+ disscount + ", guestID=" + guestID + "]";
	}
	
}
