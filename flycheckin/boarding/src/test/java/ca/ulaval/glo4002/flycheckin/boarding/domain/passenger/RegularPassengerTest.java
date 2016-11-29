package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import java.util.Date;

import org.junit.Before;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.Luggage;

public class RegularPassengerTest {

  private static final String FLIGHT_NUMBER = "AAAA";
  private static final Date FLIGHT_DATE = new Date();
  private static final String HASH = "hash";
  private static final String ECONOMY = "economy";
  private static final boolean VIP_STATUS = false;
  private static final double DELTA = 0.01;

  private Luggage luggageMock;

  private RegularPassenger regularPassenger;

  @Before
  public void initiateTest() {
    regularPassenger = new RegularPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, ECONOMY, VIP_STATUS);
  }

  public void test1() {

  }

  public void test2() {

  }

  public void test3() {

  }

  public void test4() {

  }

  public void test5() {

  }

  public void test6() {

  }

  public void test7() {

  }

  public void test8() {

  }

  private Luggage simulateStandardCarryOnLuggage(Luggage luggage) {
    return luggage;
  }

  private Luggage simulateUnusualCarryOnLuggage(Luggage luggage) {
    return luggage;
  }

  private Luggage simulateStandardCheckedLuggage(Luggage luggage) {
    return luggage;
  }

  private Luggage simulateUnusualCheckedLuggage(Luggage luggage) {
    return luggage;
  }
}
