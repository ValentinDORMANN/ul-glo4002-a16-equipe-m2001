package ca.ulaval.glo4002.flycheckin.reservation.api;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class BookingResourceTest {

	private BookingResource bookingResource;
	private JSONObject jsonTest = new JSONObject();

	public final String DATE = "2016/01/30";
	public final String FLIGHT_DATE = "2016-10-30T13:45:30Z";
	public final String RESERVATION_DATE = "2016-01-31";
	public final int RESERVATION_NUMBER = 37353;
	public final String FLIGHT_NUMBER = "AC1765";

	@Before
	public void initialization() {
		bookingResource = new BookingResource();
		jsonTest.put("reservation_number", RESERVATION_NUMBER);
		jsonTest.put("reservation_date", RESERVATION_DATE);
		jsonTest.put("flight_date", FLIGHT_DATE);
		jsonTest.put("flight_number", FLIGHT_NUMBER);
	}

	@Test(expected = JSONException.class)
	public void givenJsonWithMissingReservationDateThenReturnException() {
		// Given
		jsonTest.remove("reservation_date");

		// When
		bookingResource.validateJson(jsonTest);
	}

	@Test
	public void givenJsonWithMissingFlightDateThenReturnException() {
		// Given
		jsonTest.remove("flight_date");

		// When
		boolean valid = bookingResource.validateJson(jsonTest);

		// Then
		assertFalse(valid);
	}

	@Test(expected = JSONException.class)
	public void givenJsonWithMissingReservationNumberThenReturnException() {
		// Given
		jsonTest.remove("reservation_number");

		// When
		bookingResource.validateJson(jsonTest);
	}

	@Test(expected = JSONException.class)
	public void givenJsonWithMissingFlighNumberThenReturnException() {
		// Given
		jsonTest.remove("flight_number");

		// When
		bookingResource.validateJson(jsonTest);
	}

	@Test()
	public void givenValidJsonWhenValidateThenReturnTrue() {

		assertTrue(bookingResource.validateJson(jsonTest));
	}

	/*
	 * @Test public void WhenCreatingBookingThenVerifyServiceCreateReservation()
	 * throws NotFoundException { // When UriInfo uriInfo;
	 * bookingResource.createBooking(uriInfo, bookingRequest); jsonTest= new
	 * JSONObject(bookingRequest)
	 * 
	 * // Then verify(mockService).createReservation(jsonTest); }
	 */

}
