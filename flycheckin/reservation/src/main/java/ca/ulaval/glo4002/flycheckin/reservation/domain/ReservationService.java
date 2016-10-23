package ca.ulaval.glo4002.flycheckin.reservation.domain;

import ca.ulaval.glo4002.flycheckin.reservation.api.DTO.*;

public class ReservationService {

  public Reservation createReservation(ReservationDto reservationDto) {
    return new Reservation(reservationDto);
  }
}