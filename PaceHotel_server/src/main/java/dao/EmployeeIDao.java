package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

import entity.Employee;

public interface EmployeeIDao extends Remote {
	public Employee login(String employeeID, String password) throws RemoteException;
	public List<Employee> getAllEmployees() throws RemoteException;
	public int addEmployee(Employee e) throws RemoteException, ParseException;
	public boolean deleteEmployee(String employeeId) throws RemoteException;
	public int findEmployee(String employeeId) throws RemoteException;
	public int updateEmployee(String id, String name, String DOB, int gender, String phone, String email, double salary) throws RemoteException, ParseException;
	public Employee layNhanVienTheoMa(String mnv) throws RemoteException;
	public int layMaNVLonNhat() throws RemoteException;
}
