package ca.ulaval.glo4002.flycheckin.boarding.services.luggage;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;


import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CheckedLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.LuggageFactory;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.NotAllowableLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;
import ca.ulaval.glo4002.flycheckin.boarding.persistence.PassengerLuggagePersistence;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;
import ca.ulaval.glo4002.flycheckin.boarding.services.passenger.PassengerService;
import ca.ulaval.glo4002.flycheckin.boarding.exception.BoardingModuleException;

public class LuggageCheckinServiceTest {

  private static final String PASSENGER_HASH = "HASH";
  private static final int ALLOWED_LINEAR_DIMENSION = 10;
  private static final int ALLOWED_WEIGHT = 10;
  private static final int OVER_WEIGHT = 25;
  private static final String TYPE = "checked";
  private static final int OVER_LINEAR_DIMENSION = 1000;
private static final String LINEAR_DIMENSION_UNIT = "cm";
  private Passenger mockPassenger;
  private LuggageDto luggageDto = new LuggageDto();
  private PassengerService mockPassengerService;
  private LuggageCheckinService luggageCheckinService;
  private LuggageFactory mockLuggageFactory;
  private PassengerLuggagePersistence  mockassengerLuggagePersistence;

  @Before
  public void initiateTest() {
	assignedDtoAttribut(luggageDto);
    mockPassengerService = mock(PassengerService.class);
    mockPassenger = mock(Passenger.class);
    mockLuggageFactory = mock(LuggageFactory.class);
    mockassengerLuggagePersistence = mock(PassengerLuggagePersistence.class);
    luggageCheckinService = new LuggageCheckinService(mockPassengerService, mockPassenger, mockLuggageFactory,mockassengerLuggagePersistence);
  }
  private void assignedDtoAttribut(LuggageDto luggageDto){
	  	luggageDto.linear_dimension = ALLOWED_LINEAR_DIMENSION;
	    luggageDto.linear_dimension_unit = LINEAR_DIMENSION_UNIT;
	    luggageDto.weight = ALLOWED_WEIGHT;
	    luggageDto.type = TYPE;	  

  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenInexistantPassengerHashWhenAssignLuggageThenThrowException() {
	  willReturn(new CheckedLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT)).given(mockLuggageFactory).createLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT, TYPE);
    willThrow(NotFoundPassengerException.class).given(mockPassengerService).getPassengerByHash(PASSENGER_HASH);
  
    luggageCheckinService.assignLuggage(PASSENGER_HASH, luggageDto);
  }
  
/* @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageOverDimensionWhenAssignLuggageThenThrowException() {
    luggageDto.linear_dimension = OVER_LINEAR_DIMENSION;
  
    luggageCheckinService.assignLuggage(PASSENGER_HASH, luggageDto);
  }*/
  
 /*  @Test(expected = BoardingModuleException.class)
  public void givenLuggageOverWeightWhenAssignLuggageThenThrowException() {
    luggageDto.weight = OVER_WEIGHT;
  
    luggageCheckinService.assignLuggage(PASSENGER_HASH, luggageDto);
  }*/
  
  @Test
  public void whenAssignLuggageToPassengerVerifyPassengerServiceGetPassengerByHash() {
   // willReturn(PASSENGER_HASH).given(mockPassenger).getPassengerHash();
    willReturn(mockPassenger).given(mockPassengerService).getPassengerByHash(PASSENGER_HASH);
    CheckedLuggage checkedLuggage =new CheckedLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT);
    willReturn(checkedLuggage).given(mockLuggageFactory).createLuggage(ALLOWED_LINEAR_DIMENSION, ALLOWED_WEIGHT, TYPE);
    
  
    luggageCheckinService.assignLuggage(PASSENGER_HASH, luggageDto);
  
    verify(mockPassenger, times(1)).addLuggage(checkedLuggage);
  }
  
  /*@Test
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
