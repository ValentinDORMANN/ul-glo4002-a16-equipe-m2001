package ca.ulaval.glo4002.flycheckin.boarding.domain;

public interface SeatAssignationRepository {
  int assignSeatToPassenger();

  String getPassengerSeat();
}
