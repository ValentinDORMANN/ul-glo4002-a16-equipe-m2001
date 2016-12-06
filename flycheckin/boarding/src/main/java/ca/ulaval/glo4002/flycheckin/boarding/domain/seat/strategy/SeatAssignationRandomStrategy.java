package ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationRandomStrategy extends SeatAssignationStrategy {

  @Override
  public String assignSeatNumber(List<Seat> availableSeats, String seatClass, boolean isChild) throws NoSeatAvailableException {
    availableSeats = siftAvailableSeatsBySeatClass(availableSeats, seatClass);
    siftAvailableSeatAccordingPassengerAge(availableSeats, isChild);

    int randomIndex = (int) Math.floor(Math.random() * (availableSeats.size() - 1));
    Seat seat = availableSeats.get(randomIndex);
    return seat.getSeatNumber();
  }
}
