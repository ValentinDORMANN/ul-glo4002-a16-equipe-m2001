package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public class LuggageFactoryTest {

  private static final String CHECKED_LUGGAGE = "checked";
  private static final String CARRY_ON_LUGGAGE = "carry-on";
  private static final String UNDEFINED_LUGGAGE = "undefined";
  private static final int LINEAR_DIMENSION = 100;
  private static final int WEIGHT = 10;
  
  private LuggageDto luggageDto;
  
  private LuggageFactory luggageFactory;
  
  @Before
  public void initiateTest(){ 
    luggageDto = new LuggageDto();
    luggageDto.linear_dimension = LINEAR_DIMENSION;
    luggageDto.weight = WEIGHT;
    
    luggageFactory = new LuggageFactory();
  }
  
  @Test
  public void givenCheckedLuggageDtoWhenCreateLuggageThenCreateCheckedLuggage() {
    luggageDto.type = CHECKED_LUGGAGE;
    
    Luggage luggage = luggageFactory.createLuggage(luggageDto);
    
    //assertTrue(luggage.isType(CHECKED_LUGGAGE));
    assertTrue(luggage instanceof CheckedLuggage);
  }
  
  @Test
  public void givenCarryOnLuggageDtoWhenCreateLuggageThenCreateCarryOnLuggage() {
    luggageDto.type = CARRY_ON_LUGGAGE;
    
    Luggage luggage = luggageFactory.createLuggage(luggageDto);
    
    assertTrue(luggage instanceof CarryOnLuggage);
  }
  
  @Test(expected = LuggageTypeUndefinedException.class)
  public void givenUndefinedLuggageDtoWhenCreateLuggageThenThrowException() {
    luggageDto.type = UNDEFINED_LUGGAGE;
    
    luggageFactory.createLuggage(luggageDto);
  }

}
