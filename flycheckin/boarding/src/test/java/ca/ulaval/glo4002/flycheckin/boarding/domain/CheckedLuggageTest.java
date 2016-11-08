package ca.ulaval.glo4002.flycheckin.boarding.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public class CheckedLuggageTest {
  private static final int WEIGHT = 10;
  private static final int DIMENSION = 30;
  private static final int ONLY_LBS_WEIGHT = 40;
  private static final int ONLY_CM_DIMENSION = 80;
  private static final int WRONG_WEIGHT = 100;
  private static final int WRONG_DIMENSION = 1000;
  private static final String DIMENSION_UNIT_IN_CM = "cm";
  private static final String WEIGHT_UNIT_IN_KG = "kg";
  private static final String WEIGHT_UNIT_IN_LBS = "lbs";
  private static final String DIMENSION_UNIT_IN_PO = "po";
  private LuggageDto mockCheckedLuggageDto;
  private CheckedLuggage checkedLuggage;

  @Before
  public void initiateTest() {
    mockCheckedLuggageDto = mock(LuggageDto.class);
    mockCheckedLuggageDto.linear_dimension = DIMENSION;
    mockCheckedLuggageDto.weight = ONLY_LBS_WEIGHT;
    mockCheckedLuggageDto.linear_dimension_unit = DIMENSION_UNIT_IN_CM;
    mockCheckedLuggageDto.weight_unit = WEIGHT_UNIT_IN_LBS;
  }

  @Test
  public void givenLuggageWeightWhenCheckIfWeightIsAllowedThenReturnTrue() {
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);

    assertTrue(checkedLuggage.isAllowed());
  }

  @Test
  public void givenWrongLuggageWeightWhenCheckIfWeightIsAllowedThenReturnFalse() {
    mockCheckedLuggageDto.weight = WRONG_WEIGHT;
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);

    assertFalse(checkedLuggage.isAllowed());
  }

  @Test
  public void givenWrongLuggageDimentionWhenCheckIfDimensionIsAllowedThenReturnFalse() {
    mockCheckedLuggageDto.linear_dimension = WRONG_DIMENSION;
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);

    assertFalse(checkedLuggage.isAllowed());
  }

  @Test
  public void givenLuggageTooHeavyForKgWhenCheckIfWeightIsAllowedThenReturnFalse() {
    mockCheckedLuggageDto.weight_unit = WEIGHT_UNIT_IN_KG;
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);

    assertFalse(checkedLuggage.isAllowed());
  }

  @Test
  public void givenLuggageTooLongForPoWhenCheckIfDimensionIsAllowedThenReturnFalse() {
    mockCheckedLuggageDto.linear_dimension_unit = DIMENSION_UNIT_IN_PO;
    mockCheckedLuggageDto.linear_dimension = ONLY_CM_DIMENSION;
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);

    assertFalse(checkedLuggage.isAllowed());
  }

  @Test
  public void givenLightLuggageWhenCheckedIfIsAllowedThenReturnTrue() {
    mockCheckedLuggageDto.weight = WEIGHT;
    checkedLuggage = new CheckedLuggage(mockCheckedLuggageDto);

    assertTrue(checkedLuggage.isAllowed());
  }
}
