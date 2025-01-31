package service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.RoomIDao;
import entity.Booking;
import entity.Room;
import entity.RoomType;
import jakarta.persistence.EntityManager;

public class Room_DAO extends UnicastRemoteObject implements RoomIDao, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7368623043637158145L;

	private EntityManager entityManager;
	public Room_DAO(EntityManager entityManager) throws RemoteException {
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}

	public List<Room> getAllRoom(){
	    try {
	    	return entityManager.createQuery("SELECT r.roomNo, r.status, r.roomType FROM Room r ORDER BY r.roomNo", Room.class).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
	public List<Object[]> getAllInfoRoom() {
	    try {
	    	return entityManager.createQuery("SELECT "
	    			+ "r.roomNo,"
	    			+ " rt.roomType"
	    			+ ", rt.people,"
	    			+ " rt.qtyBed,"
	    			+ " rt.fisrtHourFee,"
	    			+ " rt.nextHourFee,"
	    			+ " rt.overNightFee,"
	    			+ " r.status,"
	    			+ " rt.disscount " +
	                "FROM Room r JOIN r.roomType rt ORDER BY r.roomNo", Object[].class).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public int addRoom(Room r) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(r);
            entityManager.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

	public Object[] getLastRoom(String newID) {
		Object[] vd = null;
		for (Object[] o : getAllInfoRoom()) {
			if(o[0].equals(newID)) {
				vd = o;
			}
		}
		return vd;
	}
	
//	public Room revertRowOfTable(ResultSet rs) {
//		Room r = null;
//		try {
//			r = new Room(rs.getString(1), rs.getString(3), rs.getString(2));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return r;
//	}

	 public boolean deleteRoom(String roomNo) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            Room room = entityManager.find(Room.class, roomNo);
            if (room != null) {
                entityManager.remove(room);
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

	public int findRoom(String roomNo) {
		for(int i = 0; i < getAllInfoRoom().size(); i++) {
			if(getAllInfoRoom().get(i)[0].equals(roomNo)) {
				return i;
			}
		}
		return -1;
	}
	
	public int updateRoomStatus(String roomNo, String roomStatus) throws RemoteException {
	    try {
	        entityManager.getTransaction().begin();
	        Room room = entityManager.find(Room.class, roomNo);
	        if (room != null) {
	            room.setStatus(roomStatus);
	            entityManager.merge(room);
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
	
	public Boolean findStatus(String id) throws RemoteException {
		for(int i = 0; i < getAllInfoRoom().size(); i++) {
			if(getAllInfoRoom().get(i)[0].equals(id) && getAllInfoRoom().get(i)[7].equals("Dirty")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean findStatusOccupied(String id) throws RemoteException {
		for(int i = 0; i < getAllInfoRoom().size(); i++) {
			if(getAllInfoRoom().get(i)[0].equals(id) && getAllInfoRoom().get(i)[7].equals("Occupied")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean findStatusOrdered(String id) throws RemoteException {
		for(int i = 0; i < getAllInfoRoom().size(); i++) {
			if(getAllInfoRoom().get(i)[0].equals(id) && getAllInfoRoom().get(i)[7].equals("Ordered")) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean findStatusReady(String id) throws RemoteException {
		for(int i = 0; i < getAllInfoRoom().size(); i++) {
			if(getAllInfoRoom().get(i)[0].equals(id) && getAllInfoRoom().get(i)[7].equals("Ready")) {
				return true;
			}
		}
		return false;
	}

	
	public Room findRoomByRoomNo(String roomNo) throws RemoteException {
        try {
        	Room room = entityManager.createQuery("SELECT r FROM Room r JOIN FETCH r.roomType WHERE r.roomNo = :roomNo", Room.class)
                    .setParameter("roomNo", roomNo)
                    .getSingleResult();
            if (room != null) {
                return room;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
