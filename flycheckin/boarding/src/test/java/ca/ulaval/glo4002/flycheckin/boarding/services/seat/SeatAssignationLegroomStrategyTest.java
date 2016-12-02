package ca.ulaval.glo4002.flycheckin.boarding.services.seat;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.domain.seat.Seat;
import ca.ulaval.glo4002.flycheckin.boarding.exception.NoSeatAvailableException;

public class SeatAssignationLegroomStrategyTest {
  private static final double SMALL_PRICE = 100.00;
  private static final double MEDIUM_PRICE = 150.00;
  private static final double LARGE_PRICE = 200.00;
  private static final double XLARGE_PRICE = 300.00;
  private static final int SMALL_LEGROOM = 38;
  private static final int MEDIUM_LEGROOM = 56;
  private static final int LARGE_LEGROOM = 90;
  private static final String SMALL_SEAT_NUMBER = "16-B";
  private static final String MEDIUM_SEAT_NUMBER = "10-K";
  private static final String LARGE_SEAT_NUMBER = "7-A";
  private static final String XLARGE_SEAT_NUMBER = "7-C";

  private static final String PASSENGER_SEAT_CLASS = "business";
  private static final String ANOTHER_SEAT_CLASS = "big-front";
  private Seat smallSeat, mediumSeat, largeSeat, xLargeSeat;
  private List<Seat> availableSeats;
  private SeatAssignationLegroomStrategy legroomSeatAssignation;

  @Before
  public void initiateTest() {
    smallSeat = new Seat();
    smallSeat.setPrice(SMALL_PRICE);
    smallSeat.setLegroom(SMALL_LEGROOM);
    smallSeat.setSeatNumber(SMALL_SEAT_NUMBER);
    smallSeat.setSeatClass(PASSENGER_SEAT_CLASS);
    mediumSeat = new Seat();
    mediumSeat.setPrice(MEDIUM_PRICE);
    mediumSeat.setLegroom(MEDIUM_LEGROOM);
    mediumSeat.setSeatNumber(MEDIUM_SEAT_NUMBER);
    mediumSeat.setSeatClass(PASSENGER_SEAT_CLASS);
    largeSeat = new Seat();
    largeSeat.setPrice(LARGE_PRICE);
    largeSeat.setLegroom(LARGE_LEGROOM);
    largeSeat.setSeatNumber(LARGE_SEAT_NUMBER);
    largeSeat.setSeatClass(PASSENGER_SEAT_CLASS);
    xLargeSeat = new Seat();
    xLargeSeat.setPrice(XLARGE_PRICE);
    xLargeSeat.setLegroom(LARGE_LEGROOM);
    xLargeSeat.setSeatNumber(XLARGE_SEAT_NUMBER);
    xLargeSeat.setSeatClass(PASSENGER_SEAT_CLASS);
    availableSeats = new ArrayList<Seat>();
    legroomSeatAssignation = new SeatAssignationLegroomStrategy();
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenEmptyAvailableSeatListWhenAssignSeatToPassengerThenReturnException() {
    legroomSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS);
  }

  @Test(expected = NoSeatAvailableException.class)
  public void givenAvailableSeatListOfSameClassWhenAssignSeatWithAnotherClassThenReturnException() {
    availableSeats.add(smallSeat);

    legroomSeatAssignation.assignSeatNumber(availableSeats, ANOTHER_SEAT_CLASS);
  }

  @Test
  public void givenAvailableSeatsListWithDifferentLegroomSizeWhenAssignSeatThenReturnLargestLegroomSeat() {
    availableSeats.add(smallSeat);
    availableSeats.add(mediumSeat);
    availableSeats.add(xLargeSeat);

    String seatNumber = legroomSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS);

    assertEquals(XLARGE_SEAT_NUMBER, seatNumber);
  }

  @Test
  public void givenManySeatsWithTheGreaterLegroomWhenAssignSeatThenReturnTheCheapestSeatBetweenThem() {
    availableSeats.add(smallSeat);
    availableSeats.add(mediumSeat);
    availableSeats.add(xLargeSeat);
    availableSeats.add(largeSeat);

    String seatNumber = legroomSeatAssignation.assignSeatNumber(availableSeats, PASSENGER_SEAT_CLASS);

    assertEquals(LARGE_SEAT_NUMBER, seatNumber);
  }
}
