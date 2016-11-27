package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;

public class SeatAssignationLegroomStrategy extends SeatAssignationStrategy {

  @Override
  public String assignSeatNumber(List<Seat> availableSeats, String seatClass) {
    availableSeats = siftAvailableSeatsBySeatClass(availableSeats, seatClass);
    Seat selectedSeat = getCheapestLargeLegroomSeat(availableSeats);
    return selectedSeat.getSeatNumber();
  }

  private Seat getCheapestLargeLegroomSeat(List<Seat> availableSeats) {
    Seat selectedSeat = availableSeats.get(0);
    for (Seat seat : availableSeats) {
      if (seat.isLegroomGreaterThan(selectedSeat)
          || (seat.hasSameLegroomWith(selectedSeat) && seat.isCheaperThan(selectedSeat)))
        selectedSeat = seat;
    }
    return selectedSeat;
  }
}
