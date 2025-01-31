package entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Role")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4994221506933382224L;

	@Id
	@Column(nullable = false)
	private String RoleId;
	
	private String RoleTitle;
	
	@OneToMany(mappedBy = "roleId", fetch = FetchType.EAGER)
	private List<Employee> employee;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String roleId, String roleTitle, List<Employee> employee) {
		super();
		RoleId = roleId;
		RoleTitle = roleTitle;
		this.employee = employee;
	}

	public String getRoleId() {
		return RoleId;
	}

	public void setRoleId(String roleId) {
		RoleId = roleId;
	}

	public String getRoleTitle() {
		return RoleTitle;
	}

	public void setRoleTitle(String roleTitle) {
		RoleTitle = roleTitle;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Role [RoleId=" + RoleId + ", RoleTitle=" + RoleTitle + ", employee=" + employee + "]";
	}

	
	
	
}
