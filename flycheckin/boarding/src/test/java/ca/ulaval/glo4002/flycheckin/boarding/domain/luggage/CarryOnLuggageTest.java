package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarryOnLuggageTest {

  private static final int ALLOWED_WEIGHT_KG = 9;
  private static final double WEIGHT_LIMIT_KG = 10;
  private static final int OVER_WEIGHT_KG = 11;

  private static final int ALLOWED_DIMENSION_CM = 117;
  private static final int DIMENSION_LIMIT_CM = 118;
  private static final int OVER_DIMENSION_CM = 119;

  private static final String LUGGAGE_TYPE = "carry-on";
  private static final String OTHER_LUGGAGE_TYPE = "other";
  private static final double BASE_PRICE = 30;
  private static final double DELTA = 0.01;

  private CarryOnLuggage carryOnLuggage;

  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageWeightInKgOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, OVER_WEIGHT_KG);

    carryOnLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test
  public void givenLuggageWeightInKgLimitWhenCheckLuggageAllowableThenDoNothing() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, (int) WEIGHT_LIMIT_KG);

    carryOnLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test
  public void givenLuggageWeightInKgUnderLimitWhenCheckLuggageAllowableThenDoNothing() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);

    carryOnLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageDimensionInCmOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggage = new CarryOnLuggage(OVER_DIMENSION_CM, ALLOWED_WEIGHT_KG);

    carryOnLuggage.verifyAllowableDimension();
  }

  @Test
  public void givenLuggageDimensionInCmLimitWhenCheckLuggageAllowableThenDoNothing() {
    carryOnLuggage = new CarryOnLuggage(DIMENSION_LIMIT_CM, ALLOWED_WEIGHT_KG);

    carryOnLuggage.verifyAllowableDimension();
  }

  @Test
  public void givenLuggageDimensionInCmUnderLimitWhenCheckLuggageAllowableThenDoNothing() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);

    carryOnLuggage.verifyAllowableDimension();
  }

  @Test
  public void givenCarryOnLuggageWhenCompareIsTypeWithCarryONLuggageThenReturnTrue() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);

    assertTrue(carryOnLuggage.isType(LUGGAGE_TYPE));
  }

  @Test
  public void givenOtherLuggageWhenCompareIsTypeWithCarryOnLuggageThenReturnFalse() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);

    assertFalse(carryOnLuggage.isType(OTHER_LUGGAGE_TYPE));
  }

  @Test
  public void whenCalculatePriceThenGetBasePrice() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);

    carryOnLuggage.calculatePrice();

    assertEquals(BASE_PRICE, carryOnLuggage.getPrice(), DELTA);
  }

  @Test
  public void whenAssignLuggageFreeThenIsNotFree() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);

    carryOnLuggage.assignLuggageFree();

    assertFalse(carryOnLuggage.isFree());
  }
}
