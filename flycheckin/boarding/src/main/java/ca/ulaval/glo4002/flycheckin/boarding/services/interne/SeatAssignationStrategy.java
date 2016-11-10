package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;

public interface SeatAssignationStrategy {

  public String assignSeatNumber(List<Seat> availableSeats, String seatClass);
}
