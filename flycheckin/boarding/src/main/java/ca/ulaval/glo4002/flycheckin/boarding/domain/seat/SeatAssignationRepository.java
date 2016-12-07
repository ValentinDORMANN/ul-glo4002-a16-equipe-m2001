package ca.ulaval.glo4002.flycheckin.boarding.domain.seat;

public interface SeatAssignationRepository {

  void persistSeatAssignation(SeatAssignation seatAssignation);

  String getPassengerHashSeatNumber(String passengerHash);
}
