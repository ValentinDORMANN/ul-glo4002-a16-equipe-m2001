package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.InMemoryPassenger;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.externe.PassengerService;

public class LuggageCheckinServiceTest {

  private static final boolean IS_ALLOWED = true;
  private static final boolean NOT_ALLOWED = false;
  private static final String PASSENGER_HASH = "HASH";
  private static final int LINEAR_DIMENSION = 10;
  private static final String LINEAR_DIMENSION_UNIT = "cm";
  private static final int WEIGHT = 10;
  private static final String WEIGHT_UNIT = "kg";
  private static final String TYPE = "checked";
  private LuggageDto mockLuggageDto;
  private Passenger mockPassenger;
  private InMemoryPassenger mockInMemoryPassenger;
  private PassengerService mockPassengerService;
  private LuggageCheckinService luggageCheckinService;

  @Before
  public void initiateTest() {
    mockLuggageDto = mock(LuggageDto.class);
    mockLuggageDto.linear_dimension = LINEAR_DIMENSION;
    mockLuggageDto.linear_dimension_unit = LINEAR_DIMENSION_UNIT;
    mockLuggageDto.weight = WEIGHT;
    mockLuggageDto.weight_unit = WEIGHT_UNIT;
    mockLuggageDto.type = TYPE;
    mockPassenger = mock(Passenger.class);
    mockInMemoryPassenger = mock(InMemoryPassenger.class);
    mockPassengerService = mock(PassengerService.class);
    luggageCheckinService = new LuggageCheckinService(mockInMemoryPassenger, mockPassengerService);
    willReturn(mockPassenger).given(mockInMemoryPassenger).getPassengerByHash(PASSENGER_HASH);
  }

  @Test
  public void givenPassengerHashWhenPassengerNotInMemoryThenVerifyIfServiceGetHim() {
    willThrow(NotFoundPassengerException.class).given(mockInMemoryPassenger).getPassengerByHash(PASSENGER_HASH);

    luggageCheckinService.assignLuggage(PASSENGER_HASH, mockLuggageDto);

    verify(mockPassengerService).getPassengerByHashInReservation(PASSENGER_HASH);
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenPassengerHashWhenPassengerNotInMemoryNeitherReservationThenThrowException() {
    willThrow(NotFoundPassengerException.class).given(mockInMemoryPassenger).getPassengerByHash(PASSENGER_HASH);
    willThrow(NotFoundPassengerException.class).given(mockPassengerService)
        .getPassengerByHashInReservation(PASSENGER_HASH);

    luggageCheckinService.assignLuggage(PASSENGER_HASH, mockLuggageDto);
  }

  @Test
  public void givenBigLuggageWhenCreateLuggageThenThrowException() {

  }

}
