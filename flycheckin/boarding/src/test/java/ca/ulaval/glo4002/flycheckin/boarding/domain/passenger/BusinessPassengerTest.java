package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CarryOnLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.NotAllowableLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.RegularLuggage;

public class BusinessPassengerTest {

  private static final int CHECKED_LUGGAGE_WEIGHT_LIMIT = 30;
  private static final int FREE_LUGGAGE_LIMIT = 2;

  private static final String CHECKED_LUGGAGE_CATEGORY = "checked";
  private static final String CARRYON_LUGGAGE_CATEGORY = "standard";

  private static final String FLIGHT_NUMBER = "AAAA";
  private static final Date FLIGHT_DATE = new Date();
  private static final String HASH = "hash";
  private static final String ECONOMY = "economy";
  private static final boolean IS_VIP = true;
  private static final boolean IS_CHILD = true;
  private static final double LUGGAGE_PRICE = 100;
  private static final double VIP_LUGGAGE_PRICE = 95;
  private static final double DELTA = 0.01;

  private RegularLuggage regularLuggageMock;
  private CarryOnLuggage carryOnLuggageMock;

  private BusinessPassenger businessPassenger;

  @Before
  public void initiateTest() {
    regularLuggageMock = mock(RegularLuggage.class);
    carryOnLuggageMock = mock(CarryOnLuggage.class);

    businessPassenger = new BusinessPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, ECONOMY, !IS_VIP, !IS_CHILD);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenUnusualWeightCheckedLuggageWhenAddLuggageToPassengerThenThrowException() {
    givenUnusualWeightRegularCheckedLuggage();

    businessPassenger.addLuggage(regularLuggageMock);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenUnusualSizeCheckedLuggageWhenAddLuggageToPassengerThenThrowException() {
    givenUnusualSizeRegularCheckedLuggage();

    businessPassenger.addLuggage(regularLuggageMock);
  }

  @Test
  public void givenAllowableCheckedLuggageWhenAddLuggageToPassengerThenVerifyLinearDimensionLimit() {
    givenRegularCheckedLuggage();

    businessPassenger.addLuggage(regularLuggageMock);

    verify(regularLuggageMock, times(1)).verifyAllowableDimension();
  }

  @Test
  public void givenAllowableCheckedLuggageWhenAddLuggageToPassengerThenVerifyWeightLimit() {
    givenRegularCheckedLuggage();

    businessPassenger.addLuggage(regularLuggageMock);

    verify(regularLuggageMock, times(1)).verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_LIMIT);
  }

  @Test
  public void givenAllowableCheckedLuggageWhenAddLuggageToPassengerThenVerifyLuggageCalculateHisPrice() {
    givenRegularCheckedLuggage();

    businessPassenger.addLuggage(regularLuggageMock);

    verify(regularLuggageMock, times(1)).calculatePrice();
  }

  @Test
  public void givenNoFreeLuggageAssignWhenAddLuggageToPassengerThenVerifyLuggageAssignLuggageAsFree() {
    givenRegularCheckedLuggage();

    businessPassenger.addLuggage(regularLuggageMock);

    verify(regularLuggageMock, times(1)).assignLuggageFree();
  }

  @Test
  public void givenBusinessPassengerWhenGetTotalLuggagePriceThenReturnLuggageTotalPrice() {
    givenRegularCheckedLuggage();

    businessPassenger.addLuggage(regularLuggageMock);

    assertEquals(LUGGAGE_PRICE, businessPassenger.getTotalPrice(), DELTA);
  }

  @Test
  public void givenVipBusinessPassengerWhenGetTotalLuggagePriceThenApplyDiscount() {
    businessPassenger = new BusinessPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, ECONOMY, IS_VIP, !IS_CHILD);
    givenRegularCheckedLuggage();

    businessPassenger.addLuggage(regularLuggageMock);

    assertEquals(VIP_LUGGAGE_PRICE, businessPassenger.getTotalPrice(), DELTA);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenAllCheckedLuggageRecordedWhenAddAnotherCheckedLuggageToPassengerThenReturnException() {
    givenNotVipBusinessPassengerWithCheckedLuggageLimitReached();

    businessPassenger.addLuggage(regularLuggageMock);
  }

