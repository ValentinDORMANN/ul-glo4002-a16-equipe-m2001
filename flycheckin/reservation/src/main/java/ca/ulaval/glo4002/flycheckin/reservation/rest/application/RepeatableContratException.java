package ca.ulaval.glo4002.flycheckin.reservation.rest.application;

public class RepeatableContratException extends RuntimeException{
 
  private static final long serialVersionUID = 1L;

  public RepeatableContratException(Class<?> contract) {
    super("That's a second time that your registering this : '" + contract.getCanonicalName() + "'");
  }
}
