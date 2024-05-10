package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.RoomType;

public interface RoomTypeIDao extends Remote {
	public List<RoomType> getAllRoomType() throws RemoteException;
	public RoomType getRoomType(String roomTypeId) throws RemoteException;
	public int addRoomType(RoomType r) throws RemoteException;
	public boolean deleteRoomType(String roomTypeId) throws RemoteException;
	public int findRoomType(String id) throws RemoteException;
	public RoomType getLastRoom(String id) throws RemoteException;
	public int updateRoomType(String roomType, double firstHour, double nextHour, double overNight, int qtyBed, int people, float discount, float surcharge) throws RemoteException;
}
