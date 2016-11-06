package ca.ulaval.glo4002.flycheckin.boarding.exception;

public class ExcededCheckedLuggageException extends FlyCheckinApplicationException {
  
  private static final long serialVersionUID = 1L;

  public ExcededCheckedLuggageException() {
  }

  public ExcededCheckedLuggageException(String message) {
    super(message);
  }
}
