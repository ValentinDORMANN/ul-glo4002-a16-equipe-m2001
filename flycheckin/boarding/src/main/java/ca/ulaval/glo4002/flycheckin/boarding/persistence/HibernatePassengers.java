package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignationRepository;

public class HibernatePassengers implements SeatAssignationRepository {

  @Override
  public int assignSeatToPassenger(String passengerHash) {
    return 0;
  }

  @Override
  public String getPassengerSeat(String passengerHash) {
    // TODO Auto-generated method stub
    return null;
  }
}
