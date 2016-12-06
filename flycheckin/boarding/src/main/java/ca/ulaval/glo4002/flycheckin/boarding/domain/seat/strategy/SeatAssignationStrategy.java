package ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public abstract class SeatAssignationStrategy {

  private static final String NO_SEAT_AVAILABLE = "No more seat available for this seat class";

  public abstract String assignSeatNumber(List<Seat> availableSeats, String seatClass, boolean isJunior);

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

  protected void siftAvailableSeatAccordingPassengerAge(List<Seat> availableSeats, boolean isJunior) {
    if (isJunior) {
      for (int index = 0; index < availableSeats.size(); index++) {
        if (availableSeats.get(index).isExitRow()) {
          availableSeats.remove(index);
          index--;
        }
      }
      if (availableSeats.isEmpty())
        throw new NoSeatAvailableException(NO_SEAT_AVAILABLE);
    }
  }
}
