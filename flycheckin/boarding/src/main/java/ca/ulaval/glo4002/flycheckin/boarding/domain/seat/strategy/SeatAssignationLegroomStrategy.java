package ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy;

import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;

public class SeatAssignationLegroomStrategy extends SeatAssignationStrategy {

  @Override
  public String assignSeatNumber(List<Seat> availableSeats, String seatClass, boolean isChild) {
    availableSeats = siftAvailableSeatsBySeatClass(availableSeats, seatClass);
    availableSeats = siftAvailableSeatAccordingPassengerAge(availableSeats, isChild);
    System.out.println("MON PASSAGER EST UN JUNIOR = " + isChild + "\n");
    afficherListe(availableSeats, 2);

    Seat selectedSeat = getCheapestLargeLegroomSeat(availableSeats);
    return selectedSeat.getSeatNumber();
  }

  private Seat getCheapestLargeLegroomSeat(List<Seat> availableSeats) {
    Seat selectedSeat = availableSeats.get(0);
    for (Seat seat : availableSeats) {
      if (seat.isLegroomGreaterThan(selectedSeat) || (seat.hasSameLegroomWith(selectedSeat) && seat.isCheaperThan(selectedSeat)))
        selectedSeat = seat;
    }
    return selectedSeat;
  }

  private void afficherListe(List<Seat> seatList, int moment) {
    if (moment == 1)
      System.out.println("AVANT FILTRE DE EXITROW **** " + seatList.size() + " sieges\n");
    else
      System.out.println("APRÈS FILTRE DE EXITROW **** " + seatList.size() + " sieges\n");

    for (Seat seat : seatList) {
      System.out.println(seat.getSeatNumber() + " : ExitRow = " + seat.isExitRow());
      System.out.println();
    }
  }
}
