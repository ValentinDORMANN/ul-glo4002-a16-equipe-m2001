package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CarryOnLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CheckedLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotAllowableLuggageException;

public class RegularPassengerTest {

  private static final double CHECKED_LUGGAGE_PRICE = 50;
  private static final double CARRY_ON_LUGGAGE_PRICE = 30;
  private static final double DELTA = 0.01;
  private static final String CHECKED_LUGGAGE_TYPE = "checked";
  private static final int CHECKED_LUGGAGE_DIMENSION_MAX_CM = 158;
  private static final int CHECKED_LUGGAGE_WEIGHT_MAX_KG = 23;
  
  private CarryOnLuggage carryOnLuggageMock;
  private CheckedLuggage checkedLuggageMock;
  
  private RegularPassenger regularPassenger;
  
  @Before
  public void initiateTest(){
    carryOnLuggageMock = mock(CarryOnLuggage.class);
    checkedLuggageMock = mock(CheckedLuggage.class);
    regularPassenger = new RegularPassenger(null, null, null, null, false);
  }
  
  @Test
  public void givenCarryOnLuggageWhenCalculateLuggagePriceThenReturnCarryOnLuggagePrice() {
    given(checkedLuggageMock.isType(CHECKED_LUGGAGE_TYPE)).willReturn(false);
    
    double price = regularPassenger.calculateLuggagePrice(carryOnLuggageMock);
    
    assertEquals(CARRY_ON_LUGGAGE_PRICE, price, DELTA);
  }
  
  @Test
  public void givenCheckedLuggageWhenCalculateLuggagePriceThenReturnChechedLuggagePrice() {
    given(checkedLuggageMock.isType(CHECKED_LUGGAGE_TYPE)).willReturn(true);
    
    double price = regularPassenger.calculateLuggagePrice(checkedLuggageMock);
    
    assertEquals(CHECKED_LUGGAGE_PRICE, price, DELTA);
  }
  
  @Test
  public void givenCarryOnLuggageWhenVerifyLuggageDimensionAllowableThenVerify() {  
    regularPassenger.verifyLuggageDimensionAllowable(carryOnLuggageMock);
    
    verify(carryOnLuggageMock).verifyAllowableDimension(CHECKED_LUGGAGE_DIMENSION_MAX_CM);
  }
  
  @Test(expected = NotAllowableLuggageException.class)
  public void givenOverDimensionCarryOnLuggageWhenVerifyLuggageDimensionAllowableThenThrowException() {
    willThrow(new NotAllowableLuggageException()).given(carryOnLuggageMock).verifyAllowableDimension(CHECKED_LUGGAGE_DIMENSION_MAX_CM);
  
    regularPassenger.verifyLuggageDimensionAllowable(carryOnLuggageMock);
  }
  
  @Test
  public void givenCarryOnLuggageWhenVerifyLuggageWeightAllowableThenVerify() {
    regularPassenger.verifyLuggageWeightAllowable(carryOnLuggageMock);
    
    verify(carryOnLuggageMock).verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_MAX_KG);
  }
  
  @Test(expected = NotAllowableLuggageException.class)
  public void givenOverWeightCarryOnLuggageWhenVerifyLuggageAllowableThenThrowException() {
    willThrow(new NotAllowableLuggageException()).given(carryOnLuggageMock).verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_MAX_KG);
  
    regularPassenger.verifyLuggageWeightAllowable(carryOnLuggageMock);
  }
}
