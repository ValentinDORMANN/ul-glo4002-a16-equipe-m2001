package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignationRepository;

public class HibernateSeatAssignation implements SeatAssignationRepository {

  @Override
  public int persistSeatAssignation(SeatAssignation seatAssignation) {
    // TODO
    return 0;
  }

  @Override
  public String getSeatAssignationByPassenger(String passengerHash) {
    // TODO Auto-generated method stub
    return null;
  }
}
