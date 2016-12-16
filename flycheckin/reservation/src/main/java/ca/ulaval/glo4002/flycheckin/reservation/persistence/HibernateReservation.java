package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public class HibernateReservation {

	  private static final String UNFOUND_RESERVATION_ERROR = "Error : reservation not found !";
	  private static final String INVALID_PASSENGER_ERROR = "Error : no reservation for this passenger!";
	  private static final String DOUBLE_RESERVATION_ERROR = "Error : This reservation exists already.";
	  private EntityManager entityManager;

	  public HibernateReservation() {
	    this.entityManager = new EntityManagerProvider().getEntityManager();
	  }

	  public void insertNewReservation(Reservation newReservation) throws IllegalArgumentReservationException {
	    EntityTransaction transaction = entityManager.getTransaction();
	    transaction.begin();  
	    if(entityManager.contains(newReservation)) {
	    	throw new IllegalArgumentReservationException(DOUBLE_RESERVATION_ERROR);}
	      else {
	      	entityManager.persist(newReservation);
	      transaction.commit();}
	      	  
	  	  }

	  public Reservation findReservationByNumber(int reservationNumber) {
	    Reservation reservationFound;
	    	 String hql = "select r from Reservation r where r.reservationNumber = :reservationNumber";
	       TypedQuery<Reservation> query = entityManager.createQuery(hql, Reservation.class);
	       query.setParameter("reservationNumber", reservationNumber);
	       reservationFound = query.getSingleResult();
	    if (reservationFound == null)
	      throw new NotFoundReservationException(UNFOUND_RESERVATION_ERROR);
	    return reservationFound;
	  }

	  public Reservation findReservationByPassengerHash(String hash) throws NotFoundPassengerException {
	    String hql = "select r from Reservation as r inner join Passenger where r.passengerHash = :passengerHash";
	    TypedQuery<Reservation> query = entityManager.createQuery(hql, Reservation.class);
	    query.setParameter("passengerHash", hash);
	    Reservation reservationFound = query.getSingleResult();
	    if (reservationFound == null)
	      throw new NotFoundReservationException(INVALID_PASSENGER_ERROR);
	    return reservationFound;
	  }
	}