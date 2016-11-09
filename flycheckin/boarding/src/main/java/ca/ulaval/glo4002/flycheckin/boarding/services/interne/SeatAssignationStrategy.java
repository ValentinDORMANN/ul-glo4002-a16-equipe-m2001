package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;

public interface SeatAssignationStrategy {

  public String chooseSeatNumber(List<Seat> availableSeats, String seatClass);
}
