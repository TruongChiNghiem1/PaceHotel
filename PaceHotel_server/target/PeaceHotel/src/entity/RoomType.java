package entity;

public class RoomType {
	private String roomType; 
	private double fisrtFee, secondFee, overNightFee ;
	private int qtyBed, people;
	private float disscount, surcharge;
	public RoomType(String roomType, double fisrtFee, double secondFee, double overNightFee, int qtyBed, int people,
			float disscount, float surcharge) {
		super();
		this.roomType = roomType;
		this.fisrtFee = fisrtFee;
		this.secondFee = secondFee;
		this.overNightFee = overNightFee;
		this.qtyBed = qtyBed;
		this.people = people;
		this.disscount = disscount;
		this.surcharge = surcharge;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public double getFisrtFee() {
		return fisrtFee;
	}
	public void setFisrtFee(double fisrtFee) {
		this.fisrtFee = fisrtFee;
	}
	public double getSecondFee() {
		return secondFee;
	}
	public void setSecondFee(double secondFee) {
		this.secondFee = secondFee;
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
	
	
}
