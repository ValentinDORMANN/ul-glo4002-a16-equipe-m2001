package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.List;

public class SeatAssignationCheapestStrategy implements SeatAssignationStrategy {

  @Override
  public String chooseSeatNumber(List<Seat> availableSeats) {
    // TODO Auto-generated method stub
    return "12-C";
  }

}
