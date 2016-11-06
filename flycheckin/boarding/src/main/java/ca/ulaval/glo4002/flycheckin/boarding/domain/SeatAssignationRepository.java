package ca.ulaval.glo4002.flycheckin.boarding.domain;

public interface SeatAssignationRepository {

  void persistSeatAssignation(int assignationNumber, SeatAssignation seatAssignation);

  String getPassengerHashSeatNumber(String passengerHash);
}
