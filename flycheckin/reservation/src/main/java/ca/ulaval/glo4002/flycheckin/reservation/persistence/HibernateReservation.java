package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.domain.ReservationRegistry;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public class HibernateReservation implements ReservationRegistry {

  private static final String UNFOUND_RESERVATION_ERROR = "Error : reservation not found !";
  private static final String INVALID_PASSENGER_ERROR = "Error : no reservation for this passenger!";
  private static final String DOUBLE_RESERVATION_ERROR = "Error : This reservation exists already.";
  private EntityManager entityManager;

  public HibernateReservation() {
    this.entityManager = new EntityManagerProvider().getEntityManager();
  }

  @Override
  public void saveNewReservation(Reservation newReservation) throws IllegalArgumentReservationException {
    try {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(newReservation);
      entityManager.getTransaction().commit();
      transaction.commit();
    } catch (EntityExistsException ex) {
      throw new IllegalArgumentReservationException(DOUBLE_RESERVATION_ERROR);
    }
  }

  @Override
  public Reservation getReservationByNumber(int reservationNumber) {
    Reservation reservationFound;
    try {
      reservationFound = entityManager.find(Reservation.class, reservationNumber);
    } catch (NullPointerException ex) {
      throw new NotFoundReservationException(UNFOUND_RESERVATION_ERROR);
    }
    return reservationFound;
  }

  @Override
  public Reservation getReservationByPassengerHash(String hash) throws NotFoundPassengerException {
    String hql = "select r from Reservation as r inner join Passenger where r.passengerHash = :passengerHash";
    TypedQuery<Reservation> query = entityManager.createQuery(hql, Reservation.class);
    query.setParameter("passengerHash", hash);
    Reservation reservationFound = query.getSingleResult();
    if (reservationFound == null)
      throw new NotFoundReservationException(INVALID_PASSENGER_ERROR);
    return reservationFound;
  }
}