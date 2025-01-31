package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import UI.Bill;
import connectDB.ConnectDB;
import entity.BillEntity;

public class Bill_DAO {
	public List<BillEntity> getAllBill(){
		List<BillEntity> billList = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stm = null;
		ResultSet rs = null;
		String sql = "Select * from Bill order by BillID";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				billList.add(new BillEntity(rs.getInt(1), rs.getFloat(2), rs.getDate(3), rs.getFloat(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return billList;
	}
	
	public int findBillLastDetail() {
		return getAllBill().get(getAllBill().size()-1).getBillID();
	}
	
	
	
	public int addBill(BillEntity b) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		Date now = new Date();
		Timestamp timestamp = new Timestamp(now.getTime());
		int n = 0;
		try {
			String sql = "insert into Bill (SubTotal, PaymentDate, Disscount, GuestGuestID) values (?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setFloat(1, b.getSubTotal());
			pst.setTimestamp(2, timestamp);
			pst.setFloat(3, b.getDisscount());
			pst.setString(4, b.getGuestID());
			
			n = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n;
	}
	
	public int updateBillCheckOut(BillEntity bill) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement pst = null;
		Date now = new Date();
		
		Timestamp timestamp = new Timestamp(now.getTime());
		try {
			String sql = "update [dbo].[Bill] set [SubTotal] = ?, [PaymentDate] = ?, [Disscount] = ? where [BillID] = ?";
			pst = con.prepareStatement(sql);
			pst.setFloat(1, bill.getSubTotal());
			pst.setTimestamp(2, timestamp);
			pst.setFloat(3, bill.getDisscount());
			pst.setInt(4, bill.getBillID());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 1;
	}
}
