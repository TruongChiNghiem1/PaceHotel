package service;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.BookingIDao;
import entity.Bill;
import entity.Booking;
import entity.Guest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class Booking_DAO extends UnicastRemoteObject implements BookingIDao, Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 482958858243362221L;
	private EntityManager entityManager;
	public Booking_DAO(EntityManager entityManager) throws RemoteException {
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}
	
	public List<Object[]> getAllBooking() throws RemoteException {
	    try {
	        List<Object[]> result = new ArrayList<Object[]>();
	        String sql = "Select "
	                + "b.bookingId, "
	                + "b.qtyRoom, "
	                + "b.numAdults, "
	                + "b.numChildren, "
	                + "b.note, "
	                + "b.bookingDateTime, "
	                + "b.arrivalDateTime, "
	                + "b.departureDateTime, "
	                + "r.roomNo "
	                + "from Booking b "
	                + "join b.room r";
	        TypedQuery<Object[]> query = entityManager.createQuery(sql, Object[].class);
	        result.addAll(query.getResultList());
	        return result;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ArrayList<Object[]>();
	    }
	}
	
	public List<Booking> getAllBooking1() throws RemoteException {
        try {
            return entityManager.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Booking>();
        }
    }

	 public int addBooking(Booking b) throws RemoteException, ParseException {
        try {
            entityManager.getTransaction().begin();
            LocalDateTime today = LocalDateTime.now();
            b.setArrivalDateTime(today);
            entityManager.persist(b);
            entityManager.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return 0;
        }
    }

	 public int addOrder(Booking b) throws RemoteException, ParseException {
        try {
            entityManager.getTransaction().begin();
            LocalDateTime today = LocalDateTime.now();
            b.setBookingDateTime(today);
            entityManager.persist(b);
            entityManager.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return 0;
        }
    }
	
	 public Object[] getOneBooking(int BookingID) throws RemoteException {
	        try {
	            Booking booking = entityManager.find(Booking.class, BookingID);
	            if (booking != null) {
	                return new Object[] {
	                    booking.getBookingDateTime(),
	                    booking.getNumAdults(),
	                    booking.getNumChildren(),
	                    booking.getNote(),
	                    booking.getGuest().getGuestID(),
	                    booking.getEmployee().getEmployeeId(),
	                    booking.getRoom().getRoomNo(),
	                    booking.getArrivalDateTime(),
	                    booking.getDepartureDateTime(),
	                    booking.getBookingDateTime(),
	                    booking.getBookingId()
	                };
	            } else {
	                return null;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	

	public int findBooking(String idBooking) throws RemoteException {
		for (int i = 0; i < getAllBooking().size(); i++) {
			if(getAllBooking().get(i)[0].equals(idBooking)) {
				return i;
			}
		}
		return -1;
	}

	public int findRoomBooking(String room) throws RemoteException {
		for (int i = 0; i < getAllBooking().size(); i++) {
			if(getAllBooking().get(i)[8].equals(room)) {
				if(getAllBooking().get(i)[7] == null) {
					return (Integer) getAllBooking().get(i)[0];
				}
			}
		}
		return -1;
	}
	
	public int findRoomBooking1() throws RemoteException {
		return (Integer) (getAllBooking().get((getAllBooking().size()) - 1)[0]);
	}
	
	public int updateBookingToArr(int idBooking) throws RemoteException {
        try {
            Booking booking = entityManager.find(Booking.class, idBooking);
            if (booking != null) {
                entityManager.getTransaction().begin();
                LocalDateTime today = LocalDateTime.now();
                booking.setArrivalDateTime(today);
                entityManager.merge(booking);
                entityManager.getTransaction().commit();
                return findBooking(idBooking);
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return -1;
        }
    }
	
	public int updateBookingToDep(int idBooking) throws RemoteException {
        try {
            Booking booking = entityManager.find(Booking.class, idBooking);
            if (booking != null) {
                entityManager.getTransaction().begin();
                LocalDateTime today = LocalDateTime.now();
                booking.setDepartureDateTime(today);
                entityManager.merge(booking);
                entityManager.getTransaction().commit();
                return findBooking(idBooking);
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return -1;
        }
    }
	
	public Object[] getOneRoom(int bookingId) throws RemoteException {
	    try {
	        String sql = "SELECT " +
	                     "r.roomNo, " +
	                     "rt.roomType, " +
	                     "rt.fisrtHourFee, " +
	                     "rt.nextHourFee, " +
	                     "rt.overNightFee " +
	                     "FROM Booking b " +
	                     "JOIN b.room r " +
	                     "JOIN r.roomType rt " +
	                     "WHERE b.bookingId = :bookingId";

	        TypedQuery<Object[]> query = entityManager.createQuery(sql, Object[].class);
	        query.setParameter("bookingId", bookingId);
	        List<Object[]> results = query.getResultList();

	        if (!results.isEmpty()) {
	            return results.get(0);
	        } else {
	            return null;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public List<Object[]> filterStatistics(String empID, String fromDate, String toDate) throws RemoteException {
        try {
            List<Object[]> results = new ArrayList<Object[]>();
            String sql = "select r.RoomTypeRoomType, r.RoomNo, b.ArrivalDateTime, b.DepartureDateTime, g.FullName, bill.SubTotal " +
                         "from Booking b " +
                         "join Employee e on b.EmployeeEmployeeID = e.EmployeeID " +
                         "join Guest g on b.GuestGuestID = g.GuestID " +
                         "join Room r on b.RoomRoomNo = r.RoomNo " +
                         "join BillDetail bdt on b.BookingID = bdt.BookingBookingID " +
                         "join Bill bill on bdt.BillBillID = bill.BillID " +
                         "where b.EmployeeEmployeeID = ? and b.DepartureDateTime >= ? and b.DepartureDateTime <= ?";
            TypedQuery<Object[]> query = entityManager.createQuery(sql, Object[].class);
            query.setParameter(1, empID);
            query.setParameter(2, fromDate);
            query.setParameter(3, toDate);
            results.addAll(query.getResultList());
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Object[]>();
        }
    }
	
	public Booking getOneBooking1(int bookingId) throws RemoteException {
        try {
            Booking booking = entityManager.find(Booking.class, bookingId);
            if (booking != null) {
                return booking;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public int findBooking(int BookingID) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
