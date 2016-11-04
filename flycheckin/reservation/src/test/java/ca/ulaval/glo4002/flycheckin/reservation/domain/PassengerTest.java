package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.PassengerDto;

public class PassengerTest {

  private static final int CHILD_AGE = 10;
  private static final int ADULT_AGE = 30;
  private static final String FIRST_NAME = "FirstName";
  private static final String LAST_NAME = "LastName";
  private static final String PASSPORT_NUMBER = "NUMBER";
  private static final String EMPTY_STRING = "";
  private Passenger passenger;
  private Passenger fakePassenger;
  private PassengerDto mockPassengerDto;
  private PassengerDto mockWrongPassengerDto;

  @Before
  public void setUp() {
    mockPassengerDto = mock(PassengerDto.class);
    mockWrongPassengerDto = mock(PassengerDto.class);
    mockPassengerDto.age = CHILD_AGE;
    mockPassengerDto.first_name = FIRST_NAME;
    mockPassengerDto.last_name = LAST_NAME;
    mockPassengerDto.passport_number = PASSPORT_NUMBER;
    mockWrongPassengerDto.age = ADULT_AGE;
    mockWrongPassengerDto.first_name = EMPTY_STRING;
    mockWrongPassengerDto.last_name = EMPTY_STRING;
    mockWrongPassengerDto.passport_number = EMPTY_STRING;
    passenger = new Passenger(mockPassengerDto);
    fakePassenger = new Passenger(mockWrongPassengerDto);
  }

  @Test
  public void givenChildPassengerDtoWhenCheckIfChildThenReturnTrue() {
    assertTrue(passenger.isChild());
  }

  @Test
  public void givenAdultPassengerDtoWhenCheckIfChildThenReturnFalse() {
    assertFalse(fakePassenger.isChild());
  }

  @Test
  public void givenPassengerWhenCheckIfIsValidThenReturnTrue() {
    assertTrue(passenger.isValid());
  }

  @Test
  public void givenIncompleteFirstNameWhenCheckIfValidThenReturnFalse() {
    mockPassengerDto.first_name = EMPTY_STRING;
    passenger = new Passenger(mockPassengerDto);
    
    assertFalse(passenger.isValid());
  }

  @Test
  public void givenIncompleteLastNameWhenCheckIfValidThenReturnFalse() {
    mockPassengerDto.last_name = EMPTY_STRING;
    passenger = new Passenger(mockPassengerDto);
    
    assertFalse(passenger.isValid());
  }

  @Test
  public void givenIncompletePassportNumberWhenCheckIfValidThenReturnFalse() {
    mockPassengerDto.passport_number = EMPTY_STRING;
    passenger = new Passenger(mockPassengerDto);
    
    assertFalse(passenger.isValid());
  }

  @Test
  public void givenWrongPassengerWhenCheckIfValidThenReturnFalse() {
    assertFalse(fakePassenger.isValid());
  }
}
