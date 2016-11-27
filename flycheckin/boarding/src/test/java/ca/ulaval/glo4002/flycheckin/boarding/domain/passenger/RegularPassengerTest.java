package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CarryOnLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CheckedLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotAllowableLuggageException;

public class RegularPassengerTest {

  private static final double CHECKED_LUGGAGE_PRICE = 50;
  private static final double CARRY_ON_LUGGAGE_PRICE = 30;
  private static final double FREE_LUGGAGE_PRICE = 0;
  private static final String CHECKED_LUGGAGE_TYPE = "checked";
  private static final String CARRY_ON_LUGGAGE_TYPE = "carry-on";
  private static final int CHECKED_LUGGAGE_DIMENSION_MAX_CM = 158;
  private static final int CHECKED_LUGGAGE_WEIGHT_MAX_KG = 23;
  
  private static final String FLIGHT_NUMBER = "AAAA";
  private static final Date FLIGHT_DATE = new Date();
  private static final String HASH = "hash";
  private static final String ECONOMY = "economy";
  private static final boolean VIP_STATUS = false;
  private static final double DELTA = 0.01;
  
  private CarryOnLuggage carryOnLuggageMock;
  private CheckedLuggage checkedLuggageMock;
  
  private RegularPassenger regularPassenger;
  
