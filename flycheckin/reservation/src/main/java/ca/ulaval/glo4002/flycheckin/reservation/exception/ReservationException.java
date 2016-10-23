package ca.ulaval.glo4002.flycheckin.reservation.exception;

public class ReservationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ReservationException() {
  }

  public ReservationException(String message) {
    super(message);
  }

}
