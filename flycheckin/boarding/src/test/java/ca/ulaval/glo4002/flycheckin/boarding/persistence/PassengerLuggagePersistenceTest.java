package ca.ulaval.glo4002.flycheckin.boarding.persistence;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.passenger.Passenger;

public class PassengerLuggagePersistenceTest {

  private static final String HASH = "AAA";
  private static final String WRONG_HASH = "BBB";
  private Passenger passengerMock;
  private PassengerLuggagePersistence passengerLuggagePersistence;

  @Before
  public void initiateTest() {
    passengerLuggagePersistence = new PassengerLuggagePersistence();
    passengerMock = mock(Passenger.class);
    willReturn(HASH).given(passengerMock).getPassengerHash();
  }

  @Test
  public void givenEmptyPersistenceWhenFindPassengerByHashThenThrowException() {
    List<Luggage> luggages = passengerLuggagePersistence.getPassengerLuggage(HASH);

    assertTrue(luggages.isEmpty());
  }

  /*  @Test(expected = NotFoundPassengerException.class)
  public void givenRealPassengerWhenGetPassengerWithLuggageAssignedThenReturnNotEmptyLuggageList() {
    willReturn(HASH).given(passengerMock).getPassengerHash();
    passengerLuggagePersistence.savePassengerLuggage(passengerMock);
  
    List<Luggage> luggages = passengerLuggagePersistence.getPassengerLuggage(HASH);
  
    assertFalse(luggages.isEmpty());
  }*/

  @After
  public void clearMap() {
    passengerLuggagePersistence.clearPassengerMap();
  }

}
