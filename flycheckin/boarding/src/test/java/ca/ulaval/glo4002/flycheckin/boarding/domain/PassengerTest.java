package ca.ulaval.glo4002.flycheckin.boarding.domain;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PassengerDto;

public class PassengerTest {

  private static final String FLIGHT_NUMBER = "A320";
  private static final Date FLIGHT_DATE = new Date();
  private static final String SEAT_CLASS = "business";
  private static final String PASSENGER_HASH = "PassengerHash";
  private static final String TYPE_CHECKED = "checked";
  private static final String TYPE_CARRY_ON = "carry-on";
  private static final int LIMIT_CHECKED_LUGGAGES = 3;
  private static final boolean IS_CHECKED = true;

  private Passenger passenger;
  private PassengerDto mockPassengerDto;
  private Luggage mockCheckedLuggage;
  private Luggage mockCarryOnLuggage;

  @Before
  public void initiateTest() {
    mockPassengerDto = mock(PassengerDto.class);
    mockPassengerDto.seat_class = SEAT_CLASS;
    mockPassengerDto.passenger_hash = PASSENGER_HASH;
    mockCheckedLuggage = mock(CheckedLuggage.class);
    mockCarryOnLuggage = mock(CarryOnLuggage.class);
    passenger = new Passenger(FLIGHT_NUMBER, FLIGHT_DATE, PASSENGER_HASH, SEAT_CLASS);
    mockCheckedLuggage = mock(Luggage.class);
    willReturn(IS_CHECKED).given(mockCheckedLuggage).isType(TYPE_CHECKED);
    willReturn(IS_CHECKED).given(mockCarryOnLuggage).isType(TYPE_CARRY_ON);
  }

  @Test
  public void whenAddThanThreeCheckedLuggagesThenVerifyPassengersHasThreeLuggages() {
    for (int index = 0; index < LIMIT_CHECKED_LUGGAGES; index++) {
      passenger.addLuggage(mockCheckedLuggage);
    }

    assertEquals(LIMIT_CHECKED_LUGGAGES, passenger.getLuggages().size());
  }

  @Test(expected = ExcededLuggageException.class)
  public void whenAddMoreThanThreeCheckedLuggagesThenThrowException() {
    for (int index = 0; index < LIMIT_CHECKED_LUGGAGES; index++) {
      passenger.addLuggage(mockCheckedLuggage);
    }

    passenger.addLuggage(mockCheckedLuggage);
  }
  
  @Test
  public void whenAddOneCarryOnLuggageThenVerifyPassengerHasOneLuggage() {
    passenger.addLuggage(mockCarryOnLuggage);
    
    assertEquals(1, passenger.getLuggages().size());
  }
  
  @Test(expected = ExcededLuggageException.class)
  public void whenAddMoreThanOneCarryOnLuggageThenThenThrowException() {
    passenger.addLuggage(mockCarryOnLuggage);
    passenger.addLuggage(mockCarryOnLuggage);
  }
}
