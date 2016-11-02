package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.api.dto.PassengerDto;

public class PassengerTest {

  private static final int CHILD_AGE = 14;
  private static final int ADULT_AGE = 15;
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
  public void givenChildPassengerDto_thenBackTrue() {
    assertTrue(passenger.isChild());
  }

  @Test
  public void givenAdultdPassengerDto_thenBackFalse() {
    assertFalse(fakePassenger.isChild());
  }

  @Test
  public void givenGoodPassenger_whenIsValid_thenBackTrue() {
    assertTrue(passenger.isValid());
  }

  @Test
  public void givenIncompleteFirstNamePassenger_whenIsValid_thenBackFalse() {
    mockPassengerDto.first_name = EMPTY_STRING;
    passenger = new Passenger(mockPassengerDto);
    assertFalse(passenger.isValid());
  }

  @Test
  public void givenIncompleteLastNamePassenger_whenIsValid_thenBackFalse() {
    mockPassengerDto.last_name = EMPTY_STRING;
    passenger = new Passenger(mockPassengerDto);
    assertFalse(passenger.isValid());
  }

  @Test
  public void givenIncompletePassportNumberPassenger_whenIsValid_thenBackFalse() {
    mockPassengerDto.passport_number = EMPTY_STRING;
    passenger = new Passenger(mockPassengerDto);
    assertFalse(passenger.isValid());
  }

  @Test
  public void givenWrongPassenger_whenIsValid_thenBackFalse() {
    assertFalse(fakePassenger.isValid());
  }
}
