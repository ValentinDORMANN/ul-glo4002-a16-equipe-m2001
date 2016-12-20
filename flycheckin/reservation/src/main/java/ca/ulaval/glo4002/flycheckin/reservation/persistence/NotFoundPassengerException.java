package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;

public class NotFoundPassengerException extends ReservationModuleException {

  private static final long serialVersionUID = 1L;

  public NotFoundPassengerException() {
  }
  
  public NotFoundPassengerException(String message) {
    super(message);
  }
}

