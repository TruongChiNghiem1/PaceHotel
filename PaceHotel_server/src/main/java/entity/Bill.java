package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Bill")
public class Bill implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6778452855554715192L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int billId;
	private double subTotal;
	private LocalDateTime paymentDate;
	private double disscount;
	
	@OneToMany(mappedBy = "bill")
	private List<BillDetail> billDetail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Guest")
	private Guest guest;

	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Bill(int billId, double subTotal, LocalDateTime paymentDate, double disscount) {
		super();
		this.billId = billId;
		this.subTotal = subTotal;
		this.paymentDate = paymentDate;
		this.disscount = disscount;
	}
	
	public Bill(int billId, double subTotal, LocalDateTime paymentDate, double disscount,
			Guest guest) {
		super();
		this.billId = billId;
		this.subTotal = subTotal;
		this.paymentDate = paymentDate;
		this.disscount = disscount;
		this.guest = guest;
	}

	public Bill(int billId, double subTotal, LocalDateTime paymentDate, double disscount, List<BillDetail> billDetail,
			Guest guest) {
		super();
		this.billId = billId;
		this.subTotal = subTotal;
		this.paymentDate = paymentDate;
		this.disscount = disscount;
		this.billDetail = billDetail;
		this.guest = guest;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getDisscount() {
		return disscount;
	}

	public void setDisscount(double disscount) {
		this.disscount = disscount;
	}

	public List<BillDetail> getBillDetail() {
		return billDetail;
	}

	public void setBillDetail(List<BillDetail> billDetail) {
		this.billDetail = billDetail;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", subTotal=" + subTotal + ", paymentDate=" + paymentDate + ", disscount="
				+ disscount + ", billDetail=" + billDetail + ", guest=" + guest + "]";
	}

	
	
}
