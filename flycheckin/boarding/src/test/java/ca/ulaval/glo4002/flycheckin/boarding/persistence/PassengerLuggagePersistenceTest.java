package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;

public class PassengerLuggagePersistenceTest {

  private static final String HASH = "AAA";
  private static final String WRONG_HASH = "BBB";
  private Passenger mockPassenger;
  private PassengerLuggagePersistence passengerLuggagePersistence;

  @Before
  public void initiateTest() {
    passengerLuggagePersistence = new PassengerLuggagePersistence();
    mockPassenger = mock(Passenger.class);
    willReturn(HASH).given(mockPassenger).getPassengerHash();
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenEmptyPersistenceWhenFindPassengerByHashThenThrowException() {
    passengerLuggagePersistence.getPassengerLuggage(HASH);
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenWrongHashWhenGetPassengerAlreadyInMapThenThrowException() {
    passengerLuggagePersistence.savePassengerLuggage(mockPassenger);

    passengerLuggagePersistence.getPassengerLuggage(WRONG_HASH);
  }

  @After
  public void clearMap() {
    passengerLuggagePersistence.clearPassengerMap();
  }

}
