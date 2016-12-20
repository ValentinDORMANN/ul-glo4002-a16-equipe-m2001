package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;

public class NotFoundPassengerException extends BoardingModuleException {

  private static final long serialVersionUID = 1L;

  public NotFoundPassengerException() {
  }

  public NotFoundPassengerException(String message) {
    super(message);
  }
}
