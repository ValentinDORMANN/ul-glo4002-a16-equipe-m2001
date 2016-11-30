package ca.ulaval.glo4002.flycheckin.boarding.services.luggage;

import static org.mockito.Mockito.*;

import org.junit.Before;

import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.passenger.PassengerService;

public class LuggageCheckinServiceTest {

  private static final String PASSENGER_HASH = "HASH";
  private static final int ALLOWED_LINEAR_DIMENSION = 10;
  private static final String LINEAR_DIMENSION_UNIT = "cm";
  private static final int ALLOWED_WEIGHT = 10;
  private static final int OVER_WEIGHT = 25;
  private static final String WEIGHT_UNIT = "kg";
  private static final String TYPE = "checked";
  private static final int OVER_LINEAR_DIMENSION = 1000;
  private Passenger mockPassenger;
  private LuggageDto mockLuggageDto;
  private PassengerService mockPassengerService;
  private LuggageCheckinService luggageCheckinService;

  @Before
  public void initiateTest() {
    mockLuggageDto = mock(LuggageDto.class);
    mockLuggageDto.linear_dimension = ALLOWED_LINEAR_DIMENSION;
    mockLuggageDto.linear_dimension_unit = LINEAR_DIMENSION_UNIT;
    mockLuggageDto.weight = ALLOWED_WEIGHT;
    mockLuggageDto.weight_unit = WEIGHT_UNIT;
    mockLuggageDto.type = TYPE;
    mockPassengerService = mock(PassengerService.class);
    mockPassenger = mock(Passenger.class);
    luggageCheckinService = new LuggageCheckinService(mockPassengerService, mockPassenger, mockLuggageDto);
  }

  /* TODO
  // @Test(expected = NotFoundPassengerException.class)
  public void givenInexistantPassengerHashWhenAssignLuggageThenThrowException() {
    willThrow(NotFoundPassengerException.class).given(mockPassengerService).getPassengerByHash(PASSENGER_HASH);
  
    luggageCheckinService.assignLuggage(PASSENGER_HASH, mockLuggageDto);
  }
  
  // @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageOverDimensionWhenAssignLuggageThenThrowException() {
    mockLuggageDto.linear_dimension = OVER_LINEAR_DIMENSION;
  
    luggageCheckinService.assignLuggage(PASSENGER_HASH, mockLuggageDto);
  }
  
  // @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageOverWeightWhenAssignLuggageThenThrowException() {
    mockLuggageDto.weight = OVER_WEIGHT;
  
    luggageCheckinService.assignLuggage(PASSENGER_HASH, mockLuggageDto);
  }
  
  @Test
  public void whenAssignLuggageToPassengerVerifyPassengerServiceGetPassengerByHash() {
    willReturn(PASSENGER_HASH).given(mockPassenger).getPassengerHash();
    willReturn(mockPassenger).given(mockPassengerService).getPassengerByHash(PASSENGER_HASH);
    mockLuggageDto.linear_dimension = ALLOWED_LINEAR_DIMENSION;
    mockLuggageDto.weight = ALLOWED_WEIGHT;
    mockLuggageDto.linear_dimension_unit = LINEAR_DIMENSION_UNIT;
    mockLuggageDto.weight_unit = WEIGHT_UNIT;
  
    luggageCheckinService.assignLuggage(PASSENGER_HASH, mockLuggageDto);
  
    verify(mockPassengerService, times(1)).getPassengerByHash(PASSENGER_HASH);
  }
  
  @Test
  public void whenAssignLuggageToPassengerVerifyPassengerAddLuggage() {
    willReturn(PASSENGER_HASH).given(mockPassenger).getPassengerHash();
    willReturn(mockPassenger).given(mockPassengerService).getPassengerByHash(PASSENGER_HASH);
    mockLuggageDto.linear_dimension = ALLOWED_LINEAR_DIMENSION;
    mockLuggageDto.weight = ALLOWED_WEIGHT;
    mockLuggageDto.linear_dimension_unit = LINEAR_DIMENSION_UNIT;
    mockLuggageDto.weight_unit = WEIGHT_UNIT;
  
    luggageCheckinService.assignLuggage(PASSENGER_HASH, mockLuggageDto);
  
    verify(mockPassenger, times(1)).addLuggage(any(Luggage.class));
  }*/
}
