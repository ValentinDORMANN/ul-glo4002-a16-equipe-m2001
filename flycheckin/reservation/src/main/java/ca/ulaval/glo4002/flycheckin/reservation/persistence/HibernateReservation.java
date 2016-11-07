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

  private static final String MESSAGE_ERROR_RESERVATION = "Error : reservation not found !";
  private static final String MESSAGE_ERROR_RESERVATION2 = "Error : no reservation for this passenger!";
  private EntityManager entityManager;
  
  public HibernateReservation(){
    this.entityManager = new EntityManagerProvider().getEntityManager();
  }
  
  public void insertNewReservation(Reservation newReservation) throws IllegalArgumentReservationException {
    int reservationNumber = newReservation.getReservationNumber();
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(newReservation);
      transaction.commit();
    } catch (EntityExistsException ex) {
      throw new IllegalArgumentReservationException("Reservation " + reservationNumber + " already exists.");
    }
  }
  
  public Reservation findReservationByNumber(int reservationNumber) {
	Reservation reservationFound;
	try {
	  reservationFound = entityManager.find(Reservation.class, reservationNumber);
	} catch (NullPointerException ex) {
	  throw new NotFoundReservationException(MESSAGE_ERROR_RESERVATION);
	}
	return reservationFound;
  }

  public Reservation findReservationByPassengerHash(String hash) throws NotFoundPassengerException {
    String hql = "select r from Reservation as r inner join Passenger where r.passengerHash = :passengerHash";
    TypedQuery<Reservation> query = entityManager.createQuery(hql, Reservation.class);
    query.setParameter("passengerHash", hash);
    Reservation reservationFound = query.getSingleResult();
    if (reservationFound == null)
      throw new NotFoundPassengerException(MESSAGE_ERROR_RESERVATION2);
    return reservationFound;
  }
}
