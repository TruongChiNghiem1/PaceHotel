package service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import dao.BillDetailIDao;
import entity.BillDetail;
import entity.Room;
import entity.RoomType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class BillDetail_DAO extends UnicastRemoteObject implements BillDetailIDao, Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3695385817541532728L;
	private EntityManager entityManager;
	public BillDetail_DAO(EntityManager entityManager) throws RemoteException {
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}
	
	
	public List<BillDetail> getAllBillDetail() throws RemoteException {
        try {
            return entityManager.createQuery("SELECT bd FROM BillDetail bd", BillDetail.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<BillDetail>();
        }
    }

	
	public int findBillID(int bookingID) throws RemoteException {
		for (int i = 0; i < getAllBillDetail().size(); i++) {
			if(getAllBillDetail().get(i).getService().equalsIgnoreCase("S002") && getAllBillDetail().get(i).getBooking().getBookingId() == bookingID) {
				return getAllBillDetail().get(i).getBillDetailId();
			}
		}
		return -1;
	}
	
	public int findBillDetailID(int bookingID) throws RemoteException {
		for (int i = 0; i < getAllBillDetail().size(); i++) {
			if(getAllBillDetail().get(i).getService().equalsIgnoreCase("S002") && getAllBillDetail().get(i).getBooking().getBookingId() == bookingID) {
				return getAllBillDetail().get(i).getBillDetailId();
			}
		}
		return -1;
	}
	
	 public int addBillDetail(BillDetail bd) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(bd);
            entityManager.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return 0;
        }
    }
	
	 public List<Object[]> getAllService(int bookingID) throws RemoteException {
        try {
            List<Object[]> results = new ArrayList<Object[]>();
            String sql = "select "
            		+ "bd.billDetailId, "
            		+ "s.serviceName, "
            		+ "s.price, "
            		+ "bd.qtyService, "
            		+ "s.price * bd.qtyService as amount, "
            		+ "s.serviceID "
            		+ "from BillDetail bd join bd.service s "
            		+ "where bd.booking.bookingId = :bookingId";
            TypedQuery<Object[]> query = entityManager.createQuery(sql, Object[].class);
            query.setParameter("bookingId", bookingID);
            
            results.addAll(query.getResultList());
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Object[]>();
        }
    }
	
	 public List<Object[]> getAllServiceBill(int bookingID) throws RemoteException {
		 System.out.println(bookingID);
        try {
            List<Object[]> results = new ArrayList<Object[]>();
            String sql = "select "
            		+ "b.room.roomNo, "
            		+ "s.serviceName, "
            		+ "bd.qtyService, "
            		+ "s.price, "
            		+ "s.price * bd.qtyService as amount " 
            		+ "from BillDetail bd "
            		+ "join bd.service s " 
            		+ "join bd.booking b " 
            		+ "where bd.booking.bookingId = :bookingId";
            TypedQuery<Object[]> query = entityManager.createQuery(sql, Object[].class);
            query.setParameter("bookingId", bookingID);
            results.addAll(query.getResultList());
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Object[]>();
        }
    }
	
	 public boolean deleteService(int id) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            BillDetail billDetail = entityManager.find(BillDetail.class, id);
            if (billDetail != null) {
                entityManager.remove(billDetail);
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
	 
	public List<Room> getAllRoom() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Object[]> getAllInfoRoom() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	public int addRoom(Room r) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	public Object[] getLastRoom(String newID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean deleteRoom(String roomNo) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	public int findRoom(String roomNo) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	public int updateRoom(String roomNo, RoomType roomType, String roomStatus) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
}
