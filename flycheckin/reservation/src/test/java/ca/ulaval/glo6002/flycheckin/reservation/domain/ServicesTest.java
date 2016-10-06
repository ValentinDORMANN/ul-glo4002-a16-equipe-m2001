package ca.ulaval.glo6002.flycheckin.reservation.domain;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo6002.flycheckin.reservation.infrastructure.BookingRepository;

public class ServicesTest {

	private Booking booking;
	private BookingPassengers booking_passenger;
	private JSONObject json;
	private BookingRepository bookingRepository;
	private Services services;

	@Before
	public void setUpServicesTest() {
		this.booking = mock(Booking.class);
		this.booking_passenger = mock(BookingPassengers.class);
		this.json = mock(JSONObject.class);
		this.bookingRepository = mock(BookingRepository.class);

		this.services = new Services(this.bookingRepository);
	}

	@Test
	public void givenJSONWhenCreateReservationThenVerifyIfJsonToBookingCalled() throws JSONException, ParseException {
		// this.json.append("flight_date", "2016-09-08");
		willReturn("2015-09-09").given(this.json).getString("flight_date");

		this.services.JsonToPassengers(this.json);

	}

}
