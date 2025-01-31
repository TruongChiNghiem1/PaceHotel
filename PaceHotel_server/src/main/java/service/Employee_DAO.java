package service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import dao.EmployeeIDao;
import entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class Employee_DAO extends UnicastRemoteObject implements EmployeeIDao, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2774272830753420475L;
	private EntityManager entityManager;
	public Employee_DAO(EntityManager entityManager) throws RemoteException {
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}
	

	public List<Employee> getAllEmployees() {
	    return entityManager.createQuery("SELECT e FROM Employee e", Employee.class)
	            .getResultList();
	}
	
//	public Employee revertRowToEmployee(ResultSet rs) {
//		Employee e = null;
//		try {
//			e = new Employee(rs.getString(1),
//					rs.getString(2),
//					rs.getString(3),
//					rs.getInt(4),
//					rs.getString(5),
//					rs.getString(6),
//					rs.getString(7),
//					rs.getDouble(8),
//					rs.getString(9));
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return e;
//	}
               


	public Employee login(String employeeID, String password) {
	    try {
	        return (Employee) entityManager.createNativeQuery("SELECT e.employeeId, e.fullName, e.dOB, e.gender, e.phoneNo, e.email, e.password, e.salary, e.roleId "
	        		+ "FROM employee e "
	        		+ "WHERE e.employeeId = :employeeId "
	        		+ "AND e.password = :password",
	            Employee.class)
	            .setParameter("employeeId", employeeID)
	            .setParameter("password", password)
	            .getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public int addEmployee(Employee e) throws RemoteException, ParseException {
        try {
            entityManager.getTransaction().begin();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = dateFormat.parse(e.getDOB());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            e.setDOB(sqlDate.toString());
            entityManager.persist(e);
            entityManager.getTransaction().commit();
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return 0;
        }
    }
	

	public boolean deleteEmployee(String employeeId) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            Employee employee = entityManager.find(Employee.class, employeeId);
            if (employee != null) {
                entityManager.remove(employee);
                entityManager.getTransaction().commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }
	
	public int findEmployee(String employeeId) throws RemoteException {
        try {
            List<Employee> employees = getAllEmployees();
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getEmployeeId().equalsIgnoreCase(employeeId)) {
                    return i;
                }
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
	
	public int updateEmployee(String id, String name, String DOB, int gender, String phone, String email, double salary) throws RemoteException, ParseException {
        try {
            entityManager.getTransaction().begin();
            Employee employee = entityManager.find(Employee.class, id);
            if (employee != null) {
                employee.setFullName(name);
                employee.setDOB(DOB);
                employee.setGender(gender);
                employee.setPhoneNo(phone);
                employee.setEmail(email);
                employee.setSalary(salary);
                entityManager.merge(employee);
                entityManager.getTransaction().commit();
                return findEmployee(id);
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return -1;
        }
    }
	
	public Employee layNhanVienTheoMa(String mnv) throws RemoteException {
		try {
            Employee employee = entityManager.find(Employee.class, mnv);
            if (employee != null) {
                return employee;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
//	lấy mã nhân viên lớn nhất để set mã tự động
	public int layMaNVLonNhat() throws RemoteException {
		try {
			String sql = "SELECT CAST(MAX(SUBSTRING(e.employeeId, 2)) AS INTEGER) AS employeeIdMax FROM Employee e";
			TypedQuery<Integer> query = entityManager.createQuery(sql, Integer.class);
			Integer maxID = query.getSingleResult();
            return maxID != null ? maxID : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
	}
}
