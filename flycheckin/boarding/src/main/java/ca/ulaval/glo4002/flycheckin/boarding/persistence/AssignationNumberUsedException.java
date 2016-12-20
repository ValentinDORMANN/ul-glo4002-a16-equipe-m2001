package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;

public class AssignationNumberUsedException extends BoardingModuleException {

  private static final long serialVersionUID = 1L;

  public AssignationNumberUsedException() {
  }

  public AssignationNumberUsedException(String message) {
    super(message);
  }
}
