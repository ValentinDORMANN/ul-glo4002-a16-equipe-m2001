package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignationRepository;

public class SeatAssignationServiceTest {

  private static final String RANDOM_MODE = "RANDOM";
  private static final String PASSENGER_HASH = "HASH001";

  private static final String SEAT_NUMBER = "12-C";
  private static final String FLIGHT_NUMBER = "A3832";
  private static final Date FLIGHT_DATE = new Date();
  private Passenger mockPassenger;
  private SeatAssignation mockSeatAssignation;
  private SeatAssignationStrategy mockSeatAssignationStrategy;
  private SeatAssignationRepository mockSeatAssignationRepository;
  private SeatAssignationService seatAssignationService;

  @Before
  public void initiateTest() {
    mockPassenger = mock(Passenger.class);
    mockSeatAssignation = mock(SeatAssignation.class);
    mockSeatAssignationStrategy = mock(SeatAssignationStrategy.class);
    mockSeatAssignationRepository = mock(SeatAssignationRepository.class);
    when(mockPassenger.getFlightNumber()).thenReturn(FLIGHT_NUMBER);
    when(mockPassenger.getFlightDate()).thenReturn(FLIGHT_DATE);
    when(mockPassenger.getPassengerHash()).thenReturn(PASSENGER_HASH);
    when(mockSeatAssignationStrategy.assignSeatNumber(any(), any())).thenReturn(SEAT_NUMBER);
    seatAssignationService = new SeatAssignationService(mockSeatAssignation, mockSeatAssignationRepository,
        mockSeatAssignationStrategy);
  }

  @Test
  public void givenPassengerWithNoSeatAssignedWhenAssignSeatToPassengerThenVerifyChooseSeatNumber() {
    seatAssignationService.assignSeatToPassenger(mockPassenger, RANDOM_MODE);

    verify(mockSeatAssignationStrategy, times(1)).assignSeatNumber(any(), any());
  }

  @Test
  public void givenPassengerWithNoSeatAssignedWhenAssignSeatToPassengerThenVerifyAssignationCreated() {
    seatAssignationService.assignSeatToPassenger(mockPassenger, RANDOM_MODE);

    verify(mockSeatAssignation, times(1)).createAssignation(any(String.class), any(String.class), any(Integer.class));
  }

  @Test
  public void givenPassengerWithNoSeatAssignedWhenAssignSeatToPassengerThenVerifyPersistAssignation() {
    seatAssignationService.assignSeatToPassenger(mockPassenger, RANDOM_MODE);

    verify(mockSeatAssignationRepository, times(1)).persistSeatAssignation(mockSeatAssignation);
  }

  @Test
  public void givenPassengerWhenAssignSeatToPassengerThenReturnSeatAssignationWithPassengerHash() {
    seatAssignationService = new SeatAssignationService(new SeatAssignation(), mockSeatAssignationRepository,
        mockSeatAssignationStrategy);

    SeatAssignation seatAssignation = seatAssignationService.assignSeatToPassenger(mockPassenger, RANDOM_MODE);

    assertEquals(PASSENGER_HASH, seatAssignation.getPassengerHash());
  }
}
