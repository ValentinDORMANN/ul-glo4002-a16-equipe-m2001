package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.exception.AssignationNumberUsedException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.SeatAlreadyAssignedException;

public class InMemorySeatAssignationTest {

  private static final String PASSENGER_HASH = "HASH001";
  private static final String SEAT_NUMBER = "12-A";
  private static final int ASSIGNATION_NUMBER = 15;
  private static final int ASSIGNATION_NUMBER2 = 16;
  private SeatAssignation mockSeatAssignation;
  private InMemorySeatAssignation inMemorySeatAssignation;

  @Before
  public void initiateTest() {
    mockSeatAssignation = mock(SeatAssignation.class);
    when(mockSeatAssignation.getPassengerHash()).thenReturn(PASSENGER_HASH);
    when(mockSeatAssignation.getSeatNumber()).thenReturn(SEAT_NUMBER);
    when(mockSeatAssignation.getAssignationNumber()).thenReturn(ASSIGNATION_NUMBER);
    inMemorySeatAssignation = new InMemorySeatAssignation();
  }

  @Test
  public void givenPassengerWithSeatAssignedWhenGetPassengerSeatNumberThenReturnSeatNumber() {
    inMemorySeatAssignation.persistSeatAssignation(mockSeatAssignation);

    String seatNumber = inMemorySeatAssignation.getPassengerHashSeatNumber(PASSENGER_HASH);

    assertEquals(SEAT_NUMBER, seatNumber);
  }

  @Test(expected = AssignationNumberUsedException.class)
  public void givenAssignationNumberUsedWhenPersistSeatAssignationThenThrowException() {
    inMemorySeatAssignation.persistSeatAssignation(mockSeatAssignation);

    inMemorySeatAssignation.persistSeatAssignation(mockSeatAssignation);
  }

  @Test(expected = SeatAlreadyAssignedException.class)
  public void givenPassengerWithSeatAssignedWhenPersistWithAnotherAssignationNumberThenThrowException() {
    inMemorySeatAssignation.persistSeatAssignation(mockSeatAssignation);

    when(mockSeatAssignation.getAssignationNumber()).thenReturn(ASSIGNATION_NUMBER2);
    inMemorySeatAssignation.persistSeatAssignation(mockSeatAssignation);
  }

  @After
  public void finishTest() {
    inMemorySeatAssignation.clearSeatAssignationMap();
  }
}
