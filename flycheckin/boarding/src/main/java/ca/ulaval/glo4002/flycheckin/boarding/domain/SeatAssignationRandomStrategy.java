package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationRandomStrategy implements SeatAssignationStrategy {

  private static final String NO_SEAT_AVAILABLE = "No more seat available for this seat class";

  @Override
  public String chooseSeatNumber(List<Seat> availableSeats) {
    if (availableSeats.isEmpty())
      throw new NoSeatAvailableException(NO_SEAT_AVAILABLE);
    int randomIndex = (int) Math.ceil(Math.random() * (availableSeats.size() - 1));
    return availableSeats.get(randomIndex).seatNumber;
  }

}
