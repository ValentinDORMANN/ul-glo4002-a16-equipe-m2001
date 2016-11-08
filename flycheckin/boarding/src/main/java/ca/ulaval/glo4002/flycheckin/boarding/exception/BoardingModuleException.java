package ca.ulaval.glo4002.flycheckin.boarding.exception;

public class BoardingModuleException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BoardingModuleException() {
  }

  public BoardingModuleException(String message) {
    super(message);
  }

}
