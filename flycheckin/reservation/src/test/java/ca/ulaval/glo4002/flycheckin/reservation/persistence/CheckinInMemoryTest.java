package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.exception.ReservationModuleException;

public class CheckinInMemoryTest {

  private static final int CHECKIN_LIST_LIMIT = 100;
  private static final String PASSENGER_HASH = "acb15 de26f 4mf99z";
  private static final String PASSENGER_HASH2 = "jfd45 d4g5f 48dfz";
  private int checkinNumber;
  private CheckinInMemory checkinMemory;

  @Before
  public void initiateTest() {
    checkinMemory = new CheckinInMemory();
  }

  @Test
  public void givenEmptyMemoryWhenDoCheckinThenReturnCheckinNumber() {
    checkinNumber = checkinMemory.doPassengerCheckin(PASSENGER_HASH);

    assertTrue(checkinNumber > CHECKIN_LIST_LIMIT);
  }

  @Test(expected = ReservationModuleException.class)
  public void givenPassengerAlreadyCheckinWhenDoCheckinThenReturnException() {
    checkinMemory.doPassengerCheckin(PASSENGER_HASH2);

    checkinMemory.doPassengerCheckin(PASSENGER_HASH2);
  }
}
