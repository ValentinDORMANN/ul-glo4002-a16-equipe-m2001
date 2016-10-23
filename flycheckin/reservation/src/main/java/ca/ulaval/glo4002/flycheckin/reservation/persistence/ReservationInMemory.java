package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public class ReservationInMemory {

  private static Map<Integer, Reservation> reservationList = new HashMap<Integer, Reservation>();

  public void saveNewReservation(Reservation newReservation) throws IllegalArgumentReservationException {
    int reservationNumber = newReservation.getReservationNumber();
    if (reservationList.containsKey(reservationNumber))
      throw new IllegalArgumentReservationException("Reservation " + reservationNumber + " already exists.");
    else
      reservationList.put(reservationNumber, newReservation);
  }

  public Reservation getReservationByNumber(int reservationNumber) throws NotFoundReservationException {
    if (reservationList.isEmpty() || !reservationList.containsKey(reservationNumber))
      throw new NotFoundReservationException("Reservation " + reservationNumber + " not found.");
    else
      return reservationList.get(reservationNumber);
  }

}
