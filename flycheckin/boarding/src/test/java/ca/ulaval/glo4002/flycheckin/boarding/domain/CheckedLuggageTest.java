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
  private LuggageDto mockWrongCheckedLuggageDto;
  private CheckedLuggage wrongCheckedLuggage;
  private LuggageDto mockCheckedLuggageDtoOtherDimensions;
  private CheckedLuggage checkedLuggageOtherDimensions;

  @Before
  public void initiateTest() {
    mockCheckedLuggageDto = mock(LuggageDto.class);
    mockCheckedLuggageDto.dimension = DIMENSION;
    mockCheckedLuggageDto.weight = WEIGHT;
    mockCheckedLuggageDto.dimension_unit = DIMENSION_UNIT;
    mockCheckedLuggageDto.weight_unit = WEIGHT_UNIT;
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);
    mockWrongCheckedLuggageDto = mock(LuggageDto.class);
    mockWrongCheckedLuggageDto.dimension = WRONG_DIMENSION;
    mockWrongCheckedLuggageDto.weight = WRONG_WEIGHT;
    mockWrongCheckedLuggageDto.dimension_unit = DIMENSION_UNIT;
    mockWrongCheckedLuggageDto.weight_unit = WEIGHT_UNIT;
    wrongCheckedLuggage = new CheckedLuggage(mockWrongCheckedLuggageDto);
    mockCheckedLuggageDtoOtherDimensions = mock(LuggageDto.class);
    mockCheckedLuggageDtoOtherDimensions.dimension =DIMENSION;
    mockCheckedLuggageDtoOtherDimensions.weight = WEIGHT;
    mockCheckedLuggageDtoOtherDimensions.dimension_unit = DIMENSION_UNIT_IN_CM;
    mockCheckedLuggageDtoOtherDimensions.weight_unit = WEIGHT_UNIT_IN_KG;
    checkedLuggageOtherDimensions = new CheckedLuggage(mockCheckedLuggageDtoOtherDimensions);
    
  }
  
  @Test
  public void givenLuggageWeightWhenCheckIfWeightIsAllowedThenReturnTrue() {
    assertTrue(checkedLuggage.isWeightAllowed());
  }
  
  @Test
  public void givenWrongLuggageWeightWhenCheckIfWeightIsAllowedThenReturnFalse() {
    assertFalse(wrongCheckedLuggage.isWeightAllowed());
  }
  
  @Test
  public void givenWrongLuggageDimentionWhenCheckIfDimensionIsAllowedThenReturnFalse() {
    assertFalse(wrongCheckedLuggage.isDimensionAllowed());
  }
  
  @Test
  public void givenLuggageDimensionWhenCheckIfDimensionIsAllowedThenReturnTrue() {
    assertTrue(checkedLuggage.isDimensionAllowed());
  }
  
  @Test
  public void givenLuggageWeightInKgWhenCheckIfWeightIsAllowedThenReturnTrue() {
    assertTrue(checkedLuggageOtherDimensions.isWeightAllowed());
  }
  
  @Test
  public void givenLuggageDimensionInCmWhenCheckIfDimensionIsAllowedThenReturnTrue() {
    assertTrue(checkedLuggageOtherDimensions.isDimensionAllowed());
  }
  
}
