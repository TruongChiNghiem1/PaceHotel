package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Room;
import entity.RoomType;

public interface RoomIDao extends Remote {
	public List<Room> getAllRoom() throws RemoteException;
	public List<Object[]> getAllInfoRoom() throws RemoteException;
	public int addRoom(Room r) throws RemoteException;
	public Object[] getLastRoom(String newID) throws RemoteException;
	public boolean deleteRoom(String roomNo) throws RemoteException;
	public int findRoom(String roomNo) throws RemoteException;
	public int updateRoomStatus(String roomNo, String roomStatus) throws RemoteException;
	public Boolean findStatus(String id) throws RemoteException;
	public Boolean findStatusOccupied(String id) throws RemoteException;
	public Boolean findStatusOrdered(String id) throws RemoteException;
	public Boolean findStatusReady(String id) throws RemoteException;
	public Room findRoomByRoomNo(String roomNo) throws RemoteException;
}
