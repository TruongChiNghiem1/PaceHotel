package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Guest;

public class Guest_DAO {
	public List<Guest> getAllGuest() {
		List<Guest> guestList = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stm = null;
		ResultSet rs = null;
		String sql = "Select * from Guest";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				guestList.add(new Guest(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getFloat(9)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guestList;
	}
	
	public static Guest getOneGuest(String indentityCard) {
//		Object[] guest = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from Guest where GuestID = ?";
		try {
			pst = con.prepareStatement(sql);
			
			pst.setString(1, indentityCard);
			rs = pst.executeQuery();
			if(rs.next()) {
				Guest guest = new Guest(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8) ,rs.getFloat(9));
				return guest;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public int addGuest(Guest g) throws ParseException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = dateFormat.parse(g.getDOB());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		int n = 0;
		try {
			String sql = "Insert into Guest values(?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, g.getGuestID());
			stmt.setString(2, g.getFullName());
			stmt.setDate(3, sqlDate);
			stmt.setInt(4, g.getGender());
			stmt.setString(5, g.getPhoneNo());
			stmt.setString(6, g.getEmail());
			stmt.setString(7, g.getIndentityCard());
			stmt.setString(8, g.getAddress());
			stmt.setFloat(9, g.getStarPoints());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public int findGuest(String GuestID) {
		for (int i = 0; i < getAllGuest().size(); i++) {
			if(getAllGuest().get(i).getGuestID().equalsIgnoreCase(GuestID)) {
				return i;
			}
		}
		return -1;
	}
}
