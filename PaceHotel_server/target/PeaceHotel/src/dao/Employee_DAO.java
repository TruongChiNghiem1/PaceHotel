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
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.Employee;

public class Employee_DAO 
{
	public List<Employee> getAllEmployee()
	{
		List<Employee> dsEmployee = new ArrayList<Employee>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		Statement stm = null;
		ResultSet rs = null;
		try
		{
			String sql = "select * from Employee";
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next())
			{
				dsEmployee.add(revertRowToEmployee(rs));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return dsEmployee;
	}
	
	public Employee revertRowToEmployee(ResultSet rs) {
		Employee e = null;
		try {
			e = new Employee(rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getDouble(8),
					rs.getString(9));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}
               


	public static Employee login(String EmployeeID, String Password)
	{
		Employee tk = null;
		String sql = "select * from Employee where EmployeeID = ? and Password = ?";
		
		try
		{
			ConnectDB conDB = new ConnectDB();
			Connection con = conDB.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, EmployeeID);
			pst.setString(2, Password);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
			{
				Employee taiKhoan = new Employee(rs.getString(1), rs.getString(2));
				return taiKhoan;
			}
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
		return tk;
	}
	
	public int addEmployee(Employee e) throws ParseException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = -1;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = dateFormat.parse(e.getDOB());
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		try {
			stmt = con.prepareStatement("insert into Employee values(?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, e.getEmployeeID());
			stmt.setString(2, e.getFullName());
			stmt.setDate(3, sqlDate);
			stmt.setInt(4, e.getGender());
			stmt.setString(5, e.getPhoneNo());
			stmt.setString(6, e.getEmail());
			stmt.setString(7, "123");
			stmt.setDouble(8, e.getSalary());
			stmt.setString(9, e.getRoleID());
			n = stmt.executeUpdate();
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return n;
	}
	
	public boolean deleteEmployee(String IDDelt) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		boolean flag = false;
		String sql = "delete from Employee where EmployeeID = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, IDDelt);
			stmt.executeUpdate();
			flag = true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public int findEmployee(String IDFind) {
		List<Employee> list = getAllEmployee();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getEmployeeID().equalsIgnoreCase(IDFind)) {
				return i;
			}
		}
		return -1;
	}
	
	public int updateEmployee(String id, String name, String DOB, int gd, String phone, String email, String salary) throws ParseException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = dateFormat.parse(DOB);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		String sql = "update Employee set FullName = ?, DOB = ?, Gender = ?, PhoneNo = ?, Email = ?, Salary = ? where EmployeeID = ?";
		try {
			stm = con.prepareStatement(sql);
			stm.setString(1, name);
			stm.setDate(2, sqlDate);
			stm.setInt(3, gd);
			stm.setString(4, phone);
			stm.setString(5, email);
			stm.setDouble(6, Double.parseDouble(salary));
			stm.setString(7, id);
			stm.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return findEmployee(id);
	}
	
//	
	public Employee layNhanVienTheoMa(String mnv)
	{
		try
		{
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Employee where EmployeeID = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, mnv);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				String EmployeeID = rs.getString(1);
				String FullName = rs.getString(2);
				String DOB = rs.getString(3);
				int Gender = rs.getInt(4);
				String PhoneNo = rs.getString(5);
				String Email = rs.getString(6);
				String Password = rs.getString(7);
				Double Salary = rs.getDouble(8);
				String RoleID = rs.getString(9);
				
				Employee emp = new Employee(EmployeeID, FullName, DOB, Gender, PhoneNo, Email, Password, Gender, RoleID);
				
				return emp;
			}
		}
		catch (Exception e)
		{
			
		}
		return null;
	}
	
//	lấy mã nhân viên lớn nhất để set mã tự động
	public int layMaNVLonNhat()
	{
		int maNhanVienLonNhat = 0;
		try
		{
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Employee";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
			{
				String maNhanVien = rs.getString(1).substring(1).trim();
				if (maNhanVienLonNhat < Integer.parseInt(maNhanVien))
				{
					maNhanVienLonNhat = Integer.parseInt(maNhanVien);
				}
			}
		}
		catch (Exception e)
		{
			
		}
		return maNhanVienLonNhat;
	}
}
