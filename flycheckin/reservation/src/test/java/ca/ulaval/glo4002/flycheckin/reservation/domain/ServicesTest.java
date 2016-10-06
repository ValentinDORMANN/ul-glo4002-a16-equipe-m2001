package ca.ulaval.glo4002.flycheckin.reservation.domain;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Booking;
import ca.ulaval.glo4002.flycheckin.reservation.domain.BookingPassengers;
import ca.ulaval.glo4002.flycheckin.reservation.domain.Services;
import ca.ulaval.glo4002.flycheckin.reservation.infrastructure.BookingRepository;

public class ServicesTest {

	private static final int NB_passengers = 1;

	private Booking booking;
	private BookingPassengers booking_passenger;
	private List<Passenger> list_passenger;
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

		this.services = new Services();

		willReturn("2015-09-09").given(this.json).getString("flight_date");
		willReturn(this.mock_json_array).given(this.json).getJSONArray("passengers");
		willReturn(NB_passengers).given(this.mock_json_array).length();
		willReturn(this.mock_json_passenger).given(this.mock_json_array).getJSONObject(NB_passengers - 1);
		willReturn("B0074584").given(this.mock_json_passenger).getString("passport_number");
		willReturn("AR5673").given(this.mock_json_passenger).getString("flight_number");
	}

	@Test
	public void givenJSONWhenCreateReservationThenVerifyIfJsonToBookingCalled() throws JSONException, ParseException {
		this.booking = this.services.JsonToBooking(this.json);
		verify(this.json).getInt("reservation_number");
		verify(this.json).getString("reservation_date");
		verify(this.json).getString("reservation_confirmation");
		verify(this.json).getString("payment_location");
	}

	@Test
	public void givenJSONWhenCreateReservationThenVerifyIfJsonToPassengersCalledAndReturnList()
			throws JSONException, ParseException {
		this.list_passenger = this.services.JsonToPassengers(this.json);
		verify(this.json).getString("flight_date");
		verify(this.json).getJSONArray("passengers");
		assertEquals(NB_passengers, this.list_passenger.size());
	}

	@Test
	public void givenJSONWhenCreateReservationThenVerifyIfJsonToBookingPassenger()
			throws JSONException, ParseException {
		this.booking_passenger = this.services.JsonToBookingPassenger(this.json);
		verify(this.json).getString("flight_number");
		verify(this.json, atLeast(2)).getString("flight_date");

	}

}
