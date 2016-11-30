package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;

public class LuggageTypeUndefinedException extends BoardingModuleException {

  private static final long serialVersionUID = 1L;

  public LuggageTypeUndefinedException() {
  }

  public LuggageTypeUndefinedException(String message) {
    super(message);
  }
}

