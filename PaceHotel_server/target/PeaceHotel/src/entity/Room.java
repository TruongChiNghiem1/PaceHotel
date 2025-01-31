package entity;

public class Room {
	private String Room, RoomType , Status;

	public Room(String room, String roomType, String status) {
		super();
		Room = room;
		RoomType = roomType;
		Status = status;
	}

	public String getRoom() {
		return Room;
	}

	public void setRoom(String room) {
		Room = room;
	}

	public String getRoomType() {
		return RoomType;
	}

	public void setRoomType(String roomType) {
		RoomType = roomType;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "Room [Room=" + Room + ", RoomType=" + RoomType + ", Status=" + Status + "]";
	}
	
	
	
	
	
}
