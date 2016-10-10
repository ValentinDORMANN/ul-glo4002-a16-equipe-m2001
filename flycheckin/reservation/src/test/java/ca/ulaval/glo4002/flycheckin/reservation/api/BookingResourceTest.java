package ca.ulaval.glo4002.flycheckin.reservation.api;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.willThrow;



import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class BookingResourceTest {

	private BookingResource bookingResource;
	private JSONObject jsonTest = new JSONObject();

	public final String DATE = "2016/01/30";
	public final String FLIGHT_DATE = "2016-10-30T00:00:00Z";
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

	@Test(expected = Exception.class)
	public void givenJsonMessageWhenReadThenValidateReservationDate() {
		willThrow(Exception.class).given(bookingResource).validateJson(jsonTest);
		jsonTest.remove("reservation_date");
		
		bookingResource.validateJson(jsonTest);
	}

	@Test(expected = Exception.class)
	public void givenJsonMessageWhenReadThenValidateFlightDate() {
		willThrow(Exception.class).given(bookingResource).validateJson(jsonTest);
		jsonTest.remove("flight_date");
		
		bookingResource.validateJson(jsonTest);
	}

	@Test(expected = Exception.class)
	public void givenJsonMessageWhenReadThenValidateBookingNumber() {
		willThrow(Exception.class).given(bookingResource).validateJson(jsonTest);
		jsonTest.remove("reservation_number");
		
		bookingResource.validateJson(jsonTest);
	}

	@Test(expected = Exception.class)
	public void givenJsonMessageWhenReadThenValidateFirstName() {
		willThrow(Exception.class).given(bookingResource).validateJson(jsonTest);
		jsonTest.remove("flight_number");
		
		bookingResource.validateJson(jsonTest);
	}

	@Test()
	public void givenRequestMessageWhenHavingInValideRequestThenVerify() throws RuntimeException {
		
		assertFalse(bookingResource.validateJson(jsonTest));
		
	}

}
