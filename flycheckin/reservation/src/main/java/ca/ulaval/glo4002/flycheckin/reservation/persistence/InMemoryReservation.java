package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;
import ca.ulaval.glo4002.flycheckin.reservation.exception.IllegalArgumentReservationException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.reservation.exception.NotFoundReservationException;

public class InMemoryReservation {

  private static Map<Integer, Reservation> reservationMap = new HashMap<Integer, Reservation>();
  private static final String MESSAGE_ERROR_RESERVATION = "Error : reservation not found !";
  private static final String MESSAGE_ERROR_RESERVATION2 = "Error : no reservation for this passenger!";

  public void saveNewReservation(Reservation newReservation) throws IllegalArgumentReservationException {
    int reservationNumber = newReservation.getReservationNumber();
    if (reservationMap.containsKey(reservationNumber))
      throw new IllegalArgumentReservationException("Reservation " + reservationNumber + " already exists.");
    else
      reservationMap.put(reservationNumber, newReservation);
  }

  public Reservation getReservationByNumber(int reservationNumber) throws NotFoundReservationException {
    if (reservationMap.isEmpty() || !reservationMap.containsKey(reservationNumber))
      throw new NotFoundReservationException(MESSAGE_ERROR_RESERVATION);
    else
      return reservationMap.get(reservationNumber);
  }

  public Reservation getReservationByPassengerHash(String hash) {
    for (Reservation reservation : reservationMap.values()) {
      if (reservation.getPassengerHashListInReservation().contains(hash))
        return reservation;
    }
    throw new NotFoundPassengerException(MESSAGE_ERROR_RESERVATION2);
  }
}
