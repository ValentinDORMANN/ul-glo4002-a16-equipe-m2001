package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public interface ReservationRegistry {

  void saveNewReservation(Reservation newReservation) throws IllegalArgumentReservationException;

  Reservation getReservationByNumber(int reservationNumber) throws NotFoundReservationException;

  Reservation getReservationByPassengerHash(String hash);

}
