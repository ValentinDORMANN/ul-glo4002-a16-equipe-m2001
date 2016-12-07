package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;

public class UndefinedTypeLuggageException extends BoardingModuleException {

  private static final long serialVersionUID = 1L;

  public UndefinedTypeLuggageException() {
  }

  public UndefinedTypeLuggageException(String message) {
    super(message);
  }
}

