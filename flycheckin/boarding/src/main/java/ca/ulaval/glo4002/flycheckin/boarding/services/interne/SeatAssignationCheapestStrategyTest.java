package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationCheapestStrategyTest {

  private static final String SEAT_NUMBER = "16-B";
  private static final String OTHER_SEAT_NUMBER = "20-D";
  private static final String SEAT_CLASS = "economic";
  private static final String ANOTHER_SEAT_CLASS = "otherSeatClass";
  private Seat seat, otherSeat;
  private List<Seat> availableSeats;
  private SeatAssignationCheapestStrategy cheapestSeatAssignation;

  @Before
  public void initiateTest() {
    seat = new Seat();
    seat.setSeatNumber(SEAT_NUMBER);
    seat.setSeatClass(SEAT_CLASS);
    otherSeat = new Seat();
    otherSeat.setSeatNumber(OTHER_SEAT_NUMBER);
    otherSeat.setSeatClass(SEAT_CLASS);
    availableSeats = new ArrayList<Seat>();
    cheapestSeatAssignation = new SeatAssignationCheapestStrategy();
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenEmptySeatListWhenAssignSeatToPassengerThenReturnException() {
    cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS);
  }

  @Test
  public void givenAvailableSeatListWithTwoSeatWhenAssignSeatToPassengeThenAssignCheapestSeat() {
    availableSeats.add(seat);
    availableSeats.add(otherSeat);

    String seatNumber = cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS);

    // AssertEquals()
  }
}
