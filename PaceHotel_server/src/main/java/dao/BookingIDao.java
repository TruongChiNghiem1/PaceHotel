package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

import entity.Booking;

public interface BookingIDao extends Remote{
	public List<Object[]> getAllBooking() throws RemoteException;
	public int addBooking(Booking b) throws RemoteException, ParseException;
	public int addOrder(Booking b) throws RemoteException, ParseException;
	public Object[] getOneBooking(int BookingID) throws RemoteException;
	public int findBooking(int BookingID) throws RemoteException;
	public int findRoomBooking(String room) throws RemoteException;
	public int findRoomBooking1() throws RemoteException;
	public int updateBookingToArr(int idBooking) throws RemoteException;
	public int updateBookingToDep(int idBooking) throws RemoteException;
	public Object[] getOneRoom(int BookingID) throws RemoteException;
	public List<Object[]> filterStatistics(String empID, String fromDate, String toDate) throws RemoteException;
	public Booking getOneBooking1(int bookingId) throws RemoteException;
}
