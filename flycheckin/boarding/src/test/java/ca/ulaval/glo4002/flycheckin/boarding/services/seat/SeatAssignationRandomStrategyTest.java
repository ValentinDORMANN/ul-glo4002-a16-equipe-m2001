package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

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
  private static final boolean IS_ADULT = false;

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
  public void givenEmptyAvailableSeatListWhenAssignSeatNumberThenReturnException() {
    randomSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, IS_ADULT);
  }

  /*  @Test
  public void givenListWithOnlyOneSeatWhenAssignSeatNumberWithSameSeatClassThenReturnSeatNumber() {
    givenSeatHasPassengerSeatClass();
    availableSeats.add(seatMock);
  
    String seatNumber = randomSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, IS_ADULT);
  
    assertEquals(SEAT_NUMBER, seatNumber);
  }*/

  @Test(expected = NoSeatAvailableException.class)
  public void givenListWithOnlyOneSeatWhenAssignSeatNumberWithAnotherSeatClassThenReturnException() {
    givenSeatHasPassengerSeatClass();
    availableSeats.add(seatMock);

    randomSeatAssignation.assignSeatNumber(availableSeats, ANOTHER_SEAT_CLASS, IS_ADULT);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void test1() {
    givenExitRowSeatHasPassengerSeatClass();
    availableSeats.add(seatMock);

    randomSeatAssignation.assignSeatNumber(availableSeats, SEAT_CLASS, IS_JUNIOR);
  }

  private void givenExitRowSeatHasPassengerSeatClass() {
    when(seatMock.getSeatClass()).thenReturn(SEAT_CLASS);
    when(seatMock.getSeatNumber()).thenReturn(SEAT_NUMBER);
    when(seatMock.isExitRow()).thenReturn(true);
  }

  private void givenSeatHasPassengerSeatClass() {
    when(seatMock.getSeatClass()).thenReturn(SEAT_CLASS);
    when(seatMock.getSeatNumber()).thenReturn(SEAT_NUMBER);
  }
}
