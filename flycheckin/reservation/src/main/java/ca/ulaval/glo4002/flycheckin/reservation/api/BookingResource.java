package ca.ulaval.glo4002.flycheckin.reservation.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import ca.ulaval.glo4002.flycheckin.reservation.domain.Services;

@Path("/events/reservation-created")
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {
	private Services service ;
	private JSONObject json;

	@POST
	public String createBooking(String bookingRequest)  {

		json = new JSONObject(bookingRequest);
		int reservationNumber =0;
		try {
			service = new Services();
			reservationNumber = service.createReservation(json);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ""+reservationNumber ;
	}
/*	@GET
	public String getNumberResevation(Services service ,@QueryParam("token") String token) throws JSONException, ParseException{
		int reservationNumber = service.createReservation(json);
		return "/reservations/{reservation_number::"+reservationNumber+"}";
	}*/

	public boolean validateReservationDate(String reservationDate) {
		// String date =json.getString("reservation_date");
		if (reservationDate.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
			return true;
		}
		return false;

	}

	/*public String extractFlightDate(String flightDate) throws RuntimeException {
		String date = flightDate.substring(0, 9);
		if (validateReservationDate(date)) {
			return flightDate.substring(0, 9);
		} else {
			throw new RuntimeException();
		}

	}

	public boolean validateflightDate(String flightDate) {
		boolean validateDate = true;
		try {
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ");
			// java.util.Date dateformatter = date.parse(flightDate);
		} catch (Exception e) {
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

	}*/
}