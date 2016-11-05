package ca.ulaval.glo4002.flycheckin.boarding.domain;

public interface RepositorySeatAssignation {
  int assignSeatToPassenger();

  String getPassengerSeat();
}
