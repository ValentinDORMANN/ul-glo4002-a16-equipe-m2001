package ca.ulaval.glo4002.flycheckin.reservation.exception;

public class NotFoundPassengerException extends ReservationModuleException {

  private static final long serialVersionUID = 1L;

  public NotFoundPassengerException() {
  }
  
  public NotFoundPassengerException(String message) {
    super(message);
  }
}

