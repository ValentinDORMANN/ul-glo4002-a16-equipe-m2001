package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.List;

public class SeatAssignationRandomStrategy implements SeatAssignationStrategy {

  @Override
  public String assignSeatNumber(List<Seat> availableSeats) {
    // TODO Auto-generated method stub
    return "12-R";
  }

}
