package ca.ulaval.glo4002.flycheckin.boarding.domain;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededCheckedLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PassengerDto;

public class PassengerTest {

  private static final String FLIGHT_NUMBER = "A320";
  private static final Date FLIGHT_DATE = new Date();
  private static final String SEAT_CLASS = "business";
  private static final String PASSENGER_HASH = "PassengerHash";
  private static final String TYPE_CHECKED = "checked";
  private static final int LIMIT_CHECKED_LUGGAGES = 3;
  private static final double BASE_PRICE = 0;
  private static final double SURPLUS_PRICE_ONE_LUGGAGE = 50;
  private static final double SURPLUS_PRICE_TWO_LUGGAGE = 100;
  private static final double DELTA = 0.01;
  private static final boolean IS_CHECKED = true;

  private Passenger passenger;
  private PassengerDto mockPassengerDto;
  private Luggage mockCheckedLuggage;
  private Luggage mockExceededCheckedLuggage;

  @Before
  public void initiateTest() {
    mockPassengerDto = mock(PassengerDto.class);
    mockPassengerDto.seat_class = SEAT_CLASS;
    mockPassengerDto.passenger_hash = PASSENGER_HASH;
    passenger = new Passenger(FLIGHT_NUMBER, FLIGHT_DATE, PASSENGER_HASH, SEAT_CLASS);
    mockCheckedLuggage = mock(Luggage.class);
    mockExceededCheckedLuggage = mock(Luggage.class);
    willReturn(IS_CHECKED).given(mockCheckedLuggage).isType(TYPE_CHECKED);
    willReturn(BASE_PRICE).given(mockCheckedLuggage).getPrice();
    willReturn(SURPLUS_PRICE_ONE_LUGGAGE).given(mockExceededCheckedLuggage).getPrice();
  }

  @Test
  public void whenAddThanThreeCheckedLuggagesThenVerifyPassengersHasThreeLuggages() {
    for (int index = 0; index < LIMIT_CHECKED_LUGGAGES; index++) {
      passenger.addLuggage(mockCheckedLuggage);
    }

    assertEquals(LIMIT_CHECKED_LUGGAGES, passenger.getLuggages().size());
  }

  @Test(expected = ExcededCheckedLuggageException.class)
  public void whenAddMoreThanThreeCheckedLuggagesThenThrowException() {
    for (int index = 0; index < LIMIT_CHECKED_LUGGAGES; index++) {
      passenger.addLuggage(mockCheckedLuggage);
    }

    passenger.addLuggage(mockCheckedLuggage);
  }

  @Test
  public void givenAnyLuggageWhenGetTotalPriceThenReturnBasePrice() {
    double price = passenger.getTotalPrice();

    assertEquals(BASE_PRICE, price, DELTA);
  }

  @Test
  public void givenOneLuggageWhenGetTotalPriceThenReturnBasePrice() {
    passenger.addLuggage(mockCheckedLuggage);

    double price = passenger.getTotalPrice();

    assertEquals(BASE_PRICE, price, DELTA);
  }

  @Test
  public void givenTwoLuggageWhenGetTotalPriceThenReturnOneSurplusPrice() {
    passenger.addLuggage(mockCheckedLuggage);
    passenger.addLuggage(mockExceededCheckedLuggage);

    double price = passenger.getTotalPrice();

    assertEquals(SURPLUS_PRICE_ONE_LUGGAGE, price, DELTA);
  }

  @Test
  public void givenThreeLuggageWhenGetTotalPriceThenReturnOneSurplusPrice() {
    passenger.addLuggage(mockCheckedLuggage);
    passenger.addLuggage(mockExceededCheckedLuggage);
    passenger.addLuggage(mockExceededCheckedLuggage);

    double price = passenger.getTotalPrice();

    assertEquals(SURPLUS_PRICE_TWO_LUGGAGE, price, DELTA);
  }
}
