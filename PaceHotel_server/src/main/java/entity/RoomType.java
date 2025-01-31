package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "RoomType")
public class RoomType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8614463250064141211L;
	@Id
	@Column(nullable = false)
	private String roomType; 
	private double fisrtHourFee, nextHourFee, overNightFee ;
	private int qtyBed, people;
	private float disscount, surcharge;
	
	@OneToMany(mappedBy = "roomType", fetch = FetchType.EAGER)
	private List<Room> room;

	public RoomType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomType(String roomType, double fisrtHourFee, double nextHourFee, double overNightFee, int qtyBed,
			int people, float disscount, float surcharge, List<Room> room) {
		super();
		this.roomType = roomType;
		this.fisrtHourFee = fisrtHourFee;
		this.nextHourFee = nextHourFee;
		this.overNightFee = overNightFee;
		this.qtyBed = qtyBed;
		this.people = people;
		this.disscount = disscount;
		this.surcharge = surcharge;
		this.room = room;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getFisrtHourFee() {
		return fisrtHourFee;
	}

	public void setFisrtHourFee(double fisrtHourFee) {
		this.fisrtHourFee = fisrtHourFee;
	}

	public double getNextHourFee() {
		return nextHourFee;
	}

	public void setNextHourFee(double nextHourFee) {
		this.nextHourFee = nextHourFee;
	}

	public double getOverNightFee() {
		return overNightFee;
	}

	public void setOverNightFee(double overNightFee) {
		this.overNightFee = overNightFee;
	}

	public int getQtyBed() {
		return qtyBed;
	}

	public void setQtyBed(int qtyBed) {
		this.qtyBed = qtyBed;
	}

	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public float getDisscount() {
		return disscount;
	}

	public void setDisscount(float disscount) {
		this.disscount = disscount;
	}

	public float getSurcharge() {
		return surcharge;
	}

	public void setSurcharge(float surcharge) {
		this.surcharge = surcharge;
	}

	public List<Room> getRoom() {
		return room;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "RoomType [roomType=" + roomType + ", fisrtHourFee=" + fisrtHourFee + ", nextHourFee=" + nextHourFee
				+ ", overNightFee=" + overNightFee + ", qtyBed=" + qtyBed + ", people=" + people + ", disscount="
				+ disscount + ", surcharge=" + surcharge + ", room=";
	}
	
	
	
}
