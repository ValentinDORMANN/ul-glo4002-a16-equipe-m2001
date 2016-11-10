package ca.ulaval.glo4002.flycheckin.boarding.services.interne;

import java.util.List;

import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.Seat;

public class SeatAssignationCheapestStrategyTest {

  private static final int SEAT_ROW = 16;
  private static final String SEAT_COLUMN = "B";
  private static final String SEAT_CLASS = "economic";
  private static final String ANOTHER_SEAT_CLASS = "wrongSeatClass";
  private Seat seat;
  private List<Seat> availableSeats;
  private SeatAssignationCheapestStrategy cheapestSeatAssignation;

  @Test
  public void test() {
    // TODO fail("Not yet implemented");
  }

  @Test
  public void givenEmptySeatListWhenAssignSeatThenReturnException() {

  }

}
