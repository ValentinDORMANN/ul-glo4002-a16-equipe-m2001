package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.NotAllowableLuggageException;

public class BusinessPassengerTest {

  private static final int CHECKED_LUGGAGE_MAX_KG = 30;
  private static final String CHECKED_LUGGAGE_TYPE = "checked";
  private static final String CARRY_ON_LUGGAGE_TYPE = "carry-on";
  private static final String FLIGHT_NUMBER = "AAAA";
  private static final Date FLIGHT_DATE = new Date();
  private static final String HASH = "hash";
  private static final String ECONOMY = "economy";
  private static final boolean VIP_STATUS = false;
  private Luggage luggageMock;
  private BusinessPassenger businessPassenger;

  @Before
  public void initiateTest() {
    luggageMock = mock(Luggage.class);
    businessPassenger = new BusinessPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, ECONOMY, VIP_STATUS);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void test1() {
    simulateUnusualCarryOnLuggage();

    regularPassenger.addLuggage(luggageMock);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenCarryOnLuggageTooLongWhenAddLuggageThenThrowException() {
    willReturn(false).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willThrow(NotAllowableLuggageException.class).given(luggageMock).verifyAllowableDimension();

    businessPassenger.addLuggage(luggageMock);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenCarryOnLuggageTooHeavyWhenAddLuggageThenThrowException() {
    willReturn(false).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willThrow(NotAllowableLuggageException.class).given(luggageMock).verifyAllowableWeight(CARRY_ON_LUGGAGE_MAX_KG);

    businessPassenger.addLuggage(luggageMock);
  }

  @Test
  public void givenLuggageWhenAddLuggageThenVerifySetPrice() {
    businessPassenger.addLuggage(luggageMock);

    verify(luggageMock).setPrice(any(double.class));
  }

  @Test
  public void givenCarryOnLuggageWhenAddLuggageThenVerifySetPrice() {
    willReturn(false).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);

    businessPassenger.addLuggage(luggageMock);

    verify(luggageMock).setPrice(CARRY_ON_LUGGAGE_PRICE);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void given2CarryOnLuggageWhenAddLuggageThenThrowException() {
    willReturn(false).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willReturn(true).given(luggageMock).isType(CARRY_ON_LUGGAGE_TYPE);

    businessPassenger.addLuggage(luggageMock);
    businessPassenger.addLuggage(luggageMock);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenCheckedLuggageTooLongWhenAddLuggageThenThrowException() {
    willReturn(true).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willThrow(NotAllowableLuggageException.class).given(luggageMock).verifyAllowableDimension();

    businessPassenger.addLuggage(luggageMock);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void givenCheckedLuggageTooHeavyWhenAddLuggageThenThrowException() {
    willReturn(true).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willThrow(NotAllowableLuggageException.class).given(luggageMock).verifyAllowableWeight(CHECKED_LUGGAGE_MAX_KG);

    businessPassenger.addLuggage(luggageMock);
  }

  @Test(expected = NotAllowableLuggageException.class)
  public void given4CheckedLuggageWhenAddLuggageThenThrowException() {
    willReturn(true).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);

    businessPassenger.addLuggage(luggageMock);
    businessPassenger.addLuggage(luggageMock);
    businessPassenger.addLuggage(luggageMock);
    businessPassenger.addLuggage(luggageMock);
  }

  @Test
  public void given3CheckedLuggageWhenAddLuggageThenVerifySetPrice() {
    willReturn(true).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);

    businessPassenger.addLuggage(luggageMock);
    businessPassenger.addLuggage(luggageMock);
    businessPassenger.addLuggage(luggageMock);

    verify(luggageMock, times(2)).setPrice(0);
    verify(luggageMock, times(1)).setPrice(CHECKED_LUGGAGE_PRICE);
  }

  private void givenStandardCarryOnLuggage(Luggage luggage) {
  }

  private void givenUnusualCarryOnLuggage() {
    doThrow(new NotAllowableLuggageException()).when(luggageMock).verifyAllowableDimension();
    doThrow(new NotAllowableLuggageException()).when(luggageMock).verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_LIMIT);
  }

  private void givenStandardCheckedLuggage(Luggage luggage) {

  }

  private void givenUnusualCheckedLuggage(Luggage luggage) {

  }
}
