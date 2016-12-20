package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignation;

public class SeatAssignationHibernateTest {

  private static final boolean IS_SAME = true;
  private static final String PASSENGER_HASH = "HASH001";
  private static final String SEAT_NUMBER = "12-A";
  private static final int ASSIGNATION_NUMBER = 15;
  private static final int ASSIGNATION_NUMBER2 = 16;
  
  private SeatAssignation seatAssignationMock;
  private EntityManagerProvider entityManagerProviderMock;
  private EntityManager entityManagerMock;
  
  private SeatAssignationHibernate seatAssignationHibernate;

  @Before
  public void initiateTest() {
    seatAssignationMock = mock(SeatAssignation.class);
    entityManagerProviderMock = mock(EntityManagerProvider.class);
    entityManagerMock = mock(EntityManager.class);
    
    when(entityManagerProviderMock.getEntityManager()).thenReturn(entityManagerMock);
    when(seatAssignationMock.isAssociateToThisHash(PASSENGER_HASH)).thenReturn(IS_SAME);
    when(seatAssignationMock.getPassengerHash()).thenReturn(PASSENGER_HASH);
    when(seatAssignationMock.getSeatNumber()).thenReturn(SEAT_NUMBER);
    when(seatAssignationMock.getAssignationNumber()).thenReturn(ASSIGNATION_NUMBER);
    seatAssignationHibernate = new SeatAssignationHibernate(entityManagerMock);
  }

  @Test
  public void givenPassengerWithSeatAssignedWhenGetPassengerSeatNumberThenReturnSeatNumber() {
    seatAssignationHibernate.persistSeatAssignation(seatAssignationMock);

    String seatNumber = seatAssignationHibernate.getPassengerHashSeatNumber(PASSENGER_HASH);

    assertEquals(SEAT_NUMBER, seatNumber);
  }

  @Test(expected = AssignationNumberUsedException.class)
  public void givenAssignationNumberUsedWhenPersistSeatAssignationThenThrowException() {
    seatAssignationHibernate.persistSeatAssignation(seatAssignationMock);

    seatAssignationHibernate.persistSeatAssignation(seatAssignationMock);
  }

  @Test(expected = SeatAlreadyAssignedException.class)
  public void givenPassengerWithSeatAssignedWhenPersistWithAnotherAssignationNumberThenThrowException() {
    seatAssignationHibernate.persistSeatAssignation(seatAssignationMock);

    when(seatAssignationMock.getAssignationNumber()).thenReturn(ASSIGNATION_NUMBER2);
    seatAssignationHibernate.persistSeatAssignation(seatAssignationMock);
  }
}
