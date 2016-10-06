package ca.ulaval.glo4002.flycheckin.reservation.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Booking;
import ca.ulaval.glo4002.flycheckin.reservation.domain.BookingPassengers;

public class BookingRepository {

	public static Map<Integer, Booking> bookingList = new HashMap<Integer, Booking>();;
	public static Map<Integer, BookingPassengers> bookingPassengersList = new HashMap<Integer, BookingPassengers>();

	public int saveNewBooking(Booking booking, BookingPassengers bookingPassengers) {
		int number = booking.getReservationNumber();
		bookingList.put(number, booking);
		bookingPassengersList.put(number, bookingPassengers);
		return number;
	}

	public BookingPassengers getBookingInfos(int bookingNumber) {
		return bookingPassengersList.get(bookingNumber);
	}

	/*
	 * private boolean isNull(Object obj) { return obj == null; }
	 */
}
