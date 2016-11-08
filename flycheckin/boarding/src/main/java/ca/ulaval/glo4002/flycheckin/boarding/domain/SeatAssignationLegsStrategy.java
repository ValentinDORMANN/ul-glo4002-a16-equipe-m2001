package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.List;

public class SeatAssignationLegsStrategy implements SeatAssignationStrategy {

  @Override
  public String chooseSeatNumber(List<Seat> availableSeats, String seatClass) {
    // TODO Auto-generated method stub
    return "12-L";
  }

}
