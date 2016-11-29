package ca.ulaval.glo4002.flycheckin.boarding.exception;

public class LuggageTypeUndefinedException extends BoardingModuleException {

  private static final long serialVersionUID = 1L;

  public LuggageTypeUndefinedException() {
  }

  public LuggageTypeUndefinedException(String message) {
    super(message);
  }
}

