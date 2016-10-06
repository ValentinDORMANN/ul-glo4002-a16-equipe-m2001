package ca.ulaval.glo4002.flycheckin.reservation.api;


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.fasterxml.jackson.jaxrs.json.annotation.JSONP;

import ca.ulaval.glo4002.flycheckin.reservation.domain.BookingPassengers;


@Path("/events/reservation-created")
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {
	private JSONObject json;
	
	public BookingResource(JSONObject json){
		this.json = json;
		
	}

	@POST
    public void createBooking(String message) {
    	
    }

	public boolean validateReservationDate(String reservationDate) {
		//String date =json.getString("reservation_date");
		if (reservationDate.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")){
			return true;
		}
		return false;
		
	}

	public String extractFlightDate(String flightDate) throws RuntimeException {
		String date = flightDate.substring(0, 9);
		if(validateReservationDate(date)){
			return flightDate.substring(0, 9);
		}
		else{
			throw new RuntimeException();
		}
		
	
		
	
}

	public boolean validateflightDate(String flightDate) {
		boolean validateDate =true;
		try{
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ"); 
			java.util.Date dateformatter = date.parse(flightDate);
		}catch(Exception e){
			validateDate = false;
		}
		return validateDate;
		
		
	}

	public boolean validateBookingNumber(String bookingNumber) {
		return bookingNumber.matches("^[0-9]+$");
		
		
		
	}

	public boolean validateFirstName(String firstname) {
		return firstname.matches("^[a-zA-Z]+$");
		
	}

	public boolean validateLastName(String name) {
		return name.matches("^[a-zA-Z]+$");
		
	}

	public boolean validatePassport(String passport) {
		return !passport.isEmpty();
		
	}
	
}
