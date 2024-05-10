package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Room")
public class Room implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2110023853689899095L;

	@Id
	@Column(nullable = false)
	private String roomNo;
	
	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roomType")
	private RoomType roomType;
	
	@OneToMany(mappedBy = "room")
	private List<Booking> booking;

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Room(String roomNo, String status, RoomType roomType) {
		super();
		this.roomNo = roomNo;
		this.status = status;
		this.roomType = roomType;
	}

	public Room(String roomNo, String status, RoomType roomType, List<Booking> booking) {
		super();
		this.roomNo = roomNo;
		this.status = status;
		this.roomType = roomType;
		this.booking = booking;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "Room [roomNo=" + roomNo + ", status=" + status + ", roomType=," + "booking=" + booking
				+ "]";
	}
	
	

}
