package ca.ulaval.glo4002.flycheckin.boarding.exception;

public class NoSeatAssignedException extends BoardingModuleException {

  private static final long serialVersionUID = 1L;

  public NoSeatAssignedException() {
  }

  public NoSeatAssignedException(String message) {
    super(message);
  }
}
