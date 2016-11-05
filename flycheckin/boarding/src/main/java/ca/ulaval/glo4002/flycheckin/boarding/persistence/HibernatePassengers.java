package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import ca.ulaval.glo4002.flycheckin.boarding.domain.PassengersRepository;

public class HibernatePassengers implements PassengersRepository {

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
