package ca.ulaval.glo4002.flycheckin.boarding.domain.seat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SeatTest {

  private static final String SEAT_CLASS = "class";
  private static final String SEAT_NUMBER = "12-A";
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
    seat = new Seat(SEAT_CLASS, SEAT_NUMBER, LEGROOM, true, true, false, NORMAL_PRICE);
  }

  @Test
  public void givenSeatWhenVerifyIfCheaperThanAnotherSeatMoreExpensiveThenReturnTrue() {
    Seat otherSeat = new Seat();
    otherSeat.setPrice(EXPENSIVE_PRICE);

    assertTrue(seat.isCheaperThan(otherSeat));
  }

  @Test
  public void givenSeatWhenVerifyIfCheaperThanAnotherSeatWithSamePriceThenReturnFalse() {
    Seat otherSeat = new Seat();
    otherSeat.setPrice(NORMAL_PRICE);

    assertFalse(seat.isCheaperThan(otherSeat));
  }

  @Test
  public void givenSeatWhenVerifyIfCheaperThanAnotherSeatLessExpensiveThenReturnFalse() {
    Seat otherSeat = new Seat();
    otherSeat.setPrice(CHEAPER_PRICE);

    assertFalse(seat.isCheaperThan(otherSeat));
  }

  @Test
  public void givenSeatWhenVerifyIfLegroomGreaterThanAnotherSeatWithMoreLegroomThenReturnFalse() {
    Seat otherSeat = new Seat();
    otherSeat.setLegroom(MORE_LEGROOM);

    assertFalse(seat.isLegroomGreaterThan(otherSeat));
  }

  @Test
  public void givenSeatWhenVerifyIfLegroomGreaterThanAnotherSeatWithSameLegroomThenReturnFalse() {
    Seat otherSeat = new Seat();
    otherSeat.setLegroom(LEGROOM);

    assertFalse(seat.isLegroomGreaterThan(otherSeat));
  }

  @Test
  public void givenSeatWhenVerifyIfLegroomGreaterThanAnotherLessLegroomThenReturnTrue() {
    Seat otherSeat = new Seat();
    otherSeat.setLegroom(LESS_LEGROOM);

    assertTrue(seat.isLegroomGreaterThan(otherSeat));
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

  @Test
  public void givenSeatWhenVerifyIfSeatHasSameLegroomWithAnotherSeatWithSameLegroomThenReturnTrue() {
    Seat otherSeat = new Seat();
    otherSeat.setLegroom(LEGROOM);

    assertTrue(seat.hasSameLegroomWith(otherSeat));
  }

  @Test
  public void givenSeatWhenVerifyIfSeatHasSameLegroomWithAnotherSeatWithLessLegroomThenReturnFalse() {
    Seat otherSeat = new Seat();
    otherSeat.setLegroom(LESS_LEGROOM);

    assertFalse(seat.hasSameLegroomWith(otherSeat));
  }

  @Test
  public void givenSeatWhenVerifyIfSeatHasSameLegroomWithAnotherSeatWithMoreLegroomThenReturnFalse() {
    Seat otherSeat = new Seat();
    otherSeat.setLegroom(MORE_LEGROOM);

    assertFalse(seat.hasSameLegroomWith(otherSeat));
  }

  @Test
  public void givenSeatWithClearViewWhenCheckIsClearViewThenReturnTrue() {
    boolean isClearView = seat.isClearView();

    assertTrue(isClearView);
  }

  @Test
  public void givenSeatNearWindowWhenCheckIsNearWindowThenReturnTrue() {
    boolean windowView = seat.isNearWindow();

    assertTrue(windowView);
  }

  @Test
  public void givenSeatNotInExitRowWhenCheckIsExitRowThenReturnFalse() {
    boolean isExitRow = seat.isExitRow();

    assertFalse(isExitRow);
  }
}
