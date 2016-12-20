package ca.ulaval.glo4002.flycheckin.boarding.services.passenger;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.client.CheckinHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.client.NotCheckedinException;
import ca.ulaval.glo4002.flycheckin.boarding.client.ReservationHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.PassengerFactory;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.PassengerLuggagePersistence;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PassengerDto;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.ReservationDto;

public class PassengerServiceTest {

  private static final String HASH = "HASH";
  private static final String FIRST_NAME = "john";
  private static final String LAST_NAME = "mary";
  private static final String PASPORT_NUMBER = "JM123";
  private static final String FLIGHT_NUMBER = "NUMBER";
  private static final Date FLIGHT_DATE = new Date();
  private static final String SEAT_CLASS = "economy";
  private static final boolean IS_VIP = true;
  private static final boolean IS_CHILD = true;

  private ReservationHttpClient reservationHttpClientMock;
  private PassengerLuggagePersistence inMemoryPassengerMock;
  private Passenger passengerMock;
  private ReservationDto reservationDto;
  private PassengerDto passengerDto;
  private PassengerFactory passengerFactoryMock;
  private CheckinHttpClient checkinHttpClientMock;

  private PassengerService passengerService;

  @Before
  public void initiateTest() {
    passengerDto = new PassengerDto();
    reservationDto = new ReservationDto();
    givenPassengerDto(passengerDto);
    PassengerDto[] passengerListDto = { passengerDto };
    givenReservationDto(reservationDto, passengerListDto);

    reservationHttpClientMock = mock(ReservationHttpClient.class);
    inMemoryPassengerMock = mock(PassengerLuggagePersistence.class);
    passengerMock = mock(Passenger.class);
    passengerFactoryMock = mock(PassengerFactory.class);
    checkinHttpClientMock = mock(CheckinHttpClient.class);

    passengerService = new PassengerService(reservationHttpClientMock, inMemoryPassengerMock, passengerFactoryMock, checkinHttpClientMock);
  }

  @Test
  public void givenPassengerHashWhenGetPassengerByHashThenVerifyPassengerIsGetFromReservationModule() {
    willReturn(reservationDto).given(reservationHttpClientMock).getReservationDtoFromReservation(HASH);

    passengerService.getPassengerByHash(HASH);

    verify(reservationHttpClientMock, times(1)).getReservationDtoFromReservation(HASH);
  }

  @Test
  public void givenPassengerHashStoredInReservationModuleWhenGetPassengerByHashThenVerifyPassengerIsCreated() {
    willReturn(reservationDto).given(reservationHttpClientMock).getReservationDtoFromReservation(HASH);

    passengerService.getPassengerByHash(HASH);

    verify(passengerFactoryMock, times(1)).createPassenger(any(String.class), any(Date.class), any(String.class), any(String.class),
        any(boolean.class), any(boolean.class));
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenStoredReservationWhenGetPassengerNotInThisReservationThenThrowException() {
    willThrow(NotFoundPassengerException.class).given(reservationHttpClientMock).getReservationDtoFromReservation(HASH);

    passengerService.getPassengerByHash(HASH);
  }

  @Test(expected = NotCheckedinException.class)
  public void givenPassengerDoesNotCheckInWhenGetPassengerWithCheckInDoneThenThrowException() {
    willReturn(reservationDto).given(reservationHttpClientMock).getReservationDtoFromReservation(HASH);
    willReturn(passengerMock).given(passengerFactoryMock).createPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, SEAT_CLASS, IS_VIP, IS_CHILD);
    willReturn(HASH).given(passengerMock).getPassengerHash();
    willThrow(NotCheckedinException.class).given(passengerMock).isCheckinDone(checkinHttpClientMock);

    passengerService.getCheckedPassengerByHash(HASH);
  }

  @Test
  public void givenPassengerAlreadyCheckInWhenGetPassengerWithCheckInDoneThenVerifyIfCheckinIsDone() {
    willReturn(reservationDto).given(reservationHttpClientMock).getReservationDtoFromReservation(HASH);
    willReturn(passengerMock).given(passengerFactoryMock).createPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, SEAT_CLASS, IS_VIP, IS_CHILD);
    willReturn(HASH).given(passengerMock).getPassengerHash();

    passengerService.getCheckedPassengerByHash(HASH);

    verify(passengerMock, times(1)).isCheckinDone(any());
  }

  @Test
  public void givenPassengerAlreadyCheckInWhenGetPassengerWithCheckInDoneThenReturnPassenger() {
    willReturn(reservationDto).given(reservationHttpClientMock).getReservationDtoFromReservation(HASH);
    willReturn(passengerMock).given(passengerFactoryMock).createPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, SEAT_CLASS, IS_VIP, IS_CHILD);
    willReturn(HASH).given(passengerMock).getPassengerHash();

    passengerService.getCheckedPassengerByHash(HASH);

    assertEquals(HASH, passengerMock.getPassengerHash());
  }

  private void givenReservationDto(ReservationDto reservationDto, PassengerDto[] passengersDto) {
    reservationDto.passengers = passengersDto;
    reservationDto.flight_date = FLIGHT_DATE;
    reservationDto.flight_number = FLIGHT_NUMBER;
  }

  private void givenPassengerDto(PassengerDto passengerDto) {
    passengerDto.first_name = FIRST_NAME;
    passengerDto.passenger_hash = HASH;
    passengerDto.seat_class = SEAT_CLASS;
    passengerDto.child = IS_CHILD;
    passengerDto.isVip = IS_VIP;
    passengerDto.last_name = LAST_NAME;
    passengerDto.passport_number = PASPORT_NUMBER;
  }
}
