package ca.ulaval.glo6002.flycheckin.reservation.infrastructure;

import java.util.Map;

import ca.ulaval.glo6002.flycheckin.reservation.domain.Booking;
import ca.ulaval.glo6002.flycheckin.reservation.domain.BookingPassengers;

public class BookingRepository {

	// private Booking book;
	// private BookingPassengers flyPassengers;

	/*
	 * public BookingRepository(Booking booking, BookingPassengers
	 * bookingPassengers) { this.book = booking; this.flyPassengers =
	 * bookingPassengers; }
	 */
	static Map<Integer, Booking> listBooking;
	static Map<Integer, Booking> listBookingPassengers;

	public int saveNewBooking(Booking booking, BookingPassengers bookingPassengers) {
		int number = booking.getReservationNumber();
		if (saveNewBookingPassengers(number, bookingPassengers))
			return number;
		else
			return 0;
	}

	public boolean saveNewBookingPassengers(int bookingNumber, BookingPassengers bookingPassengers) {
		return false;
	}
}
