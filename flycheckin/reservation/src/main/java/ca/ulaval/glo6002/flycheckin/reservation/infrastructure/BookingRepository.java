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

	public boolean saveNewBooking(Booking booking, BookingPassengers bookingPassengers) {
		int number = booking.getReservationNumber();
		return saveNewBookingPassengers(number, bookingPassengers);
	}

	public boolean saveNewBookingPassengers(int bookingNumber, BookingPassengers bookingPassengers) {
		return false;
	}
}
