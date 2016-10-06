package ca.ulaval.glo4002.flycheckin.reservation.api;

import static org.junit.Assert.*;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;


import com.fasterxml.jackson.databind.JsonNode;

import ca.ulaval.glo4002.flycheckin.reservation.api.BookingResource;

public class BookingResourceTest {
	private BookingResource bookingResource;
	private JSONObject mockJson=mock(JSONObject.class);
	private JSONObject mockJsonWrongDate=mock(JSONObject.class);  
	public final static String DATE = "2016/01/30";
	
	@Before
	public void initialization(){
		bookingResource = new BookingResource(mockJson);
		
	}

	@Test(expected=Exception.class)
	public void givenJsonMessageWhenReadThenValidateReservationDate() {
		willThrow(Exception.class).given(bookingResource).validateReservationDate(mockJsonWrongDate.getString("reservation_date"));
		bookingResource.validateReservationDate(mockJsonWrongDate.getString("reservation_date"));
		
		/*String reservation_number= mockJson.getString("reservation_number");
		String date = mockJson.getString("reservation_date");
		String reservation_confirmation=mockJson.getString("reservation_confirmation");
		String flight_number = mockJson.getString("AC1765");
		String flight_date = mockJson.getString("2016-10-30T00:00:00Z");*/
	}
	
	@Test(expected=Exception.class)
	public void givenJsonMessageWhenReadThenValidateFlightDate() {
		willThrow(Exception.class).given(bookingResource).validateflightDate(mockJsonWrongDate.getString("flight_date"));
		bookingResource.validateflightDate(mockJsonWrongDate.getString("flight_date"));
	}
	
	@Test(expected=Exception.class)
	public void givenJsonMessageWhenReadThenValidateBookingNumber() {
		willThrow(Exception.class).given(bookingResource).validateBookingNumber(mockJsonWrongDate.getString("reservation_number"));
		bookingResource.validateBookingNumber(mockJsonWrongDate.getString("reservation_number"));
		
	}
	
	@Test(expected=Exception.class)
	public void givenJsonMessageWhenReadThenValidateFirstName() {
		willThrow(Exception.class).given(bookingResource).validateFirstName(mockJsonWrongDate.getString("firstname"));
		bookingResource.validateFirstName(mockJsonWrongDate.getString("firstname"));
		
	}
	
	@Test(expected=Exception.class)
	public void givenJsonMessageWhenReadThenValidateLastName() {
		willThrow(Exception.class).given(bookingResource).validateLastName(mockJsonWrongDate.getString("name"));
		bookingResource.validateLastName(mockJsonWrongDate.getString("name"));
		
	}
	
	@Test(expected=Exception.class)
	public void givenJsonMessageWhenReadThenValidatePassPort() {
		willThrow(Exception.class).given(bookingResource).validatePassport(mockJsonWrongDate.getString("name"));
		bookingResource.validatePassport(mockJsonWrongDate.getString("name"));
		
	}
	
	@Test()
	public void givenJsonMessageWhenExtractFliyingDateThenValidateDate() throws RuntimeException{
	//	willThrow(RuntimeException.class).given(bookingResource).validateReservationDate(mockJsonWrongDate.getString("flight_date"));
	//s	bookingResource.extractFlightDate(mockJsonWrongDate.getString("flight_date"));
		
		/*String reservation_number= mockJson.getString("reservation_number");
		String date = mockJson.getString("reservation_date");
		String reservation_confirmation=mockJson.getString("reservation_confirmation");
		String flight_number = mockJson.getString("AC1765");
		String flight_date = mockJson.getString("2016-10-30T00:00:00Z");*/
	}

}
