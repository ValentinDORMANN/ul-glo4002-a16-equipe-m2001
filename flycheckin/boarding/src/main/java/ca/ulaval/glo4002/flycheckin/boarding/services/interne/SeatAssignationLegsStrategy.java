package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;

public class SeatAssignationLegsStrategy implements SeatAssignationStrategy {

  @Override
  public String chooseSeatNumber(List<Seat> availableSeats, String seatClass) {
    // TODO Auto-generated method stub
    return "12-L";
  }
}
