package ca.ulaval.glo6002.flycheckin.reservation.infrastructure;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo6002.flycheckin.reservation.domain.Booking;
import ca.ulaval.glo6002.flycheckin.reservation.domain.BookingPassengers;
import ca.ulaval.glo6002.flycheckin.reservation.domain.Passenger;

//import ca.ulaval.glo6002.flycheckin.reservation.domain.Booking;
//import ca.ulaval.glo6002.flycheckin.reservation.domain.BookingPassengers;

public class BookingRepositoryTest {
	private int validKey = 44444;
	private int invalidKey = 99999;
	private BookingRepository bookingRepo;
	Booking bookingTst;
	Passenger person;
	BookingPassengers bookingPassengersTst;
	// Booking mockBooking = mock(Booking.class);

	@Before
	public void initiateTest() throws ParseException {
		person = new Passenger("Taylor", "Mahugnon", 12, "B0074584", "economic", "B0074584:AF-305:1121");
		List<Passenger> listPassengers = new ArrayList<Passenger>();
		listPassengers.add(person);
		bookingTst = new Booking(validKey, "date", "confirm", "paymentLoc");
		bookingPassengersTst = new BookingPassengers(listPassengers, "AF-305", "2016-11-21");
	}

	@Test
	public void WhenSavedNewBookingThenReturnBookingNumber() {
		bookingRepo = new BookingRepository();
		int value = bookingRepo.saveNewBooking(bookingTst, bookingPassengersTst);

		assertEquals(validKey, value);
	}

	@Test
	public void WhenSavedNewBookingThenListsShouldHaveSameSize() {
		bookingRepo = new BookingRepository();
		bookingRepo.saveNewBooking(bookingTst, bookingPassengersTst);

		assertEquals(bookingRepo.bookingList.size(), bookingRepo.bookingPassengersList.size());
	}

	/*
	 * @Test public void WhenSavedBookingInfoThenBookingListShouldNotBeEmpty() {
	 * bookingRepo = new BookingRepository();
	 * bookingRepo.saveBookingInfo(bookingTest);
	 * 
	 * assertFalse(bookingRepo.bookingList.isEmpty()); }
	 */

}
