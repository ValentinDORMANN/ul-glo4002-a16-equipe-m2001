package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignationRepository;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy.SeatAssignationStrategy;

public class SeatAssignationServiceTest {

  private static final String RANDOM_MODE = "RANDOM";  
  private static final String PASSENGER_HASH = "HASH001";
  private static final String FLIGHT_NUMBER = "A3832";
  private static final Date FLIGHT_DATE = new Date();
  
  private Passenger passengerMock;
  private SeatAssignation seatAssignationMock;
  private SeatAssignationStrategy seatAssignationStrategyMock;
  private SeatAssignationRepository SeatAssignationRepositoryMock;
  
  private SeatAssignationService seatAssignationService;

  @Before
  public void initiateTest() {
    passengerMock = mock(Passenger.class);
    seatAssignationMock = mock(SeatAssignation.class);
    seatAssignationStrategyMock = mock(SeatAssignationStrategy.class);
    SeatAssignationRepositoryMock = mock(SeatAssignationRepository.class);
    
    //when(seatAssignationStrategyMock.assignSeatNumber(any(), any(), any(boolean.class))).thenReturn(SEAT_NUMBER);
    givenPassenger();
    
    seatAssignationService = new SeatAssignationService(seatAssignationMock, SeatAssignationRepositoryMock, seatAssignationStrategyMock);
  }

  @Test
  public void givenPassengerWithNoSeatAssignedWhenAssignSeatToPassengerThenVerifyChooseSeatNumber() {
    seatAssignationService.assignSeatToPassenger(passengerMock, RANDOM_MODE);

    verify(seatAssignationStrategyMock, times(1)).assignSeatNumber(any(), any(), any(boolean.class));
  }

  @Test
  public void givenPassengerWithNoSeatAssignedWhenAssignSeatToPassengerThenVerifyAssignationCreated() {
    seatAssignationService.assignSeatToPassenger(passengerMock, RANDOM_MODE);

    verify(seatAssignationMock, times(1)).createAssignation(any(String.class), any(String.class), any(Integer.class));
  }

  @Test
  public void givenPassengerWithNoSeatAssignedWhenAssignSeatToPassengerThenVerifyPersistAssignation() {
    seatAssignationService.assignSeatToPassenger(passengerMock, RANDOM_MODE);

    verify(SeatAssignationRepositoryMock, times(1)).persistSeatAssignation(seatAssignationMock);
  }

  @Test
  public void givenPassengerWhenAssignSeatToPassengerThenReturnSeatAssignationWithPassengerHash() {
    seatAssignationService = new SeatAssignationService(new SeatAssignation(), SeatAssignationRepositoryMock, seatAssignationStrategyMock);

    SeatAssignation seatAssignation = seatAssignationService.assignSeatToPassenger(passengerMock, RANDOM_MODE);

    assertEquals(PASSENGER_HASH, seatAssignation.getPassengerHash());
  }
  
  private void givenPassenger() { 
    when(passengerMock.getFlightNumber()).thenReturn(FLIGHT_NUMBER);
    when(passengerMock.getFlightDate()).thenReturn(FLIGHT_DATE);
    when(passengerMock.getPassengerHash()).thenReturn(PASSENGER_HASH);
  }
}
