package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NotFoundPassengerException;

public class InMemoryPassengerTest {

  private static final String HASH = "AAA";
  private static final String WRONG_HASH = "BBB";
  private Passenger mockPassenger;
  private InMemoryPassenger inMemoryPassenger;

  @Before
  public void initiateTest() {
    inMemoryPassenger = new InMemoryPassenger();
    mockPassenger = mock(Passenger.class);
    willReturn(HASH).given(mockPassenger).getPassengerHash();
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenEmptyPersistenceWhenFindPassengerByHashThenThrowException() {
    inMemoryPassenger.getPassengerByHash(HASH);
  }

  @Test
  public void givenPassengerHashInPersistenceWhenGetPassengerByHashThenReturnIt() {
    inMemoryPassenger.savePassenger(mockPassenger);

    Passenger passenger = inMemoryPassenger.getPassengerByHash(HASH);

    assertEquals(mockPassenger, passenger);
  }

  @Test(expected = NotFoundPassengerException.class)
  public void givenWrongHashWhenGetPassengerAlreadyInMapThenThrowException() {
    inMemoryPassenger.savePassenger(mockPassenger);

    inMemoryPassenger.getPassengerByHash(WRONG_HASH);
  }

  @After
  public void clearMap() {
    inMemoryPassenger.clearPassengerMap();
  }

}
