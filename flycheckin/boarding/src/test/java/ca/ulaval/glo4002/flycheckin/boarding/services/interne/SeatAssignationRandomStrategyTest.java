package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationRandomStrategyTest {

  private static final String SEAT_NUMBER = "13-K";
  private static final String SEAT_CLASS = "economic";
  private static final String ANOTHER_SEAT_CLASS = "wrongSeatClass";
  private Seat seat;
  private List<Seat> availableSeats;
  private SeatAssignationRandomStrategy randomSeatAssignation;

  @Before
  public void initiateTest() {
    availableSeats = new ArrayList<Seat>();
    randomSeatAssignation = new SeatAssignationRandomStrategy();
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenEmptyAvailableSeatListWhenAssignSeatNumberThenReturnException() {
    randomSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS);
  }

  @Test
  public void givenListWithOnlyOneSeatWhenAssignSeatNumberWithSameSeatClassThenReturnSeatNumber() {
    seat = new Seat();
    seat.setSeatNumber(SEAT_NUMBER);
    seat.setSeatClass(SEAT_CLASS);
    availableSeats.add(seat);

    String seatNumber = randomSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS);

    assertEquals(SEAT_NUMBER, seatNumber);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenListWithOnlyOneSeatWhenAssignSeatNumberWithAnotherSeatClassThenReturnException() {
    seat = new Seat();
    seat.setSeatNumber(SEAT_NUMBER);
    seat.setSeatClass(SEAT_CLASS);
    availableSeats.add(seat);

    randomSeatAssignation.assignSeatNumber(availableSeats, ANOTHER_SEAT_CLASS);
  }
}
