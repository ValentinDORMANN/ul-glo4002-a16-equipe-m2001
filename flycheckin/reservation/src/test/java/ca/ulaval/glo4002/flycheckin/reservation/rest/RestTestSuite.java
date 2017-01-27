package ca.ulaval.glo4002.flycheckin.reservation.rest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.ulaval.glo4002.flycheckin.reservation.ReservationServer;

@RunWith(Suite.class)
@SuiteClasses({ ReservationResourceRestTest.class })
public class RestTestSuite {

  public static final int TEST_SERVER_PORT = 9292;

  private static ReservationServer reservationServer;

  @BeforeClass
  public static void setUpClass() {
    reservationServer = new ReservationServer();
    reservationServer.start(TEST_SERVER_PORT);
  }

  @AfterClass
  public static void tearDownClass() {
    reservationServer.stop();
  }
}
