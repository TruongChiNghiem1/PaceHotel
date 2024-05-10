package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Service;

public class Service_DAO {
	public List<Service> getAllService() {
		List<Service> dsService = new ArrayList<Service>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stm = null;
		ResultSet rs = null;
		try {
			String sql = "select * from Service";
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				dsService.add(revertRowToService(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsService;
	}

	public Service revertRowToService(ResultSet rs) {
		Service s = null;
		try {
			s = new Service(rs.getString(1), rs.getString(2), rs.getDouble(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;

	}

	public int addService(Service s) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("insert into Service values (?, ? , ?)");
			stm.setString(1, s.getServiceID());
			stm.setString(2, s.getServiceName());
			stm.setDouble(3, s.getPrice());
			n = stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public int findService(String IDFind) {
		ConnectDB.getInstance();
		List<Service> ex = getAllService();
		for (int i = 0; i < ex.size(); i++) {
			if (ex.get(i).getServiceID().equalsIgnoreCase(IDFind)) {
				return i;
			}
		}
		return -1;
	}

	public boolean deleteService(String IDDelt) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		boolean flag = false;
		String sql = "delete from Service where ServiceID = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, IDDelt);
			stm.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public int updateService(String id, String name, String price) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		String sql = "update Service set ServiceName = ?, ServicePrice = ? where ServiceId = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, name);
			stm.setDouble(2, Double.parseDouble(price));
			stm.setString(3, id);
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return findService(id);
	}
	
	

}
