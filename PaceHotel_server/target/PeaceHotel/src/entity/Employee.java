package entity;

import java.util.Date;

public class Employee 
{
	private String EmployeeID, FullName, DOB;
	private int Gender;
	private String PhoneNo, Email, Password;
	private double Salary;
	private String RoleID;
	public Employee(String employeeID, String fullName, String dOB, int gender, String phoneNo, String email, String password, double salary, String roleID) 
	{
		super();
		this.EmployeeID = employeeID;
		this.FullName = fullName;
		this.DOB = dOB;
		this.Gender = gender;
		this.PhoneNo = phoneNo;
		this.Email = email;
		this.Password = password;
		this.Salary = salary;
		this.RoleID = roleID;
	}
	
	public Employee(String EmployeeID, String Password)
	{
		this.EmployeeID = EmployeeID;
		this.Password = Password;
	}

	public String getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public int getGender() {
		return Gender;
	}
	public void setGender(int gender) {
		Gender = gender;
	}
	public String getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public double getSalary() {
		return Salary;
	}
	public void setSalary(double salary) {
		Salary = salary;
	}
	public String getRoleID() {
		return RoleID;
	}
	public void setRoleID(String roleID) {
		RoleID = roleID;
	}
	
	
	
	
	
	
}
