package ca.ulaval.glo4002.flycheckin.reservation.api;

import static org.mockito.Mockito.*;

import org.json.JSONObject;
import org.junit.Before;

public class BookingResourceTest {
	private BookingResource bookingResource;
	private JSONObject mockJson = mock(JSONObject.class);
	private JSONObject mockJsonWrong = mock(JSONObject.class);
	public final static String DATE = "2016/01/30";

	@Before
	public void initialization() {
		bookingResource = new BookingResource();

	}
	/*
	 * @Test(expected = Exception.class) public void
	 * givenJsonMessageWhenReadThenValidateReservationDate() {
	 * willThrow(Exception.class).given(bookingResource)
	 * .validateReservationDate(mockJsonWrong.getString("reservation_date"));
	 * bookingResource.validateReservationDate(mockJsonWrong.getString(
	 * "reservation_date"));
	 * 
	 * String reservation_number = mockJson.getString("reservation_number");
	 * String date = mockJson.getString("reservation_date"); String
	 * reservation_confirmation =
	 * mockJson.getString("reservation_confirmation"); String flight_number =
	 * mockJson.getString("AC1765"); String flight_date =
	 * mockJson.getString("2016-10-30T00:00:00Z"); }
	 * 
	 * @Test(expected = Exception.class) public void
	 * givenJsonMessageWhenReadThenValidateFlightDate() {
	 * willThrow(Exception.class).given(bookingResource).validateflightDate(
	 * mockJsonWrong.getString("flight_date"));
	 * bookingResource.validateflightDate(mockJsonWrong.getString("flight_date")
	 * ); }
	 * 
	 * @Test(expected = Exception.class) public void
	 * givenJsonMessageWhenReadThenValidateBookingNumber() {
	 * willThrow(Exception.class).given(bookingResource)
	 * .validateBookingNumber(mockJsonWrong.getString("reservation_number"));
	 * bookingResource.validateBookingNumber(mockJsonWrong.getString(
	 * "reservation_number"));
	 * 
	 * }
	 * 
	 * @Test(expected = Exception.class) public void
	 * givenJsonMessageWhenReadThenValidateFirstName() {
	 * willThrow(Exception.class).given(bookingResource).validateFirstName(
	 * mockJsonWrong.getString("firstname"));
	 * bookingResource.validateFirstName(mockJsonWrong.getString("firstname"));
	 * 
	 * }
	 * 
	 * @Test(expected = Exception.class) public void
	 * givenJsonMessageWhenReadThenValidateLastName() {
	 * willThrow(Exception.class).given(bookingResource).validateLastName(
	 * mockJsonWrong.getString("name"));
	 * bookingResource.validateLastName(mockJsonWrong.getString("name"));
	 * 
	 * }
	 * 
	 * 
	 * @Test(expected = Exception.class) public void
	 * givenJsonMessageWhenReadThenValidatePassPort() {
	 * willThrow(Exception.class).given(bookingResource).validatePassport(
	 * mockJsonWrong.getString("name"));
	 * bookingResource.validatePassport(mockJsonWrong.getString("name"));
	 * 
	 * }
	 * 
	 * 
	 * @Test(expected = RuntimeException.class) public void
	 * givenRequestMessageWhenHavingBadRequestThenException() throws
	 * RuntimeException {
	 * willThrow(RuntimeException.class).given(bookingResource).
	 * validateReservationJson(); bookingResource.validateReservationJson();// à
	 * voir
	 * 
	 * }
	 */

}
