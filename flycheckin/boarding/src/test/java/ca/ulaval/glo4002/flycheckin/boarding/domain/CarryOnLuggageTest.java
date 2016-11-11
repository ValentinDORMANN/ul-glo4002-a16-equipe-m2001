package ca.ulaval.glo4002.flycheckin.boarding.domain;

import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.CarryOnLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededLuggageException;

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
  private CarryOnLuggage carryOnLuggage;

  @Test(expected = ExcededLuggageException.class)
  public void givenLuggageWeightInKgOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, DIMENSION_UNIT_IN_CM, OVER_WEIGHT_KG, WEIGHT_UNIT_IN_KG);

    carryOnLuggage.checkLuggageAllowable();
  }

  @Test(expected = ExcededLuggageException.class)
  public void givenLuggageDimensionInCmOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggage = new CarryOnLuggage(OVER_DIMENSION_CM, DIMENSION_UNIT_IN_CM, ALLOWED_WEIGHT_KG,
                                        WEIGHT_UNIT_IN_KG);

    carryOnLuggage.checkLuggageAllowable();
  }

  @Test(expected = ExcededLuggageException.class)
  public void givenLuggageWeightInLbsOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_PO, DIMENSION_UNIT_IN_PO, OVER_WEIGHT_LBS, 
                                        WEIGHT_UNIT_IN_LBS);

    carryOnLuggage.checkLuggageAllowable();
  }

  @Test(expected = ExcededLuggageException.class)
  public void givenLuggageDimensionInPoOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggage = new CarryOnLuggage(OVER_DIMENSION_PO, DIMENSION_UNIT_IN_PO, ALLOWED_WEIGHT_LBS,
                                        WEIGHT_UNIT_IN_LBS);

    carryOnLuggage.checkLuggageAllowable();
  }

  @Test
  public void givenLuggageAllowableWhenCheckLuggageAllowableThenDoNothing() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, DIMENSION_UNIT_IN_CM, ALLOWED_WEIGHT_KG,
                                        WEIGHT_UNIT_IN_KG);

    carryOnLuggage.checkLuggageAllowable();
  }
}
