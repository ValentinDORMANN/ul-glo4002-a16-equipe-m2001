package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignationRepository;

public class HibernateSeatAssignation implements SeatAssignationRepository {

  @Override
  public void persistSeatAssignation(SeatAssignation seatAssignation) {
    // TODO
  }

  @Override
  public String getPassengerHashSeatNumber(String passengerHash) {
    // TODO Auto-generated method stub
    return null;
  }
}
