package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dao.BillDetailIDao;
import dao.BillIDao;
import dao.BookingIDao;
import dao.EmployeeIDao;
import dao.GuestIDao;
import dao.RoomIDao;
import dao.RoomTypeIDao;
import dao.ServiceIDao;
import jakarta.persistence.EntityManager;
import service.BillDetail_DAO;
import service.Bill_DAO;
import service.Booking_DAO;
import service.Employee_DAO;
import service.Guest_DAO;
import service.RoomType_DAO;
import service.Room_DAO;
import service.Service_DAO;
import untils.EntityManagerFactoryUtil;

public class ServerRMI {
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {

		// Create a registry
		Registry registry = LocateRegistry.createRegistry(2000);

		EntityManagerFactoryUtil entityManagerFactory = new EntityManagerFactoryUtil("server_hotel");
		EntityManager entityManager = entityManagerFactory.getEnManager();
		
		BillDetailIDao billDetailIDao = new BillDetail_DAO(entityManager);
		BillIDao billIDao = new Bill_DAO(entityManager);
		BookingIDao bookingIDao = new Booking_DAO(entityManager);
		EmployeeIDao employeeIDao = new Employee_DAO(entityManager); // Remote Object
		GuestIDao guestIDao = new Guest_DAO(entityManager);
		RoomIDao roomIDao = new Room_DAO(entityManager);
		RoomTypeIDao roomTypeIDao = new RoomType_DAO(entityManager);
		ServiceIDao serviceIDao = new Service_DAO(entityManager);
		
		registry.bind("billDetailIDao", billDetailIDao);
		registry.bind("billIDao", billIDao);
		registry.bind("bookingIDao", bookingIDao);
		registry.bind("employeeIDao", employeeIDao);
		registry.bind("roomIDao", roomIDao);
		registry.bind("roomTypeIDao", roomTypeIDao);
		registry.bind("guestIDao", guestIDao);
		registry.bind("serviceIDao", serviceIDao);

		System.out.println("RMI Server ready");
	}
}
