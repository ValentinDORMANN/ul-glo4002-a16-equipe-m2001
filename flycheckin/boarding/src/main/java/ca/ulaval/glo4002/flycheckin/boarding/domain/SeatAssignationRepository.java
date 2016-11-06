package ca.ulaval.glo4002.flycheckin.boarding.domain;

public interface SeatAssignationRepository {

  int persistSeatAssignation(SeatAssignation seatAssignation);

  String getSeatAssignationByPassenger(String passengerHash);
}
