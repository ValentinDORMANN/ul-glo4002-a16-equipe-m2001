package ca.ulaval.glo4002.flycheckin.reservation.infrastructure;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Booking;
import ca.ulaval.glo4002.flycheckin.reservation.domain.BookingPassengers;
import javassist.NotFoundException;

public class BookingRepository {

	public static Map<Integer, Booking> bookingList = new HashMap<Integer, Booking>();;
	public static Map<Integer, BookingPassengers> bookingPassengersList = new HashMap<Integer, BookingPassengers>();

	public int saveNewBooking(Booking booking, BookingPassengers bookingPassengers) {
		int number = booking.getReservationNumber();
		bookingList.put(number, booking);
		bookingPassengersList.put(number, bookingPassengers);
		return number;
	}

	public BookingPassengers getBookingInfos(int bookingNumber) throws NotFoundException {
		BookingPassengers bookingPassengers;
		bookingPassengers = bookingPassengersList.get(bookingNumber);
		if (isNull(bookingPassengers))
			throw new NotFoundException("BOOKING NOT FOUND");
		return bookingPassengers;
	}

	private boolean isNull(Object obj) {
		return obj == null;
	}

}
