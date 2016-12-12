package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CarryOnLuggageTest {

  private static final int WEIGHT_LIMIT_KG = 10;
  private static final int DIMENSION_LIMIT_CM = 118;
  private static final double BASE_PRICE = 30;
  private static final double DELTA = 0.01;
  private static final String CATEGORY = "standard";
  private static final String OTHER_CATEGORY = "standard";

  private CarryOnLuggage carryOnLuggage;
  private CarryOnLuggage otherLuggage;

  @Before
  public void initiateTest() {
    otherLuggage = new CarryOnLuggage(OTHER_CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageWeightInKgOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggage = new CarryOnLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG + 1);

    carryOnLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test
  public void givenLuggageWeightInKgLimitWhenCheckLuggageAllowableThenDoNothing() {
    carryOnLuggage = new CarryOnLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    carryOnLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test
  public void givenLuggageDimensionInCmLimitWhenCheckLuggageAllowableThenDoNothing() {
    carryOnLuggage = new CarryOnLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    carryOnLuggage.verifyAllowableDimension();
  }

  @Test
  public void givenLuggageWeightInKgUnderLimitWhenCheckLuggageAllowableThenDoNothing() {
    carryOnLuggage = new CarryOnLuggage(CATEGORY, DIMENSION_LIMIT_CM - 1, WEIGHT_LIMIT_KG - 1);

    carryOnLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageDimensionInCmOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggage = new CarryOnLuggage(CATEGORY, DIMENSION_LIMIT_CM + 1, WEIGHT_LIMIT_KG);

    carryOnLuggage.verifyAllowableDimension();
  }

  @Test
  public void givenCarryOnLuggageWhenCompareItsTypeWithCarryOnLuggageThenReturnTrue() {
    carryOnLuggage = new CarryOnLuggage(CATEGORY, DIMENSION_LIMIT_CM - 1, WEIGHT_LIMIT_KG - 1);

    boolean hasTheSameType = carryOnLuggage.hasSameCategory(otherLuggage);

    assertTrue(hasTheSameType);
  }

  @Test
  public void givenOtherLuggageWhenCompareItsTypeWithCarryOnLuggageThenReturnFalse() {
    carryOnLuggage = new CarryOnLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    boolean hasTheSameType = carryOnLuggage.hasSameCategory(otherLuggage);

    assertFalse(hasTheSameType);
  }

  @Test
  public void givenAllowableLuggageWhenCalculatePriceThenGetBasePrice() {
    carryOnLuggage = new CarryOnLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    carryOnLuggage.calculatePrice();

    assertEquals(BASE_PRICE, carryOnLuggage.getPrice(), DELTA);
  }

  @Test
  public void givenAllowableLuggageWhenAssignLuggageFreeThenIsNotFree() {
    carryOnLuggage = new CarryOnLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    carryOnLuggage.assignLuggageFree();

    assertFalse(carryOnLuggage.isFree());
  }
}
