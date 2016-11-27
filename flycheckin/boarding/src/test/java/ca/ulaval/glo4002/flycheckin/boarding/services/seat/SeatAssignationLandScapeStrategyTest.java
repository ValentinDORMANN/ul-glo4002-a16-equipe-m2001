package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationLandScapeStrategyTest {

  private static final String PASSENGER_SEAT_CLASS = "business";
  private static final String ANOTHER_SEAT_CLASS = "economic";
  private static final String CHEAPER_BEST_VIEW_SEAT_NUMBER = "1A";
  private static final String EXPENSIVE_BEST_VIEW_SEAT_NUMBER = "1D";
  private static final String NEAR_WINDOW_SEAT_NUMBER = "2A";
  private static final String WORST_VIEW_SEAT_NUMBER = "3A";
  private static final double CHEAPEST_PRICE = 200;
  private static final double EXPENSIVE_PRICE = 300;
  private static final int LEGROOM = 10;

  private Seat anotherClassSeat, cheaperBestViewSeat, expensiveBestViewSeat, nearWindowSeat, worstViewSeat;
  private List<Seat> availableSeats;
  private SeatAssignationLandScapeStrategy bestViewSeatAssignation;

  @Before
  public void initiateTest() {
    cheaperBestViewSeat = new Seat(PASSENGER_SEAT_CLASS, CHEAPER_BEST_VIEW_SEAT_NUMBER, LEGROOM, true, true,
        CHEAPEST_PRICE);

    expensiveBestViewSeat = new Seat(PASSENGER_SEAT_CLASS, EXPENSIVE_BEST_VIEW_SEAT_NUMBER, LEGROOM, true, true,
        EXPENSIVE_PRICE);

    nearWindowSeat = new Seat(PASSENGER_SEAT_CLASS, NEAR_WINDOW_SEAT_NUMBER, LEGROOM, true, false, EXPENSIVE_PRICE);

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
  public void givenSeatListWithNoOneHasPassengerSeatClassWhenAssignSeatToPassengerThenReturnException() {
    availableSeats = new ArrayList<Seat>();
    availableSeats.add(anotherClassSeat);

    bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS);
  }

  @Test
  public void test() {
    availableSeats = new ArrayList<Seat>();
    availableSeats.add(anotherClassSeat);
    availableSeats.add(worstViewSeat);

    String assignedSeatNumber = bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS);

    assertEquals(WORST_VIEW_SEAT_NUMBER, assignedSeatNumber);
  }

  /*  @Test
  public void test2() {
    availableSeats = new ArrayList<Seat>();
    availableSeats.add(worstViewSeat);
    availableSeats.add(nearWindowSeat);
  
    String assignedSeatNumber = bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS);
  
    assertEquals(NEAR_WINDOW_SEAT_NUMBER, assignedSeatNumber);
  }
  
  @Test
  public void test3() {
    availableSeats = new ArrayList<Seat>();
    availableSeats.add(worstViewSeat);
    availableSeats.add(nearWindowSeat);
    availableSeats.add(expensiveBestViewSeat);
  
    String assignedSeatNumber = bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS);
  
    assertEquals(EXPENSIVE_BEST_VIEW_SEAT_NUMBER, assignedSeatNumber);
  }
  
  @Test
  public void test4() {
    availableSeats = new ArrayList<Seat>();
    availableSeats.add(nearWindowSeat);
    availableSeats.add(expensiveBestViewSeat);
    availableSeats.add(cheaperBestViewSeat);
  
    String assignedSeatNumber = bestViewSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS);
  
    assertEquals(CHEAPER_BEST_VIEW_SEAT_NUMBER, assignedSeatNumber);
  }*/
}
