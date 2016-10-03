package ca.ulaval.glo6002.flycheckin.reservation.domain;

import static org.mockito.Mockito.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class ServicesTest {

	private Booking booking;
	private BookingPassengers booking_passenger;
	private JSONObject json;

	@Before
	public void setUpServicesTest() {
		this.booking = mock(Booking.class);
		this.booking_passenger = mock(BookingPassengers.class);
		this.json = mock(JSONObject.class);
	}

	@Test
	public void test() {
		// fail("Not yet implemented");
	}

}
