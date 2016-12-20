package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;

public class NotCheckedinException extends ReservationModuleException {

  private static final long serialVersionUID = 1L;

  public NotCheckedinException() {
  }

  public NotCheckedinException(String message) {
    super(message);
  }
}
