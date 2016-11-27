package ca.ulaval.glo4002.flycheckin.boarding.services.passenger;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.client.ReservationHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.PassengerFactory;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.PassengerLuggagePersistence;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PassengerDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class PassengerServiceTest {

  private static final String HASH = "HASH";
  private static final String FLIGHT_NUMBER = "NUMBER";
  private static final Date FLIGHT_DATE = new Date();
  private static final String SEAT_CLASS = "SEAT_CLASS";
  private static final boolean IS_VIP = true;

  private ReservationHttpClient reservationHttpClientMock;
  private PassengerLuggagePersistence inMemoryPassengerMock;
  private Passenger passengerMock;
  private ReservationDto reservationDtoMock;
  private PassengerDto passengerDtoMock;
  private PassengerFactory passengerFactoryMock;

  private PassengerService passengerService;

  @Before
  public void initiateTest() {
    reservationHttpClientMock = mock(ReservationHttpClient.class);
    inMemoryPassengerMock = mock(PassengerLuggagePersistence.class);
    passengerMock = mock(Passenger.class);
    reservationDtoMock = mock(ReservationDto.class);
    passengerDtoMock = mock(PassengerDto.class);
    passengerFactoryMock = mock(PassengerFactory.class);
    passengerService = new PassengerService(reservationHttpClientMock, inMemoryPassengerMock, passengerFactoryMock);
    passengerDtoMock.passenger_hash = HASH;
    passengerDtoMock.seat_class = SEAT_CLASS;
    passengerDtoMock.isVip = IS_VIP;
    PassengerDto[] passengersDto = { passengerDtoMock };
    reservationDtoMock.passengers = passengersDto;
    reservationDtoMock.flight_date = FLIGHT_DATE;
    reservationDtoMock.flight_number = FLIGHT_NUMBER;
  }

  @Test
  public void givenPassengerHashWithNoLuggageWhenGetPassengerByHashThenVerifyPassengerIsGetInReservation() {
    willReturn(reservationDtoMock).given(reservationHttpClientMock).getReservationDtoFromReservation(HASH);

    passengerService.getPassengerByHash(HASH);

    verify(reservationHttpClientMock, times(1)).getReservationDtoFromReservation(HASH);
  }

  @Test
  public void givenValidPassengerHashWhenGetPassengerByHashThenVerifyPassengerIsCreated() {
    willReturn(reservationDtoMock).given(reservationHttpClientMock).getReservationDtoFromReservation(HASH);

    passengerService.getPassengerByHash(HASH);

    verify(passengerFactoryMock, times(1)).createPassenger(any(String.class), any(Date.class), any(String.class),
        any(String.class), any(boolean.class));
  }

  @Test
  public void givenPassengerHashWithNoLuggageWhenGetPassengerByHashThenGetGoogPassengerFromReservation() {
    willReturn(reservationDtoMock).given(reservationHttpClientMock).getReservationDtoFromReservation(HASH);
    willReturn(passengerMock).given(passengerFactoryMock).createPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, SEAT_CLASS,
        IS_VIP);
    willReturn(HASH).given(passengerMock).getPassengerHash();

    Passenger passenger = passengerService.getPassengerByHash(HASH);

    assertEquals(HASH, passenger.getPassengerHash());
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenStoredReservationWhenGetPassengerNotInThisReservationThenThrowException() {
    willThrow(NotFoundPassengerException.class).given(reservationHttpClientMock).getReservationDtoFromReservation(HASH);

    passengerService.getPassengerByHash(HASH);
  }
}
