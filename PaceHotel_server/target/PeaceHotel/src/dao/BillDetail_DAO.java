package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.BillDetail;

public class BillDetail_DAO {
	public List<BillDetail> getAllBillDetail(){
		List<BillDetail> billDetailList = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stm = null;
		ResultSet rs = null;
		String sql = "select * from BillDetail";
		try {
		stm = con.createStatement();
		rs = stm.executeQuery(sql);
		while(rs.next()) {
			billDetailList.add(new BillDetail(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
			
		}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return billDetailList;
	}
	
	public int findBillID(int bookingID) {
		for (int i = 0; i < getAllBillDetail().size(); i++) {
			if(getAllBillDetail().get(i).getServiceID().equalsIgnoreCase("S002") && getAllBillDetail().get(i).getBookingID() == bookingID) {
				return getAllBillDetail().get(i).getBillID();
			}
		}
		return -1;
	}
	
	public int findBillDetailID(int bookingID) {
		for (int i = 0; i < getAllBillDetail().size(); i++) {
			if(getAllBillDetail().get(i).getServiceID().equalsIgnoreCase("S002") && getAllBillDetail().get(i).getBookingID() == bookingID) {
				return getAllBillDetail().get(i).getBillID();
			}
		}
		return -1;
	}
	
	public int addBillDetail(BillDetail bd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		int n = 0;
		try {
			String sql  = "insert into BillDetail (ServiceServiceID, BillBillID, QtyService, BookingBookingID) values (?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, bd.getServiceID());
			pst.setInt(2, bd.getBillID());
			pst.setInt(3, bd.getQtyService());
			pst.setInt(4, bd.getBookingID());
			n = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n;
	}
	
	public List<Object[]> getAllService(int bookingID){
		List<Object[]> serviceList = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select bd.BillDetailID, s.ServiceName, s.ServicePrice, bd.QtyService, s.ServicePrice * bd.QtyService as Amount from BillDetail bd join Service s on bd.ServiceServiceID = s.ServiceID where BookingBookingID = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, bookingID);
			rs = pst.executeQuery();
			while(rs.next()) {
				serviceList.add(new Object[] {
					rs.getInt(1),
					rs.getString(2),
					rs.getFloat(3),
					rs.getInt(4),
					rs.getFloat(5)
				});
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return serviceList;
	}
	
	public List<Object[]> getAllServiceBill(int bookingID){
		List<Object[]> serviceList = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select b.RoomRoomNo, s.ServiceName, bd.QtyService,  s.ServicePrice, s.ServicePrice * bd.QtyService as Amount from [dbo].[Booking] b join [dbo].[BillDetail] bd on b.BookingID = bd.BookingBookingID join [dbo].[Service] s on bd.ServiceServiceID = s.ServiceID where BookingID = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, bookingID);
			rs = pst.executeQuery();
			while(rs.next()) {
				serviceList.add(new Object[] {
					rs.getString(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getFloat(4),
					rs.getFloat(5)
				});
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return serviceList;
	}
	
	public boolean DeleteService(int id) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		boolean flag = false;
		String sql = "delete from [dbo].[BillDetail] where [BillDetailID] = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			flag = true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