  @Test
  public void givenAllowableRegularLuggageAssignedWhenAddCarryOnLuggageToPassengerThenAddLuggage() {
    givenNotVipBusinessPassengerWithCheckedLuggageLimitReached();
    int luggagesAssigned = businessPassenger.getLuggages().size();

    givenStandardCarryOnLuggage();
    businessPassenger.addLuggage(carryOnLuggageMock);

    assertEquals(luggagesAssigned + 1, businessPassenger.getLuggages().size());
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void whenAddAnotherCheckedLuggageToPassengerThenReturnException() {
    givenVipBusinessPassengerWithCheckedLuggageLimitReached();

    businessPassenger.addLuggage(regularLuggageMock);
  }

  @Test
  public void givenBusinessPassengerWithTwoFreeCheckedLuggageWhenAddCheckedLuggageThenDontAssignLuggageAsFree() {
    givenBusinessPassengerWithFreeCheckedLuggagesAlreadyAssigned();

    businessPassenger.addLuggage(regularLuggageMock);

    verify(regularLuggageMock, times(FREE_LUGGAGE_LIMIT)).assignLuggageFree();
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenUnusualWeightCarryOnLuggageWhenAddLuggageToPassengerThenThrowException() {
    givenUnusualWeightCarryOnLuggage();

    businessPassenger.addLuggage(carryOnLuggageMock);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenUnusualSizeCarryOnLuggageWhenAddLuggageToPassengerThenThrowException() {
    givenUnusualSizeCarryOnLuggage();

    businessPassenger.addLuggage(carryOnLuggageMock);
  }

  @Test
  public void givenStandardCarryOnLuggageWhenAddLuggageToPassengerThenVerifyLinearDimensionLimit() {
    givenStandardCarryOnLuggage();

    businessPassenger.addLuggage(carryOnLuggageMock);

    verify(carryOnLuggageMock, times(1)).verifyAllowableDimension();
  }

  @Test
  public void givenStandardCarryOnLuggageWhenAddLuggageToPassengerThenVerifyWeightLimit() {
    givenStandardCarryOnLuggage();

    businessPassenger.addLuggage(carryOnLuggageMock);

    verify(carryOnLuggageMock, times(1)).verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_LIMIT);
  }

  @Test
  public void givenStandardCarryOnLuggageWhenAddLuggageToPassengerThenVerifyLuggageCalculateHisPrice() {
    givenStandardCarryOnLuggage();

    businessPassenger.addLuggage(carryOnLuggageMock);

    verify(carryOnLuggageMock, times(1)).calculatePrice();
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenCarryOnLuggageAssignedWhenAddAnotherCarryOnLuggageToPassengerThenReturnException() {
    givenStandardCarryOnLuggage();
    businessPassenger.addLuggage(carryOnLuggageMock);

    businessPassenger.addLuggage(carryOnLuggageMock);
  }

  private void givenRegularCheckedLuggage() {
    willReturn(CHECKED_LUGGAGE_CATEGORY).given(regularLuggageMock).getCategory();
    willReturn(true).given(regularLuggageMock).hasSameCategory(regularLuggageMock);
    willReturn(false).given(regularLuggageMock).hasSameCategory(carryOnLuggageMock);
    willReturn(LUGGAGE_PRICE).given(regularLuggageMock).getPrice();
  }

  private void givenUnusualWeightRegularCheckedLuggage() {
    willReturn(CHECKED_LUGGAGE_CATEGORY).given(regularLuggageMock).getCategory();
    doThrow(new NotAllowableLuggageException()).when(regularLuggageMock).verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_LIMIT);
  }

  private void givenUnusualSizeRegularCheckedLuggage() {
    willReturn(CHECKED_LUGGAGE_CATEGORY).given(regularLuggageMock).getCategory();
    doThrow(new NotAllowableLuggageException()).when(regularLuggageMock).verifyAllowableDimension();
  }

  private void givenNotVipBusinessPassengerWithCheckedLuggageLimitReached() {
    givenRegularCheckedLuggage();
    businessPassenger.addLuggage(regularLuggageMock);
    businessPassenger.addLuggage(regularLuggageMock);
    businessPassenger.addLuggage(regularLuggageMock);
  }

  private void givenVipBusinessPassengerWithCheckedLuggageLimitReached() {
    businessPassenger = new BusinessPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, ECONOMY, IS_VIP, !IS_CHILD);
    givenRegularCheckedLuggage();
    businessPassenger.addLuggage(regularLuggageMock);
    businessPassenger.addLuggage(regularLuggageMock);
    businessPassenger.addLuggage(regularLuggageMock);
    businessPassenger.addLuggage(regularLuggageMock);
  }

  private void givenBusinessPassengerWithFreeCheckedLuggagesAlreadyAssigned() {
    givenRegularCheckedLuggage();
    willReturn(true).given(regularLuggageMock).isFree();
    businessPassenger.addLuggage(regularLuggageMock);
    businessPassenger.addLuggage(regularLuggageMock);
  }

  private void givenStandardCarryOnLuggage() {
    willReturn(CARRYON_LUGGAGE_CATEGORY).given(carryOnLuggageMock).getCategory();
    willReturn(true).given(carryOnLuggageMock).hasSameCategory(carryOnLuggageMock);
    willReturn(false).given(carryOnLuggageMock).hasSameCategory(regularLuggageMock);
    willReturn(LUGGAGE_PRICE).given(carryOnLuggageMock).getPrice();
  }

  private void givenUnusualWeightCarryOnLuggage() {
    givenStandardCarryOnLuggage();
    doThrow(new NotAllowableLuggageException()).when(carryOnLuggageMock).verifyAllowableWeight(any(Integer.class));
  }

  private void givenUnusualSizeCarryOnLuggage() {
    givenStandardCarryOnLuggage();
    doThrow(new NotAllowableLuggageException()).when(carryOnLuggageMock).verifyAllowableDimension();
  }
}