  @Before
  public void initiateTest() {
    carryOnLuggageMock = mock(CarryOnLuggage.class);
    checkedLuggageMock = mock(CheckedLuggage.class);
    regularPassenger = new RegularPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, ECONOMY, VIP_STATUS);
  }
  
  @Test
  public void givenCarryOnLuggageWhenCalculateLuggagePriceThenReturnCarryOnLuggagePrice() {
    given(checkedLuggageMock.isType(CHECKED_LUGGAGE_TYPE)).willReturn(false);
    
    double price = regularPassenger.calculateLuggagePrice(carryOnLuggageMock);
    
    assertEquals(CARRY_ON_LUGGAGE_PRICE, price, DELTA);
  }
  
  @Test
  public void givenCheckedLuggageWhenCalculateLuggagePriceThenReturnCheckedLuggagePrice() {
    given(checkedLuggageMock.isType(CHECKED_LUGGAGE_TYPE)).willReturn(true);
    
    double price = regularPassenger.calculateLuggagePrice(checkedLuggageMock);
    
    assertEquals(0, price, DELTA);
  }
  
  @Test
  public void givenCheckedLuggageWhenVerifyLuggageDimensionAllowableThenVerify() {
    given(checkedLuggageMock.isType(CHECKED_LUGGAGE_TYPE)).willReturn(true);
    
    regularPassenger.verifyLuggageDimensionAllowable(checkedLuggageMock);
    
    verify(checkedLuggageMock).verifyAllowableDimension(CHECKED_LUGGAGE_DIMENSION_MAX_CM);
  }
  
  @Test
  public void givenCarryOnLuggageWhenVerifyLuggageDimensionAllowableThenVerify() {  
    regularPassenger.verifyLuggageDimensionAllowable(carryOnLuggageMock);
    
    verify(carryOnLuggageMock).checkLuggageAllowable();
  }
  
  @Test(expected = NotAllowableLuggageException.class)
  public void givenOverDimensionCarryOnLuggageWhenVerifyLuggageDimensionAllowableThenThrowException() {
    willThrow(new NotAllowableLuggageException()).given(carryOnLuggageMock).checkLuggageAllowable();
  
    regularPassenger.verifyLuggageDimensionAllowable(carryOnLuggageMock);
  }
  
  @Test(expected = NotAllowableLuggageException.class)
  public void givenOverDimensionCheckedLuggageWhenVerifyLuggageDimensionAllowableThenThrowException() {
    given(checkedLuggageMock.isType(CHECKED_LUGGAGE_TYPE)).willReturn(true);
    
    willThrow(new NotAllowableLuggageException()).given(checkedLuggageMock).verifyAllowableDimension(CHECKED_LUGGAGE_DIMENSION_MAX_CM);
  
    regularPassenger.verifyLuggageDimensionAllowable(checkedLuggageMock);
  }
  
  @Test
  public void givenCarryOnLuggageWhenVerifyLuggageWeightAllowableThenVerify() {
    regularPassenger.verifyLuggageWeightAllowable(carryOnLuggageMock);
    
    verify(carryOnLuggageMock).checkLuggageAllowable();
  }
  
  @Test
  public void givenCheckedLuggageWhenVerifyLuggageWeightAllowableThenVerify() {
    given(checkedLuggageMock.isType(CHECKED_LUGGAGE_TYPE)).willReturn(true);
    
    regularPassenger.verifyLuggageWeightAllowable(checkedLuggageMock);
    
    verify(checkedLuggageMock).verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_MAX_KG);
  }
  
  @Test(expected = NotAllowableLuggageException.class)
  public void givenOverWeightCarryOnLuggageWhenVerifyLuggageAllowableThenThrowException() {
    willThrow(new NotAllowableLuggageException()).given(carryOnLuggageMock).checkLuggageAllowable();
  
    regularPassenger.verifyLuggageWeightAllowable(carryOnLuggageMock);
  }
  
  @Test(expected = NotAllowableLuggageException.class)
  public void givenOverWeightCheckedLuggageWhenVerifyLuggageAllowableThenThrowException() {
    willThrow(new NotAllowableLuggageException()).given(checkedLuggageMock).checkLuggageAllowable();
  
    regularPassenger.verifyLuggageWeightAllowable(checkedLuggageMock);
  }

  @Test
  public void whenAddFirstCarryOnLuggageAddItAndVerifyPrice() {
    regularPassenger.addLuggage(carryOnLuggageMock);
    
    verify(carryOnLuggageMock, times(1)).setPrice(CARRY_ON_LUGGAGE_PRICE);
    List<Luggage> luggages = regularPassenger.getLuggages();
    assertEquals(1, luggages.size());
  }
  
  @Test(expected = NotAllowableLuggageException.class)
  public void whenAddMoreThanOneCarryOnLuggageThenThrownException() {
    given(carryOnLuggageMock.isType(CARRY_ON_LUGGAGE_TYPE)).willReturn(true);
    
    regularPassenger.addLuggage(carryOnLuggageMock);
    regularPassenger.addLuggage(carryOnLuggageMock);
  }
  
  @Test
  public void whenAddFisrtCheckedLuggageThenAdditAndVerifyPrice() {
    given(checkedLuggageMock.isType(CHECKED_LUGGAGE_TYPE)).willReturn(true);
    
    regularPassenger.addLuggage(checkedLuggageMock);
    
    verify(checkedLuggageMock, times(1)).setPrice(FREE_LUGGAGE_PRICE);
  }
  
  @Test
  public void whenAddThreeCheckedLuggageThenAddThemAndVerifyPrice() {
    given(checkedLuggageMock.isType(CHECKED_LUGGAGE_TYPE)).willReturn(true);

    regularPassenger.addLuggage(checkedLuggageMock);
    regularPassenger.addLuggage(checkedLuggageMock);
    regularPassenger.addLuggage(checkedLuggageMock);

    verify(checkedLuggageMock, times(1)).setPrice(FREE_LUGGAGE_PRICE);
    verify(checkedLuggageMock, times(2)).setPrice(CHECKED_LUGGAGE_PRICE);
  }
  
  @Test(expected = NotAllowableLuggageException.class)
  public void whenAddMoreThanThreeCheckedLuggageThenThrownException() {
    given(checkedLuggageMock.isType(CHECKED_LUGGAGE_TYPE)).willReturn(true);
    
    regularPassenger.addLuggage(checkedLuggageMock);
    regularPassenger.addLuggage(checkedLuggageMock);
    regularPassenger.addLuggage(checkedLuggageMock);
    regularPassenger.addLuggage(checkedLuggageMock);
  }
}
