package ca.ulaval.glo4002.flycheckin.boarding.domain;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.CarryOnLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.LuggageDto;

public class CarryOnLuggageTest {

  private static final int OVER_WEIGHT_KG = 11;
  private static final int OVER_WEIGHT_LBS = 23;
  private static final int ALLOWED_WEIGHT_KG = 9;
  private static final int ALLOWED_WEIGHT_LBS = 21;
  private static final int OVER_DIMENSION_CM = 119;
  private static final int OVER_DIMENSION_PO = 47;
  private static final int ALLOWED_DIMENSION_CM = 117;
  private static final int ALLOWED_DIMENSION_PO = 45;
  private static final String DIMENSION_UNIT_IN_CM = "cm";
  private static final String WEIGHT_UNIT_IN_KG = "kg";
  private static final String WEIGHT_UNIT_IN_LBS = "lbs";
  private static final String DIMENSION_UNIT_IN_PO = "po";
  private static final String LUGGAGE_TYPE = "carry-on";
  private LuggageDto carryOnLuggageDto;
  private CarryOnLuggage carryOnLuggage;

  @Before
  public void initiateTest() {
    carryOnLuggageDto = new LuggageDto();
    carryOnLuggageDto.type = LUGGAGE_TYPE;
  }

  @Test(expected = ExcededLuggageException.class)
  public void givenLuggageWeightInKgOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggageDto.linear_dimension = ALLOWED_DIMENSION_CM;
    carryOnLuggageDto.linear_dimension_unit = DIMENSION_UNIT_IN_CM;
    carryOnLuggageDto.weight = OVER_WEIGHT_KG;
    carryOnLuggageDto.weight_unit = WEIGHT_UNIT_IN_KG;
    carryOnLuggage = new CarryOnLuggage(carryOnLuggageDto);

    carryOnLuggage.checkLuggageAllowable();
  }

  @Test(expected = ExcededLuggageException.class)
  public void givenLuggageDimensionInCmOverLimitWhenCheckLuggageAllowableThenThrowException() {
	  carryOnLuggageDto.linear_dimension = OVER_DIMENSION_CM;
	  carryOnLuggageDto.linear_dimension_unit = DIMENSION_UNIT_IN_CM;
	  carryOnLuggageDto.weight = ALLOWED_WEIGHT_KG;
	  carryOnLuggageDto.weight_unit = WEIGHT_UNIT_IN_KG;
	  carryOnLuggage = new CarryOnLuggage(carryOnLuggageDto);

	  carryOnLuggage.checkLuggageAllowable();
  }

  @Test(expected = ExcededLuggageException.class)
  public void givenLuggageWeightInLbsOverLimitWhenCheckLuggageAllowableThenThrowException() {
	  carryOnLuggageDto.linear_dimension = ALLOWED_DIMENSION_PO;
	  carryOnLuggageDto.linear_dimension_unit = DIMENSION_UNIT_IN_PO;
	  carryOnLuggageDto.weight = OVER_WEIGHT_LBS;
	  carryOnLuggageDto.weight_unit = WEIGHT_UNIT_IN_LBS;
	  carryOnLuggage = new CarryOnLuggage(carryOnLuggageDto);

	  carryOnLuggage.checkLuggageAllowable();
  }

  @Test(expected = ExcededLuggageException.class)
  public void givenLuggageDimensionInPoOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggageDto.linear_dimension = OVER_DIMENSION_PO;
    carryOnLuggageDto.linear_dimension_unit = DIMENSION_UNIT_IN_PO;
    carryOnLuggageDto.weight = ALLOWED_WEIGHT_LBS;
    carryOnLuggageDto.weight_unit = WEIGHT_UNIT_IN_LBS;
    carryOnLuggage = new CarryOnLuggage(carryOnLuggageDto);

    carryOnLuggage.checkLuggageAllowable();
  }

  @Test
  public void givenLuggageAllowableWhenCheckLuggageAllowableThenDoNothing() {
    carryOnLuggageDto.linear_dimension = ALLOWED_DIMENSION_CM;
    carryOnLuggageDto.linear_dimension_unit = DIMENSION_UNIT_IN_CM;
    carryOnLuggageDto.weight = ALLOWED_WEIGHT_KG;
    carryOnLuggageDto.weight_unit = WEIGHT_UNIT_IN_KG;
    carryOnLuggage = new CarryOnLuggage(carryOnLuggageDto);

    carryOnLuggage.checkLuggageAllowable();
  }
}
