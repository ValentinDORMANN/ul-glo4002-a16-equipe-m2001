package ca.ulaval.glo4002.flycheckin.boarding.services.interne.seat;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;

public interface SeatAssignationStrategy {

  public String assignSeatNumber(List<Seat> availableSeats, String seatClass);
}
