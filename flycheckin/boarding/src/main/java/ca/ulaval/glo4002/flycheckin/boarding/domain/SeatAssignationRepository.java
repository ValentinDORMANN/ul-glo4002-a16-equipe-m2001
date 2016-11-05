package ca.ulaval.glo4002.flycheckin.boarding.domain;

public interface SeatAssignationRepository {
  int assignSeatToPassenger(Passenger passenger);

  String getPassengerSeat(String passengerHash);
}
