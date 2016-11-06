package ca.ulaval.glo4002.flycheckin.boarding.domain;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.boarding.exception.ExcededCheckedLuggageException;
import ca.ulaval.glo4002.flycheckin.boarding.rest.dto.PassengerDto;

public class PassengerTest {

  private static final String FIRSTNAME = "Firstname";
  private static final String LASTNAME = "LastName";
  private static final String PASSPORT_NUMBER = "NUMBER";
  private static final String SEAT_CLASS = "business";
  private static final boolean CHILD = false;
  private static final String PASSENGER_HASH = "PassengerHash";
  private static final String TYPE_CHECKED = "checked";
  private static final int LIMIT_CHECKED_LUGGAGES = 3;
  private static final boolean IS_CHECKED = true;
  
  private Passenger passenger;
  private PassengerDto mockPassengerDto;
  private Luggage mockLuggage;
  
  @Before
  public void initiateTest() {
    mockPassengerDto = mock(PassengerDto.class);
    mockPassengerDto.first_name = FIRSTNAME;
    mockPassengerDto.last_name = LASTNAME;
    mockPassengerDto.passport_number = PASSPORT_NUMBER;
    mockPassengerDto.seat_class = SEAT_CLASS;
    mockPassengerDto.child = CHILD;
    mockPassengerDto.passenger_hash = PASSENGER_HASH;
    passenger = new Passenger(mockPassengerDto);
    mockLuggage = mock(Luggage.class);
    willReturn(IS_CHECKED).given(mockLuggage).isType(TYPE_CHECKED);
  }
  
  @Test(expected = ExcededCheckedLuggageException.class)
  public void givenManyLuggageWhenAddItThenPassengerHaveIt() {
    for(int i = 0; i <= LIMIT_CHECKED_LUGGAGES; i++) {
      passenger.addLuggage(mockLuggage);
    }
  }
}
