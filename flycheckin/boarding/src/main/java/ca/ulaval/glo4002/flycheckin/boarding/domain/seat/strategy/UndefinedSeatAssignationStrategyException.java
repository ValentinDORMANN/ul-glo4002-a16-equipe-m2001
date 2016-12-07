package ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy;

import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;

public class UndefinedSeatAssignationStrategyException extends BoardingModuleException{

  private static final long serialVersionUID = 1L;

  public UndefinedSeatAssignationStrategyException() {
  }

  public UndefinedSeatAssignationStrategyException(String message) {
    super(message);
  }
}
