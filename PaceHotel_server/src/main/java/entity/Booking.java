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
@Table(name = "Booking")
public class Booking implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1831837452886072165L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int bookingId;
	
	private int qtyRoom;
	private int numAdults;
	private int numChildren;
	private String note;
	private LocalDateTime bookingDateTime;
	private LocalDateTime arrivalDateTime;
	private LocalDateTime departureDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roomNo")
	private Room room;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "guestId")
	private Guest guest;
	
	@OneToMany(mappedBy = "booking")
	private List<BillDetail> billDetail;

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Booking(int qtyRoom, int numAdults, int numChildren, String note, LocalDateTime bookingDateTime,
			LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, Room room, Employee employee, Guest guest) {
		super();
		this.qtyRoom = qtyRoom;
		this.numAdults = numAdults;
		this.numChildren = numChildren;
		this.note = note;
		this.bookingDateTime = bookingDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.departureDateTime = departureDateTime;
		this.room = room;
		this.employee = employee;
		this.guest = guest;
	}

	public Booking(int bookingId, int qtyRoom, int numAdults, int numChildren, String note, LocalDateTime bookingDateTime,
			LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, Room room, Employee employee, Guest guest,
			List<BillDetail> billDetail) {
		super();
		this.bookingId = bookingId;
		this.qtyRoom = qtyRoom;
		this.numAdults = numAdults;
		this.numChildren = numChildren;
		this.note = note;
		this.bookingDateTime = bookingDateTime;
		this.arrivalDateTime = arrivalDateTime;
		this.departureDateTime = departureDateTime;
		this.room = room;
		this.employee = employee;
		this.guest = guest;
		this.billDetail = billDetail;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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

	public int getNumChildren() {
		return numChildren;
	}

	public void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getBookingDateTime() {
		return bookingDateTime;
	}

	public void setBookingDateTime(LocalDateTime bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}

	public LocalDateTime getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}

	public LocalDateTime getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(LocalDateTime departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public List<BillDetail> getBillDetail() {
		return billDetail;
	}

	public void setBillDetail(List<BillDetail> billDetail) {
		this.billDetail = billDetail;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", qtyRoom=" + qtyRoom + ", numAdults=" + numAdults
				+ ", numChildren=" + numChildren + ", note=" + note + ", bookingDateTime=" + bookingDateTime
				+ ", arrivalDateTime=" + arrivalDateTime + ", departureDateTime=" + departureDateTime + ", room=" + room
				+ ", employee=" + employee + ", guest=" + guest + ", billDetail=" + billDetail + "]";
	}
	
	
}
