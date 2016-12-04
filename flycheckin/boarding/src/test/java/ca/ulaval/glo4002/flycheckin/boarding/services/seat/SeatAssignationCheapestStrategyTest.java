package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationCheapestStrategyTest {

  private static final double SEAT_PRICE = 45.00;
  private static final String SEAT_NUMBER = "16-B";
  private static final String SEAT_CLASS = "economic";
  private static final double EXPENSIVE_SEAT_PRICE = 75.00;
  private static final String EXPENSIVE_SEAT_NUMBER = "20-D";
  private static final String ANOTHER_SEAT_CLASS = "otherSeatClass";
  private static final boolean IS_JUNIOR = true;
  private static final boolean IS_ADULT = false;

  private Seat seat;
  private Seat expensiveSeat;
  private List<Seat> availableSeats;
  private SeatAssignationCheapestStrategy cheapestSeatAssignation;

  @Before
  public void initiateTest() {
    seat = new Seat();
    seat.setSeatNumber(SEAT_NUMBER);
    seat.setSeatClass(SEAT_CLASS);
    seat.setPrice(SEAT_PRICE);
    expensiveSeat = new Seat();
    expensiveSeat.setSeatNumber(EXPENSIVE_SEAT_NUMBER);
    expensiveSeat.setSeatClass(SEAT_CLASS);
    expensiveSeat.setPrice(EXPENSIVE_SEAT_PRICE);
    availableSeats = new ArrayList<Seat>();
    cheapestSeatAssignation = new SeatAssignationCheapestStrategy();
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenEmptySeatListWhenAssignSeatToPassengerThenReturnException() {
    cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, IS_ADULT);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenNotEmptySeatListWhenAssignSeatToPassengerWhoHasNoSeatAvailableThenReturnException() {
    availableSeats.add(expensiveSeat);
    availableSeats.add(seat);

    cheapestSeatAssignation.assignSeatNumber(availableSeats, ANOTHER_SEAT_CLASS, IS_ADULT);
  }

  @Test
  public void givenTwoSeatWithSameClassWhenAssignSeatToPassengerWhoHasSeatClassThenAssertSeatAssignIsTheCheapest() {
    availableSeats.add(expensiveSeat);
    availableSeats.add(seat);

    String seatNumber = cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, IS_ADULT);

    assertEquals(SEAT_NUMBER, seatNumber);
  }

  @Test
  public void givenListWithOneSeatHasPassengerSeatClassWhenAssignSeatToPassengerThenAssertPassengerGetExpectedSeat() {
    seat.setSeatClass(ANOTHER_SEAT_CLASS);
    availableSeats.add(expensiveSeat);
    availableSeats.add(seat);

    String seatNumber = cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, IS_ADULT);

    assertEquals(EXPENSIVE_SEAT_NUMBER, seatNumber);
  }
}
