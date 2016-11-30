package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CarryOnLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CheckedLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.NotAllowableLuggageException;

public class BusinessPassengerTest {

  private static final int CHECKED_LUGGAGE_WEIGHT_LIMIT = 30;
  private static final int FREE_LUGGAGE_LIMIT = 2;
  private static final String CHECKED_LUGGAGE_TYPE = "checked";
  private static final String CARRYON_LUGGAGE_TYPE = "carry-on";
  private static final String FLIGHT_NUMBER = "AAAA";
  private static final Date FLIGHT_DATE = new Date();
  private static final String HASH = "hash";
  private static final String ECONOMY = "economy";
  private static final boolean VIP_STATUS = true;
  private static final double LUGGAGE_PRICE = 45;
  private static final double DELTA = 0.01;

  private Luggage luggageMock;
  private BusinessPassenger businessPassenger;

  @Before
  public void initiateTest() {
    businessPassenger = new BusinessPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, ECONOMY, !VIP_STATUS);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenUnusualWeightCheckedLuggageWhenAddLuggageToPassengerThenThrowException() {
    givenUnusualWeightCheckedLuggage();

    businessPassenger.addLuggage(luggageMock);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenUnusualSizeCheckedLuggageWhenAddLuggageToPassengerThenThrowException() {
    givenUnusualSizeCheckedLuggage();

    businessPassenger.addLuggage(luggageMock);
  }

  @Test
  public void givenStandardCheckedLuggageWhenAddLuggageToPassengerThenVerifyLinearDimensionLimit() {
    givenStandardCheckedLuggage();

    businessPassenger.addLuggage(luggageMock);

    verify(luggageMock, times(1)).verifyAllowableDimension();
  }

  @Test
  public void givenStandardCheckedLuggageWhenAddLuggageToPassengerThenVerifyWeightLimit() {
    givenStandardCheckedLuggage();

    businessPassenger.addLuggage(luggageMock);

    verify(luggageMock, times(1)).verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_LIMIT);
  }

  @Test
  public void givenStandardCheckedLuggageWhenAddLuggageToPassengerThenVerifyLuggageCalculateHisPrice() {
    givenStandardCheckedLuggage();

    businessPassenger.addLuggage(luggageMock);

    verify(luggageMock, times(1)).calculatePrice();
  }

  @Test
  public void givenNoFreeLuggageAssignWhenAddLuggageToPassengerThenVerifyLuggageAssignLuggageAsFree() {
    givenStandardCheckedLuggage();

    businessPassenger.addLuggage(luggageMock);

    verify(luggageMock, times(1)).assignLuggageFree();
  }

