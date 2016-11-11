package ca.ulaval.glo4002.flycheckin.reservation.exception;

public class ReservationModuleException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ReservationModuleException() {
  }

  public ReservationModuleException(String message) {
    super(message);
  }
}
