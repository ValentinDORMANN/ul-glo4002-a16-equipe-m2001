package ca.ulaval.glo4002.flycheckin.reservation.exception;

public class ApplicationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ApplicationException() {
  }

  public ApplicationException(String message) {
    super(message);
  }

}
