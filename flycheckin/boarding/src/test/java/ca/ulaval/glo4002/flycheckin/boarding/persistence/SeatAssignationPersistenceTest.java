package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignation;

public class SeatAssignationPersistenceTest {

  private static final boolean IS_SAME = true;
  private static final String PASSENGER_HASH = "HASH001";
  private static final String SEAT_NUMBER = "12-A";
  private static final int ASSIGNATION_NUMBER = 15;
  private static final int ASSIGNATION_NUMBER2 = 16;
  private SeatAssignation mockSeatAssignation;
  private SeatAssignationPersistence seatAssignationPersistence;

  @Before
  public void initiateTest() {
    mockSeatAssignation = mock(SeatAssignation.class);
    when(mockSeatAssignation.isAssociateToThisHash(PASSENGER_HASH)).thenReturn(IS_SAME);
    when(mockSeatAssignation.getPassengerHash()).thenReturn(PASSENGER_HASH);
    when(mockSeatAssignation.getSeatNumber()).thenReturn(SEAT_NUMBER);
    when(mockSeatAssignation.getAssignationNumber()).thenReturn(ASSIGNATION_NUMBER);
    seatAssignationPersistence = new SeatAssignationPersistence();
  }

  @Test
  public void givenPassengerWithSeatAssignedWhenGetPassengerSeatNumberThenReturnSeatNumber() {
    seatAssignationPersistence.persistSeatAssignation(mockSeatAssignation);

    String seatNumber = seatAssignationPersistence.getPassengerHashSeatNumber(PASSENGER_HASH);

    assertEquals(SEAT_NUMBER, seatNumber);
  }

  @Test(expected = AssignationNumberUsedException.class)
  public void givenAssignationNumberUsedWhenPersistSeatAssignationThenThrowException() {
    seatAssignationPersistence.persistSeatAssignation(mockSeatAssignation);

    seatAssignationPersistence.persistSeatAssignation(mockSeatAssignation);
  }

  @Test(expected = SeatAlreadyAssignedException.class)
  public void givenPassengerWithSeatAssignedWhenPersistWithAnotherAssignationNumberThenThrowException() {
    seatAssignationPersistence.persistSeatAssignation(mockSeatAssignation);

    when(mockSeatAssignation.getAssignationNumber()).thenReturn(ASSIGNATION_NUMBER2);
    seatAssignationPersistence.persistSeatAssignation(mockSeatAssignation);
  }

  @After
  public void finishTest() {
    seatAssignationPersistence.clearSeatAssignationMap();
  }
}
