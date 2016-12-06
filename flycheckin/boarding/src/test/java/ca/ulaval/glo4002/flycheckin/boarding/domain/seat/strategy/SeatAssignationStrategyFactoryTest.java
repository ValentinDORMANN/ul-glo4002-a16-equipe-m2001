package ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SeatAssignationStrategyFactoryTest {

  private static final String RANDOM_MODE = "RANDOM";
  private static final String CHEAPEST_MODE = "CHEAPEST";
  private static final String LEGROOM_MODE = "LEGS";
  private static final String LANDSCAPE_MODE = "LANDSCAPE";
  private static final String UNDEFINED_MODE = "UNDEFINED";

  private SeatAssignationStrategyFactory seatAssignationStrategyFactory;

  @Before
  public void initiateTest() {
    seatAssignationStrategyFactory = new SeatAssignationStrategyFactory();
  }

  @Test(expected = UndefinedSeatAssignationStrategyException.class)
  public void givenUndefinedModewhenCreateSeatAssignationStrategyThenThrowException() {
    seatAssignationStrategyFactory.createSeatAssignationStrategy(UNDEFINED_MODE);
  }

  @Test
  public void WhenCreateSeatAssignationStrategyWithRandomModeThenReturnSeatAssignationRandomStrategy() {
    SeatAssignationStrategy SeatAssignationStrategy = seatAssignationStrategyFactory.createSeatAssignationStrategy(RANDOM_MODE);

    assertTrue(SeatAssignationStrategy instanceof SeatAssignationRandomStrategy);
  }

  @Test
  public void WhenCreateSeatAssignationStrategyWithCheapestModeThenReturnSeatAssignationCheapestStrategy() {
    SeatAssignationStrategy SeatAssignationStrategy = seatAssignationStrategyFactory.createSeatAssignationStrategy(CHEAPEST_MODE);

    assertTrue(SeatAssignationStrategy instanceof SeatAssignationCheapestStrategy);
  }

  @Test
  public void WhenCreateSeatAssignationStrategyWithLegroomModeThenReturnSeatAssignationLegroomStrategy() {
    SeatAssignationStrategy SeatAssignationStrategy = seatAssignationStrategyFactory.createSeatAssignationStrategy(LEGROOM_MODE);

    assertTrue(SeatAssignationStrategy instanceof SeatAssignationLegroomStrategy);
  }

  @Test
  public void WhenCreateSeatAssignationStrategyWithLandscapeModeThenReturnSeatAssignationLandscapeStrategy() {
    SeatAssignationStrategy SeatAssignationStrategy = seatAssignationStrategyFactory.createSeatAssignationStrategy(LANDSCAPE_MODE);

    assertTrue(SeatAssignationStrategy instanceof SeatAssignationLandScapeStrategy);
  }
}
