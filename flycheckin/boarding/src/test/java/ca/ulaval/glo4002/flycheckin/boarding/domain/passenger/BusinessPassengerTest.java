package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

=======
>>>>>>> eb87a2543879c3a75d564714ae4525e8280f6f02
import java.util.Date;

import org.junit.Before;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;

public class BusinessPassengerTest {

  private static final int CHECKED_LUGGAGE_WEIGHT_LIMIT = 30;
  private static final String CHECKED_LUGGAGE_TYPE = "checked";
  private static final String CARRYON_LUGGAGE_TYPE = "carry-on";
  private static final String FLIGHT_NUMBER = "AAAA";
  private static final Date FLIGHT_DATE = new Date();
  private static final String HASH = "hash";
  private static final String ECONOMY = "economy";
  private static final boolean VIP_STATUS = false;
  private Luggage luggageMock;
  private BusinessPassenger businessPassenger;

  @Before
  public void initiateTest() {
    businessPassenger = new BusinessPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, ECONOMY, VIP_STATUS);
  }
  /*
  @Test(expected = NotAllowableLuggageException.class)
  public void whenAddLuggageToPassengerThenThrowException1() {
    givenUnusualWeightCarryOnLuggage();
  
    businessPassenger.addLuggage(luggageMock);
  }
  
  @Test(expected = NotAllowableLuggageException.class)
  public void whenAddLuggageToPassengerThenThrowException() {
    givenUnusualSizeCarryOnLuggage();
  
    businessPassenger.addLuggage(luggageMock);
  }
  
    @Test(expected = NotAllowableLuggageException.class)
  public void givenCarryOnLuggageTooHeavyWhenAddLuggageThenThrowException() {
    willReturn(false).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    // willThrow(NotAllowableLuggageException.class).given(luggageMock).verifyAllowableWeight();
  
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
  
    // verify(luggageMock).setPrice(CARRY_ON_LUGGAGE_PRICE);
  }
  
  @Test(expected = NotAllowableLuggageException.class)
  public void given2CarryOnLuggageWhenAddLuggageThenThrowException() {
    willReturn(false).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willReturn(true).given(luggageMock).isType(CARRYON_LUGGAGE_TYPE);
  
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
    willThrow(NotAllowableLuggageException.class).given(luggageMock)
        .verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_LIMIT);
  
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
    // verify(luggageMock, times(1)).setPrice();
  }
  
  private void givenStandardCarryOnLuggage(Luggage luggage) {
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
  
  private void givenStandardCheckedLuggage(Luggage luggage) {
  
  }
  
  private void givenUnusualCheckedLuggage(Luggage luggage) {
    luggageMock = mock(CheckedLuggage.class);
    willReturn(true).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
    willReturn(false).given(luggageMock).isType(CARRYON_LUGGAGE_TYPE);
    doThrow(new NotAllowableLuggageException()).when(luggageMock).verifyAllowableDimension();
    doThrow(new NotAllowableLuggageException()).when(luggageMock).verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_LIMIT);
  }*/
}
