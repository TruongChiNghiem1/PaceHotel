package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Bill;

public interface BillIDao extends Remote{
	public List<Bill> getAllBill() throws RemoteException;
	public int findBillLastDetail() throws RemoteException;
	public int addBill(Bill b) throws RemoteException;
	public int updateBillCheckOut(Bill bill) throws RemoteException;
	public Bill findBill(int billId) throws RemoteException;
}
