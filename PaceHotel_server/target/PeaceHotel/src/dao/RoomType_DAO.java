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
import entity.RoomType;

public class RoomType_DAO {
	private Room_DAO listRoom = new Room_DAO();
	public List<RoomType> getAllRoomType(){
		List<RoomType> dsRoomType = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stm = null;
		ResultSet rs = null;
		String sql = "Select * from RoomType";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				dsRoomType.add(revertRowToRoomType(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsRoomType;
	}
	
	public RoomType revertRowToRoomType(ResultSet rs) {
		RoomType rt = null;
		try {
			rt = new RoomType(rs.getString(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getFloat(7), rs.getFloat(8));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rt;
	}
	
	public int addRoomType(RoomType r) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert into RoomType values(?, ?, ?, ?, ?, ?, ?, ?)";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, r.getRoomType());
			stmt.setDouble(2, r.getFisrtFee());
			stmt.setDouble(3, r.getSecondFee());
			stmt.setDouble(4, r.getOverNightFee());
			stmt.setInt(5, r.getQtyBed());
			stmt.setInt(6, r.getPeople());
			stmt.setDouble(7, r.getDisscount());
			stmt.setDouble(8, r.getSurcharge());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n;
	}
	
	public boolean deleteRoomType(String id) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from RoomType where RoomType = ?";
		for (Room r : listRoom.getAllRoom()) {
			if(r.getRoomType().equalsIgnoreCase(id)) {
				return false;
			}
		}
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public int findRoomType(String id) {
		for (int i = 0; i < getAllRoomType().size(); i++) {
			if(getAllRoomType().get(i).getRoomType().equalsIgnoreCase(id)) {
				return i;
			}
		}
		return -1;
	}
	
	public RoomType getLastRoom(String id) {
		RoomType vd = null;
		for (RoomType o : getAllRoomType()) {
			if(o.getRoomType().equals(id)) {
				vd = o;
			}
		}
		return vd;
	}
	
	public int updateRoomType(String roomType,double firstHour,double nextHour,double overNight,int qtyBed,int people,float discount,float surchage) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "update RoomType set FirstHourFee = ?,[NextHourFee] = ?,[OverNightFee] = ?,[QtyBed] = ?,[People] = ?,[Disscount] = ?,[Surcharge] = ? where RoomType= ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, firstHour);
			stmt.setDouble(2, nextHour);
			stmt.setDouble(3, overNight);
			stmt.setInt(4, qtyBed);
			stmt.setInt(5, people);
			stmt.setDouble(6, discount);
			stmt.setDouble(7, surchage);
			stmt.setString(8, roomType);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return findRoomType(roomType);
	}
}
