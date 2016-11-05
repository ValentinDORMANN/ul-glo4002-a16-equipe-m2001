package ca.ulaval.glo4002.flycheckin.boarding.exception;

public class NotFoundPassengerException extends FlyCheckinApplicationException {

  private static final long serialVersionUID = 1L;

  public NotFoundPassengerException() {
  }

  public NotFoundPassengerException(String message) {
    super(message);
  }
}
