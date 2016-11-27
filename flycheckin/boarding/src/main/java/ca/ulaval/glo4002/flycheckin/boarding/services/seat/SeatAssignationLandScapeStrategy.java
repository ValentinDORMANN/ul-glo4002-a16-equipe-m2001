package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationLandScapeStrategy extends SeatAssignationStrategy {

  @Override
  public String assignSeatNumber(List<Seat> availableSeats, String seatClass) throws NoSeatAvailableException {
    availableSeats = siftAvailableSeatsBySeatClass(availableSeats, seatClass);
    Seat selectedSeat = getSeatWithBestView(availableSeats);
    return selectedSeat.getSeatNumber();
  }

  private Seat getSeatWithBestView(List<Seat> availableSeats) {
    Seat selectedSeat = availableSeats.get(0);
    return selectedSeat;
  }

  private List<Seat> filterAvailableSeatsByLandScape(List<Seat> availableSeats) {
    return null;
  }

  private List<Seat> filterAvailableSeatsWithClearView(List<Seat> availableSeats) {
    return null;
  }

  private List<Seat> filterAvailableSeatsWithWindowView(List<Seat> availableSeats) {
    return null;
  }

  private Seat selectCheapestSeat(List<Seat> availableSeats) {
    Seat selectedSeat = availableSeats.get(0);
    return selectedSeat;
  }
}
