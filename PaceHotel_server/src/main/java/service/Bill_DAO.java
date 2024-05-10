package service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.BillIDao;
import entity.Bill;
import entity.Employee;
import jakarta.persistence.EntityManager;


public class Bill_DAO extends UnicastRemoteObject implements BillIDao, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8166285703643932750L;
	private EntityManager entityManager;
	public Bill_DAO(EntityManager entityManager) throws RemoteException {
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}
	
	public List<Bill> getAllBill() throws RemoteException {
        try {
            return entityManager.createQuery("SELECT b FROM Bill b", Bill.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Bill>();
        }
    }
	
	public int findBillLastDetail() throws RemoteException {
		return getAllBill().get(getAllBill().size()-1).getBillId();
	}
	
	
	
	public int addBill(Bill b) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            LocalDateTime today = LocalDateTime.now();
            b.setPaymentDate(today);
            entityManager.persist(b);
            entityManager.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return 0;
        }
    }

    public int updateBillCheckOut(Bill bill) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            LocalDateTime today = LocalDateTime.now();
            bill.setPaymentDate(today);
            entityManager.merge(bill);
            entityManager.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return 0;
        }
    }
    
    public Bill findBill(int billId) throws RemoteException {
		try {
			Bill billRender = entityManager.find(Bill.class, billId);
            if (billRender != null) {
                return billRender;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}
