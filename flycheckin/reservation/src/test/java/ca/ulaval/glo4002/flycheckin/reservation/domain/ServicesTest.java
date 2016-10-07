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

import ca.ulaval.glo4002.flycheckin.reservation.infrastructure.BookingRepository;
import javassist.NotFoundException;

public class ServicesTest {

	private static final int NB_passengers = 1;
	private static final int RESERVATION_NUMBER = 37353;
	private static final String FLIGHT_DATE = "2015-09-09";
	private static final String PASSPORT_NUMBER = "B0074584";
	private static final String FLIGHT_NUMBER = "AR5673";

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

		willReturn(RESERVATION_NUMBER).given(this.json).getInt("reservation_number");
		willReturn(FLIGHT_DATE).given(this.json).getString("flight_date");
		willReturn(this.mock_json_array).given(this.json).getJSONArray("passengers");
		willReturn(NB_passengers).given(this.mock_json_array).length();
		willReturn(this.mock_json_passenger).given(this.mock_json_array).getJSONObject(NB_passengers - 1);
		willReturn(PASSPORT_NUMBER).given(this.mock_json_passenger).getString("passport_number");
		willReturn(FLIGHT_NUMBER).given(this.mock_json_passenger).getString("flight_number");
	}

	/* Set Reservation */

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

	@Test
	public void givenReservationNumberWhenCreateReservationThenCompareNumber() throws JSONException, ParseException {
		int number = this.services.createReservation(this.json);
		assertEquals(this.RESERVATION_NUMBER, number);
	}

	/* Get Reservation */

	@Test
	public void test() throws JSONException, ParseException, NotFoundException {
		this.booking_passenger = this.services.JsonToBookingPassenger(this.json);
		int number = this.bookingRepository.saveNewBooking(this.booking, this.booking_passenger);
		// assertEquals(this.booking_passenger,
		// this.bookingRepository.getBookingInfos(number));
	}
}
