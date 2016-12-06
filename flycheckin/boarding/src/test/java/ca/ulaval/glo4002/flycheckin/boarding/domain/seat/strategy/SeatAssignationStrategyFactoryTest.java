package ca.ulaval.glo4002.flycheckin.boarding.domain.seat.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SeatAssignationStrategyFactoryTest {
  
  private static final String RANDOM_MODE = "RANDOM";
  private static final String UNDEFINED_MODE = "UNDEFINED";
  
  private SeatAssignationStrategyFactory seatAssignationStrategyFactory;
  
  @Before
  public void initiateTest() {
    seatAssignationStrategyFactory = new SeatAssignationStrategyFactory();
  }
  
  @Test(expected = UndefinedSeatAssignationStrategyException.class)
  public void givenUndefinedModewhenCreateSeatAssignationStrategyThenThrowException() {
    SeatAssignationStrategy SeatAssignationStrategy = seatAssignationStrategyFactory.createSeatAssignationStrategy(UNDEFINED_MODE);
  }
  
  @Test
  public void givenRandomModewhenCreateSeatAssignationStrategyThenReturnSeatAssignationRandomStrategy() {
    SeatAssignationStrategy SeatAssignationStrategy = seatAssignationStrategyFactory.createSeatAssignationStrategy(RANDOM_MODE);
    
    assertTrue(SeatAssignationStrategy instanceof SeatAssignationRandomStrategy);
  }
}
