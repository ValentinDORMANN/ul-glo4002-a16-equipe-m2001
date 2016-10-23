package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.*;
import ca.ulaval.glo4002.flycheckin.reservation.exception.*;

public class ReservationService {

  public Reservation createReservation(ReservationDto reservationDto) throws IllegalArgumentReservationException {
    return new Reservation(reservationDto);
  }
}
