package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;

public class HibernateReservation {

  private static final String UNFOUND_RESERVATION_ERROR = "Error : reservation not found !";
  private static final String DOUBLE_RESERVATION_ERROR = "Error : This reservation exists already.";
  

  private EntityManager entityManager;

  public HibernateReservation() {
    this.entityManager = new EntityManagerProvider().getEntityManager();
  }

  public void persisteReservation(Reservation newReservation) throws IllegalArgumentReservationException {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    try {
      if (entityManager.find(Reservation.class, newReservation.getReservationNumber()) != null)
        throw new IllegalArgumentReservationException(DOUBLE_RESERVATION_ERROR);
      else
        entityManager.persist(newReservation);
    } finally {    	
      transaction.commit();
    }
  }

  public Reservation findReservationByNumber(int reservationNumber) {
    Reservation reservationFound;
    reservationFound = entityManager.find(Reservation.class, reservationNumber);
    if (reservationFound == null)
      throw new NotFoundReservationException(UNFOUND_RESERVATION_ERROR);
    return reservationFound;
  }

  public Reservation findReservationByPassengerHash(String hash) throws NotFoundPassengerException {
    Passenger passenger = entityManager.find(Passenger.class, hash);
    if (passenger == null)
      throw new NotFoundPassengerException();
    return passenger.getReservation();
  }

  public void update(Reservation reservation) {
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    try {
      entityManager.persist(reservation);
    } finally {
      transaction.commit();
    }
  }
}