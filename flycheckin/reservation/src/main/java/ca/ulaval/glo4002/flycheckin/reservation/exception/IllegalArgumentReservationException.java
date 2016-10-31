package ca.ulaval.glo4002.flycheckin.reservation.exception;

public class IllegalArgumentReservationException extends ApplicationException {

  private static final long serialVersionUID = 1L;

  public IllegalArgumentReservationException(String message) {
    super(message);
  }
}