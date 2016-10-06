package ca.ulaval.glo6002.flycheckin.reservation.domain;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo6002.flycheckin.reservation.infrastructure.BookingRepository;

public class ServicesTest {

	private Booking booking;
	private BookingPassengers booking_passenger;
	private JSONObject json;
	private JSONArray mock_json_array;
	private JSONObject mock_json_passenger;
	private BookingRepository bookingRepository;
	private Services services;

	@Before
	public void setUpServicesTest() {
		this.booking = mock(Booking.class);
		this.booking_passenger = mock(BookingPassengers.class);
		this.json = mock(JSONObject.class);
		this.bookingRepository = mock(BookingRepository.class);
		this.mock_json_array = mock(JSONArray.class);
		this.mock_json_passenger = mock(JSONObject.class);

		this.services = new Services(this.bookingRepository);

		willReturn("2015-09-09").given(this.json).getString("flight_date");
		willReturn(this.mock_json_array).given(this.json).getJSONArray("passengers");
		willReturn(1).given(this.mock_json_array).length();
		willReturn(this.mock_json_passenger).given(this.mock_json_array).getJSONObject(0);
		willReturn("B0074584").given(this.mock_json_passenger).getString("passport_number");
		willReturn("AR5673").given(this.mock_json_passenger).getString("flight_number");
	}

	@Test
	public void givenJSONWhenCreateReservationThenVerifyIfJsonToBookingCalled() throws JSONException, ParseException {
		this.booking = this.services.JsonToBooking(this.json);
	}

}
