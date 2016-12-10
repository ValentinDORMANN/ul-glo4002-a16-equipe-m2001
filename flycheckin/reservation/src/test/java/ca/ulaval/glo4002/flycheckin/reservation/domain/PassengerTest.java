package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.rest.dto.PassengerDto;

public class PassengerTest {

	private static final int CHILD_AGE = 10;
	private static final int ADULT_AGE = 30;
	private static final String FIRST_NAME = "FirstName";
	private static final String LAST_NAME = "LastName";
	private static final String PASSPORT_NUMBER = "NUMBER";
	private static final String UNDEFINED_INFO = "";
	private static final boolean IS_VIP = true;

	private PassengerDto passengerDto;
	private PassengerDto otherPassengerDto;
	private Passenger passenger;
	private Passenger otherPassenger;

	@Before
	public void initiateTest() {
		passengerDto = new PassengerDto();
		otherPassengerDto = new PassengerDto();

		passengerDto.age = CHILD_AGE;
		passengerDto.first_name = FIRST_NAME;
		passengerDto.last_name = LAST_NAME;
		passengerDto.passport_number = PASSPORT_NUMBER;
		otherPassengerDto.age = ADULT_AGE;
		otherPassengerDto.first_name = UNDEFINED_INFO;
		otherPassengerDto.last_name = UNDEFINED_INFO;
		otherPassengerDto.passport_number = UNDEFINED_INFO;

		passenger = new Passenger(passengerDto);
		otherPassenger = new Passenger(otherPassengerDto);
	}

	@Test
	public void givenChildPassengerDtoWhenCheckIfChildThenReturnTrue() {
		assertTrue(passenger.isChild());
	}

	@Test
	public void givenAdultPassengerDtoWhenCheckIfChildThenReturnFalse() {
		assertFalse(otherPassenger.isChild());
	}

	@Test
	public void givenPassengerWhenCheckIfIsValidThenReturnTrue() {
		assertTrue(passenger.isValid());
	}

	@Test
	public void givenIncompleteFirstNameWhenCheckIfValidThenReturnFalse() {
		passengerDto.first_name = UNDEFINED_INFO;
		passenger = new Passenger(passengerDto);

		assertFalse(passenger.isValid());
	}

	@Test
	public void givenIncompleteLastNameWhenCheckIfValidThenReturnFalse() {
		passengerDto.last_name = UNDEFINED_INFO;
		passenger = new Passenger(passengerDto);

		assertFalse(passenger.isValid());
	}

	@Test
	public void givenIncompletePassportNumberWhenCheckIfValidThenReturnFalse() {
		passengerDto.passport_number = UNDEFINED_INFO;
		passenger = new Passenger(passengerDto);

		assertFalse(passenger.isValid());
	}

	@Test
	public void givenWrongPassengerWhenCheckIfValidThenReturnFalse() {
		assertFalse(otherPassenger.isValid());
	}

	@Test
	public void givenNewPassengerWhenGetVipStatusThenReturnFalse() {
		assertFalse(passenger.getIsVip());
	}

	@Test
	public void givenVipPassengerWhenGetVipStatusTHenReturnTrue() {
		passenger.changeVipStatus(IS_VIP);

		assertTrue(passenger.getIsVip());
	}
}