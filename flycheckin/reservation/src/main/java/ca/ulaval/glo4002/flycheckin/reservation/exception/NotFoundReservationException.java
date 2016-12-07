package ca.ulaval.glo4002.flycheckin.reservation.exception;

public class NotFoundReservationException extends ReservationModuleException {

  private static final long serialVersionUID = 1L;

  public NotFoundReservationException() {
  }
  
  public NotFoundReservationException(String message) {
    super(message);
  }
}
