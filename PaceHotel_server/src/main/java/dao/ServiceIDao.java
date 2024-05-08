package dao;

import java.rmi.RemoteException;
import java.util.List;

import entity.Service;

public interface ServiceIDao {
	public List<Service> getAllService() throws RemoteException;
	public int addService(Service s) throws RemoteException;
	public int findService(String serviceId) throws RemoteException;
	public boolean deleteService(String serviceId) throws RemoteException;
	public int updateService(String id, String name, double price) throws RemoteException;
	
	
}