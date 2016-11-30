package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckedLuggageTest {

  private static final int WEIGHT_LIMIT_KG = 23;
  private static final int DIMENSION_LIMIT_CM = 158;
  private static final String LUGGAGE_TYPE = "checked";
  private static final String OTHER_LUGGAGE_TYPE = "other";
  private static final double BASE_PRICE = 50;
  private static final double DELTA = 0.001;

private static final Object UNDEFINED_HASH = "";

  private CheckedLuggage checkedLuggage;

  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageWeightInKgOverLimitWhenCheckLuggageAllowableThenThrowException() {
    checkedLuggage = new CheckedLuggage(DIMENSION_LIMIT_CM,  WEIGHT_LIMIT_KG + 1 );

    checkedLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test
  public void givenLuggageWeightInKgLimitWhenCheckLuggageAllowableThenDoNothing() {
    checkedLuggage = new CheckedLuggage(DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    checkedLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test
  public void givenLuggageWeightInKgUnderLimitWhenCheckLuggageAllowableThenDoNothing() {
    checkedLuggage = new CheckedLuggage(DIMENSION_LIMIT_CM -1 , WEIGHT_LIMIT_KG );

    checkedLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageDimensionInCmOverLimitWhenCheckLuggageAllowableThenDoNothing() {
    checkedLuggage = new CheckedLuggage(DIMENSION_LIMIT_CM +1,  WEIGHT_LIMIT_KG);

    checkedLuggage.verifyAllowableDimension();
  }

  @Test
  public void givenLuggageWithDimensionLimitWhenCheckLuggageAllowableThenDoNothing() {
    checkedLuggage = new CheckedLuggage(DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    checkedLuggage.verifyAllowableDimension();
  }

  @Test
  public void givenLuggageDimensionInCmUnderLimitWhenCheckLuggageAllowableThenDoNothing() {
    checkedLuggage = new CheckedLuggage(DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG -1);

    checkedLuggage.verifyAllowableDimension();
  }

  @Test
  public void givenCheckedLuggageWhenCompareItsTypeWithCheckedLuggageThenReturnTrue() {
    checkedLuggage = new CheckedLuggage(DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);
    
    boolean hasTheSameType = checkedLuggage.isType(LUGGAGE_TYPE);

    assertTrue(hasTheSameType);
  }

  @Test
  public void givenOtherLuggageWhenCompareItsTypeWithCheckedLuggageThenReturnFalse() {
    checkedLuggage = new CheckedLuggage(DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);
    
    boolean hasTheSameType = checkedLuggage.isType(OTHER_LUGGAGE_TYPE);

    assertFalse(hasTheSameType);
  }

  @Test
  public void givenLuggageAllowableWhenCalculatePriceThenGetBasePrice() {
    checkedLuggage = new CheckedLuggage(DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    checkedLuggage.calculatePrice();

    assertEquals(BASE_PRICE, checkedLuggage.getPrice(), DELTA);
  }

  @Test
  public void givenLuggageAllowableWhenAssignLuggageFreeThenLuggageIsFree() {
    checkedLuggage = new CheckedLuggage(DIMENSION_LIMIT_CM, WEIGHT_LIMIT_KG);

    checkedLuggage.assignLuggageFree();

    assertTrue(checkedLuggage.isFree());
  }
  
  @Test
  public void givenLuggageWhenNeededHashCodeThenReturnDefineHash() {
	  checkedLuggage = new CheckedLuggage(DIMENSION_LIMIT_CM,  WEIGHT_LIMIT_KG);
	  
	  String hashLuggage = checkedLuggage.getLuggageHash();

	  assertNotEquals(UNDEFINED_HASH,hashLuggage);
  }
}
