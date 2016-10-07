package ca.ulaval.glo4002.flycheckin.reservation.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Services;

@Path("/events/reservation-created")
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {
	private Services service;
	private JSONObject jsonRequest;
	private boolean validatedJson = true;

	@POST
	public Response createBooking(String bookingRequest) throws JSONException, ParseException {

		jsonRequest = new JSONObject(bookingRequest);
		int reservationNumber = 0;
		service = new Services();
		reservationNumber = service.createReservation(jsonRequest);

		// String informationOfBookingNumber= "reservations" +"/{"+"
		// reservation_number::";
		return Response.ok(reservationNumber).build();
	}

	@GET
	@Path("/reservations/{reservation_number}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNumberResevation(@QueryParam("reservationNumber") String reservationNumber)
			throws JSONException, ParseException {
		service = new Services();
		JSONObject jsonObject = service.getReservation(Integer.parseInt(reservationNumber));
		return Response.ok(jsonObject.toString()).build();
	}

	public boolean validateReservationDate(String reservationDate) {
		if (!reservationDate.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
			validatedJson = false;
		}
		return validatedJson;

	}

/*	public String extractFlightDate(String flightDate) throws RuntimeException {
		String date = flightDate.substring(0, 9);
		if (validateReservationDate(date)) {
			return flightDate.substring(0, 9);
		} else {
			throw new RuntimeException();
		}

	}*/

	public boolean validateflightDate(String flightDate) {
		boolean validateDate = true;
		try {
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ");
			 java.util.Date dateformatter = date.parse(flightDate);
		} catch (Exception e) {
			validateDate = false;
		}
		return validateDate;

	}

	public boolean validateBookingNumber(String bookingNumber) {
		 if (!bookingNumber.matches("^[0-9]+$")){
			validatedJson =false;
		 }
		 return validatedJson;

	}

	public boolean validateFirstName(String firstname) {
		if(!firstname.matches("^[a-zA-Z]+$")){
			validatedJson = false;
		}
		return validatedJson;

	}

	public boolean validateLastName(String name) {
		 if(!name.matches("^[a-zA-Z]+$")){
			 validatedJson = false;
		 }
		 return validatedJson;

	}

	public boolean validatePassport(String passport) {
		if(passport.isEmpty()){
			validatedJson = false;
		}
		return validatedJson;

	}

	public boolean validateReservationJson() {
		return validatedJson; 
		
	}
}