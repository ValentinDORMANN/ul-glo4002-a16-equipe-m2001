package ca.ulaval.glo4002.flycheckin.reservation.persistence;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckinInMemoryTest {

  private static final String PASSENGER_HASH = "acb15 de26f 4mf99z";
  private int checkinNumber;
  private CheckinInMemory checkinMemory;

  @Test
  public void givenEmptyMemoryWhenDoCheckinThenReturnCheckinNumber() {
    checkinMemory = new CheckinInMemory();

    checkinNumber = checkinMemory.doPassengerCheckin(PASSENGER_HASH);

    assertTrue(checkinNumber > 100);
  }
}
