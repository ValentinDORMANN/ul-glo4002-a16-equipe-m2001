package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationLandScapeStrategyTest {

  private static final String PASSENGER_SEAT_CLASS = "business";
  private static final String ANOTHER_SEAT_CLASS = "economic";
  private static final String BEST_VIEW_SEAT_NUMBER = "1A";
  private static final String NEAR_WINDOW_SEAT_NUMBER = "2A";
  private static final String WORST_VIEW_SEAT_NUMBER = "3A";
  private static final double CHEAPEST_PRICE = 200;
  private static final double EXPENSIVE_PRICE = 300;
  private static final int LEGROOM = 10;

  private Seat anotherClassSeat, firstBestViewSeat, secondBestViewSeat, nearWindowSeat, worstViewSeat;
  private List<Seat> availableSeats;
  private SeatAssignationLandScapeStrategy bestViewSeatAssignation;

  @Before
  public void initiateTest() {
    firstBestViewSeat = new Seat(PASSENGER_SEAT_CLASS, BEST_VIEW_SEAT_NUMBER, LEGROOM, true, true, CHEAPEST_PRICE);

    secondBestViewSeat = new Seat(PASSENGER_SEAT_CLASS, BEST_VIEW_SEAT_NUMBER, LEGROOM, true, true, EXPENSIVE_PRICE);

    nearWindowSeat = new Seat(PASSENGER_SEAT_CLASS, NEAR_WINDOW_SEAT_NUMBER, LEGROOM, true, false, CHEAPEST_PRICE);

    worstViewSeat = new Seat(PASSENGER_SEAT_CLASS, WORST_VIEW_SEAT_NUMBER, LEGROOM, false, false, CHEAPEST_PRICE);

    anotherClassSeat = new Seat();
    anotherClassSeat.setSeatClass(ANOTHER_SEAT_CLASS);

    bestViewSeatAssignation = new SeatAssignationLandScapeStrategy();
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenNoSeatAvailableWhenAssignSeatToPassengerThenReturnException() {
    availableSeats = new ArrayList<Seat>();

    bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void test2() {
    availableSeats = new ArrayList<Seat>();
    availableSeats.add(anotherClassSeat);

    bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS);
  }
}
