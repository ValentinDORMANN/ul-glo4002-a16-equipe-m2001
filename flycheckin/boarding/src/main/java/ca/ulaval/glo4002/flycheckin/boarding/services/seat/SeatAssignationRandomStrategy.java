package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationRandomStrategy extends SeatAssignationStrategy {

  @Override
  public String assignSeatNumber(List<Seat> availableSeats, String seatClass, boolean isJunior) throws NoSeatAvailableException {
    availableSeats = siftAvailableSeatsBySeatClass(availableSeats, seatClass);
    siftAvailableSeatAccordingPassengerAge(availableSeats, isJunior);

    int randomIndex = (int) Math.floor(Math.random() * (availableSeats.size() - 1));
    Seat seat = availableSeats.get(randomIndex);
    return seat.getSeatNumber();
  }
}
