package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationRandomStrategyTest {

  private static final String SEAT_NUMBER = "13-K";
  private static final String SEAT_CLASS = "economic";
  private static final String ANOTHER_SEAT_CLASS = "wrongSeatClass";
  private static final boolean IS_JUNIOR = true;

  private Seat seatMock;
  private List<Seat> availableSeats;
  private SeatAssignationRandomStrategy randomSeatAssignation;

  @Before
  public void initiateTest() {
    seatMock = mock(Seat.class);
    availableSeats = new ArrayList<Seat>();
    randomSeatAssignation = new SeatAssignationRandomStrategy();
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenEmptyAvailableSeatListWhenAssignSeatToPassengerThenReturnException() {
    randomSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, !IS_JUNIOR);
  }

  @Test
  public void givenListWithOnlyOneSeatWhenAssignSeatWithSameSeatClassThenReturnSeatNumber() {
    givenSeatHasPassengerSeatClass();
    availableSeats.add(seatMock);

    String seatNumber = randomSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, !IS_JUNIOR);

    assertEquals(SEAT_NUMBER, seatNumber);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenListWithOnlyOneSeatWhenAssignSeatWithAnotherSeatClassThenReturnException() {
    givenSeatHasPassengerSeatClass();
    availableSeats.add(seatMock);

    randomSeatAssignation.assignSeatNumber(availableSeats, ANOTHER_SEAT_CLASS, !IS_JUNIOR);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenOnlyExitRowSeatAvailableWhenAssignSeatToJuniorPassengerThenReturnException() {
    givenExitRowSeatHasPassengerSeatClass();
    availableSeats.add(seatMock);

    randomSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, IS_JUNIOR);
  }

  @Test
  public void givenNotExitRowSeatAvailableWhenAssignSeatToJuniorPassengerThenVerifySeatNumber() {
    givenSeatHasPassengerSeatClass();
    availableSeats.add(seatMock);

    String seatNumber = randomSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, IS_JUNIOR);

    assertEquals(SEAT_NUMBER, seatNumber);
  }

  private void givenExitRowSeatHasPassengerSeatClass() {
    when(seatMock.hasClass(SEAT_CLASS)).thenReturn(true);
    when(seatMock.getSeatNumber()).thenReturn(SEAT_NUMBER);
    when(seatMock.isExitRow()).thenReturn(true);
  }

  private void givenSeatHasPassengerSeatClass() {
    when(seatMock.hasClass(SEAT_CLASS)).thenReturn(true);
    when(seatMock.getSeatNumber()).thenReturn(SEAT_NUMBER);
    when(seatMock.isExitRow()).thenReturn(false);
  }
}
