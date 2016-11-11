package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationCheapestStrategyTest {

  private static final double SEAT_PRICE = 45.00;
  private static final String SEAT_NUMBER = "16-B";
  private static final String SEAT_CLASS = "economic";
  private static final double EXPENSIVE_SEAT_PRICE = 75.00;
  private static final String OTHER_SEAT_NUMBER = "20-D";
  private static final String ANOTHER_SEAT_CLASS = "otherSeatClass";
  private Seat seat;
  private Seat otherSeat;
  private List<Seat> availableSeats;
  private SeatAssignationCheapestStrategy cheapestSeatAssignation;

  @Before
  public void initiateTest() {
    seat = new Seat();
    seat.setSeatNumber(SEAT_NUMBER);
    seat.setSeatClass(SEAT_CLASS);
    seat.setPrice(SEAT_PRICE);
    otherSeat = new Seat();
    otherSeat.setSeatNumber(OTHER_SEAT_NUMBER);
    otherSeat.setSeatClass(SEAT_CLASS);
    otherSeat.setPrice(EXPENSIVE_SEAT_PRICE);
    availableSeats = new ArrayList<Seat>();
    cheapestSeatAssignation = new SeatAssignationCheapestStrategy();
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenEmptySeatListWhenAssignSeatToPassengerThenReturnException() {
    cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenNotEmptySeatListWhenAssignSeatToPassengerWhoHasWrongseatClassThenReturnException() {
    availableSeats.add(otherSeat);
    availableSeats.add(seat);

    cheapestSeatAssignation.assignSeatNumber(availableSeats, ANOTHER_SEAT_CLASS);
  }

  @Test
  public void givenTwoSeatWithSameClassWhenAssignSeatToPassengerWhoHasSeatClassThenAssertSeatAssignIsTheCheapest() {
    availableSeats.add(otherSeat);
    availableSeats.add(seat);

    String seatNumber = cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS);

    assertEquals(SEAT_NUMBER, seatNumber);
  }

  @Test
  public void givenListWithOneSeatHasPassengerSeatClassWhenAssignSeatToPassengerThenAssertPassengerGetExpectedSeat() {
    otherSeat.setSeatClass(ANOTHER_SEAT_CLASS);
    availableSeats.add(otherSeat);
    availableSeats.add(seat);

    String seatNumber = cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS);

    assertEquals(SEAT_NUMBER, seatNumber);
  }
}
