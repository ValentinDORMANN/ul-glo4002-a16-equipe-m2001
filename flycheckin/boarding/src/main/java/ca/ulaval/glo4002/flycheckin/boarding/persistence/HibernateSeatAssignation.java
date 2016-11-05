package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignationRepository;

public class HibernateSeatAssignation implements SeatAssignationRepository {

  @Override
  public int assignSeatToPassenger(Passenger passenger) {
    // TODO
    return 0;
  }

  @Override
  public String getPassengerSeat(String passengerHash) {
    // TODO Auto-generated method stub
    return null;
  }
}
