package service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;

import java.util.List;

import dao.GuestIDao;
import entity.Guest;
import jakarta.persistence.EntityManager;

public class Guest_DAO extends UnicastRemoteObject implements GuestIDao, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3905403026562596723L;
	private EntityManager entityManager;
	public Guest_DAO(EntityManager entityManager) throws RemoteException {
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}

	public List<Guest> getAllGuest() throws RemoteException {
        try {
            return entityManager.createQuery("SELECT g FROM Guest g", Guest.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	
	public Guest getOneGuest(String guestID) throws RemoteException {
        try {
            Guest guest = entityManager.find(Guest.class, guestID);
            if (guest != null) {
                return guest;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public int addGuest(Guest g) throws RemoteException, ParseException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(g);
            entityManager.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return 0;
        }
    }
	
	public int findGuest(String GuestID) throws RemoteException {
		for (int i = 0; i < getAllGuest().size(); i++) {
			if(getAllGuest().get(i).getGuestID().equalsIgnoreCase(GuestID)) {
				return i;
			}
		}
		return -1;
	}
}
