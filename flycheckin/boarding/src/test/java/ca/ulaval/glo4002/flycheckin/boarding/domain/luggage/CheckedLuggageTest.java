package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckedLuggageTest {
  private static final int WEIGHT_LIMIT_KG = 23;
  private static final int OVER_WEIGHT_KG = 24;
  private static final int ALLOWED_WEIGHT_KG = 22;
  private static final int OVER_DIMENSION_CM = 159;
  private static final int ALLOWED_DIMENSION_CM = 157;
  private static final String LUGGAGE_TYPE = "checked";
  private static final String OTHER_LUGGAGE_TYPE = "other";
  private CheckedLuggage checkedLuggage;

  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageWeightInKgOverLimitWhenCheckLuggageAllowableThenThrowException() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM, OVER_WEIGHT_KG);

    checkedLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageDimensionInCmOverLimitWhenCheckLuggageAllowableThenThrowException() {
    checkedLuggage = new CheckedLuggage(OVER_DIMENSION_CM, ALLOWED_WEIGHT_KG);

    checkedLuggage.verifyAllowableDimension();
  }

  @Test
  public void givenCheckedLuggageWhenCompareIsTypeWithCheckedLuggageThenReturnTrue() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);

    assertTrue(checkedLuggage.isType(LUGGAGE_TYPE));
  }

  @Test
  public void givenCheckedLuggageWhenCompareIsTypeWithOtherLuggageThenReturnFalse() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM,ALLOWED_WEIGHT_KG);

    assertFalse(checkedLuggage.isType(OTHER_LUGGAGE_TYPE));
  }
}
