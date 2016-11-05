package ca.ulaval.glo4002.flycheckin.boarding.exception;

public class IllegalArgumentReservationException extends FlyCheckinApplicationException {

  private static final long serialVersionUID = 1L;

  public IllegalArgumentReservationException() {
  }

  public IllegalArgumentReservationException(String message) {
    super(message);
  }
}