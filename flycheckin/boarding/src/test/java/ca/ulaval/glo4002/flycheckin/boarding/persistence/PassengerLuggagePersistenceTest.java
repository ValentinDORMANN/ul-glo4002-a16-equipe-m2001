package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
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
    passengerLuggagePersistence.getPassengerByHash(HASH);
  }

  @Test
  public void givenPassengerHashInPersistenceWhenGetPassengerByHashThenReturnIt() {
    passengerLuggagePersistence.savePassengerLuggage(mockPassenger);

    Passenger passenger = passengerLuggagePersistence.getPassengerByHash(HASH);

    assertEquals(mockPassenger, passenger);
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenWrongHashWhenGetPassengerAlreadyInMapThenThrowException() {
    passengerLuggagePersistence.savePassengerLuggage(mockPassenger);

    passengerLuggagePersistence.getPassengerByHash(WRONG_HASH);
  }

  @After
  public void clearMap() {
    passengerLuggagePersistence.clearPassengerMap();
  }

}
