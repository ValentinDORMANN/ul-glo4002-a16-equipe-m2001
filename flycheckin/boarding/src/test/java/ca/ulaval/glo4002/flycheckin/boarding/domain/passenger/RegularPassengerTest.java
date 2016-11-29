package ca.ulaval.glo4002.flycheckin.boarding.domain.passenger;

import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;

import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CarryOnLuggage;
import ca.ulaval.glo4002.flycheckin.boarding.domain.luggage.CheckedLuggage;

public class RegularPassengerTest {

  private static final String FLIGHT_NUMBER = "AAAA";
  private static final Date FLIGHT_DATE = new Date();
  private static final String HASH = "hash";
  private static final String ECONOMY = "economy";
  private static final boolean VIP_STATUS = false;
  private static final double DELTA = 0.01;

  private CarryOnLuggage carryOnLuggageMock;
  private CheckedLuggage checkedLuggageMock;

  private RegularPassenger regularPassenger;

  @Before
  public void initiateTest() {
    carryOnLuggageMock = mock(CarryOnLuggage.class);
    checkedLuggageMock = mock(CheckedLuggage.class);
    regularPassenger = new RegularPassenger(FLIGHT_NUMBER, FLIGHT_DATE, HASH, ECONOMY, VIP_STATUS);
  }
}
