package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7428951181122743342L;

	@Id
	@Column(nullable = false)
	private String employeeId;
	
	private String fullName; 
	private String DOB;
	private int gender;
	private String phoneNo, email, password;
	private double salary;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleId")
	private Role roleId;
	
	@OneToMany(mappedBy = "employee")
	private List<HistoryLogin> historyLogin;
	
	@OneToMany(mappedBy = "employee")
	private List<Booking> booking;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee(String employeeId, String fullName, String dOB, int gender, String phoneNo, String email,
			String password, double salary) {
		super();
		this.employeeId = employeeId;
		this.fullName = fullName;
		DOB = dOB;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.email = email;
		this.password = password;
		this.salary = salary;
	}
	
	public Employee(String employeeId, String fullName, String dOB, int gender, String phoneNo, String email,
			String password, double salary, Role roleId) {
		super();
		this.employeeId = employeeId;
		this.fullName = fullName;
		DOB = dOB;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.email = email;
		this.password = password;
		this.salary = salary;
		this.roleId = roleId;
	}

	public Employee(String employeeId, String fullName, String dOB, int gender, String phoneNo, String email,
			String password, double salary, Role roleId, List<HistoryLogin> historyLogin, List<Booking> booking) {
		super();
		this.employeeId = employeeId;
		this.fullName = fullName;
		DOB = dOB;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.email = email;
		this.password = password;
		this.salary = salary;
		this.roleId = roleId;
		this.historyLogin = historyLogin;
		this.booking = booking;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeID) {
		this.employeeId = employeeID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	public List<HistoryLogin> getHistoryLogin() {
		return historyLogin;
	}

	public void setHistoryLogin(List<HistoryLogin> historyLogin) {
		this.historyLogin = historyLogin;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeId + ", fullName=" + fullName + ", DOB=" + DOB + ", gender=" + gender
				+ ", phoneNo=" + phoneNo + ", email=" + email + ", password=" + password + ", salary=" + salary
				+ ", role=" + roleId.getRoleTitle();
	}
	
	

}
