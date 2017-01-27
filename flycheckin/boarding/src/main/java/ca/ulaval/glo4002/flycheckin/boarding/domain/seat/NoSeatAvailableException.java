package ca.ulaval.glo4002.flycheckin.boarding.domain.seat;

import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;

public class NoSeatAvailableException extends BoardingModuleException {

  private static final long serialVersionUID = 1L;

  public NoSeatAvailableException() {
  }

  public NoSeatAvailableException(String message) {
    super(message);
  }
}
