package ca.ulaval.glo6002.flycheckin.reservation.domain;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

public class BookingTest {

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private int RESERVATION_NUMBER = 37353;
	private String RESERVATION_DATE = "2016-10-03";
	private String RESERVATION_CONFIRMATION = "A3833";
	private String FLIGHT_NUMBER = "AC1765";
	private String FLIGHT_DATE = "2016-10-30";
	private String PAYMENT_LOCATION = "payments/da3944";

	private Booking booking;

	@Before
	public void InitializeBooking() throws ParseException {
		this.booking = new Booking(this.RESERVATION_NUMBER, this.RESERVATION_DATE, this.RESERVATION_CONFIRMATION,
				this.PAYMENT_LOCATION);

	}

	@Test
	public void lookIfReservationNumberIsTheSame() {
		assertEquals(this.RESERVATION_NUMBER, this.booking.getReservationNumber());
	}

	@Test
	public void lookIfReservationDateIsTheSame() throws ParseException {
		assertEquals(this.RESERVATION_DATE, this.booking.getReservationDate());
	}

	@Test
	public void lookIfReservationConfirmationIsTheSame() {
		assertEquals(this.RESERVATION_CONFIRMATION, this.booking.getReservationConfirmation());
	}

	@Test
	public void lookIfPaymentLocationIsTheSame() {
		assertEquals(this.PAYMENT_LOCATION, this.booking.getPaymentLocation());
	}

}
