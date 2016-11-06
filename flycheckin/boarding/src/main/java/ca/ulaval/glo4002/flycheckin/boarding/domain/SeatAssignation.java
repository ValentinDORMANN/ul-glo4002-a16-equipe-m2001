package ca.ulaval.glo4002.flycheckin.boarding.domain;

public class SeatAssignation {

  private String passengerHash;
  private String seatNumber;

  public SeatAssignation() {
  }

  public String getPassengerHash() {
    return passengerHash;
  }

  public String getSeatNumber() {
    return seatNumber;
  }

  public String assignSeatNumberToPassenger(String passengerHash, String mode) {
    return null;
  }
}
