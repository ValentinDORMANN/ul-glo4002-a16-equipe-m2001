package ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationLegroomStrategyTest {
  private static final String SMALL_SEAT_NUMBER = "16-B";
  private static final String LARGE_SEAT_NUMBER = "7-A";
  private static final boolean IS_JUNIOR = true;

  private static final String PASSENGER_SEAT_CLASS = "business";
  private static final String ANOTHER_SEAT_CLASS = "economy";

  private Seat smallSeatMock, largeSeatMock;
  private List<Seat> availableSeats;
  private SeatAssignationLegroomStrategy legroomSeatAssignation;

  @Before
  public void initiateTest() {
    availableSeats = new ArrayList<Seat>();
    legroomSeatAssignation = new SeatAssignationLegroomStrategy();
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenEmptyAvailableSeatListWhenAssignSeatToPassengerThenReturnException() {
    legroomSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenOnlyOneSeatAvailableWhenAssignSeatToPassengerWithAnotherSeatClassThenReturnException() {
    givenOnlySmallLegroomSeatAvailable(ANOTHER_SEAT_CLASS);

    legroomSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);
  }

  @Test
  public void givenOnlyOneSeatAvailableWhenAssignSeatToPassengerWithSameSeatClassThenReturnSeatNumber() {
    givenOnlySmallLegroomSeatAvailable(PASSENGER_SEAT_CLASS);

    String seatNumber = legroomSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);

    assertEquals(SMALL_SEAT_NUMBER, seatNumber);
  }

  @Test
  public void givenAvailableSeatsListWithDifferentLegroomSizeWhenAssignSeatThenReturnLargestLegroomSeat() {
    givenSmallSeatLegroom(PASSENGER_SEAT_CLASS);
    givenLargeSeatLegroom(PASSENGER_SEAT_CLASS);

    String seatNumber = legroomSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);

    assertEquals(LARGE_SEAT_NUMBER, seatNumber);
  }

  @Test
  public void givenSeatWithSameLegroomValueWhenAssignSeatToPassengerThenVerifyTheCheapestSeatIsAssigned() {
    givenCheapSeatAndExpensiveSeatWithSameLegroomValue();
    when(smallSeatMock.isCheaperThan(largeSeatMock)).thenReturn(true);

    String seatNumber = legroomSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);

    assertEquals(SMALL_SEAT_NUMBER, seatNumber);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void test1() {
    givenOnlySmallLegroomSeatAvailable(PASSENGER_SEAT_CLASS);
    when(smallSeatMock.isExitRow()).thenReturn(true);

    legroomSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, IS_JUNIOR);
  }

  @Test
  public void test2() {
    givenSmallSeatLegroom(PASSENGER_SEAT_CLASS);
    givenLargeSeatLegroom(PASSENGER_SEAT_CLASS);
    when(smallSeatMock.isExitRow()).thenReturn(false);
    when(largeSeatMock.isExitRow()).thenReturn(true);

    String seatNumber = legroomSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, IS_JUNIOR);

    assertEquals(SMALL_SEAT_NUMBER, seatNumber);
  }

  private void givenCheapSeatAndExpensiveSeatWithSameLegroomValue() {
    givenLargeSeatLegroom(PASSENGER_SEAT_CLASS);
    when(largeSeatMock.isLegroomGreaterThan(smallSeatMock)).thenReturn(false);
    when(largeSeatMock.hasSameLegroomWith(smallSeatMock)).thenReturn(true);
    givenOnlySmallLegroomSeatAvailable(PASSENGER_SEAT_CLASS);
    when(smallSeatMock.isLegroomGreaterThan(largeSeatMock)).thenReturn(false);
    when(smallSeatMock.hasSameLegroomWith(largeSeatMock)).thenReturn(true);
  }

  private void givenOnlySmallLegroomSeatAvailable(String seatClass) {
    smallSeatMock = mock(Seat.class);
    when(smallSeatMock.getSeatNumber()).thenReturn(SMALL_SEAT_NUMBER);
    when(smallSeatMock.hasClass(seatClass)).thenReturn(true);
    availableSeats.add(smallSeatMock);
  }

  private void givenSmallSeatLegroom(String seatClass) {
    givenOnlySmallLegroomSeatAvailable(seatClass);
    when(smallSeatMock.isLegroomGreaterThan(largeSeatMock)).thenReturn(false);
  }

  private void givenLargeSeatLegroom(String seatClass) {
    largeSeatMock = mock(Seat.class);
    when(largeSeatMock.getSeatNumber()).thenReturn(LARGE_SEAT_NUMBER);
    when(largeSeatMock.hasClass(seatClass)).thenReturn(true);
    when(largeSeatMock.isLegroomGreaterThan(any(Seat.class))).thenReturn(true);
    availableSeats.add(largeSeatMock);
  }
}
