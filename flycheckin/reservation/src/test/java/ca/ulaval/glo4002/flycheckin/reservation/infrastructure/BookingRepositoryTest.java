package ca.ulaval.glo4002.flycheckin.reservation.infrastructure;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Booking;
import ca.ulaval.glo4002.flycheckin.reservation.domain.BookingPassengers;
import ca.ulaval.glo4002.flycheckin.reservation.domain.Passenger;

//import ca.ulaval.glo6002.flycheckin.reservation.domain.Booking;
//import ca.ulaval.glo6002.flycheckin.reservation.domain.BookingPassengers;

public class BookingRepositoryTest {
	private final int VALID_KEY = 44444;
	private final int INVALID_KEY = 99999;
	private final Booking BOOKING_TST = new Booking(VALID_KEY, "date", "confirm", "paymentLoc");
	private final Passenger PERSON = new Passenger("Taylor", "Mahugnon", 12, "B0074584", "economic", "B0074584:AF-305:1121");
	BookingPassengers BOOKING_PASSENGERS_TST;
	private int checkValue;
	private BookingRepository bookingRepo;

	// Booking mockBooking = mock(Booking.class);

	@Before
	public void initiateTest() throws ParseException {
		bookingRepo = new BookingRepository();
		List<Passenger> listPassengers = new ArrayList<Passenger>();
		listPassengers.add(PERSON);
		BOOKING_PASSENGERS_TST = new BookingPassengers(listPassengers, "AF-305", "2016-11-21");
		checkValue = bookingRepo.saveNewBooking(BOOKING_TST, BOOKING_PASSENGERS_TST);
	}

	@Test
	public void WhenSavedNewBookingThenReturnBookingNumber() {
		assertEquals(VALID_KEY, checkValue);
	}

	@Test
	public void WhenSavedNewBookingThenMapsShouldHaveSameSize() {
		assertEquals(bookingRepo.bookingList.size(), bookingRepo.bookingPassengersList.size());
	}

	@Test
	public void WhenGetBookingInfosWithValidKeyShouldReturnInfos() {
		// When
		BookingPassengers checkBookingInfos = bookingRepo.getBookingInfos(VALID_KEY);

		// Then
		assertEquals(BOOKING_PASSENGERS_TST, checkBookingInfos);
	}

	@Test
	public void WhenGetBookingInfosWithInvalidKeyShouldReturnNull() {
		// When
		BookingPassengers checkBookingInfos = bookingRepo.getBookingInfos(INVALID_KEY);

		// Then
		assertEquals(null, checkBookingInfos);
	}

}
