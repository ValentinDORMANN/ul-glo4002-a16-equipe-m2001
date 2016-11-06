package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignation;
import ca.ulaval.glo4002.flycheckin.boarding.domain.SeatAssignationRepository;

public class ServiceSeatAssignationTest {

  private static final String RANDOM_MODE = "RANDOM";
  private static final String PASSENGER_HASH_NO_SEAT = "HASH001";
  private static final String PASSENGER_HASH_WITH_SEAT = "HASH002";
  private static final String SEAT_NUMBER = "12-C";
  private static final String FLIGHT_NUMBER = "A3832";
  private static final Date FLIGHT_DATE = new Date();
  private Passenger mockPassenger;
  private SeatAssignation mockSeatAssignation;
  private SeatAssignationRepository mockSeatAssignationRepository;
  private ServiceSeatAssignation serviceSeatAssignation;

  @Before
  public void initiateTest() {
    mockPassenger = mock(Passenger.class);
    mockSeatAssignation = mock(SeatAssignation.class);
    mockSeatAssignationRepository = mock(SeatAssignationRepository.class);
    when(mockPassenger.getFlightNumber()).thenReturn("FLIGHT_NUMBER");
    when(mockPassenger.getFlightDate()).thenReturn(FLIGHT_DATE);
    serviceSeatAssignation = new ServiceSeatAssignation(mockSeatAssignation, mockSeatAssignationRepository);
  }

  /*  @Test
  public void givenPassengerWithNoSeatAssignedWhenAssignSeatToPassengerThenVerifyAssignationWithGoodMode() {
    when(mockPassenger.getPassengerHash()).thenReturn(PASSENGER_HASH_NO_SEAT);
  
    serviceSeatAssignation.assignSeatToPassenger(mockPassenger, RANDOM_MODE);
  
    verify(mockSeatAssignation, times(1)).assignSeatNumberToPassenger(PASSENGER_HASH_NO_SEAT, RANDOM_MODE);
  }*/

  /*  @Test(expected = PassengerAlreadySeatAssigned.class)
  public void givenPassengerWithSeatAssignWhenAssignSeatToPassengerThenReturnException() {
    when(mockPassenger.getPassengerHash()).thenReturn(PASSENGER_HASH_WITH_SEAT);
    serviceSeatAssignation.assignSeatToPassenger(mockPassenger, RANDOM_MODE);
  
    serviceSeatAssignation.assignSeatToPassenger(mockPassenger, RANDOM_MODE);
  }*/
}
