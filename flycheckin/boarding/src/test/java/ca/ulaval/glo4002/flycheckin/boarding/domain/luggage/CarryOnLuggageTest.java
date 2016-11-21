package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.exception.NotAllowableLuggageException;

public class CarryOnLuggageTest {

  private static final int OVER_WEIGHT_KG = 11;
  private static final int ALLOWED_WEIGHT_KG = 9;
  private static final int OVER_DIMENSION_CM = 119;
  private static final int ALLOWED_DIMENSION_CM = 117;
  private static final String DIMENSION_UNIT_IN_CM = "cm";
  private static final String WEIGHT_UNIT_IN_KG = "kg";
  private CarryOnLuggage carryOnLuggage;

  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageWeightInKgOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, DIMENSION_UNIT_IN_CM, OVER_WEIGHT_KG, WEIGHT_UNIT_IN_KG);

    carryOnLuggage.checkLuggageAllowable();
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageDimensionInCmOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggage = new CarryOnLuggage(OVER_DIMENSION_CM, DIMENSION_UNIT_IN_CM, ALLOWED_WEIGHT_KG, WEIGHT_UNIT_IN_KG);

    carryOnLuggage.checkLuggageAllowable();
  }

  @Test
  public void givenLuggageAllowableWhenCheckLuggageAllowableThenDoNothing() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, DIMENSION_UNIT_IN_CM, ALLOWED_WEIGHT_KG,
        WEIGHT_UNIT_IN_KG);

    carryOnLuggage.checkLuggageAllowable();
  }
}
