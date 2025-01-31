package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.BillDetail;
import entity.Room;
import entity.RoomType;

public interface BillDetailIDao extends Remote {
	public List<BillDetail> getAllBillDetail() throws RemoteException;
	public int findBillID(int bookingID) throws RemoteException;
	public int findBillDetailID(int bookingID) throws RemoteException;
	public int addBillDetail(BillDetail bd) throws RemoteException;
	public List<Object[]> getAllService(int bookingID) throws RemoteException;
	public List<Object[]> getAllServiceBill(int bookingID) throws RemoteException;
	public boolean deleteService(int id) throws RemoteException;
	public List<Room> getAllRoom() throws RemoteException;
	public List<Object[]> getAllInfoRoom() throws RemoteException;
	public int addRoom(Room r) throws RemoteException;
	public Object[] getLastRoom(String newID) throws RemoteException;
	public boolean deleteRoom(String roomNo) throws RemoteException;
	public int findRoom(String roomNo) throws RemoteException;
	public int updateRoom(String roomNo, RoomType roomType, String roomStatus) throws RemoteException;
}
