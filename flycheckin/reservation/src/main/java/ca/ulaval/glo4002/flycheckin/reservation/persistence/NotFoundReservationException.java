package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;

public class NotFoundReservationException extends ReservationModuleException {

  private static final long serialVersionUID = 1L;

  public NotFoundReservationException() {
  }
  
  public NotFoundReservationException(String message) {
    super(message);
  }
}
