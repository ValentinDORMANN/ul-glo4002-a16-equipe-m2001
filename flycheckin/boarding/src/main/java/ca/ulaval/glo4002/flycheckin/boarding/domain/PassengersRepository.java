package ca.ulaval.glo4002.flycheckin.boarding.domain;

public interface PassengersRepository {
  int assignSeatToPassenger(String passengerHash);

  String getPassengerSeat(String passengerHash);
}
