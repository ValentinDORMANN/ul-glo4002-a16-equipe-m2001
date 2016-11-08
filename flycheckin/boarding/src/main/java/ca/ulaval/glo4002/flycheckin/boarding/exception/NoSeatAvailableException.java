package ca.ulaval.glo4002.flycheckin.boarding.exception;

public class NoSeatAvailableException extends BoardingModuleException {

  private static final long serialVersionUID = 1L;

  public NoSeatAvailableException() {
  }

  public NoSeatAvailableException(String message) {
    super(message);
  }
}
