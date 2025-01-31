package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Room;

public class Room_DAO {
	public List<Object[]> getAllInfoRoom() {
		List<Object[]> roomList = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stm = null;
		ResultSet rs = null;
		String sql = "SELECT r.RoomNo, rt.RoomType, rt.People, rt.QtyBed, rt.FirstHourFee, rt.NextHourFee,rt.OverNightFee, r.Status, rt.Disscount FROM [dbo].[Room] r JOIN [dbo].[RoomType] rt ON r.RoomTypeRoomType = rt.RoomType order by r.RoomNo";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				roomList.add(new Object[] 
						{rs.getString(1),
						rs.getString(2), 
						rs.getInt(3), 
						rs.getInt(4), 
						rs.getDouble(5), 
						rs.getDouble(6), 
						rs.getDouble(7), 
						rs.getString(8),
						rs.getFloat(9)});
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return roomList;
	}
	
	public List<Room> getAllRoom(){
		List<Room> roomList = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stm = null;
		ResultSet rs = null;
		String sql = "select * from Room order by RoomNo";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				roomList.add(new Room(rs.getString(1), rs.getString(3), rs.getString(2)));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roomList;
	}
	
	public int addRoom(Room r) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert into Room values(?, ?, ?)";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, r.getRoom());
			stmt.setString(2, r.getStatus());
			stmt.setString(3, r.getRoomType());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public Object[] getLastRoom(String newID) {
		Object[] vd = null;
		for (Object[] o : getAllInfoRoom()) {
			if(o[0].equals(newID)) {
				vd = o;
			}
		}
		return vd;
	}
	
	public Room revertRowOfTable(ResultSet rs) {
		Room r = null;
		try {
			r = new Room(rs.getString(1), rs.getString(3), rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public boolean deleteRoom(String IDdelt) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		boolean flag = false;
		String sql = "delete from Room where RoomNo = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, IDdelt);
			stmt.executeUpdate();
			flag = true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public int findRoom(String roomNo) {
		for(int i = 0; i < getAllInfoRoom().size(); i++) {
			if(getAllInfoRoom().get(i)[0].equals(roomNo)) {
				return i;
			}
		}
		return -1;
	}
	
	public int updateRoom(String roomNo, String roomStatus) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "update Room set Status = ? where RoomNo = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, roomStatus);
			stm.setString(2, roomNo);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findRoom(roomNo);
	}
	
	public Boolean findStatus(String id) {
		for(int i = 0; i < getAllInfoRoom().size(); i++) {
			if(getAllInfoRoom().get(i)[0].equals(id) && getAllInfoRoom().get(i)[7].equals("Dirty")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean findStatusOccupied(String id) {
		for(int i = 0; i < getAllInfoRoom().size(); i++) {
			if(getAllInfoRoom().get(i)[0].equals(id) && getAllInfoRoom().get(i)[7].equals("Occupied")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean findStatusOrdered(String id) {
		for(int i = 0; i < getAllInfoRoom().size(); i++) {
			if(getAllInfoRoom().get(i)[0].equals(id) && getAllInfoRoom().get(i)[7].equals("Ordered")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean findStatusReady(String id) {
		for(int i = 0; i < getAllInfoRoom().size(); i++) {
			if(getAllInfoRoom().get(i)[0].equals(id) && getAllInfoRoom().get(i)[7].equals("Ready")) {
				return true;
			}
		}
		return false;
	}
	
}
