package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.List;

import entity.Guest;

public interface GuestIDao extends Remote {
	public List<Guest> getAllGuest() throws RemoteException;
	public int addGuest(Guest g) throws RemoteException, ParseException;
	public int findGuest(String GuestID) throws RemoteException;
	public Guest getOneGuest(String guestID) throws RemoteException;
}
