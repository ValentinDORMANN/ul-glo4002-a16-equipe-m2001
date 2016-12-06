package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignationRepository;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy.SeatAssignationStrategy;
import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy.SeatAssignationStrategyFactory;

public class SeatAssignationServiceTest {

  private static final String RANDOM_MODE = "RANDOM";
  private static final String PASSENGER_HASH = "HASH001";
  private static final String FLIGHT_NUMBER = "A3832";
  private static final Date FLIGHT_DATE = new Date();

  private Passenger passengerMock;
  private SeatAssignation seatAssignationMock;
  private SeatAssignationStrategyFactory seatAssignationStrategyFactoryMock;
  private SeatAssignationStrategy seatAssignationStrategyMock;
  private SeatAssignationRepository SeatAssignationRepositoryMock;

  private SeatAssignationService seatAssignationService;

  @Before
  public void initiateTest() {
    passengerMock = mock(Passenger.class);
    seatAssignationMock = mock(SeatAssignation.class);
    seatAssignationStrategyFactoryMock = mock(SeatAssignationStrategyFactory.class);
    seatAssignationStrategyMock = mock(SeatAssignationStrategy.class);
    SeatAssignationRepositoryMock = mock(SeatAssignationRepository.class);

    givenPassenger();

    seatAssignationService = new SeatAssignationService(seatAssignationMock, SeatAssignationRepositoryMock,
        seatAssignationStrategyFactoryMock);
  }

  @Test
  public void test() {
    given(seatAssignationStrategyFactoryMock.createSeatAssignationStrategy(RANDOM_MODE)).willReturn(seatAssignationStrategyMock);

    seatAssignationService.assignSeatToPassenger(passengerMock, RANDOM_MODE);

    verify(seatAssignationStrategyFactoryMock, times(1)).createSeatAssignationStrategy(RANDOM_MODE);
  }

  @Test
  public void givenPassengerWithNoSeatAssignedWhenAssignSeatToPassengerThenVerifyChooseSeatNumber() {
    given(seatAssignationStrategyFactoryMock.createSeatAssignationStrategy(RANDOM_MODE)).willReturn(seatAssignationStrategyMock);

    seatAssignationService.assignSeatToPassenger(passengerMock, RANDOM_MODE);

    verify(seatAssignationStrategyMock, times(1)).assignSeatNumber(any(), any(), any(boolean.class));
  }

  @Test
  public void givenPassengerWithNoSeatAssignedWhenAssignSeatToPassengerThenVerifyAssignationCreated() {
    given(seatAssignationStrategyFactoryMock.createSeatAssignationStrategy(RANDOM_MODE)).willReturn(seatAssignationStrategyMock);

    seatAssignationService.assignSeatToPassenger(passengerMock, RANDOM_MODE);

    verify(seatAssignationMock, times(1)).createAssignation(any(String.class), any(String.class), any(Integer.class));
  }

  @Test
  public void givenPassengerWithNoSeatAssignedWhenAssignSeatToPassengerThenVerifyPersistAssignation() {
    given(seatAssignationStrategyFactoryMock.createSeatAssignationStrategy(RANDOM_MODE)).willReturn(seatAssignationStrategyMock);

    seatAssignationService.assignSeatToPassenger(passengerMock, RANDOM_MODE);

    verify(SeatAssignationRepositoryMock, times(1)).persistSeatAssignation(seatAssignationMock);
  }

  @Test
  public void givenPassengerWhenAssignSeatToPassengerThenReturnSeatAssignationWithPassengerHash() {
    given(seatAssignationStrategyFactoryMock.createSeatAssignationStrategy(RANDOM_MODE)).willReturn(seatAssignationStrategyMock);
    seatAssignationService = new SeatAssignationService(new SeatAssignation(), SeatAssignationRepositoryMock,
        seatAssignationStrategyFactoryMock);

    SeatAssignation seatAssignation = seatAssignationService.assignSeatToPassenger(passengerMock, RANDOM_MODE);

    assertEquals(PASSENGER_HASH, seatAssignation.getPassengerHash());
  }

  private void givenPassenger() {
    when(passengerMock.getFlightNumber()).thenReturn(FLIGHT_NUMBER);
    when(passengerMock.getFlightDate()).thenReturn(FLIGHT_DATE);
    when(passengerMock.getPassengerHash()).thenReturn(PASSENGER_HASH);
  }
}
