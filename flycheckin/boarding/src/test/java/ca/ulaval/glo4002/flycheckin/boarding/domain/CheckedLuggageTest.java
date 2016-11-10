package ca.ulaval.glo4002.flycheckin.boarding.domain;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededCheckedLuggageException;
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
  private CheckedLuggage checkedLuggage;

  @Test(expected = ExcededCheckedLuggageException.class)
  public void givenLuggageWeightInKgOverLimitWhenCheckLuggageAllowableThenThrowException() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM, DIMENSION_UNIT_IN_CM, OVER_WEIGHT_KG, WEIGHT_UNIT_IN_KG);

    checkedLuggage.checkLuggageAllowable();
  }

  @Test(expected = ExcededCheckedLuggageException.class)
  public void givenLuggageDimensionInCmOverLimitWhenCheckLuggageAllowableThenThrowException() {
    checkedLuggage = new CheckedLuggage(OVER_DIMENSION_CM, DIMENSION_UNIT_IN_CM, ALLOWED_WEIGHT_KG,
    WEIGHT_UNIT_IN_KG);

    checkedLuggage.checkLuggageAllowable();
  }

  @Test(expected = ExcededCheckedLuggageException.class)
  public void givenLuggageWeightInLbsOverLimitWhenCheckLuggageAllowableThenThrowException() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_PO, DIMENSION_UNIT_IN_PO, OVER_WEIGHT_LBS,
                                        WEIGHT_UNIT_IN_LBS);

    checkedLuggage.checkLuggageAllowable();
  }

  @Test(expected = ExcededCheckedLuggageException.class)
  public void givenLuggageDimensionInPoOverLimitWhenCheckLuggageAllowableThenThrowException() {
    checkedLuggage = new CheckedLuggage(OVER_DIMENSION_PO, DIMENSION_UNIT_IN_PO, ALLOWED_WEIGHT_LBS,
    		                            WEIGHT_UNIT_IN_LBS);

    checkedLuggage.checkLuggageAllowable();
  }

  @Test
  public void givenLuggageAllowableWhenCheckLuggageAllowableThenDoNothing() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM, DIMENSION_UNIT_IN_CM, ALLOWED_WEIGHT_KG, 
    		                            WEIGHT_UNIT_IN_KG);

    checkedLuggage.checkLuggageAllowable();
  }
}
