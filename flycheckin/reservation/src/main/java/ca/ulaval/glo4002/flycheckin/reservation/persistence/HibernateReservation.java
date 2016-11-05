package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public class HibernateReservation {

  private static final String MESSAGE_ERROR_RESERVATION = "Error : reservation not found !";
  private static final String MESSAGE_ERROR_RESERVATION2 = "Error : no reservation for this passenger!";
  private EntityManager entityManager;
  
  public HibernateReservation(){
    this.entityManager = new EntityManagerProvider().getEntityManager();
  }
  
  public void insertNewReservation(Reservation newReservation) throws IllegalArgumentReservationException {
    int reservationNumber = newReservation.getReservationNumber();
	try {
	  EntityTransaction aTransaction = entityManager.getTransaction();
	  aTransaction.begin();
	  entityManager.persist(newReservation);
	  aTransaction.commit();
    } catch (EntityExistsException ex){
    	throw new IllegalArgumentReservationException("Reservation " + reservationNumber + " already exists.");
    }
  }
  
  public Reservation findReservationByNumber(int reservationNumber) throws NotFoundReservationException {
    Reservation reservationFound = entityManager.find(Reservation.class, reservationNumber);
    if (reservationFound == null)
      throw new NotFoundReservationException(MESSAGE_ERROR_RESERVATION);
    return reservationFound;
  }
	  
  public Reservation findReservationByPassengerHash(String hash) {
	  return new Reservation();
  }

	  /*public Reservation getReservationByPassengerHash(String hash) {
	    for (Reservation reservation : reservationMap.values()) {
	      if (reservation.getPassengerHashListInReservation().contains(hash))
	        return reservation;
	    }
	    throw new NotFoundPassengerException(MESSAGE_ERROR_RESERVATION2);
	  }*/
}
