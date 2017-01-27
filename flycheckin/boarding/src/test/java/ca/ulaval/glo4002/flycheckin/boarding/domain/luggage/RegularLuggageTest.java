package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RegularLuggageTest {

  private static final int WEIGHT_LIMIT_KG = 23;
  private static final int DIMENSION_LIMIT_CM = 158;
  private static final String CATEGORY = "checked";
  private static final String OTHER_CATEGORY = "other";
  private static final double BASE_PRICE = 50;
  private static final double FIRST_STAGE_PENALTY_PRICE = BASE_PRICE + BASE_PRICE * (double) 10 / (double) 100;
  private static final double SECOND_STAGE_PENALTY_PRICE = BASE_PRICE + BASE_PRICE * (double) 21 / (double) 100;
  private static final double DELTA = 0.001;

  private static final String UNDEFINED_HASH = "";

  private RegularLuggage regularLuggage;
  private RegularLuggage otherLuggage;

  @Before
  public void initiateTest() {
    otherLuggage = new RegularLuggage(OTHER_CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);
  }

  @Test
  public void givenLuggageWeightInKgOverLimitWhenCheckLuggageAllowableThenFirstStagePenaltyPrice() {
    regularLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG + 1);

    regularLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
    regularLuggage.calculatePrice();

    assertEquals(FIRST_STAGE_PENALTY_PRICE, regularLuggage.getPrice(), DELTA);
  }

  @Test
  public void givenLuggageWeightInKgLimitWhenCheckLuggageAllowableThenReturnBasePrice() {
    regularLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    regularLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
    regularLuggage.calculatePrice();

    assertEquals(BASE_PRICE, regularLuggage.getPrice(), DELTA);
  }

  @Test
  public void givenLuggageWeightInKgUnderLimitWhenCheckLuggageAllowableThenReturnBasePrice() {
    regularLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM - 1, WEIGHT_LIMIT_KG);

    regularLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
    regularLuggage.calculatePrice();

    assertEquals(BASE_PRICE, regularLuggage.getPrice(), DELTA);
  }

  @Test
  public void givenLuggageDimensionInCmOverLimitWhenCheckLuggageAllowableReturnFirstStagePenaltyPrice() {
    regularLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM + 1, WEIGHT_LIMIT_KG);

    regularLuggage.verifyAllowableDimension();
    regularLuggage.calculatePrice();

    assertEquals(FIRST_STAGE_PENALTY_PRICE, regularLuggage.getPrice(), DELTA);
  }

  @Test
  public void givenLuggageWithDimensionLimitWhenCheckLuggageAllowableThenReturnBasePrice() {
    regularLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    regularLuggage.verifyAllowableDimension();
    regularLuggage.calculatePrice();

    assertEquals(BASE_PRICE, regularLuggage.getPrice(), DELTA);
  }

  @Test
  public void givenLuggageDimensionInCmUnderLimitWhenCheckLuggageAllowableThenReturnBasePrice() {
    regularLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG - 1);

    regularLuggage.verifyAllowableDimension();
    regularLuggage.calculatePrice();

    assertEquals(BASE_PRICE, regularLuggage.getPrice(), DELTA);
  }

  @Test
  public void givenLuggageDimensionAndWeightOverLimitWhenCheckLuggageAllowableReturnSecondStagePenaltyPrice() {
    regularLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM + 1, WEIGHT_LIMIT_KG + 1);

    regularLuggage.verifyAllowableDimension();
    regularLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
    regularLuggage.calculatePrice();

    assertEquals(SECOND_STAGE_PENALTY_PRICE, regularLuggage.getPrice(), DELTA);
  }

  @Test
  public void givenregularLuggageWhenCompareItsTypeWithregularLuggageThenReturnTrue() {
    regularLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);
    otherLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    boolean hasTheSameType = regularLuggage.hasSameCategory(otherLuggage);

    assertTrue(hasTheSameType);
  }

  @Test
  public void givenOtherLuggageWhenCompareItsTypeWithregularLuggageThenReturnFalse() {
    regularLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    boolean hasTheSameType = regularLuggage.hasSameCategory(otherLuggage);

    assertFalse(hasTheSameType);
  }

  @Test
  public void givenLuggageAllowableWhenCalculatePriceThenGetBasePrice() {
    regularLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    regularLuggage.calculatePrice();

    assertEquals(BASE_PRICE, regularLuggage.getPrice(), DELTA);
  }

  @Test
  public void givenLuggageAllowableWhenAssignLuggageFreeThenLuggageIsFree() {
    regularLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    regularLuggage.assignLuggageFree();

    assertTrue(regularLuggage.isFree());
  }

  @Test
  public void givenLuggageWhenNeededHashCodeThenReturnDefineHash() {
    regularLuggage = new RegularLuggage(CATEGORY, DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    String hashLuggage = regularLuggage.getLuggageHash();

    assertNotEquals(UNDEFINED_HASH, hashLuggage);
  }
}
