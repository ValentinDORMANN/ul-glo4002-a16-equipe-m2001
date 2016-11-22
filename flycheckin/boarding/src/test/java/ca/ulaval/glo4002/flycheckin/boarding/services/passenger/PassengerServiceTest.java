package ca.ulaval.glo4002.flycheckin.boarding.services.passenger;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.client.ReservationHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.PassengerFactory;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.InMemoryPassenger;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PassengerDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class PassengerServiceTest {

  private static final String HASH = "HASH";
  private static final String FLIGHT_NUMBER = "NUMBER";
  private static final Date FLIGHT_DATE = new Date();
  private static final String SEAT_CLASS = "SEAT_CLASS";
  private ReservationHttpClient mockReservationHttpClient;
  private InMemoryPassenger mockInMemoryPassenger;
  private Passenger mockPassenger;
  private ReservationDto mockReservationDto;
  private PassengerDto mockPassengerDto;
  private PassengerService passengerService;
  private PassengerFactory mockPassengerFactory;

  @Before
  public void initiateTest() {
    mockReservationHttpClient = mock(ReservationHttpClient.class);
    mockInMemoryPassenger = mock(InMemoryPassenger.class);
    mockPassenger = mock(Passenger.class);
    mockReservationDto = mock(ReservationDto.class);
    mockPassengerDto = mock(PassengerDto.class);
    mockPassengerFactory = mock(PassengerFactory.class);
    passengerService = new PassengerService(mockReservationHttpClient, mockInMemoryPassenger, mockPassengerFactory);
    mockPassengerDto.passenger_hash = HASH;
    mockPassengerDto.seat_class = SEAT_CLASS;
    PassengerDto[] passengersDto = { mockPassengerDto };
    mockReservationDto.passengers = passengersDto;
    mockReservationDto.flight_date = FLIGHT_DATE;
    mockReservationDto.flight_number = FLIGHT_NUMBER;
  }

  @Test
  public void givenPassengerResearchInMemoryWhenGetPassengerByHashThenDoNothing() {
    willReturn(mockPassenger).given(mockInMemoryPassenger).getPassengerByHash(HASH);

    passengerService.getPassengerByHash(HASH);
  }

  @Test
  public void givenPassengerResearchNotInMemoryWhenGetPassengerByHashThenCompareHash() {
    willThrow(NotFoundPassengerException.class).given(mockInMemoryPassenger).getPassengerByHash(HASH);
    willReturn(mockReservationDto).given(mockReservationHttpClient).getReservationDtoFromReservation(HASH);

    Passenger passenger = passengerService.getPassengerByHash(HASH);

    assertEquals(HASH, passenger.getPassengerHash());
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenStoredReservationWhenGetPassengerNotInThisReservationThenThrowException() {
    willThrow(NotFoundPassengerException.class).given(mockInMemoryPassenger).getPassengerByHash(HASH);
    willThrow(NotFoundPassengerException.class).given(mockReservationHttpClient).getReservationDtoFromReservation(HASH);

    passengerService.getPassengerByHash(HASH);
  }
}
