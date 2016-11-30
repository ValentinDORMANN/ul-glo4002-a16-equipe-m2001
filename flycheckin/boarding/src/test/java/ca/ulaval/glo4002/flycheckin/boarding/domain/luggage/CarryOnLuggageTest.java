package ca.ulaval.glo4002.flycheckin.boarding.domain.luggage;

import static org.junit.Assert.*;

import org.junit.Test;

public class CarryOnLuggageTest {

  private static final int ALLOWED_WEIGHT_KG = 9;
  private static final double WEIGHT_LIMIT_KG = 10;
  private static final int OVER_WEIGHT_KG = 11;
  
  private static final int ALLOWED_DIMENSION_CM = 117;
  private static final int OVER_DIMENSION_CM = 119;
  
  private static final String LUGGAGE_TYPE = "carry-on";
  private static final String OTHER_LUGGAGE_TYPE = "other";
  private static final double BASE_PRICE = 30;
  private static final double FREE_PRICE = 0;
  private static final double DELTA = 0.01;
  
  private CarryOnLuggage carryOnLuggage;
  
  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageWeightInKgOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, OVER_WEIGHT_KG);
  
    assertEquals(OVER_WEIGHT_KG, carryOnLuggage.getWeightInKg());
    carryOnLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }
  
  @Test
  public void givenLuggageWeightInKgUnderLimitWhenWhenCheckLuggageAllowableThenDoNothing() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);
    
    assertEquals(ALLOWED_WEIGHT_KG, carryOnLuggage.getWeightInKg());
    carryOnLuggage.verifyAllowableWeight(WEIGHT_LIMIT_KG);
  }
  
  @Test(expected = NotAllowableLuggageException.class)
  public void givenLuggageDimensionInCmOverLimitWhenCheckLuggageAllowableThenThrowException() {
    carryOnLuggage = new CarryOnLuggage(OVER_DIMENSION_CM, ALLOWED_WEIGHT_KG);
    
    assertEquals(OVER_DIMENSION_CM, carryOnLuggage.getDimensionInCm());
    carryOnLuggage.verifyAllowableDimension();
  }
  
  @Test
  public void givenLuggageDimensionInCmUnderLimitWhenCheckLuggageAllowableThenDoNothing() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);
    
    assertEquals(ALLOWED_DIMENSION_CM, carryOnLuggage.getDimensionInCm());
    carryOnLuggage.verifyAllowableDimension();
  }
  
  @Test
  public void givenCheckedLuggageWhenCompareIsTypeWithCheckedLuggageThenReturnTrue() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);
  
    assertTrue(carryOnLuggage.isType(LUGGAGE_TYPE));
  }
  
  @Test
  public void givenCheckedLuggageWhenCompareIsTypeWithOtherLuggageThenReturnFalse() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);
  
    assertFalse(carryOnLuggage.isType(OTHER_LUGGAGE_TYPE));
  }

  @Test
  public void whenCalculatePriceThenSetBasePrice() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);
    
    carryOnLuggage.calculatePrice();
    
    assertEquals(BASE_PRICE, carryOnLuggage.getPrice(), DELTA);
  }
  
  @Test
  public void whenAssignLuggageFreeThenSetFreePrice() {
    carryOnLuggage = new CarryOnLuggage(ALLOWED_DIMENSION_CM, ALLOWED_WEIGHT_KG);
    
    carryOnLuggage.assignLuggageFree();
    
    assertEquals(FREE_PRICE, carryOnLuggage.getPrice(), DELTA);
  }
}
