package ca.ulaval.glo4002.flycheckin.boarding.domain;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public class CheckedLuggageTest {
  private static final int OVER_WEIGHT_KG = 24;
  private static final int OVER_WEIGHT_LBS = 51;
  private static final int ALLOWED_WEIGHT_KG = 22;
  private static final int ALLOWED_WEIGHT_LBS = 49;
  private static final int OVER_DIMENSION_CM = 159;
  private static final int OVER_DIMENSION_PO = 63;
  private static final int ALLOWED_DIMENSION_CM = 150;
  private static final int ALLOWED_DIMENSION_PO = 60;
  private static final String DIMENSION_UNIT_IN_CM = "cm";
  private static final String WEIGHT_UNIT_IN_KG = "kg";
  private static final String WEIGHT_UNIT_IN_LBS = "lbs";
  private static final String DIMENSION_UNIT_IN_PO = "po";
  private static final String LUGGAGE_TYPE = "checked";
  private LuggageDto CheckedLuggageDto;
  private CheckedLuggage checkedLuggage;

  @Before
  public void initiateTest() {
    CheckedLuggageDto = new LuggageDto();
    CheckedLuggageDto.type = LUGGAGE_TYPE;
  }

  @Test(expected = ExcededLuggageException.class)
  public void givenLuggageWeightInKgOverLimitWhenCheckLuggageAllowableThenThrowException() {
    CheckedLuggageDto.linear_dimension = ALLOWED_DIMENSION_CM;
    CheckedLuggageDto.linear_dimension_unit = DIMENSION_UNIT_IN_CM;
    CheckedLuggageDto.weight = OVER_WEIGHT_KG;
    CheckedLuggageDto.weight_unit = WEIGHT_UNIT_IN_KG;
    checkedLuggage = new CheckedLuggage(CheckedLuggageDto);

    checkedLuggage.checkLuggageAllowable();
  }

  @Test(expected = ExcededLuggageException.class)
  public void givenLuggageDimensionInCmOverLimitWhenCheckLuggageAllowableThenThrowException() {
    CheckedLuggageDto.linear_dimension = OVER_DIMENSION_CM;
    CheckedLuggageDto.linear_dimension_unit = DIMENSION_UNIT_IN_CM;
    CheckedLuggageDto.weight = ALLOWED_WEIGHT_KG;
    CheckedLuggageDto.weight_unit = WEIGHT_UNIT_IN_KG;
    checkedLuggage = new CheckedLuggage(CheckedLuggageDto);

    checkedLuggage.checkLuggageAllowable();
  }

  @Test(expected = ExcededLuggageException.class)
  public void givenLuggageWeightInLbsOverLimitWhenCheckLuggageAllowableThenThrowException() {
    CheckedLuggageDto.linear_dimension = ALLOWED_DIMENSION_PO;
    CheckedLuggageDto.linear_dimension_unit = DIMENSION_UNIT_IN_PO;
    CheckedLuggageDto.weight = OVER_WEIGHT_LBS;
    CheckedLuggageDto.weight_unit = WEIGHT_UNIT_IN_LBS;
    checkedLuggage = new CheckedLuggage(CheckedLuggageDto);

    checkedLuggage.checkLuggageAllowable();
  }

  @Test(expected = ExcededLuggageException.class)
  public void givenLuggageDimensionInPoOverLimitWhenCheckLuggageAllowableThenThrowException() {
    CheckedLuggageDto.linear_dimension = OVER_DIMENSION_PO;
    CheckedLuggageDto.linear_dimension_unit = DIMENSION_UNIT_IN_PO;
    CheckedLuggageDto.weight = ALLOWED_WEIGHT_LBS;
    CheckedLuggageDto.weight_unit = WEIGHT_UNIT_IN_LBS;
    checkedLuggage = new CheckedLuggage(CheckedLuggageDto);

    checkedLuggage.checkLuggageAllowable();
  }

  @Test
  public void givenLuggageAllowableWhenCheckLuggageAllowableThenDoNothing() {
    CheckedLuggageDto.linear_dimension = ALLOWED_DIMENSION_CM;
    CheckedLuggageDto.linear_dimension_unit = DIMENSION_UNIT_IN_CM;
    CheckedLuggageDto.weight = ALLOWED_WEIGHT_KG;
    CheckedLuggageDto.weight_unit = WEIGHT_UNIT_IN_KG;
    checkedLuggage = new CheckedLuggage(CheckedLuggageDto);

    checkedLuggage.checkLuggageAllowable();
  }
}
