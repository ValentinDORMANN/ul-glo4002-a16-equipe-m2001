package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationCheapestStrategy extends SeatAssignationStrategy {

  @Override
  public String assignSeatNumber(List<Seat> availableSeats, String seatClass, boolean isJunior) throws NoSeatAvailableException {
    availableSeats = siftAvailableSeatsBySeatClass(availableSeats, seatClass);
    siftAvailableSeatAccordingPassengerAge(availableSeats, isJunior);

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
}
