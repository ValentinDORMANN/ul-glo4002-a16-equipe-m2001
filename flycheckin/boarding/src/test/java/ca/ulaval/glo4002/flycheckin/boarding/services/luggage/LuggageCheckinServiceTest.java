package ca.ulaval.glo4002.flycheckin.boarding.services.luggage;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.client.CheckinHttpClient;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.LuggageFactory;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.RegularLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotCheckedinException;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.PassengerLuggagePersistence;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.passenger.PassengerService;

public class LuggageCheckinServiceTest {

  private static final String PASSENGER_HASH = "HASH";
  private static final int ALLOWED_LINEAR_DIMENSION = 10;
  private static final int ALLOWED_WEIGHT = 10;
  private static final int OVER_WEIGHT = 25;
  private static final String TYPE = "checked";
  private static final int OVER_LINEAR_DIMENSION = 1000;

  private Passenger mockPassenger;
  private LuggageDto luggageDto = new LuggageDto();
  private PassengerService passengerServiceMock;
  private LuggageFactory luggageFactoryMock;
  private PassengerLuggagePersistence passengerLuggagePersistenceMock;
  private CheckinHttpClient checkinHttpClientMock;
  private LuggageCheckinService luggageCheckinService;

  @Before
  public void initiateTest() {
    assignedDtoAttribut(luggageDto);
    passengerServiceMock = mock(PassengerService.class);
    mockPassenger = mock(Passenger.class);
    luggageFactoryMock = mock(LuggageFactory.class);
    passengerLuggagePersistenceMock = mock(PassengerLuggagePersistence.class);
    checkinHttpClientMock = mock(CheckinHttpClient.class);
    luggageCheckinService = new LuggageCheckinService(passengerServiceMock, mockPassenger, luggageFactoryMock,
        passengerLuggagePersistenceMock, checkinHttpClientMock);
  }

  private void assignedDtoAttribut(LuggageDto luggageDto) {
    luggageDto.linear_dimension = ALLOWED_LINEAR_DIMENSION;
    luggageDto.weight = ALLOWED_WEIGHT;
    luggageDto.type = TYPE;
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenInexistantPassengerHashWhenAssignLuggageThenThrowException() {
    willReturn(new RegularLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT)).given(luggageFactoryMock)
        .createLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT, TYPE);
    willThrow(NotFoundPassengerException.class).given(passengerServiceMock).getPassengerByHash(PASSENGER_HASH);

    luggageCheckinService.assignLuggage(PASSENGER_HASH, luggageDto);
  }

  @Test(expected = NotCheckedinException.class)
  public void givenNotCheckedinPassengerHashWhenAssignLuggageThenThrowException() {
    willReturn(new RegularLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT)).given(luggageFactoryMock)
        .createLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT, TYPE);
    willReturn(mockPassenger).given(passengerServiceMock).getPassengerByHash(PASSENGER_HASH);
    willThrow(NotCheckedinException.class).given(checkinHttpClientMock).verifyCheckinFromReservation(PASSENGER_HASH);

    luggageCheckinService.assignLuggage(PASSENGER_HASH, luggageDto);
  }

  @Test
  public void givenCheckedinPassengerHashWhenAssignLuggage() {
    willReturn(new RegularLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT)).given(luggageFactoryMock)
        .createLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT, TYPE);
    willReturn(mockPassenger).given(passengerServiceMock).getPassengerByHash(PASSENGER_HASH);

    luggageCheckinService.assignLuggage(PASSENGER_HASH, luggageDto);

    verify(checkinHttpClientMock, times(1)).verifyCheckinFromReservation(PASSENGER_HASH);
  }

  @Test
  public void givenLuggageOverDimensionWhenAssignLuggageThenVerifyLuggageAddToPassenger() {
    willReturn(mockPassenger).given(passengerServiceMock).getPassengerByHash(PASSENGER_HASH);
    luggageDto.linear_dimension = OVER_LINEAR_DIMENSION;
    RegularLuggage checkedLuggage = new RegularLuggage(luggageDto.linear_dimension, ALLOWED_WEIGHT);
    willReturn(checkedLuggage).given(luggageFactoryMock).createLuggage(luggageDto.linear_dimension, ALLOWED_WEIGHT,
        TYPE);

    luggageCheckinService.assignLuggage(PASSENGER_HASH, luggageDto);

    verify(mockPassenger, times(1)).addLuggage(checkedLuggage);
  }

  @Test
  public void givenLuggageOverWeightWhenAssignLuggageThenVerifyLuggageAddToPassenger() {
    willReturn(mockPassenger).given(passengerServiceMock).getPassengerByHash(PASSENGER_HASH);
    luggageDto.weight = OVER_WEIGHT;
    RegularLuggage checkedLuggage = new RegularLuggage(ALLOWED_LINEAR_DIMENSION, luggageDto.weight);
    willReturn(checkedLuggage).given(luggageFactoryMock).createLuggage(ALLOWED_LINEAR_DIMENSION, luggageDto.weight,
        TYPE);

    luggageCheckinService.assignLuggage(PASSENGER_HASH, luggageDto);

    verify(mockPassenger, times(1)).addLuggage(checkedLuggage);
  }

  @Test
  public void givenAllowableLuggageWhenAssignLuggageToPassengerVerifyAddLuggageIsCalled() {
    willReturn(mockPassenger).given(passengerServiceMock).getPassengerByHash(PASSENGER_HASH);
    RegularLuggage checkedLuggage = new RegularLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT);
    willReturn(checkedLuggage).given(luggageFactoryMock).createLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT, TYPE);

    luggageCheckinService.assignLuggage(PASSENGER_HASH, luggageDto);

    verify(mockPassenger, times(1)).addLuggage(checkedLuggage);
  }

  @Test
  public void givenALuggageWhenAssignToPassengerVerifySavePassengerLuggageIsCalledFormPersistence() {
    willReturn(mockPassenger).given(passengerServiceMock).getPassengerByHash(PASSENGER_HASH);
    RegularLuggage checkedLuggage = new RegularLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT);
    willReturn(checkedLuggage).given(luggageFactoryMock).createLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT, TYPE);

    luggageCheckinService.assignLuggage(PASSENGER_HASH, luggageDto);

    verify(passengerLuggagePersistenceMock, times(1)).savePassengerLuggage(mockPassenger);
  }
}
