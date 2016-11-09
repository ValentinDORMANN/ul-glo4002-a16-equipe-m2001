package ca.ulaval.glo4002.flycheckin.boarding.exception;

public class ExcededLuggageException extends BoardingModuleException {

  private static final long serialVersionUID = 1L;

  public ExcededLuggageException() {
  }

  public ExcededLuggageException(String message) {
    super(message);
  }
}
