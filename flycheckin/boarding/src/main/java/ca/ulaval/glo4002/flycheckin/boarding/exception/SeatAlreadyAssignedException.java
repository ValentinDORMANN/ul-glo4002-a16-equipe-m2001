package ca.ulaval.glo4002.flycheckin.boarding.exception;

public class SeatAlreadyAssignedException extends BoardingModuleException {

  private static final long serialVersionUID = 1L;

  public SeatAlreadyAssignedException() {
  }

  public SeatAlreadyAssignedException(String message) {
    super(message);
  }
}
