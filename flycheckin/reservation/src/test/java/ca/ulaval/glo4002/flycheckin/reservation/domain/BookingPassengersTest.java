package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javassist.NotFoundException;

public class BookingPassengersTest {
	private final String FIRST_NAME = "John";
	private final String LAST_NAME = "Doe";
	private final int AGE = 18;
	private final String PASSPORT_NUMBER = "B1007408";
	private final String SEAT_CLASS = "economy";
	private final String FLIGHT_NUMBER = "AC1765";
	private final String FLIGHT_DATE = "2016-10-30";
	private final String PERSON_HASH = "B1007408:12345";
	private BookingPassengers bookingPassengers;
	private final Passenger PERSON = new Passenger(FIRST_NAME, LAST_NAME, AGE, PASSPORT_NUMBER, SEAT_CLASS,
			PERSON_HASH);

	@Before
	public void InitializeBookingPassengers() throws ParseException {
		List<Passenger> passengers = new ArrayList<Passenger>();
		passengers.add(PERSON);
		bookingPassengers = new BookingPassengers(passengers, FLIGHT_NUMBER, FLIGHT_DATE);
	}

	@Test
	public void GivenValidPassengerHashWhenGetPassengerInfoThenReturnGoodPassenger() throws NotFoundException {
		// When
		Passenger anotherPerson = bookingPassengers.getPassengerInfos(PERSON_HASH);

		// Then
		assertEquals(PERSON_HASH, anotherPerson.getHashCode());
	}
}
