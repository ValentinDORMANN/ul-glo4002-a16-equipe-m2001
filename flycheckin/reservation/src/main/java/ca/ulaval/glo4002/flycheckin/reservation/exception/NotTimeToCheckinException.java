package ca.ulaval.glo4002.flycheckin.reservation.exception;

public class NotTimeToCheckinException extends FlyCheckinApplicationException {

  private static final long serialVersionUID = 1L;
  
  public NotTimeToCheckinException(String message) {
    super(message);
  }
}