package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationCheapestStrategy implements SeatAssignationStrategy {

  private static final String NO_SEAT_AVAILABLE = "No more seat available for this seat class";

  @Override
  public String assignSeatNumber(List<Seat> availableSeats, String seatClass) throws NoSeatAvailableException {
    availableSeats = siftAvailableSeatsBySeatClass(availableSeats, seatClass);
    if (availableSeats.isEmpty())
      throw new NoSeatAvailableException(NO_SEAT_AVAILABLE);
    Seat selectedSeat = getCheapestSeat(availableSeats);
    return selectedSeat.getSeatNumber();
  }

  private Seat getCheapestSeat(List<Seat> availableSeats) {
    Seat selectedSeat = availableSeats.get(0);
    for (Seat seat : availableSeats) {
      if (seat.isCheaperThan(selectedSeat))
        selectedSeat = seat;
    }
    return selectedSeat;
  }

  private List<Seat> siftAvailableSeatsBySeatClass(List<Seat> availableSeats, String seatClass) {
    List<Seat> availableSeatClass = new ArrayList<Seat>();
    for (Seat seat : availableSeats) {
      if (seat.hasClass(seatClass))
        availableSeatClass.add(seat);
    }
    if (availableSeatClass.isEmpty())
      throw new NoSeatAvailableException(NO_SEAT_AVAILABLE);
    return availableSeatClass;
  }
}
