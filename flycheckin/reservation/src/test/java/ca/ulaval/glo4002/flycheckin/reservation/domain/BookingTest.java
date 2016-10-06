package ca.ulaval.glo4002.flycheckin.reservation.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Booking;

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

}
