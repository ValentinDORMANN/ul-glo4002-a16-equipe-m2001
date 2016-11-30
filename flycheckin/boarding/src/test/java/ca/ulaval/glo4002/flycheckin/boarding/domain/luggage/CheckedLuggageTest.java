package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckedLuggageTest {

  private static final double WEIGHT_LIMIT_KG = 23;

  private static final int ALLOWED_DIMENSION_CM = 157;
  private static final int DIMENSION_LIMIT_CM = 158;
  private static final int OVER_DIMENSION_CM = 159;

  private static final String LUGGAGE_TYPE = "checked";
  private static final String OTHER_LUGGAGE_TYPE = "other";
  private static final double BASE_PRICE = 50;
  private static final double DELTA = 0.01;

  private CheckedLuggage checkedLuggage;

  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageWeightInKgOverLimitWhenCheckLuggageAllowableThenThrowException() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM, (int) WEIGHT_LIMIT_KG + 1);

    checkedLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test
  public void givenLuggageWeightInKgLimitWhenCheckLuggageAllowableThenDoNothing() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM, (int) WEIGHT_LIMIT_KG);

    checkedLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test
  public void givenLuggageWeightInKgUnderLimitWhenCheckLuggageAllowableThenDoNothing() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM, (int) WEIGHT_LIMIT_KG - 1);

    checkedLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageDimensionInCmOverLimitWhenCheckLuggageAllowableThenDoNothing() {
    checkedLuggage = new CheckedLuggage(OVER_DIMENSION_CM, (int) WEIGHT_LIMIT_KG);

    checkedLuggage.verifyAllowableDimension();
  }

  @Test
  public void givenLuggageDimensionInCmLimitWhenCheckLuggageAllowableThenDoNothing() {
    checkedLuggage = new CheckedLuggage(DIMENSION_LIMIT_CM, (int) WEIGHT_LIMIT_KG);

    checkedLuggage.verifyAllowableDimension();
  }

  @Test
  public void givenLuggageDimensionInCmUnderLimitWhenCheckLuggageAllowableThenDoNothing() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM, (int) WEIGHT_LIMIT_KG);

    checkedLuggage.verifyAllowableDimension();
  }

  @Test
  public void givenCheckedLuggageWhenCompareIsTypeWithCheckedLuggageThenReturnTrue() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM, (int) WEIGHT_LIMIT_KG);

    assertTrue(checkedLuggage.isType(LUGGAGE_TYPE));
  }

  @Test
  public void givenOtherLuggageWhenCompareIsTypeWithCheckedLuggageThenReturnFalse() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM, (int) WEIGHT_LIMIT_KG);

    assertFalse(checkedLuggage.isType(OTHER_LUGGAGE_TYPE));
  }

  @Test
  public void whenCalculatePriceThenGetBasePrice() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM, (int) WEIGHT_LIMIT_KG);

    checkedLuggage.calculatePrice();

    assertEquals(BASE_PRICE, checkedLuggage.getPrice(), DELTA);
  }

  @Test
  public void whenAssignLuggageFreeThenIsFree() {
    checkedLuggage = new CheckedLuggage(ALLOWED_DIMENSION_CM, (int) WEIGHT_LIMIT_KG);

    checkedLuggage.assignLuggageFree();

    assertTrue(checkedLuggage.isFree());
  }
}
