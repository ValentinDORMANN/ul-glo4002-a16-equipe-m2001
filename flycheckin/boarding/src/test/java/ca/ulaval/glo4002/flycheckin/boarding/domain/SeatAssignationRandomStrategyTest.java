package ca.ulaval.glo4002.flycheckin.boarding.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationRandomStrategyTest {

  private static final String SEAT_NUMBER = "13-K";
  private Seat seat;
  private List<Seat> availableSeats;
  private SeatAssignationRandomStrategy randomSeatAssignation;

  @Before
  public void initiateTest() {
    availableSeats = new ArrayList<Seat>();
    randomSeatAssignation = new SeatAssignationRandomStrategy();
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenEmptyAvailableSeatListWhenChooseSeatNumberThenReturnException() {
    randomSeatAssignation.chooseSeatNumber(availableSeats);
  }

  @Test
  public void givenListWithOneSeatWhenChosseSeatNumberThenReturnSeatNumber() {
    seat = new Seat();
    seat.seatNumber = SEAT_NUMBER;
    availableSeats.add(seat);

    String seatNumber = randomSeatAssignation.chooseSeatNumber(availableSeats);

    assertEquals(SEAT_NUMBER, seatNumber);
  }
}
