package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;

public class NotAllowableLuggageException extends BoardingModuleException {

  private static final long serialVersionUID = 1L;

  public NotAllowableLuggageException() {
  }

  public NotAllowableLuggageException(String message) {
    super(message);
  }
}
