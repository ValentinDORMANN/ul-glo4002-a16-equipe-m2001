package ca.ulaval.glo4002.flycheckin.boarding.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public class CheckedLuggageTest {
  private static final int WEIGHT = 10;
  private static final int DIMENSION = 30;
  private static final int WRONG_WEIGHT = 60;
  private static final int WRONG_DIMENSION = 67;
  private static final String DIMENSION_UNIT_IN_CM = "cm";
  private static final String WEIGHT_UNIT_IN_KG = "kg";
  private static final String WEIGHT_UNIT = "lbs"; 
  private static final String DIMENSION_UNIT = "po";
  private LuggageDto mockCheckedLuggageDto;
  private CheckedLuggage checkedLuggage;

  @Before
  public void initiateTest() {
    mockCheckedLuggageDto = mock(LuggageDto.class);
    mockCheckedLuggageDto.dimension = DIMENSION;
    mockCheckedLuggageDto.weight = WEIGHT;
    mockCheckedLuggageDto.dimension_unit = DIMENSION_UNIT;
    mockCheckedLuggageDto.weight_unit = WEIGHT_UNIT;
  }
  
  @Test
  public void givenLuggageWeightWhenCheckIfWeightIsAllowedThenReturnTrue() {  
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);
    
    assertTrue(checkedLuggage.isWeightAllowed());
  }
  
  @Test
  public void givenWrongLuggageWeightWhenCheckIfWeightIsAllowedThenReturnFalse() {
    mockCheckedLuggageDto.weight = WRONG_WEIGHT;
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);
    
    assertFalse(checkedLuggage.isWeightAllowed());
  }
  
  @Test
  public void givenWrongLuggageDimentionWhenCheckIfDimensionIsAllowedThenReturnFalse() {
    mockCheckedLuggageDto.dimension = WRONG_DIMENSION;
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);
    
    assertFalse(checkedLuggage.isDimensionAllowed());
  }
  
  @Test
  public void givenLuggageDimensionWhenCheckIfDimensionIsAllowedThenReturnTrue() {
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);
    
    assertTrue(checkedLuggage.isDimensionAllowed());
  }
  
  @Test
  public void givenLuggageWeightInKgWhenCheckIfWeightIsAllowedThenReturnTrue() {
    mockCheckedLuggageDto.weight_unit = WEIGHT_UNIT_IN_KG;
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);
    
    assertTrue(checkedLuggage.isWeightAllowed());
  }
  
  @Test
  public void givenLuggageDimensionInCmWhenCheckIfDimensionIsAllowedThenReturnTrue() {
    mockCheckedLuggageDto.dimension_unit = DIMENSION_UNIT_IN_CM;
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);
    
    assertTrue(checkedLuggage.isDimensionAllowed());
  }
  
}
