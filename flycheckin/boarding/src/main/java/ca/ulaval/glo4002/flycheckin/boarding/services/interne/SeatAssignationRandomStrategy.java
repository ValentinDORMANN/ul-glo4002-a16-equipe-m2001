package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationRandomStrategy implements SeatAssignationStrategy {

  private static final String NO_SEAT_AVAILABLE = "No more seat available for this seat class";

  @Override
  public String chooseSeatNumber(List<Seat> availableSeats, String seatClass) {
    availableSeats = siftAvailableSeatsBySeatClass(availableSeats, seatClass);
    if (availableSeats.isEmpty())
      throw new NoSeatAvailableException(NO_SEAT_AVAILABLE);
    int randomIndex = (int) Math.floor(Math.random() * (availableSeats.size() - 1));
    return availableSeats.get(randomIndex).getSeatNumber();
  }

  private List<Seat> siftAvailableSeatsBySeatClass(List<Seat> availableSeats, String seatClass) {
    List<Seat> availableSeatClass = new ArrayList<Seat>();
    for (Seat seat : availableSeats) {
      if (seat.getSeatClass().equals(seatClass))
        availableSeatClass.add(seat);
    }
    return availableSeatClass;
  }

}
