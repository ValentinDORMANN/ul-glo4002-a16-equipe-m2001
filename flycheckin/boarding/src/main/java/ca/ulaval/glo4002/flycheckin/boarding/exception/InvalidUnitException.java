package ca.ulaval.glo4002.flycheckin.boarding.exception;

public class InvalidUnitException extends BoardingModuleException {

  private static final long serialVersionUID = 1L;

  public InvalidUnitException() {
  }

  public InvalidUnitException(String message) {
    super(message);
  }
}
