package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public interface ReservationRegistry {

  void persist(Reservation newReservation) throws IllegalArgumentReservationException;

  Reservation findReservationByNumber(int reservationNumber) throws NotFoundReservationException;

  Reservation findReservationByPassengerHash(String hash);
  
  void update(Reservation reservation);
}
