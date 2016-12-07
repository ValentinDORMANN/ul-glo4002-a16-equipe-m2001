package ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public abstract class SeatAssignationStrategy {

  private static final String NO_SEAT_AVAILABLE = "No more seat available for this seat class";

  public abstract String assignSeatNumber(List<Seat> availableSeats, String seatClass, boolean isChild);

  protected List<Seat> siftAvailableSeatsBySeatClass(List<Seat> availableSeats, String seatClass) {
    List<Seat> availableSeatClass = new ArrayList<Seat>();

    for (Seat seat : availableSeats) {
      if (seat.hasClass(seatClass))
        availableSeatClass.add(seat);
    }
    if (availableSeatClass.isEmpty())
      throw new NoSeatAvailableException(NO_SEAT_AVAILABLE);
    return availableSeatClass;
  }

  protected List<Seat> siftAvailableSeatAccordingPassengerAge(List<Seat> availableSeats, boolean isChild) {
    List<Seat> availableChildSeats = new ArrayList<Seat>();
    if (isChild) {
      for (Seat seat : availableSeats) {
        if (!seat.isExitRow()) {
          availableChildSeats.add(seat);
        }
      }
      if (availableChildSeats.isEmpty())
        throw new NoSeatAvailableException(NO_SEAT_AVAILABLE);
      return availableChildSeats;
    } else
      return availableSeats;
  }

  protected Seat selectCheapestSeat(List<Seat> availableSeats) {
    Seat selectedSeat = availableSeats.get(0);
    for (Seat seat : availableSeats) {
      if (seat.isCheaperThan(selectedSeat))
        selectedSeat = seat;
    }
    return selectedSeat;
  }
}
