package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.NotAllowableLuggageException;

public class RegularPassengerTest {

  private static final String CHECKED_LUGGAGE_TYPE = "checked";
  private static final String CARRY_ON_LUGGAGE_TYPE = "carry-on";
  private static final String FLIGHT_NUMBER = "AAAA";
  private static final Date FLIGHT_DATE = new Date();
  private static final String HASH = "hash";
  private static final String ECONOMY = "economy";
  private static final boolean VIP_STATUS = false;
  private static final double DELTA = 0.01;
  private static final double CHECKED_LUGGAGE_WEIGHT_LIMIT = 23;

  private Luggage luggageMock;

  private RegularPassenger regularPassenger;

  @Before
  public void initiateTest() {
    regularPassenger = new RegularPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, ECONOMY, VIP_STATUS);
  }

  public void test1() {
    givenUnusualCarryOnLuggage(luggageMock);

    regularPassenger.addLuggage(luggageMock);
  }

  private void givenStandardCarryOnLuggage(Luggage luggageMock) {
    willReturn(true).given(luggageMock).isType(CARRY_ON_LUGGAGE_TYPE);
    willReturn(false).given(luggageMock).isFree();
  }

  private void givenUnusualCarryOnLuggage(Luggage luggageMock) {
    willReturn(true).given(luggageMock).isType(CARRY_ON_LUGGAGE_TYPE);
    willReturn(false).given(luggageMock).isFree();
    doThrow(new NotAllowableLuggageException()).when(luggageMock).verifyAllowableDimension();
    doThrow(new NotAllowableLuggageException()).when(luggageMock).verifyAllowableWeight(CHECKED_LUGGAGE_WEIGHT_LIMIT);
  }

  private void givenStandardCheckedLuggage(Luggage luggageMock) {
    willReturn(false).given(luggageMock).isType(CARRY_ON_LUGGAGE_TYPE);
    willReturn(true).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
  }

  private void givenUnusualCheckedLuggage(Luggage luggageMock) {
    willReturn(false).given(luggageMock).isType(CARRY_ON_LUGGAGE_TYPE);
    willReturn(true).given(luggageMock).isType(CHECKED_LUGGAGE_TYPE);
  }
}