  @Test
  public void givenNoFreeLuggageAssignWhenAddLuggageToPassengerThenAssertTotalLuggagePrice() {
    givenStandardCheckedLuggage();

    businessPassenger.addLuggage(luggageMock);

    assertEquals(LUGGAGE_PRICE, businessPassenger.getTotalPrice(), DELTA);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenCheckedLuggageLimitReachedWhenAddAnotherCheckedLuggageToPassengerThenReturnException() {
    givenStandardCheckedLuggage();
    businessPassenger.addLuggage(luggageMock);
    businessPassenger.addLuggage(luggageMock);
    businessPassenger.addLuggage(luggageMock);

    businessPassenger.addLuggage(luggageMock);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void whenAddAnotherCheckedLuggageToPassengerThenReturnException() {
    givenVipPassengerWithCheckedLuggageLimitReached();

    businessPassenger.addLuggage(luggageMock);
  }

  @Test
  public void givenBusinessPassengerWithTwoFreeCheckedLuggageWhenAddCheckedLuggageThenDontAssignLuggageAsFree() {
    givenBusinessPassengerWithFreeCheckedLuggagesAlreadyAssigned();

    businessPassenger.addLuggage(luggageMock);

    verify(luggageMock, times(FREE_LUGGAGE_LIMIT)).assignLuggageFree();
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenUnusualWeightCarryOnLuggageWhenAddLuggageToPassengerThenThrowException() {
    givenUnusualWeightCarryOnLuggage();

    businessPassenger.addLuggage(luggageMock);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenUnusualSizeCarryOnLuggageWhenAddLuggageToPassengerThenThrowException() {
    givenUnusualSizeCarryOnLuggage();

    businessPassenger.addLuggage(luggageMock);
  }

  @Test
  public void givenStandardCarryOnLuggageWhenAddLuggageToPassengerThenVerifyLinearDimensionLimit() {
    givenStandardCarryOnLuggage();

    businessPassenger.addLuggage(luggageMock);

    verify(luggageMock, times(1)).verifyAllowableDimension();
  }

  @Test
  public void givenStandardCarryOnLuggageWhenAddLuggageToPassengerThenVerifyWeightLimit() {
    givenStandardCarryOnLuggage();

    businessPassenger.addLuggage(luggageMock);

    verify(luggageMock, times(1)).verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_LIMIT);
  }

  @Test
  public void givenStandardCarryOnLuggageWhenAddLuggageToPassengerThenVerifyLuggageCalculateHisPrice() {
    givenStandardCarryOnLuggage();

    businessPassenger.addLuggage(luggageMock);

    verify(luggageMock, times(1)).calculatePrice();
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenCarryOnLuggageAssignedWhenAddAnotherCarryOnLuggageToPassengerThenReturnException() {
    givenStandardCarryOnLuggage();
    businessPassenger.addLuggage(luggageMock);

    businessPassenger.addLuggage(luggageMock);
  }

  private void givenStandardCheckedLuggage() {
    luggageMock = mock(CheckedLuggage.class);
    willReturn(true).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willReturn(false).given(luggageMock).isType(CARRYON_LUGGAGE_TYPE);
    willReturn(LUGGAGE_PRICE).given(luggageMock).getPrice();
  }

  private void givenUnusualWeightCheckedLuggage() {
    luggageMock = mock(CheckedLuggage.class);
    willReturn(true).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willReturn(false).given(luggageMock).isType(CARRYON_LUGGAGE_TYPE);
    doThrow(new NotAllowableLuggageException()).when(luggageMock).verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_LIMIT);
  }

  private void givenUnusualSizeCheckedLuggage() {
    luggageMock = mock(CheckedLuggage.class);
    willReturn(true).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willReturn(false).given(luggageMock).isType(CARRYON_LUGGAGE_TYPE);
    doThrow(new NotAllowableLuggageException()).when(luggageMock).verifyAllowableDimension();
  }

  private void givenVipPassengerWithCheckedLuggageLimitReached() {
    businessPassenger = new BusinessPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, ECONOMY, VIP_STATUS);
    givenStandardCheckedLuggage();
    businessPassenger.addLuggage(luggageMock);
    businessPassenger.addLuggage(luggageMock);
    businessPassenger.addLuggage(luggageMock);
    businessPassenger.addLuggage(luggageMock);
  }

  private void givenBusinessPassengerWithFreeCheckedLuggagesAlreadyAssigned() {
    givenStandardCheckedLuggage();
    willReturn(true).given(luggageMock).isFree();
    businessPassenger.addLuggage(luggageMock);
    businessPassenger.addLuggage(luggageMock);
  }

  private void givenStandardCarryOnLuggage() {
    luggageMock = mock(CarryOnLuggage.class);
    willReturn(false).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willReturn(true).given(luggageMock).isType(CARRYON_LUGGAGE_TYPE);
    willReturn(LUGGAGE_PRICE).given(luggageMock).getPrice();
  }

  private void givenUnusualWeightCarryOnLuggage() {
    luggageMock = mock(CarryOnLuggage.class);
    willReturn(false).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willReturn(true).given(luggageMock).isType(CARRYON_LUGGAGE_TYPE);
    doThrow(new NotAllowableLuggageException()).when(luggageMock).verifyAllowableWeight(any(Integer.class));
  }

  private void givenUnusualSizeCarryOnLuggage() {
    luggageMock = mock(CarryOnLuggage.class);
    willReturn(false).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willReturn(true).given(luggageMock).isType(CARRYON_LUGGAGE_TYPE);
    doThrow(new NotAllowableLuggageException()).when(luggageMock).verifyAllowableDimension();
  }
}
