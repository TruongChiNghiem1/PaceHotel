package service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.RoomTypeIDao;
import entity.RoomType;
import jakarta.persistence.EntityManager;

public class RoomType_DAO extends UnicastRemoteObject implements RoomTypeIDao, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5828951035063032921L;
	private EntityManager entityManager;
	public RoomType_DAO(EntityManager entityManager) throws RemoteException {
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}
	
	public List<RoomType> getAllRoomType() throws RemoteException {
        try {
            return entityManager.createQuery("SELECT rt FROM RoomType rt", RoomType.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
//	public RoomType revertRowToRoomType(ResultSet rs) {
//		RoomType rt = null;
//		try {
//			rt = new RoomType(rs.getString(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6), rs.getFloat(7), rs.getFloat(8));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return rt;
//	}
	
	public RoomType getRoomType(String roomTypeId) throws RemoteException {
        try {
            return entityManager.find(RoomType.class, roomTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public int addRoomType(RoomType r) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(r);
            entityManager.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return 0;
        }
    }
	
	public boolean deleteRoomType(String roomTypeId) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            RoomType roomType = entityManager.find(RoomType.class, roomTypeId);
            if (roomType != null) {
                entityManager.remove(roomType);
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
	
	public int findRoomType(String id) throws RemoteException {
		for (int i = 0; i < getAllRoomType().size(); i++) {
			if(getAllRoomType().get(i).getRoomType().equalsIgnoreCase(id)) {
				return i;
			}
		}
		return -1;
	}
	
	public RoomType getLastRoom(String id) throws RemoteException {
		RoomType vd = null;
		for (RoomType o : getAllRoomType()) {
			if(o.getRoomType().equals(id)) {
				vd = o;
			}
		}
		return vd;
	}
	
	public int updateRoomType(String roomType, double firstHour, double nextHour, double overNight, int qtyBed, int people, float discount, float surcharge) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            RoomType rt = entityManager.find(RoomType.class, roomType);
            if (rt != null) {
                rt.setFisrtHourFee(firstHour);
                rt.setNextHourFee(nextHour);
                rt.setOverNightFee(overNight);
                rt.setQtyBed(qtyBed);
                rt.setPeople(people);
                rt.setDisscount(discount);
                rt.setSurcharge(surcharge);
                entityManager.merge(rt);
                entityManager.getTransaction().commit();
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return 0;
        }
    }
}
