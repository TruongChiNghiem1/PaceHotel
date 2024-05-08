package entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HistoryLogin")
public class HistoryLogin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7712361894288086608L;
	@Id
	@Column(nullable = false)
	private int loginId;
	private LocalDate loginDateTime;
	private LocalDate logoutDateTime;
	private String employeeHisId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeId")
	private Employee employee;

	public HistoryLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoryLogin(int loginId, LocalDate loginDateTime, LocalDate logoutDateTime, String employeeHisId,
			Employee employee) {
		super();
		this.loginId = loginId;
		this.loginDateTime = loginDateTime;
		this.logoutDateTime = logoutDateTime;
		this.employeeHisId = employeeHisId;
		this.employee = employee;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public LocalDate getLoginDateTime() {
		return loginDateTime;
	}

	public void setLoginDateTime(LocalDate loginDateTime) {
		this.loginDateTime = loginDateTime;
	}

	public LocalDate getLogoutDateTime() {
		return logoutDateTime;
	}

	public void setLogoutDateTime(LocalDate logoutDateTime) {
		this.logoutDateTime = logoutDateTime;
	}

	public String getEmployeeHisId() {
		return employeeHisId;
	}

	public void setEmployeeHisId(String employeeHisId) {
		this.employeeHisId = employeeHisId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "HistoryLogin [loginId=" + loginId + ", loginDateTime=" + loginDateTime + ", logoutDateTime="
				+ logoutDateTime + ", employeeId=" + employeeHisId + ", employee=" + employee + "]";
	}
	
	
}
