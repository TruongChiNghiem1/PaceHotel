package dao;

import java.beans.Statement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connectDB.ConnectDB;
import entity.Booking;
import entity.Guest;

public class Booking_DAO {
	public List<Object[]> getAllBooking() {
		List<Object[]> bookingList = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		java.sql.Statement stm = null;
		ResultSet rs = null;
		String sql = "Select * from Booking";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				bookingList.add(new Object[] { rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getDate(10),
						rs.getDate(11) });
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bookingList;
	}

	public int addBooking(Booking b) throws ParseException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		Date now = new Date();
		Timestamp timestamp = new Timestamp(now.getTime());
		int n = 0;
		try {
			String sql = "insert into Booking (NumAdults, NumChildren, Note, GuestGuestID, EmployeeEmployeeID, RoomRoomNo, ArrivalDateTime) values(?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, b.getNumAdults());
			pst.setInt(2, b.getNumChild());
			pst.setString(3, b.getNote());
			pst.setString(4, b.getGuestID());
			pst.setString(5, b.getEmployeeID());
			pst.setString(6, b.getRoomNo());
			pst.setTimestamp(7, timestamp);
			n = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n;
	}

	public int addOrder(Booking b) throws ParseException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		Date now = new Date();
		Timestamp timestamp = new Timestamp(now.getTime());
		int n = 0;
		try {
			String sql = "insert into Booking (NumAdults, NumChildren, Note, GuestGuestID, EmployeeEmployeeID, RoomRoomNo, BookingDateTime) values(?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, b.getNumAdults());
			pst.setInt(2, b.getNumChild());
			pst.setString(3, b.getNote());
			pst.setString(4, b.getGuestID());
			pst.setString(5, b.getEmployeeID());
			pst.setString(6, b.getRoomNo());
			pst.setTimestamp(7, timestamp);
			n = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n;
	}
	
	public static Object[] getOneBooking(int BookingID) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from Booking where BookingID = ?";
		try {
			pst = con.prepareStatement(sql);
			
			pst.setInt(1, BookingID);
			rs = pst.executeQuery();
			if(rs.next()) {
				Object[] o = new Object[] 
						{rs.getInt(1), 
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getTimestamp(9),
						rs.getTimestamp(10),
						rs.getTimestamp(11)};
				return o;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	

	public int findBooking(int BookingID) {
		for (int i = 0; i < getAllBooking().size(); i++) {
			if(getAllBooking().get(i)[0].equals(BookingID)) {
				return i;
			}
		}
		return -1;
	}

	public int findRoomBooking(String room) {
		for (int i = 0; i < getAllBooking().size(); i++) {
			if(getAllBooking().get(i)[7].equals(room)) {
				if(getAllBooking().get(i)[10] == null) {
					return (int) getAllBooking().get(i)[0];
				}
			}
		}
		return -1;
	}
	
	public int findRoomBooking1() {
		return ((int) getAllBooking().get((getAllBooking().size()) - 1)[0]);
	}
	
	public int updateBookingToArr(int idBooking) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		Date now = new Date();
		Timestamp timestamp = new Timestamp(now.getTime());
		int n = 0;
		try {
			String sql = "update Booking set ArrivalDateTime = ? where BookingID = ?";
			pst = con.prepareStatement(sql);
			pst.setTimestamp(1, timestamp);
			pst.setInt(2, idBooking);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return findBooking(idBooking);
	}
	
	public int updateBookingToDep(int idBooking) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		Date now = new Date();
		Timestamp timestamp = new Timestamp(now.getTime());
		try {
			String sql = "update Booking set DepartureDateTime = ? where BookingID = ?";
			pst = con.prepareStatement(sql);
			pst.setTimestamp(1, timestamp);
			pst.setInt(2, idBooking);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return findBooking(idBooking);
	}
	
	public static Object[] getOneRoom(int BookingID) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select r.RoomNo, rt.RoomType,rt.FirstHourFee, rt.NextHourFee, rt.OverNightFee from [dbo].[Booking] b join [dbo].[Room] r on b.RoomRoomNo = r.RoomNo join [dbo].[RoomType] rt on rt.RoomType = r.RoomTypeRoomType where b.BookingID = ?";
		try {
			pst = con.prepareStatement(sql);
			
			pst.setInt(1, BookingID);
			rs = pst.executeQuery();
			if(rs.next()) {
				Object[] o = new Object[] 
						{rs.getString(1), 
						rs.getString(2),
						rs.getFloat(3),
						rs.getFloat(4),
						rs.getFloat(5)};
				return o;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Object[]> filterStatistics(String empID, String fromDate, String toDate) {
		List<Object[]> empList = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select r.RoomTypeRoomType, r.RoomNo, b.ArrivalDateTime, b.DepartureDateTime, g.FullName, bill.SubTotal from [dbo].[Booking] b join [dbo].[Employee] e on b.EmployeeEmployeeID = e.EmployeeID join [dbo].[Guest] g on g.GuestID = b.GuestGuestID join [dbo].[Room] r on b.RoomRoomNo = r.RoomNo join [dbo].[BillDetail] bdt on b.BookingID = bdt.BookingBookingID join [dbo].[Bill] bill on bdt.BillBillID = bill.BillID where b.EmployeeEmployeeID = ? and b.DepartureDateTime >= ? and b.DepartureDateTime <= ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, empID);
			pst.setString(2, fromDate);
			pst.setString(3, toDate);
			rs = pst.executeQuery();
			while(rs.next()) {
				empList.add(new Object[] 
						{rs.getString(1), 
						rs.getString(2),
						rs.getTimestamp(3),
						rs.getTimestamp(4),
						rs.getString(5),
						rs.getFloat(6)});
			}
			return empList;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
