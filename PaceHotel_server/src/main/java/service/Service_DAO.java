package service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.ServiceIDao;
import entity.Bill;
import entity.Service;
import jakarta.persistence.EntityManager;

public class Service_DAO extends UnicastRemoteObject implements ServiceIDao, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4237399249315606692L;
	private EntityManager entityManager;
	public Service_DAO(EntityManager entityManager) throws RemoteException {
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}
	
	public List<Service> getAllService() throws RemoteException {
	    try {
	        return entityManager.createQuery("SELECT s FROM Service s", Service.class).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

//	public Service revertRowToService(ResultSet rs) {
//		Service s = null;
//		try {
//			s = new Service(rs.getString(1), rs.getString(2), rs.getDouble(3));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return s;
//	}

	public int addService(Service s) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(s);
            entityManager.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return 0;
        }
    }

	public int findService(String serviceId) throws RemoteException {
        try {
            List<Service> services = entityManager.createQuery("SELECT s FROM Service s", Service.class).getResultList();
            for (int i = 0; i < services.size(); i++) {
                if (services.get(i).getServiceID().equalsIgnoreCase(serviceId)) {
                    return i;
                }
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

	public boolean deleteService(String serviceId) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            Service service = entityManager.find(Service.class, serviceId);
            if (service != null) {
                entityManager.remove(service);
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
	
	public int updateService(String id, String name, double price) throws RemoteException {
        try {
            entityManager.getTransaction().begin();
            Service service = entityManager.find(Service.class, id);
            if (service != null) {
                service.setServiceName(name);
                service.setPrice(price);
                entityManager.merge(service);
                entityManager.getTransaction().commit();
                return findService(id);
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return -1;
        }
    }
	
	public Service findOneService(String serviceId) throws RemoteException {
		try {
			Service serviceRender = entityManager.find(Service.class, serviceId);
            if (serviceRender != null) {
                return serviceRender;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}

}
