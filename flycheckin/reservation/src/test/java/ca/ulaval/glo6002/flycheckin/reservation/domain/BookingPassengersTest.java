package ca.ulaval.glo6002.flycheckin.reservation.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookingPassengersTest {
	private String FIRST_NAME = "John";
	private String LAST_NAME = "Doe";
	private int AGE = 18;
	private String PASSPORT_NUMBER = "B10074008";
	private String SEAT_CLASS = "economy";
	private String FLIGHT_NUMBER = "AC1765";
	private BookingPassengers booking_passengers;

	@Before
	public void InitializeBookingPaasengers() {
		this.booking_passengers = new BookingPassengers(this.FIRST_NAME, this.LAST_NAME, this.AGE, this.PASSPORT_NUMBER,
				this.SEAT_CLASS, this.FLIGHT_NUMBER);
	}

	@Test
	public void lookIfFirstNameIsTheSame() {
		assertEquals(this.FIRST_NAME, this.booking_passengers.getFirstName());
	}

	@Test
	public void lookIfLastNameIsTheSame() {
		assertEquals(this.LAST_NAME, this.booking_passengers.getLastName());
	}

	@Test
	public void lookIfAgeIsTheSame() {
		assertEquals(this.AGE, this.booking_passengers.getAge());
	}

	@Test
	public void lookIfPassportNumberIsTheSame() {
		assertEquals(this.PASSPORT_NUMBER, this.booking_passengers.getPassportNumber());
	}

	@Test
	public void lookIfSeatClassIsTheSame() {
		assertEquals(this.SEAT_CLASS, this.booking_passengers.getSeatClass());
	}

	@Test
	public void lookIfFlightNumberIsTheSame() {
		assertEquals(this.FLIGHT_NUMBER, this.booking_passengers.getFlightNumber());
	}

}
