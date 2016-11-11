package ca.ulaval.glo4002.flycheckin.boarding.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SeatTest {

  private static final int ROW = 13;
  private static final String COLUMN = "d";
  private static final String SEAT_CLASS = "class";
  private static final String OTHER_SEAT_CLASS = "wrongClass";
  private static final double NORMAL_PRICE = 125.00;
  private static final double CHEAPER_PRICE = 75.00;
  private static final double EXPENSIVE_PRICE = 175.00;
  private static final int LESS_LEGROOM = 38;
  private static final int LEGROOM = 58;
  private static final int MORE_LEGROOM = 78;
  private Seat seat;

  @Before
  public void initiateTest() {
    seat = new Seat();
    seat.setSeatClass(SEAT_CLASS);
    seat.setPrice(NORMAL_PRICE);
    seat.setLegroom(LEGROOM);
  }

  @Test
  public void givenSeatWhenVerifyIfCheaperThanAnotherMoreExpensiveThenReturnTrue() {
    Seat otherSeat = new Seat();
    otherSeat.setPrice(EXPENSIVE_PRICE);

    assertTrue(seat.isCheaperThan(otherSeat));
  }

  @Test
  public void givenSeatWhenVerifyIfCheaperThanAnotherWithSamePriceThenReturnFalse() {
    Seat otherSeat = new Seat();
    otherSeat.setPrice(NORMAL_PRICE);

    assertFalse(seat.isCheaperThan(otherSeat));
  }

  @Test
  public void givenSeatWhenVerifyIfCheaperThanAnotherLessExpensiveThenReturnFalse() {
    Seat otherSeat = new Seat();
    otherSeat.setPrice(CHEAPER_PRICE);

    assertFalse(seat.isCheaperThan(otherSeat));
  }

  @Test
  public void givenSeatWhenVerifyIfSpaciousThanAnotherMoreLegroomThenReturnFalse() {
    Seat otherSeat = new Seat();
    otherSeat.setLegroom(MORE_LEGROOM);

    assertFalse(seat.isSpaciousThan(otherSeat));
  }

  @Test
  public void givenSeatWhenVerifyIfSpaciousThanAnotherWithSameLegroomThenReturnFalse() {
    Seat otherSeat = new Seat();
    otherSeat.setLegroom(LEGROOM);

    assertFalse(seat.isSpaciousThan(otherSeat));
  }

  @Test
  public void givenSeatWhenVerifyIfSpaciousThanAnotherLessLegroomThenReturnTrue() {
    Seat otherSeat = new Seat();
    otherSeat.setLegroom(LESS_LEGROOM);

    assertTrue(seat.isSpaciousThan(otherSeat));
  }

  @Test
  public void givenGoodSeatClassWhenVerifyIfSeatHasThisClassThenReturnTrue() {
    boolean hasClass = seat.hasClass(SEAT_CLASS);

    assertTrue(hasClass);
  }

  @Test
  public void givenWrongSeatClassWhenVerifyIfSeatHasThisClassThenReturnFalse() {
    boolean hasClass = seat.hasClass(OTHER_SEAT_CLASS);

    assertFalse(hasClass);
  }
}
