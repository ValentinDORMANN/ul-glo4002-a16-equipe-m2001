package ca.ulaval.glo4002.flycheckin.boarding.exception;

public class PassengerAlreadySeatAssigned extends FlyCheckinApplicationException {

  private static final long serialVersionUID = 1L;

  public PassengerAlreadySeatAssigned() {
  }

  public PassengerAlreadySeatAssigned(String message) {
    super(message);
  }
}
