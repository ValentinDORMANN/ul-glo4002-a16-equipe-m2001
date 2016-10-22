package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Reservation;

public class ReservationInMemory {

  private static Map<Integer, Reservation> reservationList = new HashMap<Integer, Reservation>();

  public void saveNewReservation(Reservation newReservation) {

  }

  private boolean isNull(Object obj) {
    return obj == null;
  }
}
