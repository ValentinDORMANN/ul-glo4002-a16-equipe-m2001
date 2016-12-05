package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationLandScapeStrategyTest {

  private static final String PASSENGER_SEAT_CLASS = "business";
  private static final String ANOTHER_SEAT_CLASS = "economic";
  private static final String FIRST_SEAT_NUMBER = "1A";
  private static final String SECOND_SEAT_NUMBER = "1D";
  private static final boolean IS_JUNIOR = true;

  private Seat firstSeatMock, secondSeatMock;
  private List<Seat> availableSeats;
  private SeatAssignationLandScapeStrategy bestViewSeatAssignation;

  @Before
  public void initiateTest() {
    availableSeats = new ArrayList<Seat>();
    bestViewSeatAssignation = new SeatAssignationLandScapeStrategy();
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenNoSeatAvailableWhenAssignSeatToPassengerThenReturnException() {
    bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenOnlyOneSeatAvailableWithAnotherSeatClassWhenAssignSeatToPassengerThenReturnException() {
    givenAnySeatAvailableWith(ANOTHER_SEAT_CLASS);

    bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);
  }

  @Test
  public void givenAnySeatAvailableWithPassengerSeatClassWhenAssignSeatToPassengerThenVerifySeatNumberIsAssigned() {
    givenAnySeatAvailableWith(PASSENGER_SEAT_CLASS);

    String seatNumber = bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);

    assertEquals(FIRST_SEAT_NUMBER, seatNumber);
  }

  @Test
  public void givenWorstViewSeatAndSeatWithWindowViewAvailableWhenAssignSeatToPassengerThenAssignWindowViewSeat() {
    givenFirstSeatWithoutClearViewAndWindowView(PASSENGER_SEAT_CLASS);
    givenSecondSeatWithOnlyWindowView(PASSENGER_SEAT_CLASS);

    String assignedSeatNumber = bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);

    assertEquals(SECOND_SEAT_NUMBER, assignedSeatNumber);
  }

  @Test
  public void givenWorstViewSeatAndSeatWithClearViewAvailableWhenAssignSeatToPassengerThenAssignClearViewSeat() {
    givenFirstSeatWithoutClearViewAndWindowView(PASSENGER_SEAT_CLASS);
    givenSecondSeatWithOnlyClearView(PASSENGER_SEAT_CLASS);

    String assignedSeatNumber = bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);

    assertEquals(SECOND_SEAT_NUMBER, assignedSeatNumber);
  }

  @Test
  public void givenClearViewSeatAndWindowViewSeatAvailableWhenAssignSeatToPassengerThenAssignWindowViewSeat() {
    givenFirstSeatWithOnlyClearView(PASSENGER_SEAT_CLASS);
    givenSecondSeatWithOnlyWindowView(PASSENGER_SEAT_CLASS);

    String assignedSeatNumber = bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);

    assertEquals(SECOND_SEAT_NUMBER, assignedSeatNumber);
  }

  @Test
  public void givenBestViewSeatAndSeatWithOnlyWindowViewAvailableWhenAssignSeatToPassengerThenAssignBestViewSeat() {
    givenFirstSeatWithOnlyClearView(PASSENGER_SEAT_CLASS);
    when(firstSeatMock.isNearWindow()).thenReturn(true);
    givenSecondSeatWithOnlyWindowView(PASSENGER_SEAT_CLASS);

    String assignedSeatNumber = bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);

    assertEquals(FIRST_SEAT_NUMBER, assignedSeatNumber);
  }

  @Test
  public void givenTwoSeatWithBothHasBestViewWhenAssignSeatToPassengerThenAssignTheCheapestSeat() {
    givenFirstSeatWithOnlyClearView(PASSENGER_SEAT_CLASS);
    when(firstSeatMock.isNearWindow()).thenReturn(true);
    givenSecondSeatWithOnlyWindowView(PASSENGER_SEAT_CLASS);
    when(secondSeatMock.isClearView()).thenReturn(true);
    setSecondSeatCheaperThanFirstSeat();

    String assignedSeatNumber = bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, !IS_JUNIOR);

    assertEquals(SECOND_SEAT_NUMBER, assignedSeatNumber);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenOnlyExitRowSeatAvailableWithPassengerSeatClassWhenAssignSeatToJuniorPassengerThenReturnException() {
    givenAnyExitRowSeatAvailableWith(PASSENGER_SEAT_CLASS);

    bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, IS_JUNIOR);
  }

  @Test
  public void givenAnySeatNotInExitRowAndBestViewSeatInExitRowWhenAssignSeatToJuniorPassengerThenAssignSeatNotInExitRow() {
    givenFirstSeatWithoutClearViewAndWindowView(PASSENGER_SEAT_CLASS);
    givenSecondSeatWithOnlyWindowView(PASSENGER_SEAT_CLASS);
    when(secondSeatMock.isClearView()).thenReturn(true);
    when(secondSeatMock.isExitRow()).thenReturn(true);

    String assignedSeatNumber = bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS, IS_JUNIOR);

    assertEquals(FIRST_SEAT_NUMBER, assignedSeatNumber);
  }

  private void givenAnySeatAvailableWith(String seatClass) {
    givenFirstSeat(seatClass);
    availableSeats.add(firstSeatMock);
  }

  private void givenAnyExitRowSeatAvailableWith(String seatClass) {
    givenFirstSeat(seatClass);
    when(firstSeatMock.isExitRow()).thenReturn(true);
    availableSeats.add(firstSeatMock);
  }

  private void givenFirstSeatWithoutClearViewAndWindowView(String seatClass) {
    givenFirstSeat(seatClass);
    availableSeats.add(firstSeatMock);
  }

  private void givenSecondSeatWithOnlyWindowView(String seatClass) {
    givenSecondSeat(seatClass);
    when(secondSeatMock.isNearWindow()).thenReturn(true);
    availableSeats.add(secondSeatMock);
  }

  private void givenFirstSeatWithOnlyClearView(String seatClass) {
    givenFirstSeat(seatClass);
    when(firstSeatMock.isClearView()).thenReturn(true);
    availableSeats.add(firstSeatMock);
  }

  private void givenSecondSeatWithOnlyClearView(String seatClass) {
    givenSecondSeat(seatClass);
    when(secondSeatMock.isClearView()).thenReturn(true);
    availableSeats.add(secondSeatMock);
  }

  private void givenFirstSeat(String seatClass) {
    firstSeatMock = mock(Seat.class);
    when(firstSeatMock.getSeatNumber()).thenReturn(FIRST_SEAT_NUMBER);
    when(firstSeatMock.hasClass(seatClass)).thenReturn(true);
  }

  private void givenSecondSeat(String seatClass) {
    secondSeatMock = mock(Seat.class);
    when(secondSeatMock.getSeatNumber()).thenReturn(SECOND_SEAT_NUMBER);
    when(secondSeatMock.hasClass(seatClass)).thenReturn(true);
  }

  private void setSecondSeatCheaperThanFirstSeat() {
    when(secondSeatMock.isCheaperThan(firstSeatMock)).thenReturn(true);
    when(firstSeatMock.isCheaperThan(secondSeatMock)).thenReturn(false);
  }
}
