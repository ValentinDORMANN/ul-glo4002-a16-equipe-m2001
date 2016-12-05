package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationCheapestStrategyTest {

  private static final String SEAT_NUMBER = "16-B";
  private static final String SEAT_CLASS = "economic";
  private static final String EXPENSIVE_SEAT_NUMBER = "20-D";
  private static final String ANOTHER_SEAT_CLASS = "otherSeatClass";
  private static final boolean IS_JUNIOR = true;

  private Seat seatMock;
  private Seat expensiveSeatMock;
  private List<Seat> availableSeats;
  private SeatAssignationCheapestStrategy cheapestSeatAssignation;

  @Before
  public void initiateTest() {
    availableSeats = new ArrayList<Seat>();
    cheapestSeatAssignation = new SeatAssignationCheapestStrategy();
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenEmptySeatListWhenAssignSeatToPassengerThenReturnException() {
    cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, !IS_JUNIOR);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenNotEmptySeatListWhenAssignSeatToPassengerWhoHasNoSeatAvailableThenReturnException() {
    givenExpensiveSeatAvailable(SEAT_CLASS);
    givenCheaperSeatAvailable(SEAT_CLASS);

    cheapestSeatAssignation.assignSeatNumber(availableSeats, ANOTHER_SEAT_CLASS, !IS_JUNIOR);
  }

  @Test
  public void givenTwoSeatWithSameClassWhenAssignSeatToPassengerWhoHasSeatClassThenAssertSeatAssignIsTheCheapest() {
    givenExpensiveSeatAvailable(SEAT_CLASS);
    givenCheaperSeatAvailable(SEAT_CLASS);

    String seatNumber = cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, !IS_JUNIOR);

    assertEquals(SEAT_NUMBER, seatNumber);
  }

  @Test
  public void givenListWithOneSeatHasPassengerSeatClassWhenAssignSeatToPassengerThenAssertPassengerGetExpectedSeat() {
    givenExpensiveSeatAvailable(SEAT_CLASS);
    givenCheaperSeatAvailable(ANOTHER_SEAT_CLASS);

    String seatNumber = cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, !IS_JUNIOR);

    assertEquals(EXPENSIVE_SEAT_NUMBER, seatNumber);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenOnlyExitRowSeatAvailableWhenAssignSeatToJuniorPassengerThenReturnException() {
    givenCheaperSeatAvailable(SEAT_CLASS);
    setSeatAsExitRow(seatMock);

    cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, IS_JUNIOR);
  }

  @Test
  public void givenNotExitRowSeatAvailableWhenAssignSeatToJuniorPassengerThenVerifySeatNumberAssigned() {
    givenExpensiveSeatAvailable(SEAT_CLASS);
    givenCheaperSeatAvailable(SEAT_CLASS);
    setSeatAsExitRow(seatMock);

    String seatNumber = cheapestSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, IS_JUNIOR);

    assertEquals(EXPENSIVE_SEAT_NUMBER, seatNumber);
  }

  private void givenCheaperSeatAvailable(String seatClass) {
    seatMock = mock(Seat.class);
    when(seatMock.getSeatNumber()).thenReturn(SEAT_NUMBER);
    when(seatMock.hasClass(seatClass)).thenReturn(true);
    when(seatMock.isCheaperThan(expensiveSeatMock)).thenReturn(true);
    availableSeats.add(seatMock);
  }

  private void givenExpensiveSeatAvailable(String seatClass) {
    expensiveSeatMock = mock(Seat.class);
    when(expensiveSeatMock.getSeatNumber()).thenReturn(EXPENSIVE_SEAT_NUMBER);
    when(expensiveSeatMock.hasClass(seatClass)).thenReturn(true);
    when(expensiveSeatMock.isCheaperThan(seatMock)).thenReturn(true);
    availableSeats.add(expensiveSeatMock);
  }

  private void setSeatAsExitRow(Seat seatMock) {
    when(seatMock.isExitRow()).thenReturn(true);
  }
}
