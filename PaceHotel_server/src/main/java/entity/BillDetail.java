package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "BillDetail")
public class BillDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7250751345866332177L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int billDetailId;
	
	private int qtyService;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "billId")
	private Bill bill;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booking")
	private Booking booking;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serviceId")
	private Service service;

	public BillDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BillDetail(int qtyService, Bill bill, Booking booking, Service service) {
		super();
		this.qtyService = qtyService;
		this.bill = bill;
		this.booking = booking;
		this.service = service;
	}

	public BillDetail(int billDetailId, int qtyService, Bill bill, Booking booking, Service service) {
		super();
		this.billDetailId = billDetailId;
		this.qtyService = qtyService;
		this.bill = bill;
		this.booking = booking;
		this.service = service;
	}

	public int getBillDetailId() {
		return billDetailId;
	}

	public void setBillDetailId(int billDetailId) {
		this.billDetailId = billDetailId;
	}

	public int getQtyService() {
		return qtyService;
	}

	public void setQtyService(int qtyService) {
		this.qtyService = qtyService;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "BillDetail [billDetailId=" + billDetailId + ", qtyService=" + qtyService + ", bill=" + bill
				+ ", booking=" + booking + ", service=" + service + "]";
	}
	
	
}
