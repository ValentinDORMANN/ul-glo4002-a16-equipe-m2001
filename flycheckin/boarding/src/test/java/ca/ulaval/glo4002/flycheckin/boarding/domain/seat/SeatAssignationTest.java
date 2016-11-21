package ca.ulaval.glo4002.flycheckin.boarding.domain.seat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.SeatAssignation;

public class SeatAssignationTest {

  private static final String WRONG_HASH = "wrong";
  private static final String HASH = "hash";
  private static final String SEAT_NUMBER = "12-A";
  private static final int ASSIGNATION_NUMBER = 1;
  private SeatAssignation seatAssignation;

  @Before
  public void initiateTest() {
    seatAssignation = new SeatAssignation();
    seatAssignation.createAssignation(SEAT_NUMBER, HASH, ASSIGNATION_NUMBER);
  }

  @Test
  public void givenPassengerHashWhenCompareAssignationSeatPassengerHashThenReturnTrue() {
    assertTrue(seatAssignation.isAssociateToThisHash(HASH));
  }

  @Test
  public void givenOtherPassengerHashWhenCompareAssignationSeatPassengerHashThenReturnFalse() {
    assertFalse(seatAssignation.isAssociateToThisHash(WRONG_HASH));
  }
}
